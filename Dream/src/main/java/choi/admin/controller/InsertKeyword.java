package choi.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import choi.product.model.InterProductDAO;
import choi.product.model.ProductDAO;
import common.controller.AbstractController;

public class InsertKeyword extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		try {
			if("admin".equals(userid)) {	//요청한 사용자가 admin 이라면
				if(!"GET".equalsIgnoreCase(method)) { //요청방식이 "POST"일 때 
					String keyword = request.getParameter("recommend_keyword");
					
					InterProductDAO pdao = new ProductDAO();
					int n = pdao.insertRecommendKeyword(keyword);
					
					String message = "";
					String loc = "";
					if(n==1) {	//추천검색어 등록이 성공하면
						message = "입력하신 추천검색어'" + keyword + "' (이)가 등록되었습니다!";
						loc = request.getContextPath()+"/product/searchProduct.dream";
						
						request.setAttribute("message", message);
						request.setAttribute("loc", loc);
						
						super.setRedirect(false);
						super.setViewPage("/WEB-INF/view/msg.jsp");
					}
					else {	//추천검색어 등록이 실패하면
						message = "추천검색어 등록이 실패하였습니다. 다시 시도해주세요!";
						loc = request.getContextPath()+"/product/searchProduct.dream";
						
						request.setAttribute("message", message);
						request.setAttribute("loc", loc);
						
						super.setRedirect(false);
						super.setViewPage("/WEB-INF/view/msg.jsp");
					}
					
				}//end of if(!"GET".equalsIgnoreCase(method))
			}//end of if("admin".equals(userid))
			else {	//요청한 사람이 관리자가 아니라면
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/index.dream");
			}
		}catch(Exception e) {
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.dream");
		}
		
		
		
		
	}

}
