package ss.vo.board;

public class BoardVoteResultVO {
	
	private int result ;  //VOTE TABLE, BOARD TABLE 수정 완료 되면 2가 담긴다
	private int votesum ; //BOARD의 votesum 값을 담는다.
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
