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
		System.out.println("최신 글 목록 출력");
		ModelAndView mav = new ModelAndView();
		UserVO uservo = new UserVO();
		
		uservo = (UserVO)session.getAttribute("userInfo");
		
		
		
		 List<BoardVO> Llist = boardService.getLatestList(uservo);  // 최신순 탭 VO 10개를 담은 List
		 List<BoardVO> Plist = boardService.getPopList(uservo);     // 인기순 탭 VO 10개를 담은 List
 		 BoardVO lastblist = Llist.get(0);
		 //System.out.println(lastblist);
 		 mav.addObject("lastbseq", lastblist.getBseq());
		 mav.addObject("LatestList", Llist);
		 mav.addObject("PopList", Plist);
		 mav.setViewName("Index4"); // Main 화면으로 돌아갈 페이지 설정
		
		return mav;
	}
	
	@RequestMapping(value="/mainsearch.do")
	public ModelAndView getsearchResult(@RequestParam(defaultValue="") String keyword, @RequestParam(defaultValue="1") int curPage) {
		int count = boardService.countArticleSearch(keyword);
		
		// 페이징 처리 한번에 10개씩만 출력하기 위해서
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
		int cp = 0; // 현재 페이지 담을 변수(페이징 처리용)
		int tab_mode = svo.getMode();  // 최신순, 인기순 탭에 따른 모드 설정  인기순 : 0, 최신순 : 1 
		
		// 다음 10개를 불러오기 위하여 다음 페이지로 이동 (페이징 처리)
		if (tab_mode == 0) {  // 인기순 탭
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
