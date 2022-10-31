<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
	String ctxPath = request.getContextPath();
	//
%>
    
<jsp:include page="/WEB-INF/view/admin/ad_header.jsp" />

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/admin/memberManage.css" />

<script type="text/javascript">

	$(document).ready(function() {
		
		
         $("select#searchType").val("${requestScope.searchType}");
         $("input#searchWord").val("${requestScope.searchWord}"); 
	    
		
		if("${requestScope.searchType}" == "joindate" ) {
			$("#start_date").val("${requestScope.start_date}");
			$("#end_date").val("${requestScope.end_date}");
			$("#date_button").css("display","flex");
		}
		
		if("${requestScope.searchType}" == "secession" || "${requestScope.searchType}" == "rest_member" 
		  || "${requestScope.searchType}" == "membership" ) {
			$("input#searchWord").css({'placeholder':'찾으시는 회원ID 입력'});
		}
		
		
		$("#searchType").change(function() {
			const searchType = $("#searchType").val();
			
			if(searchType == 'joindate') {
				$("#end_date").val(new Date().toISOString().substring(0, 10));
				$("#date_button").css("display","flex");
			}
			else {
				$("#date_button").hide();
			}
		});
		
	});
	
	function goSearch() {
		
		const searchType = $("#searchType").val();
		
		if(searchType == "" && $("input#searchWord").val().length > 0) {
			toastr["warning"]("검색어를 입력하세요");
			return;
		}
		
		if(searchType == 'joindate') {
			if( $("#start_date").val() == '' || $("#end_date").val() == '' ) {
				toastr["warning"]("시작날짜와 마지막날짜는 둘다 입력해야 합니다.");
				return;
			}
			if($("#start_date").val() > new Date().toISOString().substring(0, 10)) {
				toastr["warning"]("시작날짜가 오늘날짜보다 작아야 합니다.");
				$("#start_date").val('');
				$("#start_date").focus();
				return;
			}
			if($("#start_date").val() > $("#end_date").val()) {
				toastr["warning"]("마지막 날짜가 시작날짜보다 빠를 수 없습니다.");
				return;
			}
		}
		
		const frm = document.memberFrm;
		frm.action = "memberManage.dream";
		frm.method = "GET";
		frm.submit();
	}

</script>



<%-- 회원관리페이지 코드 시작 --%>
<div class="container d-flex flex-column">
  <table class="table table-hover">
     <thead>
       <tr>
         <th colspan="7"><h4 style="font-weight:bold;">회원목록</h4></th>
       </tr>
       <tr class="bg-dark">
          <td class="text-center">아이디</td>
          <td class="text-center">회원명</td>
          <td class="text-center">핸드폰번호</td>
          <td class="text-center">가입일자</td>
          <td class="text-center">탈퇴유무</td>
          <td class="text-center">휴면유무</td>
          <td class="text-center">멤버쉽여부</td>
       </tr>
     </thead>
     <tbody>
     <c:if test="${not empty requestScope.memberList}">
	     <c:forEach var="mdto" items="${requestScope.memberList}" >
		     <tr onclick="location.href='<%= ctxPath%>/admin/memberDetail.dream?userid=${mdto.userid}'" style="cursor:pointer">
		         <td class="text-center">${mdto.userid}</td>
		         <td class="text-center">${mdto.username}</td>
		         <td class="text-center">${mdto.mobile}</td>
		         <td class="text-center">${mdto.joindate}</td>
		         <c:choose>
		         	<c:when test="${mdto.secession == 0}">
						<td class="text-center">X</td>
					</c:when>
		         	<c:otherwise>
		         		<td class="text-center">O</td>
		         	</c:otherwise>
		         </c:choose>
		         <c:choose>
		         	<c:when test="${mdto.rest_member == 0}">
						<td class="text-center">X</td>
					</c:when>
		         	<c:otherwise>
		         		<td class="text-center">O</td>
		         	</c:otherwise>
		         </c:choose>
		         <c:choose>
		         	<c:when test="${mdto.membership == 0}">
						<td class="text-center">X</td>
					</c:when>
		         	<c:otherwise>
		         		<td class="text-center">O</td>
		         	</c:otherwise>
		         </c:choose>
		      </tr>
	     </c:forEach>
	 </c:if>
	 <c:if test="${empty requestScope.memberList}">
	 	<tr> <td colspan="7" style="text-align: center; font-weight: bold">조건에 맞는 회원이 없습니다.</td> </tr>
	 </c:if>
     </tbody>
  </table>
  
  
  
<%------------------------ 페이지바 시작 ------------------------%>
  <nav aria-label="..." class="m-auto pt-2" id="section_page_bar">
	  <div style="display: flex; width: 80%">
		 <ul class="pagination" style="margin: auto">${requestScope.pageBar}</ul>
	  </div>
  </nav>
<%------------------------ 페이지바 끝 ------------------------%>


 <%-- 검색부분 시작 --%>
 <form name="memberFrm" class="mx-auto mt-3">
   <div id="search_area" class="d-flex pt-2">
     <select id="searchType" name="searchType">
       <option value="">선택하세요</option>
       <option value="userid">아이디</option>
       <option value="username">회원명</option>
       <option value="mobile">핸드폰번호</option>
       <option value="joindate">가입일자</option>
       <option value="secession">탈퇴유무</option>
       <option value="membership">멤버쉽여부</option>
       <option value="rest_member">휴면여부</option>
     </select>
     
     
    <div id="memberIdSearch" class="d-flex ml-3">
      <div id="input_id">
        <input type="text" placeholder="검색어" id="searchWord" name="searchWord" class="rounded">
      </div>
      <div id="search_btn">
        <button type="button" class="btn btn-white" id="btn_search" onclick="goSearch();">
        		<i class="fas fa-xl fa-thin fa-magnifying-glass" style="" ></i>
        </button>
      </div>
    </div>
   </div>
   
   <%-- 기간조회 시작 --%>
	<div id="date_button" class="mt-2">

		<div id="from_date" class="input_date" style="vertical-align: middle;">
			<input type="date" name="start_date" id="start_date" style="width: 120px; margin: 6px auto;" value ="" ></input>~
		</div>

		<div id="to_date" class="input_date">
			<input type="date" name="end_date" id="end_date" style="width: 120px; margin: 6px auto;" value="">
		</div>

	</div>
	<%-- 기간조회 끝 --%>
 </form>
  
  
  <%-- 검색부분 끝 --%>
</div>
<%-- 회원관리페이지 코드 끝 --%>


<%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />