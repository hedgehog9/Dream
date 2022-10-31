package jjy.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import common.controller.AbstractController;
import jjy.member.model.InterMemberDAO;
import jjy.member.model.MemberDAO;
import jjy.member.model.MemberDTO;

public class MembershipRegistController extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();
		if("POST".equalsIgnoreCase(method)) { 
			
			HttpSession session = request.getSession(); 
			String loginuserid = (String)session.getAttribute("userid"); // 세션에 저장된 사용자 아이디 불러오기
			
			InterMemberDAO mdao = new MemberDAO();
			Map<String, String> useridMap = new HashMap<>();
			useridMap.put("loginuserid", loginuserid);
			
			// 1. 멤버십 가입 여부 알아오기 0: 미가입 1: 가입중 
			MemberDTO loginUserInfo = mdao.selectOneUser(loginuserid);
			boolean b_membership;
			if(loginUserInfo.getMembership() == 1) { // 멤버십 가입
				b_membership = true;
			}
			else { // 멤버십 미가입 
				b_membership = false;
				mdao.registMembership(useridMap);
			}
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("b_membership", b_membership);
			
			String json = jsonObj.toString(); 
			
			request.setAttribute("json", json);
			
			super.setViewPage("/WEB-INF/view/jjyjsonview.jsp");
		}

	}

}
