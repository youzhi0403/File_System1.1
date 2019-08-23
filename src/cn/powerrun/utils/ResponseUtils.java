package cn.powerrun.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


public class ResponseUtils {
	public static void write(HttpServletResponse response, JSONObject jsonObject) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(jsonObject.toString());
		out.flush();// 刷新
		out.close();
	}
	
	public static void write(HttpServletResponse response, JSONArray array) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(array.toString());
		out.flush();// 刷新
		out.close();
	}
}
