package ss.entity.board;

import java.util.Date;

public class BoardEntityList extends BoardEntity {

	private int useq;
	private String nickname;

	public BoardEntityList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardEntityList(int bcode, int bseq, String title, int useq, String article, Date datetime, Date updatetime,
			int vote, int deleteart, int hit, int noticeart, String link) {
		super(bcode, bseq, title, useq, article, datetime, updatetime, vote, deleteart, hit, noticeart, link);
		// TODO Auto-generated constructor stub
	}

	public BoardEntityList(int useq, String nickname) {
		super();
		this.useq = useq;
		this.nickname = nickname;
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

	@Override
	public String toString() {
		return "BoardEntityList [useq=" + useq + ", nickname=" + nickname + "]";
	}

}
