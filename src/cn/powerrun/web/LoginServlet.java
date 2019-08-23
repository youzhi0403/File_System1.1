package cn.powerrun.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.powerrun.model.User;
import cn.powerrun.service.IUserService;
import cn.powerrun.service.impl.UserServiceImpl;
import cn.powerrun.utils.ResponseUtils;

public class LoginServlet extends HttpServlet{
	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		try{
			boolean result = userService.isExist(account);
			if(result){
				User user = userService.login(account,password);
				if(user != null){
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", 200);
					jsonObject.put("data", "登陆成功");
					jsonObject.put("userId", user.getId());
					jsonObject.put("username", user.getUsername());
					
					ResponseUtils.write(response, jsonObject);
				}else{
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", 201);
					jsonObject.put("data", "密码错误");
					ResponseUtils.write(response, jsonObject);
				}
				
			}else{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", 201);
				jsonObject.put("data", "账户不存在");
				ResponseUtils.write(response, jsonObject);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	

}
