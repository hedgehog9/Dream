package jjy.login.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import common.controller.AbstractController;
import jjy.login.model.*;
import net.nurigo.java_sdk.api.Message;

public class FindPwdController extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		 String method = request.getMethod(); 
		 
		 if("POST".equalsIgnoreCase(method)) { // "POST"방식
			 
			String api_key = "NCS3QAJRVZXIPIX4"; // 발급받은 본인 API Key
			String api_secret = "XNCSM8KRACNOF4APKHFQXYDM3N9BSFVS";  // 발급받은 본인 API Secret

			Message coolsms = new Message(api_key, api_secret);
			
			String smsContent = "Dream 임시 비밀번호 [ "; // 랜덤 문자 + 숫자 + 특수문자 8자리 임시 비밀번호 발급  
			
			/////////////////////////////////////////////////////////////////////////////////////////
			// 임시 비밀번호 생성 
			Random rnd = new Random();
			char randchar = ' ';
			
			String tempPwd = "";
			
			for(int i=0; i<4; i++) {
				randchar = (char) (rnd.nextInt('z' - 'a' + 1) + 'a');
				tempPwd += randchar;
			}// end of for--------------------------
			
			int randnum = 0;
			for(int i=0; i<4; i++) {
				randnum = rnd.nextInt(9 - 0 + 1) + 0;
				tempPwd += randnum;
			}// end of for--------------------------
			
			final char[] passwordTable =  { '!', '@', '#', '$', '%', '^', '&', '*', '(', ')' };
			tempPwd += passwordTable[rnd.nextInt(passwordTable.length)];
			smsContent += tempPwd; 
			smsContent += " ]입니다."; 
			///////////////////////////////////////////////////////////////////////////////////////////
			
			String userid = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			
			
			HashMap<String, String> findPwdMap = new HashMap<>();
			findPwdMap.put("mobile", mobile);
			findPwdMap.put("userid", userid);
			findPwdMap.put("tempPwd",tempPwd);
			findPwdMap.put("smsContent",smsContent);
			
			InterLoginDAO logindao = new LoginDAO();
			boolean isExistUser = logindao.checkMobileEmail(findPwdMap);
			
			if(isExistUser) { // 이메일, 아이디에 일치하는 회원이 존재하는 경우 
		        HashMap<String, String> paraMap = new HashMap<>();
		        paraMap.put("to", mobile);         
		        paraMap.put("from", "01088665389");
		                                            
		        paraMap.put("type", "SMS");        
		        paraMap.put("text", smsContent);   
		        
		        paraMap.put("app_version", "JAVA SDK v2.2"); 
		        JSONObject jsonObj = (JSONObject)coolsms.send(paraMap);
		        String json = jsonObj.toString();
		        
		        logindao.updatePassword(findPwdMap);
		        
		        request.setAttribute("json", json);
		        super.setViewPage("/WEB-INF/view/jjyjsonview.jsp");
				return;
			}
			else { // 이메일과 아이디가 일치하는 회원이 존재하지 않는 경우 
				JSONObject jsonObj2 = new JSONObject(); //  {}
				
				String json = jsonObj2.toString(); 
				
				request.setAttribute("json", json);
				
			//	super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/jjyjsonview.jsp");
				return;
			}
			
		 } 
		
		super.setViewPage("/WEB-INF/view/login/findPwd.jsp");
	}

}
