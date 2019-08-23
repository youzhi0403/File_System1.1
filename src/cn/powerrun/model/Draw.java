package cn.powerrun.model;

import java.util.Date;
/**
 * 
 * @author Admin
 * 存储每一个工单的制图的信息
 */
public class Draw {
	private String id; // 制图模块id
	private Date drawDate; // 制图完成时间

	private String airPhoto; // 航拍图存储位置
	private String drawJSON; // 制图所产生的定位json存储位置

	public Draw() {
		super();
	}

	public Draw(String id) {
		super();
		this.id = id;
	}

	public Draw(String id, Date drawDate, String airPhoto, String drawJSON) {
		super();
		this.id = id;
		this.drawDate = drawDate;
		this.airPhoto = airPhoto;
		this.drawJSON = drawJSON;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDrawDate() {
		return drawDate;
	}

	public void setDrawDate(Date drawDate) {
		this.drawDate = drawDate;
	}

	public String getAirPhoto() {
		return airPhoto;
	}

	public void setAirPhoto(String airPhoto) {
		this.airPhoto = airPhoto;
	}

	public String getDrawJSON() {
		return drawJSON;
	}

	public void setDrawJSON(String drawJSON) {
		this.drawJSON = drawJSON;
	}

	@Override
	public String toString() {
		return "Draw [id=" + id + ", drawDate=" + drawDate + ", airPhoto=" + airPhoto + ", drawJSON=" + drawJSON + "]";
	}

}
