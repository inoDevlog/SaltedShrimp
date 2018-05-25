package ss.vo.board;

import java.util.Date;

public class WriteBoardVO {//게시물 작성시 사용하는 vo
	
	private int btype; //1, 2, 3, 4
	private int ccode;  //1, 2, 3
	private String title; // 게시물제목
	private String article; // 게시물내용
	private String link; // 링크
	private int useq; // 사용자번호(seq / FK)
	private int bcode; // 게시판타입번호(FK)
	
	
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
	
	
	
	
	
	
	//private int bseq; // 게시물번호(PK)
	//private String nickname; // 닉네임   //필요하면 넣읍시당
	//private Date datetime; // 게시물 작성일자
	//private Date updatetime; // 게시물 수정일자
	//private int votesum; // sum(voteup)-sum(votedown) 갯수 //컬럼 명 수정 예정
	//private int deleteart; // 게시물 삭제여부
	//private int hit; // 조회수
	//private int noticeart; // 공지사항 여부
	
	
	
	
	
	
	

	
	
	
}
