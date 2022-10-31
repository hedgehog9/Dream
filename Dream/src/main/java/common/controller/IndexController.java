package common.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import josh.product.model.*;

public class IndexController extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.goBackURL(request);
		
		InterProductDAO pdao = new ProductDAO();
		
		int totalDiscount = pdao.totalDiscount(); // 할인 상품의 전체개수를 알아온다.
		
		request.setAttribute("totalDiscount", totalDiscount);
		// System.out.println(totalDiscount);
		
		super.setViewPage("/WEB-INF/view/index.jsp");
	}

}
