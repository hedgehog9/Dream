package choi.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import choi.member.model.InterMemberDAO;
import choi.member.model.MemberDAO;
import common.controller.AbstractController;

public class MobileDuplicateCheck extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod(); 
		
		if("post".equalsIgnoreCase(method)) { // post 방식일경우만 id 중복검사 진행 
			
			String mobile = request.getParameter("mobile"); // " " 에는 태그의 name이 (지금은 userid) 들어온다.
		
			// json 은 자바스크립트 객체 표기법으로만 해주면 끝난다. { }
			InterMemberDAO mdao = new MemberDAO();
			boolean isExists = mdao.mobileDuplicateCheck(mobile);
			
			// 자바스크립트 객체 표기법으로 나타내려면 별도의 라이브러리가 필요 (Json in java.jar 또는 json_simple.jar)
			JSONObject jsonobj = new JSONObject();
			// 위 코드는 자바스크립트 객체 { } 하나를 생성한 것과 같다.
			jsonobj.put("isExists", isExists); // {"isExists":true} 또는 {"isExists":false} 으로 만들어준다.
			
			String json = jsonobj.toString(); // web에 보내주기 위해 문자열 형태인 "{"isExists":true}" 또는 "{"isExists":false}" 으로 만들어준다.
		//	System.out.println(">>> 확인용 json => " + json);
			
			request.setAttribute("json", json);
			
		 // super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/jsonview.jsp");
			
		}// end of if("post".equalsIgnoreCase(method)) -----
		else {
			
		}
		
	}
	
}
