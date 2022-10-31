package hgb.address.controller;

import java.awt.Window.Type;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import hgb.address.model.AddressDAO;
import hgb.address.model.AddressDTO;
import hgb.address.model.InterAddressDAO;



public class AddressRegisterController extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if("GET".equalsIgnoreCase(method)) {	
		//  GET 방식이라면
		//	super.setRedirect(false);	
			super.setViewPage("/WEB-INF/view/member/address.jsp");
		}
		
		
		else {
			
			
			 // POST 방식이라면(즉, 저장하기 버튼을 클릭한 경우)
			 String order_name = request.getParameter("order_name"); 				
			 String mobile = request.getParameter("mobile"); 
			 String post_code = request.getParameter("post_code"); 
			 String address = request.getParameter("address"); 	         
	         String detail_address = request.getParameter("detail_address");
	         
	         String address_num = request.getParameter("address_num");
	         String basic_address = "";
	         if( request.getParameter("basic_address") != null ) {
	        	 basic_address = request.getParameter("basic_address");
	         }
	         else {// null 이면 off
	        	 basic_address = "off";
	         }
	        
	         
	         
	         

	         
	         
	         HttpSession session = request.getSession();	//로그인중인 userid값 가져오기위한 session 객체생성
			 //로그인 중인 사람의 userid 값 가져오기
			 String userid = (String)session.getAttribute("userid");
		//	 String userid = "josh@gmail.com";	//세션 코드 합치면 위의코드로 변경하기 이건 가라로  해놓은거임
	         
	         
	         AddressDTO address1 = new AddressDTO(order_name, mobile, post_code, address, detail_address, basic_address, address_num, userid);
	         InterAddressDAO adao = new AddressDAO();
	         
	         
	         
	         //데이터가 있는지 없는지 여부 검사하기
	         
	         boolean check_basic_address = adao.check_basic(userid);	//기본배송지가 있으면 true, 없으면 false;
			 
	         try {
	        	int n = 0;
	        	
	        	
	        	if(basic_address.equalsIgnoreCase("on")){
	        		n = adao.registerBasicAddress(address1);
	        		
	        	}
	        	else {
	        	
		        	if(check_basic_address) {	//기본배송지가 있다면
		        		n = adao.registerAddress(address1);
		        	}
		        	else {	//기본배송지가 없다면
		        		n = adao.registerBasicAddress(address1);
		        	}
	        	
	        	}
	     
     			if(n==1) {	//제대로 입력이 되었더라면
     				super.setRedirect(true);
     				super.setViewPage(request.getContextPath()+"/member/address.dream"); 
	        	}
     			
     			else {	// 무언가의 이유로 입력이 안되었으면
     			
     			}
	        	 
	         } catch(SQLException e) {
	        	e.printStackTrace();
	 			super.setRedirect(true);
	 			super.setViewPage(request.getContextPath()+"/WEB-INF/view/member/address.jsp"); 
	         }
			
			
			
		}
		
		

		
	}

}
