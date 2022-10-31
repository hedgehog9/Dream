package kim.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import kim.member.model.*;

public class PurchaseEnd extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod(); // "GET" 또는 "POST"
		
		if("POST".equalsIgnoreCase(method)) {
			
			String userid = request.getParameter("userid");
			String product_num = request.getParameter("product_num");
			String cart_cnt = request.getParameter("cart_cnt");
			
			
			InterMemberDAO mdao = new MemberDAO();
			
			
			Map<String,String> paraMap = new HashMap<>();

			paraMap.put("userid", userid);
			paraMap.put("product_num", product_num);
			paraMap.put("cart_cnt", cart_cnt);
			
				
			int n = mdao.updateCart(paraMap);
			
			String message = "";
			
			if(n >= 1) {
				
				message = "구매가 성공적으로 완료되었습니다!!";
			}
			else {
				message = "구매에 실패하였습니다!!";
			}
			
			String loc = "javascript:history.back()";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
			
		}
		
		else {
			// POST 방식이 아니라면
			
			String message = "비정상적인 경로로 접근했습니다!!";
			String loc = "javascript:history.back()";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
		}
		
	}


	

}
