package cn.powerrun.model;

import java.util.Date;
import java.util.List;

public class Team {
	private String id; 
	private User captain;    //团队队长
	private String teamName;   //团队名称
	private Date createDate;  //团队创建日期
	private List<User> teamUsers;  //团队成员

	public Team() {
		super();
	}

	public Team(String id, User captain, String teamName, Date createDate, List<User> teamUsers) {
		super();
		this.id = id;
		this.captain = captain;
		this.teamName = teamName;
		this.createDate = createDate;
		this.teamUsers = teamUsers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getCaptain() {
		return captain;
	}

	public void setCaptain(User captain) {
		this.captain = captain;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<User> getTeamUsers() {
		return teamUsers;
	}

	public void setTeamUsers(List<User> teamUsers) {
		this.teamUsers = teamUsers;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", captain=" + captain + ", teamName=" + teamName + ", createDate=" + createDate
				+ ", teamUsers=" + teamUsers + "]";
	}
	
	

}
