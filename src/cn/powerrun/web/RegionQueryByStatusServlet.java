package cn.powerrun.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.powerrun.model.Region;
import cn.powerrun.service.IRegionService;
import cn.powerrun.service.impl.RegionServiceImpl;
import cn.powerrun.utils.ResponseUtils;

public class RegionQueryByStatusServlet extends HttpServlet{
	IRegionService regionService = new RegionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String status = request.getParameter("status");
		List<Region> list = new ArrayList();
		
		try {
			list = regionService.regionQueryByStatus(userId,status);
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			for(Region region:list) {
				JSONObject jo = new JSONObject();
				jo.put("id", region.getId());
				jo.put("regionName", region.getRegionName());
				jo.put("createDate", region.getCreateDate());
				jo.put("finishDate", region.getFinishDate());
				jo.put("status", region.getStatus());
				jsonArray.put(jo);
			}
			jsonObject.put("data", jsonArray);
			jsonObject.put("code", 200);
			ResponseUtils.write(response, jsonObject);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
}
