package jjy.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import common.controller.AbstractController;
import jjy.login.model.*;
import jjy.member.model.MemberDTO;

public class FindEmailController extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) { //"POST" 방식
			String mobile = request.getParameter("mobile");
			
			InterLoginDAO logindao = new LoginDAO();
			
			MemberDTO loginuser = logindao.userExist(mobile);
			
			String userid="9999";
			
			boolean existMobile = false;
			
			if(loginuser != null) { // 조회해온 사용자 정보가 있다면 true
				userid = loginuser.getUserid();
				userid = userid.replaceAll("(?<=.{2}).(?=.*@)", "*"); // 이메일의 @ 앞부분 2자리 빼고 모두 * 처리
				existMobile = true;
			}
			
			JSONObject jsonObj = new JSONObject(); // {}
			if(existMobile) {
			
				jsonObj.put("existMobile", existMobile);     // {"isExists":true} 또는 {"isExists":false} 으로 만들어준다. 
				jsonObj.put("userid", userid);
				
				String json = jsonObj.toString(); // 문자열 형태인 "{"isExists":true}" 또는 "{"isExists":false}" 으로 만들어준다.
				request.setAttribute("json", json);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/jjyjsonview.jsp");
				return;
			}
			
			else {
				jsonObj.put("existMobile", existMobile); 
				String json = jsonObj.toString();
				request.setAttribute("json", json);
				
				super.setViewPage("/WEB-INF/view/jjyjsonview.jsp");
				return;
			}
			
		}
		
		super.setViewPage("/WEB-INF/view/login/findEmail.jsp");
	}

}
