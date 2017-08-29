package kr.or.ddit.vo;

public class MemberVO {
	//어느 VO클래스건 index의 id
	private String id; //기본키
	
	private boolean isAdmin;
	
	private String mbUserId;
	
	private String mbUserPw;
	
	private String mbUserName;
	
	private String mbUserMoney;



	public MemberVO(String id, boolean isAdmin, String mbUserId, String mbUserPw, String mbUserName,
			String mbUserMoney) {
		super();
		this.id = id;
		this.isAdmin = isAdmin;
		this.mbUserId = mbUserId;
		this.mbUserPw = mbUserPw;
		this.mbUserName = mbUserName;
		this.mbUserMoney = mbUserMoney;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getMbUserId() {
		return mbUserId;
	}

	public void setMbUserId(String mbUserId) {
		this.mbUserId = mbUserId;
	}

	public String getMbUserPw() {
		return mbUserPw;
	}

	public void setMbUserPw(String mbUserPw) {
		this.mbUserPw = mbUserPw;
	}

	public String getMbUserName() {
		return mbUserName;
	}

	public void setMbUserName(String mbUserName) {
		this.mbUserName = mbUserName;
	}

	public String getMbUserMoney() {
		return mbUserMoney;
	}

	public void setMbUserMoney(String mbUserMoney) {
		this.mbUserMoney = mbUserMoney;
	}

	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", isAdmin=" + isAdmin + ", mbUserId=" + mbUserId + ", mbUserPw=" + mbUserPw
				+ ", mbUserName=" + mbUserName + ", mbUserMoney=" + mbUserMoney + "]";
	}
}
