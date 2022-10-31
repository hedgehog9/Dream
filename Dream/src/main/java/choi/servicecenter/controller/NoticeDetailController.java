package choi.servicecenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import choi.servicecenter.model.InterServiceCenterDAO;
import choi.servicecenter.model.ServiceCenterDAO;
import choi.servicecenter.model.NoticeDTO;
import common.controller.AbstractController;

public class NoticeDetailController extends AbstractController{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.goBackURL(request);
		String method = request.getMethod();
		
		if("GET".equalsIgnoreCase(method)) {	// method가 "GET"이라면
			int notice_num = Integer.parseInt(request.getParameter("num"));
			InterServiceCenterDAO sdao = new ServiceCenterDAO();
			NoticeDTO ndto = sdao.select_one_notice(notice_num);
			
			request.setAttribute("notice_num", notice_num);
			request.setAttribute("notice_title", ndto.getNotice_title());
			request.setAttribute("notice_content", ndto.getNotice_content());
			request.setAttribute("write_date", ndto.getWrite_date());
			super.setViewPage("/WEB-INF/view/notice/noticeDetail.jsp");
		}
		else {	//method가 "POST"라면
			
		}
		
	}
}
