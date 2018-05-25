package ss.vo.board;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private int bcode; // 게시판타입번호(FK)
	private int bseq; // 게시물번호(PK)
	private String title; // 게시물제목
	private int useq; // 사용자번호(seq / FK)
	private String nickname; // 닉네임
	private String article; // 게시물내용
	private Date datetime; // 게시물 작성일자
	private Date updatetime; // 게시물 수정일자
	private int votesum; // sum(voteup)-sum(votedown) 갯수 //컬럼 명 수정 예정
	private int deleteart; // 게시물 삭제여부
	private int hit; // 조회수
	private int noticeart; // 공지사항 여부
	private String link; // 링크
	private int lastbseq; // 최신탭 글 갱신을 위한 번호
	private int checkstat;
	private String sublink;//link의 고유값
	
	
	


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

	public Date getDatetime() {  //수정
		return datetime;
	}

	public void setDatetime(Date datetime) {//수정
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
