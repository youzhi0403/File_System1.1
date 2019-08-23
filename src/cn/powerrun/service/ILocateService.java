package cn.powerrun.service;

import java.sql.SQLException;

import cn.powerrun.model.Locate;

public interface ILocateService {

	int locateInsert(Locate locate) throws SQLException;

	int locateUpdate(Locate locate) throws SQLException;

	int delete(Locate locate) throws SQLException;

}
