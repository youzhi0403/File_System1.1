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

public class RegionIsRepeatServlet extends HttpServlet{
	IRegionService regionService = new RegionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String regionName = request.getParameter("regionName");
			boolean isRepeat = regionService.isRepeat(regionName);
			JSONObject result = new JSONObject();
			if(isRepeat) {
				
				result.put("code", 201);
				result.put("data", "台区重名");
			}else {
				result.put("code", 200);
				result.put("data", "台区名称可以使用");
			}
			ResponseUtils.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
}
