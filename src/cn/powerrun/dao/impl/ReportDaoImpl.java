package cn.powerrun.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import cn.powerrun.dao.IReportDao;
import cn.powerrun.model.Report;
import cn.powerrun.utils.DBUtils;

public class ReportDaoImpl implements IReportDao{

	@Override
	public int reportInsert(Report report) throws SQLException {
		String sql = "insert into report(id,outputDate,reportExcel,reportPicWithBG,reportPicWithoutBG,reportCover) values(?,?,?,?,?,?)";

		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, report.getId());
		pstmt.setTimestamp(2, new Timestamp(report.getOutputDate().getTime()));
		pstmt.setString(3, report.getReportExcel());
		pstmt.setString(4, report.getReportPicWithBG());
		pstmt.setString(5, report.getReportPicWithoutBG());
		pstmt.setString(6, report.getReportCover());
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public int reportUpdate(Report report) throws SQLException {
		String sql = "update region set outputDate=?,reportExcel=?,reportPicWithBG=?,reportPicWithoutBG=?,reportCover=? where id=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setTimestamp(1, new Timestamp(report.getOutputDate().getTime()));
		pstmt.setString(2, report.getReportExcel());
		pstmt.setString(3, report.getReportPicWithBG());
		pstmt.setString(4, report.getReportPicWithoutBG());
		pstmt.setString(5, report.getReportCover());
		pstmt.setString(6, report.getId());
		
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public Report reportQueryById(String id) throws SQLException {
		String sql = "select * from report where id=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		Report report = new Report();
		if(rs.next()) {
			report.setId(rs.getString("id"));
			report.setOutputDate(rs.getTimestamp("outputDate"));
			report.setReportExcel(rs.getString("reportExcel"));
			report.setReportPicWithBG(rs.getString("reportPicWithBG"));
			report.setReportPicWithoutBG(rs.getString("reportPicWithoutBG"));
			report.setReportCover(rs.getString("reportCover"));
		}
		
		DBUtils.closeResource(connection, pstmt, rs);
		return report;
	}

	@Override
	public int reportDelete(String id) throws SQLException {
		String sql = "delete from report where id=?";

		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, id);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

}
