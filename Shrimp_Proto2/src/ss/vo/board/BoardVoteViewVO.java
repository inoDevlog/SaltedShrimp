package ss.vo.board;

import java.util.Date;

public class BoardVoteViewVO {

	private int vseq; // 투표변호(PK)
	private int bseq; // 게시물번호(FK)//
	private int useq; // 사용자번호(FK)//
	private int cseq; // 댓글번호(FK)
	private Date datetime; // 생성일자
	private int vote; // 투표
	//추가 (view 단에서 ajax 를 통해서 서버로 보내지는 추가 데이터)
	private int votesum; //board 테이블에 들어간 내용
	private String checkTable;

	public int getVotesum() {
		return votesum;
	}

	public void setVotesum(int votesum) {
		this.votesum = votesum;
	}

	public String getCheckTable() {
		return checkTable;
	}

	public void setCheckTable(String checkTable) {
		this.checkTable = checkTable;
	}

	public BoardVoteViewVO() {
		super();
	}

	

	public BoardVoteViewVO(int vseq, int bseq, int useq, int cseq, Date datetime, int vote, int votesum, String checkTable) {
		super();
		this.vseq = vseq;
		this.bseq = bseq;
		this.useq = useq;
		this.cseq = cseq;
		this.datetime = datetime;
		this.vote = vote;
		this.votesum = votesum;
		this.checkTable = checkTable;
	}

	public int getVseq() {
		return vseq;
	}

	public void setVseq(int vseq) {
		this.vseq = vseq;
	}

	public int getBseq() {
		return bseq;
	}

	public void setBseq(int bseq) {
		this.bseq = bseq;
	}

	public int getUseq() {
		return useq;
	}

	public void setUseq(int useq) {
		this.useq = useq;
	}

	public int getCseq() {
		return cseq;
	}

	public void setCseq(int cseq) {
		this.cseq = cseq;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	@Override
	public String toString() {
		return "BoardVoteVO [vseq=" + vseq + ", bseq=" + bseq + ", useq=" + useq + ", cseq=" + cseq + ", datetime="
				+ datetime + ", vote=" + vote + ", votesum=" + votesum + ", checkTable=" + checkTable + "]";
	}



}
