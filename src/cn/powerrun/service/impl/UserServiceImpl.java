package cn.powerrun.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import cn.powerrun.dao.IUserDao;
import cn.powerrun.dao.impl.UserDaoImpl;
import cn.powerrun.model.User;
import cn.powerrun.service.IUserService;

public class UserServiceImpl implements IUserService{
	IUserDao userDao = new UserDaoImpl();

	@Override
	public List<User> userQuery() throws SQLException {
		return userDao.userQuery();
	}

	public int register(String account, String username, String password) throws SQLException {
		User user = new User(UUID.randomUUID().toString(),username,password,account);
		return userDao.add(user);
	}

	@Override
	public boolean isExist(String account) throws SQLException {
		return userDao.isExist(account);
	}

	@Override
	public User login(String account, String password) throws SQLException {
		return userDao.login(account,password);
	}

}
