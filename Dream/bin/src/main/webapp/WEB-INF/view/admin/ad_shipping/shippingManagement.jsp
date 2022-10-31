<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="jjy.purchase.model.*"%>

<%
	String ctxPath = request.getContextPath();
%>

<%-- header 호출 --%>
<jsp:include page="/WEB-INF/view/admin/ad_header.jsp" />


<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css"
	href="<%=ctxPath%>/css/admin/shippingManagement.css" />
<link rel="stylesheet" href="<%=ctxPath%>/css/sidebar.css">

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%= ctxPath%>/js/admin/shippngManagement.js" ></script>




<div id="purchase_list" class="mt-4">
<%-- 내용물 시작 --%>

<%-------------------- 사이드바 시작 ----------------------%>

<script>
   $(document).ready(function(){
	    $("div#shipping_cnt_left").trigger("click");
   });// end of $(document).ready(function()----------------------------------
</script>

		<%-- 구매내역 시작  --%>
		<div id="purchase" class="container">
			<form name="purchaseFrm">
				<div id="content_title">
					<strong id="string_title" style="font-size: 24px;">배송관리</strong>
				</div>


				<%-- 상단 진행중, 종료 버튼 시작 --%>
				<div id="status_button">
					<input type="hidden" id="input_shipping" name="input_shipping" value="0" /> 
					<input type="hidden" id="userid" name="userid" value="${sessionScope.userid}" readonly />

					<div id="shipping_cnt_left" class="shipping_cnt">
						<div id="sipping_count" class="purchase_count">${requestScope.OrderCntMap.shipping_ready}</div>
						<div id="shipping" class="shipping_status">배송 준비중</div>
					</div>
					
					<div id="shipping_cnt_middle" class="shipping_cnt">
						<div id="sipping_processing_count" class="purchase_count">${requestScope.OrderCntMap.shipping}</div>
						<div id="shipping_processing" class="shipping_status">배송중</div>
					</div>

					<div id="shipping_cnt_right" class="shipping_cnt">
						<div id="shipping_end_count" class="purchase_count">${requestScope.OrderCntMap.shipping_complete}</div>
						<div id="shipping_completed" class="shipping_status">배송완료</div>
					</div>

				</div>
				<%-- 상단 진행중, 종료 버튼 끝 --%>


				<%-- 기간조회 시작 --%>
				<div id="date_button">
				<%-- 	<button type="button" id="two_month" class="btn_month">최근 2개월</button>
					<button type="button" id="four_month" class="btn_month">4개월</button>
					<button type="button" id="six_month" class="btn_month">6개월</button>

					<div id="from_date" class="input_date">
						<input type="text" name="start_date" id="start_date" style="width: 120px;"value =""></input>~
					</div>

					<div id="to_date" class="input_date">
						<input type="text" name="end_date" id="end_date" style="width: 120px;" value="">
					</div>
				--%>
					<%-- 배송 일괄 처리 버튼 div --%>
			<div id="allCheckAndButtons">
				<div class='div_postSend'>
					<input id="allCheckSend" type="checkbox" name= "allCheckSend" onclick="allCheckBox();" /> <label for="allCheckSend" >전체선택(배송하기)</label>
					<button class="btn_shipping" type="button" onclick="updateShipping();" >배송하기</button>
				</div>
				<div class='div_postEnd'>
					<input id="allCheckEnd" type="checkbox" name= "allCheckEnd" onclick="allCheckBox2();" /> <label for="allCheckEnd" >전체선택(배송완료)</label>
					<button class="btn_shipping" type="button" onclick="updateShippingEnd();" >배송완료</button>
				</div>
			</div>
				
					<button style="display:none;" type="button" id="search_simple">조회</button>
					<button style="display:none;" type="button" id="search_date">기간조회</button>

				</div>
				<%-- 기간조회 끝 --%>



				<%-- 모바일 화면에서 기간 선택 시작 --%>
				<select name="month" id="select_month" class="container" >
					<option value="기간선택">기간선택</option>
					<option></option>
					<option></option>
					<option></option>
					<option></option>
					<option></option>
					<option></option>
					<option></option>
				</select>
				<%-- 모바일 화면에서 기간 선택 끝 --%>

				<hr style="background-color: #ebebeb;">

				<div id="result" class="my-4">
					<%-- 구매일, 상태 버튼 시작 --%>
					<div id="result_buttons">
						<%-- 전체버튼 일단 비활성화 --%>
						<%-- <button id="total">전체 <i class="fa-sharp fa-solid fa-caret-down"></i></button> --%>

						<div id="sort">
							<%-- 여기 input 태그 name 은 테이블의 컬럼? 오름차순, 내림차순 여부 , 진행중, 상태 --%>
							<button style="display:none;" type="button" id="btn_purchaseDate" class="result_detail">
								<input id="sort_date" name="sort" type="hidden" value="" />구매일<i class="fas fa-duotone fa-sort-up"></i>
							</button>
							<%-- <button type="button" id="btn_purchaseStatus" class="result_detail"><input id="sort_status" name="status" type="hidden" value="진행중"/> 상태<i class="fas fa-duotone fa-sort-up"></i></button>--%>
						</div>
					</div>
			</form>
			<%-- 구매일, 상태 버튼 끝 --%>
			
			
			
			<div id="sihpping_title">
				<span class="span_stitle" style="width:100px">&nbsp;</span>
				<span class="span_stitle" id="shipping_pname">상품명</span>
				<span class="span_stitle" id="shipping_onum">주문번호</span>
				<span class="span_stitle" id="shipping_ocnt">구매수량</span>
				<span class="span_stitle" id="shipping_tprice">주문총액</span>
				<span class="span_stitle" id="shipping_tpoint">포인트</span>
				<span class="span_stitle" id="shipping_shipping">배송상태</span>
				<span class="span_stitle" id="shipping_send"></span>
				<span class="span_stitle" id="shipping_end"></span>
				<span class="span_stitle" id="shipping_odate">거래일자</span>
			</div>
			
			
			
			<%-- jsp 넘어가서 조건문 넣어서 제품이 없을경우 거래 내역이 없습니다 출력  --%>
			<div id="no_result" class="text-center my-5" style="color: #22222280; font-size: 13;"></div>
			<%-- jsp 넘어가서 조건문 넣어서 제품이 없을경우 거래 내역이 없습니다 출력 끝  --%>

						
			<%-- 배송준비중 클릭시 보여줄 div --%>
			<div id="show_shipping"></div>
			
			
			<%-- 배송중 클릭시 보여줄 div 	--%>
			<div id="show_shipping_processing"></div>
			
			
			<%-- 종료 클릭시 보여줄 div --%>
			<div id="show_shipping_completed"></div>
			
			
			
			
		<%-- 더보기 버튼 넣기 시작  --%>
			<button type="button" id="btnMoreHIT" value="">더보기</button>
			<span id="totalHITCount"></span>
            <span id="countHIT"></span>

		</div>
		<%-- 구매내역 끝  --%>
</div>
<%-- 내용물 끝 --%>
</div>



<%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />