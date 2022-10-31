package choi.servicecenter.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import choi.servicecenter.model.InterServiceCenterDAO;
import choi.servicecenter.model.ServiceCenterDAO;
import choi.servicecenter.model.NoticeDTO;
import common.controller.AbstractController;

public class NoticeController extends AbstractController{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.goBackURL(request);
		String method = request.getMethod();
		
		if("GET".equalsIgnoreCase(method)) {	//method가 "GET"일 때
			try {
				int page= 1;
				if(request.getParameter("p")!=null && request.getParameter("p").trim() != "") {		//파라미터 page가 not empty라면!
					page = Integer.parseInt(request.getParameter("p").trim());
				}
				InterServiceCenterDAO sdao = new ServiceCenterDAO();
				int total_cnt = sdao.cntAllNotice();                      //총 게시물 수
				float display_cntf = 10f;	                              //한 페이지당 보여줄 게시물 수 float형
				int display_cnt = (int)display_cntf;	                  //한 페이지당 보여줄 게시물 수 int형
				int display_page = 5;					                  //한번에 보여줄 페이지번호의 갯수 int형
				float display_pagef = 5f;								  //한번에 보여줄 페이지번호의 갯수 float형
				int totalPage = (int)Math.ceil(total_cnt/ display_cntf); //총 게시물수 / 한페이지당 보여줄 게시물 수 를 올림
				if(totalPage == 0) {	//총페이지가 0이라면
					totalPage = 1;
				}
				if(page<=1) {	//페이지가 1보다 작다면
			    	page = 1;
			    }
			    else if(page>=totalPage) {	//페이지가 총페이지보다 크다면
			    	page = totalPage;
			    }
				
				int endPage = (int)(((page-1)/display_page)+1) * display_page;	//하단 페이지번호 마지막번호
				if(totalPage<endPage){
				    endPage = totalPage;
				}
				
			    int startPage = (int)((page-1)/display_page)*display_page+1;	//하단 페이지번호 시작번호
			    if(totalPage<startPage) {
			    	startPage = totalPage;
			    }
			    
			    
			    boolean last_display_page = false;								// 보고있는 페이지가 마지막 페이지번호쪽 인지 여부검사
			    int a = (int)Math.ceil(totalPage / display_pagef);	//총페이지 / 한페이지당 보여줄 페이지 수
			    int b = (int)Math.ceil(page / display_pagef);		//현재페이지번호 / 한 페이지당 보여줄 페이지 수
//			    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			    System.out.println("a : "+ a);
//			    System.out.println("b : "+ b);
			    if(a == b) {
			    	last_display_page = true;
			    }
			    
			    Map<String,String> paraMap = new HashMap<>();
			    paraMap.put("page", String.valueOf(page));
			    paraMap.put("display_cnt",String.valueOf(display_cnt));
			    
				
				List<NoticeDTO> noticeList = sdao.selectAllNotice(paraMap);	//페이지 번호에 알맞는 게시물을 한 페이지에 보여줄 게시물 수만큼가져오는 메소드
				
				
//			    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			    System.out.println("현재보고있는 페이지 : "+ page);
//			    System.out.println("한 페이지당 보여줄 게시물 수 : "+ display_cnt);
//			    System.out.println("한번에 보여줄 페이지번호의 갯수 : "+ display_page);
//			    System.out.println("총 게시물 수 : "+ total_cnt);
//			    System.out.println("총페이지 : "+ totalPage);
//			    System.out.println("아래 시작페이지 : "+ startPage);
//			    System.out.println("아래 끝페이지 : "+ endPage);
//			    System.out.println("현재 페이지가 마지막 페이지단인지 여부 : "+ last_display_page);
				
				
				request.setAttribute("noticeList", noticeList);
				request.setAttribute("display_cnt", display_cnt);
				request.setAttribute("display_page", display_page);
				request.setAttribute("page", page);
				request.setAttribute("total_cnt", total_cnt);	
				request.setAttribute("totalPage", totalPage);	
				request.setAttribute("startPage", startPage);	
				request.setAttribute("endPage", endPage);
				request.setAttribute("last_display_page",last_display_page);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/notice/notice.jsp");
				
			
			}catch(SQLException e) {	//SQL오류가 떳을 경우
				super.setRedirect(true);
//				super.setViewPage(request.getContextPath()+"/notice/notice.dream");	//SQL에러발생 페이지로 이동,경로 정해주기!!
				e.printStackTrace();
			}catch(Exception e) {	//URL에있는 page번호로 장난했을 경우
				e.printStackTrace();
				super.setRedirect(true);
			//	super.setViewPage(request.getContextPath()+"/index.dream");	//그냥 notice.dream 페이지로 URL 어케적지?
				
				super.setViewPage(request.getContextPath()+"/notice/notice.dream");
			}
		}
		else {	//method가 "POST"일 때
			
		}
	}
}
