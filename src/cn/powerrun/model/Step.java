package cn.powerrun.model;

import java.util.Date;

public abstract class Step {
	private String id;
	private Date submitDate;
	
	public abstract String getId();
	public abstract void setId(String id);
	public abstract Date getSubmitDate();
	public abstract void setSubmitDate(Date submitDate);

}
