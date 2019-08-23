package cn.powerrun.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import cn.powerrun.dao.IDrawDao;
import cn.powerrun.dao.ILocateDao;
import cn.powerrun.dao.IModelDao;
import cn.powerrun.dao.IRegionDao;
import cn.powerrun.dao.IReportDao;
import cn.powerrun.dao.impl.DrawDaoImpl;
import cn.powerrun.dao.impl.LocateDaoImpl;
import cn.powerrun.dao.impl.ModelDaoImpl;
import cn.powerrun.dao.impl.RegionDaoImpl;
import cn.powerrun.dao.impl.ReportDaoImpl;
import cn.powerrun.model.Draw;
import cn.powerrun.model.Locate;
import cn.powerrun.model.Model;
import cn.powerrun.model.Pagination;
import cn.powerrun.model.Region;
import cn.powerrun.model.Report;
import cn.powerrun.model.User;
import cn.powerrun.service.IRegionService;
import cn.powerrun.utils.StringUtil;

public class RegionServiceImpl implements IRegionService{
	IRegionDao regionDao = new RegionDaoImpl();
	IDrawDao drawDao = new DrawDaoImpl();
	ILocateDao locateDao = new LocateDaoImpl();
	IModelDao modelDao = new ModelDaoImpl();
	IReportDao reportDao = new ReportDaoImpl();

	@Override
	public int regionInsert(String regionName, String createUserId, String drawUserId,
			String modelUserId, String outputUserId, String finishUserId, String marketExcel) throws SQLException {
		String id = UUID.randomUUID().toString();
		Date createDate = new Date();
		String status = "1";
		
		Region region = new Region(id,regionName,createDate,new User(createUserId),new User(drawUserId),new User(modelUserId),new User(outputUserId),new User(finishUserId),marketExcel,status);

		return regionDao.regionInsert(region);
	}

	@Override
	public boolean isRepeat(String regionName) throws SQLException {
		return regionDao.isRepeat(regionName);
	}

	@Override
	public int regionUpdate(Draw draw, String regionName) throws SQLException {
		
		return regionDao.regionUpdate(draw,regionName);
	}

	@Override
	public Region regionQueryByName(String regionName) throws SQLException {
		Region result = regionDao.regionQueryByName(regionName);
		if(!StringUtil.isEmpty(result.getDraw().getId())){
			Draw draw = drawDao.drawQueryById(result.getDraw().getId());
			result.setDraw(draw);
		}
		if(!StringUtil.isEmpty(result.getLocate().getId())) {
			Locate locate = locateDao.locateQueryById(result.getLocate().getId());
			result.setLocate(locate);
		}
		if(!StringUtil.isEmpty(result.getModel().getId())) {
			Model model = modelDao.modelQueryById(result.getModel().getId());
			result.setModel(model);
		}
		if(!StringUtil.isEmpty(result.getReport().getId())) {
			Report report = reportDao.reportQueryById(result.getReport().getId());
			result.setReport(report);
		}
		return result;
		
	}

	@Override
	public int regionUpdate(Locate locate, String regionName) throws SQLException {
		return regionDao.regionUpdate(locate, regionName);
	}

	@Override
	public Draw drawQuery(String regionName) throws SQLException {
		//先查询出drawId
		Region region = regionDao.regionQueryByName(regionName);
		//再查询出Draw
		return drawDao.drawQueryById(region.getDraw().getId());
	}

	@Override
	public Locate locateQuery(String regionName) throws SQLException {
		Region region = regionDao.regionQueryByName(regionName);
		
		return locateDao.locateQueryById(region.getLocate().getId());
	}

	@Override
	public int regionUpdate(Model model, String regionName) throws SQLException {
		return regionDao.regionUpdate(model, regionName);
	}

	@Override
	public Region regionQuery(String regionName) throws SQLException {
		return regionDao.regionQueryByName(regionName);
	}

	@Override
	public Model modelQuery(String regionName) throws SQLException {
		//先查询出drawId
		Region region = regionDao.regionQueryByName(regionName);
		//再查询出Draw
		return modelDao.modelQueryById(region.getModel().getId());
	}

	@Override
	public int regionUpdate(Report report, String regionName) throws SQLException {
		return regionDao.regionUpdate(report, regionName);
	}

	@Override
	public Report reportQuery(String regionName) throws SQLException {
		//先查询出drawId
		Region region = regionDao.regionQueryByName(regionName);
		//再查询出Draw
		return reportDao.reportQueryById(region.getReport().getId());
	}

	@Override
	public int regionStatusUpdate(String regionName) throws SQLException {
		return regionDao.regionStatusUpdate(regionName);
	}

	@Override
	public List<Region> regionQueryByStatus(String userId, String status) throws SQLException {
		List<Region> list = new ArrayList();
		switch(status) {
			case "1":
				list = regionDao.regionQueryByStatusAndCreateUserId(userId,status);
				break;
			case "2":
				list = regionDao.regionQueryByStatusAndDrawUserId(userId,status);
				break;
			case "3":
				list = regionDao.regionQueryByStatusAndLocateUserId(userId,status);
				break;
			case "4":
				list = regionDao.regionQueryByStatusAndModelUserId(userId,status);
				break;
			case "5":
				list = regionDao.regionQueryByStatusAndOutputUserId(userId,status);
				break;
			case "6":
				list = regionDao.regionQueryByStatusAndFinishUserId(userId,status);
				break;
		}
		return list;
	}

	@Override
	public List<Region> regionQueryByNotRetreat(String status,String step,Pagination p) throws SQLException {
		
		return regionDao.regionQueryByNotRetreat(status,step,p);
	}

	@Override
	public int regionCountByNotRetreat(String status, String step) throws SQLException {
		return regionDao.regionCountByNotRetreat(status,step);
	}


	@Override
	public List<Region> regionQueryByMySelf(String userId, String drawRetreat, Pagination pagination,String status) throws SQLException {
		// TODO Auto-generated method stub
		return regionDao.regionQueryByMySelf(userId,drawRetreat,pagination,status);
	}

	@Override
	public int regionCountByMySelf(String userId, String drawRetreat,String status) throws SQLException {
		return regionDao.regionQueryByMySelf(userId,drawRetreat,status);
	}

	@Override
	public int retreat(String regionId, String step) throws SQLException {
		return regionDao.retreat(regionId,step);
	}

	@Override
	public Region regionQueryById(String regionId) throws SQLException {
		Region result = regionDao.regionQueryById(regionId);
		if(!StringUtil.isEmpty(result.getDraw().getId())){
			Draw draw = drawDao.drawQueryById(result.getDraw().getId());
			result.setDraw(draw);
		}
		if(!StringUtil.isEmpty(result.getLocate().getId())) {
			Locate locate = locateDao.locateQueryById(result.getLocate().getId());
			result.setLocate(locate);
		}
		if(!StringUtil.isEmpty(result.getModel().getId())) {
			Model model = modelDao.modelQueryById(result.getModel().getId());
			result.setModel(model);
		}
		if(!StringUtil.isEmpty(result.getReport().getId())) {
			Report report = reportDao.reportQueryById(result.getReport().getId());
			result.setReport(report);
		}
		return result;
	}

	@Override
	public int regionDeleteById(String regionId) throws SQLException {
		return regionDao.regionDeleteById(regionId);
	}

	@Override
	public int rob(String userId, String regionId, String step) throws SQLException {
		
		return regionDao.rob(userId,regionId,step);
	}

	@Override
	public int updateRegionStatusAndModelRob(String regionId) throws SQLException {
		return regionDao.updateRegionStatusAndModelRob(regionId);
	}

	@Override
	public List<Region> regionQueryByStatus(String status,Pagination pagination) throws SQLException {
		
		return regionDao.regionQueryByStatus(status,pagination);
	}

	@Override
	public int regionCountByStatus(String status) throws SQLException {
		
		return regionDao.regionCountByStatus(status);
	}

	@Override
	public int regionFinish(String regionId) throws SQLException {
		return regionDao.regionUpdate(regionId,new Date(),"5");
	}

	@Override
	public int updateRegionStatusAndReportRobAndReportId(String regionId,String status, String id) throws SQLException {
		return regionDao.updateRegionStatusAndModelRob(regionId,status,id);
	}

	@Override
	public int updateRegionByDrawUpload(Draw draw, Locate locate, String status,String regionId) throws SQLException {
		// TODO Auto-generated method stub
		return regionDao.updateRegionByDrawUpload(draw, locate, status, regionId);
	}

	@Override
	public int updateRegionByModelReject(String regionId) throws SQLException {
		
		return regionDao.updateRegionByModelReject(regionId);
	}


}
