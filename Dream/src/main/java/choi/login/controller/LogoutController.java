package choi.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;

public class LogoutController extends AbstractController{
    
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod(); 
		
		if("GET".equalsIgnoreCase(method)) { // post 방식일경우만 id 중복검사 진행 
			HttpSession session = request.getSession();
			session.removeAttribute("userid");
			
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.dream");
		}// end of if("GET".equalsIgnoreCase(method)) -----
	}
	
}
