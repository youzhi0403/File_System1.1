package cn.powerrun.service;

import java.sql.SQLException;

import cn.powerrun.model.Model;

public interface IModelService {

	int modelInsert(Model model) throws SQLException;

	int modelUpdate(Model model) throws SQLException;

	int delete(String id) throws SQLException;

}
