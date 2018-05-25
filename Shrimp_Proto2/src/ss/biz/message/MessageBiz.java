package ss.biz.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ss.biz.user.UserBiz;
import ss.dao.message.SendMessageDAO;
import ss.vo.message.MessageVO;

@Service
public class MessageBiz {
	
	@Autowired
	SendMessageDAO dao;
	@Autowired
	UserBiz userBiz;
	
	//삭제 예정 -> isNickname으로 대체
	public int checkRecvNickname(String recvNickname) {
		int i = userBiz.selectNickname(recvNickname);
		return i;
	}
	
	
	// DB에 INSERT (후 전송 완료 alert)
	public int insertMessage(MessageVO vo) {
		int result = dao.insertMessage(vo);
		return result;
	}
	
	//메세지를 작성할때 입력된 사용자 닉네임이 존재하는지 유무를 판단. 
	public int isNickname(String nickname) {
		return userBiz.selectNickname(nickname);  //1: 존재한다.    0: 존재하지 않는다.
	}
	


	public List<MessageVO> getMessageList(MessageVO msgvo) {
		
		return dao.getMessageList(msgvo);
	}
	
	public int countMessageMember(MessageVO msgvo) {
		
		return dao.countMessageMember(msgvo);
	}


	public List<MessageVO> getDialogue(MessageVO msgvo) {
		
		return dao.getDialogue(msgvo);
	}


	public int getMessageCount(MessageVO msgvo) {
		
		return dao.getMessageCount(msgvo);
	}


	public List<MessageVO> previousMsg(MessageVO msgvo) {
		return dao.previousMsg(msgvo);
	}


	public List<MessageVO> addmsg(MessageVO mvo) {
		return dao.addmsg(mvo);
	}


	public void deletemsg(MessageVO msgvo) {
		dao.deletemsg(msgvo);
	}

}
