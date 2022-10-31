package choi.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;

public class MobileCheck extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		
		if("GET".equalsIgnoreCase(method)) {	//method가 "GET"일 때
			
		}
		else {	//method가 "POST"일 때 
			
		}
	}
	
}
