package cn.powerrun.service.impl;

import java.sql.SQLException;

import cn.powerrun.dao.IReportDao;
import cn.powerrun.dao.impl.ReportDaoImpl;
import cn.powerrun.model.Report;
import cn.powerrun.service.IReportService;

public class ReportServiceImpl implements IReportService{
	IReportDao reportDao = new ReportDaoImpl();

	@Override
	public int reportInsert(Report report) throws SQLException {
		return reportDao.reportInsert(report);
	}

	@Override
	public int reportUpdate(Report report) throws SQLException {
		return reportDao.reportUpdate(report);
	}

	@Override
	public int reportDelete(String id) throws SQLException {
		return reportDao.reportDelete(id);
	}

}
