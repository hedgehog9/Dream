package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import josh.member.model.MemberDTO;
import my.util.MyUtil;

public abstract class AbstractController implements InterCommand {

	/*
	    === 다음의 나오는 것은 우리끼리한 약속이다. ===
	
	    ※ view 단 페이지(.jsp)로 이동시 forward 방법(dispatcher)으로 이동시키고자 한다라면 
	      자식클래스에서는 부모클래스에서 생성해둔 메소드 호출시 아래와 같이 하면 되게끔 한다.
	     
	      super.setRedirect(false); 
	      super.setViewPage("/WEB-INF/index.jsp");
	    
	    
        ※ URL 주소를 변경하여 페이지 이동시키고자 한다라면
          즉, sendRedirect 를 하고자 한다라면    
          자식클래스에서는 부모클래스에서 생성해둔 메소드 호출시 아래와 같이 하면 되게끔 한다.
            
          super.setRedirect(true);
          super.setViewPage("registerMember.up");               
	*/   
	
	
	private boolean isRedirect = false;
	// isRedirect 변수의 값이 false 이라면 view단 페이지(.jsp)로  forward 방법(dispatcher)으로 이동시키겠다. 
    // isRedirect 변수의 값이 true 이라면 sendRedirect 로 페이지이동을 시키겠다.
	
	private String viewPage;
	// viewPage 는 isRedirect 값이 false 이라면 view단 페이지(.jsp)의 경로명 이고,
    // isRedirect 값이 true 이라면 이동해야할 페이지 URL 주소 이다.

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	
	///////////////////////////////////////////////////////////////////////
	
	public boolean checkLogin(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String loginuser = (String) session.getAttribute("userid");
		
		if( loginuser != null && loginuser != "") {
			// 로그인 한 경우
			return true;
		}
		else {
			// 로그인 안한 경우
			return false;
		}
	}
	
	// == 로그인 또는 로그아웃을 하면 시작페이지로 가는 것이 아니라 방금 보았던 그 페이지로 그대로 가기 위한 것임. == //
	public void goBackURL(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.removeAttribute("goBackURL");
		session.setAttribute("goBackURL", MyUtil.getCurrentURL(request));// 현재 URL 을 얻어온다음 session에 넣어둔다.
		
//			System.out.println( MyUtil.getCurrentURL(request) );
		
	}

	
	
}
