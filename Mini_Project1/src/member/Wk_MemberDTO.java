package member;

public class Wk_MemberDTO {

	private String id;//아이디
	private int pw;//비번
	private String Nick;//닉네임
	private String date;//가입날짜
	
	
	Wk_MemberDTO(){}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getPw() {
		return pw;
	}


	public void setPw(int pw) {
		this.pw = pw;
	}


	public String getNick() {
		return Nick;
	}


	public void setNick(String nick) {
		Nick = nick;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

	
	
	}
	
	
