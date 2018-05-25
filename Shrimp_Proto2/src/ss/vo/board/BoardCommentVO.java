package ss.vo.board;

import java.util.Date;

public class BoardCommentVO {
	
	private int bseq; // �Խù���ȣ(FK)
	private int cseq; // ��۹�ȣ(PK)
	private int useq; // ����ڹ�ȣ(FK)
	private String comment; // ��۳���
	private int comord; // ��ۼ���
	private String datetime; // ����ۼ�����   //����
	private int deletecom; // ��ۻ�������
	private int vote; // ��� vote count
	private String nickname;
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public BoardCommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardCommentVO(int bseq, int cseq, int useq, String comment, int comord, String datetime, int deletecom,
			int vote, String nickname) {
		super();
		this.bseq = bseq;
		this.cseq = cseq;
		this.useq = useq;
		this.comment = comment;
		this.comord = comord;
		this.datetime = datetime;
		this.deletecom = deletecom;
		this.vote = vote;
		this.nickname = nickname;
	}

	public int getBseq() {
		return bseq;
	}

	public void setBseq(int bseq) {
		this.bseq = bseq;
	}

	public int getCseq() {
		return cseq;
	}

	public void setCseq(int cseq) {
		this.cseq = cseq;
	}

	public int getUseq() {
		return useq;
	}

	public void setUseq(int useq) {
		this.useq = useq;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getComord() {
		return comord;
	}

	public void setComord(int comord) {
		this.comord = comord;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public int getDeletecom() {
		return deletecom;
	}

	public void setDeletecom(int deletecom) {
		this.deletecom = deletecom;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	@Override
	public String toString() {
		return "BoardCommentVO [bseq=" + bseq + ", cseq=" + cseq + ", useq=" + useq + ", comment=" + comment
				+ ", comord=" + comord + ", datetime=" + datetime + ", deletecom=" + deletecom + ", vote=" + vote
				+ ", nickname=" + nickname + "]";
	}

	
	
	
	
}
