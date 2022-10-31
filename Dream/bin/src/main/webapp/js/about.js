$(document).ready(function() {
      $('#fullpage').fullpage({
        //options here
        anchors:['section1','section2','section3','section4','section5','section6'],
        // autoScrolling:true,
        // scrollHorizontally: true,
        navigation:true,
        navigationPosition:'right',
        afterLoad: function(anchorLink,idx){
          // console.log("현재섹션번호는 : "+idx);
          if(idx == 1){ //1번섹션일때 실행될 애니메이션
            // alert("1번입니다!");
            console.log("현재섹션번호는 : "+idx);
            $("h1#project_dream").fadeIn(2000);
          }
          else if(idx == 2){  //2번섹션일때 실행될 애니메이션
          }
          else if(idx == 3) { //3번섹션일때 실행될 애니메이션
            
          }
          else if(idx == 4) { //4번섹션일때 실행될 애니메이션
            
          }
          else if(idx == 5) { //5번섹션일때 실행될 애니메이션
            
          }
        }//end of afterLoad: function(anchorLink,idx){}----

      });//end of $('#fullpage').fullpage({})---

    });//end of $(document).ready(function() {})------