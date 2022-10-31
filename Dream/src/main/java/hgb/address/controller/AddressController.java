package hgb.address.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import hgb.address.model.AddressDAO;
import hgb.address.model.AddressDTO;
import hgb.address.model.InterAddressDAO;



public class AddressController extends AbstractController{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {				
		
		String method = request.getMethod();		
           
		
		if("POST".equalsIgnoreCase(method)) {	//method가 "POST" 일 때
						
			
		}
		
		else {	//method가 "GET"일 때 
			
			HttpSession session = request.getSession();	//로그인중인 userid값 가져오기위한 session 객체생성
			
			//로그인 중인 사람의 userid 값 가져오기
			String userid = (String)session.getAttribute("userid");
//			String userid = "josh@gmail.com";	//세션 코드 합치면 위의코드로 변경하기 이건 가라로  해놓은거임
			
			
			if(userid == null) {	//get요청을 한 사용자가 관리자로 로그인중이 아니라면
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/login/login.dream");
			}
			else {
			
			
			
			
			
			
			
			try {
			
				int page= 1;
				if(request.getParameter("p")!=null && request.getParameter("p").trim() != "") {		//파라미터 page가 not empty라면!
					page = Integer.parseInt(request.getParameter("p").trim());
				}
				
				
				// 기본배송지 하나 가져오기(한개)
				InterAddressDAO adao = new AddressDAO();
				
				
				int total_cnt = adao.cntAllAddress(userid);                      //총 게시물 수 기본배송지 제외
				float display_cntf = 10f;	                              //한 페이지당 보여줄 게시물 수 float형
				int display_cnt = (int)display_cntf;	                  //한 페이지당 보여줄 게시물 수 int형
				int display_page = 5;					                  //한번에 보여줄 페이지번호의 갯수 int형
				float display_pagef = 5f;								  //한번에 보여줄 페이지번호의 갯수 float형
				int totalPage = (int)Math.ceil(total_cnt/ display_cntf);  //총 게시물수 / 한페이지당 보여줄 게시물 수 를 올림
				
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
				/*
				 * System.out.println(
				 * "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				 * System.out.println("a : "+ a); System.out.println("b : "+ b);
				 */
			    if(a == b) {
			    	last_display_page = true;
			    }
			    
			    
				
			    
			    Map<String,String> paraMap = new HashMap<>();
			    paraMap.put("page", String.valueOf(page));
			    paraMap.put("display_cnt",String.valueOf(display_cnt));
			    paraMap.put("userid", userid);
			    
				
			    List<AddressDTO> addressList = adao.selectAddress(paraMap);
				
				
				/*
				 * System.out.println(
				 * "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				 * System.out.println("현재보고있는 페이지 : "+ page);
				 * System.out.println("한 페이지당 보여줄 게시물 수 : "+ display_cnt);
				 * System.out.println("한번에 보여줄 페이지번호의 갯수 : "+ display_page);
				 * System.out.println("총 게시물 수 : "+ total_cnt); System.out.println("총페이지 : "+
				 * totalPage); System.out.println("아래 시작페이지 : "+ startPage);
				 * System.out.println("아래 끝페이지 : "+ endPage);
				 * System.out.println("현재 페이지가 마지막 페이지단인지 여부 : "+ last_display_page);
				 */
				
				
				request.setAttribute("addressList", addressList);
				request.setAttribute("display_cnt", display_cnt);
				request.setAttribute("display_page", display_page);
				request.setAttribute("page", page);
				request.setAttribute("total_cnt", total_cnt);	
				request.setAttribute("totalPage", totalPage);	
				request.setAttribute("startPage", startPage);	
				request.setAttribute("endPage", endPage);
				request.setAttribute("last_display_page",last_display_page);
				
				
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			 	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				
				
				
				AddressDTO basic_adto = adao.select_basic_address(userid);
				AddressDTO address_num = adao.select_basic_address(userid);
				String basic_mobile = basic_adto.getMobile();
				
				//전화번호를 첫번째자리 3자리, 두번째자리 4자리, 세번째자리 4자리 
	//			01074239713
				String basic_first_mobile = basic_mobile.substring(0,3);
				String basic_second_mobile = basic_mobile.substring(3,4) + "***";
				String basic_third_mobile = basic_mobile.substring(7,8) + "***";
				
				request.setAttribute("basic_mobile",basic_mobile);
				request.setAttribute("basic_first_mobile", basic_first_mobile);
				request.setAttribute("basic_second_mobile", basic_second_mobile);
				request.setAttribute("basic_third_mobile", basic_third_mobile);
				request.setAttribute("basic_adto", basic_adto);		
				request.setAttribute("address_num", address_num);
				
				
				// 기본배송지가 아닌것들 가져오기(여러개) 여러개니까 리스트로 가져오는데, 리스트 안에 들어가는것은 AddressDTO
	//			List<AddressDTO> adressList = adao.select_no_basic_address(userid);
				
	//			나온 값을
	//			request에 담아서
				
				
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/member/address.jsp");
				
			} catch (NullPointerException e) {	//배송지가 등록된게 아무것도 없을때
				e.printStackTrace();
				String basic_mobile = null;
				request.setAttribute("basic_mobile", basic_mobile);
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/member/address.jsp");
			} catch (Exception e) {	//모든 exception에 대한 처리
				e.printStackTrace();
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/member/address.dream");
			}//end of try-catch-----------------
			
		}
	  }
		
	}		
}


