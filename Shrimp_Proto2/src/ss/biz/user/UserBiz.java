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

	//�α��� :  ����� ���� Ȯ��
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
	
	
	//ȸ������ :  ����� ���� �Է�
	public int insertUser(UserVO vo) {
		int i = userDAO.insertUser(vo);
		return i; //�Է��� ���������� �Ǿ����� �ȵǾ����� ����(1, 0)�� ����
	}

	//ȸ������ : ����� �г��� ����(�ߺ�Ȯ��)
	public int selectNickname(String nickname) {
		int i = userDAO.selectNickname(nickname);
		return i;
	}
	
}
