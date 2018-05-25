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
	// CRUD ���
	// �� ���
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(WriteBoardVO vo, HttpServletRequest req) throws IOException {
		System.out.println("�� ��� ó�� Spring Annotation");
		System.out.println(vo);
		UserVO uvo = (UserVO) req.getSession().getAttribute("userInfo");
		
		// btype ���� ���� �� ��� ������ �޶�����.
		// 1 : �ܼ� �ؽ�Ʈ  
		// 2 : ��ũ
		// 3 : �̹���
		// 4 : ����
		switch(vo.getBtype()) {
		case 1: boardService.insertBoard(vo, uvo); break;  
		case 2: boardService.insertLinkBoard(vo, uvo); break;
		case 3: boardService.insertImageBoard(vo, uvo, req); break;
		case 4: boardService.insertVideoBoard(vo, uvo, req); break;
		}
		
		
		// ���� ���ε� ó��
		/*MultipartFile uploadFile = vo.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("C:\\Users\\Playdata\\Desktop\\Upload\\" + fileName));
		}*/
		
		//boardService.insertBoard(vo, uvo);
		return "redirect:getBoardList.do"; //�Խñ� �ۼ� �� ���� �̵�����... �۸��? �� ��ȭ��?
		
	}
	
	// �� ����
	/*@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println("�� ���� ó�� MVC Annotation");
		
		boardService.updateBoard(vo);

		return "redirect:getBoardList.do";
	}*/
	
	// �� ����
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		System.out.println("�� ���� ó�� MVC");
		
		boardService.deleteBoard(vo);

		return "redirect:getBoardList.do";
	}
	
	// �� �� ��ȸ
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model, HttpSession session) {
		System.out.println("�� �� ��ȸ ó�� MVC");
		String checkstat = "";
		
		
		// ��ȸ�� ���� ó��
		boardService.increaseHit(vo);
		vo = boardService.getBoard(vo);
		System.out.println("BOARD ��Ʈ�ѷ����� vo��"+vo);
		model.addAttribute("board", vo);//�Խñۿ� ���� VO
		model.addAttribute("commentList", boardCommentBiz.SelectListBoardComment(vo.getBseq()));//�Խñ��� commentVO List
		//VOTE TABLE���� ������ �����ϴ��� Ȯ���ϰ� 
		//������ �׳� "0"
		//������, 
		try {
			UserVO uservo = (UserVO)session.getAttribute("userInfo");
			
			int result = boardVoteBiz.checkVoteInViewBoard(uservo.getUseq(), vo.getBseq());
			System.out.println("boardVoteBiz ��� : " + result);
			checkstat = Integer.toString(result);
		}catch(NullPointerException e) {
			checkstat = "0";
			
		}
		
		
		
		model.addAttribute("checkstat", checkstat);//�Խñۿ� �ش� ����ڰ� ��ǥ�� �ߴ� ���� �ִ���... ������...
		
		return "getBoard_D";
	}

	
	// �˻� ���� ����(For Jsp)
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<>();
		conditionMap.put("����", "TITLE");
		conditionMap.put("����", "CONTENT");
		return conditionMap;
	}
	
	// �� ��� �˻�
	@RequestMapping("/getBoardList.do")
	public ModelAndView getBoardList(@RequestParam(defaultValue="title") String searchOption,
							   @RequestParam(defaultValue="") String keyword, 
							   @RequestParam(defaultValue="1") int curPage 
							   ) {
		System.out.println("�� ��� �˻� ó�� MVC");
		// ���ڵ��� ���� ���
	    int count = boardService.countArticle(searchOption, keyword);
	    System.out.println("searchOption : " + searchOption + "keyWord"+ keyword);
	    
	    // ������ ������ ���� ó��
	    BoardPager boardPager = new BoardPager(count, curPage);
	    
	    int start = boardPager.getPageBegin() - 1;
	    System.out.println(start);
	    int end = boardPager.getPageEnd();
	    
	    List<BoardVO> list = boardService.getBoardList(start, end, searchOption, keyword);
	    
	    // �����͸� �ʿ� ���� 
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("list", list); // list
	    map.put("count", count); // ���ڵ��� ����
	    map.put("searchOption", searchOption); // �˻��ɼ�
	    map.put("keyword", keyword); // �˻�Ű����
	    map.put("boardPager", boardPager);
	    
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("map", map); // �ʿ� ����� �����͸� mav�� ����
	    mav.setViewName("dukjilList"); // �並 dukjilList.jsp�� ����
		// Null Check
		// if (vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		// if (vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		
		// model.addAttribute("boardList", boardService.getBoardList(vo));
		
		return mav;
	}
}
