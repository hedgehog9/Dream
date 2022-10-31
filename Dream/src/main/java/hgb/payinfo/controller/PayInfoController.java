package hgb.payinfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import hgb.payinfo.model.InterPayInfoDAO;
import hgb.payinfo.model.PayInfoDAO;
import hgb.payinfo.model.PayInfoDTO;



public class PayInfoController extends AbstractController{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) {	//method가 "POST" 일 때
			
		}
		
		
		
		
		else {	//method가 "GET"일 때 
			super.setViewPage("/WEB-INF/view/member/payinfo.jsp");
		}
	}
}
