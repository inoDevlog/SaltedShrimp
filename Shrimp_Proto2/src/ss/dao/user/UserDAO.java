package ss.dao.user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ss.vo.user.UserVO;

@Repository
public class UserDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	// 로그인 기능
	public UserVO selectUser(UserVO vo) {
		UserVO vo1 = null;
		
		try {
			System.out.println("DAO (받은 이메일) : " + vo.getEmail());
			System.out.println("DAO (받은 비번) : " + vo.getPasswd());
			vo1 = sqlSession.selectOne("userMapper.checkUserByEmail", vo);
			System.out.println("vo" + vo1);
			/// System.out.println("DAO - "+vo.getEmail() + " : " + vo.getpasswd());/*체크용*/
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return vo1;
	}

	
	
	// 회원가입
	public int insertUser(UserVO vo) {
		int i =0;
//		SqlSession session = factory.openSession();
		try {

			System.out.println("DAO (form 입력값) : " + vo);
			i = sqlSession.insert("userMapper.insert_userVO", vo);
			System.out.println("result : " + i);
			//if(i>0) sqlSession.commit();
			
		} catch (Exception e) {
			//sqlSession.rollback();
			e.printStackTrace();
		} finally {
			//sqlSession.close();
		}

		return i; //입력이 성공적으로 되었는지 안되었는지 여부(1, 0)만 리턴
	}

	//회원가입 : 사용자 닉네임 도금(중복확인)
	public int selectNickname(String nickname) {
		int i = 0;
		try {
			System.out.println("여기는 DAO) nickname : " + nickname); 
			i = sqlSession.selectOne("userMapper.checkUserByNickname", nickname);
			System.out.println("DAO 쿼리 결과 : "+i);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
