package ss.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ss.biz.user.UserBiz;
import ss.vo.user.LoginwithUriVO;
import ss.vo.user.UserVO;

@Controller
public class LoginController{

	@Autowired
	UserBiz biz;

	//로그인 컨트롤러 ver 1. => 로그인을 수행하면 무조건 메인 페이지 (index4.jsp)로 이동한다.   (사용 x)
	/*@RequestMapping(value="/login2.do", method=RequestMethod.POST)
	public String login(UserVO vo, HttpSession session, HttpServletRequest req) {
		String mav = null;
		System.out.println("로그인 컨트롤러" + vo.getEmail() + ":"+vo.getPasswd());  체크용
		UserVO vo1 = biz.selectUser(vo);  DB에서 받아온 회원정보 vo1
		//System.out.println("요청이 들어오는 주소 (getContextPath())"+req.getContextPath());
		//System.out.println("요청이 들어오는 주소 (req.getLocalAddr())"+req.getLocalAddr());
		if (vo1 != null) {  //이메일 존재하면 (1.비번이 같을 경우  2. 비번이 다를 경우)
			if(vo.getPasswd().equals(vo1.getPasswd())) {
				
				session.setAttribute("userInfo", vo1); //세션 유지를 위해 유저 정보는 세션의 userInfo에 저장
				
				//System.out.println("req.getPathInfo() : " + req.getPathInfo());
				//System.out.println("req.getServletPath() : " + req.getServletPath());
				//System.out.println("req.getPathTranslated() : " + req.getPathTranslated());
				System.out.println("req.getRequestURI() : "+req.getRequestURI());
				mav = "redirect:main.do";//페이지 이동
				System.out.println("컨트롤러 case1");
			}else {
				//mav = new ModelAndView("notLogin", "message", "패스워드가 옳지 않습니다.");//alert창
				System.out.println("컨트롤러 case2");
			}
		} else { //이메일이 존재 하지 않을 경우
			//mav = new ModelAndView("notLogin", "message", "이메일이 존재하지 않습니다.");//alert창
			System.out.println("컨트롤러 case3");
		}
		return mav;
	}*/
	
	
	
	//로그인 컨트롤러 ver 2. => 로그인 후, 로그인이 진행되었던 페이지로 이동 
	@RequestMapping(value="/login2.do", method=RequestMethod.POST)
	public String login2(LoginwithUriVO vo, HttpSession session, HttpServletRequest req) {
		String mav = null;
		System.out.println("로그인 컨트롤러 : " + vo);  /*체크용*/
		String uri = vo.getLoginUri();   //리다이렉트로 로그인 후 보내줄 URL 주소
		UserVO vo1 = biz.selectUser(vo);  /*DB에서 받아온 회원정보 vo1*/

		if (vo1 != null) {  //이메일 존재하면 (1.비번이 같을 경우  2. 비번이 다를 경우)
			if(vo.getPasswd().equals(vo1.getPasswd())) {
				session.setAttribute("userInfo", vo1); //세션 유지를 위해 유저 정보는 세션의 userInfo에 저장
				mav = "redirect:"+uri;//페이지 이동
				System.out.println("컨트롤러 case1");
			}else {
				//mav = new ModelAndView("notLogin", "message", "패스워드가 옳지 않습니다.");//alert창
				
				System.out.println("컨트롤러 case2");
			}
		} else { //이메일이 존재 하지 않을 경우 =
			//mav = new ModelAndView("notLogin", "message", "이메일이 존재하지 않습니다.");//alert창
			System.out.println("컨트롤러 case3");
		}
		return mav;
	}
	
	//로그인 컨트롤러 ver 3. => 로그인 실패 메세지 출력
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login3(LoginwithUriVO vo, HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
		String mav = null;
		System.out.println("로그인 컨트롤러 : " + vo);  /*체크용*/
		String uri = vo.getLoginUri();   //리다이렉트로 로그인 후 보내줄 URL 주소
		UserVO vo1 = biz.selectUser(vo);  /*DB에서 받아온 회원정보 vo1*/

		if (vo1 != null) {  //이메일 존재하면 (1.비번이 같을 경우  2. 비번이 다를 경우)
			if(vo.getPasswd().equals(vo1.getPasswd())) {
				session.setAttribute("userInfo", vo1); //세션 유지를 위해 유저 정보는 세션의 userInfo에 저장
				mav = "redirect:"+uri;//페이지 이동
				System.out.println("컨트롤러 case1");
			}else {
				//mav = new ModelAndView("notLogin", "message", "패스워드가 옳지 않습니다.");//alert창
				resp.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = null;
				try {
					out = resp.getWriter();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            out.println("<script>alert('로그인 실패 : 다시 시도해 주세요'); history.go(-1);</script>");
	            out.flush();
				System.out.println("컨트롤러 case2");
			}
		} else { //이메일이 존재 하지 않을 경우 =
			resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = null;
			try {
				out = resp.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            out.println("<script>alert('로그인 실패 : 다시 시도해 주세요'); history.go(-1);</script>");
            out.flush();
			System.out.println("컨트롤러 case3");
		}
		return mav;
	}
	
	
	//로그아웃을 하면 메인페이지(index3.html)로 이동한다.
	//회원가입이 완료되지 않으면 이 컨트롤러를 거친다.
	@RequestMapping(value="/loginPage.do")
	public String loginPage() {
		//로그인을 안한 상태에서 어떤 로직을 할 경우
		
		return "redirect:Index4.jsp";
	}
	
	//회원 메일 페이지(userIndex3.html) 로 이동하는 페이지 (로직 분리용)
	// : 회원 가입 후 가입 확인 페이지로 이동 한 뒤, 다시 회원 메인 페이지로 이동할떄 사용
	// : 로그인 로직시, 회원 데이터 확인 후  세션값에 입력 후 회원 메인 페이지로 이동할때 사용 (분리 가능)
	@RequestMapping("/main.do")
	public String userMainPage() {
		//세션에 회원 정보 넣는 로직 넣을까 말까? - 현재 사용중이긴 하나 나중에 수정 예정. : 넣지 말자... 다른데서 메인으로 페이지 이동할때 쓴다.
		
		//페이지 이동
		//return new ModelAndView("Index4");
		//return "/WEB-INF/view/userIndex3";
		return "redirect:Index4.jsp";
	}
	
	//로그인이 필요한 특정 활동을 하려는 경우 Intercepter에서 캐치
	//메인페이지(Index4.jsp)로 이동하되 로그인 모달을 띄운다.
	@RequestMapping("/needLogin.do")
	public String needLogin(HttpServletResponse res) {
		System.out.println("needLogin 컨트롤러");
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setContentType("text/html; charset=utf-8");
		out.println("<script language='javascript'>");
		out.println("$('#loginModal').modal('show');");
		out.println("</script>");
		out.flush();
		return "redirect:Index4.jsp";
	}
	
	
}
