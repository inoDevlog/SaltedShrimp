package ss.controller.user;




import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class LogoutController {

	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest req) {  //HttpSession session
		System.out.println("�α׾ƿ� ó�� ��Ʈ�ѷ�");
		System.out.println("����� �� userInfo : " + req.getSession().getAttribute("userInfo"));
		
		//session.invalidate();
		req.getSession().invalidate();
		
		System.out.println("����� �� �� userInfo : " + req.getSession().getAttribute("userInfo"));

		return "redirect:mainBoard.do";
		
	}

}
