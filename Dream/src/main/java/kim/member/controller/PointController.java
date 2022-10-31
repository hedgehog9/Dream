package kim.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import jjy.purchase.model.InterPurchaseListDAO;
import jjy.purchase.model.PurchaseListDAO;
import jjy.purchase.model.PurchaseListDTO;
import kim.member.model.InterMemberDAO;
import kim.member.model.MemberDAO;
import kim.member.model.PointVO;

public class PointController extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String sessionUserid = (String) session.getAttribute("userid");
		String userid = sessionUserid;
		if (sessionUserid != null) {

		

//			if (sessionUserid.equals(userid)) {

				InterMemberDAO pdao = new MemberDAO();
				// system.out.println(userid);

				List<PointVO> pointList = pdao.allPointList(userid);
				
				int n = pdao.getTotalPoint(userid);
				// system.out.println(n);
				if(n != -1) { // sql 문제없이 결과가 나온경우
					
					request.setAttribute("totalPoint", n);
				}
				
				// system.out.println("포인트리스트 사이즈"+pointList.size());
				
				request.setAttribute("pointList", pointList);

				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/member/point.jsp");
//			} else {
//				String message = "비정상적인 경로접근!!";
//				String loc = "javascript:history.back()";
//
//				request.setAttribute("message", message);
//				request.setAttribute("loc", loc);
//
//				// super.setRedirect(false);
//				super.setViewPage("/WEB-INF/view/msg.jsp");
//			}
		}

	}

		
		

	

}
