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

	//�α��� ��Ʈ�ѷ� ver 1. => �α����� �����ϸ� ������ ���� ������ (index4.jsp)�� �̵��Ѵ�.   (��� x)
	/*@RequestMapping(value="/login2.do", method=RequestMethod.POST)
	public String login(UserVO vo, HttpSession session, HttpServletRequest req) {
		String mav = null;
		System.out.println("�α��� ��Ʈ�ѷ�" + vo.getEmail() + ":"+vo.getPasswd());  üũ��
		UserVO vo1 = biz.selectUser(vo);  DB���� �޾ƿ� ȸ������ vo1
		//System.out.println("��û�� ������ �ּ� (getContextPath())"+req.getContextPath());
		//System.out.println("��û�� ������ �ּ� (req.getLocalAddr())"+req.getLocalAddr());
		if (vo1 != null) {  //�̸��� �����ϸ� (1.����� ���� ���  2. ����� �ٸ� ���)
			if(vo.getPasswd().equals(vo1.getPasswd())) {
				
				session.setAttribute("userInfo", vo1); //���� ������ ���� ���� ������ ������ userInfo�� ����
				
				//System.out.println("req.getPathInfo() : " + req.getPathInfo());
				//System.out.println("req.getServletPath() : " + req.getServletPath());
				//System.out.println("req.getPathTranslated() : " + req.getPathTranslated());
				System.out.println("req.getRequestURI() : "+req.getRequestURI());
				mav = "redirect:main.do";//������ �̵�
				System.out.println("��Ʈ�ѷ� case1");
			}else {
				//mav = new ModelAndView("notLogin", "message", "�н����尡 ���� �ʽ��ϴ�.");//alertâ
				System.out.println("��Ʈ�ѷ� case2");
			}
		} else { //�̸����� ���� ���� ���� ���
			//mav = new ModelAndView("notLogin", "message", "�̸����� �������� �ʽ��ϴ�.");//alertâ
			System.out.println("��Ʈ�ѷ� case3");
		}
		return mav;
	}*/
	
	
	
	//�α��� ��Ʈ�ѷ� ver 2. => �α��� ��, �α����� ����Ǿ��� �������� �̵� 
	@RequestMapping(value="/login2.do", method=RequestMethod.POST)
	public String login2(LoginwithUriVO vo, HttpSession session, HttpServletRequest req) {
		String mav = null;
		System.out.println("�α��� ��Ʈ�ѷ� : " + vo);  /*üũ��*/
		String uri = vo.getLoginUri();   //�����̷�Ʈ�� �α��� �� ������ URL �ּ�
		UserVO vo1 = biz.selectUser(vo);  /*DB���� �޾ƿ� ȸ������ vo1*/

		if (vo1 != null) {  //�̸��� �����ϸ� (1.����� ���� ���  2. ����� �ٸ� ���)
			if(vo.getPasswd().equals(vo1.getPasswd())) {
				session.setAttribute("userInfo", vo1); //���� ������ ���� ���� ������ ������ userInfo�� ����
				mav = "redirect:"+uri;//������ �̵�
				System.out.println("��Ʈ�ѷ� case1");
			}else {
				//mav = new ModelAndView("notLogin", "message", "�н����尡 ���� �ʽ��ϴ�.");//alertâ
				
				System.out.println("��Ʈ�ѷ� case2");
			}
		} else { //�̸����� ���� ���� ���� ��� =
			//mav = new ModelAndView("notLogin", "message", "�̸����� �������� �ʽ��ϴ�.");//alertâ
			System.out.println("��Ʈ�ѷ� case3");
		}
		return mav;
	}
	
	//�α��� ��Ʈ�ѷ� ver 3. => �α��� ���� �޼��� ���
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login3(LoginwithUriVO vo, HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
		String mav = null;
		System.out.println("�α��� ��Ʈ�ѷ� : " + vo);  /*üũ��*/
		String uri = vo.getLoginUri();   //�����̷�Ʈ�� �α��� �� ������ URL �ּ�
		UserVO vo1 = biz.selectUser(vo);  /*DB���� �޾ƿ� ȸ������ vo1*/

		if (vo1 != null) {  //�̸��� �����ϸ� (1.����� ���� ���  2. ����� �ٸ� ���)
			if(vo.getPasswd().equals(vo1.getPasswd())) {
				session.setAttribute("userInfo", vo1); //���� ������ ���� ���� ������ ������ userInfo�� ����
				mav = "redirect:"+uri;//������ �̵�
				System.out.println("��Ʈ�ѷ� case1");
			}else {
				//mav = new ModelAndView("notLogin", "message", "�н����尡 ���� �ʽ��ϴ�.");//alertâ
				resp.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = null;
				try {
					out = resp.getWriter();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            out.println("<script>alert('�α��� ���� : �ٽ� �õ��� �ּ���'); history.go(-1);</script>");
	            out.flush();
				System.out.println("��Ʈ�ѷ� case2");
			}
		} else { //�̸����� ���� ���� ���� ��� =
			resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = null;
			try {
				out = resp.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            out.println("<script>alert('�α��� ���� : �ٽ� �õ��� �ּ���'); history.go(-1);</script>");
            out.flush();
			System.out.println("��Ʈ�ѷ� case3");
		}
		return mav;
	}
	
	
	//�α׾ƿ��� �ϸ� ����������(index3.html)�� �̵��Ѵ�.
	//ȸ�������� �Ϸ���� ������ �� ��Ʈ�ѷ��� ��ģ��.
	@RequestMapping(value="/loginPage.do")
	public String loginPage() {
		//�α����� ���� ���¿��� � ������ �� ���
		
		return "redirect:Index4.jsp";
	}
	
	//ȸ�� ���� ������(userIndex3.html) �� �̵��ϴ� ������ (���� �и���)
	// : ȸ�� ���� �� ���� Ȯ�� �������� �̵� �� ��, �ٽ� ȸ�� ���� �������� �̵��ҋ� ���
	// : �α��� ������, ȸ�� ������ Ȯ�� ��  ���ǰ��� �Է� �� ȸ�� ���� �������� �̵��Ҷ� ��� (�и� ����)
	@RequestMapping("/main.do")
	public String userMainPage() {
		//���ǿ� ȸ�� ���� �ִ� ���� ������ ����? - ���� ������̱� �ϳ� ���߿� ���� ����. : ���� ����... �ٸ����� �������� ������ �̵��Ҷ� ����.
		
		//������ �̵�
		//return new ModelAndView("Index4");
		//return "/WEB-INF/view/userIndex3";
		return "redirect:Index4.jsp";
	}
	
	//�α����� �ʿ��� Ư�� Ȱ���� �Ϸ��� ��� Intercepter���� ĳġ
	//����������(Index4.jsp)�� �̵��ϵ� �α��� ����� ����.
	@RequestMapping("/needLogin.do")
	public String needLogin(HttpServletResponse res) {
		System.out.println("needLogin ��Ʈ�ѷ�");
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
