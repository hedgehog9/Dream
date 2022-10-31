<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();
%>

  <%--header 호출 --%>
  <jsp:include page="/WEB-INF/view/header.jsp" />
  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/add_payInfo.css" />
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%= ctxPath%>/js/payinfo.js" ></script>



<body>







<!-- 컨테이너 시작  -->
<div class="container">



<!--------------------------------------------------- 사이드바 시작 --------------------------------------------------->
		<div class="sidebar mr-5" style="width: 180px;">
			<div>
				<h4 class="mb-4" style="font-weight: bold;">마이 페이지</h4>
			</div>
			<div class="category-section mb-5">
				<h5 class="category-title font-weight-bold">쇼핑 정보</h5>
				<ul class="nav flex-column">
					<li class="nav-item"><a class="nav-link pl-0 text-muted"
						href="#">구매 내역</a></li>
					<li class="nav-item"><a class="nav-link pl-0 text-muted"
						href="#">관심 상품</a></li>
				</ul>
			</div>

			<div class="category-section">
				<h5 class="category-title font-weight-bold">내 정보</h5>
				<ul class="nav flex-column">
					<li class="nav-item"><a class="nav-link pl-0 text-muted"
						href="#">프로필 정보</a></li>
					<li class="nav-item"><a class="nav-link pl-0 text-muted"
						href="#">주소록</a></li>
					<li class="nav-item"><a class="nav-link pl-0 text-muted"
						href="#">결제정보</a></li>
					<li class="nav-item"><a class="nav-link pl-0 text-muted"
						href="#">포인트</a></li>
				</ul>
			</div>
		</div>
<!--------------------------------------------------- 사이드바 끝 --------------------------------------------------->




<!--------------------------------------------------- 메인컨텐츠 시작 --------------------------------------------------->
<div class="content_area">
    <div class="my_addressbook">
      <div class="content_title" id="pay_title">
					<div class="title">
						<h3>결제 정보</h3>		
						<p class="sub_title">수수료(페널티, 착불배송비 등)가 정산되지 않을 경우, 별도 고지 없이 해당 금액을 결제 시도할 수 있습니다.</p>				
					</div>
					 <div class="btn_box" id="pay_btn_box">
						<a href="#" id="pay_add_btn" class="btn_add">							
							<span class="btn_txt">+ 새 카드 추가하기</span>						     
						</a>
					</div>
</div>				
				
				
				<!------------------------------------------------------------- 결제정보 없을시 출력되는 부분 시작 ------------------------------------------------------------->
				
				
				
				
				<div class="empty_area" id="no_pay_area">
					<p class="desc" id="pay_desc">
						추가하신 결제정보가 없습니다.
					</p>
					
				</div>
				
				
				
				
				<!------------------------------------------------------------- 결제정보 없을시 출력되는 부분 끝 ------------------------------------------------------------->




                <!------------------------------------------------------------- 결제정보 있을때 출력되는 부분 시작 ------------------------------------------------------------->
             
                <div class="my_list" id="yes_pay_area">


					<div class="basic" id="pay_basic">
						<div id="button" class="my_item" default-mark="기본 결제">
							<div class="info_bind">
								<div class="card_info">
									<a class="btn_outlinegrey_small2"> KB</a>
									<div class="card_num">
										<span class="num_bind d-flex"> <span class="dot"><span
												class="dot">****</span></span><span class="hyphen">-</span> <span
											class="dot"><span class="dot">****</span></span><span
											class="hyphen">-</span> <span class="dot">****</span><span
											class="hyphen">-</span> <span class="last_num_box"><span
												class="last_num">4005</span></span>
										</span> <span class="mark" id="pay_mark">기본 결제</span>
									</div>
								</div>
							</div>
							<div class="btn_bind">
								<a href="#" class="btn_outlinegrey_small2" id="basic_delete"> 삭제 </a>
							</div>
						</div>
					</div>






		     <div data-v-1c284ef0="" class="other" id="other_pay">
						<div data-v-1c284ef0="" class="other_list">
							<div id="button" data-v-7d49a47c="" class="my_item" data-v-1c284ef0="">
								<div data-v-7d49a47c="" class="info_bind">
									<div class="card_info" id="other_card_info">
										<a class="btn_outlinegrey_small2"> SH</a>
										<div class="card_num">
											<span class="num_bind d-flex"> <span class="dot"><span
													class="dot">****</span></span><span class="hyphen">-</span> <span
												class="dot"><span class="dot">****</span></span><span
												class="hyphen">-</span> <span class="dot">****</span><span
												class="hyphen">-</span> <span class="last_num_box"><span
													class="last_num">4005</span></span>
											</span>

										</div>
									</div>
								</div>
								<div class="btn_bind" id="other_bind">
									<a href="#" class="btn_outlinegrey_small2"> 기본결제 </a> <a
										href="#" class="btn_outlinegrey_small2" id="delete"> 삭제 </a>
								</div>
							</div>
							
								
								
								
								
								
								<div id="button" data-v-7d49a47c="" class="my_item" data-v-1c284ef0="">
								<div data-v-7d49a47c="" class="info_bind">
									<div class="card_info" id="other_card_info">
										<a class="btn_outlinegrey_small2"> NH</a>
										<div class="card_num">
											<span class="num_bind d-flex"> <span class="dot"><span
													class="dot">****</span></span><span class="hyphen">-</span> <span
												class="dot"><span class="dot">****</span></span><span
												class="hyphen">-</span> <span class="dot">****</span><span
												class="hyphen">-</span> <span class="last_num_box"><span
													class="last_num">4005</span></span>
											</span>

										</div>
									</div>
								</div>
								<div class="btn_bind" id="other_bind">
									<a href="#" class="btn_outlinegrey_small2"> 기본결제 </a> <a
										href="#" class="btn_outlinegrey_small2" id="delete"> 삭제 </a>
								</div>
							</div>
							
								
								
						</div>
					</div>


<!-------------------------------------------------------- 페이지번호 시작 ---------------------------------------------------------->


					<div class="pagination" style="">
						<div data-v-1f9de2f0="" class="pagination_box_first_last" style="">
						
						<!---------------------첫 페이지 버튼 시작  ----------------------->
							<!-- <div data-v-1f9de2f0="" class="prev_btn_box">
								<a data-v-1f9de2f0="" href="/my/address?page=1" class="btn_arr"
									aria-label="첫 페이지"><svg data-v-1f9de2f0=""
										xmlns="http://www.w3.org/2000/svg"
										class="arr-page-first icon sprite-icons">
										<use data-v-1f9de2f0=""
											href="/_nuxt/1a4fefc9c245c25be8c820c7d584e4d7.svg#i-arr-page-first"
											xlink:href="/_nuxt/1a4fefc9c245c25be8c820c7d584e4d7.svg#i-arr-page-first"></use></svg></a><a
									data-v-1f9de2f0="" href="/my/address?page=0" class="btn_arr"
									aria-label="이전 페이지"><svg data-v-1f9de2f0=""
										xmlns="http://www.w3.org/2000/svg"
										class="arr-page-prev icon sprite-icons">
										<use data-v-1f9de2f0=""
											href="/_nuxt/1a4fefc9c245c25be8c820c7d584e4d7.svg#i-arr-page-prev"
											xlink:href="/_nuxt/1a4fefc9c245c25be8c820c7d584e4d7.svg#i-arr-page-prev"></use></svg></a>
							</div> -->
							<!---------------------첫 페이지 버튼 끝  ----------------------->
							
							<!-- 
							<div data-v-1f9de2f0="" class="page_bind">
								<a data-v-1f9de2f0="" href="/my/address?page=1"
									class="btn_page_active" aria-label="1페이지"> 1 </a>
							</div> -->
							
							
							
							<!---------------------마지막 페이지 버튼 시작  ----------------------->
							<!-- <div data-v-1f9de2f0="" class="next_btn_box">
								<a data-v-1f9de2f0="" href="/my/address?page=2" class="btn_arr"
									aria-label="다음 페이지"><svg data-v-1f9de2f0=""
										xmlns="http://www.w3.org/2000/svg"
										class="arr-page-next icon sprite-icons">
										<use data-v-1f9de2f0=""
											href="/_nuxt/1a4fefc9c245c25be8c820c7d584e4d7.svg#i-arr-page-next"
											xlink:href="/_nuxt/1a4fefc9c245c25be8c820c7d584e4d7.svg#i-arr-page-next"></use></svg></a><a
									data-v-1f9de2f0="" href="/my/address?page=1" class="btn_arr"
									aria-label="마지막 페이지"><svg data-v-1f9de2f0=""
										xmlns="http://www.w3.org/2000/svg"
										class="arr-page-last icon sprite-icons">
										<use data-v-1f9de2f0=""
											href="/_nuxt/1a4fefc9c245c25be8c820c7d584e4d7.svg#i-arr-page-last"
											xlink:href="/_nuxt/1a4fefc9c245c25be8c820c7d584e4d7.svg#i-arr-page-last"></use></svg></a>
							</div> -->
							<!---------------------마지막 페이지 버튼 끝  ----------------------->
							
						</div>
					</div>



					<!-------------------------------------------------------- 페이지번호 끝 ---------------------------------------------------------->






</div>				
<!------------------------------------------------------------- 결제정보가 있을때 출력되는 부분 끝 -------------------------------------------------------------></div> 


</div>
<!--------------------------------------------------- 메인컨텐츠 끝 --------------------------------------------------->

</div>
<!-- 컨테이너 끝  -->




<%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />
