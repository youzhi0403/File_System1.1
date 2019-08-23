package cn.powerrun.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.powerrun.model.Pagination;
import cn.powerrun.utils.FileUtils;
import cn.powerrun.utils.ResponseUtils;

public class FileListServlet extends HttpServlet {
	final String saveDir = FileUtils.dir + File.separator + "fileSave";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取指定文件夹,进行展示
		/*
		 * String currentPath = request.getParameter("path") == null ? null :
		 * request.getParameter("path").trim();
		 */
		String currentPath = request.getParameter("currentPath");
		int page = Integer.parseInt(request.getParameter("page").trim());
		int rows = Integer.parseInt(request.getParameter("rows").trim());
		Pagination pagination = new Pagination(page, rows);
		
		JSONObject result = null;
		String makePath = FileUtils.makePath(saveDir, currentPath);
		result = FileUtils.getFileList(makePath, pagination);
		try {
			ResponseUtils.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	public static void printMap(Map map) {
		Iterator<Map.Entry<Object, Object>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<Object, Object> entry = entries.next();
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
	}

}
