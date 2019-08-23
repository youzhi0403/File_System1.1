package cn.powerrun.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import cn.powerrun.dao.IDrawDao;
import cn.powerrun.model.Draw;
import cn.powerrun.utils.DBUtils;

public class DrawDaoImpl implements IDrawDao{

	@Override
	public int drawInsert(Draw draw) throws SQLException {
		String sql = "insert into draw values(?,?,?,?)";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, draw.getId());
		pstmt.setTimestamp(2, new Timestamp(draw.getDrawDate().getTime()));
		pstmt.setString(3, draw.getAirPhoto());
		pstmt.setString(4, draw.getDrawJSON());
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public Draw drawQueryById(String id) throws SQLException {
		String sql = "select * from draw where id = ?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		Draw draw = new Draw();
		if(rs.next()) {
			draw.setId(rs.getString("id"));
			draw.setDrawDate(rs.getTimestamp("drawDate"));
			draw.setAirPhoto(rs.getString("airPhoto"));
			draw.setDrawJSON(rs.getString("drawJSON"));
		}
		
		
		DBUtils.closeResource(connection, pstmt, null);
		return draw;
	}

	@Override
	public int drawUpdate(Draw draw) throws SQLException {
		String sql = "update draw set drawDate=?,airPhoto=?,drawJSON=? where id=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setTimestamp(1, new Timestamp(draw.getDrawDate().getTime()));
		pstmt.setString(2, draw.getAirPhoto());
		pstmt.setString(3, draw.getDrawJSON());
		pstmt.setString(4, draw.getId());
		
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public int drawRetreat(String userId, String regionId) throws SQLException {
		String sql = "update region set drawRetreat=? where id = ?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, regionId);
		
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public int delete(String id) throws SQLException {
		String sql = "delete from draw where id=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, id);
		
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

}
