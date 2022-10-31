package kim.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import kim.member.model.InterMemberDAO;
import kim.member.model.MemberDAO;
import kim.member.model.MemberVO;

public class GoPurchase extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 아임포트 결제창을 사용하기 위한 전제조건은 먼저 로그인을 해야 하는 것이다.
		if( super.checkLogin(request) ) {
			// 로그인을 했으면
			
			String userid = request.getParameter("userid");
			
			HttpSession session = request.getSession();
			String loginuserid = (String) session.getAttribute("userid");
			// system.out.println(userid);
			
			// system.out.println(loginuserid);
			if( loginuserid.equals(userid) ) {
				// 로그인한 사용자가 자기거 결제하는 경우 
			
				
				String productName = request.getParameter("productName"); //"새우깡";
				String fullPrice = request.getParameter("fullPrice"); 
				// system.out.println("fullPrice"+fullPrice);
				
				
				MemberVO user = new MemberVO();
	
				InterMemberDAO mdao = new MemberDAO();
				user = mdao.pointCheck(userid);
				
								
				request.setAttribute("productName", productName);
				request.setAttribute("email", userid);
				request.setAttribute("name", user.getUsername());
				request.setAttribute("mobile", user.getMobile());
				request.setAttribute("fullPrice", fullPrice);
				request.setAttribute("userid", userid);
				
				
							
				

				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/member/paymentGateway.jsp");
				
				
			}
			else {
				// 로그인한 사용자가 다른 사용자의 물건구매하려고 시도하는 경우 
				String message = "다른 사용자의 물건구매 결제 시도는 불가합니다.!!";
				String loc = "javascript:history.back()";
				
				request.setAttribute("message", message);
				request.setAttribute("loc", loc);
				
			//	super.setRedirect(false);
				super.setViewPage("/WEB-INF/msg.jsp");
				return;
			}
			
		}
		else {
			// 로그인을 안 했으면
			String message = "결제를 하기 위해서는 먼저 로그인을 하세요!!";
			String loc = "javascript:history.back()";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
		}
		
 		
		
	}

}
