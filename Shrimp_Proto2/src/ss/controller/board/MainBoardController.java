package ss.controller.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ss.biz.board.BoardPager;
import ss.biz.board.BoardService;
import ss.vo.board.BoardVO;
import ss.vo.etc.ScrollVO;
import ss.vo.user.UserVO;

@Controller
public class MainBoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/mainBoard.do")
	public ModelAndView getLatelyBoardList (HttpSession session) {
		System.out.println("�ֽ� �� ��� ���");
		ModelAndView mav = new ModelAndView();
		UserVO uservo = new UserVO();
		
		uservo = (UserVO)session.getAttribute("userInfo");
		
		
		
		 List<BoardVO> Llist = boardService.getLatestList(uservo);  // �ֽż� �� VO 10���� ���� List
		 List<BoardVO> Plist = boardService.getPopList(uservo);     // �α�� �� VO 10���� ���� List
 		 BoardVO lastblist = Llist.get(0);
		 //System.out.println(lastblist);
 		 mav.addObject("lastbseq", lastblist.getBseq());
		 mav.addObject("LatestList", Llist);
		 mav.addObject("PopList", Plist);
		 mav.setViewName("Index4"); // Main ȭ������ ���ư� ������ ����
		
		return mav;
	}
	
	@RequestMapping(value="/mainsearch.do")
	public ModelAndView getsearchResult(@RequestParam(defaultValue="") String keyword, @RequestParam(defaultValue="1") int curPage) {
		int count = boardService.countArticleSearch(keyword);
		
		// ����¡ ó�� �ѹ��� 10������ ����ϱ� ���ؼ�
		BoardPager boardPager = new BoardPager(count, curPage);
		
		int start = boardPager.getPageBegin() - 1;
		
		List<BoardVO> list = boardService.getMainBoardSearch(start, keyword);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("slist", list);
		mav.addObject("keyword", keyword);
		mav.setViewName("searchResult");
		
		return mav;
		
	}
	
	@RequestMapping(value="/infiniteScrollDown.do", method= {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody List<BoardVO> infiniteScrollDownPOST(@RequestBody ScrollVO svo, HttpSession session) {
		
		UserVO uservo = (UserVO)session.getAttribute("userInfo");
		int cp = 0; // ���� ������ ���� ����(����¡ ó����)
		int tab_mode = svo.getMode();  // �ֽż�, �α�� �ǿ� ���� ��� ����  �α�� : 0, �ֽż� : 1 
		
		// ���� 10���� �ҷ����� ���Ͽ� ���� �������� �̵� (����¡ ó��)
		if (tab_mode == 0) {  // �α�� ��
			cp = svo.getCurrentPage_P();
		} else {
			cp = svo.getCurrentPage_L();
		}
		
		cp++;
		int count = boardService.countAllArticle();
		BoardPager boardPager = new BoardPager(count, cp);
		int start = boardPager.getPageBegin() - 1;

		List<BoardVO> scroll_list = new ArrayList<>();
		scroll_list = boardService.infiniteScrollDown(start, tab_mode, uservo);
		
		return scroll_list;
	}
	
	@RequestMapping(value="/addnewBoard.do", method=RequestMethod.POST)
	public @ResponseBody List<BoardVO> addnewLastestBoard(@RequestBody BoardVO bvo) {
		List<BoardVO> addBoardList_L = boardService.addnewboard_L(bvo);
		
		
		return addBoardList_L;
	}
	
	@RequestMapping(value="/countNewBoard.do", method=RequestMethod.POST)
	public @ResponseBody int countNewBoard(@RequestBody BoardVO bvo) {
		int num = boardService.countNewBoard(bvo);
		return num;
	}
	
	
}
