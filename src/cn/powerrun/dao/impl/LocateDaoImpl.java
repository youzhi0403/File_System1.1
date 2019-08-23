package cn.powerrun.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import cn.powerrun.dao.ILocateDao;
import cn.powerrun.model.Locate;
import cn.powerrun.utils.DBUtils;

public class LocateDaoImpl implements ILocateDao{

	@Override
	public int locateInsert(Locate locate) throws SQLException {
		String sql = "insert into locate(id,locateDate,jdkJAR,sdkJAR,northPhoto,locateJSON) values(?,?,?,?,?,?)";

		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, locate.getId());
		pstmt.setTimestamp(2, new Timestamp(locate.getLocateDate().getTime()));
		pstmt.setString(3, locate.getJdkJAR());
		pstmt.setString(4, locate.getSdkJAR());
		pstmt.setString(5, locate.getNorthPhoto());
		pstmt.setString(6, locate.getLocateJSON());
		
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public Locate locateQueryById(String id) throws SQLException {
		String sql = "select * from locate where id=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		Locate locate = new Locate();
		if(rs.next()) {
			locate.setId(rs.getString("id"));
			locate.setLocateDate(rs.getTimestamp("locateDate"));
			locate.setJdkJAR(rs.getString("jdkJAR"));
			locate.setSdkJAR(rs.getString("sdkJAR"));
			locate.setNorthPhoto(rs.getString("northPhoto"));
			locate.setLocateJSON(rs.getString("locateJSON"));
		}

		return locate;
	}

	@Override
	public int locateUpdate(Locate locate) throws SQLException {
		String sql = "update locate set locateDate=?,jdkJAR=?,sdkJAR=?,northPhoto=?,locateJSON=? where id=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setTimestamp(1, new Timestamp(locate.getLocateDate().getTime()));
		pstmt.setString(2, locate.getJdkJAR());
		pstmt.setString(3, locate.getSdkJAR());
		pstmt.setString(4, locate.getNorthPhoto());
		pstmt.setString(5, locate.getLocateJSON());
		pstmt.setString(6, locate.getId());
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public int delete(String id) throws SQLException {
		String sql = "delete from locate where id=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, id);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

}
