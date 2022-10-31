package kim.product.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import kim.member.model.MemberVO;
import kim.product.model.InterProductDAO;
import kim.product.model.ProductDAO;
import kim.product.model.ProductVO;


public class DetailProductController extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 로그인 또는 로그아웃을 하면 시작페이지로 가는 것이 아니라 방금 보았던 그 페이지로 그대로 가기 위한 것임. 
		super.goBackURL(request);

		
		String method = request.getMethod();
		
		// System.out.println(method);
		
		if("GET".equalsIgnoreCase(method)) {	//method가 "GET" 일 때
			InterProductDAO pdao = new ProductDAO();
			// product_num 은 바뀔 수 있음
			
			
			String productnum = request.getParameter("num");

			HttpSession session = request.getSession();
			String loginuserid = (String) session.getAttribute("userid");
			
			
			try {
				
				ProductVO product = pdao.getDetail(productnum);
		
				request.setAttribute("pdNum", product.getProduct_num());
			
				request.setAttribute("pdImage", product.getProduct_image_array());
				request.setAttribute("pdName", product.getProduct_name());
				request.setAttribute("pdSize", product.getProduct_size());
				request.setAttribute("pdCnt", product.getProduct_cnt());
				request.setAttribute("product", product);
				
				if(loginuserid != null) { // 로그인 중이라면
					int n = pdao.ilikethis(productnum , loginuserid);
					request.setAttribute("like", n);
				}
				
			//	super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/product/product_detail.jsp");
				
			} catch(SQLException e) {
			    e.printStackTrace();
				super.setRedirect(true); 
			    super.setViewPage(request.getContextPath()+"/error.up");
			}
			
		}
		else {	//method가 "POST" 일 때
			
		}
		
				
	
		
		
		
		
		
		
		
		
		
	}
	
	
	

}
