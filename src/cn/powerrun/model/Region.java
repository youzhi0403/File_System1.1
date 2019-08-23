package cn.powerrun.model;

import java.util.Date;
/**
 * 
 * @author Admin
 * 存储工单的信息
 */
public class Region {
	private String id; // 台区id
	private String regionName; // 台区名字
	private Date createDate; // 工单创建日期
	private Date finishDate; // 工单完成日期
	private User createUser; // 工单创建人

	private User drawRetreat; //制图抢单人
	private User modelRetreat; //建模抢单人
	private User outputRetreat;  //输出报告抢单人

	private User drawUser; // 制图负责人
	private Team drawTeam; // 制图团队

	private User modelUser; // 建模负责人
	private Team modelTeam; // 建模团队

	private User outputUser; // 输出报告负责人
	private Team outputTeam; // 输出报告团队

	private User finishUser; // 审查负责人

	private String marketExcel; // 营销数据表

	private Draw draw;       //工单的制图模块
	private Locate locate;   //工单的定位模块
	private Model model;     //工单的建模模块
	private Report report;   //工单的出报告模块

	private String status;   //工单的状态

	public Region() {
		super();
	}

	public Region(String id, String regionName, Date createDate, User createUser, User drawUser, User modelUser,
			User outputUser, User finishUser, String marketExcel, String status) {
		super();
		this.id = id;
		this.regionName = regionName;
		this.createDate = createDate;
		this.createUser = createUser;
		this.drawUser = drawUser;
		this.modelUser = modelUser;
		this.outputUser = outputUser;
		this.finishUser = finishUser;
		this.marketExcel = marketExcel;
		this.status = status;
	}

	public Region(String id, String regionName, Date createDate, Date finishDate, User createUser, User drawUser,
			Team drawTeam, User modelUser, Team modelTeam, User outputUser, Team outputTeam, User finishUser,
			String marketExcel, Draw draw, Locate locate, Model model, Report report, String status) {
		super();
		this.id = id;
		this.regionName = regionName;
		this.createDate = createDate;
		this.finishDate = finishDate;
		this.createUser = createUser;
		this.drawUser = drawUser;
		this.drawTeam = drawTeam;
		this.modelUser = modelUser;
		this.modelTeam = modelTeam;
		this.outputUser = outputUser;
		this.outputTeam = outputTeam;
		this.finishUser = finishUser;
		this.marketExcel = marketExcel;
		this.draw = draw;
		this.locate = locate;
		this.model = model;
		this.report = report;
		this.status = status;
	}
	

	public User getDrawRetreat() {
		return drawRetreat;
	}

	public void setDrawRetreat(User drawRetreat) {
		this.drawRetreat = drawRetreat;
	}

	public User getModelRetreat() {
		return modelRetreat;
	}

	public void setModelRetreat(User modelRetreat) {
		this.modelRetreat = modelRetreat;
	}

	public User getOutputRetreat() {
		return outputRetreat;
	}

	public void setOutputRetreat(User outputRetreat) {
		this.outputRetreat = outputRetreat;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public User getDrawUser() {
		return drawUser;
	}

	public void setDrawUser(User drawUser) {
		this.drawUser = drawUser;
	}

	public Team getDrawTeam() {
		return drawTeam;
	}

	public void setDrawTeam(Team drawTeam) {
		this.drawTeam = drawTeam;
	}

	public User getModelUser() {
		return modelUser;
	}

	public void setModelUser(User modelUser) {
		this.modelUser = modelUser;
	}

	public Team getModelTeam() {
		return modelTeam;
	}

	public void setModelTeam(Team modelTeam) {
		this.modelTeam = modelTeam;
	}

	public User getOutputUser() {
		return outputUser;
	}

	public void setOutputUser(User outputUser) {
		this.outputUser = outputUser;
	}

	public Team getOutputTeam() {
		return outputTeam;
	}

	public void setOutputTeam(Team outputTeam) {
		this.outputTeam = outputTeam;
	}

	public User getFinishUser() {
		return finishUser;
	}

	public void setFinishUser(User finishUser) {
		this.finishUser = finishUser;
	}

	public String getMarketExcel() {
		return marketExcel;
	}

	public void setMarketExcel(String marketExcel) {
		this.marketExcel = marketExcel;
	}

	public Draw getDraw() {
		return draw;
	}

	public void setDraw(Draw draw) {
		this.draw = draw;
	}

	public Locate getLocate() {
		return locate;
	}

	public void setLocate(Locate locate) {
		this.locate = locate;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", regionName=" + regionName + ", createDate=" + createDate + ", finishDate="
				+ finishDate + ", createUser=" + createUser + ", drawUser=" + drawUser + ", drawTeam=" + drawTeam
				+ ", modelUser=" + modelUser + ", modelTeam=" + modelTeam + ", outputUser=" + outputUser
				+ ", outputTeam=" + outputTeam + ", finishUser=" + finishUser + ", marketExcel=" + marketExcel
				+ ", draw=" + draw + ", locate=" + locate + ", model=" + model + ", report=" + report + ", status="
				+ status + "]";
	}

}
