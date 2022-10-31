package kim.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import kim.product.model.InterProductDAO;
import kim.product.model.ProductDAO;

public class DeleteProduct extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String productNo = request.getParameter("productNum");
		

		InterProductDAO pdao = new ProductDAO();
		try {
			int n = pdao.deleeteproduct(productNo);
			System.out.println(n);
			String message = "";
			if(n==1) {
				message = productNo+ "번 제품이 성공적으로 삭제되었습니다.";
			}
			else {
				message = "제품 삭제에 실패했습니다.";
				
			}
			String loc = request.getContextPath()+"/product/shop.dream";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			
//			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/msg.jsp");
			
		}catch (Exception e) {
			 e.printStackTrace();
		}
		
		
		

	
	}

}
