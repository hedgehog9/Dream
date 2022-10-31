<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%
   String ctxPath = request.getContextPath();
%>

  <%--header 호출 --%>
  <jsp:include page="/WEB-INF/view/header.jsp" />
  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/point.css" />
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%= ctxPath%>/js/point.js" ></script>


  


<body>
  
<!-- 모달 시작 -->
  <!-- ? modal -->
<div id="ModalPoint" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
  <!-- #ModalPoint을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. -->
     <div class="modal-content">
        
        <!-- Modal header -->
        <div class="modal-header">
        <h5 class="modal-title">이용안내</h5>
        
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          
          <ul class="description_list">
            <li class="description">• 1,000P 이상부터 구매금액 제한 없이 사용하실 수 있습니다.</li>
            <li class="description">• 입찰 삭제, 거래 취소 시 사용한 포인트는 환불됩니다.</li>
            <li class="description">• 먼저 적립된 포인트부터 순서대로 사용되며, 사용하지 않으실 경우 유효기간이 지나면 자동 소멸됩니다. </li>
            <li class="description">• 유효기간이 지난 후 환불받은 포인트는 다시 사용하실 수 없습니다.</li>
          </ul>
        </div>
        
        <!-- Modal footer -->
        <button id="x" type="button" class="close" data-dismiss="modal">&times;</button>
        
        <div class="modal-footer">
          <button type="button" class="btn outlinegrey medium" data-dismiss="modal">확인</button>


        </div>
     </div>
  </div>
</div>

  <!-- 포인트 적립 modal -->
<div id="ModalPoint_insert" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
  <!-- #ModalPoint을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. -->
     <div class="modal-content">
        
        <!-- Modal header -->
        <div class="modal-header">
        <h5 class="modal-title">포인트 적립</h5>
        
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div class="register_point">
            <div class="input_box">
              <h4 class="input_title">포인트 코드</h4>
              <div class="input_item">
                <input type="h4" id="pointCode" placeholder="쿠폰 코드를 입력하세요." autocomplete="off" class="input_txt">
              </div>
            </div>
            <p class="description"> • 유효기간이 지난 쿠폰 코드는 등록이 불가합니다.<br data-v-6d9a7a00="" data-v-1f7c6d3f=""> • 쿠폰에 따라 발급 수량 및 계정당 사용 횟수가 제한될 수 있습니다. </p> 
          </div>
          <div class="layer_btn">
            <button data-dismiss="modal" type="button" class="btn outlinegrey medium">취소</button>
            <button id="pointRegisterButton" disabled="disabled" type="button" class="btn solid medium disabled"> 적립하기 </button>
          </div>
        </div>
        
        
     </div>
  </div>
</div>



<!-- 모달 끝 -->

<div class="container d-flex">
  
  <!-- 마이페이지 왼쪽 사이드바 -->
  <%-- 사이드바 호출 --%>
  <div id="leftside">
  	<jsp:include page="/WEB-INF/view/sidebar.jsp" />
  </div>

  <!-- content 시작 -->
  <div class="content_area">
    <div class="point">
      <div class="content_title">
        <div  class="title">
          <h3 >포인트</h3>
        </div>
        
        <button type="button" class="btn_help">
          <div class="i_box">
            <a href="#" data-toggle="modal" data-target="#ModalPoint">
              <i class="fa-regular fa-circle-question" ></i>
            </a>
            
          </div>
        </button>
      </div>
      <!--  dashboard => 반응형 780 이상일때 나오는 화면-->
      <div class = "dashboard">
        <div class="point_summary">
          <div class="point_info">
            <p class="title">사용 가능한 포인트</p>
            <p  class="point">
              <b >${totalPoint}P</b>
            </p>
          </div>
          <div class="divider">
            <div  class="divider_line">

            </div>
          </div>
         
        </div>

        <div class="register">
          <button type="button" class="btn solid medium" data-toggle="modal" data-target="#ModalPoint_insert"> + 포인트 적립하기 </button>
        </div>


      </div>


        <!--  header 780 밑으로가면 상단에 고정되는 상단바-->
        <div class="point_header">
          <div class="inner">
            <a href="#" class="btn_cancel">
              <i id="cancel" class="fa-solid fa-arrow-left"></i>
            </a>
            <a href="#" aria-label="닫기" class="btn_close" style="display: none;">
            </a>
            <h1 class="title">
              <span class="title_txt">포인트</span>
            </h1>
            
            <a href="#" class="btn_help" id="layeron">
              <i class="fa-regular fa-circle-question"></i>
            </a>
            <!-- 화면 작아질때 ? 누르면 나오는 layer 시작-->
            <div class="layer" >
              <div class="layer_container">
                <div class="layer_header" >
                  <h2 class="title">이용안내</h2>
                </div>
                <div class="layer_content">
                  <ul class="description_list">
                    <li class="description">• 1,000P 이상부터 구매금액 제한 없이 사용하실 수 있습니다.</li>
                    <li class="description">• 입찰 삭제, 거래 취소 시 사용한 포인트는 환불됩니다.</li>
                    <li class="description">• 먼저 적립된 포인트부터 순서대로 사용되며, 사용하지 않으실 경우 유효기간이 지나면 자동 소멸됩니다. </li>
                    <li class="description">• 유효기간이 지난 후 환불받은 포인트는 다시 사용하실 수 없습니다.</li>
                  </ul>
                  
                </div>
                
                <button type="button" class="btn_layer_close">x</button>
              </div>
            </div>
            <!-- 화면 작아질때 ? 누르면 나오는 layer 끝 -->
          </div>
        </div>

        <!--  dashboard_2 => 반응형 780 이하일때 나오는 화면-->
        <div class = "dashboard_2">

          <button class="btn" data-toggle="modal" data-target="#ModalPoint_insert"> + 포인트 적립하기 </button>

          <div class="point_info">
            <p class="available_point_title">사용 가능한 포인트</p>
            <p class="available_point"><b>${totalPoint}</b>P </p>
          
            </div>
        </div>

        <p class="description"> 포인트 유효기간은 적립일로부터 최대 1년까지이며, 유형에 따라 달라질 수 있습니다. </p>

        <table class="point_history_table">
          <thead >
            <tr >
              <th class="point_history_header">상세 내역</th>
              <th class="point_history_header">적립/사용</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td colspan="2">
              <c:set value="${pointList}" var="pointList" />
              
              <c:if test="${fn:length(pointList) == 0}">
                <div class="point_empty">
                  <p class="text">적립 또는 사용한 내역이 없습니다. </p>
                </div>
              </c:if>
              
              <c:forEach items="${pointList}" var="pointObj">
              
              	<c:if test="${ pointObj.status == '적립'}">
              		<div class="point_obj plus">
                    <div class="point_circle">
                      <!-- 나중에 포인트 사용내역을 불러와서 적립이면 plus class 추가 사용이면 minus 클래스 추가해서 화이팅 -->
                      <div>적립</div>
                    </div>
                    <div class="point_text">
                      <div class="point_date">
                        ${pointObj.event_date}
                      </div>
                      <div class="point_change_explain">
                        ${pointObj.event_type} 적립
                        
                      </div>
                      <div class="point_date">
                        .
                      </div>

                    </div>
                    <div class="point_amount">
                      <!-- plus 면 + 아니면 - 가 span 의 값이 되도록 -->
                      <span class="plus-minus">+</span>
                      ${pointObj.point_amount}
                    </div>
                  </div>
              	</c:if>
              	
              	
              
                <c:if test="${ pointObj.status != '적립'}">
              		<div class="point_obj minus">
                    <div class="point_circle">
                      <!--minus 면 사용 나오도록 -->
                      <div>사용</div>
                    </div>
                    <div class="point_text">
                      <div class="point_date">
                        ${pointObj.event_date}
                      </div>
                      <div class="point_change_explain">
                        <!-- 얘는 위의  point_circle 의 클래스가 plus 면 포인트적립 minus면 구매 시 사용-->
                        ${pointObj.event_type} 시 사용<br> 주문번호:
                        <!-- 나중에 포인트 내역 테이블 제작지 포인트 적립방법, 어떤 이벤트인지 기록해야될듯 -->
                        <!--  point_circle 의 클래스가 minus 이므로 주문번호가 들어가야함-->
                        9494994945
                      </div>
                      <div class="point_date">
                        .
                      </div>

                    </div>
                    <div class="point_amount">
                      <!-- plus 면 + 아니면 - 가 span 의 값이 되도록 -->
                      <span class="plus-minus">-</span>
                      ${pointObj.point_amount}
                    </div>
                  </div>
              	</c:if>
              
              
              
              
              
              </c:forEach>
       
              </td>
            </tr>
          </tbody>
        </table>

    </div>
  </div>
</div>

</body>

<%--footer 호출 --%>
 <jsp:include page="/WEB-INF/view/footer.jsp" />



