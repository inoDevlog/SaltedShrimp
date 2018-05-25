package ss.controller.message;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ss.biz.message.MessageBiz;
import ss.vo.message.MessageVO;
import ss.vo.user.UserVO;

@Controller
public class SendMessageController {
	
	@Autowired
	MessageBiz biz;
	
	
	//�޼��� �����⸦ ������ �޼��� �ۼ� ��� ������(WEB-INF/view/writeMessage.jsp) �̵��ϱ� ���� ��Ʈ�ѷ�
	@RequestMapping("/message/write.do")
	public String writeMessageForm (){
		System.out.println("Message ��Ʈ�ѷ���");
		System.out.println("Message �ۼ� �������� �����");
		return "/WEB-INF/view/writeMessage"; 
	}
	
	//�޼��� ����� �ۼ��ϰ� ������ ��ư�� Ŭ�� �ϸ� 
	//view �ܿ��� ���� �ΰ��� �����͸� ��Ʈ�ѷ�����
	//
	// DB�� INSERT �� 
	//indox.do �������� �̵�
	@RequestMapping(value = "/message/send.do", method=RequestMethod.POST)
	public ModelAndView sendMessage(MessageVO vo, HttpSession session, HttpServletResponse res) {  //view���� �޾ƿ��� ������ : "recv_nick": �޼��� �޴� ����� �г���, "message":�޼��� ����
		System.out.println("Message ��Ʈ�ѷ���");
		System.out.println("view���� ���� ������ " + vo.getRecv_nick() + ":" + vo.getMessage());
		ModelAndView mav = new ModelAndView();
		
		//���ǿ��� ����� ���� �޾Ƽ� vo�� �ֱ�
		System.out.println((UserVO)session.getAttribute("userInfo"));
		UserVO uservo = (UserVO)session.getAttribute("userInfo");//������ ������� �г����� session���� ���ϱ� ���� UserVO ��ü ��� 
		vo.setSend_nick(uservo.getNickname());//messageVO�� ������ ����� �г��� ����
		
		//biz�� ���� �ض�
		int i = biz.insertMessage(vo);  //i=1 : DB�� ����Ǿ���.    i=0 : ����ȵǾ���.
		
		//param������ �־��ֱ�
		mav.addObject("sendResult", i);  //��� ���� (alert ����ֱ� ���� �뵵)
		mav.setViewName("redirect:inbox.do");
		return mav;
	}
	
	// ä�ù濡�� �������� �۵�
		@RequestMapping(value="/message/sendchatroom.do", method= RequestMethod.POST)
		public @ResponseBody void sendMsgroom(@RequestBody MessageVO vo, HttpSession session) {
			biz.insertMessage(vo);
		}

	
	
	//�޼��� �Է� â���� �޼����� ���� ����� �г����� �Է��ϸ� �����ϴ� �г�������(1), �������� �ʴ� �г������� (0) �����Ѵ�.
	@RequestMapping(value = "/message/msgNickCheck.do" , method=RequestMethod.GET)
	public void msgNicknameCheck(@RequestParam("nickname") String nickname, HttpServletResponse res) {
		System.out.println("�޼��� �ۼ� ��Ŀ��� �г����� �����ϴ��� �Ǻ� ��Ʈ�ѷ�");
		int result = biz.isNickname(nickname);
		System.out.println("��Ʈ�ѷ� : "+result);
		String result_str;
		if(result==1) {
			result_str = "1";
		}else {
			result_str = "0";
		}
		try {
			res.getWriter().print(result_str);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/message/msgdelete.do")
	public String msgeDelete(HttpSession session, MessageVO msgvo) {
		UserVO uservo = (UserVO)session.getAttribute("userInfo");

		msgvo.setSend_nick(uservo.getNickname());  // ���� �α����� ����� ���� �޼����� ���� ����̹Ƿ�
		biz.deletemsg(msgvo);
		
		return "redirect:inbox.do";
	}
	
}
