package choi.member.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import common.controller.AbstractController;

public class EmailCheck extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();	//"GET" 또는 "POST"
		
		if("POST".equalsIgnoreCase(method)) {	//"POST"방식일 때 
			//이메일인증번호 전송버튼을 클릭했을 경우
			String userid = request.getParameter("userid");
			
			
			boolean sendMailSuccess = false; // 메일이 정상적으로 전송되었는지 유무를 알아오기 위한 용도
				//인증키를 랜덤하게 생성하도록 한다.
				Random rnd = new Random();
				
				String certificationCode = "";
				// 예: certificationCode ==> dngrn4745003
				//인증키는 영문 소문자 5글자 + 숫자 7글자 로 만들겠습니다.
				
				char randchar= ' ';
				
				for(int i=0; i<5; i++) {
					/*
					 	min 부터 max 사이의 값으로 랜덤한 정수를 얻으려면 
                		int rndnum = rnd.nextInt(max - min + 1) + min;	
					 */
					
					// 영문 소문자 'a'부터 'z' 까지 랜덤하게 1개를 만든다.
					
//						rnd.nextInt('z'- min + 1) + min;
						randchar = (char)(rnd.nextInt('z'-'a'+ 1)+'a');
						certificationCode += randchar;
				}//end of for---
				
				int randnum = 0;
				for(int i=0; i<5; i++) {
					/*
					 	min 부터 max 사이의 값으로 랜덤한 정수를 얻으려면 
                		int rndnum = rnd.nextInt(max - min + 1) + min;	
					 */
					
					// 영문 소문자 'a'부터 'z' 까지 랜덤하게 1개를 만든다.
					
//						rnd.nextInt('z'- min + 1) + min;
					randnum = rnd.nextInt(9 -0 + 1) + 0;
					certificationCode += randnum;
				}//end of for---
//				System.out.println("확인용 certificationCode => "+certificationCode);
				
				// 랜덤하게 생성한 인증코드(certificationCode)를
				GoogleMail mail = new GoogleMail();
				try {
					mail.sendmail(userid,certificationCode);
					sendMailSuccess = true;	//메일 전송했음을 기록함.
					
//					세션 불러오기
					HttpSession session = request.getSession();
					
					session.setAttribute("certificationCode", certificationCode);
					//발급한 인증코드를 세션에 저장시킴.
				} catch(Exception e) {	//메일 전송이 실패한 경우
					sendMailSuccess = false;	//메일 전송 실패했음을 기록함.
				}//end of try-catch()----
				
				
				
			//제이슨 객체생성
			JSONObject jsonobj = new JSONObject();
			//제이슨에 값 담기
			jsonobj.put("userid", userid);
			jsonobj.put("sendMailSuccess", sendMailSuccess);
			jsonobj.put("certificationCode", certificationCode);
			
			String json = jsonobj.toString();
			request.setAttribute("json", json);
			
			// super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/jsonview.jsp");
		}//end of ("POST".equalsIgnoreCase(method))
		else {	//"GET"방식일때
			
		}
		
	}
		

}
