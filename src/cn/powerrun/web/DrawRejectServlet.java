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
import cn.powerrun.service.impl.RegionServiceImpl;
import cn.powerrun.utils.ResponseUtils;
import cn.powerrun.utils.StringUtil;

public class DrawRejectServlet extends HttpServlet {
	IRegionService regionService = new RegionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regionId = request.getParameter("regionId");
		try{
			//查询出region,获取excel,进行删除
			Region region = regionService.regionQueryById(regionId);
			if(!StringUtil.isEmpty(region.getMarketExcel())){
				new File(region.getMarketExcel()).delete();
				String dir = region.getMarketExcel().substring(0, region.getMarketExcel().lastIndexOf("\\"));
				new File(dir).delete();
			}
			//删除这条数据
			int deleteNum = regionService.regionDeleteById(regionId);
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
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
