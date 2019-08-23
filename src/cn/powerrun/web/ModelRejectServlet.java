package cn.powerrun.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.powerrun.model.Region;
import cn.powerrun.service.IDrawService;
import cn.powerrun.service.ILocateService;
import cn.powerrun.service.IRegionService;
import cn.powerrun.service.impl.DrawServiceImpl;
import cn.powerrun.service.impl.LocateServiceImpl;
import cn.powerrun.service.impl.RegionServiceImpl;
import cn.powerrun.utils.ResponseUtils;
import cn.powerrun.utils.StringUtil;

public class ModelRejectServlet extends HttpServlet {
	IRegionService regionService = new RegionServiceImpl();
	IDrawService drawService = new DrawServiceImpl();
	ILocateService locateService = new LocateServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String regionId = request.getParameter("regionId");
		// 删除制图,定位的文件,修改modelRob,修改工单的staturegionIds,删除
		try {
			Region region = regionService.regionQueryById(regionId);
			if (!StringUtil.isEmpty(region.getDraw().getAirPhoto())) {
				new File(region.getDraw().getAirPhoto()).delete();
			}
			if (!StringUtil.isEmpty(region.getDraw().getDrawJSON())) {
				new File(region.getDraw().getDrawJSON()).delete();
			}
			if (!StringUtil.isEmpty(region.getLocate().getJdkJAR())) {
				new File(region.getLocate().getJdkJAR()).delete();
			}
			if (!StringUtil.isEmpty(region.getLocate().getSdkJAR())) {
				new File(region.getLocate().getSdkJAR()).delete();
			}

			if (!StringUtil.isEmpty(region.getLocate().getLocateJSON())) {
				new File(region.getLocate().getLocateJSON()).delete();
			}
			if (!StringUtil.isEmpty(region.getLocate().getNorthPhoto())) {
				new File(region.getLocate().getNorthPhoto()).delete();
			}
			
			//删除draw和locate
			int updateNum = regionService.updateRegionByModelReject(regionId);
			int drawDeleteNum = drawService.delete(region.getDraw());
			int locateDeleteNum = locateService.delete(region.getLocate());
			
			if (updateNum == 1 && drawDeleteNum == 1 && locateDeleteNum == 1) {
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
