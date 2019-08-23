package cn.powerrun.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.powerrun.model.Region;
import cn.powerrun.service.IRegionService;
import cn.powerrun.service.IReportService;
import cn.powerrun.service.impl.RegionServiceImpl;
import cn.powerrun.service.impl.ReportServiceImpl;
import cn.powerrun.utils.ResponseUtils;
import cn.powerrun.utils.StringUtil;

public class FinishRejectServlet extends HttpServlet{
	IRegionService regionService = new RegionServiceImpl();
	IReportService reportService = new ReportServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regionId = request.getParameter("regionId");
		// 删除制图,定位的文件,修改modelRob,修改工单的status
		try {
			Region region = regionService.regionQueryById(regionId);
			if (!StringUtil.isEmpty(region.getReport().getReportCover())) {
				new File(region.getReport().getReportCover()).delete();
			}
			if (!StringUtil.isEmpty(region.getReport().getReportPicWithBG())) {
				new File(region.getReport().getReportPicWithBG()).delete();
			}
			if (!StringUtil.isEmpty(region.getReport().getReportPicWithoutBG())) {
				new File(region.getReport().getReportPicWithoutBG()).delete();
			}
			if (!StringUtil.isEmpty(region.getReport().getReportExcel())) {
				new File(region.getReport().getReportExcel()).delete();
			}

			//将reportId置为空,删除reportId这条数据,更新status字段和outputRetreat这个字段
			//将outRetreat置为空，将reportId置为空
			int updateNum = regionService.updateRegionStatusAndReportRobAndReportId(regionId,"5",region.getOutputRetreat().getId());
			
			if (updateNum == 1) {
				int deleteNum = reportService.reportDelete(region.getReport().getId());
				if(deleteNum == 1){
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", 200);
					jsonObject.put("data", "驳回成功");
					ResponseUtils.write(response, jsonObject);
				}else{
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", 201);
					jsonObject.put("data", "驳回失败");
					ResponseUtils.write(response, jsonObject);
				}
				
			} else {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", 201);
				jsonObject.put("data", "驳回失败");
				ResponseUtils.write(response, jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
