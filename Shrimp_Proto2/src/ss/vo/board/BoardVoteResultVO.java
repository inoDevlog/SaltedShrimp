package ss.vo.board;

public class BoardVoteResultVO {
	
	private int result ;  //VOTE TABLE, BOARD TABLE ���� �Ϸ� �Ǹ� 2�� ����
	private int votesum ; //BOARD�� votesum ���� ��´�.
	public BoardVoteResultVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardVoteResultVO(int result, int votesum) {
		super();
		this.result = result;
		this.votesum = votesum;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getVotesum() {
		return votesum;
	}
	public void setVotesum(int votesum) {
		this.votesum = votesum;
	}
	
	
	
	

}
