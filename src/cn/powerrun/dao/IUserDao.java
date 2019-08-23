package cn.powerrun.dao;

import java.sql.SQLException;
import java.util.List;

import cn.powerrun.model.User;

public interface IUserDao {

	List<User> userQuery() throws SQLException;

	int add(User user) throws SQLException;

	boolean isExist(String account) throws SQLException;

	User login(String account, String password) throws SQLException;

}
