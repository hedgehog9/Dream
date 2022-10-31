package kim.cart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import common.controller.AbstractController;
import kim.member.model.InterMemberDAO;
import kim.member.model.MemberDAO;
import kim.product.model.InterProductDAO;
import kim.product.model.ProductDAO;

public class UpdateCart extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if( super.checkLogin(request) ) {
			// 로그인을 했으면

			String userid = request.getParameter("userid");
		
			HttpSession session = request.getSession();
			String loginuserid = (String) session.getAttribute("userid");
			// form 으로 보내준 userid 와 현재 로그인한 session 의 userid 값이 동일한지 확인해야댐
			if( loginuserid.equals(userid) ) {
				
				/*
				System.out.println(request.getParameter("sizeArray"));
				System.out.println(request.getParameter("cntArray"));
				System.out.println("cartupdate 넘어온 값 확인!");
				*/
				Map<String, String> paraMap = new HashMap<>();
				 
				int length = Integer.parseInt(request.getParameter("length")); 
				String productNum = request.getParameter("productNum");
				
				ArrayList<String>sizeArr = new ArrayList<String>();
				ArrayList<String>cntArr = new ArrayList<String>();
				
//				여기서 부터 따온거
				JSONArray sizeArray = new JSONArray(request.getParameter("sizeArray"));
				JSONArray cntArray = new JSONArray(request.getParameter("cntArray"));  
				
				//System.out.println(sizeArray);
				
				for (int i=0; i < sizeArray.length(); i++) {
					 sizeArr.add(sizeArray.getString(i)); 
				}
				for (int i=0; i < cntArray.length(); i++) {
					cntArr.add(cntArray.getString(i)); 
				}
				/*
				System.out.println("sizeArr"+sizeArr);
				System.out.println("cntArr"+cntArr);
			 	*/
				for(int i=0; i<length ;i++) {
					paraMap.put("size"+i, sizeArr.get(i));
					paraMap.put("cnt"+i, cntArr.get(i));
					
				}
				paraMap.put("userid",userid);
				paraMap.put("productNum",productNum);
				paraMap.put("length",request.getParameter("length"));
				
				InterProductDAO pdao = new ProductDAO();
				
				// 재고 리스트 가져오기
				Map<String, ArrayList<String>> result = pdao.cnt_check(productNum);
				
				ArrayList<String> stockSizeArr = result.get("size");
				ArrayList<String> stockCntArr = result.get("cnt");
				
				// ajax 로 가져온 주문량과 비교하여 주문 가능한 상태인지 체크
				boolean isItOk = true;
				
				outer:
				for(int i=0; i<length ;i++) {
					String sizeOption = sizeArr.get(i);
					// System.out.println(sizeOption+"sizeOption");
					int cntOption = Integer.parseInt(cntArr.get(i));
					for(int n=0; n<stockSizeArr.size();n++) {
						// 사이즈 옵션과 같은 사이즈가 나올때의 인덱스를 구할거임
						if(sizeOption.equals(stockSizeArr.get(n))) {
							// 그 인덱스의 재고량을 체크해서 비교
							if(cntOption > Integer.parseInt(stockCntArr.get(n))){
								// 주문량이 더 많다면
								isItOk = false;
								// System.out.println("주문량이 더 많아서 주문이 안됩니다!!");
								break outer;
							}	
						}	
					}
				} // out of outer 
				
				if(isItOk) {
					// 재고가 확인 되었다면 카트에 추가해주자
					
					InterMemberDAO mdao = new MemberDAO();
					// System.out.println("들어간다");
					int n = mdao.updateCart(paraMap);
					// System.out.println("나온다");
					if(n == -1) {
						// 실패했다면
						isItOk = false;
					}
					
				}
				JSONObject jsonObj = new JSONObject();
				
				jsonObj.put("isItOk", isItOk);    
				
				String json = jsonObj.toString(); 
				request.setAttribute("json", json);
				
			//	System.out.println(json);
				
		   //	super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/jsonview.jsp");
				
				
			
				
			}
			else{
				// 로그인한 사용자가 다른 사용자의 장바구니에 추가하려고 시도하는 경우 
				String message = "로그인한 유저의 장바구니만 추가할 수 있습니다!";
				String loc = "javascript:history.back()";
				
				request.setAttribute("message", message);
				request.setAttribute("loc", loc);
				
			//	super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/msg.jsp");
				return;
			}
		 
		 
		
		}
		else {
			// 로그인을 안 했으면
			String message = "로그인을 한 이후에 이용할 수 있습니다!";
			String loc = "javascript:history.back()";
			// 뒤로보내거나 로그인창으로
			
			request.setAttribute("message", message);
			
			request.setAttribute("loc", loc);
			
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
		}

	}


}
