package ss.util.intercepter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionCheckIntercepter extends HandlerInterceptorAdapter{ 
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		try {
			//userInfo 세션값이 널일경우
			System.out.println("여기는 세션값 확인하는 인터셉터");
			if(request.getSession().getAttribute("userInfo") == null ){
					//로그인페이지(index4.jsp)로 redirect
					System.out.println("세션값이 없군!! index4.jsp로 이동하겠음!!!");
					//login 모달창의 띄우기 위해 특정 param 값을 달고 페이지 이동. 
					response.sendRedirect(request.getContextPath()+ "/Index4.jsp?login=0");
						
					return false;  //컨트롤러를 호출하지 않는다.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//널이 아니면 정상적으로 컨트롤러 호출
		return true;
	}
	
	
	
	
  
}
