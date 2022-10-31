package choi.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import choi.product.model.InterProductDAO;
import choi.product.model.ProductDAO;
import choi.product.model.ProductDTO;
import common.controller.AbstractController;
import my.util.MyUtil;

public class ShopController extends AbstractController{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.goBackURL(request);
		
		String method = request.getMethod();
		
		if("GET".equalsIgnoreCase(method)) { //요청방식이 "GET"일 때 
			try {
				HttpSession session = request.getSession();
				//변수 선언
				String userid = (String) session.getAttribute("userid");
				String category = null;			//제품카테고리
				String detail_category = null;	//제품상세카테고리
				String bestyn = "N";			//베스트상품여부
				String gender = "없음";			//남성용인지 여성용인지
				String sort = "전체";				//정렬방식
				String start_price = null;	//시작가격
				String end_price = null;	//끝가격
				int page= 1;					//페이지번호
				
				
				//getParameter로 값 다 받아오기
				if(request.getParameter("p")!=null && request.getParameter("p").trim() != "") {		//파라미터 page가 not empty라면!
					page = Integer.parseInt(request.getParameter("p").trim());
				}
				if(request.getParameter("category")!=null && request.getParameter("category").trim() != "") {		
					category = request.getParameter("category").trim();
				}
				if(request.getParameter("detail_category")!=null && request.getParameter("detail_category").trim() != "") {
					detail_category = request.getParameter("detail_category").trim();
				}
				if(request.getParameter("bestyn")!=null && request.getParameter("bestyn").trim() != "") {		
					bestyn = request.getParameter("bestyn").trim();
				}
				if(request.getParameter("gender")!=null && request.getParameter("gender").trim() != "") {		
					gender = request.getParameter("gender").trim();
				}
				if(request.getParameter("sort")!=null && request.getParameter("sort").trim() != "") {		
					sort = request.getParameter("sort").trim();
				}
				if(request.getParameter("start_price")!=null && request.getParameter("start_price").trim() != "") {		
					start_price = request.getParameter("start_price").trim();
				}
				if(request.getParameter("end_price")!=null && request.getParameter("end_price").trim() != "") {
					end_price = request.getParameter("end_price").trim();
				}
				
				InterProductDAO pdao = new ProductDAO();
				Map<String,String> paraMap = new HashMap<>();
				
				paraMap.put("category", category);
				paraMap.put("detail_category", detail_category);
				paraMap.put("bestyn", bestyn);
				paraMap.put("gender", gender);
				paraMap.put("sort", sort);
				paraMap.put("start_price", start_price);
				paraMap.put("end_price", end_price);
				
				int total_cnt = pdao.cntAllProduct(paraMap);              //총 게시물 수
				float display_cntf = 20f;	                              //한 페이지당 보여줄 게시물 수 float형
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
			    
			    
			    paraMap.put("page", String.valueOf(page));
			    paraMap.put("display_cnt",String.valueOf(display_cnt));
			    paraMap.put("userid",userid);
			    
			    
				List<ProductDTO> productList = pdao.selectAllProduct(paraMap);	//페이지 번호에 알맞는 게시물을 한 페이지에 보여줄 게시물 수만큼가져오는 메소드
				
				
//			    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			    System.out.println("현재보고있는 페이지 : "+ page);
//			    System.out.println("한 페이지당 보여줄 게시물 수 : "+ display_cnt);
//			    System.out.println("한번에 보여줄 페이지번호의 갯수 : "+ display_page);
//			    System.out.println("총 게시물 수 : "+ total_cnt);
//			    System.out.println("총페이지 : "+ totalPage);
//			    System.out.println("아래 시작페이지 : "+ startPage);
//			    System.out.println("아래 끝페이지 : "+ endPage);
//			    System.out.println("현재 페이지가 마지막 페이지단인지 여부 : "+ last_display_page);
				
				
				
				request.setAttribute("category", category);
				request.setAttribute("detail_category", detail_category);
				request.setAttribute("bestyn", bestyn);
				request.setAttribute("gender", gender);
				request.setAttribute("sort", sort);
				request.setAttribute("start_price", start_price);
				request.setAttribute("end_price", end_price);
				request.setAttribute("productList", productList);
				request.setAttribute("display_cnt", display_cnt);
				request.setAttribute("display_page", display_page);
				request.setAttribute("page", page);
				request.setAttribute("total_cnt", total_cnt);	
				request.setAttribute("totalPage", totalPage);	
				request.setAttribute("startPage", startPage);	
				request.setAttribute("endPage", endPage);
				request.setAttribute("last_display_page",last_display_page);
				
				///////////////////////////////////////////////////////
				
				String currentURL = MyUtil.getCurrentURL(request);
				request.setAttribute("currentURL", currentURL);
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/product/shop.jsp");
			}catch(Exception e) {	//URL에있는 page번호로 장난했을 경우
				e.printStackTrace();
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/product/shop.dream");	//shop.dream 페이지로 redirect
			}
		}
		else {	//요청방식이 "POST"일 때 
		}
	}//end of public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {})------
}
