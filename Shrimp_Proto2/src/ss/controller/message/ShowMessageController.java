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

		// 보낸 메세지 리스트로 이동하는 컨트롤러(메세지 작성 완료 한 후 이곳으로 이동)
		@Autowired
		MessageBiz messageService; //sendMessageService -> messageListService로 변경


		
		@RequestMapping("/message/inbox.do")
		public ModelAndView showMessageList(@RequestParam(defaultValue="1") int curPage, HttpSession session) {
			System.out.println("메세지함");
			
			MessageVO msgvo = new MessageVO();
			
			// 페이징 처리
			UserVO uservo = (UserVO)session.getAttribute("userInfo");//보내는 사용자의 닉네임을 session에서 구하기 위해 UserVO 객체 사용
			
			msgvo.setSend_nick(uservo.getNickname());  // session에서 가져온 nick 정보 = 보낸 사람
			

			int count = messageService.countMessageMember(msgvo);  // 현재 로그인 한사람이 보낸 메세지 개수

			System.out.println("메세지 총개수" + count);
			
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
		public ModelAndView getDialogue(HttpSession session, MessageVO msgvo) {  // messageList.jsp에서 특정 User 클릭시 특정 유저 nick = recv_nick이므로 그 정보를 담을 MessageVO
			System.out.println("1:1 대화창 오픈");
			ModelAndView mav = new ModelAndView();
			
			
			// DB에서 대화로그를 꺼내올 유저정보를 적용한 MessageVO 구현
			UserVO uservo = (UserVO)session.getAttribute("userInfo");  // 현재 로그인되어있는 유저정보(보낸사람)
			msgvo.setSend_nick(uservo.getNickname());
			System.out.println("너 누구냐 : " +uservo.getNickname());
			
			List<MessageVO> dialogue = messageService.getDialogue(msgvo);
			for (MessageVO vo : dialogue) {
				if (vo.getSend_nick().equals(uservo.getNickname())) {  // 보낸 메세지_view 단에서 쓰는 서식 분리 
					vo.setSv_index(0);
				}
				if (vo.getRecv_nick().equals(uservo.getNickname())) {  // 받은 메세지_view 단에서 쓰는 서식 분리
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
			System.out.println("메세지 스크롤링");
			
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
