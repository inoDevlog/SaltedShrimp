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
	
	//���� ���� -> isNickname���� ��ü
	public int checkRecvNickname(String recvNickname) {
		int i = userBiz.selectNickname(recvNickname);
		return i;
	}
	
	
	// DB�� INSERT (�� ���� �Ϸ� alert)
	public int insertMessage(MessageVO vo) {
		int result = dao.insertMessage(vo);
		return result;
	}
	
	//�޼����� �ۼ��Ҷ� �Էµ� ����� �г����� �����ϴ��� ������ �Ǵ�. 
	public int isNickname(String nickname) {
		return userBiz.selectNickname(nickname);  //1: �����Ѵ�.    0: �������� �ʴ´�.
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
