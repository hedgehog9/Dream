package choi.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import choi.product.model.InterProductDAO;
import choi.product.model.ProductDAO;
import common.controller.AbstractController;

public class DeleteKeyword extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		try {
			if("admin".equals(userid)) {	//요청한 사용자가 admin 이라면
				if("GET".equalsIgnoreCase(method)) { //요청방식이 "GET"일 때 
					String keyword = request.getParameter("keyword");
					
					InterProductDAO pdao = new ProductDAO();
					int n = pdao.DeleteRecommendKeyword(keyword);
					
					String message = "";
					String loc = "";
					if(n==1) {	//추천검색어 삭제가 성공하면
						message = "추천검색어'" + keyword + "' (이)가 삭제되었습니다!";
						loc = request.getContextPath()+"/product/searchProduct.dream";
						
						request.setAttribute("message", message);
						request.setAttribute("loc", loc);
						
						super.setRedirect(false);
						super.setViewPage("/WEB-INF/view/msg.jsp");
					}
					else {	//추천검색어 삭제이 실패하면
						message = "추천검색어 삭제가 실패하였습니다. 다시 시도해주세요!";
						loc = request.getContextPath()+"/product/searchProduct.dream";
						
						request.setAttribute("message", message);
						request.setAttribute("loc", loc);
						
						super.setRedirect(false);
						super.setViewPage("/WEB-INF/view/msg.jsp");
					}
				}//end of if("GET".equalsIgnoreCase(method))--
			}//end of if("admin".equals(userid))--
			
			
		}catch(Exception e) {
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.dream");
		}//end of try-catch--
		
	}

}
