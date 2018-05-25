package ss.dao.board;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ss.vo.board.BoardVoteViewVO;
import ss.vo.board.VoteTableCheckVO;

@Repository
public class BoardVoteDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	//VOTE TABLE UPDATE DAO
	public int updateVoteTable(BoardVoteViewVO vo) {  // vote, vseq �� �־�� �ϴ� vo
		System.out.println("DAO �Դ�.");
		return sqlSession.update("boardVoteMapper.update_VoteTable", vo);
	}
	
	//BOARD TABLE UPDATE DAO
	public int updateBoardTable(BoardVoteViewVO vo) {//votesum , bseq �� �־�� �ϴ� vo
		System.out.println("DAO �Դ�.");
		return sqlSession.update("boardVoteMapper.update_BoardTable", vo);
		
	}
	
	//VOTE TABLE INSERT DAO
	public int insertVote(BoardVoteViewVO vo) {  //useq, bseq, vote�� �־�� �ϴ� vo
		System.out.println("DAO �Դ�.");
		return sqlSession.insert("boardVoteMapper.insert_VoteTable", vo);
		
	}
	
	//VOTE TABLE SELECT DAO (CHECK)
	public int selectVoteCount(VoteTableCheckVO vo) {  //useq, bseq �� �־�� �ϴ� vo
		System.out.println("DAO �Դ�.");
		return sqlSession.selectOne("boardVoteMapper.checkVoteByUseq", vo);
		
	}
	
	//BOARD TABLE SELECT VOTESUM
	public int selectVotesum(int bseq) {  // bseq�� �־�� �Ѵ�.
		System.out.println("DAO �Դ�.");
		return sqlSession.selectOne("boardVoteMapper.select_BoardVoteSum", bseq);
		
		
	}
	//VOTE TABLE SELECT VOTE
	public int selectVoteValue(VoteTableCheckVO vo) {
		System.out.println("DAO");
		System.out.println(vo.getBseq() +" : " + vo.getUseq());
		int res = sqlSession.selectOne("boardVoteMapper.select_Vote", vo);
		System.out.println("DAO RESULT : " + res);
		return res;
		
		
	}
	

}
