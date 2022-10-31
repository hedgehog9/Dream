// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

//URL관련 변수선언
let URL = sessionStorage.getItem("currentURL");

//category
const category_start_idx = URL.indexOf('=',URL.indexOf('category'))+1;
const category_last_idx = URL.indexOf('&',category_start_idx);
let category_val = "전체";
if(sessionStorage.getItem("category").trim() != ""){
	category_val = sessionStorage.getItem("category").trim();
}

//detail_category
const detail_category_start_idx = URL.indexOf('=',URL.indexOf('detail_category'))+1;
const detail_category_last_idx = URL.indexOf('&',detail_category_start_idx);
let detail_category_val = "전체";
if(sessionStorage.getItem("detail_category").trim() != ""){
	detail_category_val = sessionStorage.getItem("detail_category").trim();
}


//bestyn
const bestyn_start_idx = URL.indexOf('=',URL.indexOf('bestyn'))+1;
const bestyn_last_idx = URL.indexOf('&',bestyn_start_idx);
let bestyn_val = 'N';
if(sessionStorage.getItem("bestyn").trim() != ""){
	bestyn_val = sessionStorage.getItem("bestyn").trim();
}

//gender
const gender_start_idx = URL.indexOf('=',URL.indexOf('gender'))+1;
const gender_last_idx = URL.indexOf('&',gender_start_idx);
let gender_val = '없음';
if(sessionStorage.getItem("gender").trim() != ""){
	gender_val = sessionStorage.getItem("gender").trim();
}


//start_price
const start_price_start_idx = URL.indexOf('=',URL.indexOf('start_price'))+1;
const start_price_last_idx = URL.indexOf('&',start_price_start_idx);
let start_price_val = "";
if(sessionStorage.getItem("start_price").trim() != ""){
	start_price_val = sessionStorage.getItem("start_price").trim();
}


//end_price
const end_price_start_idx = URL.indexOf('=', URL.indexOf('end_price'))+1;
const end_price_last_idx = URL.indexOf('&', end_price_start_idx);
let end_price_val = "";
if(sessionStorage.getItem("end_price").trim() != ""){
	end_price_val = sessionStorage.getItem("end_price").trim();
}


//sort
const sort_start_idx = URL.indexOf('=',URL.indexOf('sort'))+1;
const sort_last_idx = URL.indexOf('&',sort_start_idx);
let sort_val = "전체";
if(sessionStorage.getItem("sort").trim() != ""){
	sort_val = sessionStorage.getItem("sort").trim();
}


//page
const page_start_idx = URL.indexOf('p=')+2;
const page_last_idx = URL.indexOf('&',page_start_idx);
let page_val = "1";
if(sessionStorage.getItem("page").trim() != ""){
	page_val = sessionStorage.getItem("page").trim();
}
//URL관련 변수선언 끝

	


$("document").ready(function(){
	
      // 변수선언
      const btn_toggle_filter = $("a.btn_toggle_filter");
      const plus_icon = $("i.plus_icon");
      const minus_icon = $("i.minus_icon");
      
      $("form.togglebox").hide();
      
      minus_icon.hide();
      // 사이드필터에서 토글버튼() 클릭시 토글부분을 보여주는 함수
      btn_toggle_filter.click(function(e){
        const target = $(e.target);
        target.parent().next().toggle();
        target.find("i.plus_icon").toggle();
        target.find("i.minus_icon").toggle();
      });
      // 사이드필터에서 토글버튼(플러스) 클릭시 토글부분을 보여주는 함수
      plus_icon.click(function(e){
        const target = $(e.target);
        target.parent().parent().next().show();
        target.hide();
        target.next().show();
      });//end of btn_toggle_filter.click(function(e){}--
      // 사이드필터에서 토글버튼(마이너스버튼) 클릭시 토글부분을 보여주는 함수
      minus_icon.click(function(e){
        
        const target = $(e.target);
        target.parent().parent().next().hide();
        target.hide();
        target.prev().show();
      });//end of btn_toggle_filter.click(function(e){}--
      
      
      
      
      // 필터조건을 주었다면 필터조건 준 부분 토글박스 보이게 만들기
      if(category_val != '전체'){
		$("form#category_toggle").show();
		$("i#category_icon_plus").hide();
		$("i#category_icon_minus").show();
      }
      if(bestyn_val != 'N'){
		$("form#bestyn_toggle").show();
		$("i#bestyn_icon_plus").hide();
		$("i#bestyn_icon_minus").show();
      }
      if(gender_val != '없음'){
		$("form#gender_toggle").show();
		$("i#gender_icon_plus").hide();
		$("i#gender_icon_minus").show();
      }
      if(start_price_val != "" || end_price_val != ""){
		$("form#price_toggle").show();
		$("i#price_icon_plus").hide();
		$("i#price_icon_minus").show();
      }
      
      
      
      // ===== sidefilter 부분 javascript 끝 ===== //
      
      
      
      
      
      //============================= >> 문서가 로딩되고 해야할 일 시작 << =============================
      //검색창에 키워드 값 입력해주기
      $("input#keyword").val(sessionStorage.getItem("keyword"));
      
      
      //URL에서 카테고리값에 맞는것 버튼 클릭한효과랑 필터부분 카테고리라디오 체크하기
      $(`button.btn_fillter:contains(${category_val})`).css("background-color","black");
      $(`button.btn_fillter:contains(${category_val})`).css("color","white");
      $(`label:contains(${category_val})`).prev().attr("checked",true);
      
      //bestyn이 y일 경우 문서가 로딩되면 체크하기
      if(bestyn_val == 'Y'){
	    $(`input:checkbox[name='bestyn']`).attr("checked",true);
      }
      else if(bestyn_val =='N'){
	    $(`input:checkbox[name='bestyn']`).attr("checked",false);
	  }
	  
	  //gender값에따라 체크하기 
      if(gender_val == '남'){
	    $(`input:radio[id='man']`).attr("checked",true);
      }
      else if(gender_val == '여'){
	    $(`input:radio[id='women']`).attr("checked",true);
	  }
	  else{
		$("input:radio[id='no_gender']").attr("checked",true);
	  }


      
      //카테고리 라디오버튼 값이 변경시 버튼에 클릭트리거
      $("input:radio[name='category']").change(e=>{
		const target = $(e.target);
		const category = target.next().text();
		
		$(`button:contains(${category})`).trigger("click");
		return;
	  });
	  
	  
	  //URL에 있는 start_price 와 end_price 값 넣어주기
	  
	  $("input#start_price").val(start_price_val);
	  $("input#end_price").val(end_price_val);
	  
	  switch (sort_val){
		case '인기순':
		   $("select#sort_option option:eq(1)").attr("selected", "selected");
		  break;
		case '업로드순':
		   $("select#sort_option option:eq(2)").attr("selected", "selected");
		  break;
		case '최저가순':
		   $("select#sort_option option:eq(3)").attr("selected", "selected");
		  break;
		default:
		   $("select#sort_option option:eq(0)").attr("selected", "selected");
		  break;
	  }
	  
	  
	  //카테고리를 무엇을 선택했는지에 따라서 디테일카테고리이미지 넣기
	  let html="";
	  switch (category_val) {
	    case '전체':
          $("div.details_category").css("display","none");
	      break;
	    case '침구류':
	      html =(`<a type=button class="detail_category">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리베개.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        베개
                      </p>
                    </div>
                  </a>
                  
                  <a type=button class="detail_category ml-3">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리베개커버.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        베개커버
                      </p>
                    </div>
                  </a>
                  
                  <a type=button class="detail_category ml-3">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리이불.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        이불
                      </p>
                    </div>
                  </a>
                  
                  <a type=button class="detail_category ml-3">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리침대.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        침대
                      </p>
                    </div>
                  </a>
                  
                  <a type=button class="detail_category ml-3">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리침대커버.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        침대커버
                      </p>
                    </div>
                  </a>
                  
                  <a type=button class="detail_category ml-3">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리매트리스.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        매트리스
                      </p>
                    </div>
                  </a>`);
          $("div.details_category").html(html);
	      break;
	    case '조명':
	      html =(`<a type=button class="detail_category">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리장스탠드.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        장스탠드
                      </p>
                    </div>
                  </a>
                  
                  <a type=button class="detail_category ml-3">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리무드등.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        무드등
                      </p>
                    </div>
                  </a>
                  
                  <a type=button class="detail_category ml-3">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리북라이트.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        북라이트
                      </p>
                    </div>
                  </a>
                  
                  <a type=button class="detail_category ml-3">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리LED양초.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        LED양초
                      </p>
                    </div>
                  </a>`);
          $("div.details_category").html(html);
	      break;
	    case '파자마':
	      html =(`<a type=button class="detail_category">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리동물잠옷.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        동물잠옷
                      </p>
                    </div>
                  </a>
                  <a type=button class="detail_category ml-3">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리수면잠옷.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        수면잠옷
                      </p>
                    </div>
                  </a>
                  <a type=button class="detail_category ml-3">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리커플파자마.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        커플파자마
                      </p>
                    </div>
                  </a>`);
          $("div.details_category").html(html);
	      break;
	    case '수면용품':
	      html =(`<a type=button class="detail_category">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리안대.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        안대
                      </p>
                    </div>
                  </a>
                  <a type=button class="detail_category ml-3">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리필로우미스트.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        필로우미스트
                      </p>
                    </div>
                  </a>
                  
                  <a type=button class="detail_category ml-3">
                    <div class="d-flex flex-column">
                      <div class="category_img">
                        <img src="${getContextPath()}/images/디테일카테고리/카테고리캔들.PNG" class="rounded-circle">
                      </div>
                      <p class="details_category_name text-center">
                        캔들
                      </p>
                    </div>
                  </a>`);
          $("div.details_category").html(html);
	      break;
	  }
	  
	  
	  
	  //============================= >> 문서가 로딩되고 해야할 일 끝 << =============================
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  //======================= >> 이벤트처리부분 시작 << =================================
	  //== 검색창부분 이벤트 시작 ==//
	  $("input#keyword").keydown(e=>{
	    if(e.keyCode == 13) { // 검색어에서 엔터를 치면 검색하러 가도록 한다.
	      $("button#btn_search").trigger("click");
	    }
	  });//end of $("input#keyword").keydown
	  
	  
	  //필터 클리어 버튼 클릭시 ajax로 필터 전부 지워주기
	  $("button#btn_clear_filter").click(()=>{
		location.href=getContextPath()+'/product/shop.dream';
	  });
	  
	  
	  
	  //검색버튼 클릭시 keywordGoShop 클래스로 이동하도록 한다.
	  $("button#btn_search").click(()=>{
		keyword = $("input#keyword").val();
		location.href=getContextPath()+'/product/keywordGoShop.dream?keyword='+keyword;
	  });//end of $("button#btn_search").click--
	  //== 검색창부분 이벤트 끝 ==//
	  
	  
	  
	  //필터버튼 클릭시 이벤트 잡기
	  $("button.btn_fillter").click(e=>{
		const target = $(e.target);
		const val = target.text();
	    const detail_category_length = detail_category_val.length;
	    const page_length = page_val.length;
	    if(URL.indexOf('detail_category=') != -1){
	      let first_URL = URL.substring(0,URL.indexOf('detail_category=')-1);
	      let last_URL = URL.substring(detail_category_start_idx + detail_category_length);
	      URL = first_URL + last_URL;
	    }
	    if(URL.indexOf('?p=') != -1){
	      let first_URL = URL.substring(0,URL.indexOf('p='));
	      let last_URL = URL.substring(page_start_idx + page_length);
	      URL = first_URL + last_URL;
	    }
	    else if (URL.indexOf('&p=') != -1){
		  let first_URL = URL.substring(0,URL.indexOf('p=')-1);
	      let last_URL = URL.substring(page_start_idx + page_length);
	      URL = first_URL + last_URL;
	    }
		btn_filterClick(val);
		return;
	  });
	  
	  
	  
	  
	  //bestyn 라디오 버튼 값이 변경시 이벤트 잡기
      $("input:checkbox[name='bestyn']").change(()=>{
	    const page_length = page_val.length;
	    if(URL.indexOf('?p=') != -1){
	      let first_URL = URL.substring(0,URL.indexOf('p='));
	      let last_URL = URL.substring(page_start_idx + page_length);
	      URL = first_URL + last_URL;
	    }
	    else if (URL.indexOf('&p=') != -1){
		  let first_URL = URL.substring(0,URL.indexOf('p=')-1);
	      let last_URL = URL.substring(page_start_idx + page_length);
	      URL = first_URL + last_URL;
	    }
		if($("input:checkbox[name='bestyn']").is(":checked")){//bestyn을 체크했을 경우
		  bestynCheck();
		  return;
		}
		else{	//bestyn을 체크해제했을 경우
		  bestynClear();
		  return;
		}
	  });
	  
	  
	  
	  
	  
	  //gender 라디오 버튼 값이 변경시 이벤트 잡기
      $("input:radio[name='gender']").change(e=>{
	    const page_length = page_val.length;
	    if(URL.indexOf('?p=') != -1){
	      let first_URL = URL.substring(0,URL.indexOf('p='));
	      let last_URL = URL.substring(page_start_idx + page_length);
	      URL = first_URL + last_URL;
	    }
	    else if (URL.indexOf('&p=') != -1){
		  let first_URL = URL.substring(0,URL.indexOf('p=')-1);
	      let last_URL = URL.substring(page_start_idx + page_length);
	      URL = first_URL + last_URL;
	    }
		const target = $(e.target);
		const val = target.next().text();
		genderChange(val);
		return;
	  });
	  
	  
	  
	  //가격대 검색창 입력시 숫자만 입력 가능하게 만들기
	  $("input#start_price").keyup(function(){ $(this).val($(this).val().replace(/[^0-9]/gi,"") );  }); //숫자만
	  $("input#end_price").keyup(function(){ $(this).val($(this).val().replace(/[^0-9]/gi,"") );  }); //숫자만
	  //가격대로 검색 버튼 클릭시 이벤트
	  $("button#btn_price_search").click(()=>{
		
		const input_start_price = $("input#start_price");
		const input_end_price = $("input#end_price");
		let start_price = input_start_price.val();
		let end_price = input_end_price.val();
		if(parseInt(start_price) > parseInt(end_price)){	//시작가격이 끝 가격보다 높다면
		  alert("최고가격은 최저가격보다 높아야 합니다!");
		  input_end_price.val("");
		  input_end_price.focus();
		  return;
		}
		else if(start_price != "" && end_price == "") {//최고가격을 입력하지 않은경우
		  alert("최고가격을 입력해주세요!");
		  input_end_price.focus();
		  return;
		}
		else{	//정상적으로 입력했을 경우
		  const page_length = page_val.length;
		  if(URL.indexOf('?p=') != -1){
	        let first_URL = URL.substring(0,URL.indexOf('p='));
	        let last_URL = URL.substring(page_start_idx + page_length);
	        URL = first_URL + last_URL;
	      }
	      else if (URL.indexOf('&p=') != -1){
		    let first_URL = URL.substring(0,URL.indexOf('p=')-1);
	        let last_URL = URL.substring(page_start_idx + page_length);
	        URL = first_URL + last_URL;
	      }
		  search_price(start_price,end_price);
		  return;
		}
	  });
	  
	  
	  
	  
	  //가격대 검색부분 지우기버튼클릭시 입력한 가격부분 지워주기 
	  $("button#btn_price_clear").click(()=>{
		$("input#start_price").val("");
		$("input#end_price").val("");
	  });
	  
	  
	  
	  
	  
	  // detail_category 클릭시 detail_category URL에 넣어서 전송하기
	  $("a.detail_category").click(e=>{
        const target = $(e.currentTarget);	//이벤트버블링방지 currentTarget 사용
        const detail_category = target.children().children("p.details_category_name").text().trim();
        detail_categoryClick(detail_category);
	  });
	  
	  
	  
	  
	  // 정렬옵션 선택시 URL에 정렬옵션 넣어서 전송하기
	  $("select#sort_option").change(e=>{
		const target = $(e.target);
		const sort = target.val();
		const page_length = page_val.length;
		if(URL.indexOf('?p=') != -1){
	      let first_URL = URL.substring(0,URL.indexOf('p='));
	      let last_URL = URL.substring(page_start_idx + page_length);
	      URL = first_URL + last_URL;
	    }
	    else if (URL.indexOf('&p=') != -1){
		  let first_URL = URL.substring(0,URL.indexOf('p=')-1);
	      let last_URL = URL.substring(page_start_idx + page_length);
	      URL = first_URL + last_URL;
	    }
		sortChange(sort);
	  });
	  
	  
	  
	  
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
							const like_cnt = target.children("div.like_cnt").text();
							target.children("div.like_cnt").text(`${parseInt(like_cnt)-1}`);
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
						const like_cnt = target.children("div.like_cnt").text();
						target.children("div.like_cnt").text(`${parseInt(like_cnt)+1}`);
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
	  
	  
	  
	  //======================= >> 이벤트처리부분 끝 << =================================
  }); // end of $("document").ready(function(){}----
  
  
  
  
  
  
  
  
  
  //=================================================================================//
  //================================= Function Declaration ==========================//
  //=================================================================================//
  
  
  
  
  //category필터 버튼 클릭시 URL에 category=값 넣기 함수
  function btn_filterClick(val){
	const category_val_length = category_val.length;
	 if(URL.indexOf('category=')!=-1 &&
	  !(URL.indexOf('detail_category=')== -1 &&
	    URL.indexOf('bestyn=') == -1 &&
	    URL.indexOf('gender=') == -1 &&
	 	URL.indexOf('start_price=') == -1 &&
	 	URL.indexOf('end_price=') == -1 &&
	 	URL.indexOf('p=') == -1 &&
	 	URL.indexOf('sort=') == -1) )
	 {	//URL에 category파라미터가 있고,다른파라미터가 있다면
	   let start_URL = URL.substring(0,category_start_idx)+val;
	   let last_URL = URL.substring(category_start_idx+category_val_length);
	   location.href=getContextPath()+start_URL + last_URL;
	   return;
	 }
	 
	 
	 else if (URL.indexOf('category=')==-1 &&
		      URL.indexOf('detail_category=')== -1 &&
	 		  URL.indexOf('bestyn=') == -1 &&
	 		  URL.indexOf('gender=') == -1 &&
	 		  URL.indexOf('start_price=') == -1 &&
	 		  URL.indexOf('end_price=') == -1 &&
	 		  URL.indexOf('p=') == -1 &&
	 		  URL.indexOf('sort=') == -1
	          )
	 {	//URL에 category 파라미터가 없고,다른 파라미터도 없다면
	    location.href=getContextPath()+'/product/shop.dream?category='+val;
	    return;
	 }
	 
	 
	 else if(URL.indexOf('category=')!=-1 &&
	         URL.indexOf('detail_category=')== -1 &&
	 		 URL.indexOf('bestyn=') == -1 &&
	 		 URL.indexOf('gender=') == -1 &&
	 		 URL.indexOf('start_price=') == -1 &&
	 		 URL.indexOf('end_price=') == -1 &&
	 		 URL.indexOf('p=') == -1 &&
	 		 URL.indexOf('sort=') == -1)
	 {  //URL에 category 파라미터는 있고, 다른파라미터는 없다면
	   location.href=getContextPath()+URL.substring(0,category_start_idx)+val;
	   return;
	 } 
		
		
		
	 else {	//URL에 category파라미터는 없고,다른파라미터는 있다면,
		URL += '&category='+val;
		location.href=getContextPath()+URL;
		return;
	 }
	  
  }//end of function btn_filterClick()
  
  
  
  
  
  
  
  
  //bestyn체크박스 체크시 URL에 bestyn=y입력하기 함수
  function bestynCheck(){
	
	if( URL.indexOf('bestyn=')!=-1 &&
	  !(URL.indexOf('detail_category=')== -1 &&
	    URL.indexOf('category=') == -1 &&
	    URL.indexOf('gender=') == -1 &&
	 	URL.indexOf('start_price=') == -1 &&
	 	URL.indexOf('end_price=') == -1 &&
	 	URL.indexOf('p=') == -1 &&
	 	URL.indexOf('sort=') == -1) )
	 {	//URL에 category파라미터가 있고,다른파라미터가 있다면
	   let start_URL = URL.substring(0,bestyn_start_idx)+'Y';
	   let last_URL = URL.substring(bestyn_start_idx+1);
	   location.href=getContextPath()+start_URL + last_URL;
	   return;
	 }
	 
	 
	 else if (URL.indexOf('bestyn=')==-1 &&
		      URL.indexOf('detail_category=')== -1 &&
	 		  URL.indexOf('category=') == -1 &&
	 		  URL.indexOf('gender=') == -1 &&
	 		  URL.indexOf('start_price=') == -1 &&
	 		  URL.indexOf('end_price=') == -1 &&
	 		  URL.indexOf('p=') == -1 &&
	 		  URL.indexOf('sort=') == -1
	          )
	 {	//URL에 category 파라미터가 없고,다른 파라미터도 없다면
	    location.href=getContextPath()+'/product/shop.dream?bestyn='+'Y';
	    return;
	 }
	 
	 
	 else if(URL.indexOf('bestyn=')!=-1 &&
	         URL.indexOf('detail_category=')== -1 &&
	 		 URL.indexOf('category=') == -1 &&
	 		 URL.indexOf('gender=') == -1 &&
	 		 URL.indexOf('start_price=') == -1 &&
	 		 URL.indexOf('end_price=') == -1 &&
	 		 URL.indexOf('p=') == -1 &&
	 		 URL.indexOf('sort=') == -1)
	 {  //URL에 category 파라미터는 있고, 다른파라미터는 없다면
	   location.href=getContextPath()+URL.substring(0,bestyn_start_idx)+'Y';
	   return;
	 } 
		
		
		
	 else {	//URL에 category파라미터는 없고,다른파라미터는 있다면,
		URL += '&bestyn=Y';
		location.href=getContextPath()+URL;
		return;
	 }
  }//end of function bestynCheck()
  
  
  
  
  //bestyn체크박스 해제시 URL에 bestyn=n입력하기 함수
  function bestynClear(){
	
	if( URL.indexOf('bestyn=')!=-1 &&
	  !(URL.indexOf('detail_category=')== -1 &&
	    URL.indexOf('category=') == -1 &&
	    URL.indexOf('gender=') == -1 &&
	 	URL.indexOf('start_price=') == -1 &&
	 	URL.indexOf('end_price=') == -1 &&
	 	URL.indexOf('p=') == -1 &&
	 	URL.indexOf('sort=') == -1) )
	 {	//URL에 category파라미터가 있고,다른파라미터가 있다면
	   let start_URL = URL.substring(0,bestyn_start_idx)+'N';
	   let last_URL = URL.substring(bestyn_start_idx+1);
	   location.href=getContextPath()+start_URL + last_URL;
	   return;
	 }
	 
	 
	 else if (URL.indexOf('bestyn=')==-1 &&
		      URL.indexOf('detail_category=')== -1 &&
	 		  URL.indexOf('category=') == -1 &&
	 		  URL.indexOf('gender=') == -1 &&
	 		  URL.indexOf('start_price=') == -1 &&
	 		  URL.indexOf('end_price=') == -1 &&
	 		  URL.indexOf('p=') == -1 &&
	 		  URL.indexOf('sort=') == -1
	          )
	 {	//URL에 category 파라미터가 없고,다른 파라미터도 없다면
	    location.href=getContextPath()+'/product/shop.dream?bestyn='+'N';
	    return;
	 }
	 
	 
	 else if(URL.indexOf('bestyn=')!=-1 &&
	         URL.indexOf('detail_category=')== -1 &&
	 		 URL.indexOf('category=') == -1 &&
	 		 URL.indexOf('gender=') == -1 &&
	 		 URL.indexOf('start_price=') == -1 &&
	 		 URL.indexOf('end_price=') == -1 &&
	 		 URL.indexOf('p=') == -1 &&
	 		 URL.indexOf('sort=') == -1)
	 {  //URL에 category 파라미터는 있고, 다른파라미터는 없다면
	   location.href=getContextPath()+URL.substring(0,bestyn_start_idx)+'N';
	   return;
	 } 
		
		
		
	 else {	//URL에 category파라미터는 없고,다른파라미터는 있다면,
		URL += '&bestyn=N';
		location.href=getContextPath()+URL;
		return;
	 }
  }//end of function bestynCheck()
  
  
  
  
  
  //성별부분 라디오버튼 값이 바뀔시 호출될 함수
  function genderChange(val){
	const gender_val_length = gender_val.length;
	 if(URL.indexOf('gender=')!=-1 &&
	  !(URL.indexOf('detail_category=')== -1 &&
	    URL.indexOf('bestyn=') == -1 &&
	    URL.indexOf('category=') == -1 &&
	 	URL.indexOf('start_price=') == -1 &&
	 	URL.indexOf('end_price=') == -1 &&
	 	URL.indexOf('p=') == -1 &&
	 	URL.indexOf('sort=') == -1) )
	 {	//URL에 category파라미터가 있고,다른파라미터가 있다면
	   let start_URL = URL.substring(0,gender_start_idx)+val;
	   let last_URL = URL.substring(gender_start_idx+gender_val_length);
	   location.href=getContextPath()+start_URL + last_URL;
	   return;
	 }
	 
	 
	 else if (URL.indexOf('gender=')==-1 &&
		      URL.indexOf('detail_category=')== -1 &&
	 		  URL.indexOf('bestyn=') == -1 &&
	 		  URL.indexOf('category=') == -1 &&
	 		  URL.indexOf('start_price=') == -1 &&
	 		  URL.indexOf('end_price=') == -1 &&
	 		  URL.indexOf('p=') == -1 &&
	 		  URL.indexOf('sort=') == -1
	          )
	 {	//URL에 category 파라미터가 없고,다른 파라미터도 없다면
	    location.href=getContextPath()+'/product/shop.dream?gender='+val;
	    return;
	 }
	 
	 
	 else if(URL.indexOf('gender=')!=-1 &&
	         URL.indexOf('detail_category=')== -1 &&
	 		 URL.indexOf('bestyn=') == -1 &&
	 		 URL.indexOf('category=') == -1 &&
	 		 URL.indexOf('start_price=') == -1 &&
	 		 URL.indexOf('end_price=') == -1 &&
	 		 URL.indexOf('p=') == -1 &&
	 		 URL.indexOf('sort=') == -1)
	 {  //URL에 category 파라미터는 있고, 다른파라미터는 없다면
	   location.href=getContextPath()+URL.substring(0,gender_start_idx)+val;
	   return;
	 } 
		
		
	 else {	//URL에 category파라미터는 없고,다른파라미터는 있다면,
		URL += '&gender='+val
		location.href=getContextPath()+URL;
		return;
	 }
	 
  }//end of function genderChange(val){}-----
  
  
  //가격대 검색버튼을 눌렀을 때, 시작가격과 끝 가격을 URL에 넣어줄 함수
  function search_price(start_price,end_price){
	const start_price_val_length = start_price_val.length;
	const end_price_val_length = end_price_val.length;
	
	
	// 1.start_price 먼저 URL에 추가
	if(URL.indexOf('start_price=')!=-1 &&
	  !(URL.indexOf('detail_category=')== -1 &&
	    URL.indexOf('bestyn=') == -1 &&
	    URL.indexOf('category=') == -1 &&
	 	URL.indexOf('gender=') == -1 &&
	 	URL.indexOf('end_price=') == -1 &&
	 	URL.indexOf('p=') == -1 &&
	 	URL.indexOf('sort=') == -1) )
	 {	//URL에 start_price파라미터가 있고,다른파라미터가 있다면
	   let start_URL = URL.substring(0,start_price_start_idx)+start_price+'&end_price='+end_price;
	   let last_URL = URL.substring(start_price_start_idx+start_price_val_length+end_price_val_length+11);
	   location.href=getContextPath()+start_URL + last_URL;
	   return;
	 }
	 
	 
	 else if (URL.indexOf('start_price=')==-1 &&
		      URL.indexOf('detail_category=')== -1 &&
	 		  URL.indexOf('bestyn=') == -1 &&
	 		  URL.indexOf('category=') == -1 &&
	 		  URL.indexOf('gender=') == -1 &&
	 		  URL.indexOf('end_price=') == -1 &&
	 		  URL.indexOf('p=') == -1 &&
	 		  URL.indexOf('sort=') == -1
	          )
	 {	//URL에 start_price 파라미터가 없고,다른 파라미터도 없다면
	    location.href=getContextPath()+'/product/shop.dream?start_price='+start_price+'&end_price='+end_price;
	    return;
	 }
	 
	 
	 else if(URL.indexOf('start_price=')!=-1 &&
	         URL.indexOf('detail_category=')== -1 &&
	 		 URL.indexOf('bestyn=') == -1 &&
	 		 URL.indexOf('category=') == -1 &&
	 		 URL.indexOf('gender=') == -1 &&
	 		 URL.indexOf('end_price=') == -1 &&
	 		 URL.indexOf('p=') == -1 &&
	 		 URL.indexOf('sort=') == -1)
	 {  //URL에 start_price 파라미터는 있고, 다른파라미터는 없다면
	   location.href=getContextPath()+URL.substring(0,start_price_start_idx)+start_price+'&end_price='+end_price;
	   return;
	 } 
		
	 else {	//URL에 start_price파라미터는 없고,다른파라미터는 있다면,
		URL += '&start_price='+start_price+'&end_price='+end_price;
		location.href=getContextPath()+URL;
		return;
	 }
	
  }//end of function search_price(start_price,end_price){}----
  
  
  
  
  
  //detail_category 클릭시 선택한값을 파라미터로 받아 URL에 넣고 컨트롤러에 전송해주는 메소드
  function detail_categoryClick(detail_category){
	const detail_category_val_length = detail_category_val.length;
	 if(URL.indexOf('detail_category=')!=-1 &&
	  !(URL.indexOf('category=')== -1 &&
	    URL.indexOf('bestyn=') == -1 &&
	    URL.indexOf('gender=') == -1 &&
	 	URL.indexOf('start_price=') == -1 &&
	 	URL.indexOf('end_price=') == -1 &&
	 	URL.indexOf('p=') == -1 &&
	 	URL.indexOf('sort=') == -1) )
	 {	//URL에 category파라미터가 있고,다른파라미터가 있다면
	   let start_URL = URL.substring(0,detail_category_start_idx)+detail_category;
	   let last_URL = URL.substring(detail_category_start_idx+detail_category_val_length);
	   location.href=getContextPath()+start_URL + last_URL;
	   return;
	 }
	 
	 
	 else if (URL.indexOf('detail_category=')==-1 &&
		      URL.indexOf('category=')== -1 &&
	 		  URL.indexOf('bestyn=') == -1 &&
	 		  URL.indexOf('gender=') == -1 &&
	 		  URL.indexOf('start_price=') == -1 &&
	 		  URL.indexOf('end_price=') == -1 &&
	 		  URL.indexOf('p=') == -1 &&
	 		  URL.indexOf('sort=') == -1
	          )
	 {	//URL에 category 파라미터가 없고,다른 파라미터도 없다면
	    location.href=getContextPath()+'/product/shop.dream?detail_category='+detail_category;
	    return;
	 }
	 
	 
	 else if(URL.indexOf('detail_category=')!=-1 &&
	         URL.indexOf('category=')== -1 &&
	 		 URL.indexOf('bestyn=') == -1 &&
	 		 URL.indexOf('gender=') == -1 &&
	 		 URL.indexOf('start_price=') == -1 &&
	 		 URL.indexOf('end_price=') == -1 &&
	 		 URL.indexOf('p=') == -1 &&
	 		 URL.indexOf('sort=') == -1)
	 {  //URL에 category 파라미터는 있고, 다른파라미터는 없다면
	   location.href=getContextPath()+URL.substring(0,detail_category_start_idx)+detail_category;
	   return;
	 } 
		
		
		
	 else {	//URL에 category파라미터는 없고,다른파라미터는 있다면,
		URL += '&detail_category='+detail_category;
		location.href=getContextPath()+URL;
		return;
	 }
	
	
  }//end of function detail_categoryClick(detail_category){}--
  
  
  
  
  //정렬방식 값을 바꿀 경우 정렬방식 값을 URL에 넣고 전송하기
  function sortChange(sort){
	const sort_val_length = sort_val.length;
	 if(URL.indexOf('sort=')!=-1 &&
	  !(URL.indexOf('category=')== -1 &&
	    URL.indexOf('bestyn=') == -1 &&
	    URL.indexOf('gender=') == -1 &&
	 	URL.indexOf('start_price=') == -1 &&
	 	URL.indexOf('end_price=') == -1 &&
	 	URL.indexOf('p=') == -1 &&
	 	URL.indexOf('detail_category=') == -1) )
	 {	//URL에 category파라미터가 있고,다른파라미터가 있다면
	   let start_URL = URL.substring(0,sort_start_idx)+sort;
	   let last_URL = URL.substring(sort_start_idx+sort_val_length);
	   location.href=getContextPath()+start_URL + last_URL;
	   return;
	 }
	 
	 
	 else if (URL.indexOf('sort=')==-1 &&
		      URL.indexOf('category=')== -1 &&
	 		  URL.indexOf('bestyn=') == -1 &&
	 		  URL.indexOf('gender=') == -1 &&
	 		  URL.indexOf('start_price=') == -1 &&
	 		  URL.indexOf('end_price=') == -1 &&
	 		  URL.indexOf('p=') == -1 &&
	 		  URL.indexOf('detail_category=') == -1
	          )
	 {	//URL에 category 파라미터가 없고,다른 파라미터도 없다면
	    location.href=getContextPath()+'/product/shop.dream?sort='+sort;
	    return;
	 }
	 
	 
	 else if(URL.indexOf('sort=')!=-1 &&
	         URL.indexOf('category=')== -1 &&
	 		 URL.indexOf('bestyn=') == -1 &&
	 		 URL.indexOf('gender=') == -1 &&
	 		 URL.indexOf('start_price=') == -1 &&
	 		 URL.indexOf('end_price=') == -1 &&
	 		 URL.indexOf('p=') == -1 &&
	 		 URL.indexOf('detail_category=') == -1)
	 {  //URL에 category 파라미터는 있고, 다른파라미터는 없다면
	   location.href=getContextPath()+URL.substring(0,sort_start_idx)+sort;
	   return;
	 } 
		
		
		
	 else {	//URL에 category파라미터는 없고,다른파라미터는 있다면,
		URL += '&sort='+sort;
		location.href=getContextPath()+URL;
		return;
	 }
  }
  
  
  
  
  
  //페이지번호를 파라미터로 받아와서, 조건에맞게 URL에 페이지번호를 넘겨주는 함수
  function goPage(page){
	const page_val_length = page_val.length;
	 if(URL.indexOf('p=')!=-1 &&
	  !(URL.indexOf('category=')== -1 &&
	    URL.indexOf('bestyn=') == -1 &&
	    URL.indexOf('gender=') == -1 &&
	 	URL.indexOf('start_price=') == -1 &&
	 	URL.indexOf('end_price=') == -1 &&
	 	URL.indexOf('detail_category=') == -1 &&
	 	URL.indexOf('sort=') == -1) )
	 {	//URL에 page파라미터가 있고,다른파라미터가 있다면
	   let start_URL = URL.substring(0,page_start_idx)+page;
	   let last_URL = URL.substring(page_start_idx+page_val_length);
	   location.href=getContextPath()+start_URL + last_URL;
	   return
	 }
	 
	 
	 else if (URL.indexOf('p=')==-1 &&
		      URL.indexOf('category=')== -1 &&
	 		  URL.indexOf('bestyn=') == -1 &&
	 		  URL.indexOf('gender=') == -1 &&
	 		  URL.indexOf('start_price=') == -1 &&
	 		  URL.indexOf('end_price=') == -1 &&
	 		  URL.indexOf('sort=') == -1 &&
	 		  URL.indexOf('detail_category=') == -1
	          )
	 {	//URL에 page 파라미터가 없고,다른 파라미터도 없다면
	    location.href=getContextPath()+'/product/shop.dream?p='+page;
	    return;
	 }
	 
	 
	 else if(URL.indexOf('p=')!=-1 &&
	         URL.indexOf('category=')== -1 &&
	 		 URL.indexOf('bestyn=') == -1 &&
	 		 URL.indexOf('gender=') == -1 &&
	 		 URL.indexOf('start_price=') == -1 &&
	 		 URL.indexOf('end_price=') == -1 &&
	 		 URL.indexOf('sort=') == -1 &&
	 		 URL.indexOf('detail_category=') == -1)
	 {  //URL에 page 파라미터는 있고, 다른파라미터는 없다면
	   location.href=getContextPath()+URL.substring(0,page_start_idx)+page;
	   return;
	 } 
		
		
		
	 else {	//URL에 page파라미터는 없고,다른파라미터는 있다면,
		URL += '&p='+page;
		location.href=getContextPath()+URL;
		return;
	 }
  }
  
  //검색페이지에서 샵페이지로 온 경우 페이지 처리
  function goPage_keyword(page){
	location.href=getContextPath()+'/product/keywordGoShop.dream?keyword='+sessionStorage.getItem("keyword")+'&p='+page;
  }



