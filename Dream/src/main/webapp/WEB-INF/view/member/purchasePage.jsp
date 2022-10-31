<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="jjy.purchase.model.*" %>
<%-- 다음 주소검색  --%>
<script type="text/javascript" src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<%
   String ctxPath = request.getContextPath();
%>

  <%--header 호출 --%>
  <jsp:include page="/WEB-INF/view/header.jsp" />
  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/purchasePage.css" />
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%= ctxPath%>/js/purchasePage.js" ></script>


  <div class="container">

 

      <%-- 제품 이미지 + 상품 디테일 --%>
      <div class="product_info_area">
      <section>
        <div class="product_info">
          <div class="product">
          	      <%-- 물결 --%>
		      <div class="wrap">
		  		
				   <c:forEach var="imgvo" items="${product.product_image_array}" varStatus="status">	
					<c:if test="${status.index==0}">
						<div id = "modalImage">
							<img src="<%= ctxPath%>/images/제품이미지/${imgvo}"
							 alt="제품이미지를<br>등록해주세요">
							
						</div>
					</c:if>	
				
				</c:forEach>
				</div>	
            
          </div>
          <div class="product_detail">
            <strong class="model_numbe-r">${product.product_num}</strong>
            <p class="model_title">${product.product_name}</p>
            <p class="model_ko">${product.product_name}</p>
            <div class="model_desc">
            	
            	<table class="table">
				 
				  <tbody>
				   <c:forEach var="size" items="${product.order_product_size}" varStatus="status">
				    <tr id="SeletedOption">
				      <td>${size}</td>
				      <td>${product.order_product_cnt[status.index]}</td>
				      <td>${product.order_product_cnt[status.index]*discountPrice}</td>
				    </tr>
				   
		    		</c:forEach>
            	</tbody>
          		</table>  	
            
            
            </div>
          </div>
        </div>  
      </section>


      <%-- 제품 주소 && 배송 섹션--%>
      <section style="padding:32px">
      <div class="section_title">
            <h3 class="title_txt">배송지 선택</h3>
          </div>
      
        <div class="section_unit">
          <c:if test="${empty requestScope.basic_mobile}">
			<div class="empty_area" id="no_add_area">
				<p class="desc">
					배송지 정보가 없습니다.<br />새 배송지를 등록해주세요
				</p>
				<span href="#" id="add_btn2" class="btn_btn_add2" data-toggle="modal" data-target="#add_address" onclick="new_add()">새 배송지 추가 </span>
			</div>
		  </c:if>
				
 				
				
				<%------------------------------------------------------------- 주소정보 없을시 출력되는 부분 끝 -------------------------------------------------------------%>




                <%------------------------------------------------------------- 주소정보가 있을때 출력되는 부분 시작 -------------------------------------------------------------%>
                <c:if test="${not empty requestScope.basic_mobile}">
                <div class="my_list " id="yes_add_area">
                     		<div class="basic adressOption selectedAddress" id="div${requestScope.basic_adto.address_num}">
						<div class="my_item" default-mark="기본 배송지">
							<div class="info_bind">
								
								<div class="address_info">
									<div class="name_b	ox">
										<input type="hidden" id="address_num" name="address_num" value="${requestScope.basic_adto.address_num}"/>${requestScope.basic_adto.address_num}
										<span id="basic_text" class="name">${requestScope.basic_adto.order_name} </span>
										<span class="mark">기본 배송지</span>
									</div>
									<p id="basic_text" class="phone">
										${requestScope.basic_first_mobile}-${requestScope.basic_second_mobile}-${requestScope.basic_third_mobile}
									</p>
									<div  class="address_box">
										(<span id="basic_text"  class="zipcode">${requestScope.basic_adto.post_code }</span>)
										 <span id="basic_text" class="address">${requestScope.basic_adto.address} </span>
										 <span id="basic_text" class="detail_address">${requestScope.basic_adto.detail_address}</span>
									</div>
								</div>
							</div>
				
						</div>
					</div>





          
          
             
          
					<div class="other">
						<div class="other_list">
						
						 	
				   	
				          
				   <form name="delete_add" method="post" >
						<c:forEach var="adao" items="${requestScope.addressList}"> 
					
						   
			
							<%--for문 반복횟수는 태그라이브러리를 써서 하는데 var='리스트안에 들어있는 한개아이템' items='리스트이름' --%>
							<%-- <c:forEach var="" items="${requestScope.addressList}"> --%>
							<div class="my_item_is_active adressOption" id="div${adao.address_num}" style="">
							
								<div class="info_bind" >
						  
								<div class="address_info">
									<div class="name_box">
									<input type="hidden" id="address_num" name="address_num" value="${adao.address_num}"/>${adao.address_num}
									    <%-- <span type="" name="address_num" value="${adao.address_num}"></span> --%>
										<span id="basic_text" class="name">${adao.order_name}</span>
										
									</div>
									<p id="basic_text" class="phone">
										${adao.first_mobile}-${adao.second_mobile}-${adao.third_mobile}
									</p>
									<div  class="address_box">
										(<span id="basic_text"  class="zipcode">${adao.post_code}</span>)
										 <span id="basic_text" class="address">${adao.address} </span>
										 <span id="basic_text" class="detail_address">${adao.detail_address}</span>
									</div>
								</div>
							   </div>
																						
									
							</div>		
														 	   							
							
							</c:forEach>
							</form>
						 	
							<%--for문 --%>
							


				</div>             
              
              
      </div>





         

               <%----------------------------------------------------------- 페이지 바 시작 ---------------------------------------------%>
               
               <c:if test="${not empty requestScope.addressList}">
               
        <nav aria-label="...">
		    <ul class="my pagination pagination-md justify-content-center mt-5">
		    	<%-- 첫페이지로 이동버튼 --%>
		    	<c:if test="${requestScope.page > requestScope.display_page}">
		    	<li class="page-item">
			      <a class="page-link" p="1">
			      	<i class="fa-solid fa-angles-left"></i>
			      </a>
			    </li>
			    
			    
			    <%-- 전페이지로 이동버튼 --%>
			    <li class="page-item">
			      <a class="page-link" p="${requestScope.startPage-1}">
			      	<i class="fa-solid fa-angle-left"></i>
			      </a>
			    </li>
			    </c:if>
			    
			    <%-- 페이지번호 시작--%>
			    <c:forEach begin="${requestScope.startPage-1}" end="${requestScope.endPage-1}" varStatus="i">
                <c:if test="${requestScope.page == (requestScope.startPage+i.count-1)}">
                <li class="page-item active" aria-current="page">
			    	<a id = "firstPage" class="page-link" p= "${requestScope.startPage+i.count-1}" >${requestScope.startPage+i.count-1}</a>
			    </li>
                </c:if>
                
                <c:if test="${requestScope.page != (requestScope.startPage+i.count-1)}">
                <li class="page-item">
			    	<a class="page-link" p="${requestScope.startPage+i.count-1}">${requestScope.startPage+i.count-1}</a>
			    </li>
                </c:if>
                </c:forEach>
                <%-- 페이지번호 끝 --%>
                
                
                
			    <%-- 다음페이지로 이동버튼 --%>
			    <c:if test="${!(requestScope.last_display_page)}">
			    <li class="page-item">
			      <a class="page-link" p="${requestScope.startPage+requestScope.display_page}"><i class="fa-solid fa-angle-right"></i></a>
			    </li>
			    <%-- 맨 끝페이지로 이동버튼 --%>
			    <li class="page-item">
			      <a class="page-link" p="${requestScope.totalPage}"><i class="fas fa-solid fa-angles-right"></i></a>
			    </li>
			    </c:if>
		  	</ul>
		</nav>
		
		
		</c:if>	
		<%----------------------------------------------------------- 페이지 바 끝 ---------------------------------------------%>






  </div>	
</c:if>			
<%------------------------------------------------------------- 주소정보가 있을때 출력되는 부분 끝 -------------------------------------------------------------%>			
 
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
          </div>
        </div>
       </section>
       <section>
       
        <div class="section_unit">
          <div class="section_title">
            <h3 class="title_txt">배송 방법</h3>
          </div>
        </div>
          <div class="section_content">
            <div class="delivery_service">
              <div class="delivery_way">
                <div class="way_info">
                  <div class="way_status_thumb">
                    <img src="https://kream-phinf.pstatic.net/MjAyMTExMjlfMTQ4/MDAxNjM4MTc4MjI5NTk3.2phJLPtRvFqViNfhZu06HzNRiUBlT4cmZR4_Ukqsyesg.ikrfWOrL7WXCVO0Rqy5kMvOn3B2YpjLUj6RuJqosPX0g.PNG/a_8b54cbca40e945f4abf1ee24bdd031f7.png" alt="3,000원" class="way_img">
                  </div>
                  <div class="way_desc">
                    <p class="company">
                      <span class="badge_title">일반배송 </span>
                      <span class="title">무료</span>
                    </p>
                    <p class="sub_text">배송 ・ 1-3일 내 도착 예정</p>
                  </div>
                </div>
              </div>
            </div>
       	</div>  
      </section>
      
      
      


      <%-- 포인트 섹션 --%>
      <section>
        <div class="section_title">
          <h3 class="title_txt">포인트</h3>
        </div>
        <div class="section_content">
          <div class="section_input">
            <input placeholder="0" id="point" class="input_credit"></input>
            <button  id="pointAlluse" class="btn_use_credit disabled"> 모두 사용 </button>
          </div>
          <div class="info_point">
            <div>
              <span class="text_current">보유 포인트</span>
              <button class="btn_question">
                <i class="fa-regular fa-circle-question"></i>
              </button>
              <div class="value_current">
                <span class="point">${user.point}</span>
                <span class="unit">P</span>
              </div>
            </div>
          </div>
        </div>
      </section>

      <section>
      <div id="final_order_info">
        <div id="final_order_title">
           최종 주문 정보
        </div>
        <div id="mini_title">
           총 결제금액
        </div>
        <div id="total_price">
           <span id="amount">-</span>
        </div>

        <div id="pirce_list">
           <ul>
              <li>
                 <span class="product_price" >상품금액</span>
                 <span class = "payment" id="payment_price" value="${requestScope.fullPrice}" style="font-weight: 700;"><fmt:formatNumber value="${requestScope.fullPrice}" pattern="#,###" />원
</span>
              </li>
              <li>
                 <span id="span_point" class="span_title">포인트</span>
                 <span class = "payment" id = "span_point_amount">-</span>
              </li>
              <li>
                 <span id="span_ship_price" class="span_title">배송비</span>
                 <span class = "payment" >0</span>
              </li>
           </ul>
        </div>


        <ul id="check_ul">
           <li>
              <div class="check_content">
                 <label class="no_margin" for="checkbox_1">
                 	<p class="content_1">판매자의 판매거부, 배송지연, 미입고 등의 사유가 발생할 경우, 거래가 취소될 수 있습니다.</p>
               	 </label>
                 <label class="no_margin" for="checkbox_1"><p class="content_2">앱 알림 해제, 알림톡 차단, 전화번호 변경 후 미등록 시에는 거래 진행 상태 알림을 받을 수 없습니다.</p></label>
              </div>
              <input class = "aaaa" name="li_check" id="checkbox_1" type="checkbox" />
           </li>
           <li>
              <div class="check_content">
                 <label class="no_margin" for="checkbox_2"><p class="content_1">'바로 결제하기' 를 선택하시면 즉시 결제가 진행되며, 단순 변심이나 실수에 의한 취소가 불가능합니다.</p></label>
                 <label class="no_margin" for="checkbox_2"><p class="content_2">본 거래는 개인간 거래로 전자상거래법(제17조)에 따른 청약철회(환불, 교환) 규정이 적용되지 않습니다.</p></label>
              </div>
              <input class = "aaaa" name="li_check" id="checkbox_2" type="checkbox" />
           </li>
           <li>
              <div class="check_content">
                 <label class="no_margin" for="checkbox_3"><p class="boldtext">구매 조건을 모두 확인하였으며, 거래 진행에 동의합니다.</p></label>
              </div>
              <input class = "aaaa" name="li_check" id="checkbox_3" type="checkbox" />
           </li>
           <li>
              <span class="total_amount">총결제금액</span><span id="final_price" value = "${requestScope.fullPrice}"><fmt:formatNumber value="${requestScope.fullPrice}" pattern="#,###" />원
              </span>
           </li>
           <li>
              <a id="payment">결제하기</a>
           </li>
        </ul>

     </div>
     

    </section>

    </div>



  </div>

<form id= "goPurchase" name="goPurchase">
    <input type="hidden" id="productName" name="productName" value="${product.product_name}"  />
	<input type="hidden" id="fullPrice" name="fullPrice" value="${requestScope.fullPrice}"  />
	<input type="hidden" id="userid" name="userid" value="${user.userid}"  />
	<input type="hidden" id="selected_address_num" name="address_num" value=""  />
					 
</form>

<form id= "goUpdate" name="goUpdate">
	<input type="hidden" id="userid" name="userid" value="${user.userid}"  />
    <input type="hidden" id="productNum" name="productNum" value="${product.product_num}"  />
    <c:if test="${user.membership == 0}">
    	<input type="hidden" id="PointPlus" name="PointPlus" value="${requestScope.fullPrice*0.05}" />	
    </c:if>
    <c:if test="${user.membership == 1}">
    	<input type="hidden" id="PointPlus" name="PointPlus" value="${requestScope.fullPrice*0.1}" />	
    </c:if>
	<input type="hidden" id="PointMinus" name="PointMinus" value="0"/>
	<input type="hidden" id="selected_address_num" name="address_num" value=""  />
	
	<c:forEach var="size" items="${product.order_product_size}" varStatus="status">
	
		<input type="hidden" id="size" name="size${status.index}" value="${size}"/>
		<input type="hidden" id="cnt" name="cnt${status.index}" value="${product.order_product_cnt[status.index]}"/>	          				          	
		
   	</c:forEach>
   	<input type="hidden" id="discountPrice" name="discountPrice" value="${discountPrice}"/>
	
   	
	
	
	<input type="hidden" id="length" name="length" value="${length}"/>
	<input type="hidden" id=event_type name="event_type" value=""/>

	
	
						 
</form>


<%-- 주소 추가 Modal --%>
  <%-------------------------------------------------------------- 모달 시작 -----------------------------------------------------------%>
        


 
            <input type="hidden"  id = "back" name="back" value=" "/>
                           	
            <div class="modal modal_box layer lg fade " id="add_address" >
                 
               <div class="layer_container" >
                 	 
                 <button type="button" class="close" data-dismiss="modal" >&times;</button>
                  <div class="layer_header">	
                      <h2 class="title1">새 주소 추가</h2> 

            
                     
                  </div>
                  <div class="layer_content">
                     <div class="delivery_bind">
                        <form name="registerFrm" class="delivery_input">
                        
                          
                          
                           <div class="input_box has_error">
               
                           <input type="hidden"  id = "fromPurchase" name="fromPurchase" value="true"/>
                           
                           <input type="hidden"  id = "address_num_modal" name="address_num" value=""/>
                              <h4 id="name" class="input_title">이름</h4>
                              <div class="input_item">
                                 <input name="order_name" class="input_txt" id="recipient_name" type="text" placeholder="수령인의 이름" autocomplete="off" >
                              </div>
                              <span class="name_error" style="color:red">올바른 이름을 입력해주세요. (2 - 50자)</span>
                           </div>
                        
                           <div class="input_box">
                              <h4 id="mobile" class="input_title">휴대폰 번호</h4>
                              <div class="input_item">
                                 <input id="mobile" name="mobile" type="text" placeholder=" '010' 으로 시작하는 11자리 번호, - 제외 " autocomplete="off"
                                    class="input_txt">
                                    
                                    <span class="mobile_error" style="color:red">정확한 휴대폰 번호를 입력해주세요 (- 제외).</span>
                              </div>
                              
                           </div>
                           
                           <div class="input_box">
                              <h4 class="input_title">우편번호</h4>
                              <div class="input_item">
                              <a href="#"  id="zipcodeSearch" class="btn btn_zipcode outline small;" onclick="openDaumPOST();"> 우편번호 </a>
                              <input type="text" id="postcode" value="ㅇㅇ" name="post_code" size="6" maxlength="5" placeholder="우편 번호를 검색하세요" readonly/><br/>
                                 <%-- <input type="text" placeholder="우편 번호를 검색하세요"
                                    readonly="readonly" autocomplete="off" class="input_txt"> --%>
                                    
                                    
                                    
                              </div>
                           </div>
                           
                           <div class="input_box">
                              <h4 class="input_title">주소</h4>
                              <div class="input_item" >
                              <input id="address" name="address" value="ㅇㅇ" type="text" placeholder="우편 번호 검색 후,자동입력 됩니다." readonly>
                              
                              </div>
                           </div>
                           
                           
                           
                           <div class="input_box">
                              <h4 class="input_title">상세 주소</h4>
                              <div class="input_item">
                                 <input name="detail_address" id="detailAddress" value="상세주소밸류" type="text" placeholder="건물, 아파트, 동/호수 입력"    autocomplete="off" class="input_txt">
                              </div>
                           </div>
                           
                           
                           <div class="input_box">
                              <input type="checkbox" id="basic_address" name="basic_address"/>
                              <label for="basic_address" class="ml-2">기본배송지로 설정</label>
                           </div>
                           
                        </form>
                        
                     </div>
                     
                  </div>
                     <div class="layer_btn">
                        <a href="#" class="btn btn_delete outlinegrey medium" data-dismiss="modal">
                           취소 </a>
                           <a href="#" class="btn btn_save solid medium" id="add_save" onclick="goRegister()"> 저장하기 </a>
                     </div>
                     
                     
                  </div>
                  
                  
               </div>
              
            
         </div>     
            
            
            <%------------------------------------------------------------------ 모달 끝  --------------------------------------------------------------------%>



	<form id="page" name="page">
		<input type="hidden" id="length" name="length" value="${length}" /> 
		<input type="hidden"id="productNum" name="productNum" value="${product.product_num}" />
		
		<c:forEach var="size" items="${product.order_product_size}" varStatus="status">
			<input type="hidden"id="size${status.index}" name="size${status.index}" value="${size}" />
			<input type="hidden"id="cnt${status.index}" name="cnt${status.index}" value="${product.order_product_cnt[status.index]}" />
    	</c:forEach>
		
		<input type="hidden"id="p" name="p" value="" />
		
	</form>


