package cn.powerrun.service;

import java.sql.SQLException;
import java.util.List;

import cn.powerrun.model.Draw;
import cn.powerrun.model.Locate;
import cn.powerrun.model.Model;
import cn.powerrun.model.Pagination;
import cn.powerrun.model.Region;
import cn.powerrun.model.Report;

public interface IRegionService {

	int regionInsert(String regionName, String createUserId, String drawUserId, String modelUserId,
			String outputUserId, String finishUserId, String marketExcel) throws SQLException;

	boolean isRepeat(String regionName) throws SQLException;

	int regionUpdate(Draw draw, String regionName) throws SQLException;

	Region regionQueryByName(String regionName) throws SQLException;

	int regionUpdate(Locate locate, String regionName) throws SQLException;

	Draw drawQuery(String regionName) throws SQLException;

	Locate locateQuery(String regionName) throws SQLException;

	int regionUpdate(Model model, String regionName) throws SQLException;

	Region regionQuery(String regionName) throws SQLException;

	Model modelQuery(String regionName) throws SQLException;

	int regionUpdate(Report report, String regionName) throws SQLException;

	Report reportQuery(String regionName) throws SQLException;

	int regionStatusUpdate(String regionName) throws SQLException;

	List<Region> regionQueryByStatus(String userId, String status) throws SQLException;

	List<Region> regionQueryByNotRetreat(String status,String step, Pagination pagination) throws SQLException;

	int regionCountByNotRetreat(String string, String string2) throws SQLException;




	int retreat(String regionId, String step) throws SQLException;

	Region regionQueryById(String regionId) throws SQLException;

	int regionDeleteById(String regionId) throws SQLException;

	int rob(String userId, String regionId, String string) throws SQLException;

	int regionCountByMySelf(String userId, String drawRetreat, String status) throws SQLException;

	List<Region> regionQueryByMySelf(String userId, String drawRetreat, Pagination pagination, String status)
			throws SQLException;


	int updateRegionStatusAndModelRob(String regionId) throws SQLException;

	List<Region> regionQueryByStatus(String string,Pagination pagination) throws SQLException;

	int regionCountByStatus(String string) throws SQLException;

	int regionFinish(String regionId) throws SQLException;

	int updateRegionStatusAndReportRobAndReportId(String regionId,String status, String id) throws SQLException;

	int updateRegionByDrawUpload(Draw draw, Locate locate, String status,String regionId) throws SQLException;

	int updateRegionByModelReject(String regionId) throws SQLException;



	
}
