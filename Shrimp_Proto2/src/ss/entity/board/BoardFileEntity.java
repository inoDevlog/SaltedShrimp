package ss.entity.board;

public class BoardFileEntity {
	private int fseq; // ���Ϲ�ȣ(PK)
	private int bseq; // �Խù���ȣ(FK)
	private String fname; // �����̸�
	private int fsize; // ����ũ��
	private String fdir; // ���ϰ��
	private int ftype; // �������� 0=image, 1=video

	public BoardFileEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardFileEntity(int fseq, int bseq, String fname, int fsize, String fdir, int ftype) {
		super();
		this.fseq = fseq;
		this.bseq = bseq;
		this.fname = fname;
		this.fsize = fsize;
		this.fdir = fdir;
		this.ftype = ftype;
	}

	public int getFseq() {
		return fseq;
	}

	public void setFseq(int fseq) {
		this.fseq = fseq;
	}

	public int getBseq() {
		return bseq;
	}

	public void setBseq(int bseq) {
		this.bseq = bseq;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public int getFsize() {
		return fsize;
	}

	public void setFsize(int fsize) {
		this.fsize = fsize;
	}

	public String getFdir() {
		return fdir;
	}

	public void setFdir(String fdir) {
		this.fdir = fdir;
	}

	public int getFtype() {
		return ftype;
	}

	public void setFtype(int ftype) {
		this.ftype = ftype;
	}

	@Override
	public String toString() {
		return "BoardFileEntity [fseq=" + fseq + ", bseq=" + bseq + ", fname=" + fname + ", fsize=" + fsize + ", fdir="
				+ fdir + ", ftype=" + ftype + "]";
	}

}
