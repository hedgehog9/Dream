package josh.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import josh.address.model.AddressDAO;
import josh.address.model.AddressDTO;
import josh.address.model.InterAdressDAO;
import josh.cart.model.*;
import josh.member.model.InterMemberDAO;
import josh.member.model.MemberDAO;
import josh.member.model.MemberDTO;

public class CartController extends AbstractController{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.goBackURL(request);
		
		if(super.checkLogin(request)) { // 로그인이 된 상태라면
			
			HttpSession session = request.getSession();
			String userid = (String) session.getAttribute("userid");
				
			InterCartDAO mdao = new CartDAO();
			
			List<CartDTO> cartList = new ArrayList<>();
			
			cartList = mdao.selectCart(userid);
			
			MemberDTO user = new MemberDTO();
            // 일단 포인트 얼마 남았나 체크
            
            InterMemberDAO memdao = new MemberDAO();
            user = memdao.pointCheck(userid);
            
            InterAdressDAO adao = new AddressDAO();
            List<AddressDTO> adList = adao.selectAddress(userid);
            
            //System.out.println("adList 길이 ==> " + adList.size() );
			
			request.setAttribute("cartList", cartList);
			request.setAttribute("user", user);
			request.setAttribute("adList", adList);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/cart/cart.jsp");
				
		}
		else {
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/login/login.dream");
		}
		
	}
}
