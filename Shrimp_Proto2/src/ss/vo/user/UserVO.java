package ss.vo.user;

import java.util.Date;

public class UserVO {

	private int useq;
	private String email;
	private String passwd;
	private String nickname;
	private String mobile;
	private Date joindate;

	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserVO(int useq, String email, String passwd, String nickname, String mobile, Date joindate) {
		super();
		this.useq = useq;
		this.email = email;
		this.passwd = passwd;
		this.nickname = nickname;
		this.mobile = mobile;
		this.joindate = joindate;
	}

	public int getUseq() {
		return useq;
	}

	public void setUseq(int useq) {
		this.useq = useq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	@Override
	public String toString() {
		return "UserVO [useq=" + useq + ", email=" + email + ", passwd=" + passwd + ", nickname=" + nickname
				+ ", mobile=" + mobile + ", joindate=" + joindate + "]";
	}

}
