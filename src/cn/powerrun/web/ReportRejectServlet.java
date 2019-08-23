package cn.powerrun.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.powerrun.model.Region;
import cn.powerrun.service.IModelService;
import cn.powerrun.service.IRegionService;
import cn.powerrun.service.impl.ModelServiceImpl;
import cn.powerrun.service.impl.RegionServiceImpl;
import cn.powerrun.utils.ResponseUtils;
import cn.powerrun.utils.StringUtil;

public class ReportRejectServlet extends HttpServlet{
	IRegionService regionService = new RegionServiceImpl();
	IModelService modelService = new ModelServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regionId = request.getParameter("regionId");
		//删除.mod文件，删除json文件,删除model数据,更新reportRetreat为null,修改工单的status
		
		try {
			Region region = regionService.regionQueryById(regionId);
			if (!StringUtil.isEmpty(region.getModel().getMod())) {
				new File(region.getModel().getMod()).delete();
			}
			if (!StringUtil.isEmpty(region.getModel().getModelJSON())) {
				new File(region.getModel().getModelJSON()).delete();
			}
			int updateNum = regionService.updateRegionStatusAndModelRob(regionId);
			int deleteNum = modelService.delete(region.getModel().getId());
			
			if (updateNum == 1 && deleteNum == 1) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", 200);
				jsonObject.put("data", "驳回成功");
				ResponseUtils.write(response, jsonObject);
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
