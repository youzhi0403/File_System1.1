package cn.powerrun.dao;

import java.sql.SQLException;

import cn.powerrun.model.Report;

public interface IReportDao {

	int reportInsert(Report report) throws SQLException;

	int reportUpdate(Report report) throws SQLException;

	Report reportQueryById(String id) throws SQLException;

	int reportDelete(String id) throws SQLException;

}
