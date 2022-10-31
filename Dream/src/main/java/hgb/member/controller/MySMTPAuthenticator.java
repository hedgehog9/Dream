package hgb.member.controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MySMTPAuthenticator extends Authenticator{

	@Override
	public PasswordAuthentication getPasswordAuthentication() {	// javax.mail로 import 를 한다.
		
		// Gmail 의 경우 @gmail.com 을 제외한 아이디만 입력한다.
		return new PasswordAuthentication("testmail6007", "qxyjhxbjpzzlfegh"); // 앞에는 @gmail.com 을 제외한 아이디 입력
																			  // 뒤에는 앱 비밀번호를 입력	
	};
	
}
