package cn.powerrun.dao;

import java.sql.SQLException;

import cn.powerrun.model.Locate;

public interface ILocateDao {

	int locateInsert(Locate locate) throws SQLException;

	Locate locateQueryById(String id) throws SQLException;

	int locateUpdate(Locate locate) throws SQLException;

	int delete(String id) throws SQLException;
	
}
