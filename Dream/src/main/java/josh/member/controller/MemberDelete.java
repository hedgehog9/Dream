package josh.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import common.controller.AbstractController;
import josh.member.model.InterMemberDAO;
import josh.member.model.MemberDAO;

public class MemberDelete extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();
		
		if(!"POST".equalsIgnoreCase(method)) {
			// GET 방식이라면
			String message = "비정상적인 경로로 들어왔습니다.";
            String loc = "javascript:history.back()";
            
            request.setAttribute("message", message);
            request.setAttribute("loc", loc);
            
       //   super.setRedirect(false);
            super.setViewPage("/WEB-INF/joshmsg.jsp");
		}
		
		else {
			
			if(super.checkLogin(request)) {
				
				String userid = request.getParameter("userid");
			//	System.out.println("확인용 userid ==> " +userid);
				
				InterMemberDAO mdao = new MemberDAO();
				
				int n = mdao.deleteMember(userid);
				
				if(n == 1) {
					HttpSession session = request.getSession();
					session.invalidate();
				}
				
				JSONObject jsonObj = new JSONObject();
				
				jsonObj.put("n", n);
				String json = jsonObj.toString();
				
				request.setAttribute("json", json);
				
		   //   super.setRedirect(false);
				super.setViewPage("/WEB-INF/joshJsonview.jsp");
				
			}
			else {
				String message = "로그인 후 이용가능합니다.";
	            String loc = "javascript:history.back()";
	            
	            request.setAttribute("message", message);
	            request.setAttribute("loc", loc);
	            
	       //   super.setRedirect(false);
	            super.setViewPage("/WEB-INF/joshmsg.jsp");
			}
			
		}
		
		
	}

}
