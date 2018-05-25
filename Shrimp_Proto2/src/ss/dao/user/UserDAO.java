package ss.dao.user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ss.vo.user.UserVO;

@Repository
public class UserDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	// �α��� ���
	public UserVO selectUser(UserVO vo) {
		UserVO vo1 = null;
		
		try {
			System.out.println("DAO (���� �̸���) : " + vo.getEmail());
			System.out.println("DAO (���� ���) : " + vo.getPasswd());
			vo1 = sqlSession.selectOne("userMapper.checkUserByEmail", vo);
			System.out.println("vo" + vo1);
			/// System.out.println("DAO - "+vo.getEmail() + " : " + vo.getpasswd());/*üũ��*/
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return vo1;
	}

	
	
	// ȸ������
	public int insertUser(UserVO vo) {
		int i =0;
//		SqlSession session = factory.openSession();
		try {

			System.out.println("DAO (form �Է°�) : " + vo);
			i = sqlSession.insert("userMapper.insert_userVO", vo);
			System.out.println("result : " + i);
			//if(i>0) sqlSession.commit();
			
		} catch (Exception e) {
			//sqlSession.rollback();
			e.printStackTrace();
		} finally {
			//sqlSession.close();
		}

		return i; //�Է��� ���������� �Ǿ����� �ȵǾ����� ����(1, 0)�� ����
	}

	//ȸ������ : ����� �г��� ����(�ߺ�Ȯ��)
	public int selectNickname(String nickname) {
		int i = 0;
		try {
			System.out.println("����� DAO) nickname : " + nickname); 
			i = sqlSession.selectOne("userMapper.checkUserByNickname", nickname);
			System.out.println("DAO ���� ��� : "+i);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
