package josh.cart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import common.controller.AbstractController;
import josh.product.model.InterProductDAO;
import josh.product.model.ProductDAO;

public class CartUpdate extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		String method = request.getMethod();
		
		if(!"POST".equalsIgnoreCase(method)) {
			// GET 방식이라면
			String message = "비정상적인 경로로 들어왔습니다.";
            String loc = request.getContextPath()+"/index.dream";
            
            request.setAttribute("message", message);
            request.setAttribute("loc", loc);
            
       //   super.setRedirect(false);
            super.setViewPage("/WEB-INF/joshmsg.jsp");
		}
		
		else if("POST".equalsIgnoreCase(method) && super.checkLogin(request)) {
			// POST 방식이고 로그인을 했다라면
			
			String cart_num = request.getParameter("cart_num");
			String cart_qty = request.getParameter("cart_qty");
			
			InterProductDAO pdao = new ProductDAO();
			
			// 장바구니 테이블에서 특정제품의 수량을 변경하기
			int n = pdao.updateCart(cart_num, cart_qty);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("n", n);
			
			String json = jsonObj.toString();
			
			request.setAttribute("json", json);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/joshJsonview.jsp");
			
		}
		
	}

}
