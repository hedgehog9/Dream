package kim.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import common.controller.AbstractController;
import kim.member.model.InterMemberDAO;
import kim.member.model.MemberDAO;


public class LikeCheck extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		
		String method = request.getMethod(); // "GET" 또는 "POST"
		
		if("POST".equalsIgnoreCase(method)) {
			
	
			String userid = request.getParameter("userid");
			String productNum = request.getParameter("productNum");
			// system.out.println(userid);
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("userid", userid);
			paraMap.put("productNum", productNum);
			
			InterMemberDAO mdao = new MemberDAO();
			Map<String, String> result = mdao.likeCheck(paraMap);
			
			JSONObject jsonObj = new JSONObject();
			
	
			jsonObj.put("resultType", result.get("resultType"));     
			jsonObj.put("resultSuccess", result.get("resultSuccess"));   
			jsonObj.put("count", result.get("count")); 
			String json = jsonObj.toString(); 
			
			request.setAttribute("json", json);
			
			// system.out.println(json);
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/jsonview.jsp");
		}	
	}

}
