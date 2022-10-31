package jjy.admin.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import jjy.purchase.model.InterPurchaseListDAO;
import jjy.purchase.model.PurchaseListDAO;
import jjy.purchase.model.PurchaseListDTO;

public class ShippingMangement extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();

		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		try {

			if ("GET".equalsIgnoreCase(method)) {

				// 현재 날짜와 시간 구하기
				Calendar currentDate = Calendar.getInstance();
				int currentYear = currentDate.get(Calendar.YEAR);
				int currentMonth = currentDate.get(Calendar.MONTH) + 1;
				int currentDay = currentDate.get(Calendar.DATE);
				// System.out.println("확인용 년/월/일" +currentYear+" "+currentMonth+" "+currentDay);

				if ("admin".equals(userid)) {

					InterPurchaseListDAO pdao = new PurchaseListDAO();

					// 전달할 시작일, 종료일, 배송상태, 정렬, userid 를 넣는 map
					Map<String, String> purchaseMap = new HashMap<>();

					String start_date = request.getParameter("start_date");
					String end_date = request.getParameter("end_date");
					String input_shipping = request.getParameter("input_shipping");
					String sort = request.getParameter("sort");

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

					// ###############################################################################

					// 배송 준비중, 배송중, 배송완료 각각의 개수 알아오기
					Map<String, String> OrderCntMap = pdao.admin_getOrderCnt();

					// 구매내을 구매내역 dto타입의 list 로 받아와 뷰단에 넘기기
					PurchaseListDTO pdto = new PurchaseListDTO();
					List<PurchaseListDTO> purchaseList = pdao.getAllPurchaseList(input_shipping);

					// System.out.println("확인용 purchaseList =>" + purchaseList);

					request.setAttribute("OrderCntMap", OrderCntMap); // 배송준비중, 배송중, 배송완료 개수
					request.setAttribute("purchaseList", purchaseList); // 배송 상태에 따른 모든 구매내역

					// 총 페이지 수 알아오기
					int totalPage = pdao.getTotalPage(purchaseMap);
					// System.out.println("확인용 조회해온 totalPage => " + totalPage);

					// 총 구매내역 수 알아오기
					int totalListCnt = pdao.getTotalListCnt(purchaseMap);

					request.setAttribute("totalPage", totalPage);
					request.setAttribute("totalListCnt", totalListCnt);

					super.setRedirect(false);
					super.setViewPage("/WEB-INF/view/admin/ad_shipping/shippingManagement.jsp");

				} // end of if ("admin".equals(userid)) {}---------------
				else {
					super.setRedirect(true);
					super.setViewPage(request.getContextPath() + "/index.dream");
				}
			}
		} catch (Exception e) {
			super.setRedirect(true);
			super.setViewPage(request.getContextPath() + "/index.dream");

		}
	}
}
