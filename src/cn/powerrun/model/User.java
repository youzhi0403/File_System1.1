package cn.powerrun.model;

public class User {
	private String id;
	private String username;   //用户名
	private String password;  //用户密码
	private String account;  //用户账号

	public User() {
		super();
	}

	public User(String id) {
		super();
		this.id = id;
	}

	public User(String id, String username, String password, String account) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.account = account;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", account=" + account + "]";
	}

}
