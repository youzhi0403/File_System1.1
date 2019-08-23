package cn.powerrun.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.powerrun.service.IUserService;
import cn.powerrun.service.impl.UserServiceImpl;
import cn.powerrun.utils.ResponseUtils;

public class UserIsRepeatServlet extends HttpServlet{
	IUserService userService  = new UserServiceImpl();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		JSONObject result = new JSONObject();
		try {
			if(userService.isExist(account)) {
				result.put("code", 201);
				result.put("data", "台区重名");
			}else {
				result.put("code", 200);
				result.put("data", "台区名称可以使用");
			}
		ResponseUtils.write(response, result);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
