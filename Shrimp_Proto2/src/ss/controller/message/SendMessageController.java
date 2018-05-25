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
	
	
	//메세지 보내기를 누르면 메세지 작성 양식 페이지(WEB-INF/view/writeMessage.jsp) 이동하기 위한 컨트롤러
	@RequestMapping("/message/write.do")
	public String writeMessageForm (){
		System.out.println("Message 컨트롤러야");
		System.out.println("Message 작성 페이지로 가즈아");
		return "/WEB-INF/view/writeMessage"; 
	}
	
	//메세지 양식을 작성하고 보내기 버튼을 클릭 하면 
	//view 단에서 받은 두개의 데이터를 컨트롤러에서
	//
	// DB에 INSERT 후 
	//indox.do 페이지로 이동
	@RequestMapping(value = "/message/send.do", method=RequestMethod.POST)
	public ModelAndView sendMessage(MessageVO vo, HttpSession session, HttpServletResponse res) {  //view에서 받아오는 데이터 : "recv_nick": 메세지 받는 사용자 닉네임, "message":메세지 내용
		System.out.println("Message 컨트롤러야");
		System.out.println("view에서 받은 데이터 " + vo.getRecv_nick() + ":" + vo.getMessage());
		ModelAndView mav = new ModelAndView();
		
		//세션에서 사용자 정보 받아서 vo에 넣기
		System.out.println((UserVO)session.getAttribute("userInfo"));
		UserVO uservo = (UserVO)session.getAttribute("userInfo");//보내는 사용자의 닉네임을 session에서 구하기 위해 UserVO 객체 사용 
		vo.setSend_nick(uservo.getNickname());//messageVO에 보내는 사용자 닉네임 저장
		
		//biz야 일을 해라
		int i = biz.insertMessage(vo);  //i=1 : DB에 저장되었다.    i=0 : 저장안되었다.
		
		//param값으로 넣어주기
		mav.addObject("sendResult", i);  //사용 안함 (alert 띄워주기 위한 용도)
		mav.setViewName("redirect:inbox.do");
		return mav;
	}
	
	// 채팅방에서 보냈을때 작동
		@RequestMapping(value="/message/sendchatroom.do", method= RequestMethod.POST)
		public @ResponseBody void sendMsgroom(@RequestBody MessageVO vo, HttpSession session) {
			biz.insertMessage(vo);
		}

	
	
	//메세지 입력 창에서 메세지를 받을 사용자 닉네임을 입력하면 존재하는 닉네임인지(1), 존재하지 않는 닉네임인지 (0) 리턴한다.
	@RequestMapping(value = "/message/msgNickCheck.do" , method=RequestMethod.GET)
	public void msgNicknameCheck(@RequestParam("nickname") String nickname, HttpServletResponse res) {
		System.out.println("메세지 작성 양식에서 닉네임이 존재하는지 판별 컨트롤러");
		int result = biz.isNickname(nickname);
		System.out.println("컨트롤러 : "+result);
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

		msgvo.setSend_nick(uservo.getNickname());  // 현재 로그인한 사람이 보낸 메세지가 삭제 대상이므로
		biz.deletemsg(msgvo);
		
		return "redirect:inbox.do";
	}
	
}
