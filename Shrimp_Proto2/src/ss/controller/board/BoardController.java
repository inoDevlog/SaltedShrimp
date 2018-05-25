package ss.controller.board;

import java.io.File;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ss.biz.board.BoardCommentBiz;
import ss.biz.board.BoardPager;
import ss.biz.board.BoardService;
import ss.biz.board.BoardVoteBiz;
import ss.vo.board.BoardVO;
import ss.vo.board.WriteBoardVO;
import ss.vo.user.UserVO;
import ss.controller.user.LoginController;

@Controller
@SessionAttributes("board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardCommentBiz boardCommentBiz;
	@Autowired
	private BoardVoteBiz boardVoteBiz;
	// CRUD 기능
	// 글 등록
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(WriteBoardVO vo, HttpServletRequest req) throws IOException {
		System.out.println("글 등록 처리 Spring Annotation");
		System.out.println(vo);
		UserVO uvo = (UserVO) req.getSession().getAttribute("userInfo");
		
		// btype 값에 따라서 글 등록 로직이 달라진다.
		// 1 : 단순 텍스트  
		// 2 : 링크
		// 3 : 이미지
		// 4 : 비디오
		switch(vo.getBtype()) {
		case 1: boardService.insertBoard(vo, uvo); break;  
		case 2: boardService.insertLinkBoard(vo, uvo); break;
		case 3: boardService.insertImageBoard(vo, uvo, req); break;
		case 4: boardService.insertVideoBoard(vo, uvo, req); break;
		}
		
		
		// 파일 업로드 처리
		/*MultipartFile uploadFile = vo.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("C:\\Users\\Playdata\\Desktop\\Upload\\" + fileName));
		}*/
		
		//boardService.insertBoard(vo, uvo);
		return "redirect:getBoardList.do"; //게시글 작성 후 어디로 이동할지... 글목록? 글 상세화면?
		
	}
	
	// 글 수정
	/*@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println("글 수정 처리 MVC Annotation");
		
		boardService.updateBoard(vo);

		return "redirect:getBoardList.do";
	}*/
	
	// 글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		System.out.println("글 삭제 처리 MVC");
		
		boardService.deleteBoard(vo);

		return "redirect:getBoardList.do";
	}
	
	// 글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model, HttpSession session) {
		System.out.println("글 상세 조회 처리 MVC");
		String checkstat = "";
		
		
		// 조회수 증가 처리
		boardService.increaseHit(vo);
		vo = boardService.getBoard(vo);
		System.out.println("BOARD 컨트롤러에서 vo값"+vo);
		model.addAttribute("board", vo);//게시글에 대한 VO
		model.addAttribute("commentList", boardCommentBiz.SelectListBoardComment(vo.getBseq()));//게시글의 commentVO List
		//VOTE TABLE에서 데이터 존재하는지 확인하고 
		//없으면 그냥 "0"
		//있으면, 
		try {
			UserVO uservo = (UserVO)session.getAttribute("userInfo");
			
			int result = boardVoteBiz.checkVoteInViewBoard(uservo.getUseq(), vo.getBseq());
			System.out.println("boardVoteBiz 결과 : " + result);
			checkstat = Integer.toString(result);
		}catch(NullPointerException e) {
			checkstat = "0";
			
		}
		
		
		
		model.addAttribute("checkstat", checkstat);//게시글에 해당 사용자가 투표를 했던 적이 있는지... 없는지...
		
		return "getBoard_D";
	}

	
	// 검색 조건 설정(For Jsp)
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}
	
	// 글 목록 검색
	@RequestMapping("/getBoardList.do")
	public ModelAndView getBoardList(@RequestParam(defaultValue="title") String searchOption,
							   @RequestParam(defaultValue="") String keyword, 
							   @RequestParam(defaultValue="1") int curPage 
							   ) {
		System.out.println("글 목록 검색 처리 MVC");
		// 레코드의 갯수 계산
	    int count = boardService.countArticle(searchOption, keyword);
	    System.out.println("searchOption : " + searchOption + "keyWord"+ keyword);
	    
	    // 페이지 나누기 관련 처리
	    BoardPager boardPager = new BoardPager(count, curPage);
	    
	    int start = boardPager.getPageBegin() - 1;
	    System.out.println(start);
	    int end = boardPager.getPageEnd();
	    
	    List<BoardVO> list = boardService.getBoardList(start, end, searchOption, keyword);
	    
	    // 데이터를 맵에 저장 
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("list", list); // list
	    map.put("count", count); // 레코드의 갯수
	    map.put("searchOption", searchOption); // 검색옵션
	    map.put("keyword", keyword); // 검색키워드
	    map.put("boardPager", boardPager);
	    
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("map", map); // 맵에 저장된 데이터를 mav에 저장
	    mav.setViewName("dukjilList"); // 뷰를 dukjilList.jsp로 설정
		// Null Check
		// if (vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		// if (vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		
		// model.addAttribute("boardList", boardService.getBoardList(vo));
		
		return mav;
	}
}
