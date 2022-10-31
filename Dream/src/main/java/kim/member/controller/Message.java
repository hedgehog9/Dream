package kim.member.controller;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import common.controller.AbstractController;
import kim.product.model.InterProductDAO;
import kim.product.model.ProductDAO;
import kim.product.model.ProductVO;

public class Message extends AbstractController {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub
         
         String method = request.getMethod();
         
         // System.out.println(method);
         HttpSession session = request.getSession();
         String loginuserid = (String) session.getAttribute("userid");

         if("GET".equalsIgnoreCase(method)) {   //method가 "GET" 일 때
            InterProductDAO pdao = new ProductDAO();
            // product_num 은 바뀔 수 있음
      
            
            try {
                     
               String type = "receieve";
               if(request.getParameter("type")!=null && request.getParameter("type").trim() != "") {      //파라미터 type가 not empty라면!
                  type = request.getParameter("type").trim();
               }
               int page= 1;
               if(request.getParameter("p")!=null && request.getParameter("p").trim() != "") {      //파라미터 page가 not empty라면!
                  page = Integer.parseInt(request.getParameter("p").trim());
               }
         
               Map<String, Object> paraMap = new HashMap<String, Object>();
            
               paraMap = pdao.getAllMessage(loginuserid, type);
      
               ArrayList<MessageVO> messageList = (ArrayList<MessageVO>)paraMap.get("messageList"); 
               int total_cnt = messageList.size();
               //System.out.println("total_cnt"+total_cnt);
               
               
               float display_cntf = 10f;                                 //한 페이지당 보여줄 게시물 수 float형
               int display_cnt = (int)display_cntf;                     //한 페이지당 보여줄 게시물 수 int형
               int display_page = 5;                                 //한번에 보여줄 페이지번호의 갯수 int형
               float display_pagef = 5f;                          //한번에 보여줄 페이지번호의 갯수 float형
               int totalPage = (int)Math.ceil(total_cnt/ display_cntf);  //총 게시물수 / 한페이지당 보여줄 게시물 수 를 올림
               
               
               if(page<=1) {   //페이지가 1보다 작다면
                   page = 1;
                }
                else if(page>=totalPage) {   //페이지가 총페이지보다 크다면
                   page = totalPage;
                }
               
               int endPage = (int)(((page-1)/display_page)+1) * display_page;   //하단 페이지번호 마지막번호
               
               if(totalPage<endPage){
                   endPage = totalPage;
               }
               
                int startPage = (int)((page-1)/display_page)*display_page+1;   //하단 페이지번호 시작번호
                if(totalPage<startPage) {
                   startPage = totalPage;
                }
                
                
                boolean last_display_page = false;                        // 보고있는 페이지가 마지막 페이지번호쪽 인지 여부검사
                int a = (int)Math.ceil(totalPage / display_pagef);   //총페이지 / 한페이지당 보여줄 페이지 수
                int b = (int)Math.ceil(page / display_pagef);      //현재페이지번호 / 한 페이지당 보여줄 페이지 수
                /*
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("a : "+ a);
                System.out.println("b : "+ b);
                */
                if(a == b) {
                   last_display_page = true;
                }
                //  (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
                
                // 여기서 페이징 처리
                int position = page*display_cnt - (display_cnt);
                List<MessageVO> printmessageList = new ArrayList<MessageVO>();
                int lastIndex = total_cnt;
                //System.out.println(total_cnt);
                //System.out.println(lastIndex);
                if(position+9 > lastIndex) {// 총 게시글 보다 많은 수추출해야되면
                   // System.out.println("lastIndex"+lastIndex);
                  printmessageList = messageList.subList(position, lastIndex);                          
                }
                else {
                   printmessageList = messageList.subList(position, position+10);        
                }
                /*
                System.out.println("printmessageList"+printmessageList);
                
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("현재보고있는 페이지 : "+ page);
                System.out.println("한 페이지당 보여줄 게시물 수 : "+ display_cnt);
                System.out.println("한번에 보여줄 페이지번호의 갯수 : "+ display_page);
                System.out.println("총 게시물 수 : "+ total_cnt);
                System.out.println("총페이지 : "+ totalPage);
                System.out.println("아래 시작페이지 : "+ startPage);
                System.out.println("아래 끝페이지 : "+ endPage);
                System.out.println("현재 페이지가 마지막 페이지단인지 여부 : "+ last_display_page);
               */
                
                
               
               
               JSONObject jsonObj = new JSONObject();  
                jsonObj.put("printmessageList", printmessageList);
                jsonObj.put("cnt", printmessageList.size());
                
                jsonObj.put("display_cnt", display_cnt);
                jsonObj.put("display_page", display_page);
                jsonObj.put("page", page);
                jsonObj.put("total_cnt", total_cnt);   
                jsonObj.put("totalPage", totalPage);   
                jsonObj.put("startPage", startPage);   
                jsonObj.put("endPage", endPage);
                jsonObj.put("last_display_page",last_display_page);
               
                request.setAttribute("json", jsonObj);
                //System.out.println(jsonObj);
               
                
                if(request.getParameter("p") == null && request.getParameter("type") == null) {
                   request.setAttribute("printmessageList", printmessageList);
                   request.setAttribute("cnt", printmessageList.size());
                   
                   request.setAttribute("display_cnt", display_cnt);
                   request.setAttribute("display_page", display_page);
                   request.setAttribute("page", page);
                   request.setAttribute("total_cnt", total_cnt);   
                   request.setAttribute("totalPage", totalPage);   
                   request.setAttribute("startPage", startPage);   
                   request.setAttribute("endPage", endPage);
                   request.setAttribute("last_display_page",last_display_page);
                   
                   
                   
                   super.setRedirect(false);
                  super.setViewPage("/WEB-INF/view/member/message.jsp");
                  return;
                  
               }
       
               super.setRedirect(false);
               super.setViewPage("/WEB-INF/view/jsonview.jsp");
               // super.setViewPage("/WEB-INF/view/jsonview.jsp");
               
            
            } catch (Exception e) {   //모든 exception에 대한 처리
               e.printStackTrace();
               super.setRedirect(true);
               super.setViewPage("/WEB-INF/view/jsonview.jsp");
            }//end of try-catch-----------------
            
         }
         else {   //method가 "POST" 일 때 나띵
            
         }
         
               
      
         
         
         
         
         
         
         
         
         
      }
      
} 

