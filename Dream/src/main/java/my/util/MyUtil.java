package my.util;

import javax.servlet.http.HttpServletRequest;

public class MyUtil {

   // *** ? 다음의 데이터까지 포함한 현재 URL 주소를 알려주는 메소드를 생성 *** //
   public static String getCurrentURL(HttpServletRequest request) {
      
	// http://localhost:9090/Dream/notice/notice.dream?category=침구류&detail_category=침대
	   
	   String currentURL = request.getRequestURL().toString();
       // currentURL ==> http://localhost:9090/Dream/notice/notice.dream
      
       String queryString = request.getQueryString();
      // category=침구류&detail_category=침대  (GET방식일 경우)
      // nulll  (POST방식일 경우) 
      
      if(queryString != null) { // GET방식일 경우
         currentURL += "?" + queryString;
       // http://localhost:9090/Dream/notice/notice.dream?category=침구류&detail_category=침대 
      }
      
      
      String ctxPath = request.getContextPath();
      //     /Dream
      
      int beginIndex = currentURL.indexOf(ctxPath) + ctxPath.length();
      //   27        =           21                +        6
      
      currentURL = currentURL.substring(beginIndex);
      //           /notice/notice.dream?category=침구류&detail_category=침대 
      
      return currentURL;
   }
	
}
