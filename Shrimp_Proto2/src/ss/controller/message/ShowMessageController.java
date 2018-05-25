package ss.controller.message;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ss.biz.board.BoardService;
import ss.biz.message.MPager;
import ss.biz.message.MessageBiz;
import ss.vo.message.MessageVO;
import ss.vo.user.UserVO;

@Controller
public class ShowMessageController {

		// ���� �޼��� ����Ʈ�� �̵��ϴ� ��Ʈ�ѷ�(�޼��� �ۼ� �Ϸ� �� �� �̰����� �̵�)
		@Autowired
		MessageBiz messageService; //sendMessageService -> messageListService�� ����


		
		@RequestMapping("/message/inbox.do")
		public ModelAndView showMessageList(@RequestParam(defaultValue="1") int curPage, HttpSession session) {
			System.out.println("�޼�����");
			
			MessageVO msgvo = new MessageVO();
			
			// ����¡ ó��
			UserVO uservo = (UserVO)session.getAttribute("userInfo");//������ ������� �г����� session���� ���ϱ� ���� UserVO ��ü ���
			
			msgvo.setSend_nick(uservo.getNickname());  // session���� ������ nick ���� = ���� ���
			

			int count = messageService.countMessageMember(msgvo);  // ���� �α��� �ѻ���� ���� �޼��� ����

			System.out.println("�޼��� �Ѱ���" + count);
			
			MPager mPager = new MPager(count,  curPage);  
			int start = mPager.getPageBegin() - 1;
			msgvo.setStart(start);
			
			
			List<MessageVO> msglist = messageService.getMessageList(msgvo);
			for (MessageVO vo : msglist) {
				System.out.println(vo.getRecv_nick());
			}
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("msglist", msglist);
			map.put("count", count);
			map.put("mPager", mPager);
			
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("map", map);
			mav.setViewName("/WEB-INF/view/messageList");
			
			return mav;
		}
		
		@RequestMapping(value="/message/getDialogue.do", method=RequestMethod.GET)
		public ModelAndView getDialogue(HttpSession session, MessageVO msgvo) {  // messageList.jsp���� Ư�� User Ŭ���� Ư�� ���� nick = recv_nick�̹Ƿ� �� ������ ���� MessageVO
			System.out.println("1:1 ��ȭâ ����");
			ModelAndView mav = new ModelAndView();
			
			
			// DB���� ��ȭ�α׸� ������ ���������� ������ MessageVO ����
			UserVO uservo = (UserVO)session.getAttribute("userInfo");  // ���� �α��εǾ��ִ� ��������(�������)
			msgvo.setSend_nick(uservo.getNickname());
			System.out.println("�� ������ : " +uservo.getNickname());
			
			List<MessageVO> dialogue = messageService.getDialogue(msgvo);
			for (MessageVO vo : dialogue) {
				if (vo.getSend_nick().equals(uservo.getNickname())) {  // ���� �޼���_view �ܿ��� ���� ���� �и� 
					vo.setSv_index(0);
				}
				if (vo.getRecv_nick().equals(uservo.getNickname())) {  // ���� �޼���_view �ܿ��� ���� ���� �и�
					vo.setSv_index(1);
				}
			}
			
			MessageVO lastmsg = new MessageVO();
			try {
				lastmsg = dialogue.get(dialogue.size() - 1);
			} catch (Exception e) {
				
				lastmsg.setLastmseq(0);
			}
			
			mav.addObject("recv_nick", msgvo.getRecv_nick());
			mav.addObject("lastmseq", lastmsg.getMseq());
			mav.addObject("recv_nick", msgvo.getRecv_nick());
			mav.addObject("send_nick", msgvo.getSend_nick());
			mav.addObject("dialogue", dialogue);
			mav.setViewName("/WEB-INF/view/messageView");
			
			return mav;
			
		}
		
		@RequestMapping(value="/message/addnewMsg.do", method=RequestMethod.POST)
		public @ResponseBody List<MessageVO> addnewMsg (@RequestBody MessageVO mvo, HttpSession session) {
			List<MessageVO> addmsglist = messageService.addmsg(mvo);
			return addmsglist;
		}
		
		@RequestMapping(value="/message/previousMsg.do", method= RequestMethod.POST)
		public @ResponseBody List<MessageVO> previousMsg (@RequestBody MessageVO mvo, HttpSession session) {
			System.out.println("�޼��� ��ũ�Ѹ�");
			
			int cp = mvo.getCurrentPage();
			UserVO uservo = (UserVO)session.getAttribute("userInfo");
			MessageVO msgvo = new MessageVO();
			msgvo.setSend_nick(uservo.getNickname());
			msgvo.setRecv_nick(mvo.getRecv_nick());
			
			cp++;
			
			int count = messageService.getMessageCount(msgvo);
			System.out.println("cp : " + cp);
			System.out.println("count : " + count);
			
			MPager mPager = new  MPager(count, cp);
			int start = mPager.getPageBegin() - 1;
			msgvo.setStart(start);
			
			List<MessageVO> list = messageService.previousMsg(msgvo);
			for (MessageVO vo : list) {
				if (vo.getSend_nick().equals(uservo.getNickname())) {
					vo.setSv_index(0);
				}
				if (vo.getRecv_nick().equals(uservo.getNickname())) {
					vo.setSv_index(1);
				}
			}
			
			for (MessageVO vo1 :list ) {
				System.out.println(vo1);
			}
			
			return list;
			
		}
}
