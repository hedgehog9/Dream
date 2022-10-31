//JS 파일에서 contextPath 알아내기

function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

$(document).ready(function(){
  // == 변수 선언 == //
  let keyword = "";
  $("input#keyword").focus();
  
  // == 이벤트 == //
  //검색창에서 엔터를 눌렀을 경우 !
  $("input#keyword").keydown(e=>{
    if(e.keyCode == 13) { // 검색어에서 엔터를 치면 검색하러 가도록 한다.
      $("button#btn_search").trigger("click");
    }
  });//end of $("input#keyword").keydown
  
  //검색버튼 클릭시 keywordGoShop 클래스로 이동하도록 한다.
  $("button#btn_search").click(()=>{
	keyword = $("input#keyword").val();
	location.href=getContextPath()+'/product/keywordGoShop.dream?keyword='+keyword;
  });//end of $("button#btn_search").click--
  
  
  //추천검색어 클릭시 이벤트처리
  $("div.recommend_keyword").click(e=>{
	 const target = $(e.currentTarget);
	 const recommend_keyword = target.children().text();
	 
	 $("input#keyword").val(recommend_keyword);	//검색창에 값을 넣고,
	 $("button#btn_search").trigger("click");	//검색버튼 클릭
  });
  
  //맞춤검색어 클릭시 이벤트처리
  $("div.personalized_keyword").click(e=>{
	 const target = $(e.currentTarget);
	 const recommend_keyword = target.children().text();
	 
	 $("input#keyword").val(recommend_keyword);	//검색창에 값을 넣고,
	 $("button#btn_search").trigger("click");	//검색버튼 클릭
  });
  
  //인기검색어 기준 날짜 새로고침 클릭시 이벤트처리하기
  $("span#reload").click(()=>{
	window.location.reload();
  });
  
  //인기검색어 클릭시 이벤트처리
  $("span.span_best_keyword").click(e=>{
	const target = $(e.currentTarget);
	const best_keyword = target.text();
	
	$("input#keyword").val(best_keyword);		//검색창에 값을 넣고,
	$("button#btn_search").trigger("click");	//검색버튼 클릭
  });
  
  //카테고리 사진 클릭시 이벤트처리
  $("div.category_item").click(e=>{
	const target = $(e.currentTarget);
	const category = target.children("p.category_title").text();
	
	$("input#keyword").val(category);			//검색창에 값을 넣고,
	$("button#btn_search").trigger("click");	//검색버튼 클릭
  });
  
  
  
  //추천검색어 삭제 버튼 누를시 이벤트처리
  $("div.keyword_delete_box").click((e)=>{
	const target = $(e.currentTarget);
	const recommend_keyword = target.prev().children().text();
	location.href = getContextPath()+"/admin/deleteKeyword.dream?keyword="+recommend_keyword;
  });
  
  //추천검색어 추가 버튼 클릭시 이벤트처리
  $("button#insert_keyword").click((e)=>{
    const frm = document.insertKeywordFrm;
    frm.action = getContextPath()+"/admin/insertKeyword.dream"; // 상대경로이므로 맨 뒤에만 바뀜
    frm.method = "POST"; 
    frm.submit();
  });
  
  $("div.recommend_keyword_add").click(function(){
	$("input#recommend_keyword").focus();
  });
  
  
  
  
  
  
  
  
  
  
  
    
});//end of $(document).ready(function(){})------


//=================================================================================//
//================================= Function Declaration ==========================//
//=================================================================================//

