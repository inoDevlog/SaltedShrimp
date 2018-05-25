package ss.vo.board;

import java.util.Date;

public class WriteBoardVO {//�Խù� �ۼ��� ����ϴ� vo
	
	private int btype; //1, 2, 3, 4
	private int ccode;  //1, 2, 3
	private String title; // �Խù�����
	private String article; // �Խù�����
	private String link; // ��ũ
	private int useq; // ����ڹ�ȣ(seq / FK)
	private int bcode; // �Խ���Ÿ�Թ�ȣ(FK)
	
	
	public int getBtype() {
		return btype;
	}
	public void setBtype(int btype) {
		this.btype = btype;
	}
	public int getUseq() {
		return useq;
	}
	public void setUseq(int useq) {
		this.useq = useq;
	}
	public int getCcode() {
		return ccode;
	}
	public void setCcode(int ccode) {
		this.ccode = ccode;
	}
	public int getBcode() {
		return bcode;
	}
	public void setBcode(int bcode) {
		this.bcode = bcode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public WriteBoardVO(int btype, int ccode, String title, String article, String link, int useq, int bcode) {
		super();
		this.btype = btype;
		this.ccode = ccode;
		this.title = title;
		this.article = article;
		this.link = link;
		this.useq = useq;
		this.bcode = bcode;
	}
	public WriteBoardVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "WriteBoardVO [btype=" + btype + ", ccode=" + ccode + ", title=" + title + ", article=" + article
				+ ", link=" + link + ", useq=" + useq + ", bcode=" + bcode + "]";
	}
	
	
	
	
	
	
	//private int bseq; // �Խù���ȣ(PK)
	//private String nickname; // �г���   //�ʿ��ϸ� �����ô�
	//private Date datetime; // �Խù� �ۼ�����
	//private Date updatetime; // �Խù� ��������
	//private int votesum; // sum(voteup)-sum(votedown) ���� //�÷� �� ���� ����
	//private int deleteart; // �Խù� ��������
	//private int hit; // ��ȸ��
	//private int noticeart; // �������� ����
	
	
	
	
	
	
	

	
	
	
}
