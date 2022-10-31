package josh.cart.controller;

import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import common.controller.AbstractController;
import net.nurigo.java_sdk.api.Message;

public class PurchaseSms extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();
		
		
		if(!"POST".equals(method)) {
			String message = "비정상적인 경로로 들어왔습니다.";
			String loc = request.getContextPath()+"/index.dream";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			// super.setRedirect(false);
			super.setViewPage("/WEB-INF/joshmsg.jsp");
		}
		
		else {
			//String api_key = "발급받은 본인의 API Key";  // 발급받은 본인 API Key
			String api_key = "NCS3QAJRVZXIPIX4";
			
			// String api_secret = "발급받은 본인의 API Secret";  // 발급받은 본인 API Secret
			String api_secret = "XNCSM8KRACNOF4APKHFQXYDM3N9BSFVS";
			
			Message coolsms = new Message(api_key, api_secret); // net.nurigo.java_sdk.api.Message 을 import 해와야 함
	       // net.nurigo.java_sdk.api.Message 임. 
	       // 먼저 다운 받은  javaSDK-2.2.jar 를 /MyMVC/src/main/webapp/WEB-INF/lib/ 안에 넣어서  build 시켜야 함.
			
			String mobile = request.getParameter("mobile"); // 문자를 받는사람의 번호
			
			String msg = "고객님, 드림에서 주문해주셔서 감사합니다. 사랑합니다!";
			
			
			// == 4개 파라미터(to, from, type, text)는 필수사항이다. == 
		    HashMap<String, String> paraMap = new HashMap<>();
			paraMap.put("to",mobile);  				// 수신번호
			paraMap.put("from","010-8866-5389"); 	// 발신번호
													// 2020년 10월 16일 이후로 발신번호 사전등록제로 인해 등록된 발신번호로만 문자를 보내실 수 있습니다
			paraMap.put("type","SMS");				// Message type ( SMS(단문), LMS(장문), MMS, ATA )
			paraMap.put("text",msg);	// 인증번호
			
		//	paraMap.put("datatime","202210071200");  // 문자를 보낼 시간
													// 예약전송시 날짜 설정 반드시 YYYYMMDDHHMISS 형식대로 해야함.
			
			paraMap.put("app_version", "JAVA SDK v2.2"); // application name and version 꼭 써야함!!
			
			JSONObject jsonObj = (JSONObject) coolsms.send(paraMap); // 주의!! import 할때 simple 로 넣어야한다
			/*
	        org.json.JSONObject 이 아니라 
	        org.json.simple.JSONObject 이어야 한다.  
			*/
			
			String json = jsonObj.toString();
			
			// System.out.println("확인용 >>>>>>>>>>>>> json : "  +json);
			
			request.setAttribute("json", json);
			
			// super.setRedirect(false);
			super.setViewPage("/WEB-INF/joshJsonview.jsp");
		}
	}

}
