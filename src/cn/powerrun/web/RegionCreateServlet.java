package cn.powerrun.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.powerrun.service.IRegionService;
import cn.powerrun.service.impl.RegionServiceImpl;
import cn.powerrun.utils.FileUtils;
import cn.powerrun.utils.ResponseUtils;

public class RegionCreateServlet extends HttpServlet {
	IRegionService regionService = new RegionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String regionName = null;
		String createUserId = null;
		String drawUserId = null;
		String modelUserId = null;
		String outputUserId = null;
		String finishUserId = null;
		String marketExcel = null;

		
		// 数据库插入数据
		try {
			Map<String, String> map = FileUtils.uploadFiles(request);
			for(Map.Entry<String, String> entry:map.entrySet()){
				if(entry.getKey().equals("regionName")){
					regionName = entry.getValue();
				}else if(entry.getKey().equals("createUserId")){
					createUserId = entry.getValue();
				}else if(entry.getKey().equals("drawUserId")){
					drawUserId = entry.getValue();
				}else if(entry.getKey().equals("modelUserId")){
					modelUserId = entry.getValue();
				}else if(entry.getKey().equals("outputUserId")){
					outputUserId = entry.getValue();
				}else if(entry.getKey().equals("finishUserId")){
					finishUserId = entry.getValue();
				}else if(entry.getKey().substring(0, 4).equals("file")){
					String suffix = entry.getValue().substring(entry.getValue().lastIndexOf("."), entry.getValue().length());
					if(suffix.equals(".xls") || suffix.equals(".xlsx")){
						marketExcel = entry.getValue();
					}
				}
			}
			int result = regionService.regionInsert(regionName, createUserId, drawUserId, modelUserId,
					outputUserId, finishUserId,marketExcel);
			if (result == 1) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("data", "提交成功");
				jsonObject.put("code", 200);
				ResponseUtils.write(response, jsonObject);
			} else {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("data", "提交失败");
				jsonObject.put("code", 201);
				ResponseUtils.write(response, jsonObject);
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
		doGet(req, resp);
	}

}
