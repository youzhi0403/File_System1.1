package cn.powerrun.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class DBUtils {
	private static final String DRIVER;
	private static final String URL;
	private static final String USER;
	private static final String PASSWORD;

	static {
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		DRIVER = bundle.getString("driver");
		URL = bundle.getString("url");
		USER = bundle.getString("user");
		PASSWORD = bundle.getString("password");
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public static void closeResource(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		closeResultSet(rs);
		closePreparedStatement(pstmt);
		closeConnection(conn);
	}

	/**
	 * 释放连接 Connection
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 等待垃圾回收
		conn = null;
	}

	/**
	 * 释放语句执行者 Statement
	 * 
	 * @param st
	 */
	public static void closePreparedStatement(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 等待垃圾回收
		pstmt = null;
	}

	/**
	 * 释放结果集 ResultSet
	 * 
	 * @param rs
	 */
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 等待垃圾回收
		rs = null;
	}

}