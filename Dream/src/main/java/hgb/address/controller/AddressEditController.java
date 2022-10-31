package hgb.address.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import hgb.address.model.AddressDAO;
import hgb.address.model.AddressDTO;
import hgb.address.model.InterAddressDAO;

public class AddressEditController extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        String method = request.getMethod();
		
		if("GET".equalsIgnoreCase(method)) {	
		//  GET 방식이라면
		//	super.setRedirect(false);	
			super.setViewPage("/WEB-INF/view/member/address.jsp");
		}
		
		else {
			
			
             HttpSession session = request.getSession();
             //변수 선언
             String userid = (String) session.getAttribute("userid");
			
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
	                  	         	         
	         
	         AddressDTO address3 = new AddressDTO(order_name, mobile, post_code, address, detail_address, basic_address, address_num, userid);
	         
	         InterAddressDAO adao = new AddressDAO();
	         
			 
	         try {
	        	 int n = adao.editAddress(address3);
	        	 
	        	 
	     
     			if(n==1) {
	     			
     				
     				super.setRedirect(true);
     				super.setViewPage(request.getContextPath()+"/member/address.dream"); 
     				 
	     			
	        	 }
	        	 
	         } catch(SQLException e) {
	        	e.printStackTrace();
	 			super.setRedirect(true);
	 			super.setViewPage(request.getContextPath()+"/WEB-INF/view/member/address.jsp"); 
	         }
			
			
			
		}
		
		

		
	}

}
