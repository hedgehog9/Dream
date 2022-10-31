package kim.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import common.controller.AbstractController;
import kim.member.model.InterMemberDAO;
import kim.member.model.MemberDAO;

public class Deletemessage extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod(); // "GET" 또는 "POST"
		HttpSession session = request.getSession();
		String fk_sender_userid = (String) session.getAttribute("userid");
		if("GET".equalsIgnoreCase(method)) {
			
	
			String messageno = request.getParameter("messageno");
			// messageno 는 받을때부터 0 , 1, 2, 이런식으로 받을 예정
			// system.out.println(messageno);
			
			
		    try {
		    	
		    	String type = "receieve";
	            if(request.getParameter("whoami")!=null && request.getParameter("type").trim() != "") {      //파라미터 type가 not empty라면!
	            	type = request.getParameter("whoami").trim();
	            }

				
				
				
				InterMemberDAO mdao = new MemberDAO();
				
				int n = mdao.deletemessage(messageno, type);
				
				JSONObject jsonObj = new JSONObject();
				
				// 꼬우면 그냥 msg 띄우고 성공! 실패! 하고 다시 원래페이지로 보내도 되긴함
				jsonObj.put("success", n);     
				
				String json = jsonObj.toString(); 
				
				request.setAttribute("json", json);
				
				// system.out.println(json);
			//	super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/jsonview.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
            
		}	

	}

}
