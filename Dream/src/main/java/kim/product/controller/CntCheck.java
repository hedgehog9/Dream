package kim.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import common.controller.AbstractController;
import kim.member.model.InterMemberDAO;
import kim.member.model.MemberDAO;
import kim.product.model.InterProductDAO;
import kim.product.model.ProductDAO;

public class CntCheck extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod(); // "GET" 또는 "POST"
		
		if("POST".equalsIgnoreCase(method)) {
			
	
			String productNum = request.getParameter("productNum");
			
			
			
			InterProductDAO pdao = new ProductDAO();

			Map<String, ArrayList<String>> result = pdao.cnt_check(productNum);
			
			JSONObject jsonObj = new JSONObject();
			
	
			jsonObj.put("size", result.get("size"));     
			jsonObj.put("cnt", result.get("cnt"));   
			
			String json = jsonObj.toString(); 
			
			request.setAttribute("json", json);
			
		//	System.out.println(json);
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/jsonview.jsp");
		}	

	}

}
