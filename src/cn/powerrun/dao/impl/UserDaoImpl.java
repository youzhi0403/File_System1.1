package cn.powerrun.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.powerrun.dao.IUserDao;
import cn.powerrun.model.User;
import cn.powerrun.utils.DBUtils;

public class UserDaoImpl implements IUserDao{

	@Override
	public List<User> userQuery() throws SQLException {
		List<User> list = new ArrayList();
		String sql = "select * from user";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			User user = new User(rs.getString("id"),rs.getString("username"),rs.getString("pw"),rs.getString("account"));
			list.add(user);
		}
		DBUtils.closeResource(connection, pstmt, rs);
		return list;
	}

	@Override
	public int add(User user) throws SQLException {
		String sql = "insert into user(id,username,pw,account) values(?,?,?,?)";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getUsername());
		pstmt.setString(3, user.getPassword());
		pstmt.setString(4, user.getAccount());
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public boolean isExist(String account) throws SQLException {
		String sql = "select * from user where account = ?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, account);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			DBUtils.closeResource(connection, pstmt, rs);
			return true;
		}
		DBUtils.closeResource(connection, pstmt, rs);
		return false;
	}

	@Override
	public User login(String account, String password) throws SQLException {
		String sql = "select * from user where account=? and pw=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, account);
		pstmt.setString(2, password);
		User user = null;
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			user = new User(rs.getString("id"),rs.getString("username"),rs.getString("pw"),rs.getString("account"));
		}
		DBUtils.closeResource(connection, pstmt, rs);
		return user;
	}
	
}
