package cn.powerrun.model;

import java.util.Date;
/**
 * 
 * @author Admin
 * 存储每一个工单的建模的信息
 */
public class Model {
	private String id;          //建模对象ID
	private Date modelDate;     //建模完成时间

	private String mod;         //.mod文件存储位置
	private String modelJSON;   //建模json文件存储位置
	private String northPhoto; // 正北图存储位置

	public Model() {
		super();
	}

	public Model(String id) {
		super();
		this.id = id;
	}

	public Model(String id, Date modelDate, String mod, String modelJSON, String northPhoto) {
		super();
		this.id = id;
		this.modelDate = modelDate;
		this.mod = mod;
		this.modelJSON = modelJSON;
		this.northPhoto = northPhoto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getModelDate() {
		return modelDate;
	}

	public void setModelDate(Date modelDate) {
		this.modelDate = modelDate;
	}

	public String getMod() {
		return mod;
	}

	public void setMod(String mod) {
		this.mod = mod;
	}

	public String getModelJSON() {
		return modelJSON;
	}

	public void setModelJSON(String modelJSON) {
		this.modelJSON = modelJSON;
	}

	public String getNorthPhoto() {
		return northPhoto;
	}

	public void setNorthPhoto(String northPhoto) {
		this.northPhoto = northPhoto;
	}

	@Override
	public String toString() {
		return "Model [id=" + id + ", modelDate=" + modelDate + ", mod=" + mod + ", modelJSON=" + modelJSON
				+ ", northPhoto=" + northPhoto + "]";
	}

}
