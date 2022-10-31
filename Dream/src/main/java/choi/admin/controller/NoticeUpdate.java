package choi.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import choi.servicecenter.model.InterServiceCenterDAO;
import choi.servicecenter.model.NoticeDTO;
import choi.servicecenter.model.ServiceCenterDAO;
import common.controller.AbstractController;

public class NoticeUpdate extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		try {
			if("admin".equals(userid)) {	//요청한 userid가 admin 이라면
				if("GET".equalsIgnoreCase(method)) { //요청방식이 "GET"일 때 
					String notice_num_ = request.getParameter("num");
					int notice_num = Integer.parseInt(notice_num_);
					
					InterServiceCenterDAO sdao = new ServiceCenterDAO();
					NoticeDTO ndto = new NoticeDTO();
					ndto = sdao.select_one_notice(notice_num);	//게시글번호로 NoticeDTO하나 가져오기
					
					request.setAttribute("notice_num", notice_num);
					request.setAttribute("notice_title", ndto.getNotice_title());
					request.setAttribute("notice_content", ndto.getNotice_content());
					request.setAttribute("write_date", ndto.getWrite_date());
					
					
					super.setRedirect(false);
					super.setViewPage("/WEB-INF/view/admin/ad_notice/notice_write.jsp");	//공지사항 작성페이지 가서 값 꽂아주기
				}
				else {	//요청방식이 "POST"일 때 
					String notice_num = request.getParameter("notice_num");
					String notice_content = request.getParameter("notice_content");
					String notice_title = request.getParameter("notice_title");
					
					Map<String,String> paraMap = new HashMap<>();
					paraMap.put("notice_content", notice_content);
					paraMap.put("notice_title", notice_title);
					paraMap.put("notice_num", notice_num);
					
					InterServiceCenterDAO sdao = new ServiceCenterDAO();
					int n = sdao.notice_Update(paraMap);
					
					String message = "";
					String loc = "";
					if(n==1) {	//공지사항 수정에 성공하였다면
						message = notice_num+"번 글 수정이 완료되었습니다!";
						loc = request.getContextPath()+"/notice/notice.dream";
						
						request.setAttribute("message", message);
						request.setAttribute("loc", loc);
						
						super.setRedirect(false);
						super.setViewPage("/WEB-INF/view/msg.jsp");
					}
					else {	//공지사항 등록에 실패하였다면
						message = "공지사항 수정이 실패하였습니다. 다시 시도해주세요!";
						loc = request.getContextPath()+"/notice/notice.dream";
						
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
		} catch(Exception e) {
			e.printStackTrace();
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.dream");
		}//end of try=catch--
		
	}
}