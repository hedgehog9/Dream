package hgb.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import josh.member.model.InterMemberDAO;
import josh.member.model.MemberDAO;
import josh.member.model.MemberDTO;

public class MyInfoController extends AbstractController{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		if( super.checkLogin(request) ) {
			
			HttpSession session = request.getSession();
			String userid = (String) session.getAttribute("userid");
				
			InterMemberDAO mdao = new MemberDAO();
			
			MemberDTO mdto = mdao.selectOne(userid);
			
			
			request.setAttribute("mdto", mdto);
		}	
			
		else {
			// 로그인한 사용자가 다른 사용자의 정보를 수정하는 경우
			String message = "다른 사용자의 정보 변경은 불가합니다.!!";
			String loc = "javascript:history.back()";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
		}
			
		
		
		super.setViewPage("/WEB-INF/view/member/myInformation.jsp");
	}
}
