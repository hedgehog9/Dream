
$(document).ready(function() {
	  $("h1#project_dream").fadeIn(1500);
      $('#fullpage').fullpage({
        //options here
        anchors:['section1','section2','section3','section4','section5','section6'],
        // autoScrolling:true,
        // scrollHorizontally: true,
        navigation:true,
        navigationPosition:'right',
        afterLoad: function(anchorLink,idx){
	      // == 1 == //
          if(idx == 1){                  //1번섹션일때 실행될 애니메이션
            const color = 'black';
            header_color(color);
            $("h1#project_dream").hide();
            $("h1#project_dream").fadeIn(2000);
          }
          else{ 	                     //1번섹션이 아닐때
			$("h1#project_dream").hide();
          }
          
          
          // == 2 == //
          if(idx == 2){                  //2번섹션일때
            const color = 'white';
            header_color(color);
            $("#develop_reason").show();
            new TypeIt("#develop_reason")
              .pause(500)
              .go()
          }
          else{			                 //2번섹션이 아닐때
			$("#develop_reason").hide();
          }
          
          
          
          // == 3 == //
          if(idx == 3) { 				//3번섹션일때
            const color = 'black';
            header_color(color);
            $(".clipText").show();
            const dom = document.getElementById('develop_reason_text');
            clip_text(dom);
          }
          else{							//3번섹션이 아닐때
			$(".clipText").hide();
          }
          
          
          
          // == 4 == //
          if(idx == 4) {				 //4번섹션일때
            const color = 'white';
            header_color(color);
            $("#developer_introduce").show();
            new TypeIt("#developer_introduce")
              .pause(500)
              .go()
          }
          else{			 				//4번섹션이 아닐때
			$("#developer_introduce").hide();
          }
          
          
          
          // == 5 현우 소개== //
          if(idx == 5) { 				//5번섹션일때
            const color = 'black';
            header_color(color);
            $("div#hyunwoo_about_me").show();
            $("div#hyunwoo_skill").show();
            $("div#hyunwoo_contact_me").show();
            $("div#hyunwoo_project_review").show();
            $("div#hyunwoo_profile_image").show();
            
            $("div#hyunwoo_profile_image").addClass("on");
            const dom = document.getElementById('hyunwoo_about_me');
            clip_text(dom);
            const dom2 = document.getElementById('hyunwoo_skill');
            clip_text(dom2);
            const dom3 = document.getElementById('hyunwoo_contact_me');
            clip_text(dom3);
            const dom4 = document.getElementById('hyunwoo_project_review');
            clip_text(dom4);
          }
          else{			 				//5번섹션이 아닐때
            $("div#hyunwoo_profile_image").removeClass("on");
            $("div#hyunwoo_profile_image").hide();
            $("div#hyunwoo_about_me").hide();
            $("div#hyunwoo_skill").hide();
            $("div#hyunwoo_contact_me").hide();
            $("div#hyunwoo_project_review").hide();
          }
          
          
          
          
          
          // == 6 진영 소개 == //
          if(idx == 6) { 				//6번섹션일때
            const color = 'white';
            header_color(color);
            $("div#jinyoung_about_me").show();
            $("div#jinyoung_skill").show();
            $("div#jinyoung_contact_me").show();
            $("div#jinyoung_project_review").show();
            $("div#jinyoung_profile_image").show();
            
            $("div#jinyoung_profile_image").addClass("on");
            const dom = document.getElementById('jinyoung_about_me');
            clip_text(dom);
            const dom2 = document.getElementById('jinyoung_skill');
            clip_text(dom2);
            const dom3 = document.getElementById('jinyoung_contact_me');
            clip_text(dom3);
            const dom4 = document.getElementById('jinyoung_project_review');
            clip_text(dom4);
          }
          else{			 				//6번섹션이 아닐때
            $("div#jinyoung_profile_image").removeClass("on");
			$("div#jinyoung_profile_image").hide();
            $("div#jinyoung_about_me").hide();
            $("div#jinyoung_skill").hide();
            $("div#jinyoung_contact_me").hide();
            $("div#jinyoung_project_review").hide();
          }
          
          
          
          // == 7 상운 소개 == //
          if(idx == 7) { 				//7번섹션일때
            const color = 'white';
            header_color(color);
            $("div#josh_about_me").show();
            $("div#josh_skill").show();
            $("div#josh_contact_me").show();
            $("div#josh_project_review").show();
            $("div#josh_profile_image").show();
            
            $("div#josh_profile_image").addClass("on");
            const dom = document.getElementById('josh_about_me');
            clip_text(dom);
            const dom2 = document.getElementById('josh_skill');
            clip_text(dom2);
            const dom3 = document.getElementById('josh_contact_me');
            clip_text(dom3);
            const dom4 = document.getElementById('josh_project_review');
            clip_text(dom4);
          }
          else{			 				//7번섹션이 아닐때
			$("div#josh_profile_image").removeClass("on");
			$("div#josh_profile_image").hide();
            $("div#josh_about_me").hide();
            $("div#josh_skill").hide();
            $("div#josh_contact_me").hide();
            $("div#josh_project_review").hide();
          }
          
          
          
          // == 8 광빈 소개 == //
          if(idx == 8) { 				//8번섹션일때
            const color = 'white';
            header_color(color);
            $("div#hgb_about_me").show();
            $("div#hgb_skill").show();
            $("div#hgb_contact_me").show();
            $("div#hgb_project_review").show();
            $("div#hgb_profile_image").show();
            
            $("div#hgb_profile_image").addClass("on");
            const dom = document.getElementById('hgb_about_me');
            clip_text(dom);
            const dom2 = document.getElementById('hgb_skill');
            clip_text(dom2);
            const dom3 = document.getElementById('hgb_contact_me');
            clip_text(dom3);
            const dom4 = document.getElementById('hgb_project_review');
            clip_text(dom4);
          }
          else{			 				//8번섹션이 아닐때
			$("div#hgb_profile_image").removeClass("on");
			$("div#hgb_profile_image").hide();
            $("div#hgb_about_me").hide();
            $("div#hgb_skill").hide();
            $("div#hgb_contact_me").hide();
            $("div#hgb_project_review").hide();
          }
          
          
          
          // == 9 진석 소개 == //
          if(idx == 9) { 				//9번섹션일때
            const color = 'white';
            header_color(color);
            $("div#kimjs_about_me").show();
            $("div#kimjs_skill").show();
            $("div#kimjs_contact_me").show();
            $("div#kimjs_project_review").show();
            $("div#kimjs_profile_image").show();
            
            $("div#kimjs_profile_image").addClass("on");
            const dom = document.getElementById('kimjs_about_me');
            clip_text(dom);
            const dom2 = document.getElementById('kimjs_skill');
            clip_text(dom2);
            const dom3 = document.getElementById('kimjs_contact_me');
            clip_text(dom3);
            const dom4 = document.getElementById('kimjs_project_review');
            clip_text(dom4);
          }
          else{			 				//9번섹션이 아닐때
			$("div#kimjs_profile_image").removeClass("on");
			$("div#kimjs_profile_image").hide();
            $("div#kimjs_about_me").hide();
            $("div#kimjs_skill").hide();
            $("div#kimjs_contact_me").hide();
            $("div#kimjs_project_review").hide();
          }
          
          
          
          // == 10 끝 == //
          if(idx == 10) { 				//10번섹션일때
            const color = 'white';
            header_color(color);
            $("#section_end").show();
            new TypeIt("#section_end")
              .pause(1500)
              .delete(6,{delay:1000})
              .type('감사합니다!!')
              .go()
          }
          else{			 				//10번섹션이 아닐때
			$("#section_end").hide();
          }
          
        }//end of afterLoad: function(anchorLink,idx){}----

      });//end of $('#fullpage').fullpage({})---

    });//end of $(document).ready(function() {})------
    
    function header_color(color){
	  $("h1#logo").css("color",color);
      $("i.fa-facebook").css("color",color);
      $("i.fa-instagram").css("color",color);
      $("#fp-nav > ul > li > a > span").css("background-color",color);
    }
    
    const SEC = 0.5;
	function clip_text(dom){
		const childs = dom.children;
		for(let i = 0; i<childs.length; i++){
			childs[i].style.animationDelay = `${SEC * i }s`;
			childs[i].classList.add('on');
		}//end of for--
	}//end of function clip_text(dom)--
    
    
    
     
	     
    
