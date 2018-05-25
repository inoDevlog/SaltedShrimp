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
		BoardVoteResultVO resultVO = new BoardVoteResultVO();//DB���� �� view������ ������ ��ü
		int result = 0;
		System.out.println("�Խù� vote controller");
		System.out.println(" - BoardVoteViewVO : "+vo);
		//���� checkTable ���� Ȯ�� �Ѵ�. String
		if(vo.getCheckTable().equals("1")) {
			//VOTE TABLE CHECK - BIZ
			System.out.println("VOTE TABLE CHECK - BIZ�� ����.");
			result = biz.checkVoteTable(vo);
		}else {
			//VOTE TABLE UPDATE & BOARD TABLE UPDATE - BIZ
			System.out.println("VOTE TABLE UPDATE & BOARD TABLE UPDATE - BIZ�� ����.");
			result = biz.changeVote(vo);
		}
		resultVO.setResult(result);
		
		//TABLE ������ �̷����� BOARD TABLE ���� VOTESUM ���� resultVO�� �ִ´�.
		  //BOARD TABLE SELECT VOTESUM - BIZ
		System.out.println("BOARD TABLE SELECT VOTESUM - BIZ�� ����.");
		resultVO.setVotesum(biz.getVotesum(vo.getBseq()));
		
		return resultVO;
		
	}

	
	
}

