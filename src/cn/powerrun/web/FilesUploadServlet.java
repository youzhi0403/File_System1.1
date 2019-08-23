package cn.powerrun.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.powerrun.utils.FileUtils;

public class FilesUploadServlet extends HttpServlet{
	final String saveDir = FileUtils.dir + File.separator + "fileSave";
	final String tempPath = FileUtils.dir + File.separator + "temp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regionName = request.getParameter("regionName");
		
		File tempPathDirectory = new File(tempPath);
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			// Set factory constraints
			factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
			factory.setRepository(tempPathDirectory);// 设置缓冲区目录
			
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			// Set overall request size constraint
			upload.setSizeMax(419430400); // 设置最大文件尺寸，这里是400MB
			
			List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
			Iterator<FileItem> i = items.iterator();
			while(i.hasNext()) {
				FileItem fi = i.next();
				String fileName = fi.getName();
				if (fileName != null) {
					File fullFile = new File(new String(fi.getName().getBytes(), "utf-8")); // 解决文件名乱码问题
					File savedFile = new File(saveDir, fullFile.getName());
					fi.write(savedFile);
				}
			}
			
			System.out.println("upload succeed");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
