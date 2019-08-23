package cn.powerrun.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.powerrun.utils.FileUtils;

public class FilesDownloadServlet extends HttpServlet{
	final String saveDir = FileUtils.dir + File.separator + "fileSave";
	//zip包临时存储文件
	final public static String zipBasePath = "D:" + File.separator + "workspace" + File.separator + "PowerRunWeb" + File.separator + "zip";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*response.setContentType("text/html;charset=utf-8");//返回的数据类型
*/		
		String currentPath = request.getParameter("currentPath");
		String fileNames = request.getParameter("fileNames");
		String[] files = fileNames.split(",");
		if(files.length == 1) {
			// 设置响应头，控制浏览器下载该文件
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(files[0], "UTF-8"));
			
			String makePath = FileUtils.makePath(saveDir, currentPath);
			String filePath = makePath + File.separator + files[0];
			File file = new File(filePath);
			FileUtils.outputFile(file, response);
			
		}else if(files.length >1) {
			response.setHeader("content-disposition", "attachment;filename=temp.zip");
			
			//创建压缩文件所需要的空的zip包
			String zipFilePath = zipBasePath + File.separator + "temp.zip";
			//根据路径创建zip文件
			File zip = new File(zipFilePath);
			if(!zip.exists()) {
				zip.createNewFile();
			}
			//创建zip文件输出流
			FileOutputStream fos = new FileOutputStream(zip);
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			
			String makePath = FileUtils.makePath(saveDir, currentPath);
			for(int i=0;i<files.length;i++) {
				String filePath = makePath + File.separator + files[i];
				File file = new File(filePath);
				FileUtils.zipFile(file, zos);
			}
			zos.flush();
			FileUtils.outputFile(zip,response);
			fos.close();
			zos.close();
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
}
