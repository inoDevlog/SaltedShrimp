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
		System.out.println("��� �Է� ��Ʈ�ѷ�");
		//���ο� ��� ������ DB�� �ִ� BIZ
		int res = biz.InsertBoardComment(vo);
		System.out.println("res :" + res); // res üũ
		//��� ������ �������� BIZ
		List<BoardCommentVO> commentList = biz.SelectListBoardComment(vo.getBseq());
		return commentList;
		
	}

	// Delete Comment
	@RequestMapping(value = "/DeleteBoardComment.do", method = RequestMethod.GET)
	public ModelAndView DeleteBoardComment(@RequestParam("deleteComment") String deleteComment, HttpServletRequest request) {
		System.out.println("��� ���� ��Ʈ�ѷ�");
		int cseq = Integer.parseInt(request.getParameter("cseq"));
		int useq = Integer.parseInt(request.getParameter("useq"));
		System.out.println("cseq : " + cseq);
		System.out.println("useq : " + useq);
		BoardCommentVO vo = new BoardCommentVO();
		vo.setCseq(cseq);
		vo.setUseq(useq);
		int res = biz.DeleteBoardComment(vo);
		System.out.println("res : " + res); //res üũ
		ModelAndView mav = new ModelAndView("/BoardListSelect.do", "result", res);
		return mav;

	}
}
