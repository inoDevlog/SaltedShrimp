package ss.controller.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ss.biz.user.UserBiz;
import ss.vo.user.UserVO;

@Controller
public class JoinMemberController {
	
	@Autowired
	private UserBiz biz;
	
	@RequestMapping(value ="/joinmember.do", method=RequestMethod.POST)
	public String joinmember(UserVO vo, HttpSession session) {  //HttpSession or HttpServletRequest
		String result = null;
		System.out.println("회원 가입 처리 컨트롤러");
		int i =biz.insertUser(vo);
		System.out.println("i : " +i );    /*체크용*/
		
		
		//회원가입이완료 되었으면 세션에 회원 정보를 저장하고, 회원 메인페이지(userIndex3.jsp)로 이동
		//회원가입이 안되었으면 index3.html로 이동
		if(i>0) {
			vo = biz.selectUser(vo);   //DB에서 회원 전체 정보를 갖고 와서 세션에 저장한다.
			session.setAttribute("userInfo",  vo);
			result = "/WEB-INF/view/JoinPro";  //ViewResolver가 적용되었다............(나니)
//			result = "redirect:WEB-INF/view/JoinPro.jsp";  //오류발생.......(나니)
			//mav.setViewName("/WEB-INF/view/JoinPro");  //회원가입 후 완료되었다는 페이지
			
		}else {
			result = "redirect:mainBoard.do";
		}
		
		return result;
	}
	
	
	
	
	//회원가입 단계에서 닉네임 도금(중복확인) 컨트롤러
	@RequestMapping(value = "/checkNickname.do", method=RequestMethod.GET)
	public void checkNickname(@RequestParam("nickname") String nickname, HttpServletResponse res) {
		System.out.println("중복확인 컨트롤러");
		String result = null;
		int i = biz.selectNickname(nickname);
		if(i>0) {//이미 닉네임을 사용 할 수 없는 경우
			
			result = "0";
		}else {//닉네임을 사용 할 수 있는 경우
			System.out.println("사용할 수 있다.");
			result = "1";
		}
		try {
			res.getWriter().print(result);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
