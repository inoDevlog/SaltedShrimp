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
		//VOTE ���̺� �����Ͱ� �����ϴ��� üũ�Ѵ�. VOTE TABLE SELECT DAO
		System.out.println("����� BIZ");
		System.out.println("VOTE ���̺� �����Ͱ� �����ϴ��� üũ�Ѵ�.");
		VoteTableCheckVO voteTableCheckVo = new VoteTableCheckVO(vo.getUseq(), vo.getBseq());
		int result = dao.selectVoteCount(voteTableCheckVo);
		System.out.println(" - result : "+result);
		//�����ϸ� //VOTE TABLE UPDATE & BOARD TABLE UPDATE DAO
		if(result != 0) {
			System.out.println("�����ϸ� //VOTE TABLE UPDATE & BOARD TABLE UPDATE DAO");
			return changeVote(vo);
			
		}else {//�������� ������ //VOTE TABLE INSERT & BOARD TABLE UPDATE DAO
			System.out.println("�������� ������ //VOTE TABLE INSERT & BOARD TABLE UPDATE DAO");
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
	
	//VOTE TABLE CHECK IN VIEW BOARD : �Խ��� �� ��ȸ �� ���� �Խù��� ���� session����ڰ� 
	//SELECT (*) where useq, bseq 
	// 0 �̸� ��ǥ�� ���� �����ϱ� countDiv = 0
	// 1�̻��̸� ��ǥ�� ���� �����ϱ� SELECT vote �÷�
	public int checkVoteInViewBoard(int useq, int bseq) {
		VoteTableCheckVO voteTableCheckVo = new VoteTableCheckVO(useq, bseq);
		int result = dao.selectVoteCount(voteTableCheckVo);
		System.out.println("����ڰ� �ش� �Խù��� VOTE �� �� �� �ִ��� ������....");
		System.out.println(" - result : "+result);
		if(result == 0) {
			//�̷��� ����...
			return result;
		}else {
			//�̷��� �����ϱ� �� ���� �����´� (VOTE)
			int res = dao.selectVoteValue(voteTableCheckVo);
			System.out.println("Biz�� �Ѿ�� DAO�� ��� �� : " + res);
			return res;
		}
		
	}

}
