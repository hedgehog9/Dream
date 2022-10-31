package choi.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import choi.member.model.InterMemberDAO;
import choi.member.model.MemberDAO;
import common.controller.AbstractController;

public class JoinController extends AbstractController{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.goBackURL(request);
		String method = request.getMethod();
		
		if("post".equalsIgnoreCase(method)) {	//"POST"요청인 경우
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			String mobile = request.getParameter("mobile");
			String username = userid.substring(0, userid.indexOf("@"));
			
			Map<String,String> paraMap = new HashMap<>();
			paraMap.put("userid", userid);
			paraMap.put("username", username);
			paraMap.put("mobile", mobile);
			paraMap.put("passwd", passwd);
			InterMemberDAO mdao = new MemberDAO();
			
			int n = mdao.memberJoin(paraMap);	//데이터베이스에 회원정보 insert 해주는 함수 호출
			
			String message = "";
			String loc = "";
			if(n != 3) {	//DB에 저장하는게 실패한 경우
				message = "시스템오류로 회원가입에 실패하였습니다. 다시 시도해주세요";
				loc = request.getContextPath()+"/member/join.dream";
				
				request.setAttribute("message", message);
				request.setAttribute("loc", loc);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/msg.jsp");
			}
			
			else {	//DB에 저장하는게 성공한 경우
				message = userid+"님 회원가입에 성공하였습니다.";
				loc = request.getContextPath()+"/login/login.dream";
				
				request.setAttribute("message", message);
				request.setAttribute("loc", loc);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/msg.jsp");
			}
		}
		else {	//"GET"요청인 경우
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/member/join.jsp");
		}
	}
}
