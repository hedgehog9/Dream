<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
   String ctxPath = request.getContextPath();
   //
%>


<style type="text/css">
div.modal-footer>button {
   width: 70px;
   border: none;
   height: 40px;
   border-radius: 10px;
   background-color: black;
   color:white;
}

div.modal-content {
   border-radius: 10px;
   position: fixed;
   top: 50%;
   left: 50%;
   -webkit-transform: translate(-50%, -50%);
   -moz-transform: translate(-50%, -50%);
   -ms-transform: translate(-50%, -50%);
   -o-transform: translate(-50%, -50%);
   transform: translate(-50%, -50%);
   width: 500px;
    height: 500px;
}

td#icon {
  padding:0;
}


input[type="checkbox"]{

width: 20px; /*Desired width*/

height: 20px; /*Desired height*/

cursor: pointer;


}


:checked {
    accent-color: black;
}

input#contents {
  word-break:break-all;
}
</style>
    
<jsp:include page="/WEB-INF/view/admin/ad_header.jsp" />

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/message.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%= ctxPath%>/js/message.js" ></script>

<script type="text/javascript">

   $(document).ready(function() {
      
       $("#date_button").hide();
      
         $("select#searchType").val("${requestScope.searchType}");
         $("input#searchWord").val("${requestScope.searchWord}"); 
       
      
      if("${requestScope.searchType}" == "joindate" ) {
         $("#start_date").val("${requestScope.start_date}");
         $("#end_date").val("${requestScope.end_date}");
         $("#date_button").show();
      }
      
      if("${requestScope.searchType}" == "secession" || "${requestScope.searchType}" == "rest_member" 
        || "${requestScope.searchType}" == "membership" ) {
         $("input#searchWord").css({'placeholder':'찾으시는 회원ID 입력'});
      }
      
      
      $("#searchType").change(function() {
         const searchType = $("#searchType").val();
         
         if(searchType == 'joindate') {
            $("#end_date").val(new Date().toISOString().substring(0, 10));
            $("#date_button").show();
         }
         else {
            $("#date_button").hide();
         }
      });
      
      
      
      const check_length = $("input:checkbox[name='sub_check']").length;
      const Checkbox = $("input:checkbox[name='sub_check']");
      
      
      // 전체체크버튼 클릭
      $("input#checkAll").click(function (e) {
        
        const bool = $(e.target).prop("checked");
        Checkbox.prop("checked",bool);
        const checked_length = $("input:checkbox[name='sub_check']:checked").length;

        console.log(check_length);
        if(checked_length==check_length) {
         
          
        }
        else{
          
        }
      });
      
      
      
   // 그냥 버튼 체크시
      $("input#sub_check").click(function (e) {
         
        const checked_length = $("input:checkbox[name='sub_check']:checked").length;
               
        
        if(checked_length<check_length) {
          $("input#checkAll").prop("checked",false);
          
        }
        else if(checked_length==check_length) {
          $("input#checkAll").prop("checked",true);
          
        }
      });
   
   
   
      
      
   });// end of $(document).ready(function()------------------------
<<<<<<< HEAD
         
         
   function message_info(messageno,title,contents,read_check) {
           
      
      $("p#title").text("제목 : " + title);
      $("p#contents").text("내용 : " + contents);      
      
      
      goRead(read_check, messageno);
=======
		   
		   
   function message_info(messageno,title,contents,read_check) {
	   	  
	   
	   $("p#title").text("제목 : " + title);
	   $("p#contents").text("내용 : " + contents);	   
	   
	   
	   goRead(read_check, messageno);
>>>>>>> refs/remotes/origin/main
   }
   
   
   
   


</script>





<%-- 회원관리페이지 코드 시작 --%>
<div class="container  d-flex">

   <jsp:include page="/WEB-INF/view/sidebar.jsp" />
   <div style="width:100%">
  <table class="table table-hover">
  
  
  
     <thead>
       <tr>
         <th colspan="9"><h4 style="font-weight:bold; margin-top: 12px;">받은메세지 목록</h4></th>         
         <th colspan="5"><h4 style="font-weight:bold; margin-top: 12px;">보낸메세지 목록</h4></th>
       </tr>       
       <tr class="bg-dark">
          <td id="title_check"><input type="checkbox" id="checkAll"/></td>
          
           <td id="icon">
        <!-- 자리맞추기용 td 자리  -->
        </td>
          <td id="title"  colspan="7" style="margin-right: 40px;">제목</td>
         
          <td >받은날짜 </td>
         
          
       </tr>
     </thead>
     <tbody>
    
   <c:forEach var = "mvo"  items ="${printmessageList}"> 
    
        
        
        
        <tr>
        <td id="list_check" style="width:30px;" asd="${mvo.messageno}">
        <input type="checkbox" id="sub_check" name="sub_check" style="margin-right: 10px;"/>        
        </td>
        <td id="icon" style="width: 30px; padding-top:1.5%; ">
           <c:if test="${mvo.read_check==0}">
              <i class="fa-solid fa-envelope"></i>
           </c:if>
           
           <c:if test="${mvo.read_check==1}">
              <i class="fa-solid fa-envelope-open"></i>
           </c:if>
       
        </td>
        
        <td colspan="7" style=" font-weight: bold"  data-toggle="modal" data-target="#message_modal" data-dismiss="modal" onclick="message_info('${mvo.messageno}','${mvo.title}','${mvo.contents}');">
           ${mvo.title}         
          </td>
          <td data-toggle="modal" data-target="#message_modal" data-dismiss="modal" onclick="message_info('${mvo.messageno}','${mvo.title}','${mvo.contents}');">
             ${mvo.shipping}
             <input type="text" id="load_messageno" name="load_messageno" value="${mvo.messageno}"/> 
             <input type="text" id="load_fk_sender_userid" name="load_fk_sender_userid" value="${mvo.fk_sender_userid}"/>   
             
            
          </td>
       </tr>
     </c:forEach>
    
     </tbody>
  </table>
  
  <button onclick="deleteAll()" class= "btn btn-dark float-right" type= "button" style="width:10%;">삭제</button>
  <button onclick="" class= "btn btn-dark float-right mr-2" type= "button" style="width:10%;" data-toggle="modal" data-target="#message_write_modal" data-dismiss="modal">쓰기</button>
  
  
<%------------------------ 페이지바 시작 ------------------------%>
  <c:if test="${not empty requestScope.printmessageList}">
               
<<<<<<< HEAD
        <nav id="pgbar" aria-label="..." style="margin-top: 70px;">
=======
        <nav aria-label="..." style="margin-top: 70px;">
>>>>>>> refs/remotes/origin/main
          <ul class="my pagination pagination-md justify-content-center mt-5">
             <%-- 첫페이지로 이동버튼 --%>
             <c:if test="${requestScope.page > requestScope.display_page}">
             <li class="page-item">
               <a class="page-link" onclick = "goPage(1)">
                  <i class="fa-solid fa-angles-left"></i>
               </a>
             </li>
             
             
             <%-- 전페이지로 이동버튼 --%>
             <li class="page-item">
               <a class="page-link"  onclick = "goPage(${requestScope.startPage-1})">
                  <i class="fa-solid fa-angle-left"></i>
               </a>
             </li>
             </c:if>
             
             <%-- 페이지번호 시작--%>
             <c:forEach begin="${requestScope.startPage-1}" end="${requestScope.endPage-1}"  varStatus="i">
                <c:if test="${requestScope.page == (requestScope.startPage+i.count-1)}">
                <li class="page-item active" aria-current="page">
                <a id = "firstPage" class="page-link" onclick = "goPage(${requestScope.startPage+i.count-1})" >${requestScope.startPage+i.count-1}</a>
             </li>
                </c:if>
                
                <c:if test="${requestScope.page != (requestScope.startPage+i.count-1)}">
                <li class="page-item">
                <a class="page-link" onclick = "goPage(${requestScope.startPage+i.count-1})">${requestScope.startPage+i.count-1}</a>
             </li>
                </c:if>
                </c:forEach>
                <%-- 페이지번호 끝 --%>
                
                
                
             <%-- 다음페이지로 이동버튼 --%>
             <c:if test="${!(requestScope.last_display_page)}">
             <li class="page-item">
               <a class="page-link" onclick = "goPage(${requestScope.startPage+requestScope.display_page})"><i class="fa-solid fa-angle-right"></i></a>
             </li>
             <%-- 맨 끝페이지로 이동버튼 --%>
             <li class="page-item">
               <a class="page-link" onclick = "goPage(${requestScope.totalPage})"><i class="fas fa-solid fa-angles-right"></i></a>
             </li>
             </c:if>
           </ul>
      </nav>
      
      
      </c:if>   
<%------------------------ 페이지바 끝 ------------------------%>



</div>


</div>










<%-- 메세지 보기 모달 시작 --%>
  <div class="modal" id="message_modal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal header -->
        <div class="modal-header">
          <h4 class="modal-title">받은메세지</h4>          
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div id="message_title">
              <p id="title" name="title" type="text" autocomplete="off"   class="input_txt" style="border:none; padding-bottom: 10px;"> </p>
          </div>  
          <div id="message_body">                 
              <p id="contents" name="contents" type="text" autocomplete="off" class="input_txt" style="border:none; " ></p>           
         </div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer layer_btn" id="pay_button">
           <button type="button" id="reply"  > 답장 </button>
           <button type="button" id="bye_modal"  data-dismiss="modal"> 닫기 </button>           
         </div>
      </div>
      
    </div>
  </div>  
  
<%-- 메세지 보기 모달 끝 --%>  
  



  
  
<%-- 메세지 쓰기 모달 시작 --%>  
   <div class="modal" id="message_write_modal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal header -->
        <div class="modal-header">
          <h4 class="modal-title">메세지 작성</h4>          
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div id="message_title"> 제목:
              <input name="order_name" class="input_txt" id="recipient_name" type="text" placeholder="수령인의 이름" autocomplete="off" style="margin-left: 31px;">              
          </div>  
          <div >받는사람:
              <input name="order_name" class="input_txt" id="recipient_name" type="text" placeholder="수령인의 이름" autocomplete="off" >              
          </div> 
          <div id="message_body">                 
              <textarea name="order_name" class="input_txt" id="recipient_name" type="text" placeholder="수령인의 이름" autocomplete="off" style="width: 100%;
                height: 90%;"></textarea>      
         </div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer layer_btn" id="pay_button">
           <button type="button" id="reply"  > 답장 </button>
           <button type="button" id="bye_modal"  data-dismiss="modal"> 닫기 </button>           
         </div>
      </div>
      
    </div>
  </div>
  
 <%-- 메세지 보기 모달 끝 --%> 
  


<%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />