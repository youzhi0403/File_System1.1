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

public class RegisterServlet extends HttpServlet{
	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try{
			int registerNum = userService.register(account,username,password);
			if(registerNum == 1){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", 200);
				jsonObject.put("data", "注册成功");
				ResponseUtils.write(response, jsonObject);
			}else{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", 201);
				jsonObject.put("data", "注册失败");
				ResponseUtils.write(response, jsonObject);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
