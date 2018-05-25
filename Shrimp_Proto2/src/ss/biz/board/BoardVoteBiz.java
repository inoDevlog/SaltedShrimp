package ss.biz.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ss.dao.board.BoardVoteDAO;
import ss.vo.board.BoardVoteViewVO;
import ss.vo.board.VoteTableCheckVO;

@Service
public class BoardVoteBiz {
	
	@Autowired
	BoardVoteDAO dao;
	
	//VOTE TABLE CHECK
	public int checkVoteTable(BoardVoteViewVO vo) {
		//VOTE 테이블에 테이터가 존재하는지 체크한다. VOTE TABLE SELECT DAO
		System.out.println("여기는 BIZ");
		System.out.println("VOTE 테이블에 테이터가 존재하는지 체크한다.");
		VoteTableCheckVO voteTableCheckVo = new VoteTableCheckVO(vo.getUseq(), vo.getBseq());
		int result = dao.selectVoteCount(voteTableCheckVo);
		System.out.println(" - result : "+result);
		//존재하면 //VOTE TABLE UPDATE & BOARD TABLE UPDATE DAO
		if(result != 0) {
			System.out.println("존재하면 //VOTE TABLE UPDATE & BOARD TABLE UPDATE DAO");
			return changeVote(vo);
			
		}else {//존재하지 않으면 //VOTE TABLE INSERT & BOARD TABLE UPDATE DAO
			System.out.println("존재하지 않으면 //VOTE TABLE INSERT & BOARD TABLE UPDATE DAO");
			int VoteTbResult = dao.insertVote(vo);
			int BoardTbResult = dao.updateBoardTable(vo);
			return VoteTbResult+BoardTbResult;
		}
		
	}
	
	
	
	
	
	//VOTE TABLE UPDATE & BOARD TABLE UPDATE
	public int changeVote(BoardVoteViewVO vo) {
		//VOTE TABLE UPDATE DAO
		System.out.println("VOTE TABLE UPDATE DAO");
		int VoteTbResult = dao.updateVoteTable(vo);
		System.out.println(" - result : " +VoteTbResult );
		//BOARD TABLE UPDATE DAO
		System.out.println("BOARD TABLE UPDATE DAO");
		int BoardTbResult = dao.updateBoardTable(vo);
		System.out.println(" - result : " +BoardTbResult );
		
		return VoteTbResult+BoardTbResult;
	}
	
	
	//BOARD TABLE SELECT VOTESUM - BIZ
	public int getVotesum(int bseq) {
		System.out.println("BOARD TABLE SELECT VOTESUM - BIZ");
		return dao.selectVotesum(bseq);
	}
	
	//VOTE TABLE CHECK IN VIEW BOARD : 게시판 상세 조회 시 현재 게시물에 대해 session사용자가 
	//SELECT (*) where useq, bseq 
	// 0 이면 투표한 적이 없으니깐 countDiv = 0
	// 1이상이면 투표한 적이 있으니깐 SELECT vote 컬럼
	public int checkVoteInViewBoard(int useq, int bseq) {
		VoteTableCheckVO voteTableCheckVo = new VoteTableCheckVO(useq, bseq);
		int result = dao.selectVoteCount(voteTableCheckVo);
		System.out.println("사용자가 해당 게시물에 VOTE 를 한 적 있는지 없는지....");
		System.out.println(" - result : "+result);
		if(result == 0) {
			//이력이 없다...
			return result;
		}else {
			//이력이 있으니깐 그 값을 가져온다 (VOTE)
			int res = dao.selectVoteValue(voteTableCheckVo);
			System.out.println("Biz로 넘어온 DAO의 결과 값 : " + res);
			return res;
		}
		
	}

}
