package jjy.purchaseList.controller;

import java.util.Calendar;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import common.controller.AbstractController;
import jjy.purchase.model.InterPurchaseListDAO;
import jjy.purchase.model.PurchaseListDAO;
import jjy.purchase.model.PurchaseListDTO;

public class PurchaseListView extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String loginedUserid = (String) session.getAttribute("userid");

		try {
			// 현재 날짜와 시간 구하기
			Calendar currentDate = Calendar.getInstance();
			int currentYear = currentDate.get(Calendar.YEAR);
			int currentMonth = currentDate.get(Calendar.MONTH) + 1;
			int currentDay = currentDate.get(Calendar.DATE);
	//		System.out.println("확인용 년/월/일" +currentYear+" "+currentMonth+" "+currentDay);
	
			if (loginedUserid != null) {
	
				String userid = (String) session.getAttribute("userid");
	
	//			if (sessionUserid.equals(userid)) {
	
				InterPurchaseListDAO pdao = new PurchaseListDAO();
	
				// 전달할 시작일, 종료일, 배송상태, 정렬, userid 를 넣는 map
				Map<String, String> purchaseMap = new HashMap<>();
	
				String start_date = request.getParameter("start_date");
				String end_date = request.getParameter("end_date");
				String input_shipping = request.getParameter("input_shipping");
				String sort = request.getParameter("sort");
				String currentShowPageNo = request.getParameter("currentShowPageNo");
	
				// 기본값 지정 //=================================================
				if (start_date == null || start_date == "") {
					// 2022/09/27
					start_date = currentYear + "/" + currentMonth + "/" + currentDay;
				}
	
				if (end_date == null || end_date == "") {
					end_date = currentYear + "/" + (currentMonth - 2) + "/" + currentDay;
				}
	
				if (input_shipping == null || input_shipping == "") {
					input_shipping = "0";
				}
	
				if (sort == null || sort == "") {
					sort = "asc";
				}
				// 기본값 지정 끝 //===============================================
	
				purchaseMap.put("userid", userid);
				purchaseMap.put("start_date", start_date);
				purchaseMap.put("end_date", end_date);
				purchaseMap.put("input_shipping", input_shipping);
				purchaseMap.put("sort", sort);
	
				// 확인용 ///////////////////////////////////////////////////
	//					System.out.println("================================");
	//					System.out.println("확인용 view 시작일: "+start_date);
	//					System.out.println("확인용 view 종료일: "+end_date);
	//					System.out.println("확인용 view 배송상태: "+input_shipping);
	//					System.out.println("확인용 view 사용자 아이디: "+userid);
	//					System.out.println("확인용 view 정렬방식: "+sort);
	//					System.out.println("확인용 viewcurrentShowPageNo1: "+ currentShowPageNo);
	//					System.out.println("=================================");
	//					System.out.println("");
				// 확인용 ///////////////////////////////////////////////////
	
				// 페이징처리 시작
				// ###############################################################################
	
				// 진행중 개수, 종료 개수 알아오기 
				Map<String,String> orderCntMap = pdao.getOrderCnt(loginedUserid);
				request.setAttribute("orderCntMap", orderCntMap);
				
				// 총 페이지 수 알아오기
				int totalPage = pdao.getTotalPage(purchaseMap);
				// System.out.println("확인용 조회해온 totalPage => " + totalPage);
				
				// 총 구매내역 수 알아오기 
				int totalListCnt = pdao.getTotalListCnt(purchaseMap);
				// System.out.println("출력해야 할 구매내역 수 = "+totalListCnt );
	
				// System.out.println("~~~~~~~~~~~~~~~~~~확인용 totalPage => "+totalPage);
				// ~~~~~~~~~~~~~~~~~~확인용 totalPage => 21
				
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("totalListCnt", totalListCnt);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/member/purchase_list.jsp");
			}
			else {
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/index.dream"); // 인덱스 페이지로 이동
			}
		}catch(Exception e) {
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.dream");// 인덱스 페이지로 이동
		}

	}

}