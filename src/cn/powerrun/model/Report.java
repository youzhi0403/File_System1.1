package cn.powerrun.model;

import java.util.Date;
/**
 * 
 * @author Admin
 * 存储每一个工单的出报告的信息
 */
public class Report {
	private String id;        //工单报告对象的ID
	private Date outputDate;  //出报告完成时间

	private String reportExcel;   //台区的用户excel表
	private String reportPicWithBG;  //带背景的台区节点分布图
	private String reportPicWithoutBG;  //不带背景的台区节点分布图
	private String reportCover;   //报告封面

	public Report() {
		super();
	}

	public Report(String id) {
		super();
		this.id = id;
	}

	public Report(String id, Date outputDate, String reportExcel, String reportPicWithBG, String reportPicWithoutBG,
			String reportCover) {
		super();
		this.id = id;
		this.outputDate = outputDate;
		this.reportExcel = reportExcel;
		this.reportPicWithBG = reportPicWithBG;
		this.reportPicWithoutBG = reportPicWithoutBG;
		this.reportCover = reportCover;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOutputDate() {
		return outputDate;
	}

	public void setOutputDate(Date outputDate) {
		this.outputDate = outputDate;
	}

	public String getReportExcel() {
		return reportExcel;
	}

	public void setReportExcel(String reportExcel) {
		this.reportExcel = reportExcel;
	}

	public String getReportPicWithBG() {
		return reportPicWithBG;
	}

	public void setReportPicWithBG(String reportPicWithBG) {
		this.reportPicWithBG = reportPicWithBG;
	}

	public String getReportPicWithoutBG() {
		return reportPicWithoutBG;
	}

	public void setReportPicWithoutBG(String reportPicWithoutBG) {
		this.reportPicWithoutBG = reportPicWithoutBG;
	}

	public String getReportCover() {
		return reportCover;
	}

	public void setReportCover(String reportCover) {
		this.reportCover = reportCover;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", outputDate=" + outputDate + ", reportExcel=" + reportExcel + ", reportPicWithBG="
				+ reportPicWithBG + ", reportPicWithoutBG=" + reportPicWithoutBG + ", reportCover=" + reportCover + "]";
	}

}
