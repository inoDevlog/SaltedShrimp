package ss.entity.board;

public class BoardAlbumEntity {
	private int aseq; // 앨범게시판번호(seq)
	private int bseq; // 게시글번호(seq / FK)
	private int useq; // 사용자번호(seq / FK)
	private String origin_image; // 원본사진이름
	private String save_image; // 저장사진이름
	private int i_type; // 사진타입(0:가로사진,1:세로사진)
	private String origin_video; // 원본동영상이름
	private String save_video; // 저장동영상이름
	private int v_type; // 동영상타입(0:가로동영상,1:세로동영상)
	private String savefolder; // 저장폴더

	public BoardAlbumEntity() {
		super();
	}

	public BoardAlbumEntity(int aseq, int bseq, int useq, String origin_image, String save_image, int i_type,
			String origin_video, String save_video, int v_type, String savefolder) {
		super();
		this.aseq = aseq;
		this.bseq = bseq;
		this.useq = useq;
		this.origin_image = origin_image;
		this.save_image = save_image;
		this.i_type = i_type;
		this.origin_video = origin_video;
		this.save_video = save_video;
		this.v_type = v_type;
		this.savefolder = savefolder;
	}

	public int getAseq() {
		return aseq;
	}

	public void setAseq(int aseq) {
		this.aseq = aseq;
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

	public String getOrigin_image() {
		return origin_image;
	}

	public void setOrigin_image(String origin_image) {
		this.origin_image = origin_image;
	}

	public String getSave_image() {
		return save_image;
	}

	public void setSave_image(String save_image) {
		this.save_image = save_image;
	}

	public int getI_type() {
		return i_type;
	}

	public void setI_type(int i_type) {
		this.i_type = i_type;
	}

	public String getOrigin_video() {
		return origin_video;
	}

	public void setOrigin_video(String origin_video) {
		this.origin_video = origin_video;
	}

	public String getSave_video() {
		return save_video;
	}

	public void setSave_video(String save_video) {
		this.save_video = save_video;
	}

	public int getV_type() {
		return v_type;
	}

	public void setV_type(int v_type) {
		this.v_type = v_type;
	}

	public String getSavefolder() {
		return savefolder;
	}

	public void setSavefolder(String savefolder) {
		this.savefolder = savefolder;
	}

	@Override
	public String toString() {
		return "BoardAlbumEntity [aseq=" + aseq + ", bseq=" + bseq + ", useq=" + useq + ", origin_image=" + origin_image
				+ ", save_image=" + save_image + ", i_type=" + i_type + ", origin_video=" + origin_video
				+ ", save_video=" + save_video + ", v_type=" + v_type + ", savefolder=" + savefolder + "]";
	}

}
