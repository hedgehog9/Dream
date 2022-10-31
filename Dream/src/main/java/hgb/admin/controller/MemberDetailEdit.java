
package hgb.admin.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import common.controller.AbstractController;
import hgb.address.model.AddressDAO;
import hgb.address.model.AddressDTO;
import hgb.address.model.InterAddressDAO;
import hgb.member.model.InterMemberDAO;
import hgb.member.model.MemberDAO;

public class MemberDetailEdit extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
String method = request.getMethod();
		
		if("GET".equalsIgnoreCase(method)) {	
		//  GET 방식이라면
		//	super.setRedirect(false);	
			super.setViewPage("/WEB-INF/view/admin/ad_member/memberDetail.jsp");
		}
		
		else {
						
			
			 // POST 방식이라면(즉, 저장하기 버튼을 클릭한 경우)
			 Map<String, String> paraMap = new HashMap<>();
			 String userid = request.getParameter("userid"); 				
			 String username = request.getParameter("username"); 
			 if(request.getParameter("passwd") == "") {
				 String passwd = "없음";
				 paraMap.put("passwd", passwd);
			 }
			 else {
				 String passwd = request.getParameter("passwd"); 
				 paraMap.put("passwd", passwd);
			 }
			
			 String secession = request.getParameter("secession"); 
			 String rest_member = request.getParameter("rest_member"); 	         
			 String membership = request.getParameter("membership"); 	         
	         
			
			 
			 paraMap.put("userid", userid);
			 paraMap.put("username", username);
			 
			 paraMap.put("secession", secession);
			 paraMap.put("rest_member", rest_member);
			 paraMap.put("membership", membership);	         	         	        
	                  	         	         
	         InterMemberDAO mdao = new MemberDAO();
	         
	         
	         
	         
	         
	         int n = mdao.editmember(paraMap);
	         
	         
	         
	         if(n == 1) {
	        	 // 정보 수정 성공
	        	 
	        	 
	        	 	        	 
	        	 
	        	 JSONObject jsobj = new JSONObject(); // {}
	 			jsobj.put("n", n); // {n:1}
	 			
	 			String json = jsobj.toString(); // "{n:1}"
	 			
	 			request.setAttribute("json", json);
	 			
	 			super.setRedirect(false);
	 			super.setViewPage("/WEB-INF/view/jsonview.jsp");
	        	 
	         }
	         else {
	        	 // 정보 수정 실패
	        	 
	        	 
	        	 	        	 
	        	 
	        	 String message = "정보가 수정에 실패하였습니다";
	             String loc = "javascript:location.href = document.referrer";
	               
	               request.setAttribute("message", message);
	               request.setAttribute("loc", loc);
	               
	            // super.setRedirect(false);
	               super.setViewPage("/WEB-INF/view/msg.jsp");
	               return;
	        	 
	         }
			
			
		}
		
	}

}
