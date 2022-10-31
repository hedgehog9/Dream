package hgb.admin.controller;

import java.sql.SQLException;

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


public class MemberDelete extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if(!"POST".equalsIgnoreCase(method)) {
			// GET 방식이라면
			String message = "비정상적인 경로로 들어왔습니다";
		    String loc = "javascript:history.back()";
		
		    request.setAttribute("message", message);
		    request.setAttribute("loc", loc);
		   
		
	    //  super.setRedirect(false);
		    super.setViewPage("/WEB-INF/msg.jsp");
		}
		
		else  {
			// POST 방식이라면
			
			
			
			String userid = request.getParameter("userid");
			
			InterMemberDAO mdao = new MemberDAO();
			
			// 회원목록에서 회원을 삭제시키기
			int n = mdao.delMember(userid);
			
			JSONObject jsobj = new JSONObject(); // {}
			jsobj.put("n", n); // {n:1}
			
			String json = jsobj.toString(); // "{n:1}"
			
			request.setAttribute("json", json);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/jsonview.jsp");			
			
		}
		
	}

}
