package cn.powerrun.web;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.powerrun.model.Region;
import cn.powerrun.model.Report;
import cn.powerrun.service.IRegionService;
import cn.powerrun.service.IReportService;
import cn.powerrun.service.impl.RegionServiceImpl;
import cn.powerrun.service.impl.ReportServiceImpl;
import cn.powerrun.utils.FileUtils;
import cn.powerrun.utils.ResponseUtils;
import cn.powerrun.utils.StringUtil;

public class ReportUploadServlet extends HttpServlet {
	IRegionService regionService = new RegionServiceImpl();
	IReportService reportService = new ReportServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String regionName = "";
			String id = "";

			String picWithBG = "";
			String picWithoutBG = "";
			String reportCover = "";
			String reportExcel = "";

			// 接收营销数据表存放到服务器
			Map<String, String> map = FileUtils.uploadFiles(request);

			Report report = new Report();
			for (Map.Entry<String, String> entry : map.entrySet()) {

				if (entry.getKey().equals("regionName")) {
					regionName = entry.getValue();
				} else if (entry.getKey().equals("id")) {
					id = entry.getValue();
				} else if (entry.getKey().substring(0, 4).equals("file")) {
					if (StringUtil.getSuffix(entry.getValue()).equals(".jpg")
							|| StringUtil.getSuffix(entry.getValue()).equals(".png")) {
						if (entry.getValue().indexOf("-") != -1) {
							report.setReportPicWithBG(entry.getValue());
						} else {
							report.setReportPicWithoutBG(entry.getValue());
						}
					} else if (StringUtil.getSuffix(entry.getValue()).equals(".doc")
							|| StringUtil.getSuffix(entry.getValue()).equals(".docx")) {
						report.setReportCover(entry.getValue());
					} else if (StringUtil.getSuffix(entry.getValue()).equals(".xlsx")
							|| StringUtil.getSuffix(entry.getValue()).equals(".xls")) {
						report.setReportExcel(entry.getValue());
					}
				}
			}
			report.setOutputDate(new Date());

			// 根据台区名称,查询出该台区的信息
			Region region = regionService.regionQueryByName(regionName);
			// 判断region的report是否存在
			if (StringUtil.isEmpty(region.getReport().getId())) {
				report.setId(UUID.randomUUID().toString());
				int insertReportNum = reportService.reportInsert(report);
				if (insertReportNum == 1) {
					// 修改region
					int updateRegionNum = regionService.regionUpdate(report, regionName);
					if (updateRegionNum == 1) {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("code", "200");
						jsonObject.put("data", "提交成功");
						ResponseUtils.write(response, jsonObject);
					} else {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("code", "201");
						jsonObject.put("data", "台区更新失败");
						ResponseUtils.write(response, jsonObject);
					}
				} else {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", "201");
					jsonObject.put("data", "定位插入失败");
					ResponseUtils.write(response, jsonObject);
				}
			} else {
				report.setId(region.getReport().getId());
				int updateReportNum = reportService.reportUpdate(report);
				if (updateReportNum == 1) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", 200);
					jsonObject.put("data", "提交成功");
					ResponseUtils.write(response, jsonObject);
				} else {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", 201);
					jsonObject.put("data", "提交失败");
					ResponseUtils.write(response, jsonObject);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
