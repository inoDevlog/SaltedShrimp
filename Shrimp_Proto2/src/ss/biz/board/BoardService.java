package ss.biz.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ss.vo.board.BoardVO;
import ss.vo.board.WriteBoardVO;
import ss.vo.user.UserVO;

public interface BoardService {
	 void insertBoard(WriteBoardVO vo, UserVO uvo);
	 void insertLinkBoard(WriteBoardVO vo, UserVO uvo);
	 void insertImageBoard(WriteBoardVO vo, UserVO uvo, HttpServletRequest req);
	 void insertVideoBoard(WriteBoardVO vo, UserVO uvo, HttpServletRequest req);
	 // void updateBoard(BoardVO vo);
	 void deleteBoard(BoardVO vo);
	 BoardVO getBoard(BoardVO vo);
	 List<BoardVO> getBoardList(int start, int end, String searchOption, String keyword);
	 List<BoardVO> getLatestList(UserVO uvo); 
	int countArticle(String searchOption, String keyword);
	void increaseHit(BoardVO vo);
	List<BoardVO> infiniteScrollDown(int bseqToStart, int mode, UserVO uvo);
	int countAllArticle();
	List<BoardVO> getPopList(UserVO uvo);
	List<BoardVO> addnewboard_L(BoardVO bvo);
	int countNewBoard(BoardVO bvo);
	int countArticleSearch(String keyword);
	List<BoardVO> getMainBoardSearch(int start, String keyword);
}
