package hgb.member.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import common.controller.AbstractController;

public class SendEmail extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();
		String email = request.getParameter("email");
		
		boolean sendMailSuccess = false; // 메일이 정상적으로 전송되었는지 유무를 알아오기 위한 용도 
		
		if("POST".equalsIgnoreCase(method)) {
			
			// 인증키를 랜덤하게 생성하도록 한다.
			Random rnd = new Random();
			
			String certificationCode = "";
			
			char randchar = ' ';
			for(int i=0; i<5; i++) {
				
				randchar = (char) (rnd.nextInt('z' - 'a' + 1) + 'a');
				certificationCode += randchar;
			}// end of for--------------------------
			
			int randnum = 0;
			for(int i=0; i<7; i++) {
				randnum = rnd.nextInt(9 - 0 + 1) + 0;
				certificationCode += randnum;
			}// end of for--------------------------
			
			
			GoogleMail mail = new GoogleMail();
			
			try {
				 mail.sendmail(email, certificationCode);
				 sendMailSuccess = true; 
				 
					/*
					 * HttpSession session = request.getSession();
					 * session.setAttribute("certificationCode", certificationCode);
					 */
			
			} catch(Exception e) { // 메일 전송이 실패한 경우 
				e.printStackTrace();
				sendMailSuccess = false; // 메일 전송 실패했음을 기록함.
			}
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("sendMailSuccess", sendMailSuccess);     
			jsonObj.put("certificationCode", certificationCode);     
			
			String json = jsonObj.toString(); 
			
			request.setAttribute("json", json);
			
			super.setViewPage("/WEB-INF/joshJsonview.jsp");
			
		} // end of public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception
		
	}

}
