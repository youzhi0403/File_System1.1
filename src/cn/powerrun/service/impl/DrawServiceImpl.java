package cn.powerrun.service.impl;

import java.sql.SQLException;

import cn.powerrun.dao.IDrawDao;
import cn.powerrun.dao.impl.DrawDaoImpl;
import cn.powerrun.model.Draw;
import cn.powerrun.service.IDrawService;

public class DrawServiceImpl implements IDrawService{
	IDrawDao drawDao = new DrawDaoImpl();

	@Override
	public int drawInsert(Draw draw) throws SQLException {
		
		return drawDao.drawInsert(draw);
	}

	@Override
	public int drawUpdate(Draw draw) throws SQLException {
		return drawDao.drawUpdate(draw);
	}

	@Override
	public int drawRetreat(String userId, String regionId) throws SQLException {
		return drawDao.drawRetreat(userId,regionId);
	}

	@Override
	public int delete(Draw draw) throws SQLException {
		
		return drawDao.delete(draw.getId());
	}
	
}
