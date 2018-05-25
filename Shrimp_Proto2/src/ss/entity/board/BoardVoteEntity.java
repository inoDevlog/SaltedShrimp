package ss.entity.board;

import java.util.Date;

public class BoardVoteEntity {

	private int vseq; // ��ǥ��ȣ(PK)
	private int bseq; // �Խù���ȣ(FK)
	private int useq; // ����ڹ�ȣ(FK)
	private int cseq; // ��۹�ȣ(FK)
	private Date datetime; // ��������
	private int vote; // ��ǥ    // 1   -1   0   

	public BoardVoteEntity() {
		super();
	}

	public BoardVoteEntity(int vseq, int bseq, int useq, int cseq, Date datetime, int vote) {
		super();
		this.vseq = vseq;
		this.bseq = bseq;
		this.useq = useq;
		this.cseq = cseq;
		this.datetime = datetime;
		this.vote = vote;
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
		return "BoardVoteEntity [vseq=" + vseq + ", bseq=" + bseq + ", useq=" + useq + ", cseq=" + cseq + ", datetime="
				+ datetime + ", vote=" + vote + "]";
	}

}
