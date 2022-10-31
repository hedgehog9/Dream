package josh.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import josh.member.model.InterMemberDAO;
import josh.member.model.MemberDAO;

public class MyInfoEditController extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if( super.checkLogin(request) ) {
			
			if("POST".equalsIgnoreCase(method)) {
			
				String message = "";
				
				String userid = request.getParameter("userid");
				String passwd = request.getParameter("passwd");
				String username = request.getParameter("username");
				String mobile = request.getParameter("mobile");
				String passwd_store_cnt = request.getParameter("passwd_store_cnt");
				String mobile_store_cnt = request.getParameter("mobile_store_cnt");
				
				//HttpSession session = request.getSession();
				//String sessionUserid = (String) session.getAttribute("userid");
				
					
				Map<String,String> paraMap = new HashMap<>();
				//paraMap.put("sessionUserid", sessionUserid);
				paraMap.put("userid", userid);
				paraMap.put("passwd", passwd);
				paraMap.put("username", username);
				paraMap.put("mobile", mobile);
				paraMap.put("passwd_store_cnt", passwd_store_cnt);
				paraMap.put("mobile_store_cnt", mobile_store_cnt);
				
				InterMemberDAO mdao = new MemberDAO();
				int n = mdao.updateInfo(paraMap);
				//System.out.println("확인용 컨트롤에 있는 n 값~~~~~~~~~~~~~~~~~~~~~~~~~~~"+n);
				
				if(n == 1) {
				    message = "회원정보 수정 성공!!";
				}
				else {
					message = "회원정보 수정 실패!!";
				}
				
				String loc = request.getContextPath()+"/member/myInfo.dream";
				
				request.setAttribute("message", message);
				request.setAttribute("loc", loc);
				
			//	super.setRedirect(false);
				super.setViewPage("/WEB-INF/joshmsg.jsp");
					
				} // end of if(sessionUserid.equals(userid))
				
			
			else {
				// GET 방식으로 접근할 경우
			}
		
		}// end of if( super.checkLogin(request) )
		
		else {
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/login/login.dream");
		}

	}// end of public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception

}
