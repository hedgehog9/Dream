package josh.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import josh.member.model.InterMemberDAO;
import josh.member.model.MemberDAO;
import josh.member.model.MemberDTO;

public class MemberManage extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String ctxPath = request.getContextPath();
		
		if(super.checkLogin(request)) {
			
			HttpSession session = request.getSession();
			String userid = (String) (session.getAttribute("userid"));
			
			if("admin".equals(userid)) {
				
				String searchType = request.getParameter("searchType");
				String searchWord = request.getParameter("searchWord");
				String start_date = request.getParameter("start_date");
				String end_date = request.getParameter("end_date");
				
				// System.out.println("searchType =>" + searchType);
				
				InterMemberDAO mdao = new MemberDAO();
	            
	            Map<String, String> paraMap = new HashMap<>();
	            paraMap.put("searchType", searchType);
	            paraMap.put("searchWord", searchWord);
	            paraMap.put("start_date", start_date);
	            paraMap.put("end_date", end_date);
	            
	            
	            
	            String currentShowPageNo = request.getParameter("currentShowPageNo");
	            if(currentShowPageNo == null) { // 만약 null 값이라면 맨 처음 페이지를 보여준다.
	            		currentShowPageNo = "1";
	            }
	            
	            try {
	            		if(Integer.parseInt(currentShowPageNo) < 1) {
	            			currentShowPageNo = "1";
	            		}
            		
	            } catch (NumberFormatException e) {
	            		currentShowPageNo = "1";
				}
	            
	            paraMap.put("currentShowPageNo", currentShowPageNo);
	            
	            int totalPage = mdao.getTotalPage(paraMap);
	            
	            // System.out.println("totalPage =>" + totalPage);
	            
	            
	            if( Integer.parseInt(currentShowPageNo) > totalPage ) {
	            		currentShowPageNo = "1";
	            		paraMap.put("currentShowPageNo", currentShowPageNo);
	            }
	            
	            
	            List<MemberDTO> memberList = mdao.selectAllMember(paraMap);
	            
	            request.setAttribute("memberList", memberList);
	            
	            String pageBar = "";
	            
	            int blockSize = 5;
	            // blockSize 는 블럭(토막)당 보여지는 페이지번호의 개수이다.
	            
	            int loop = 1;
	            // loop는 1부터 증가하여 1개 블럭을 이루는 페이지번호의 개수(지금은 10개)까지만 증가하는 용도이다.
	            
	            // !!!! 다음은 pageNo 를 구하는 공식이다. !!!! //
	            int pageNo = ( (Integer.parseInt(currentShowPageNo) - 1)/blockSize ) * blockSize + 1 ;
	            // pageNo는 페이지바에서 보여지는 첫번째 번호이다.
	            
	            if(searchType == null) {
	            		searchType = "";
	            }
	            
	            if(searchWord == null) {
	            		searchWord = "";
	            }
	            
	            if(start_date == null) {
	            		start_date = "";
	            }
            
	            if(end_date == null) {
	            		end_date = "";
	            }
	            
	            request.setAttribute("searchType", searchType);
	            request.setAttribute("searchWord", searchWord);
	            request.setAttribute("start_date", start_date);
	            request.setAttribute("end_date", end_date);
	            
	            // ****[맨처음][이전] 만들기 **** //
	            if(pageNo != 1) {
		     	   pageBar += "<li class='page-item'> <a class='page-link' href='memberManage.dream?&currentShowPageNo=1&searchType="+searchType+"&searchWord="+searchWord+"&start_date="+start_date+"&end_date="+end_date+"'> <i class='fa-solid fa-angles-left'></i> </a> </li>";
		     	   pageBar += "<li class='page-item'> <a class='page-link' href='memberManage.dream?&currentShowPageNo="+(pageNo-1)+"&searchType="+searchType+"&searchWord="+searchWord+"&start_date="+start_date+"&end_date="+end_date+"'> <i class='fa-solid fa-angle-left'></i></a> </li>";
	            }
	         	   
	         	while( !(loop > blockSize || pageNo > totalPage) ) {
	            	
	            		if(pageNo == Integer.parseInt(currentShowPageNo)) {
	            			pageBar += "<li class='page-item active'> <a class='page-link' href='#'>"+pageNo+"</a> </li>";
	            		}
	            		else {
	            			pageBar += "<li class='page-item'> <a class='page-link' href='memberManage.dream?sizePerPage=&currentShowPageNo="+pageNo+"&searchType="+searchType+"&searchWord="+searchWord+"&start_date="+start_date+"&end_date="+end_date+"'>"+pageNo+"</a> </li>";
	            		}
	            		
	            		loop++;		// 1 2 3 4 5 6 7 8 9 10
	            		
	            		pageNo++;	//  1  2  3  4  5  6  7  8  9 10
	            					// 11 12 13 14 15 16 17 18 19 20		
	            					// 21 
	            					// 위의 것은 sizePerPage 가 10 일때 페이지바 이다.
	            		
	            }// end of while()--------------------------
	            
	            // ****[다음][마지막] 만들기 **** //
	            // 첫번째 블럭(1 2 3 4 5 6 7 8 9 10)인 경우 pageNo 11 이고
	            // 두번째 블럭(11 12 13 14 15 16 17 18 19 20)인 경우 pageNo 21 이고
	            // 세번째 블럭(21)인 경우 pageNo 22 가 된다.
	            
	            if(pageNo <= totalPage) {
	            	   pageBar += "<li class='page-item'> <a class='page-link' href='memberManage.dream?sizePerPage=&currentShowPageNo="+pageNo+"&searchType="+searchType+"&searchWord="+searchWord+"&start_date="+start_date+"&end_date="+end_date+"'> <i class='fa-solid fa-angle-right'></i> </a> </li>";
	            	   pageBar += "<li class='page-item'> <a class='page-link' href='memberManage.dream?sizePerPage=&currentShowPageNo="+totalPage+"&searchType="+searchType+"&searchWord="+searchWord+"&start_date="+start_date+"&end_date="+end_date+"'> <i class='fas fa-solid fa-angles-right'></i> </a> </li>";
	            }
	            
	            
	            request.setAttribute("pageBar", pageBar);
	            
	            super.setRedirect(false);
	    			super.setViewPage("/WEB-INF/view/admin/ad_member/memberManage.jsp");
			     
			}
			else { // admin 계정으로 진입한게 아닐때
				String message = "관리자 이외는 접근이 불가합니다.";
	            String loc = request.getContextPath()+"/index.dream";
	            
	            request.setAttribute("message", message);
	            request.setAttribute("loc", loc);
	            
	            super.setRedirect(false);
	            super.setViewPage("/WEB-INF/joshmsg.jsp");
			}
			
		}
		else { // 로그인을 하지 않고 진입했을때
			super.setRedirect(true);
	        super.setViewPage(ctxPath+"/login/login.dream");
		}
		
		
		
			
		
	}

}
