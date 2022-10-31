package josh.cart.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import common.controller.AbstractController;
import josh.point.model.InterPointDAO;
import josh.point.model.PointDAO;
import josh.product.model.*;

public class CartPurchaseUpdate extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int n = 0;
		
		String method = request.getMethod();
		
		HttpSession session = request.getSession();
		String userid =  (String) session.getAttribute("userid");
		
		if("POST".equalsIgnoreCase(method)) {
		
			JSONObject jsonObj = new JSONObject();
			
			String jsonData = request.getParameter("jsonData");
			 
			// System.out.println("확인용 jsonData ==> " + jsonData);
			 
			JSONArray purchaseArray = new JSONArray(jsonData);
			 
			for(Object obj : purchaseArray) {
				 
				jsonObj = (JSONObject)obj;
				 
				//System.out.println("확인용 jsonObj => " + jsonObj);
				 
				String product_num = (String) jsonObj.get("product_num");
				String cart_num = (String) jsonObj.get("cart_num");
				String product_size = (String) jsonObj.get("product_size");
				String cart_cnt = (String) jsonObj.get("cart_cnt");
				String fk_address_num = (String) jsonObj.get("fk_address_num");
				
				// System.out.println("확인용 product_num => " + product_num);
				// System.out.println("확인용 cart_num => " + cart_num);
				// System.out.println("확인용 product_size => " + product_size);
				// System.out.println("확인용 cart_cnt => " + cart_cnt);
				// System.out.println("확인용 fk_address => " + fk_address_num);
				 
				Map<String, String> paraMap = new HashMap<>();
				paraMap.put("product_num", product_num);
				paraMap.put("cart_num", cart_num);
				paraMap.put("product_size", product_size);
				paraMap.put("cart_cnt", cart_cnt);
				paraMap.put("userid", userid);
				paraMap.put("fk_address_num", fk_address_num);
				
				InterProductDAO pdao = new ProductDAO();
				 
				n += pdao.cartPurchaseUpdate(paraMap);
				 
			 }// end of for ------------------------------------------
			
			 String plusPoint = request.getParameter("plusPoint");
			 String minusPoint = request.getParameter("minusPoint");
			 
			 //System.out.println("확인용 minusPoint ==> " + minusPoint);
			 //System.out.println("확인용 userid ==> " + userid);
			 
			 InterPointDAO pointDao = new PointDAO();
			 
			 Map<String, String> paraMap = new HashMap<>();
			 paraMap.put("plusPoint", plusPoint);
			 paraMap.put("minusPoint", minusPoint);
			 paraMap.put("userid", userid);
			 
			 n += pointDao.insertPoint(paraMap);
			
			 jsonObj.put("n", n);
			 String json = jsonObj.toString();
			 
			 request.setAttribute("json", json);
			 
			 super.setRedirect(false);
			 super.setViewPage("/WEB-INF/joshJsonview.jsp");
		}
		else {
			String message = "비정상적인 경로로 들어왔습니다.";
			String loc = request.getContextPath()+"/login/login.dream";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			// super.setRedirect(false);
			super.setViewPage("/WEB-INF/joshmsg.jsp");
		}
	}

}
