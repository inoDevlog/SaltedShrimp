package ss.vo.board;

public class VoteTableCheckVO {
	//VOTE TABLE �� ���� �����ϴ��� Ȯ���Ҷ�.. 
	private int useq;
	private int bseq;
	public VoteTableCheckVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VoteTableCheckVO(int useq, int bseq) {
		super();
		this.useq = useq;
		this.bseq = bseq;
	}
	public int getUseq() {
		return useq;
	}
	public void setUseq(int useq) {
		this.useq = useq;
	}
	public int getBseq() {
		return bseq;
	}
	public void setBseq(int bseq) {
		this.bseq = bseq;
	}
	@Override
	public String toString() {
		return "VoteTableCheckVO [useq=" + useq + ", bseq=" + bseq + "]";
	}
	
	
	

}
