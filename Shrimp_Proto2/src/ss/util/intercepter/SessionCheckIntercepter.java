package ss.util.intercepter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionCheckIntercepter extends HandlerInterceptorAdapter{ 
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		try {
			//userInfo ���ǰ��� ���ϰ��
			System.out.println("����� ���ǰ� Ȯ���ϴ� ���ͼ���");
			if(request.getSession().getAttribute("userInfo") == null ){
					//�α���������(index4.jsp)�� redirect
					System.out.println("���ǰ��� ����!! index4.jsp�� �̵��ϰ���!!!");
					//login ���â�� ���� ���� Ư�� param ���� �ް� ������ �̵�. 
					response.sendRedirect(request.getContextPath()+ "/Index4.jsp?login=0");
						
					return false;  //��Ʈ�ѷ��� ȣ������ �ʴ´�.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//���� �ƴϸ� ���������� ��Ʈ�ѷ� ȣ��
		return true;
	}
	
	
	
	
  
}
