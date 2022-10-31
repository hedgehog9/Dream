package hgb.address.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import hgb.address.model.AddressDAO;
import hgb.address.model.AddressDTO;
import hgb.address.model.InterAddressDAO;

public class AddressDeleteController extends AbstractController {

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
			 	 
	         String address_num = request.getParameter("address_num"); 	 
	         
	         AddressDTO address2 = new AddressDTO(address_num);
	         
	         InterAddressDAO adao = new AddressDAO();
	         
	         try {
	        	 int n = adao.deleteAddress(address2);
	     
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
