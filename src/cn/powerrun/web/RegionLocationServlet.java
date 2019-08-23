package cn.powerrun.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.powerrun.model.Region;
import cn.powerrun.service.IRegionService;
import cn.powerrun.service.impl.RegionServiceImpl;
import cn.powerrun.utils.FileUtils;
import cn.powerrun.utils.ResponseUtils;
import cn.powerrun.utils.StringUtil;

public class RegionLocationServlet extends HttpServlet{
	IRegionService regionService = new RegionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String regionId = req.getParameter("regionId");
		JSONObject result = new JSONObject();
		try{
			
			Region region = regionService.regionQueryById(regionId);
			if(StringUtil.isEmpty(region.getDraw().getDrawJSON())){
				result.put("code", 201);
				result.put("data", "请先上传制图文件");
				
			}else{
				String content = FileUtils.readFiles(region.getDraw().getDrawJSON());  
				result.put("code", 200);
				JSONObject jsonObject = new JSONObject(content);
				JSONArray array = (JSONArray) jsonObject.get("pointList");
				result.put("data", array.getJSONObject(0));
			}
			
			
			ResponseUtils.write(resp, result);
		} catch(Exception e){
			result.put("code", 202);
			result.put("data", "服务器内部错误");
			e.printStackTrace();
		}
		
		
	}
	

}
