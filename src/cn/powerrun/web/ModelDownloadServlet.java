package cn.powerrun.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.powerrun.model.Locate;
import cn.powerrun.model.Model;
import cn.powerrun.service.IRegionService;
import cn.powerrun.service.impl.RegionServiceImpl;
import cn.powerrun.utils.FileUtils;
import cn.powerrun.utils.StringUtil;

public class ModelDownloadServlet extends HttpServlet{
	//zip包临时存储文件
	final public static String zipBasePath = "D:" + File.separator + "workspace" + File.separator + "PowerRunWeb" + File.separator + "zip";
	IRegionService regionService = new RegionServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regionName = request.getParameter("regionName");
		try {
			Model model = regionService.modelQuery(regionName);
			// 设置响应头，控制浏览器下载该文件
			response.setHeader("content-disposition", "attachment;filename="+regionName+"-model.zip");
			//创建压缩文件所需要的空的zip包
			String zipFilePath = zipBasePath + File.separator + "temp.zip";
			
			File zip = new File(zipFilePath);
			if(!zip.exists()) {
				zip.createNewFile();
			}
			//创建zip文件输出流
			FileOutputStream fos = new FileOutputStream(zip);
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			if(StringUtil.isNotEmpty(model.getMod())) {
				File jdk = new File(model.getMod());
				FileUtils.zipFile(jdk, zos);
			}
			if(StringUtil.isNotEmpty(model.getModelJSON())) {
				File sdk = new File(model.getModelJSON());
				FileUtils.zipFile(sdk, zos);
			}
			if(StringUtil.isNotEmpty(model.getNorthPhoto())) {
				File northPhoto = new File(model.getNorthPhoto());
				FileUtils.zipFile(northPhoto, zos);
			}

			zos.flush();
			zos.close();
			fos.close();
			FileUtils.outputFile(zip,response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
}
