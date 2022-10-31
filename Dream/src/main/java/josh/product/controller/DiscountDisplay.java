package josh.product.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import common.controller.AbstractController;
import josh.product.model.*;

public class DiscountDisplay extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String start = request.getParameter("start"); 
		String len = request.getParameter("len");
		
		InterProductDAO pdao = new ProductDAO();
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("start", start); // start  "1"  "9"  "17"  "25"  "33"
		
		String end = String.valueOf(Integer.parseInt(start) + Integer.parseInt(len) - 1);
		
		paraMap.put("end", end);		 // end	=> start + len -1;
									 // end	  "8"  "16"  "24"   "32"  "40"
		
		List<ProductDTO> discountList = pdao.selectDiscountprd(paraMap);
		
		JSONArray jsonArr = new JSONArray();
		
		
		if(discountList.size() > 0) {
			
			for(ProductDTO pdto:discountList) {
				
				JSONObject jsonObj = new JSONObject();
				
				jsonObj.put("product_num", pdto.getProduct_num());
				jsonObj.put("product_name", pdto.getProduct_name());
				jsonObj.put("product_content", pdto.getProduct_content());
				jsonObj.put("product_image", pdto.getProduct_image());
				jsonObj.put("price", pdto.getPrice());
				jsonObj.put("discount_rate", pdto.getDiscount_rate());
				jsonObj.put("discount_price", pdto.getDiscount_price());
				jsonObj.put("category", pdto.getCategory());
				
				jsonArr.put(jsonObj);
			
			}// end of for
			
			String json = jsonArr.toString();
			
			request.setAttribute("json", json);
			
			// super.setRedirect(false);
			super.setViewPage("/WEB-INF/joshJsonview.jsp");
		}
		else {
			String json = jsonArr.toString();
			
			request.setAttribute("json", json);
			
			// super.setRedirect(false);
			super.setViewPage("/WEB-INF/joshJsonview.jsp");
		}

	}

}


















