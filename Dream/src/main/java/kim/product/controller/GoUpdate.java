package kim.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import kim.member.model.InterMemberDAO;
import kim.member.model.MemberDAO;
import kim.product.model.InterProductDAO;
import kim.product.model.ProductDAO;

public class GoUpdate extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if( super.checkLogin(request) ) {
			// 로그인을 했으면

			String userid = request.getParameter("userid");
			
			
			HttpSession session = request.getSession();
			String loginuserid = (String) session.getAttribute("userid");
			// form 으로 보내준 userid 와 현재 로그인한 session 의 userid 값이 동일한지 확인해야댐
			if( loginuserid.equals(userid) ) {
				// system.out.println("update 넘어온 값 확인!");
				
				Map<String, String> paraMap = new HashMap<>();
				 
				int length = Integer.parseInt(request.getParameter("length")); 
				 System.out.println("length"+length);
				String productNum = request.getParameter("productNum");
				String PointPlus = request.getParameter("PointPlus");
				 System.out.println("PointPlus"+PointPlus);
				String PointMinus = request.getParameter("PointMinus");
				 System.out.println("PointMinus"+PointMinus);
				String event_type = request.getParameter("event_type");
				String address_num = request.getParameter("address_num");
				String discountPrice = request.getParameter("discountPrice");
				
				

				ArrayList<String>sizeArr = new ArrayList<String>();
				ArrayList<String>cntArr = new ArrayList<String>();
				for(int i=0; i<length ;i++) {
					sizeArr.add((String)request.getParameter("size"+i)); 
					cntArr.add((String)request.getParameter("cnt"+i)); 
					
					paraMap.put("size"+i, (String)request.getParameter("size"+i));
					paraMap.put("cnt"+i, (String)request.getParameter("cnt"+i));
					
				}
				
				paraMap.put("discountPrice",discountPrice);
				
				paraMap.put("userid",userid);
				paraMap.put("productNum",productNum);
				paraMap.put("length",request.getParameter("length")); // 가서 int 로 변환해서 쓰세요
				paraMap.put("PointPlus",PointPlus);
				paraMap.put("PointMinus",PointMinus);
				paraMap.put("address_num",address_num);
				
				paraMap.put("event_type",event_type);
				
				System.out.println(sizeArr);
				System.out.println(cntArr);
				
				InterProductDAO pdao = new ProductDAO();
				int n = pdao.nocartPurchaseUpdate(paraMap);
				System.out.println(n);
				if(n ==1 ) {// 안전하게 결제 다 끝나고 업데이트도 다 된 상태
					String message = "구매가 성공적으로 완료되었습니다!!";
					String loc = request.getContextPath()+"/member/buylist.dream";
					
					request.setAttribute("message", message);
					request.setAttribute("loc", loc);
					
				//	super.setRedirect(false);
					super.setViewPage("/WEB-INF/view/msg.jsp");
					return;
				}
				else {
					String message = "구매가 성공적으로 완료되지 않았습니다!!";
					String loc = "javascript:history.back()";
					
					request.setAttribute("message", message);
					request.setAttribute("loc", loc);
					
				//	super.setRedirect(false);
					super.setViewPage("/WEB-INF/view/msg.jsp");
					return;
				}
				
			}
			else{
				// 로그인한 사용자가 다른 사용자의 물건구매하려고 시도하는 경우 
				String message = "물건 구매중 다른 사용자로 로그인하였습니다!!";
				String loc = "javascript:history.back()";
				
				request.setAttribute("message", message);
				request.setAttribute("loc", loc);
				
			//	super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/msg.jsp");
				return;
			}
		 
		 
		
		}
		else {
			// 로그인을 안 했으면
			String message = "결제는 완료되었으나 제품 업데이트 이전 로그인 정보가 사라졌습니다!!";
			String loc = "javascript:history.back()";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
		}

	}

}
