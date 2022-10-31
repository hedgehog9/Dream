package jjy.admin.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.controller.AbstractController;
import jjy.product.model.InterProductDAO;
import jjy.product.model.ProductDAO;

public class ProductRegister extends AbstractController{
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
		String method = request.getMethod(); 
		
			if("GET".equalsIgnoreCase(method)) { // method가 "GET"일 때 
				
				HttpSession session = request.getSession();
				String userid = (String) session.getAttribute("userid");
				
				// GET 방식일때 먼저 카테고리 찍어주기
				InterProductDAO pdao = new ProductDAO();
				List<String> categoryList = pdao.getCagegory();
				request.setAttribute("categoryList", categoryList);
				
				if(!"admin".equalsIgnoreCase(userid)) {	//get요청을 한 사용자가 관리자로 로그인중이 아니라면
					super.setRedirect(true);
					super.setViewPage(request.getContextPath()+"/index.dream");
				}
				else {	//관리자로 get요청을 하였다면
					super.setRedirect(false);
					super.setViewPage("/WEB-INF/view/admin/ad_product/productRegister.jsp");
				}
				
			}// end of if("GET".equalsIgnoreCase(method)) { // method가 "GET"일 때 -----------------------------------------------------
			
			else {	// method가 "POST"일 때 
				
				InterProductDAO pdao = new ProductDAO();
				int pnum = pdao.getPnumOfProduct();
				HttpSession session = request.getSession();
				
				MultipartRequest mtrequest = null;
				
				ServletContext svlCtx =  session.getServletContext();
				String uploadFileDir = "C:\\Users\\sist\\git\\Dream\\Dream\\src\\main\\webapp\\images\\제품이미지";
				
				try {
					mtrequest = new MultipartRequest(request, uploadFileDir, 10*1024*1024, "UTF-8", new DefaultFileRenamePolicy() );
				} catch(IOException e) {
					
					request.setAttribute("message", "업로드 되어질 경로가 잘못되었거나 또는 최대용량 10MB를 초과했으므로 파일업로드 실패함!!");
		            request.setAttribute("loc", request.getContextPath()+"/admin/productRegister.dream"); 
		              
		            super.setViewPage("/WEB-INF/view/msg.jsp");
		            return; // 종료
				}
				// 제품 등록시 form 태그에서 입력한 값들 얻어오기
				String category = mtrequest.getParameter("category"); // 카테고리
				
				switch (category) {
					case "1":
						category = "침구류";
						break;
					case "2":
						category = "조명";
						break;
					case "3":
						category = "파자마";
						break;
					case "4":
						category = "수면용품";
						break;
				}
				
				String gender = mtrequest.getParameter("gender"); // 성별
				
				switch (gender) {
					case "no":
						gender = "없음";
						break;
					case "man":
						gender = "남";
						break;
					case "woman":
						gender = "여";
						break;
				}
				
				String detail_category = mtrequest.getParameter("detail_category"); // 소 카테고리 
				String product_image = "";
				for(int i = 0; i<3; i++) {
					if(mtrequest.getFilesystemName("attach"+i) != null) {
						product_image +=  mtrequest.getFilesystemName("attach"+i);
						product_image += ",";
					}
				}
				
				product_image = product_image.substring(0, product_image.length()-1);
				
	
				String product_name = mtrequest.getParameter("product_name"); // 수량 
				String price = mtrequest.getParameter("saleprice");  // 판매가격 
				
				String discount_rate = mtrequest.getParameter("discountrate"); // 할인율 0 ~ 100
				
				if(discount_rate == null || "".equals(discount_rate) ) {
					discount_rate = "0";
				}
				float i_discount_rate = Float.parseFloat(discount_rate)/100;
				
				discount_rate = String.valueOf(i_discount_rate);
				
				
				String product_content = mtrequest.getParameter("pcontent");   // 제품 상세 설명
				
				product_content = product_content.replaceAll("<", "&lt;");     // < 치환
				product_content = product_content.replaceAll(">", "&gt;");     // > 치환
				product_content = product_content.replaceAll("\r\n", "<br/>"); // 엔터 치환
		        
				
				Map<String,String> productMap = new HashMap<>();
				
				String size_s ="0";
				String size_m ="0";
				String size_l ="0";
				
				String product_cnt ="0";
				
				if("파자마".equals(category)) {
					// 카테고리가 파자마인경우 사이즈별 수량 각각 저장
					size_s = mtrequest.getParameter("size_s");
					size_m = mtrequest.getParameter("size_m");
					size_l = mtrequest.getParameter("size_l");
	
					productMap.put("size_s", size_s);          // 사이즈 s
					productMap.put("size_m", size_m);          // 사이즈 m
					productMap.put("size_l", size_l);          // 사이즈 l
				}
				else {
					// 카테고리가 파자마 이외의 경우 수량 한개만 저장
					product_cnt = mtrequest.getParameter("pqty");
					productMap.put("product_cnt", product_cnt);          // 제품수량
				}
				
				productMap.put("pnum", String.valueOf(pnum)); // 제품번호
				productMap.put("product_name", product_name);        // 제품명
				productMap.put("product_image", product_image); 	 // 제품이미지
				productMap.put("category", category);   			 // 대카테고리
				productMap.put("detail_category", detail_category);  // 소카테고리
				productMap.put("price", price);                      // 가격
				productMap.put("gender", gender);                    // 성별
				productMap.put("discount_rate", discount_rate);      // 할인율
				productMap.put("product_content", product_content);  // 제품상세내용
				
				int result = 0;
				
				if(  !("0".equals(size_s) && "0".equals(size_m) && "0".equals(size_l) ) || !"0".equals(product_cnt) ) {
					result = pdao.registProduct(productMap);
				}
				
				String message ="";
				String loc ="";
				
				if(result == 1) {
					message = "상품등록 성공";
					loc = request.getContextPath()+"/product/shop.dream";
				}
				else {
					message = "상품등록 실패";
					loc = request.getContextPath()+"/product/shop.dream";
				}
				
				request.setAttribute("message", message);
		        request.setAttribute("loc", loc);
		        
		        super.setRedirect(false);
		        super.setViewPage("/WEB-INF/view/msg.jsp");
				
			} // post 방식일때 끝{}---------------------------------------------------------------------------
		}catch(Exception e) {
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.dream");
		}
	}
}
