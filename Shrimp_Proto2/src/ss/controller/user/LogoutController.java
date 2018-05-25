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
		System.out.println("로그아웃 처리 컨트롤러");
		System.out.println("지우기 전 userInfo : " + req.getSession().getAttribute("userInfo"));
		
		//session.invalidate();
		req.getSession().invalidate();
		
		System.out.println("지우고 난 후 userInfo : " + req.getSession().getAttribute("userInfo"));

		return "redirect:mainBoard.do";
		
	}

}
