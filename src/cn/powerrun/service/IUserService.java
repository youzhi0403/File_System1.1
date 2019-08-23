package cn.powerrun.service;

import java.sql.SQLException;
import java.util.List;

import cn.powerrun.model.User;

public interface IUserService {

	List<User> userQuery() throws SQLException;

	int register(String account, String username, String password) throws SQLException;

	boolean isExist(String account) throws SQLException;

	User login(String account, String password) throws SQLException;

}
