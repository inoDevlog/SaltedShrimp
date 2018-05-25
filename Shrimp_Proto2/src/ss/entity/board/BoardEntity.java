package ss.entity.board;

import java.util.Date;

public class BoardEntity {
	private int bcode; // �Խ���Ÿ�Թ�ȣ(FK)
	private int bseq; // �Խù���ȣ(PK)
	private String title; // �Խù�����
	private int useq; // ����ڹ�ȣ(seq / FK)
	private String article; // �Խù�����
	private Date datetime; // �Խù� �ۼ�����
	private Date updatetime; // �Խù� ��������
	private int votesum; // sum(voteup)-sum(votedown) ����   //�÷� �� ���� ����
	private int deleteart; // �Խù� ��������
	private int hit; // ��ȸ��
	private int noticeart; // �������� ����
	private String link; // ��ũ
	public BoardEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardEntity(int bcode, int bseq, String title, int useq, String article, Date datetime, Date updatetime,
			int votesum, int deleteart, int hit, int noticeart, String link) {
		super();
		this.bcode = bcode;
		this.bseq = bseq;
		this.title = title;
		this.useq = useq;
		this.article = article;
		this.datetime = datetime;
		this.updatetime = updatetime;
		this.votesum = votesum;
		this.deleteart = deleteart;
		this.hit = hit;
		this.noticeart = noticeart;
		this.link = link;
	}
	public int getBcode() {
		return bcode;
	}
	public void setBcode(int bcode) {
		this.bcode = bcode;
	}
	public int getBseq() {
		return bseq;
	}
	public void setBseq(int bseq) {
		this.bseq = bseq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getUseq() {
		return useq;
	}
	public void setUseq(int useq) {
		this.useq = useq;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public int getVotesum() {
		return votesum;
	}
	public void setVotesum(int votesum) {
		this.votesum = votesum;
	}
	public int getDeleteart() {
		return deleteart;
	}
	public void setDeleteart(int deleteart) {
		this.deleteart = deleteart;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getNoticeart() {
		return noticeart;
	}
	public void setNoticeart(int noticeart) {
		this.noticeart = noticeart;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "BoardEntity [bcode=" + bcode + ", bseq=" + bseq + ", title=" + title + ", useq=" + useq + ", article="
				+ article + ", datetime=" + datetime + ", updatetime=" + updatetime + ", votesum=" + votesum
				+ ", deleteart=" + deleteart + ", hit=" + hit + ", noticeart=" + noticeart + ", link=" + link + "]";
	}

	
	
}
