package ss.dao.message;


import java.util.List;



import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ss.vo.message.MessageVO;

@Repository
public class SendMessageDAO {
	
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int insertMessage(MessageVO vo) {
		int i = 0;
		try {
			System.out.println("여기는 SendMessageDAO!!!");
			System.out.println("["+vo.getMessage()+" : " + vo.getRecv_nick()+"]");
			i = sqlSession.insert("messageMapper.insert_Message", vo );  
			System.out.println("DAO 결과 " + i);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public int countMessageMember(MessageVO msgvo) {
		int num = sqlSession.selectOne("messageMapper.countMessageMember", msgvo);
		return num;
	}
	
	public List<MessageVO> getMessageList(MessageVO msgvo) {
			List<MessageVO> list = sqlSession.selectList("messageMapper.getSendUserList", msgvo);
			
			return list;
	}

	public List<MessageVO> getDialogue(MessageVO msgvo) {
		System.out.println("DAO까지오는지 테스트");

			List<MessageVO> dialogue = sqlSession.selectList("messageMapper.getOneDialogue", msgvo);
			return dialogue;
		
	}

	public int getMessageCount(MessageVO msgvo) {
		return sqlSession.selectOne("messageMapper.countMessage", msgvo);
	}

	public List<MessageVO> previousMsg(MessageVO msgvo) {
		return sqlSession.selectList("messageMapper.previousMsg", msgvo);
	}

	public List<MessageVO> addmsg(MessageVO mvo) {
		return sqlSession.selectList("messageMapper.addmsg", mvo);
	}

	public void deletemsg(MessageVO msgvo) {
		sqlSession.update("messageMapper.deletesendmsg", msgvo);
		sqlSession.update("messageMapper.deleterecvmsg", msgvo);
	}

}
