package ss.dao.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ss.vo.board.BoardVO;
import ss.vo.board.WriteBoardVO;
import ss.vo.user.UserVO;

@Repository
public class BoardDAOSpring {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String BOARD_INSERT = "insert into tb_board(bcode, bseq, title, useq, article, deleteart, datetime) "
			+ "values(? ,nextval('sq_bseq'), ?, ?, ?, 0, now())";
	private final String BOARD_INSERT_LINK = "insert into tb_board(bcode, bseq, title, useq, article, deleteart, datetime, link)"
			+ "values(?, nextval('sq_bseq'), ?, ?, ?, 0, now(),?)";
			
	// private final String BOARD_UPDATE = "update board set title = ?, content = ? where seq = ?";
	
	private final String BOARD_DELETE = "update tb_board set deleteart = 1 where bseq = ?";
	private final String BOARD_GET = "select * from tb_board where bseq = ?";
	// private final String BOARD_LIST = "select * from tb_board order by bseq desc";
	
	private final String GET_NICKNAME = "select u.nickname as nickname from tb_board b, tb_user u where b.bseq = ? and u.useq = ?";
	private final String INC_HIT = "update tb_board set hit = hit + 1 where bseq = ?";
	
	// private final String BOARD_LIST = "select A.* from (select * from tb_board where article like '%'||?||'%' or title like '%'||?||'%' order by bseq desc)A limit 10 offset ?";
	private final String BOARD_LIST_T = "select A.* from (select * from tb_board where title like '%'||?||'%' and deleteart = 0 order by bseq desc)A  limit 10 offset ?";
	private final String BOARD_LIST_C = "select A.* from (select * from tb_board where article like '%'||?||'%' and deleteart = 0 order by bseq desc)A limit 10 offset ?";
	private final String BOARD_LIST_N = "select * from tb_board b, (select * from tb_user where nickname like '%'||?||'%')A where b.useq = A.useq and deleteart = 0 order by b.bseq desc limit 10 offset ?";
	// private final String BOARD_LIST_C = "select * from board where article like '%'||?||'%' order by seq desc";
	
	private final String COUNT_ARTICLE_T = "select count(*) from tb_board b where title like '%'||?||'%' and deleteart = 0";
	private final String COUNT_ARTICLE_C = "select count(*) from tb_board b where article like '%'||?||'%' and deleteart = 0";
	private final String COUNT_ARTICLE_N = "select count(c.*) from (select * from tb_board b, (select * from tb_user where nickname like '%'||?||'%')A where b.useq = A.useq and deleteart = 0)c";

	private final String COUNT_ARTICLE_MAIN = "select count(D.*) from (select bseq from tb_board b, (select * from tb_user where nickname like '%'||?||'%')A where b.useq = A.useq  union select bseq from tb_board where title like '%'||?||'%' or article like '%'||?||'%' and deleteart = 0)D";
	private final String BOARD_LIST_MAIN = "select * from tb_board f, (select bseq from tb_board b, (select * from tb_user where nickname like '%'||?||'%')A where b.useq = A.useq  union select bseq from tb_board where title like '%'||?||'%' or article like '%'||?||'%' and deleteart = 0)D where f.bseq = D.bseq order by f.bseq desc limit 10 offset ?";
	
	private final String BOARD_LIST_LATEST = "select * from tb_board where deleteart = 0 order by bseq desc limit 10";
	private final String BOARD_LIST_POP = "select * from tb_board where deleteart = 0 order by votesum desc limit 10";
	private final String SCROLLDOWN_LATELY_LIST = "select * from tb_board where deleteart = 0 order by bseq desc limit 10 offset ?";
	private final String SCROLLDOWN_POP_LIST = "select * from tb_board where deleteart = 0 order by votesum desc limit 10 offset ?";
	
	private final String COUNT_ALL = "select count(*) from tb_board";
	
	private final String ADDNEWBOARD_L = "select * from tb_board where bseq > ? order by bseq asc";
	private final String COUNT_NEWBOARD = "select count(*) from tb_board where bseq > ? ";
	
	private final String GET_VOTE_STAT = "select vote from tb_vote where bseq = ? and useq = ?";  // 메인 페이지에 뿌려질 List의 vote상태를 가져오는 sql
	
	// CRUD 기능 구현
	// 글 등록
	public void insertBoard(WriteBoardVO vo, UserVO uvo) {
		System.out.println(" insertBoard by Spring ");
		
		
		Object[] args = {vo.getBcode(), vo.getTitle(), uvo.getUseq(), vo.getArticle()};
		jdbcTemplate.update(BOARD_INSERT, args);
	}
	public void insertTextBoard(WriteBoardVO vo) {//"insert into tb_board(bcode, bseq, title, useq, article, deleteart, datetime) values(? ,nextval('sq_bseq'), ?, ?, ?, 0, now())";
		
	}
	public void insertLinkBoard(WriteBoardVO vo, UserVO uvo) {
		//INSERT INTO TB_BOARD(BCODE, BSEQ, TITLE, USEQ, ARTICLE, DELETEART, DATETIME, LINK) VALUES(?, NEXTVAL('SQ_BSEQ'),?,?,?,0,NOW(),?);
		Object[] args = {vo.getBcode(), vo.getTitle(), uvo.getUseq(), vo.getArticle(), vo.getLink()};
		int daoResult = jdbcTemplate.update(BOARD_INSERT_LINK, args);
		System.out.println(daoResult);
		
	}
	public void insertImageBoard(WriteBoardVO vo) {
		////INSERT INTO TB_BOARD(BCODE, BSEQ, TITLE, USEQ, ARTICLE, DELETEART, DATETIME) VALUES(?, NEXTVAL('SQ_BSEQ'),?,?,?,0,NOW());
		/// INSERT INTO tb_album() values();
	}
	public void insertVideoBoard(WriteBoardVO vo) {
		// INSERT INTO TB_BOARD(BCODE, BSEQ, TITLE, USEQ, ARTICLE, DELETEART, DATETIME) VALUES(?, NEXTVAL('SQ_BSEQ'),?,?,?,0,NOW());
	    // INSERT INTO tb_album() values();
		
	}
	
	
	// 글 수정
	/*public void updateBoard (BoardVO vo) {  // vo 언제쓰지?
		System.out.println(" updateBoard by Spring");
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}*/
	
	// 글 삭제
	public void deleteBoard (BoardVO vo) {
		System.out.println(" deleteOBoard by Spring");
		jdbcTemplate.update(BOARD_DELETE, vo.getBseq());
	}
	
	// 글 상세 조회
	public BoardVO getBoard (BoardVO vo) {
		System.out.println(" getBoard처리 by Spring");
		try {
			Object[] args1 = {vo.getBseq(), vo.getUseq()};
			String nickname = jdbcTemplate.queryForObject(GET_NICKNAME, args1, String.class);
			Object[] args = {vo.getBseq()};
			BoardVO vo1 = jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
			vo1.setNickname(nickname);
			return vo1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 글 목록 조회
	public List<BoardVO> getBoardList(int start, int end, String searchOption, String keyword) {
		System.out.println( " getBoardList by Spring ");

		
		Object[] args = {keyword, start}; 
		System.out.println("offset : " + start);
		List <BoardVO> list = new ArrayList<>();
		if (searchOption.equals("title")) {
			list =  jdbcTemplate.query(BOARD_LIST_T, args,  new BoardRowMapper());
		} 
		if (searchOption.equals("article")) {
			list =  jdbcTemplate.query(BOARD_LIST_C, args,  new BoardRowMapper());
		}
		if (searchOption.equals("nickname")) {
			list =  jdbcTemplate.query(BOARD_LIST_N, args,  new BoardRowMapper());
			System.out.println(list.size());
		}
		
		
		for ( BoardVO resvo : list) {
			Object[] args1 = {resvo.getBseq(), resvo.getUseq()};  // Using Board DB
			String nickname = jdbcTemplate.queryForObject(GET_NICKNAME, args1, String.class); // Using User DB about Board DB	
			resvo.setNickname(nickname);
		}
		
		return list;
		
	}

	public int countArticle(String searchOption, String keyword) {
		int res = 0;
		if (searchOption.equals("title")) {
			Object[] args = {keyword};	
			res = jdbcTemplate.queryForObject(COUNT_ARTICLE_T, args, Integer.class);
		} 
		if (searchOption.equals("article")) {
			Object[] args = {keyword};	
			res = jdbcTemplate.queryForObject(COUNT_ARTICLE_C, args, Integer.class);
		}
		if (searchOption.equals("nickname")) {
			Object[] args = {keyword};	
			res = jdbcTemplate.queryForObject(COUNT_ARTICLE_N, args, Integer.class);
		}
		return res;
	}

	public void increaseHit(BoardVO vo) {
		jdbcTemplate.update(INC_HIT, vo.getBseq());
		
	}

	public List<BoardVO> getLatestList(UserVO uvo) {
		System.out.println("4개의 게시판의 모든 글 불러오기");
		List<BoardVO> list = jdbcTemplate.query(BOARD_LIST_LATEST, new BoardRowMapper());
		for (BoardVO resvo : list) {
			System.out.println(resvo);
			int checkstat = 0;
			Object[] args = {resvo.getBseq(), resvo.getUseq()};  // Using Board DB
			try {
			Object[] args1 = {resvo.getBseq(), uvo.getUseq()};  // 게시글과, 현재 로그인 되어있는 유저 번호
			checkstat = jdbcTemplate.queryForObject(GET_VOTE_STAT, args1, Integer.class);//0
			
			}catch(Exception e) {
			checkstat = 0;
			}
			resvo.setCheckstat(checkstat);
			String nickname = jdbcTemplate.queryForObject(GET_NICKNAME, args, String.class);
			
			resvo.setNickname(nickname);
		}
		return list;
	}
	
	public List<BoardVO> getPopList(UserVO uvo) {
		List<BoardVO> list = jdbcTemplate.query(BOARD_LIST_POP, new BoardRowMapper());
		for (BoardVO resvo : list) {
			int checkstat = 0;
			Object[] args = {resvo.getBseq(), resvo.getUseq()};  // Using Board DB, 작성글과 작성자 번호
			try {
			Object[] args1 = {resvo.getBseq(), uvo.getUseq()};  // 게시글과, 현재 로그인 되어있는 유저 번호
			checkstat = jdbcTemplate.queryForObject(GET_VOTE_STAT, args1, Integer.class);//0
			}catch(Exception e) {
				checkstat = 0;
			}
			String nickname = jdbcTemplate.queryForObject(GET_NICKNAME, args, String.class);
			resvo.setCheckstat(checkstat);
			resvo.setNickname(nickname);
		}
		return list;
	}
	

	public List<BoardVO> infiniteScrollDown(int start, int mode, UserVO uvo) {
		Object[] args = {start};
		
		List<BoardVO> list = new ArrayList<>();
		
		if (mode == 0) {
			list = jdbcTemplate.query(SCROLLDOWN_POP_LIST, args, new BoardRowMapper());
			for (BoardVO resvo : list) {
				Object[] args2 = {resvo.getBseq(), resvo.getUseq()};  // Using Board DB, 작성글과 작성자 번호
				String nickname = jdbcTemplate.queryForObject(GET_NICKNAME, args2, String.class);
				resvo.setNickname(nickname);
				System.out.println(resvo);
				int checkstat = 0;
				try {
				Object[] args1 = {resvo.getBseq(), uvo.getUseq()};  // 게시글과, 현재 로그인 되어있는 유저 번호
				checkstat = jdbcTemplate.queryForObject(GET_VOTE_STAT, args1, Integer.class);
				}catch(Exception e) {
					checkstat = 0;
				}
				
				resvo.setCheckstat(checkstat);
			}
		} else {
			list = jdbcTemplate.query(SCROLLDOWN_LATELY_LIST, args, new BoardRowMapper());
			for (BoardVO resvo : list) {
				Object[] args2 = {resvo.getBseq(), resvo.getUseq()};  // Using Board DB, 작성글과 작성자 번호
				String nickname = jdbcTemplate.queryForObject(GET_NICKNAME, args2, String.class);
				resvo.setNickname(nickname);
				System.out.println(resvo);
				int checkstat = 0;
				try {
				Object[] args1 = {resvo.getBseq(), uvo.getUseq()};  // 게시글과, 현재 로그인 되어있는 유저 번호
				checkstat = jdbcTemplate.queryForObject(GET_VOTE_STAT, args1, Integer.class);
				}catch(Exception e) {
					checkstat = 0;
				}
				
				resvo.setCheckstat(checkstat);
			}
		}

		return list;
	}

	public int countAllArticle() {
		return jdbcTemplate.queryForObject(COUNT_ALL, Integer.class);
	}

	public List<BoardVO> addnewBoard_L(BoardVO bvo) {  // 새로운 글이 들어왔을때, 추가하는 메서드
		Object[] args = {bvo.getLastbseq()};
		List<BoardVO> list = jdbcTemplate.query(ADDNEWBOARD_L, args, new BoardRowMapper());
		for (BoardVO resvo : list) {
			Object[] args1 = {resvo.getBseq(), resvo.getUseq()};  // Using Board DB
			String nickname = jdbcTemplate.queryForObject(GET_NICKNAME, args1, String.class);
			resvo.setNickname(nickname);
		}
		return list;
	}

	public int countNewBoard(BoardVO bvo) {
		Object[] args = {bvo.getLastbseq()};
		return jdbcTemplate.queryForObject(COUNT_NEWBOARD, args, Integer.class);
	}
	

	// MainPage에서 검색했을때의 게시글 수 
	public int countArticleSearch(String keyword) {
		Object[] args = {keyword, keyword, keyword};
		return jdbcTemplate.queryForObject(COUNT_ARTICLE_MAIN, args, Integer.class);
	}

	public List<BoardVO> getMainSearchList(int start, String keyword) {
		Object[] args = {keyword, keyword, keyword, start}; 
		
		List <BoardVO> list = jdbcTemplate.query(BOARD_LIST_MAIN, args,  new BoardRowMapper());
		
		for ( BoardVO resvo : list) {
			Object[] args1 = {resvo.getBseq(), resvo.getUseq()};  // Using Board DB
			String nickname = jdbcTemplate.queryForObject(GET_NICKNAME, args1, String.class); // Using User DB about Board DB	
			resvo.setNickname(nickname);
		}
		
		return list;
	}

	
}

class BoardRowMapper implements RowMapper<BoardVO> {
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO board = new  BoardVO();
		board.setBcode(rs.getInt("BCODE"));
		board.setBseq(rs.getInt("BSEQ"));
		board.setTitle(rs.getString("TITLE"));
		board.setUseq(rs.getInt("USEQ"));
		board.setArticle(rs.getString("ARTICLE"));
		board.setDatetime(rs.getDate("DATETIME"));   //to_char 로 String   setDatetime
		board.setHit(rs.getInt("Hit"));
		board.setVotesum(rs.getInt("VOTESUM"));
		board.setLink(rs.getString("LINK"));  //링크 게시물일 경우 
		return board;
	}
}

