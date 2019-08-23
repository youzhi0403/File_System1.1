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

public class FilesDeleteServlet extends HttpServlet{
	final String saveDir = FileUtils.dir + File.separator + "fileSave";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPath = request.getParameter("currentPath");
		String row_names  = request.getParameter("row_names");
		boolean b = false;
		int success = 0;
		int fail = 0;
		
		String[] deleteFiles = row_names.split(",");
		
		String makePath = FileUtils.makePath(saveDir, currentPath);
		
		for(int i=0;i<deleteFiles.length;i++) {
			String str = makePath + File.separator + deleteFiles[i];
			File file = new File(str);
			if(file.isFile()) {
				b = file.delete();
			}else if(file.isDirectory()) {
				b = deleteDir(file);
			}
			
			if(b) {
				success++;
			}else {
				fail++;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 200);
		jsonObject.put("data", "成功删除" + success + "条数据");
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
	
	//递归删除目录下的所有文件
	private static boolean deleteDir(File dir) {
		if(dir.isDirectory()) {
			String[] children = dir.list();
			//递归删除目录钟的子目录下
			for(int i=0;i<children.length;i++) {
				boolean success = deleteDir(new File(dir,children[i]));
				if(!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}
	
}
