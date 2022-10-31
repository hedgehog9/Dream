<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<%
   String ctxPath = request.getContextPath();
%>
  <%--header 호출 --%>
  <jsp:include page="/WEB-INF/view/header.jsp" />
  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/mypage.css" />
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%= ctxPath%>/js/mypage.js" ></script>
  
  <div class="container my lg" style="display: flex;">
  
  
  		<%-------------------- 사이드바 시작 ----------------------%>
  		
        
        <%-- sidebar 호출 --%>
  		<jsp:include page="/WEB-INF/view/sidebar.jsp" />
        
        
        
        <%-------------------- 사이드바 끝 ----------------------%>

        <div id="right-content" style="width: 90%;" class="mt-4">
            <div id="user-membership" style="display: flex;" class="border rounded mb-5">
                <div id="user-detail" style="display:flex;" class="ml-3 mt-3">
                    <div id="user-image" class="mr-3">
                        <i class="fas fa-thin fa-circle-user fa-6x"></i> 
                    </div>
                    <div id="user-info">
                        <div id="user-name">
                            <p style="font-weight:bold; font-size: 14pt;" class="pt-1 mb-0">${requestScope.mdto.username}</p> <%-- 데이터베이스에서 값 가져옴  --%>
                        </div>
                        <div id="user-email" style="font-size:10pt;">
                            <p>${requestScope.mdto.userid}</p> <%-- 데이터베이스에서 값 가져옴  --%>
                        </div>  
                        <button type="button" class="btn btn-light outline-secondary btn-sm" style="font-size: 9pt;" onClick="location.href='/Dream/member/myInfo.dream'">프로필 수정</button>
                    </div>
                </div>
                <div id="user-membership-detail" style="margin-left: auto; display: flex; width: 200px;">
                    <div class="mr-4 align-self-center out">
                        <div class="font-weight-bold"><c:choose><c:when test="${requestScope.mdto.membership eq 0}">일반회원</c:when><c:otherwise>드림회원 </c:otherwise></c:choose></div> <%-- 데이터베이스에서 값 가져옴  --%>
                        <p style="font-size: 10pt; text-align: center;">회원등급</p> 
                    </div>
                    <div class="align-self-center out">
                        <div class="font-weight-bold text-center">${requestScope.mdto.point}p</div> <%-- 데이터베이스에서 값 가져옴  --%>
                        <p style="font-size: 10pt;">포인트</p> 
                    </div>
                </div>
            </div>
            <div id="my-home-title" style="display: flex;">
                <p class="font-weight-bold" style="font-size: 14pt;">구매내역</p>
                <a style="margin-left: auto;  text-decoration:none; color: gray; " href="<%= ctxPath %>/member/buylist.dream" >더보기 > </a>
            </div>
            <div id="recent_purchase">
                <div id="purchase_list_tab" class="row rounded bg-light">
                    <div class="col-3 border-right">
                        <div class="font-weight-bold text-center">전체</div>
                        <p style="font-size: 10pt; text-align: center;">${requestScope.map.shipping_ready + requestScope.map.shipping_now + requestScope.map.shipping_end}</p> <%-- 데이터베이스에서 값 가져옴  --%>
                    </div>
                    <div class="col-3">
                        <div class="font-weight-bold text-center fifth">배송 준비중</div>
                        <p style="font-size: 10pt; text-align: center;">
	                        <c:choose>
	                        		<c:when test="${empty requestScope.map.shipping_ready}">0</c:when>
	                        		<c:otherwise>${requestScope.map.shipping_ready}</c:otherwise>
	                        </c:choose>
                        </p> <%-- 데이터베이스에서 값 가져옴  --%>
                    </div>
                    <div class="col-3">
                        <div class="font-weight-bold text-center fifth">배송 진행중</div>
                        <p style="font-size: 10pt; text-align: center;">
						<c:choose>
	                        		<c:when test="${empty requestScope.map.shipping_now}">0</c:when>
	                        		<c:otherwise>${requestScope.map.shipping_now}</c:otherwise>
	                    </c:choose>
						</p> <%-- 데이터베이스에서 값 가져옴  --%>
                    </div>
                    <div class="col-3">
                        <div class="font-weight-bold text-center">종료</div>
                        <p style="font-size: 10pt; text-align: center;">
                        <c:choose>
	                        		<c:when test="${empty requestScope.map.shipping_end}">0</c:when>
	                        		<c:otherwise>${requestScope.map.shipping_end}</c:otherwise>
	                    </c:choose>
                        </p> <%-- 데이터베이스에서 값 가져옴  --%>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    
    
   <%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />   
   
<jsp:include page="/WEB-INF/view/myPageFooter.jsp" />
  
  s