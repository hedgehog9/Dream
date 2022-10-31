// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

$(document).ready(function(){
	//좋아요 버튼 클릭시 이벤트 잡기
	  $("div#btn_like").click(e=>{
		const target = $(e.currentTarget);	//이벤트버블링방지 currentTarget 사용
		const productNum = target.prev().attr('id');
		$.ajax({ 
			url:getContextPath()+"/product/likeCheck.dream", 
			type:"GET",
			data:{"productNum": productNum},
			dataType:"json",
			success:function(json){	//좋아요 테이블에 insert가 성공시
			  if(json.login && json.like_exist){	//좋아요가 존재한다면
			    $.ajax({ 
					url:getContextPath()+"/product/likeDelete.dream", 
					type:"GET",
					data:{"productNum": productNum},
					dataType:"json",
					success:function(json){	
						if(json.deleteLikeSuccess){ //좋아요 테이블에 delete가 성공시
							target.css("color","black");
							toastr["error"](productNum+"번 상품이 관심상품에서 <br>삭제되었습니다.");
						}
						else{ //좋아요 테이블에 delete가 실패시
						  alert("좋아요 버튼클릭 실패! 다시 시도해주세요");
						}
					},//end of success
					
					//success 대신 error가 발생하면 실행될 코드 
					error: function(request,error){
						alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
					}
				});//end of $.ajax({})---
			  }//end of if---
			  else if(!json.login){
				location.href=getContextPath()+'/login/login.dream';
			  }
			  
			  
			  else{	//좋아요가 존재하지 않는다면
			    $.ajax({ 
					url:getContextPath()+"/product/likeInsert.dream", 
					type:"GET",
					data:{"productNum": productNum},
					dataType:"json",
					success:function(json){	
					  if(json.insertLikeSuccess){	//좋아요 테이블에 insert가 성공시
						target.css("color","pink");
						toastr["success"](productNum+"번 상품이 관심상품에 <br>추가되었습니다.");
					  }
					  else{	//좋아요 테이블에 insert가 실패시
						alert("좋아요 버튼클릭 실패! 다시 시도해주세요");
					  }
					},//end of success
					//success 대신 error가 발생하면 실행될 코드 
					error: function(request,error){
						alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
					}
				});//end of $.ajax({})---
			  }//end of else--
			  
			  
			},//end of success
			//success 대신 error가 발생하면 실행될 코드 
			error: function(request,error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		  });//end of $.ajax({})---
	  });//end of $("div#btn_like").click
});//end of $(document).ready


//페이지번호를 파라미터로 받아와서, 조건에맞게 URL에 페이지번호를 넘겨주는 함수
  function goPage(page){
	location.href=getContextPath()+'/product/like.dream?p='+page;
  }