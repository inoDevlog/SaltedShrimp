package ss.biz.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ss.dao.board.BoardCommentDAO;
import ss.vo.board.BoardCommentVO;

@Service
public class BoardCommentBiz {
	@Autowired
	private BoardCommentDAO dao;

	// Create Comment
	public int InsertBoardComment(BoardCommentVO vo) {
		System.out.println("BIZ - INSERT DATA");
		int res = dao.InsertBoardComment(vo);
		return res;
	}

	// Select Comment
	public List<BoardCommentVO> SelectListBoardComment(int bseq) {
		System.out.println("BIZ - SELECT DATA ALL");
		List<BoardCommentVO> res = dao.SelectListBoardComment(bseq);
		return res;
	}

	// Delete Comment
	public int DeleteBoardComment(BoardCommentVO vo) {
		int res = dao.DeleteBoardComment(vo);
		return res;
	}

}
