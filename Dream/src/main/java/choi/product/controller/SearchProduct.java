package choi.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import choi.member.model.InterMemberDAO;
import choi.member.model.MemberDAO;
import choi.product.model.InterProductDAO;
import choi.product.model.ProductDAO;
import common.controller.AbstractController;

public class SearchProduct extends AbstractController{

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   super.goBackURL(request);
      String method = request.getMethod(); 
      
      if("GET".equalsIgnoreCase(method)) { // method가 "GET"일 때 
    	 HttpSession session = request.getSession();
  		 String userid = (String) session.getAttribute("userid");
  		 
         InterProductDAO pdao = new ProductDAO();
         //인기검색어 기준: 기준시간으로부터 일주일전까지의 검색어 기록중 제일 많이 검색된 검색어 10개를 가져온다.
         List<String> keywordList = pdao.selectBestKeyword();       //인기검색어 리스트 알아오기
         List<String> recommendList = pdao.selectRecommendKeyword();   //추천검색어 리스트 알아오기
         
         if(checkLogin(request)) {	//로그인중이라면
        	 List<String> personalizedList = pdao.selectpersonalizedKeyword(userid);   //맞춤검색어 리스트 알아오기
        	 InterMemberDAO mdao = new MemberDAO();
        	 String username = mdao.select_username(userid);
        	 request.setAttribute("personalizedList",personalizedList);
        	 request.setAttribute("username",username);
         }
         
         String currentHour = pdao.selectCurrentHour();             //현재시간에서 월,일, 시간 알아오기(기준시간)
         List<String> keywordList1 = new ArrayList<>();               //인기검색어 리스트 나누기 1
         List<String> keywordList2 = new ArrayList<>();               //인기검색어 리스트 나누기 2
         
         int size = keywordList.size();
         int cnt=1;
         if(size==10) {   //인기검색어가 10개인 경우
            for(int i=0;i<=size-1;i++) {
               if(cnt <= 5) {
                  keywordList1.add(keywordList.get(i));
               }
               else {
                  keywordList2.add(keywordList.get(i));
               }
               cnt++;
            }//end of for---
         }
         else if(0<size && size<6){
            for(int i=0;i<=size-1;i++) {
               keywordList1.add(keywordList.get(i));
            }//end of for---
         }
         else {
            for(int i=0;i<=4;i++) {
               keywordList1.add(keywordList.get(i));
            }//end of for---
            for(int i=5;i<=size-1;i++) {
               keywordList2.add(keywordList.get(i));
            }//end of for---
         }
         
         if(keywordList1.size()>0) {
            request.setAttribute("keywordList1", keywordList1);
         }
         if(keywordList2.size()>0) {
            request.setAttribute("keywordList2", keywordList2);
         }
         request.setAttribute("recommendList", recommendList);
         request.setAttribute("currentHour", currentHour);
         
         super.setRedirect(false);
         super.setViewPage("/WEB-INF/view/product/searchProduct.jsp");
      }
      else {   // method가 "POST"일 때 
         
      }
      
   }
}