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

public class RegionFinishServlet extends HttpServlet{
	IRegionService regionService = new RegionServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regionId = request.getParameter("regionId");
		try{
			System.out.println();
			int result = regionService.regionFinish(regionId);
			if(result == 1){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", 200);
				jsonObject.put("data", "台区归档成功");
				ResponseUtils.write(response, jsonObject);
			}else{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", 201);
				jsonObject.put("data", "台区归档失败");
				ResponseUtils.write(response, jsonObject);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
