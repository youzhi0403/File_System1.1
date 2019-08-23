package cn.powerrun.service.impl;

import java.sql.SQLException;

import cn.powerrun.dao.IModelDao;
import cn.powerrun.dao.impl.ModelDaoImpl;
import cn.powerrun.model.Model;
import cn.powerrun.service.IModelService;

public class ModelServiceImpl implements IModelService{
	IModelDao modelDao = new ModelDaoImpl();

	@Override
	public int modelInsert(Model model) throws SQLException {
		return modelDao.modelInsert(model);
	}

	@Override
	public int modelUpdate(Model model) throws SQLException {
		return modelDao.modelUpdate(model);
	}

	@Override
	public int delete(String id) throws SQLException {
		return modelDao.delete(id);
	}
	

}
