package ss.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserInfoController {
	
	/*
	 * 작성자 : 임성민
	 * 작성일 : 01-12
	 * 설명 : 내 정보는 보는 페이지로 이동할때 거치는 컨트롤러. 컨트롤러를 거치지 않으면 인터셉터 맵핑 써줄때 경로가 길어진다.
	 * 		그리고 로그인 후 갈 수 있는 페이지는 view단으로 넣어서 분리(보호)한다.
	 */
	
	
	@RequestMapping("/mypage.do")
	public ModelAndView mypage() {
		System.out.println("여기는 UserInfo컨트롤러이다");
		return new ModelAndView("/WEB-INF/view/myPage");
		
	}
}
