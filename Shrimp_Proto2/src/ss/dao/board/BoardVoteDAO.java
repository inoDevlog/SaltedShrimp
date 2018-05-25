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
	public int updateVoteTable(BoardVoteViewVO vo) {  // vote, vseq 가 있어야 하는 vo
		System.out.println("DAO 왔다.");
		return sqlSession.update("boardVoteMapper.update_VoteTable", vo);
	}
	
	//BOARD TABLE UPDATE DAO
	public int updateBoardTable(BoardVoteViewVO vo) {//votesum , bseq 가 있어야 하는 vo
		System.out.println("DAO 왔다.");
		return sqlSession.update("boardVoteMapper.update_BoardTable", vo);
		
	}
	
	//VOTE TABLE INSERT DAO
	public int insertVote(BoardVoteViewVO vo) {  //useq, bseq, vote가 있어야 하는 vo
		System.out.println("DAO 왔다.");
		return sqlSession.insert("boardVoteMapper.insert_VoteTable", vo);
		
	}
	
	//VOTE TABLE SELECT DAO (CHECK)
	public int selectVoteCount(VoteTableCheckVO vo) {  //useq, bseq 가 있어야 하는 vo
		System.out.println("DAO 왔다.");
		return sqlSession.selectOne("boardVoteMapper.checkVoteByUseq", vo);
		
	}
	
	//BOARD TABLE SELECT VOTESUM
	public int selectVotesum(int bseq) {  // bseq가 있어야 한다.
		System.out.println("DAO 왔다.");
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
