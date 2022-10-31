package jjy.member.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import jjy.member.model.*;

public class MembershipController extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();// "GET" or "POST"; 
		if(!"POST".equalsIgnoreCase(method)) { // "POST" 방식일 경우 
			HttpSession session = request.getSession(); 
			String loginuserid = (String)session.getAttribute("userid"); // 세션에 저장된 사용자 아이디 불러오기
			
			if(loginuserid != null) {
				InterMemberDAO mdao = new MemberDAO();
				Map<String, String> useridMap = new HashMap<>();
				useridMap.put("loginuserid", loginuserid);
				super.setViewPage("/WEB-INF/view/member/membership.jsp"); // 멤버십 페이지로 이동
			}
			else {
				super.setViewPage("/WEB-INF/view/index.jsp"); // 인덱스 페이지로 이동
			}
		
		}
	}
}
