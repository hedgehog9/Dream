<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript" src="<%= request.getContextPath()%>/jquery3.6.0/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script>
    
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"/>
<!-- toastr cdn 라이브러리 둘다 제이쿼리 밑에 있어야함 -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

<script type="text/javascript">

toastr.options = {
		  "closeButton": false,
		  "debug": false,
		  "newestOnTop": false,
		  "progressBar": true,
		  "positionClass": "toast-top-center",
		  "preventDuplicates": false,
		  "onclick": null,
		  "showDuration": "300",
		  "hideDuration": "1000",
		  "timeOut": "1500",
		  "extendedTimeOut": "1000",
		  "showEasing": "swing",
		  "hideEasing": "linear",
		  "showMethod": "fadeIn",
		  "hideMethod": "fadeOut",
		  "toastClass": 'toastr'
	}
	
	$(document).ready(function() {
		
		toastr["success"]("${requestScope.message}");
		setTimeout("location.href='${requestScope.loc}'",2000);
		   
		//setTimeout('opener.location.reload(true)',2000)
		
	});

   // alert("${requestScope.message}");  // 메시지 출력해주기 
   // location.href = "${requestScope.loc}"; // 페이지 이동
   
//   opener.location.reload(true); // 부모창 새로 고침
// opener.history.go(0);         // 부모창 새로 고침
   /*	
		location.href="javascript:history.go(-2);";  // 이전이전 페이지로 이동 
	 	location.href="javascript:history.go(-1);";  // 이전 페이지로 이동
	 	location.href="javascript:history.go(0);";   // 현재 페이지로 이동(==새로고침) 캐시에서 읽어옴.
	 	location.href="javascript:history.go(1);";   // 다음 페이지로 이동.
	 	
	 	location.href="javascript:history.back();";       // 이전 페이지로 이동 
	 	location.href="javascript:location.reload(true)"; // 현재 페이지로 이동(==새로고침) 서버에 가서 다시 읽어옴. 
	 	location.href="javascript:history.forward();";    // 다음 페이지로 이동.
	*/
 //  self.close(); // 팝업창 닫기
   
</script>    