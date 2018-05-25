package ss.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ss.biz.board.BoardCommentBiz;
import ss.vo.board.BoardCommentVO;

@Controller
public class BoardCommentController {

	@Autowired
	private BoardCommentBiz biz;

	// Create Comment
	@RequestMapping(value = "/insertBoardComment.do", method = RequestMethod.POST)
	public @ResponseBody List<BoardCommentVO> InsertBoardComment(@RequestBody BoardCommentVO vo, HttpServletResponse response) {  
		System.out.println("댓글 입력 컨트롤러");
		//새로운 댓글 데이터 DB에 넣는 BIZ
		int res = biz.InsertBoardComment(vo);
		System.out.println("res :" + res); // res 체크
		//댓글 데이터 가져오기 BIZ
		List<BoardCommentVO> commentList = biz.SelectListBoardComment(vo.getBseq());
		return commentList;
		
	}

	// Delete Comment
	@RequestMapping(value = "/DeleteBoardComment.do", method = RequestMethod.GET)
	public ModelAndView DeleteBoardComment(@RequestParam("deleteComment") String deleteComment, HttpServletRequest request) {
		System.out.println("댓글 삭제 컨트롤러");
		int cseq = Integer.parseInt(request.getParameter("cseq"));
		int useq = Integer.parseInt(request.getParameter("useq"));
		System.out.println("cseq : " + cseq);
		System.out.println("useq : " + useq);
		BoardCommentVO vo = new BoardCommentVO();
		vo.setCseq(cseq);
		vo.setUseq(useq);
		int res = biz.DeleteBoardComment(vo);
		System.out.println("res : " + res); //res 체크
		ModelAndView mav = new ModelAndView("/BoardListSelect.do", "result", res);
		return mav;

	}
}
