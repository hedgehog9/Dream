package josh.member.controller;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GoogleMail {

	public void sendmail(String recipient, String certificationCode) throws Exception {
							// 첫번째 파라미터 => 받는사람의 이메일 , 두번째 파라미터 => 인증코드
	        
	        // 1. 정보를 담기 위한 객체
	        Properties prop = new Properties(); 
	        
	        // 2. SMTP(Simple Mail Transfer Protocoal) 서버의 계정 설정
	        //    Google Gmail 과 연결할 경우 Gmail 의 email 주소를 지정 
	        prop.put("mail.smtp.user", "testmail6007@gmail.com");
	            
	        
	        // 3. SMTP 서버 정보 설정
	        //    Google Gmail 인 경우  smtp.gmail.com
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	             
	        
	        prop.put("mail.smtp.port", "465");
	        prop.put("mail.smtp.starttls.enable", "true");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.debug", "true");
	        prop.put("mail.smtp.socketFactory.port", "465");
	        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        prop.put("mail.smtp.socketFactory.fallback", "false");
	        
	        prop.put("mail.smtp.ssl.enable", "true");
	        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	          
	        
	        Authenticator smtpAuth = new MySMTPAuthenticator(); // import 시 javax.mail 로 import 해야한다. 뒤에 new 객체는 우리가 만드는 것
	        													   // 뒤에 클래스에서 비밀번호를 입력하는 것이다.
	        Session ses = Session.getInstance(prop, smtpAuth);  // import 시 javax.mail 로 import 해야한다.
	           
	        // 메일을 전송할 때 상세한 상황을 콘솔에 출력한다.
	        ses.setDebug(true);
	                
	        // 메일의 내용을 담기 위한 객체생성
	        MimeMessage msg = new MimeMessage(ses); // import 시 javax.mail.internet 로 import 해야한다.

	        // 메일 제목 설정
	        String subject = "localhost:9090/MyMVC/index.up 회원님의 비밀번호를 찾기위한 인증코드 발송";
	        msg.setSubject(subject);
	                
	        // 보내는 사람의 메일주소
	        String sender = "testmail6007@gmail.com";
	        Address fromAddr = new InternetAddress(sender); // import 시 javax.mail 로 import 해야한다.
	        msg.setFrom(fromAddr);
	                
	        // 받는 사람의 메일주소
	        Address toAddr = new InternetAddress(recipient);
	        msg.addRecipient(Message.RecipientType.TO, toAddr); // import 시 javax.mail 로 import 해야한다.
	                
	        // 메시지 본문의 내용과 형식, 캐릭터 셋 설정
	        msg.setContent("발송된 인증코드 : <span style='font-size:14pt; color:red;'>"+certificationCode+"</span>", "text/html;charset=UTF-8");
	                
	        // 메일 발송하기
	        Transport.send(msg); // import 시 javax.mail 로 import 해야한다.
	        
	     }// end of sendmail(String recipient, String certificationCode)-----------------
	
}
