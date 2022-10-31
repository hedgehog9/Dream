package jjy.login.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import jjy.login.model.*;
import jjy.member.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import common.controller.AbstractController;

public class LoginController extends AbstractController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod(); // "GET" or "POST"

		if ("GET".equalsIgnoreCase(method)) { // "GET" 방식
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/login/login.jsp");
			
		} else { // "POST" 방식일때
			
			String userid = request.getParameter("userid"); 
			String passwd = request.getParameter("passwd"); 
			String client_ip = request.getRemoteAddr();     
			
			InterLoginDAO logindao = new LoginDAO();
			// userid, password 를 각각의 키값으로 저장
			Map<String, String> userinfoMap = new HashMap<>(); 
			userinfoMap.put("userid", userid);
			userinfoMap.put("passwd", passwd);
			userinfoMap.put("client_ip", client_ip);
			
			boolean isFirstLogin = false;       // 첫 로그인 여부 
			boolean isSecession = false;        // 탈퇴 회원 여부
			boolean isRestMember = false;       // 휴면 회원 여부
			boolean isMembership = false;       // 멤버십 가입 여부
			boolean isRequirePwdChange = false; // 비밀번호 가입 여부
			boolean isMembershipGap = false;    // 멤버십 가입 기간 체크
			boolean isUserExists = false;       // 올바른 아이디, 비밀번호 체크 여부 
			
			
			if("admin".equals(userid)) { 
				boolean isAdmin = logindao.selectAdmin(userinfoMap);
				
				JSONObject jsonObj = new JSONObject();
				
				jsonObj.put("userid", userid);
				jsonObj.put("isSecession", false);        // json으로 전달할 탈퇴 회원 여부
				jsonObj.put("isMembership", false);       // json으로 전달할 멤버십 가입 여부
				jsonObj.put("isMembershipGap", false);    // json으로 전달할 멤버십 기간 1달 초과 여부
				jsonObj.put("isRequirePwdChange", false); // json으로 전달할 마지막 비밀번호 변경일 기간 1달 초과 여부
				jsonObj.put("isRestMember", false);       // json으로 전달할 휴면사용자 여부
				jsonObj.put("isUserExists", isAdmin);     // 아이디 비밀번호 일치하는 경우 
				
				HttpSession session = request.getSession();
				
				String goBackURL = (String)session.getAttribute("goBackURL");
				if(goBackURL != null) {
				    jsonObj.put("goBackURL", goBackURL); // 아이디 비밀번호 일치하는 경우 
				}
				else { // 인덱스페이지 또는 home 인경우 
					goBackURL = "/index.dream"; // 시작 홈페이지로 이동
			    	jsonObj.put("goBackURL", goBackURL); // 아이디 비밀번호 일치하는 경우 
				}
				
				String json = jsonObj.toString();
				request.setAttribute("json", json);
				logindao.updateLoginDate(userinfoMap);// 로그인 기록
				
				if(isAdmin) {
					session.setAttribute("userid", "admin");
				}

				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/jsonview.jsp");
				
			}
			else { // 입력받은 아이디가 일반 사용자인 경우 
				
				// 사용자 정보를 저장하는 객체( userid , passwd , secession , rest_member ,update_passwd_date)
				LoginDTO loginuser = logindao.selectOneUser(userinfoMap);
				
				if (loginuser != null) { // 로그인 사용자 정보가 있는 경우
					isUserExists = true;
					
					isFirstLogin = logindao.checkFirstLogin(userid);
					
					InterMemberDAO mdao = new MemberDAO();
					MemberDTO mdto = new MemberDTO();
	
					mdto = mdao.selectOneUser(userid);
	
					if (mdto.getMembership() == 1) { // 멤버십 가입 회원일 경우 날짜 비교 시작 
						isMembership = true;
						if (mdto.getMembershipregistgap() > 1) {
							
							int result = mdao.deleteMembership(userid);
							
							if (result == 1) {
								isMembership = false;
								isMembershipGap = true;
							} else {
							}
						}
					}
					////////////////////////////////////////////////////////////////////////////////////////////////////////////
					// 만약 마지막 로그인 날짜가 현재로부터 1년이 지난경우 휴면유저 처리 시작
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String lastLoginDate = logindao.checkLastLoginDate(userid); // 로그인에 기록된 마지막 로그인날짜
					String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())); // 현재날짜
	
					if (lastLoginDate == null) { // 만약 로그인 기록이 없는 경우
						lastLoginDate = logindao.checkRegistDate(userid);
					} else {
						lastLoginDate = lastLoginDate.substring(0, 11);
					}
	
					Date format1 = new SimpleDateFormat("yyyy-MM-dd").parse(lastLoginDate);
					Date format2 = new SimpleDateFormat("yyyy-MM-dd").parse(today);
	
					long diffSec = (format2.getTime() - format1.getTime()) / 1000; // 초 차이
					long diffDays = diffSec / (24 * 60 * 60); // 일자수 차이
	
					if (diffDays >= 365) {
						logindao.updateRestMember(userid);
						isRestMember = true;
					}
					// 만약 마지막 로그인 날짜가 현재로부터 1년이 지난경우 휴면유저 처리 끝
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////
					else {
						int secession = Integer.parseInt(loginuser.getSecession()); // 탈퇴 유무
						int restMember = Integer.parseInt(loginuser.getRest_member()); // 휴면 유무
	
//						System.out.println("확인용secession = " + secession);
//						System.out.println("확인용 restMember = " + restMember);
	
						if (secession == 1) { // 탈퇴 회원인 경우
							isSecession = true; // json으로 전달할 탈퇴 회원
						} 
						else { // 탈퇴 회원이 아닌경우
							if (restMember == 1) { // 휴면 사용자인경우
	//						System.out.println("##확인용## 로그인 실패 휴면 회원.");
								isRestMember = true; // json으로 전달할 휴면 회원 여부
	
							} else { // 휴면사용자가 아닌경우
	
								if (loginuser.isRequirePwdChange()) {
									isRequirePwdChange = true;
	
									// session 영역에 로그인 한 사용자 아이디(userid) 저장
									HttpSession session = request.getSession();
									session.setAttribute("userid", userid);
	
							//		System.out.println("확인용 로그인된 아이디 : " + loginuser.getUserid());
									System.out.println("로그인 컨트롤러 확인용 session에 저장된 아이디 : " + session.getAttribute("userid"));
								} else {
							//		System.out.println("loginController 에서 출력 ## 확인용 ## 로그인 성공!");
	
									// session 영역에 로그인 한 사용자 아이디(userid) 저장
									HttpSession session = request.getSession();
									session.setAttribute("userid", userid);
									
									logindao.updateLoginDate(userinfoMap);// 로그인 기록
	
								}
							}
						}
					} // 탈퇴한 회원이 아닌경우 끝
					// ---------------------------------------------------------------------------------
	
					} // if (loginuser != null) {}-------------------------------------------------------
					
					/*
					System.out.println("확인용 userid : " + userid);
					System.out.println("확인용 isSecession: " + isSecession);
					System.out.println("확인용 : isMembership " + isMembership);
					System.out.println("확인용 : isMembershipGap " + isMembershipGap);
					System.out.println("확인용 : isRequirePwdChange" + isRequirePwdChange);
					System.out.println("확인용 : isRestMember" + isRestMember);
					System.out.println("확인용 : isUserExists" + isUserExists);
					*/
					// 제이슨 객체생성
					JSONObject jsonObj = new JSONObject();
					
					// System.out.println("확인용 isFirstLogin "+isFirstLogin);
					
					// 제이슨에 값 담기
					jsonObj.put("userid", userid);
					jsonObj.put("isFirstLogin", isFirstLogin); // json으로 전달할 첫 로그인 여부
					jsonObj.put("isSecession", isSecession); // json으로 전달할 탈퇴 회원 여부
					jsonObj.put("isMembership", isMembership); // json으로 전달할 멤버십 가입 여부
					jsonObj.put("isMembershipGap", isMembershipGap); // json으로 전달할 멤버십 기간 1달 초과 여부
					jsonObj.put("isRequirePwdChange", isRequirePwdChange); // json으로 전달할 멤버십 기간 1달 초과 여부
					jsonObj.put("isRestMember", isRestMember); // json으로 전달할 휴면사용자 여부
					jsonObj.put("isUserExists", isUserExists); // 아이디 비밀번호 일치하는 경우 
					
					HttpSession session = request.getSession();
					
					String goBackURL = (String)session.getAttribute("goBackURL");
				    // /shop/prodView.up?pnum=62 (상품을 본 경우)
				    // 또는 
				    // null 
				  
				    if(goBackURL != null) {
				    	jsonObj.put("goBackURL", goBackURL); // 아이디 비밀번호 일치하는 경우 
				    }
				    else { // 인덱스페이지 또는 home 인경우 
					  // 시작페이지로 이동
				    	goBackURL = "/index.dream"; // 시작 홈페이지로 이동
				    	jsonObj.put("goBackURL", goBackURL); // 아이디 비밀번호 일치하는 경우 
				    }
					
					
					String json = jsonObj.toString();
					request.setAttribute("json", json);
	
					// super.setRedirect(false);
					super.setViewPage("/WEB-INF/view/jsonview.jsp");
				} // 괄호 닫기 아래로 이동 필요

			
		} // post else { // "POST" 방식일때 끝  ----------------------------------------------------------------

	}// end of public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {}-----------------------

} // end of public class LoginController extends AbstractController {}---------------------------
