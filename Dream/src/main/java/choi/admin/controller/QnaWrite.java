package choi.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import choi.servicecenter.model.InterServiceCenterDAO;
import choi.servicecenter.model.ServiceCenterDAO;
import common.controller.AbstractController;

public class QnaWrite extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if("admin".equals(userid)) {	//요청한 userid가 admin 이라면
			if("GET".equalsIgnoreCase(method)) { //요청방식이 "GET"일 때 
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/admin/ad_notice/qna_write.jsp");
				
			}
			else {	//요청방식이 "POST"일 때 
				String faq_title = request.getParameter("faq_title");
				String faq_subject = request.getParameter("faq_subject");
				String faq_content = request.getParameter("faq_content");
				Map<String,String> paraMap = new HashMap<>();
				paraMap.put("faq_title", faq_title);
				paraMap.put("faq_subject", faq_subject);
				paraMap.put("faq_content", faq_content);
				paraMap.put("userid", userid);
				
				InterServiceCenterDAO sdao = new ServiceCenterDAO();
				int n = sdao.faq_insert(paraMap);
				
				String message = "";
				String loc = "";
				if(n==1) {	//자주묻는 질문 등록에 성공하였다면
					message = "글제목 "+faq_subject+" 게시글 등록이 완료되었습니다!";
					loc = request.getContextPath()+"/notice/qna.dream";
					
					request.setAttribute("message", message);
					request.setAttribute("loc", loc);
					
					super.setRedirect(false);
					super.setViewPage("/WEB-INF/view/msg.jsp");
				}
				else {	//자주묻는 질문 등록에 실패하였다면
					message = "자주묻는 질문 등록이 실패하였습니다. 다시 시도해주세요!";
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
		
	}

}
