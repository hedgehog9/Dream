package josh.member.controller;

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
			// System.out.println(mdto.getUserid());
			
			request.setAttribute("mdto", mdto);
			
			// super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/member/myInformation.jsp");
		}	
			
		else {
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/login/login.dream");
		}
		
	}
}
