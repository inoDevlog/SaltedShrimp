package ss.biz.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import ss.dao.board.BoardDAOSpring;
import ss.vo.board.BoardVO;
import ss.vo.board.WriteBoardVO;
import ss.vo.user.UserVO;



@Service("boardService")
public class BoardBiz implements BoardService{

	@Autowired
	private BoardDAOSpring boardDAO;

	

	

/*	@Override
	public void updateBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDAO.updateBoard(vo);
	}*/

	@Override
	public void deleteBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDAO.deleteBoard(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		vo = boardDAO.getBoard(vo);
		System.out.println(vo);
		
		//��Ʃ�곪 Ʈ������ ��� iframe���� ��� �ϱ� ���ؼ� sublink�� �־�α�
		int bcode = vo.getBcode();
		if(bcode==5 || bcode==6) {
			vo.setSublink(subLink(vo.getLink()));
			
		}
		return vo;
	}
	
	public String subLink(String link) {
		link = link.trim(); // ���� ����
		int linkLength = link.length(); //link ��ü ����
		int startIndex = link.lastIndexOf("/")+1; //������  '/'��ġ + 1
		String sublink = link.substring(startIndex, linkLength); //link���� ����Ű�� ����
		if(sublink.startsWith("watch?")) {
			sublink = sublink.substring(8);
		}
		//System.out.println(sublink); //üũ��
		return sublink;
		
	}
	

	@Override
	public List<BoardVO> getBoardList(int start, int end, String searchOption, String keyword) {
		// TODO Auto-generated method stub
		return boardDAO.getBoardList(start, end, searchOption, keyword);
	}

	@Override
	public int countArticle(String searchOption, String keyword) {
		// TODO Auto-generated method stub
		return boardDAO.countArticle(searchOption, keyword);
	}

	@Override
	public void increaseHit(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDAO.increaseHit(vo);
	}

	@Override
	public List<BoardVO> getLatestList(UserVO uvo) {
		List<BoardVO> BoardVOList = boardDAO.getLatestList(uvo);
		for(BoardVO vo:BoardVOList) {
			int bcode = vo.getBcode();
			if(bcode==5 || bcode==6) {
				vo.setSublink(subLink(vo.getLink()));
			}
			System.out.println(vo);
		}
		return BoardVOList;
	}
	
	

	@Override
	public int countAllArticle() {
		// TODO Auto-generated method stub
		return boardDAO.countAllArticle();
	}

	@Override
	public List<BoardVO> getPopList(UserVO uvo) {
		List<BoardVO> BoardVOList = boardDAO.getPopList(uvo);
		for(BoardVO vo:BoardVOList) {
			int bcode = vo.getBcode();
			if(bcode==5 || bcode==6) {
				vo.setSublink(subLink(vo.getLink()));
			}
			
		}
		return BoardVOList;
	}

	@Override
	public List<BoardVO> infiniteScrollDown(int bseqToStart, int mode, UserVO uvo) {
		List<BoardVO> infiniteBoardVOList = boardDAO.infiniteScrollDown(bseqToStart, mode, uvo);
		for(BoardVO vo:infiniteBoardVOList) {
			int bcode = vo.getBcode();
			if(bcode==5 || bcode==6) {
				vo.setSublink(subLink(vo.getLink()));
			}
		}
		return infiniteBoardVOList;
	}

	@Override
	public List<BoardVO> addnewboard_L(BoardVO bvo) {
		return boardDAO.addnewBoard_L(bvo);
	}

	@Override
	public int countNewBoard(BoardVO bvo) {
		// TODO Auto-generated method stub
		return boardDAO.countNewBoard(bvo);
	}

	@Override
	public void insertBoard(WriteBoardVO vo, UserVO uvo) {
		vo.setBcode(1); // ���� + �ؽ�Ʈ
		boardDAO.insertBoard(vo, uvo);
		
	}

	@Override
	public void insertLinkBoard(WriteBoardVO vo, UserVO uvo) {
		// TODO Auto-generated method stub
		System.out.println(vo);
		switch(vo.getCcode()) {
		case 1: vo.setBcode(2); break; //���� + ��ũ
		case 2: vo.setBcode(5); break; //��Ʃ�� + ��ũ
		case 3: vo.setBcode(6); break; //Ʈ���� + ��ũ
		}
		boardDAO.insertLinkBoard(vo, uvo);
		
		
	}


	@Override
	public void insertImageBoard(WriteBoardVO vo, UserVO uvo, HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void insertVideoBoard(WriteBoardVO vo, UserVO uvo, HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int countArticleSearch(String keyword) {
		return boardDAO.countArticleSearch(keyword);
	}



	@Override
	public List<BoardVO> getMainBoardSearch(int start, String keyword) {
		
		return boardDAO.getMainSearchList(start, keyword);
	}

}
