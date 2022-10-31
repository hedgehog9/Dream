package jjy.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;

public class LoginJsonController extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getContextPath();
		
		if("GET".equalsIgnoreCase(method)) { //"GET" 방식 
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/login/login.jsp");
		}
		else { //"POST" 방식 
			
			
		}
		
	}

}
