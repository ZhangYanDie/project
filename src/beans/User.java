package beans;

public class User {
	
	private int id;
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}

}
