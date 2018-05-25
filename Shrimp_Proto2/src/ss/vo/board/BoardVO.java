package ss.vo.board;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private int bcode; // �Խ���Ÿ�Թ�ȣ(FK)
	private int bseq; // �Խù���ȣ(PK)
	private String title; // �Խù�����
	private int useq; // ����ڹ�ȣ(seq / FK)
	private String nickname; // �г���
	private String article; // �Խù�����
	private Date datetime; // �Խù� �ۼ�����
	private Date updatetime; // �Խù� ��������
	private int votesum; // sum(voteup)-sum(votedown) ���� //�÷� �� ���� ����
	private int deleteart; // �Խù� ��������
	private int hit; // ��ȸ��
	private int noticeart; // �������� ����
	private String link; // ��ũ
	private int lastbseq; // �ֽ��� �� ������ ���� ��ȣ
	private int checkstat;
	private String sublink;//link�� ������
	
	
	


	public BoardVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardVO(int bcode, int bseq, String title, int useq, String nickname, String article, Date datetime,
			Date updatetime, int votesum, int deleteart, int hit, int noticeart, String link, int lastbseq,
			int checkstat, String sublink) {
		super();
		this.bcode = bcode;
		this.bseq = bseq;
		this.title = title;
		this.useq = useq;
		this.nickname = nickname;
		this.article = article;
		this.datetime = datetime;
		this.updatetime = updatetime;
		this.votesum = votesum;
		this.deleteart = deleteart;
		this.hit = hit;
		this.noticeart = noticeart;
		this.link = link;
		this.lastbseq = lastbseq;
		this.checkstat = checkstat;
		this.sublink = sublink;
	}

	public int getCheckstat() {
		return checkstat;
	}

	public void setCheckstat(int checkstat) {
		this.checkstat = checkstat;
	}

	public int getLastbseq() {
		return lastbseq;
	}

	public void setLastbseq(int lastbseq) {
		this.lastbseq = lastbseq;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Date getDatetime() {  //����
		return datetime;
	}

	public void setDatetime(Date datetime) {//����
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

	
	
	public String getSublink() {
		return sublink;
	}

	public void setSublink(String sublink) {
		this.sublink = sublink;
	}

	@Override
	public String toString() {
		return "BoardVO [bcode=" + bcode + ", bseq=" + bseq + ", title=" + title + ", useq=" + useq + ", nickname="
				+ nickname + ", article=" + article + ", datetime=" + datetime + ", updatetime=" + updatetime
				+ ", votesum=" + votesum + ", deleteart=" + deleteart + ", hit=" + hit + ", noticeart=" + noticeart
				+ ", link=" + link + ", lastbseq=" + lastbseq + ", checkstat=" + checkstat + ", sublink=" + sublink
				+ "]";
	}

	

}
