package ss.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ss.biz.board.BoardVoteBiz;
import ss.vo.board.BoardVoteResultVO;
import ss.vo.board.BoardVoteViewVO;

@Controller
public class BoardVoteController {
	
	@Autowired
	BoardVoteBiz biz;
	
	@RequestMapping(value = "/changeVote.do", method = RequestMethod.POST)
	public @ResponseBody BoardVoteResultVO changeVote(@RequestBody BoardVoteViewVO vo) {
		BoardVoteResultVO resultVO = new BoardVoteResultVO();//DB수정 후 view단으로 던져줄 객체
		int result = 0;
		System.out.println("게시물 vote controller");
		System.out.println(" - BoardVoteViewVO : "+vo);
		//먼저 checkTable 값을 확인 한다. String
		if(vo.getCheckTable().equals("1")) {
			//VOTE TABLE CHECK - BIZ
			System.out.println("VOTE TABLE CHECK - BIZ로 간다.");
			result = biz.checkVoteTable(vo);
		}else {
			//VOTE TABLE UPDATE & BOARD TABLE UPDATE - BIZ
			System.out.println("VOTE TABLE UPDATE & BOARD TABLE UPDATE - BIZ로 간다.");
			result = biz.changeVote(vo);
		}
		resultVO.setResult(result);
		
		//TABLE 수정이 이뤄지면 BOARD TABLE 에서 VOTESUM 값을 resultVO에 넣는다.
		  //BOARD TABLE SELECT VOTESUM - BIZ
		System.out.println("BOARD TABLE SELECT VOTESUM - BIZ로 간다.");
		resultVO.setVotesum(biz.getVotesum(vo.getBseq()));
		
		return resultVO;
		
	}

	
	
}

