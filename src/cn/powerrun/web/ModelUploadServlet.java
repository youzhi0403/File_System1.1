package cn.powerrun.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.powerrun.model.Model;
import cn.powerrun.model.Region;
import cn.powerrun.service.IModelService;
import cn.powerrun.service.IRegionService;
import cn.powerrun.service.impl.ModelServiceImpl;
import cn.powerrun.service.impl.RegionServiceImpl;
import cn.powerrun.utils.FileUtils;
import cn.powerrun.utils.ResponseUtils;
import cn.powerrun.utils.StringUtil;

public class ModelUploadServlet extends HttpServlet{
	IModelService modelService = new ModelServiceImpl();
	IRegionService regionService = new RegionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regionName = "";
		String id = "";
		//接收文件存放服务器
		Map<String,String> map = FileUtils.uploadFiles(request);
		//将数据存放数据库
		String mod = "";
		String modelJSON = "";
		String northPhoto = "";
		
		for (Map.Entry<String,String> entry : map.entrySet()) {
			if(entry.getKey().equals("regionName")) {
				regionName = entry.getValue();
			}else if(entry.getKey().equals("id")) {
				id = entry.getValue();
			}else if(entry.getKey().substring(0, 4).equals("file")) {
				if (StringUtil.getSuffix(entry.getValue()).equals(".mod")) {
					mod = entry.getValue();
				}else if(StringUtil.getSuffix(entry.getValue()).equals(".json")) {
					modelJSON = entry.getValue();
				}else if(StringUtil.getSuffix(entry.getValue()).equals(".jpg") || StringUtil.getSuffix(entry.getValue()).equals(".png")) {
					northPhoto = entry.getValue();
				}
			}
		}
		
		try {
			Region region = regionService.regionQuery(regionName);
			
			
			if(StringUtil.isEmpty(region.getModel().getId())) {
				//插入数据
				Model model = new Model(UUID.randomUUID().toString(),new Date(),mod,modelJSON,northPhoto);
				int insertModelNum = modelService.modelInsert(model);
				if(insertModelNum == 1) {
					//修改台区数据
					int updateRegionNum = regionService.regionUpdate(model,regionName);
					if(updateRegionNum == 1) {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("code", 200);
						jsonObject.put("data", "提交成功");
						ResponseUtils.write(response, jsonObject);
					}else {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("code", 201);
						jsonObject.put("data", "修改失败");
						ResponseUtils.write(response, jsonObject);
					}
					
				}else {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", 201);
					jsonObject.put("data", "插入失败");
					ResponseUtils.write(response, jsonObject);
				}
			}else {
				//修改数据
				Model model = new Model(region.getModel().getId(),new Date(),mod,modelJSON,northPhoto);
				int updateModelNum = modelService.modelUpdate(model);
				if(updateModelNum == 1) {
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
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
