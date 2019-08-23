package cn.powerrun.dao;

import java.sql.SQLException;

import cn.powerrun.model.Draw;

public interface IDrawDao {

	int drawInsert(Draw draw) throws SQLException;

	Draw drawQueryById(String id) throws SQLException;

	int drawUpdate(Draw draw) throws SQLException;

	int drawRetreat(String userId, String regionId) throws SQLException;

	int delete(String id) throws SQLException;

}
