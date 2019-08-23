package cn.powerrun.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.powerrun.locate.utils.LocateUtils;
import cn.powerrun.model.Draw;
import cn.powerrun.model.Locate;
import cn.powerrun.model.Region;
import cn.powerrun.service.IDrawService;
import cn.powerrun.service.ILocateService;
import cn.powerrun.service.IRegionService;
import cn.powerrun.service.impl.DrawServiceImpl;
import cn.powerrun.service.impl.LocateServiceImpl;
import cn.powerrun.service.impl.RegionServiceImpl;
import cn.powerrun.utils.FileUtils;
import cn.powerrun.utils.ResponseUtils;
import cn.powerrun.utils.StringUtil;

public class DrawUploadServlet extends HttpServlet {
	IDrawService drawService = new DrawServiceImpl();
	IRegionService regionService = new RegionServiceImpl();
	ILocateService locateService = new LocateServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收文件存放服务器
		Map<String,String> map = FileUtils.uploadFiles(request);
		//接收的台区名称
		String regionName = "";
		// 将数据存放数据库
		String airPhoto = "";
		String drawJSON = "";
		String id = "";
		for (Map.Entry<String,String> entry : map.entrySet()) {
			if(entry.getKey().equals("regionName")) {
				regionName = entry.getValue();
			}else if(entry.getKey().equals("id")) {
				id = entry.getValue();
			}else if(entry.getKey().substring(0, 4).equals("file")) {
				if (StringUtil.getSuffix(entry.getValue()).equals(".jpg") || StringUtil.getSuffix(entry.getValue()).equals(".png") || StringUtil.getSuffix(entry.getValue()).equals(".JPG")) {
					airPhoto = entry.getValue();
				}else if (StringUtil.getSuffix(entry.getValue()).equals(".json")) {
					drawJSON = entry.getValue();
				}
			}
		}
		
		//增加判断,是否上传了json和图片
		if(StringUtil.isEmpty(airPhoto) || StringUtil.isEmpty(drawJSON)){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", 201);
			jsonObject.put("data", "请同时提交json文件和图片");
			try {
				ResponseUtils.write(response, jsonObject);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		Region region = null;
		try {
			region = regionService.regionQuery(regionName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//生成Locate和Draw对象
		Draw draw = new Draw();
		draw.setDrawDate(new Date());
		draw.setAirPhoto(airPhoto);
		draw.setDrawJSON(drawJSON);
		Locate locate = new Locate();
		try{
			List<String> result = LocateUtils.locate(new File(airPhoto), new File(drawJSON), regionName+"");
			locate.setLocateDate(new Date());
			for (String s : result) {
				if (StringUtil.getSuffix(s).equals(".json")) {
					locate.setLocateJSON(s);
				} else if (StringUtil.getSuffix(s).equals(".jpg")) {
					locate.setNorthPhoto(s);
				} else if (StringUtil.getSuffix(s).equals(".jar")) {
					locate.setJdkJAR(s);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		try {
			if (StringUtil.isEmpty(region.getLocate().getId()) && StringUtil.isEmpty(region.getDraw().getId())) {
				locate.setId(UUID.randomUUID().toString());
				draw.setId(UUID.randomUUID().toString());
				int insertLocateNum = locateService.locateInsert(locate);
				int insertDrawNum = drawService.drawInsert(draw);
				int updateRegionNum = regionService.updateRegionByDrawUpload(draw,locate,"2",region.getId());
				
				if (insertLocateNum == 1 && insertDrawNum == 1 && updateRegionNum==1) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", "200");
					jsonObject.put("data", "提交成功");
					ResponseUtils.write(response, jsonObject);
				} else {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", "201");
					jsonObject.put("data", "提交失败");
					ResponseUtils.write(response, jsonObject);
				}
			} else if(!StringUtil.isEmpty(region.getLocate().getId()) && !StringUtil.isEmpty(region.getDraw().getId())) {
				locate.setId(region.getLocate().getId());
				draw.setId(region.getDraw().getId());
				int updateLocateNum = locateService.locateUpdate(locate);
				int updateDrawNum = drawService.drawUpdate(draw);
				int updateRegionNum = regionService.updateRegionByDrawUpload(draw,locate,"2",region.getId());
				
				if (updateLocateNum == 1 && updateDrawNum == 1 && updateRegionNum==1) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", "200");
					jsonObject.put("data", "提交成功");
					ResponseUtils.write(response, jsonObject);
				} else {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", "201");
					jsonObject.put("data", "提交失败");
					ResponseUtils.write(response, jsonObject);
				}
			} else if(!StringUtil.isEmpty(region.getLocate().getId()) && StringUtil.isEmpty(region.getDraw().getId())){
				locate.setId(region.getLocate().getId());
				draw.setId(UUID.randomUUID().toString());
				int updateLocateNum = locateService.locateUpdate(locate);
				int insertDrawNum = drawService.drawInsert(draw);
				int updateRegionNum = regionService.updateRegionByDrawUpload(draw,locate,"2",region.getId());
				
				if (updateLocateNum == 1 && insertDrawNum == 1 && updateRegionNum==1) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", "200");
					jsonObject.put("data", "提交成功");
					ResponseUtils.write(response, jsonObject);
				} else {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", "201");
					jsonObject.put("data", "提交失败");
					ResponseUtils.write(response, jsonObject);
				}
				
			} else if(StringUtil.isEmpty(region.getLocate().getId()) && !StringUtil.isEmpty(region.getDraw().getId())){
				locate.setId(UUID.randomUUID().toString());
				draw.setId(region.getDraw().getId());
				int insertLocateNum = locateService.locateInsert(locate);
				int updateDrawNum = drawService.drawUpdate(draw);
				int updateRegionNum = regionService.updateRegionByDrawUpload(draw,locate,"2",region.getId());
				if (insertLocateNum == 1 && updateDrawNum == 1 && updateRegionNum==1) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", "200");
					jsonObject.put("data", "提交成功");
					ResponseUtils.write(response, jsonObject);
				} else {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", "201");
					jsonObject.put("data", "提交失败");
					ResponseUtils.write(response, jsonObject);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//遍历map进行打印
	public void printMap(Map<String,String[]> map) {
		for (Map.Entry<String,String[]> entry : map.entrySet()) { 
			  System.out.println("Key = " + entry.getKey() + ", Value = " + Arrays.toString(entry.getValue())); 
			}
	}

}
