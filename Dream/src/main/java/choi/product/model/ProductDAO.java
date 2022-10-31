package choi.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO implements InterProductDAO{
	// DBCP
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection pool)이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	// 생성자 
	public ProductDAO() {
		
		try {
			// connection pool 을 위한 작업
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/dream");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	// 사용한 자원을 반납하는 close() 메소드 생성하기
	private void close() {
		
		try {
			if(rs != null)    { rs.close();    rs = null; }
			if(pstmt != null) { pstmt.close(); pstmt = null; }
			if(conn != null)  { conn.close();  conn = null; }
		}catch(SQLException e){
			e.printStackTrace();
		}// end of try ~ catch()---------------------------------
		
	}
	
	
	//필터에 맞는 총 게시물 수를 알아오는 메소드
	@Override
	public int cntAllProduct(Map<String,String> paraMap) throws SQLException {
		String category = paraMap.get("category");
		String detail_category = paraMap.get("detail_category");
		String bestyn = paraMap.get("bestyn");
		String gender = paraMap.get("gender");
		String start_price = paraMap.get("start_price");
		String end_price = paraMap.get("end_price");
		int total_cnt = 1;
		try {
			conn = ds.getConnection();
			
			String sql =  " select count(*) "
					    + " from tbl_product "
					    + " where 1=1 ";
			if(category != null && !category.equals("전체")) {
				sql += " and category = '"+category +"'";
			}
			if(detail_category != null && !detail_category.equals("전체")) {
				sql += " and detail_category = '"+ detail_category + "'";
			}
			if(bestyn != null && bestyn.equalsIgnoreCase("y")) {
				sql += " and bestyn = '"+ bestyn + "'";
			}
			if(gender != null && !gender.equals("없음")){
				sql += " and gender = '"+gender + "'";
			}
			if(start_price != null && !start_price.equals("") && end_price != null && !end_price.equals("")) {
				sql += " and price - (price * discount_rate) between '"+Integer.parseInt(start_price)+"' and '"+Integer.parseInt(end_price)+"'";
			}
								
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total_cnt = rs.getInt(1);
			}
			
		} finally {
			close();
		}
		return total_cnt;
	}
	
	
	
	//페이징처리를해서, 제품들을 셀렉트해오는 메소드
	@Override
	public List<ProductDTO> selectAllProduct(Map<String, String> paraMap) throws SQLException {
		List<ProductDTO> productList = new ArrayList<>();
		String category = paraMap.get("category");
		String detail_category = paraMap.get("detail_category");
		String bestyn = paraMap.get("bestyn");
		String gender = paraMap.get("gender");
		String start_price = paraMap.get("start_price");
		String end_price = paraMap.get("end_price");
		String sort = paraMap.get("sort");
		String userid = paraMap.get("userid");
		try {
			conn = ds.getConnection();
			String sql = "";
			sql += " select * "
				 + " from "
				 + " ( "
				 + " select V.*, rownum R "
				 + " from "
				 + " ( ";
			if(userid != null) {	//로그인중일 경우
				sql += " select C.*, "
			         + " nvl(D.product_like_cnt,0) product_like_cnt "
					 + " from ( ";
			}
			
	        sql += " select A.* "
			     + "      , nvl(B.like_cnt,0) like_cnt "
			     + " from  "
			     + " ( "
			     + " select product_num "
			     + "     , register_date "
			     + "     , product_name "
			     + "     , product_image "
			     + "     , category "
			     + "     , detail_category "
			     + "     , price "
			     + "	 , (discount_rate*100) discount_rate "
			     + "     , price - (price * discount_rate) real_price  "
			     + "     , gender "
			     + "     , bestyn "
			     + " from tbl_product "
			     + " where 1=1 ";
			     if(category != null && !category.equals("전체")) {
						sql += " and category = '"+category +"' ";
					}
					if(detail_category != null && !detail_category.equals("전체")) {
						sql += " and detail_category = '"+ detail_category + "' ";
					}
					if(bestyn != null && bestyn.equalsIgnoreCase("y")) {
						sql += " and bestyn = '"+ bestyn + "' ";
					}
					if(gender != null && !gender.equals("없음")) {
						sql += " and gender = '"+gender + "' ";
					}
					if(start_price != null && !start_price.equals("") && end_price != null && !end_price.equals("")) {
						sql += " and price - (price * discount_rate) between "+Integer.parseInt(start_price)+" and "+Integer.parseInt(end_price);
					}
		    sql += " ) A "
				 + " left join "
				 + " ( "
				 + " select product_num, "
				 + "        count(*) as like_cnt "
				 + " from tbl_like "
		         + " group by product_num"
		         + " ) B "
				 + " on A.product_num = B.product_num ";
		    
			
			if(userid != null) {
				sql += " ) C "
					 + " left join "
					 + " ("
					 + " select product_num, "
					 + "        count(*) as product_like_cnt "	//product_like_cnt는 내가 좋아요를 눌렀는지 확인용?
					 + " from tbl_like"
					 + " where userid = '" + userid + "'"
					 + " group by product_num "
					 + " ) D "
					 + " on C.product_num = D.product_num ";
		    }
			
			if(sort != null){
				switch (sort) {
				    case "인기순":
				    	sql += "order by like_cnt desc";
				    	break;
					case "최신순":	//등록일자 내림차순
						sql += " order by register_date desc ";
						break;
					case "최저가순":	//가격
						sql += " order by real_price ";
						break;
					default:
						break;
				}//end of switch-case
			}    
			
			sql += " ) V "
				 + " ) T "
			     + " where R between (?*?)-(?-1) and (?*?) ";
//					(조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(paraMap.get("page")));
			pstmt.setInt(2,Integer.parseInt(paraMap.get("display_cnt")));
			pstmt.setInt(3,Integer.parseInt(paraMap.get("display_cnt")));
			pstmt.setInt(4,Integer.parseInt(paraMap.get("page")));
			pstmt.setInt(5,Integer.parseInt(paraMap.get("display_cnt")));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO pdto = new ProductDTO();	//dto 하나 생성
				pdto.setProduct_num(rs.getInt("product_num") );  
				pdto.setRegister_date(rs.getString("register_date") );  
				pdto.setProduct_name(rs.getString("product_name") );   
				pdto.setProduct_image(rs.getString("product_image") );
				pdto.setCategory(rs.getString("category") );
				pdto.setDetail_category(rs.getString("detail_category") );
				pdto.setPrice(rs.getInt("price") );
				pdto.setDiscount_rate(rs.getInt("discount_rate") );
				pdto.setGender(rs.getString("gender") );
				pdto.setBestyn(rs.getString("bestyn") );
				pdto.setReal_price(rs.getInt("real_price") );
				pdto.setLike_cnt(rs.getInt("like_cnt"));
				if(userid != null) {
					pdto.setProduct_like_cnt(rs.getInt("product_like_cnt"));
				}
				
				productList.add(pdto);	//NoticeDTO들만 들어갈 수 있는 리스트에 담기
			}
		} finally {
			close();
		}
		return productList;
			
	}

	
	
	
	// 키워드를 입력받아서, 키워드로 검색한 총게시물의 수를 알아오는 메소드
	@Override
	public int cntkeywordProduct(String keyword) throws SQLException {
		int total_cnt = 1;
		
		try {
			conn = ds.getConnection();
			
			String sql =  " select count(*) "
					    + " from tbl_product "
					    + " where product_num = ? or "
					    + "       product_name like '%'|| ? || '%' or"
					    + "       category = ? or "
					    + "       detail_category = ? ";
								
			pstmt = conn.prepareStatement(sql);
			try {
				pstmt.setInt(1,Integer.parseInt(keyword));
			}catch(NumberFormatException e) {
				pstmt.setString(1, "");
			}//end of try-catch--
			pstmt.setString(2,keyword);
			pstmt.setString(3,keyword);
			pstmt.setString(4,keyword);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total_cnt = rs.getInt(1);
			}
			
		} finally {
			close();
		}
		return total_cnt;
	}
	
	
	
	
	
	
	
	// 키워드,페이지번호,한페이지당 보여줄 게시물수,유저아이디를 맵으로 입력받아서, 키워드로 검색한 게시물의 리스트들을 받아오는 메소드
	@Override
	public List<ProductDTO> selectKeywordProduct(Map<String, String> paraMap) throws SQLException {
		List<ProductDTO> productList = new ArrayList<>();
		String userid = paraMap.get("userid");
		
		try {
			conn = ds.getConnection();
			String sql = "";
			sql += " select * "
				 + " from "
				 + " ( "
				 + " select V.*, rownum R "
				 + " from "
				 + " ( ";
			if(userid != null) {	//로그인중일 경우
				sql += " select C.*, "
			         + " nvl(D.product_like_cnt,0) product_like_cnt "
					 + " from ( " ;
			}
			
	        sql += " select A.* "
			     + "      , nvl(B.like_cnt,0) like_cnt "
			     + " from  "
			     + " ( "
			     + " select product_num "
			     + "     , register_date "
			     + "     , product_name "
			     + "     , product_image "
			     + "     , category "
			     + "     , detail_category "
			     + "     , price "
			     + "	 , (discount_rate*100) discount_rate "
			     + "     , price - (price * discount_rate) real_price  "
			     + "     , gender "
			     + "     , bestyn "
			     + " from tbl_product "
			     + " where product_num = ? or "
			     + "	   product_name like '%'|| ? || '%' or "
			     + "	   category = ? or "
			     + "	   detail_category = ? "
		         + " ) A "
				 + " left join "
				 + " ( "
				 + " select product_num, "
				 + "        count(*) as like_cnt "
				 + " from tbl_like "
		         + " group by product_num"
		         + " ) B "
				 + " on A.product_num = B.product_num ";
		    
			
			if(userid != null) {
				sql += " ) C "
					 + " left join "
					 + " ("
					 + " select product_num, "
					 + "        count(*) as product_like_cnt "	//product_like_cnt는 내가 좋아요를 눌렀는지 확인용?
					 + " from tbl_like"
					 + " where userid = '" + userid + "'"
					 + " group by product_num "
					 + " ) D "
					 + " on C.product_num = D.product_num ";
		    }
			
			
			sql += " ) V "
				 + " ) T "
			     + " where R between (?*?)-(?-1) and (?*?) ";
//					(조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
			pstmt = conn.prepareStatement(sql);
			try {
				pstmt.setInt(1,Integer.parseInt(paraMap.get("keyword")));
			}catch(NumberFormatException e) {
				pstmt.setString(1, "");
			}//end of try-catch--
			pstmt.setString(2,paraMap.get("keyword"));
			pstmt.setString(3,paraMap.get("keyword"));
			pstmt.setString(4,paraMap.get("keyword"));
			
			
			pstmt.setInt(5,Integer.parseInt(paraMap.get("page")));
			pstmt.setInt(6,Integer.parseInt(paraMap.get("display_cnt")));
			pstmt.setInt(7,Integer.parseInt(paraMap.get("display_cnt")));
			pstmt.setInt(8,Integer.parseInt(paraMap.get("page")));
			pstmt.setInt(9,Integer.parseInt(paraMap.get("display_cnt")));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO pdto = new ProductDTO();	//dto 하나 생성
				pdto.setProduct_num(rs.getInt("product_num") );  
				pdto.setRegister_date(rs.getString("register_date") );  
				pdto.setProduct_name(rs.getString("product_name") );   
				pdto.setProduct_image(rs.getString("product_image") );
				pdto.setCategory(rs.getString("category") );
				pdto.setDetail_category(rs.getString("detail_category") );
				pdto.setPrice(rs.getInt("price") );
				pdto.setDiscount_rate(rs.getInt("discount_rate") );
				pdto.setGender(rs.getString("gender") );
				pdto.setBestyn(rs.getString("bestyn") );
				pdto.setReal_price(rs.getInt("real_price") );
				if(userid != null) {
					pdto.setProduct_like_cnt(rs.getInt("product_like_cnt"));
				}
				
				productList.add(pdto);	//NoticeDTO들만 들어갈 수 있는 리스트에 담기
			}
		} finally {
			close();
		}
		return productList;
			
	
	}

	@Override
	public int insertKeyword(Map<String, String> keywordMap) throws SQLException {
		int n = 0;
		String userid = keywordMap.get("userid");
		try {
			conn = ds.getConnection();
			String sql = "";
			if(userid != null) {
				sql =  " insert into tbl_search_keyword(search_num, keyword, search_ip, userid) "
						+ " values(seq_search_num.nextval, ?, ?, ?)";
			}
			else {
				sql =  " insert into tbl_search_keyword(search_num, keyword, search_ip) "
					 + " values(seq_search_num.nextval, ?, ?)";
			}
			
								
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,keywordMap.get("keyword"));
			pstmt.setString(2,keywordMap.get("ipAddress"));
			
			if(userid != null) {
				pstmt.setString(3,keywordMap.get("userid"));
			}
			
			n = pstmt.executeUpdate();
		} finally {
			close();
		}
		return n;
	}

	
	// 최근시간 00분 00초부터 한시간전까지의 인기검색어 10개를 불러오는 메소드
	@Override
	public List<String> selectBestKeyword() throws SQLException{
		List<String> keywordList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			
			String sql =  " select keyword,cnt_keyword "
						+ " from "
						+ " ( "
						+ " select keyword,cnt_keyword,rownum R "
						+ " from "
						+ " ( "
						+ " select keyword,count(*) cnt_keyword "
						+ " from tbl_search_keyword "
						+ " where search_date between to_date(to_char(sysdate-7,'yyyy-mm-dd hh24')||':00:00','yyyy-mm-dd hh24:mi:ss') and to_date(to_char(sysdate,'yyyy-mm-dd hh24')||':00:00','yyyy-mm-dd hh24:mi:ss') "
						+ " group by keyword "
						+ " order by cnt_keyword desc "
						+ " ) V "
						+ " ) T "
						+ " where R between 1 and 10 ";
								
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				keywordList.add(rs.getString("keyword"));
			}
			
		} finally {
			close();
		}
		return keywordList;
	}

	@Override
	public String selectCurrentHour() throws SQLException {
		String currentHour = "";
		try {
			conn = ds.getConnection();
			
			String sql =  " select to_char(sysdate,'mm/dd hh24') currentHour"
					    + " from dual ";
								
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				currentHour = rs.getString("currentHour");
			}
			
		} finally {
			close();
		}
		return currentHour;
	}
	
	// 추천검색어 리스트를 알아오는 메소드
	@Override
	public List<String> selectRecommendKeyword() throws SQLException {
		List<String> recommendList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			
			String sql =  " select keyword_num,keyword"
					    + " from tbl_recommend_keyword ";
								
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				recommendList.add(rs.getString("keyword"));
			}
			
		} finally {
			close();
		}
		return recommendList;
	}

	
	
	// 추천검색어를 insert 하는 메소드
	@Override
	public int insertRecommendKeyword(String keyword) throws SQLException {
		int n = 0;
		try {
			conn = ds.getConnection();
			String sql = " insert into tbl_recommend_keyword(keyword_num,keyword) "
					    +" values(seq_keyword_num.nextval, ?) ";
								
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,keyword);
			
			n = pstmt.executeUpdate();
		} finally {
			close();
		}
		return n;
	}

	
	
	// 추천검색어를 Delete 하는 메소드
	@Override
	public int DeleteRecommendKeyword(String keyword) throws SQLException {
		int n = 0;
		try {
			conn = ds.getConnection();
			String sql = " delete from tbl_recommend_keyword "
					   + " where keyword = ?";
								
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,keyword);
			
			n = pstmt.executeUpdate();
		} finally {
			close();
		}
		return n;
	}

	
	
	// 유저아이디를 입력받아서 좋아요한 상품들 갯수를 알아오는 메소드
	@Override
	public int cntLikeAllProduct(String userid) throws SQLException {
		int total_cnt = 0;
		try {
			conn = ds.getConnection();
			
			String sql =  " select count(*) "
					    + " from tbl_product P"
					    + " left join "
					    + " tbl_like L"
					    + " on P.product_num = L.product_num "
					    + " where userid = ? ";
								
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total_cnt = rs.getInt(1);
			}
			
		} finally {
			close();
		}
		return total_cnt;
	}

	
	
	// 유저아이디를 입력받아서 좋아요한 상품들 리스트를 알아오는 메소드
	@Override
	public List<ProductDTO> selectLikeAllProduct(Map<String, String> paraMap) throws SQLException{
		
		List<ProductDTO> productList = new ArrayList<>();
		String userid = paraMap.get("userid");
		try {
			conn = ds.getConnection();
			String sql = " select *  "
					   + " from  "
					   + " (  "
					   + " select V.*"
					   + "      , rownum R "
					   + " from  "
					   + " ( "
					   + " select C.*"
			  		   + "     ,  nvl(D.product_like_cnt,0) product_like_cnt"
					   + " from "
			  		   + " ( "
					   + " select A.*"
					   + "      , nvl(B.like_cnt,0) like_cnt "
					   + " from   "
					   + " (  "
					   + " select product_num     "
					   + "      , register_date    "
					   + "      , product_name "
					   + "      , product_image    "
					   + "      , category   "
					   + "      , detail_category   "
					   + "      , price 	"
					   + "      , (discount_rate*100) discount_rate    "
					   + "      , price - (price * discount_rate) real_price   "
					   + "      , gender    "
					   + "      , bestyn "
					   + " from tbl_product  "
					   + " ) A  "
					   + " join "
					   + " ( "
					   + " select product_num    "
					   + "      , count(*) as like_cnt "
					   + " from tbl_like"
					   + " where userid= ? "
					   + " group by product_num "
					   + " ) B  "
					   + " on A.product_num = B.product_num "
					   + " ) C  "
					   + " left join  "
					   + " ( "
					   + " select product_num   "
					   + "      , count(*) as product_like_cnt "
					   + " from tbl_like "
					   + " group by product_num  "
					   + " ) D "
					   + " on C.product_num = D.product_num  "
					   + " order by like_cnt desc"
					   + " ) V  "
					   + " ) T  "
					   + " where R between (?*?)-(?-1) and (?*?)  ";
//					   (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			pstmt.setInt(2,Integer.parseInt(paraMap.get("page")));
			pstmt.setInt(3,Integer.parseInt(paraMap.get("display_cnt")));
			pstmt.setInt(4,Integer.parseInt(paraMap.get("display_cnt")));
			pstmt.setInt(5,Integer.parseInt(paraMap.get("page")));
			pstmt.setInt(6,Integer.parseInt(paraMap.get("display_cnt")));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO pdto = new ProductDTO();	//dto 하나 생성
				pdto.setProduct_num(rs.getInt("product_num") );  
				pdto.setRegister_date(rs.getString("register_date") );  
				pdto.setProduct_name(rs.getString("product_name") );   
				pdto.setProduct_image(rs.getString("product_image") );
				pdto.setCategory(rs.getString("category") );
				pdto.setDetail_category(rs.getString("detail_category") );
				pdto.setPrice(rs.getInt("price") );
				pdto.setDiscount_rate(rs.getInt("discount_rate") );
				pdto.setReal_price(rs.getInt("real_price") );
				pdto.setProduct_like_cnt(rs.getInt("product_like_cnt"));
				
				productList.add(pdto);	//NoticeDTO들만 들어갈 수 있는 리스트에 담기
			}
		} finally {
			close();
		}
		return productList;
	}

	
	// 유저아이디를 입력받아서 회원맞춤 검색어 리스트를 알아오는 메소드
	@Override
	public List<String> selectpersonalizedKeyword(String userid) throws SQLException {
		List<String> personalizedList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			
			String sql =  " select * "
					    + " from "
					    + " ( "
					    + " select rownum R,V.* "
					    + " from "
					    + " ( "
					    + " select keyword,count(*) "
					    + " from tbl_search_keyword "
					    + " where userid= ? "
					    + " group by keyword "
					    + " order by count(*) desc "
					    + " ) V "
					    + " ) T "
					    + " where R between 1 and 5 ";
								
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				personalizedList.add(rs.getString("keyword"));
			}
			
		} finally {
			close();
		}
		return personalizedList;
	}
}