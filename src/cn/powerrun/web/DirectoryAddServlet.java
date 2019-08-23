package cn.powerrun.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.powerrun.utils.FileUtils;
import cn.powerrun.utils.ResponseUtils;

public class DirectoryAddServlet extends HttpServlet{
	final String saveDir = FileUtils.dir + File.separator + "fileSave";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPath = request.getParameter("currentPath");
		String newDirectory  = request.getParameter("newDirectory");
		
		//检查该路径下是否有重名
		String makePath = FileUtils.makePath(saveDir, currentPath);
		//在当前文件夹下创建文件
		JSONObject jsonObject = new JSONObject();
		if(FileUtils.isRepeat(new File(makePath),newDirectory)) {
			jsonObject = new JSONObject();
			jsonObject.put("code", 201);
			jsonObject.put("data", "文件夹名字重复");
			
		}else {
			String str = makePath + File.separator + newDirectory;
System.out.println(str);
			File file = new File(str);
			file.mkdir();
			jsonObject = new JSONObject();
			jsonObject.put("code", 200);
			jsonObject.put("data", "文件夹创建成功");
			
		}
		try {
			ResponseUtils.write(response, jsonObject);
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
