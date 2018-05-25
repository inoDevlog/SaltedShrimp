package ss.vo.message;



public class MessageVO {
	
	private String recv_nick; // �޴� ��� �г���
	private String send_nick; // ���� ��� �г���
	private String message; // ���� ����
	private int mseq;
	private int currentPage; // ����¡ ó���� ����
	private int start;
	private int sv_index;  // ������ �޴� �޼��� ����, 0: ���� �α��� ������ ������ ���� �޼���, 1: ���� �α��� ������ ������ ���� �޼��� 
	private int lastmseq;

	
	public int getMseq() {
		return mseq;
	}
	public void setMseq(int mseq) {
		this.mseq = mseq;
	}
	public int getLastmseq() {
		return lastmseq;
	}
	public void setLastmseq(int lastmseq) {
		this.lastmseq = lastmseq;
	}
	public int getSv_index() {
		return sv_index;
	}
	public void setSv_index(int sv_index) {
		this.sv_index = sv_index;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getRecv_nick() {
		return recv_nick;
	}
	public void setRecv_nick(String recv_nick) {
		this.recv_nick = recv_nick;
	}
	public String getSend_nick() {
		return send_nick;
	}
	public void setSend_nick(String send_nick) {
		this.send_nick = send_nick;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "MessageVO [recv_nick=" + recv_nick + ", send_nick=" + send_nick + ", message=" + message + ", mseq="
				+ mseq + ", currentPage=" + currentPage + ", start=" + start + ", sv_index=" + sv_index + ", lastmseq="
				+ lastmseq + "]";
	}
	
	
	
	
	

}
