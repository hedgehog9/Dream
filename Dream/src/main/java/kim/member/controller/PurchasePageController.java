package kim.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import kim.member.model.InterMemberDAO;
import kim.member.model.MemberDAO;
import kim.member.model.MemberVO;
import kim.product.model.InterProductDAO;
import kim.product.model.ProductDAO;
import kim.product.model.ProductVO;

import hgb.address.model.AddressDAO;
import hgb.address.model.AddressDTO;
import hgb.address.model.InterAddressDAO;

public class PurchasePageController extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod(); 
		HttpSession session = request.getSession();
		String loginuserid = (String) session.getAttribute("userid");
		// system.out.println(method);
		if("GET".equalsIgnoreCase(method)) {
		// GET 방식이라면   
				
						
		}
		else {
			// 필요한 값들 일단 정리
			// System.out.println("data 도착!");
			// System.out.println(request.getParameter("length"));
			// System.out.println(request.getParameter("productNum"));
			int length = Integer.parseInt(request.getParameter("length")); 
			String productNum = request.getParameter("productNum");
			ArrayList<String>sizeArr = new ArrayList<String>();
			ArrayList<String>cntArr = new ArrayList<String>();
			request.setAttribute("length", length);
			for(int i=0; i<length ;i++) {
				sizeArr.add((String)request.getParameter("size"+i)); 
				cntArr.add((String)request.getParameter	("cnt"+i)); 
			}
			
			// system.out.println("sizeArr"+sizeArr);
			// system.out.println("cntArr"+cntArr);
			
			request.setAttribute("sizeArr", sizeArr);
			request.setAttribute("cntArr", cntArr);
			

			// system.out.println(loginuserid);
			// memberVO 객체 하나로 드갈 예정
			InterMemberDAO mdao = new MemberDAO();
			MemberVO user = new MemberVO();
			// 일단 포인트 얼마 남았나 체크
			request.setAttribute("discountPrice",request.getParameter("discountPrice"));
			
			
			user = mdao.pointCheck(loginuserid);
		
			request.setAttribute("user", user);
			// system.out.println("fullPrice"+request.getParameter("fullPrice"));
			request.setAttribute("fullPrice",request.getParameter("fullPrice"));
			
			// 선택한 제품의 정보도 가져갈 예정
			
			InterProductDAO pdao = new ProductDAO();
			ProductVO product = pdao.getDetail(productNum);
			product.setOrder_product_size(sizeArr);
			product.setOrder_product_cnt(cntArr);
			
			// system.out.println(product.getOrder_product_cnt());
			// system.out.println(product.getOrder_product_size());

			request.setAttribute("product", product);
			
			// 주소지 가져오기
			
			// 전부불러오기
			try {
				
				int page= 1;
				if(request.getParameter("p")!=null && request.getParameter("p").trim() != "") {		//파라미터 page가 not empty라면!
					page = Integer.parseInt(request.getParameter("p").trim());
				}
				
				
				// 기본배송지 하나 가져오기(한개)
				InterAddressDAO adao = new AddressDAO();
				AddressDTO basic_adto = adao.select_basic_address(loginuserid);
				
				int total_cnt = adao.cntAllAddress(loginuserid);                      //총 게시물 수 기본배송지 제외
				float display_cntf = 5f;	                              //한 페이지당 보여줄 게시물 수 float형
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
			    // system.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			    // system.out.println("a : "+ a);
			    // system.out.println("b : "+ b);
			    if(a == b) {
			    	last_display_page = true;
			    }

		    
			    Map<String,String> paraMap = new HashMap<>();
			    paraMap.put("page", String.valueOf(page));
			    paraMap.put("display_cnt",String.valueOf(display_cnt));
			    paraMap.put("userid", loginuserid);
			    
				
			    List<AddressDTO> addressList = adao.selectAddress(paraMap);
				
				
			    // system.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			    // system.out.println("현재보고있는 페이지 : "+ page);
			    // system.out.println("한 페이지당 보여줄 게시물 수 : "+ display_cnt);
			    // system.out.println("한번에 보여줄 페이지번호의 갯수 : "+ display_page);
			    // system.out.println("총 게시물 수 : "+ total_cnt);
			    // system.out.println("총페이지 : "+ totalPage);
			    // system.out.println("아래 시작페이지 : "+ startPage);
			    // system.out.println("아래 끝페이지 : "+ endPage);
			    // system.out.println("현재 페이지가 마지막 페이지단인지 여부 : "+ last_display_page);
			    // system.out.println("사이즈 : "+ addressList.size());
			    
				
				
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
				
				
				
				
				

				
				AddressDTO address_num = adao.select_basic_address(loginuserid);
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
				super.setViewPage("/WEB-INF/view/member/purchasePage.jsp");
				
			} catch (NullPointerException e) {	//배송지가 등록된게 아무것도 없을때
				e.printStackTrace();
				String basic_mobile = null;
				request.setAttribute("basic_mobile", basic_mobile);
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/member/purchasePage.jsp");
			} catch (Exception e) {	//모든 exception에 대한 처리
				e.printStackTrace();
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/member/address.dream");
			}//end of try-catch-----------------
			
		
			
			
			super.setViewPage("/WEB-INF/view/member/purchasePage.jsp");
		}
	}

}
