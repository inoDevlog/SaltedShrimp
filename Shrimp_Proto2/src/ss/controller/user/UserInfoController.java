package ss.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserInfoController {
	
	/*
	 * �ۼ��� : �Ӽ���
	 * �ۼ��� : 01-12
	 * ���� : �� ������ ���� �������� �̵��Ҷ� ��ġ�� ��Ʈ�ѷ�. ��Ʈ�ѷ��� ��ġ�� ������ ���ͼ��� ���� ���ٶ� ��ΰ� �������.
	 * 		�׸��� �α��� �� �� �� �ִ� �������� view������ �־ �и�(��ȣ)�Ѵ�.
	 */
	
	
	@RequestMapping("/mypage.do")
	public ModelAndView mypage() {
		System.out.println("����� UserInfo��Ʈ�ѷ��̴�");
		return new ModelAndView("/WEB-INF/view/myPage");
		
	}
}
