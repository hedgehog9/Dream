package choi.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import choi.servicecenter.model.InterServiceCenterDAO;
import choi.servicecenter.model.ServiceCenterDAO;
import common.controller.AbstractController;

public class FaqDelete extends AbstractController{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		try {
			if("admin".equals(userid)) {	//요청한 userid가 admin 이라면
				if(!"GET".equalsIgnoreCase(method)) { //요청방식이 "POST"일 때 
					//"GET"로 올일이 없으니 코딩안함
				}
				else {	//요청방식이 "GET"일 때 
					String faq_num_ = request.getParameter("num");
					int faq_num = Integer.parseInt(faq_num_);
					
					InterServiceCenterDAO sdao = new ServiceCenterDAO();
					int n = sdao.faq_delete(faq_num);
					
					String message = "";
					String loc = "";
					if(n==1) {	//자주묻는질문 삭제에 성공하였다면
						message = faq_num_ + "번 글 삭제가 완료되었습니다!";
						loc = request.getContextPath()+"/notice/qna.dream";
						
						request.setAttribute("message", message);
						request.setAttribute("loc", loc);
						
						super.setRedirect(false);
						super.setViewPage("/WEB-INF/view/msg.jsp");
					}
					else {	//자주묻는질문 삭제에 실패하였다면
						message = "자주묻는질문 삭제가 실패하였습니다. 다시 시도해주세요!";
						loc = request.getContextPath()+"/notice/qna.dream";
						
						request.setAttribute("message", message);
						request.setAttribute("loc", loc);
						
						super.setRedirect(false);
						super.setViewPage("/WEB-INF/view/msg.jsp");
					}
				}
			}//end of if---
			else {	//요청한 사람이 관리자가 아니라면
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/index.dream");
			}
		}catch(Exception e) {
			e.printStackTrace();
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.dream");
		}
		
	}
}
