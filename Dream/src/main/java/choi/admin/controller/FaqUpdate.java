package choi.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import choi.servicecenter.model.InterServiceCenterDAO;
import choi.servicecenter.model.QnaDTO;
import choi.servicecenter.model.ServiceCenterDAO;
import common.controller.AbstractController;

public class FaqUpdate extends AbstractController{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		try {
			if("admin".equals(userid)) {	//요청한 userid가 admin 이라면
				if("GET".equalsIgnoreCase(method)) {	//method가 "GET"일 때 
					String faq_num_ = request.getParameter("num");
					int faq_num = Integer.parseInt(faq_num_);
					
					
					InterServiceCenterDAO sdao = new ServiceCenterDAO();
					QnaDTO qdto = sdao.select_one_qna(faq_num);
					
					request.setAttribute("faq_num", faq_num);
					request.setAttribute("faq_title", qdto.getFaq_title());
					request.setAttribute("faq_subject", qdto.getFaq_subject());
					request.setAttribute("faq_content", qdto.getFaq_content());
					
					super.setRedirect(false);
					super.setViewPage("/WEB-INF/view/admin/ad_notice/qna_write.jsp");
				}
				else {	//method가 "POST"일 때 
					
					Map<String,String> paraMap = new HashMap<>();
					paraMap.put("faq_num", request.getParameter("faq_num"));
					paraMap.put("faq_title", request.getParameter("faq_title"));
					paraMap.put("faq_subject", request.getParameter("faq_subject"));
					paraMap.put("faq_content", request.getParameter("faq_content"));
					
					InterServiceCenterDAO sdao = new ServiceCenterDAO();
					int n = sdao.faq_Update(paraMap);
					
					String message = "";
					String loc = "";
					if(n==1) {	//자주묻는질문 수정에 성공하였다면
						message = paraMap.get("faq_num")+"번 글 수정이 완료되었습니다!";
						loc = request.getContextPath()+"/notice/qna.dream";
						
						request.setAttribute("message", message);
						request.setAttribute("loc", loc);
						
						super.setRedirect(false);
						super.setViewPage("/WEB-INF/view/msg.jsp");
					}
					else {	//자주묻는질문 등록에 실패하였다면
						message = "자주묻는질문 수정이 실패하였습니다. 다시 시도해주세요!";
						loc = request.getContextPath()+"/notice/qna.dream";
						
						request.setAttribute("message", message);
						request.setAttribute("loc", loc);
						
						super.setRedirect(false);
						super.setViewPage("/WEB-INF/view/msg.jsp");
					}
				}//end of post
				
			}//end of if
			else {	//요청한 사람이 관리자가 아니라면
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/index.dream");
			}
		} catch(Exception e) {
			e.printStackTrace();
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.dream");
		}//end of try=catch--
	}
}
