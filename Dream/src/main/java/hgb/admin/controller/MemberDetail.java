package hgb.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import hgb.member.model.InterMemberDAO;
import hgb.member.model.MemberDAO;
import hgb.member.model.MemberDTO;
import jjy.purchase.model.PurchaseListDTO;
import kim.member.model.PointVO;


public class MemberDetail extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String ctxPath = request.getContextPath();
		
		if(super.checkLogin(request)) {
			
			HttpSession session = request.getSession();
			String userid = (String) (session.getAttribute("userid"));
			
			if("admin".equals(userid)) {
				
				userid = request.getParameter("userid");
				InterMemberDAO mdao = new MemberDAO();
				// 유저 정보 가져와서 member 객체로 저장
				MemberDTO mdto = mdao.memberOneDetail(userid);
				request.setAttribute("mdto", mdto);							
				
				// 유저의 id 로 그 유저의 구매리스트를 가져오기			
				ArrayList<PurchaseListDTO> plist= new ArrayList<>();
				plist = mdao.memberOneBuyDetail(userid);
				request.setAttribute("plist", plist);
				
				// 유저의 id 로 그 유저의 포인트내역을 가져오기
				ArrayList<PointVO> pointlist= new ArrayList<>();
				pointlist = mdao.memberOnePointDetail(userid);
				request.setAttribute("pointlist", pointlist);	
				

				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/admin/ad_member/memberDetail.jsp");
			     
			}
			else { // admin 계정으로 진입한게 아닐때
				String message = "관리자 이외는 접근이 불가합니다.";
	            String loc = "javascript:history.back()";
	            
	            request.setAttribute("message", message);
	            request.setAttribute("loc", loc);
	            
	            super.setRedirect(false);
	            super.setViewPage("/WEB-INF/view/index.jsp");
			}
			
		}
		else { // 로그인을 하지 않고 진입했을때
			super.setRedirect(true);
	        super.setViewPage(ctxPath+"/login/login.dream");
		}
		
		
		
			
		
	}

}
