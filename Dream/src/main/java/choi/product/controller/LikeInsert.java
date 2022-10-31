package choi.product.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import choi.product.model.InterLikeDAO;
import choi.product.model.LikeDAO;
import common.controller.AbstractController;

public class LikeInsert extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		
		if("GET".equalsIgnoreCase(method)) {	//method가 "GET"일 때
			HttpSession session = request.getSession();
			String userid = (String) session.getAttribute("userid"); //session에서 로그인중인 사람의 아이디를 가져온다.
			String productNum = request.getParameter("productNum");
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("userid", userid);
			paraMap.put("productNum", productNum);
			boolean insertLikeSuccess = false;
			// json 은 자바스크립트 객체 표기법으로만 해주면 끝난다. { }
			InterLikeDAO ldao = new LikeDAO();
			int n  = ldao.likeinsert(paraMap);
			
			if(n != 1) {	//insert가 실패시
				
				
			}
			else {	//insert가 성공시
				insertLikeSuccess = true;
				// 자바스크립트 객체 표기법으로 나타내려면 별도의 라이브러리가 필요 (Json in java.jar 또는 json_simple.jar)
				JSONObject jsonobj = new JSONObject();
				// 위 코드는 자바스크립트 객체 { } 하나를 생성한 것과 같다.
				jsonobj.put("insertLikeSuccess", insertLikeSuccess); // {"isExists":true} 또는 {"isExists":false} 으로 만들어준다.
				
				String json = jsonobj.toString(); // web에 보내주기 위해 문자열 형태인 "{"isExists":true}" 또는 "{"isExists":false}" 으로 만들어준다.
				//	System.out.println(">>> 확인용 json => " + json);
				
				request.setAttribute("json", json);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/jsonview.jsp");
			}
				
			
			
			
			
		}
		else {		//method가 "POST"일 때 
			
		}
		
	}

}
