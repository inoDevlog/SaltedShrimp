 package ss.entity.message;

import java.util.Date;

public class MessageEntity {

	private int mseq; // 쪽지번호(PK)
	private String recv_nick; // 받는 사람 닉네임
	private String send_nick; // 보낸 사람 닉네임
	private String message; // 쪽지 내용
	private Date send_date; // 쪽지 보낸 날짜
	private Date read_date; // 쪽지 읽은 날짜
	private int read_msg; // 쪽지 읾음 여부 0=unread, 1=read
	private int recv_del; // 받은 사람 쪽지 삭제 여부 1=삭제
	private int send_del; // 보낸 사람 쪽지 삭제 여부 1=삭제

	public MessageEntity() {
		super();
	}

	public MessageEntity(int mseq, String recv_nick, String send_nick, String message, Date send_date, Date read_date,
			int read_msg, int recv_del, int send_del) {
		super();
		this.mseq = mseq;
		this.recv_nick = recv_nick;
		this.send_nick = send_nick;
		this.message = message;
		this.send_date = send_date;
		this.read_date = read_date;
		this.read_msg = read_msg;
		this.recv_del = recv_del;
		this.send_del = send_del;
	}

	public int getMseq() {
		return mseq;
	}

	public void setMseq(int mseq) {
		this.mseq = mseq;
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

	public Date getSend_date() {
		return send_date;
	}

	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}

	public Date getRead_date() {
		return read_date;
	}

	public void setRead_date(Date read_date) {
		this.read_date = read_date;
	}

	public int getRead_msg() {
		return read_msg;
	}

	public void setRead_msg(int read_msg) {
		this.read_msg = read_msg;
	}

	public int getRecv_del() {
		return recv_del;
	}

	public void setRecv_del(int recv_del) {
		this.recv_del = recv_del;
	}

	public int getSend_del() {
		return send_del;
	}

	public void setSend_del(int send_del) {
		this.send_del = send_del;
	}

	@Override
	public String toString() {
		return "MessageEntity [mseq=" + mseq + ", recv_nick=" + recv_nick + ", send_nick=" + send_nick + ", message="
				+ message + ", send_date=" + send_date + ", read_date=" + read_date + ", read_msg=" + read_msg
				+ ", recv_del=" + recv_del + ", send_del=" + send_del + "]";
	}

}
