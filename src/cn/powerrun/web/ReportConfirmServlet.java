package cn.powerrun.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.powerrun.service.IRegionService;
import cn.powerrun.service.impl.RegionServiceImpl;
import cn.powerrun.utils.ResponseUtils;

public class ReportConfirmServlet extends HttpServlet{
	IRegionService regionService = new RegionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regionName = request.getParameter("regionName");
		try {
			int result = regionService.regionStatusUpdate(regionName);
			if(result == 1) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", 200);
				jsonObject.put("data", "提交成功");
				ResponseUtils.write(response, jsonObject);
			}else {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", 201);
				jsonObject.put("data", "提交失败");
				ResponseUtils.write(response, jsonObject);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
}
