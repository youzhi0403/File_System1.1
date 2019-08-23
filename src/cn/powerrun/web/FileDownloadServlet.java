package cn.powerrun.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.powerrun.model.Region;
import cn.powerrun.service.IRegionService;
import cn.powerrun.service.impl.RegionServiceImpl;
import cn.powerrun.utils.FileUtils;
import cn.powerrun.utils.ResponseUtils;
import cn.powerrun.utils.StringUtil;

public class FileDownloadServlet extends HttpServlet{
	IRegionService regionService = new RegionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regionName = request.getParameter("regionName");
		String fileType = request.getParameter("fileType");
		
		//根据regionName查询
		try{
			Region region = regionService.regionQueryByName(regionName);
			File file = null;
			switch(fileType){
			case "marketExcel":
				if(!StringUtil.isEmpty(region.getMarketExcel())){
					file = new File(region.getMarketExcel());
				}
				break;
			case "airPhoto":
				if(!StringUtil.isEmpty(region.getDraw().getAirPhoto())){
					file = new File(region.getDraw().getAirPhoto());
				}
				break;
			case "drawJSON":
				if(!StringUtil.isEmpty(region.getDraw().getDrawJSON())){
					file = new File(region.getDraw().getDrawJSON());
				}
				break;
			case "locateJSON":
				if(!StringUtil.isEmpty(region.getLocate().getLocateJSON())){
					file = new File(region.getLocate().getLocateJSON());
				}
				break;
			case "northPhoto":
				if(!StringUtil.isEmpty(region.getLocate().getNorthPhoto())){
					file = new File(region.getLocate().getNorthPhoto());
				}
				break;
			case "sdkJAR":
				if(!StringUtil.isEmpty(region.getLocate().getSdkJAR())){
					file = new File(region.getLocate().getSdkJAR());
				}
				break;
			case "jdkJAR":
				if(!StringUtil.isEmpty(region.getLocate().getJdkJAR())){
					file = new File(region.getLocate().getJdkJAR());
				}
				break;
			case "mod":
				if(!StringUtil.isEmpty(region.getModel().getMod())){
					file = new File(region.getModel().getMod());
				}
				break;
			case "modelJSON":
				if(!StringUtil.isEmpty(region.getModel().getModelJSON())){
					file = new File(region.getModel().getModelJSON());
				}
				break;
			case "reportExcel":
				if(!StringUtil.isEmpty(region.getReport().getReportExcel())){
					file = new File(region.getReport().getReportExcel());
				}
				break;
			case "reportPicWithBG":
				if(!StringUtil.isEmpty(region.getReport().getReportPicWithBG())){
					file = new File(region.getReport().getReportPicWithBG());
				}
				break;
			case "reportPicWithoutBG":
				if(!StringUtil.isEmpty(region.getReport().getReportPicWithoutBG())){
					file = new File(region.getReport().getReportPicWithoutBG());
				}
				break;
			case "reportCover":
				if(!StringUtil.isEmpty(region.getReport().getReportCover())){
					file = new File(region.getReport().getReportCover());
				}
				break;
			}
			if(file == null){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", 201);
				jsonObject.put("data", "请求文件不存在");
				ResponseUtils.write(response, jsonObject);
			}else{
				// 设置响应头，控制浏览器下载该文件
				String fileName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\")+1, file.getAbsolutePath().length());
				
				String userAgent = request.getHeader("User-Agent");
				byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes()
				: fileName.getBytes("UTF-8"); // fileName.getBytes("UTF-8")处理safari的乱码问题
				fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
				response.setHeader("Content-disposition",
				String.format("attachment; filename=\"%s\"", fileName));
				
				FileUtils.outputFile(file, response);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
}
