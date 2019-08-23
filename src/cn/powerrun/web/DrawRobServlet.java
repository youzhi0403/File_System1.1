package cn.powerrun.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.powerrun.service.IDrawService;
import cn.powerrun.service.impl.DrawServiceImpl;
import cn.powerrun.utils.ResponseUtils;

public class DrawRobServlet extends HttpServlet{
	IDrawService drawService = new DrawServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String userId = request.getParameter("userId");
			String regionId = request.getParameter("regionId");
			int result = drawService.drawRetreat(userId,regionId);
			if(result == 1){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", 200);
				jsonObject.put("data", "抢单成功");
				ResponseUtils.write(response, jsonObject);
			}else{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", 201);
				jsonObject.put("data", "抢单失败");
				ResponseUtils.write(response, jsonObject);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
