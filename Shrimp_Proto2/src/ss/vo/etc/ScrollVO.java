package ss.vo.etc;

public class ScrollVO {
	private int currentPage_P;
	private int currentPage_L;
	private int mode;
	public int getCurrentPage_P() {
		return currentPage_P;
	}
	public void setCurrentPage_P(int currentPage_P) {
		this.currentPage_P = currentPage_P;
	}
	public int getCurrentPage_L() {
		return currentPage_L;
	}
	public void setCurrentPage_L(int currentPage_L) {
		this.currentPage_L = currentPage_L;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	@Override
	public String toString() {
		return "ScrollVO [currentPage_P=" + currentPage_P + ", currentPage_L=" + currentPage_L + ", mode=" + mode + "]";
	}
	
	
}
