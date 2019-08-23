package cn.powerrun.service;

import java.sql.SQLException;

import cn.powerrun.model.Report;

public interface IReportService {

	int reportInsert(Report report) throws SQLException;

	int reportUpdate(Report report) throws SQLException;

	int reportDelete(String id) throws SQLException;


}
