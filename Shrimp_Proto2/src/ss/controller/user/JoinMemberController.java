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
		System.out.println("ȸ�� ���� ó�� ��Ʈ�ѷ�");
		int i =biz.insertUser(vo);
		System.out.println("i : " +i );    /*üũ��*/
		
		
		//ȸ�������̿Ϸ� �Ǿ����� ���ǿ� ȸ�� ������ �����ϰ�, ȸ�� ����������(userIndex3.jsp)�� �̵�
		//ȸ�������� �ȵǾ����� index3.html�� �̵�
		if(i>0) {
			vo = biz.selectUser(vo);   //DB���� ȸ�� ��ü ������ ���� �ͼ� ���ǿ� �����Ѵ�.
			session.setAttribute("userInfo",  vo);
			result = "/WEB-INF/view/JoinPro";  //ViewResolver�� ����Ǿ���............(����)
//			result = "redirect:WEB-INF/view/JoinPro.jsp";  //�����߻�.......(����)
			//mav.setViewName("/WEB-INF/view/JoinPro");  //ȸ������ �� �Ϸ�Ǿ��ٴ� ������
			
		}else {
			result = "redirect:mainBoard.do";
		}
		
		return result;
	}
	
	
	
	
	//ȸ������ �ܰ迡�� �г��� ����(�ߺ�Ȯ��) ��Ʈ�ѷ�
	@RequestMapping(value = "/checkNickname.do", method=RequestMethod.GET)
	public void checkNickname(@RequestParam("nickname") String nickname, HttpServletResponse res) {
		System.out.println("�ߺ�Ȯ�� ��Ʈ�ѷ�");
		String result = null;
		int i = biz.selectNickname(nickname);
		if(i>0) {//�̹� �г����� ��� �� �� ���� ���
			
			result = "0";
		}else {//�г����� ��� �� �� �ִ� ���
			System.out.println("����� �� �ִ�.");
			result = "1";
		}
		try {
			res.getWriter().print(result);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
