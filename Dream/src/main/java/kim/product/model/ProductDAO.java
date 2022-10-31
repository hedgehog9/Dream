package kim.product.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kim.member.controller.MessageVO;
import kim.member.model.MemberVO;
import util.security.AES256;
import util.security.SecretMyKey;

public class ProductDAO implements InterProductDAO {
   
   
   private DataSource ds;  // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool) 이다.  
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   private AES256 aes;
   
   
   // 기본생성자
   public ProductDAO() {
      
      try {
         Context initContext = new InitialContext();
          Context envContext  = (Context)initContext.lookup("java:/comp/env");
          ds = (DataSource)envContext.lookup("jdbc/dream");
          
          aes = new AES256(SecretMyKey.KEY);
          // SecretMyKey.KEY 은 우리가 만든 비밀키이다.
          
      } catch(NamingException e) {
         e.printStackTrace();
      } catch(UnsupportedEncodingException e) {
         e.printStackTrace();
      }
   }
   
   // 사용한 자원을 반납하는 close() 메소드 생성하기 
   private void close() {
      try {
         if(rs != null)    {rs.close();    rs=null;}
         if(pstmt != null) {pstmt.close(); pstmt=null;}
         if(conn != null)  {conn.close();  conn=null;}
      } catch(SQLException e) {
         e.printStackTrace();
      }
   }

   @Override
   public ProductVO getDetail(String product_num) throws SQLException {
         
      ProductVO pvo = new ProductVO();
      
      try {
         
          conn = ds.getConnection();
          
          String sql = " select product_num, register_date, product_name, product_image, "
                + " category, detail_category, price, "
                + " discount_rate, gender, product_content "+
                " from tbl_product\n "+
                " where product_num = ? ";
          
          
          int product_num_int = Integer.parseInt(product_num);
          
          pstmt = conn.prepareStatement(sql);
          
          pstmt.setString(1 , product_num);
          
          rs = pstmt.executeQuery();
          
          if(rs.next()) {
             pvo.setProduct_num(rs.getInt(1));
             pvo.setRegister_date(rs.getString(2));
             pvo.setProduct_name(rs.getString(3));
             pvo.setProduct_image(rs.getString(4));
            
             pvo.setCategory(rs.getString(5));
             pvo.setDetail_category(rs.getString(6));
             pvo.setPrice(rs.getInt(7));
             pvo.setDiscount_rate(rs.getFloat(8));
            
             // system.out.println("할인율" +pvo.getDiscount_rate()); 
             pvo.setGender(rs.getString(9));
             pvo.setProduct_content(rs.getString(10));
             // tbl_product_stock 테이블에서 사이즈, 재고 알아오기
             String sql2 = " select product_num, product_size, size_cnt"+
                   " from tbl_product_stock "+
                  " where product_num = ?  ";
             pstmt = conn.prepareStatement(sql2);
             pstmt.setString(1 , product_num);
             
             // system.out.println("product_num"+product_num);
             
             rs = pstmt.executeQuery();
             List<String> sizeList = new ArrayList<>();
             List<String> cntList = new ArrayList<>();
             // 재고 VO 만들기 귀찮음 + 제품관련 호출할때마다 함수 두배로 만들기 힘드니 List 로 관리
             while(rs.next()) {
                sizeList.add(rs.getString(2));
                cntList.add(rs.getString(3));
             }
             pvo.setProduct_size(sizeList); 
             System.out.println(sizeList);
             pvo.setProduct_cnt(cntList);
             // 위 두 배열은 같은 인덱스 => 관계있는 값임
             // index0 ==> size=s  cnt=20
             // index1 ==> size=m  cnt=10
             // index2 ==> size=l  cnt=40
             
             String sql3 = "  select count(*) "+
                     " from tbl_like "+
                     " where product_num =? ";

                pstmt = conn.prepareStatement(sql3); 

               pstmt.setString(1,  product_num);
               rs = pstmt.executeQuery();
               if(rs.next()) {
                  pvo.setLikeCnt(rs.getInt(1));
               }
               else { //없는경우
                  pvo.setLikeCnt(0);
               }
             
             
             
          }// end of while------------------------
          
          
          
          
          
      } finally {
         close();
      }
      
      return pvo;
   }

   @Override
   public Map<String, ArrayList<String>> cnt_check(String productNum) throws SQLException {
      
      Map<String, ArrayList<String>> result = new HashMap<>();
      ArrayList<String> sizeArray = new ArrayList<>();
      ArrayList<String> cntArray = new ArrayList<>();
      // system.out.println(productNum);
      
      try {
         
      
         conn = ds.getConnection();
         String sql = " select product_num, product_size, size_cnt "+
               "                from tbl_product_stock "+
               "                 where product_num = ? "+
               "                 order by product_size asc ";
          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1 , productNum);
          rs = pstmt.executeQuery();
          while(rs.next()) {
             sizeArray.add(rs.getString(2));
             cntArray.add(rs.getString(3));

          }
          
          result.put("size", sizeArray);
          result.put("cnt", cntArray);

          return result;
      } finally {
         
         close();
      }
       
   }

   @Override
   public int nocartPurchaseUpdate(Map<String, String> paraMap) throws SQLException {
         int n = 0;
         int result = 0;
            try {
            
             
               
               conn = ds.getConnection();
               conn.setAutoCommit(false);
               boolean OK = true;
               // 재고테이블에서 (update) 수량삭제 ==> 필요한 것(product_num+size 복합키)
               // 반복문 돌려야한다람쥐
               int length = Integer.parseInt(paraMap.get("length"));
               // system.out.println("length"+length);
               System.out.println("length"+length);
               String sql = "";
               if(length == 0) {
                  OK= false;
               }
               
               for(int i=0; i<length; i++) {
                  sql = "   update tbl_product_stock set size_cnt = size_cnt- ? "+
                       "   where product_num = ? and PRODUCT_SIZE = ? ";
                      pstmt = conn.prepareStatement(sql);
                      
                      pstmt.setString(1, paraMap.get("cnt"+i));
                      pstmt.setString(2, paraMap.get("productNum"));
                      pstmt.setString(3, paraMap.get("size"+i));
                      n = pstmt.executeUpdate(); 
                      if(n!=1) {// 0개행 업데이트
                         // system.out.println("조져따리");
                    	  System.out.println("x");
                         OK = false;
                      }
               }
               
               
               
               
               
               for(int i=0; i<length; i++) {
                     
                  // 구매내역 테이블 insert (상품마다 insert 해야됨) product_num + size
                 sql = " insert into tbl_buylist "+
                        " (order_num, userid, product_num, buy_cnt, shipping,fk_address_num,product_size) \n "+
                        " values(seq_buylist.nextval,?,?,?,0,?,?) ";
                  pstmt = conn.prepareStatement(sql);
   
                  pstmt.setString(1, paraMap.get("userid"));
                  pstmt.setString(2, paraMap.get("productNum"));
                  pstmt.setString(3, paraMap.get("cnt"+i));
                  pstmt.setString(4, paraMap.get("address_num"));
                  pstmt.setString(5, paraMap.get("size"+i));
                        
                  
                  
                  n = pstmt.executeUpdate();
                  if(n!=1) {// 실패
                      // system.out.println("조져따리");
                	  System.out.println("x");
                      OK = false;
                   }
                  
               }
               if(!(paraMap.get("PointMinus").equalsIgnoreCase("0"))) {           
                  // 포인트 사용
                  
                  sql = " insert into tbl_point (point_num, userid, point_amount , status,  event_type,event_date) "+
                       " values(seq_point_num.nextval , ?,?, '차감',?,sysdate) ";
                  pstmt = conn.prepareStatement(sql);
                  // system.out.println("pointMinus" + paraMap.get("PointMinus"));
                  pstmt.setString(1, paraMap.get("userid"));
                  pstmt.setString(2, paraMap.get("PointMinus"));
                  pstmt.setString(3, paraMap.get("event_type"));
                  
                  n = pstmt.executeUpdate();
                  if(n!=1) {// 실패
                	  System.out.println("x");
                      
                      OK = false;
                   }
               }

               if(!paraMap.get("PointPlus").equalsIgnoreCase("0")) {
                 // 포인트 적립
                  sql = " insert into tbl_point (point_num, userid, point_amount , status,  event_type,event_date) "+
                        " values(seq_point_num.nextval , ?,?, '적립',?,sysdate) ";
                  pstmt = conn.prepareStatement(sql);

                  pstmt.setString(1, paraMap.get("userid"));
                  pstmt.setString(2, paraMap.get("PointPlus"));
                  pstmt.setString(3, paraMap.get("event_type"));
                  
                  n = pstmt.executeUpdate();
                  if(n!=1) {// 실패
                      // system.out.println("조져따리");
                	  System.out.println("x");
                      OK = false;
                   }

               }
                              
               // 여기까지 왔는데도 OK 가  true 면 전부 1행씩 잘 진행된 것 
               if(OK) {
                  conn.commit(); // 커밋함
                  result = 1; // n=1 맞춰줌 사실 안맞춰도 1인데 대충
              }
               else {
                  // system.out.println("환자발생!");
                  result=-1;
                  conn.rollback();
               }

            } catch (Exception e) {
               e.printStackTrace();
               conn.rollback();
            } finally {
              
              conn.setAutoCommit(true); 
               close();
            }
            
            
            return result;

      
      
      
      
      
   }

   @Override
   public int UpdateProduct(Map<String, String> paraMap) throws SQLException {
      int n = 0;

         try {
     
            conn = ds.getConnection();
           
            String sql = " update tbl_product "
                  + " set PRODUCT_NAME = ? , "
                  + " PRODUCT_IMAGE = ? , "
                  + " PRICE = ? , DISCOUNT_RATE = ? , "
                  + " PRODUCT_CONTENT = ? "
                  + " where product_num = ? ";       
            pstmt = conn.prepareStatement(sql);
                   
            pstmt.setString(1, paraMap.get("product_name"));
            pstmt.setString(2, paraMap.get("img_list"));
            pstmt.setString(3, paraMap.get("price"));
            pstmt.setString(4, paraMap.get("discount_rate"));
           
          
            pstmt.setString(5, paraMap.get("product_content"));
       
            pstmt.setString(6, paraMap.get("product_num"));
            n = pstmt.executeUpdate(); 
            
            if(n!=1) {// 0개행 업데이트
               // system.out.println("업데이트가 되지 않았습니다.");
              
            }
            
            
            
            
        

         } catch (Exception e) {
            e.printStackTrace();
            
         } finally {
           
       
            close();
         }
         
         
         return n;


   }

   @Override
   public int ilikethis(String productnum, String loginuserid) throws SQLException {
      int result = 0;

      try {
         conn = ds.getConnection();
         String sql = " select * from tbl_like "+
               " where userid= ? and product_num= ? ";
          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1 , loginuserid);
          pstmt.setString(2 , productnum);
          
          rs = pstmt.executeQuery();
          if(rs.next()) {
            result = 1;
          }
          
      
          return result;
      } finally {
         
         close();
      }
   }

   @Override
   public Map<String, Object> getAllMessage(String loginuserid, String type) throws SQLException {
      
      Map<String, Object> paraMap = new HashMap<String, Object>();
      ArrayList<MessageVO> messageList = new ArrayList<MessageVO>();

      
      try {

         conn = ds.getConnection();
         // receive_list 채우기
         String where = "fk_Recipient_userid";
         String where2 = "Recipient_delete";
         
         if(!(type.equalsIgnoreCase("receieve"))) {
            where = "fk_sender_userid";      
            where2 = "sender_delete";
         }

         
         
         String sql = " select messageno, fk_sender_userid, fk_Recipient_userid, title, Contents, Shipping, read_check, sender_delete, Recipient_delete "+
               " from tbl_message "+
               " where  "+ where + " = '"+loginuserid +"' and "+where2+" = 0 "
              + " order by messageno desc ";
          pstmt = conn.prepareStatement(sql);
         
          // system.out.println(sql);
          
          rs = pstmt.executeQuery();
          while(rs.next()) {
            MessageVO mvo = new MessageVO();
            mvo.setMessageno(rs.getString(1));
            mvo.setFk_sender_userid(rs.getString(2));
            mvo.setFk_Recipient_userid(rs.getString(3));
            mvo.setTitle(rs.getString(4));
            mvo.setContents(rs.getString(5));
            mvo.setShipping(rs.getString(6));
            mvo.setRead_check(rs.getString(7));
            mvo.setSender_delete(rs.getString(8));
            mvo.setRecipient_delete(rs.getString(9));
            
            messageList.add(mvo);
          }
          
      paraMap.put("messageList", messageList);    
          
                
      return paraMap;
      
      
      
      } finally {
         
         close();
      }
   }

@Override
public int deleeteproduct(String productNum) throws SQLException {
	int result = 0;
	
	
	 try {
	     System.out.println("productNum"+productNum);
         conn = ds.getConnection();
        
         String sql = " delete from tbl_product "+
        		 " where PRODUCT_NUM = ? "; 
         
         pstmt = conn.prepareStatement(sql);
                
         pstmt.setString(1, productNum);
        
         result = pstmt.executeUpdate(); 
         
         if(result!=1) {// 0개행 업데이트
            // system.out.println("업데이트가 되지 않았습니다.");
        	
         }
         
         
         
         
     

      } catch (Exception e) {
         e.printStackTrace();
         
      } finally {
    	 
    
         close();
         return result;
      }
}

}



