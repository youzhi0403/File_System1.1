package cn.powerrun.service;

import java.sql.SQLException;

import cn.powerrun.model.Draw;

public interface IDrawService {

	int drawInsert(Draw draw) throws SQLException;

	int drawUpdate(Draw draw) throws SQLException;

	int drawRetreat(String userId, String regionId) throws SQLException;

	int delete(Draw draw) throws SQLException;
	
}
