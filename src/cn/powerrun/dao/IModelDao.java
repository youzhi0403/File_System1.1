package cn.powerrun.dao;

import java.sql.SQLException;

import cn.powerrun.model.Model;

public interface IModelDao {

	int modelInsert(Model model) throws SQLException;

	int modelUpdate(Model model) throws SQLException;

	Model modelQueryById(String id) throws SQLException;

	int delete(String id) throws SQLException;

}
