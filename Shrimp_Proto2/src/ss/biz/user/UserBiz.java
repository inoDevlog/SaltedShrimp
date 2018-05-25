package ss.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ss.dao.user.UserDAO;
import ss.vo.user.LoginwithUriVO;
import ss.vo.user.UserVO;



@Service
public class UserBiz{   
	
	@Autowired
	private UserDAO userDAO;

	//로그인 :  사용자 정보 확인
	public UserVO selectUser(LoginwithUriVO vo) {
		UserVO vo1 = new UserVO();
		vo1.setEmail(vo.getEmail());
		vo1.setPasswd(vo.getPasswd());
		
		vo1 = userDAO.selectUser(vo1);
		return vo1;
	}
	
	public UserVO selectUser(UserVO vo) {
		UserVO vo1 = new UserVO();
		vo1.setEmail(vo.getEmail());
		vo1.setPasswd(vo.getPasswd());
		
		vo1 = userDAO.selectUser(vo1);
		return vo1;
	}
	
	
	//회원가입 :  사용자 정보 입력
	public int insertUser(UserVO vo) {
		int i = userDAO.insertUser(vo);
		return i; //입력이 성공적으로 되었는지 안되었는지 여부(1, 0)만 리턴
	}

	//회원가입 : 사용자 닉네임 도금(중복확인)
	public int selectNickname(String nickname) {
		int i = userDAO.selectNickname(nickname);
		return i;
	}
	
}
