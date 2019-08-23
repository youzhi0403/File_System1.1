package cn.powerrun.service.impl;

import java.sql.SQLException;

import cn.powerrun.dao.ILocateDao;
import cn.powerrun.dao.impl.LocateDaoImpl;
import cn.powerrun.model.Locate;
import cn.powerrun.service.ILocateService;

public class LocateServiceImpl implements ILocateService{
	ILocateDao locateDao = new LocateDaoImpl();

	@Override
	public int locateInsert(Locate locate) throws SQLException {
		return locateDao.locateInsert(locate);
	}

	@Override
	public int locateUpdate(Locate locate) throws SQLException {
		return locateDao.locateUpdate(locate);
	}

	@Override
	public int delete(Locate locate) throws SQLException {
		return locateDao.delete(locate.getId());
	}

}
