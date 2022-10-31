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

public class LikeCheck extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		if("GET".equalsIgnoreCase(method)) {	//"GET"
			HttpSession session = request.getSession();
			String userid = (String) session.getAttribute("userid");
			String productNum = request.getParameter("productNum");
			boolean login = false;
			if(userid == null || userid.trim().isEmpty()) {	//로그인중인 사람이 없다면 로그인페이지로 리다이렉트
				login = false;
				JSONObject jsonObj = new JSONObject();
				
				
				jsonObj.put("login", login);     
				String json = jsonObj.toString(); 
				
				request.setAttribute("json", json);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/jsonview.jsp");
			}
			
			
			else {	//로그인중인 사용자가 있다면
				login = true;
				
				Map<String, String> paraMap = new HashMap<>();
				paraMap.put("userid", userid);
				paraMap.put("productNum", productNum);
				
				InterLikeDAO ldao = new LikeDAO();
				boolean like_exist = ldao.likeCheck(paraMap);
				
				JSONObject jsonObj = new JSONObject();
				
		
				jsonObj.put("like_exist", like_exist);     
				jsonObj.put("login", login); 
				String json = jsonObj.toString(); 
				
				request.setAttribute("json", json);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/jsonview.jsp");
			}
		}
		else {		//"POST"
			
		}
	}//end of public void execute(HttpServletRequest request, HttpServletResponse response)----

}
