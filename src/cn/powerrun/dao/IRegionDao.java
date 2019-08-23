package cn.powerrun.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.powerrun.model.Draw;
import cn.powerrun.model.Locate;
import cn.powerrun.model.Model;
import cn.powerrun.model.Pagination;
import cn.powerrun.model.Region;
import cn.powerrun.model.Report;

public interface IRegionDao {

	int regionInsert(Region region) throws SQLException;

	boolean isRepeat(String regionName) throws SQLException;

	int regionUpdate(Draw draw, String regionName) throws SQLException;

	Region regionQueryByName(String regionName) throws SQLException;

	int regionUpdate(Locate locate, String regionName) throws SQLException;

	int regionUpdate(Model model, String regionName) throws SQLException;

	int regionUpdate(Report report, String regionName) throws SQLException;

	int regionStatusUpdate(String regionName) throws SQLException;

	List<Region> regionQueryByStatusAndCreateUserId(String userId, String status) throws SQLException;

	List<Region> regionQueryByStatusAndDrawUserId(String userId, String status) throws SQLException;

	List<Region> regionQueryByStatusAndLocateUserId(String userId, String status) throws SQLException;

	List<Region> regionQueryByStatusAndModelUserId(String userId, String status) throws SQLException;

	List<Region> regionQueryByStatusAndOutputUserId(String userId, String status) throws SQLException;

	List<Region> regionQueryByStatusAndFinishUserId(String userId, String status) throws SQLException;

	List<Region> regionQueryByNotRetreat(String status,String step,Pagination p) throws SQLException;

	int regionCountByNotRetreat(String status, String step) throws SQLException;

	List<Region> regionQueryByMySelf(String userId, String drawRetreat, Pagination pagination,String status) throws SQLException;

	int regionQueryByMySelf(String userId, String drawRetreat,String status) throws SQLException;

	int retreat(String regionId, String step) throws SQLException;

	Region regionQueryById(String regionId) throws SQLException;

	int regionDeleteById(String regionId) throws SQLException;



	int rob(String userId, String regionId, String step) throws SQLException;


	int updateRegionStatusAndModelRob(String regionId) throws SQLException;

	List<Region> regionQueryByStatus(String status,Pagination pagination) throws SQLException;

	int regionCountByStatus(String status) throws SQLException;

	int regionUpdate(String regionId, Date date, String string) throws SQLException;

	int updateRegionStatusAndModelRob(String regionId,String status, String id) throws SQLException;

	int updateRegionByDrawUpload(Draw draw, Locate locate, String status, String regionId) throws SQLException;

	int updateRegionByModelReject(String regionId) throws SQLException;



}
