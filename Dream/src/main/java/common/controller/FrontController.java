package common.controller;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		description = "사용자가 웹에서 *.dream을 했을 경우 이 서블릿이 응답을 해주도록 한다.", 
		urlPatterns = { "*.dream" },  // 앞에 / 가 없음 (뒤에 .dream 이 붙으면 무조건 fromtController 서블릿이 응답한다.)
		initParams = { 	// 자바에서는 역슬래시 \ 한개는 탈출문자이므로 \\를 써줘야 한다 또는 /를 써주면 된다.
				@WebInitParam(name = "propertyConfig", value = "C:\\Users\\sist\\git\\Dream\\Dream\\src\\main\\webapp\\WEB-INF\\command.properties", description = "*.dream에 대한 클래스의 매핑파일")
		})
		// initParams = 초기화 파라미터 
public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private Map<String, Object> cmdMap = new HashMap<>();
	
	public void init(ServletConfig config) throws ServletException {
/*
    웹브라우저 주소창에서  *.dream 을 하면 FrontController 서블릿이 응대를 해오는데 
    맨 처음에 자동적으로 실행되어지는 메소드가 init(ServletConfig config) 이다.
    여기서 중요한 것은 init(ServletConfig config) 메소드는 WAS(톰캣)가 구동되어진 후
    딱 1번만 init(ServletConfig config) 메소드가 실행되어지고, 그 이후에는 실행이 되지 않는다. 
    그러므로 init(ServletConfig config) 메소드에는 FrontController 서블릿이 동작해야할 환경설정을 잡아주는데 사용된다.
*/      
    // *** 확인용 *** //
	// System.out.println("~~~ 확인용 => 서블릿 FrontController 의  init(ServletConfig config) 메소드가 실행됨.");
	
		String props = config.getInitParameter("propertyConfig"); // servelt 에 대한 환경 세팅값을 전부 읽어옴.
		// value = "C:/NCS/workspace(jsp)/SemiProject/src/main/webapp/WEB-INF/Command.properties") 를 읽어온다.
		
		// System.out.println("~~~ 확인용 props => " + props);
		// ~~~ 확인용 props => C:/NCS/workspace(jsp)/SemiProject/src/main/webapp/WEB-INF/Command.properties
		// 경로명 포함 파일 이름을 불러온다.
		
		try {
			FileInputStream fis = new FileInputStream(props);
			// 특정 파일(Command.properties)에 있는 내용을 읽어오기 위한 용도로 쓰이는 객체
			// FileInputStream fis 은 C:/NCS/workspace(jsp)/SemiProject/src/main/webapp/WEB-INF/Command.properties 파일의 내용을 읽어오는데 사용되는 객체이다.
			
			Properties pr = new Properties();
			// Properties 는 Collection 중 HashMap 계열중의 하나로써
	        // "key","value"으로 이루어져 있는것이다.
	        // 그런데 중요한 것은 Properties 는 key도 String 타입이고, value도 String 타입만 가능하다는 것이다.
	        // key는 중복을 허락하지 않는다. value 값을 얻어오기 위해서는 key값만 알면 된다.
			
			pr.load(fis);
		/*
	         pr.load(fis); 은  fis 객체를 사용하여 C:/NCS/workspace(jsp)/SemiProject/WebContent/WEB-INF/Command.properties 파일의 내용을 읽어다가 
	         Properties 클래스의 객체인 pr 에 로드시킨다.
	         그러면 pr 은 읽어온 파일(Command.properties)의 내용에서 
	         = 을 기준으로 왼쪽은 key로 보고, 오른쪽은 value 로 인식한다.
	    */
			
			Enumeration<Object> en = pr.keys(); 
			/*
	          pr.keys(); 은
	          C:/NCS/workspace(jsp)/SemiProject/WebContent/WEB-INF/Command.properties 파일의 내용물에서 
	          = 을 기준으로 왼쪽에 있는 모든 key 들만 가져오는 것이다.    
			*/
			
			while(en.hasMoreElements()) { // 키가 있는지 몰어본다.
				
				String key = (String)en.nextElement(); // 키를 꺼내어 온다.
			/*
				// System.out.println("~~~ 확인용 key =>" + key);
				// ~~~ 확인용 key =>/test/test2.dream
				// ~~~ 확인용 key =>/test1.dream
			*/
			/*
				System.out.println("~~~ 확인용 value =>" + pr.getProperty(key));
				// ~~~ 확인용 value =>test.controller.Test2Controller
				// ~~~ 확인용 value =>test.controller.Test1Controller
			*/	
				String className = pr.getProperty(key);
				
				if( className != null ) { // 키는 존재하는데 value가 없는 경우는 제외
					className = className.trim(); // 혹시 클래스 이름에 실수로 앞뒤로 공백이 들어가있을 경우 삭제 
				
					Class<?> cls = Class.forName(className); 
					// <?> 은 generic 인데 어떤 클래스 타입인지는 모르지만 하여튼 클래스 타입이 들어온다는 뜻이다.
	                // String 타입으로 되어진 className 을 클래스화 시켜주는 것이다.
	                // 주의할 점은 실제로 String 으로 되어져 있는 문자열이 클래스로 존재해야만 한다는 것이다.
					
					Constructor<?> constrt = cls.getDeclaredConstructor();
					//생성자 만들기 
					
					Object obj = constrt.newInstance();
					// 생성자로부터 실제 객체(인스턴스)를 생성해주는 것이다.
					/*
					 	### 확인용 Test2Controller 클래스의 기본생성자 호출됨 ###
						~~~ 확인용 Test1Controller 클래스의 기본생성자 호출됨 ~~~
					*/
					
					// 생성되어진 객체를 Map 에 저장해둔다.
					
					cmdMap.put(key, obj);
					// cmdMap 에서 키값으로 Command.properties 파일에 저장되어진 url 을 주면 
	                // cmdMap 에서 해당 클래스에 대한 객체(인스턴스)를 얻어오도록 만든 것이다.
					
				}// end of if( className != null ) {
				
			}// end of while()------------------------------------
			
		} catch (FileNotFoundException e) {
			System.out.println(">>> C:/NCS/workspace(jsp)/SemiProject/src/main/webapp/WEB-INF/Command.properties 파일이 존재하지 않습니다. <<<");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(">>> 문자열로 명명되어진 클래스가 존재하지 않습니다. <<<");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end of public void init(ServletConfig config) throws ServletException {--------------

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestProcess(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestProcess(request,response);
	}
	
	private void requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 웹브라우저의 주소 입력창에서 
	    // http://localhost:9090/SemiProject/member/idDuplicateCheck.dream?userid=leess 와 같이 입력되었더라면
		// String url = request.getRequestURL().toString(); // stringBuffer 타입의 url 주소를 String 타입으로 바꿔줌
		
		// System.out.println("~~~ 확인용 url => " + url);
		// ~~~ 확인용 url => http://localhost:9090/SemiProject/member/idDuplicateCheck.dream

		// 웹브라우저의 주소 입력창에서 
	    // http://localhost:9090/SemiProject/member/idDuplicateCheck.dream?userid=leess 와 같이 입력되었더라면
		String uri = request.getRequestURI(); 
		// uri 를 통해 contextPathname + 주소를 가져올 수 있다.
//		System.out.println("~~~ 확인용 uri => " + uri);
		// ~~~ 확인용 uri => /SemiProject/member/idDuplicateCheck.dream
		// ~~~ 확인용 uri => /SemiProject/test1.dream
		// ~~~ 확인용 uri => /SemiProject/test/test2.dream
		request.getContextPath(); // /SemiProject
		
		String key = uri.substring(request.getContextPath().length());
		/*
		   request.getContextPath().length(); ==> 6이다.
		   /member/idDuplicateCheck.dream
		   /test1.dream
		   /test/test2.dream
		*/
		
		AbstractController action = (AbstractController)cmdMap.get(key);
		
		if(action == null) {
			System.out.println(">>> "+key+"에 URL 패턴에 매핑된 클래스가 없습니다.  <<<");
		}
		else {
			
			try {
				
				// 필터의 로직을 작성하는 메소드
			    // ==> doPost()에서 한글이 안 깨지려면 
			    //     request.getParameter("name"); 을 하기전에
			    //     request.setCharacterEncoding("UTF-8"); 을 
			    //     먼저 해주어야 한다.
			    request.setCharacterEncoding("UTF-8"); // 필터가 없을 경우 써줘야한다
				
				action.execute(request, response);
				
				boolean bool = action.isRedirect(); 
				String viewPage = action.getViewPage();
				
				if(!bool) {
					// viewPage 에 명기된 view단 페이지로 forward(dispatcher)를 하겠다는 말이다.
	                // forward 되어지면 웹브라우저의 URL주소 변경되지 않고 그대로 이면서 화면에 보여지는 내용은 forward 되어지는 jsp 파일이다.
	                // 또한 forward 방식은 forward 되어지는 페이지로 데이터를 전달할 수 있다는 것이다.
					
					if(viewPage != null) {
						RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
						dispatcher.forward(request, response);
					}
					
				}
				else {
					// viewPage 에 명기된 주소로 sendRedirect(웹브라우저의 URL주소 변경됨)를 하겠다는 말이다.
		            // 즉, 단순히 페이지이동을 하겠다는 말이다. 
		            // 암기할 내용은 sendRedirect 방식은 sendRedirect 되어지는 페이지로 데이터를 전달할 수가 없다는 것이다.
					
					if(viewPage != null) {
						response.sendRedirect(viewPage);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}// end of private void requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException

}
