package jjy.admin.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import jjy.address.model.*;

public class ShippingInfo extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		try {
			if("admin".equals(userid)) {
				
				String odruserid = request.getParameter("userid"); // 구매한 사용자 아이디
				String ordernum = request.getParameter("ordernum");// 주문번호
				
				// System.out.println(odruserid);
				// System.out.println(ordernum);
				
				// 주문번호와 사용자 아이디를 전달받아 해당 주문에 해당하는 아이디, 주문자명, 주문자연락처, 우편번호,주소,상세주소를 알아온다.
				
				Map<String,String> paraMap = new HashMap<>();
				
				paraMap.put("odruserid", odruserid);
				paraMap.put("ordernum", ordernum);
				
				InterAddressDAO adao = new AddressDAO();
				AddressDTO adto = adao.getOrderInfo(paraMap);
				
				request.setAttribute("userid", adto.getUserid());
				
				request.setAttribute("post_code", adto.getPost_code());
				
				request.setAttribute("address", adto.getAddress());
				request.setAttribute("detail_address", adto.getDetail_address());
				request.setAttribute("order_name", adto.getOrder_name());
				request.setAttribute("mobile", adto.getMobile());
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/admin/ad_shipping/shippingInfo.jsp");
			}
			else {
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/index.dream");
			}
		}catch(Exception e) {
			e.printStackTrace();
	//		super.setRedirect(true);
//			super.setViewPage(request.getContextPath()+"/index.dream");
		}
		
	}

}
