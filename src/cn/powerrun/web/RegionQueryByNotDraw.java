package cn.powerrun.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.powerrun.model.Pagination;
import cn.powerrun.model.Region;
import cn.powerrun.service.IRegionService;
import cn.powerrun.service.impl.RegionServiceImpl;
import cn.powerrun.utils.ResponseUtils;

public class RegionQueryByNotDraw extends HttpServlet{
	IRegionService regionService = new RegionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String limit = request.getParameter("limit");
		String page = request.getParameter("page");
		Pagination pagination = new Pagination(Integer.parseInt(page),Integer.parseInt(limit));
		//查询创建好的工单,未被抢的工单
		JSONObject result = new JSONObject();
		try{
			List<Region> list = regionService.regionQueryByNotRetreat("1","drawRetreat",pagination);
			int count = regionService.regionCountByNotRetreat("1","drawRetreat");
System.out.println(count);
			JSONArray jsonArray = new JSONArray();
			for(Region region:list){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", region.getId());
				jsonObject.put("regionName", region.getRegionName());
				jsonObject.put("createDate", region.getCreateDate());
				jsonArray.put(jsonObject);
			}
			result.put("data", jsonArray);
			result.put("code", 0);
			result.put("count", count);
			result.put("msg", "查询成功");
			ResponseUtils.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
}