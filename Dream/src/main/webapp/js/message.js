function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

const urlParams = new URL(location.href).searchParams;
let type = urlParams.get('type');
let p = urlParams.get('p');

$(document).ready(function () {
	


	
	if(type == null){
		type="receieve";
	}
	if(p == null){
		p == "1";
	}

	
	
	}); // emd of document
	

function goRead(read_check, messageno){
		
	if(read_check == 0 ){
	
			$.ajax({
   		url: getContextPath()+"/member/readmessage.dream",
   		type: "GET",
   		data: {"messageno": messageno},
   		dataType: "json",
   		success: function(json) {
	
			if(json.success == 1){// 1개 읽음처리
			
				$("td#icon"+messageno).empty();
				
				$("td#icon"+messageno).append(`<i class="fa-solid fa-envelope-open"></i>`);
				
			}
   			
   			
   		}, // end of success
   		 error: function(request, status, error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
   	});// end of ajax(){};===========================================================
 			 
		
	}

}// end



function goPage(page){
		
	console.log("page"+page);
	p = page;
	
	callAjax();
}// end



function deleteAll(){
		
	 let param = "";
	 let messageno ;
	        
    $("input:checkbox[id='sub_check']:checked").each(function(index,item) {
    	

       messageno = $(item).parent().attr('asd');
       param += messageno + ",";
        	
    });
    	param=param.substring(0, param.length-1);
    	console.log("param"+param);
    	console.log("type"+type);
    $.ajax({
   		url: getContextPath()+"/member/deletemessage.dream",
   		type: "GET",
   		data: {"messageno":param,
   				"type":type},
   		dataType: "json",
   		success: function(json) {
	
			callAjax(); // 삭제후 페이징 처리 새로!
   			
   			
   		}, // end of success
   		 error: function(request, status, error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
   	});// end of ajax(){};===========================================================
 			 
    

}// end


function callAjax() {
	  

   	// 결과 값 찍어주는 ajax
   	$.ajax({
   		url: getContextPath()+"/member/message.dream",
   		type: "GET",
   		data: {"type": type,
   			   "p": p,
   			   },
   		dataType: "json",
   		success: function(json) {
   	
   			
   			let html =" ";
   			let html2 =" ";
   			
   			// 조회결과가 있는 경우 
   			if(json.cnt > 0){
				console.log(json.cnt);
   				$("tbody").empty();
   				$("nav#pgbar").empty();
   			
   				$.each(json.printmessageList,function(index, item){	
				
					html+=
					`<tr>
				        <td id="list_check" asd="${item.messageno}" style="width:30px;" >
				        <input type="checkbox" id="sub_check" name="sub_check" style="margin-right: 10px;"/>        
				        </td>
				        <td id="icon${item.messageno}" style="width: 30px;" data-toggle="modal" data-target="#message_modal" data-dismiss="modal" onclick="message_info('${item.messageno}','${item.title}','${item.contents}','${item.read_check}');"> ` ;  
		            if(item.read_check==0){
						html+= `<i class="fa-solid fa-envelope"></i>`;
					 }	
					 if(item.read_check==1){
						html+= `<i class="fa-solid fa-envelope-open"></i>`;
					 }
					 html+=
					`</td>
				        
			           <td colspan="7" style=" font-weight: bold"  data-toggle="modal" data-target="#message_modal" data-dismiss="modal" onclick="message_info('${item.messageno}','${item.title}','${item.contents}','${item.read_check}');">
				           ${item.title}         
			          </td>
			          <td  data-toggle="modal" data-target="#message_modal" data-dismiss="modal" onclick="message_info('${item.messageno}','${item.title}','${item.contents}','${item.read_check}');">
				             ${item.shipping}
				             <input type="text" id="load_messageno" name="load_messageno" value="${item.messageno}"/> 
				             <input type="text" id="load_fk_sender_userid" name="load_fk_sender_userid" value="${item.fk_sender_userid}"/>   
				             
				            
			          </td>
			       </tr>`;


				})
				$("tbody").append(html);
				}
				if(json.printmessageList != null){
					html2 += `<ul class="my pagination pagination-md justify-content-center mt-5">`;
			             
			             if (json.page > json.display_page){
						html += `<li class="page-item">
			               <a class="page-link" onclick = "goPage(1)">
			                  <i class="fa-solid fa-angles-left"></i>
			               </a>
			             </li>
			             
			             
			            
			             <li class="page-item">
			               <a class="page-link"  onclick = "goPage(${json.startPage-1})">
			                  <i class="fa-solid fa-angle-left"></i>
			               </a>
			             </li>`;
						}
			             
			             
			             
			             for(let i=json.startPage-1 ; i<= json.endPage-1;i++){
							if(json.page == (json.startPage+i.count-1)){
								html2 += `<li class="page-item active" aria-current="page">
			                <a id = "firstPage" class="page-link" onclick = "goPage(${json.startPage+i.count-1})" >${json.startPage+i.count-1}</a>
			             	</li>`;
							}
							if(json.page != (json.startPage+i.count-1)){
								html2 += `<li class="page-item">
			                <a class="page-link" onclick = "goPage(${json.startPage+i.count-1})">${json.startPage+i.count-1}</a>
			            	 </li>`;
							}
						}
			      
			         
			             if (!(json.last_display_page)){
							html2 += ` <li class="page-item">
			               <a class="page-link" onclick = "goPage(${json.startPage+json.display_page})"><i class="fa-solid fa-angle-right"></i></a>
			             </li>
			       
			             <li class="page-item">
			               <a class="page-link" onclick = "goPage(${json.totalPage})"><i class="fas fa-solid fa-angles-right"></i></a>
			             </li>
			             </ul>`;
						}
			            
			          
			           
					
					
					
					
				$("nav#pgbar").append(html2);
				
				
				
				
				
				
				
				
				
			
}	
   			
   		}, // end of success
   		 error: function(request, status, error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
   	});// end of ajax(){};===========================================================
 			 
}  //end of function callAjax() {}-----------------------		
