package cn.powerrun.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.powerrun.model.User;
import cn.powerrun.service.IUserService;
import cn.powerrun.service.impl.UserServiceImpl;
import cn.powerrun.utils.ResponseUtils;

public class UserQueryServlet extends HttpServlet{
	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<User> list = userService.userQuery();
			JSONObject result = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			for(User user:list) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", user.getId());
				jsonObject.put("username", user.getUsername());
				jsonArray.put(jsonObject);
			}
			result.put("data", jsonArray);
			ResponseUtils.write(response, result);
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
