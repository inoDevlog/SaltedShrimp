package ss.dao.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ss.vo.board.BoardCommentVO;

@Repository
public class BoardCommentDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;

	public int InsertBoardComment(BoardCommentVO vo) {
		int res = 0;
		try {
			res = sqlSession.insert("boardCommentMapper.insert_boardComment", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	//해당 게시물의 댓글 List select
	public List<BoardCommentVO> SelectListBoardComment(int bseq) {
		List<BoardCommentVO> res = null;
		try {
			res = sqlSession.selectList("boardCommentMapper.selectList_boardComment", bseq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int DeleteBoardComment(BoardCommentVO vo) {
		int res = 0;
		// SqlSession session = factory.openSession();
		try {
			res = sqlSession.delete("boardCommentMapper.delete_boardComment", vo);
		} catch (Exception e) {
			e.printStackTrace();
		/*} finally {
			sqlSession.close();*/
		}
		return res;
	}
}
