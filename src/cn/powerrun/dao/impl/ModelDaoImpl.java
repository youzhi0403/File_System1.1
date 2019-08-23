package cn.powerrun.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import cn.powerrun.dao.IModelDao;
import cn.powerrun.model.Model;
import cn.powerrun.utils.DBUtils;

public class ModelDaoImpl implements IModelDao{

	@Override
	public int modelInsert(Model model) throws SQLException {
		String sql = "insert into model(id,modelDate,modFile,modelJSON,northPhoto) values(?,?,?,?,?)";

		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, model.getId());
		pstmt.setTimestamp(2, new Timestamp(model.getModelDate().getTime()));
		pstmt.setString(3, model.getMod());
		pstmt.setString(4, model.getModelJSON());
		pstmt.setString(5, model.getNorthPhoto());
		int result = pstmt.executeUpdate();
		System.out.println();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public int modelUpdate(Model model) throws SQLException {
		String sql = "update region set modelDate=?,mod=?,modelJSON=?,northPhoto=? where id=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setTimestamp(1, new Timestamp(model.getModelDate().getTime()));
		pstmt.setString(2, model.getMod());
		pstmt.setString(3, model.getModelJSON());
		pstmt.setString(4, model.getNorthPhoto());
		pstmt.setString(5, model.getId());
		
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public Model modelQueryById(String id) throws SQLException {
		String sql = "select * from model where id = ?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		Model model = new Model();
		if(rs.next()) {
			model.setId(rs.getString("id"));
			model.setModelDate(rs.getTimestamp("modelDate"));
			model.setMod(rs.getString("modFile"));
			model.setModelJSON(rs.getString("modelJSON"));
			model.setNorthPhoto(rs.getString("northPhoto"));
			
		}
		return model;
	}

	@Override
	public int delete(String id) throws SQLException {
		String sql = "delete from model where id=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, id);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

}
