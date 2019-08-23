package cn.powerrun.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.powerrun.dao.IRegionDao;
import cn.powerrun.model.Draw;
import cn.powerrun.model.Locate;
import cn.powerrun.model.Model;
import cn.powerrun.model.Pagination;
import cn.powerrun.model.Region;
import cn.powerrun.model.Report;
import cn.powerrun.model.User;
import cn.powerrun.utils.DBUtils;

public class RegionDaoImpl implements IRegionDao {

	@Override
	public int regionInsert(Region region) throws SQLException {
		String sql = "insert into region(id,regionName,status,marketExcel,createDate,createUserId,drawUserId,modelUserId,outputUserId,finishUserId) values(?,?,?,?,?,?,?,?,?,?)";

		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, region.getId());
		pstmt.setString(2, region.getRegionName());
		pstmt.setString(3, region.getStatus());
		pstmt.setString(4, region.getMarketExcel());
		pstmt.setTimestamp(5, new Timestamp(region.getCreateDate().getTime()));
		pstmt.setString(6, region.getCreateUser().getId());
		pstmt.setString(7, region.getDrawUser().getId());
		pstmt.setString(8, region.getModelUser().getId());
		pstmt.setString(9, region.getOutputUser().getId());
		pstmt.setString(10, region.getFinishUser().getId());
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public boolean isRepeat(String regionName) throws SQLException {
		String sql = "select * from region where regionName = ?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, regionName);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			DBUtils.closeResource(connection, pstmt, rs);
			return true;
		} else {
			DBUtils.closeResource(connection, pstmt, rs);
			return false;
		}
	}

	@Override
	public int regionUpdate(Draw draw, String regionName) throws SQLException {
		String sql = "update region set drawId=?,status=? where regionName=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, draw.getId());
		pstmt.setString(2, "2");
		pstmt.setString(3, regionName);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public Region regionQueryByName(String regionName) throws SQLException {
		String sql = "select * from region where regionName=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, regionName);
		ResultSet rs = pstmt.executeQuery();
		Region region = new Region();
		if (rs.next()) {
			region.setId(rs.getString("id"));
			region.setRegionName(rs.getString("regionName"));
			region.setCreateDate(rs.getTimestamp("createDate"));
			region.setFinishDate(rs.getTimestamp("finishDate"));
			region.setCreateUser(new User(rs.getString("createUserId")));
			region.setDrawUser(new User(rs.getString("drawUserId")));
			region.setModelUser(new User(rs.getString("modelUserId")));
			region.setOutputUser(new User(rs.getString("outputUserId")));
			region.setFinishUser(new User(rs.getString("finishUserId")));

			region.setDraw(new Draw(rs.getString("drawId")));
			region.setLocate(new Locate(rs.getString("locateId")));
			region.setModel(new Model(rs.getString("modelId")));
			region.setReport(new Report(rs.getString("reportId")));
			region.setStatus(rs.getString("status"));

			region.setDrawRetreat(new User(rs.getString("drawRetreat")));
			region.setModelRetreat(new User(rs.getString("modelRetreat")));
			region.setOutputRetreat(new User(rs.getString("outputRetreat")));

			region.setMarketExcel(rs.getString("marketExcel"));

		}
		DBUtils.closeResource(connection, pstmt, rs);

		return region;
	}

	@Override
	public int regionUpdate(Locate locate, String regionName) throws SQLException {
		String sql = "update region set locateId=? where regionName=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, locate.getId());
		pstmt.setString(2, regionName);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public int regionUpdate(Model model, String regionName) throws SQLException {
		String sql = "update region set modelId=?,status=? where regionName=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, model.getId());
		pstmt.setString(2, "3");
		pstmt.setString(3, regionName);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public int regionUpdate(Report report, String regionName) throws SQLException {
		String sql = "update region set reportId=?,status=? where regionName=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, report.getId());
		pstmt.setString(2, "4");
		pstmt.setString(3, regionName);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public int regionStatusUpdate(String regionName) throws SQLException {
		String sql = "update region set status=?,finishDate=? where regionName=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "6");
		pstmt.setTimestamp(2, new Timestamp(new Date().getTime()));
		pstmt.setString(3, regionName);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public List<Region> regionQueryByStatusAndCreateUserId(String userId, String status) throws SQLException {
		String sql = "select * from region where createUserId=? and status=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, status);
		List<Region> result = new ArrayList();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Region region = new Region();
			region.setId(rs.getString("id"));
			region.setRegionName(rs.getString("regionName"));
			region.setCreateDate(rs.getTimestamp("createDate"));
			region.setFinishDate(rs.getTimestamp("finishDate"));
			result.add(region);
		}
		DBUtils.closeResource(connection, pstmt, rs);
		return result;
	}

	@Override
	public List<Region> regionQueryByStatusAndDrawUserId(String userId, String status) throws SQLException {
		String sql = "select * from region where drawUserId=? and status=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, status);
		List<Region> result = new ArrayList();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Region region = new Region();
			region.setId(rs.getString("id"));
			region.setRegionName(rs.getString("regionName"));
			region.setCreateDate(rs.getTimestamp("createDate"));
			region.setFinishDate(rs.getTimestamp("finishDate"));
			result.add(region);
		}
		DBUtils.closeResource(connection, pstmt, rs);
		return result;
	}

	@Override
	public List<Region> regionQueryByStatusAndLocateUserId(String userId, String status) throws SQLException {
		String sql = "select * from region where locateUserId=? and status=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, status);
		List<Region> result = new ArrayList();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Region region = new Region();
			region.setId(rs.getString("id"));
			region.setRegionName(rs.getString("regionName"));
			region.setCreateDate(rs.getTimestamp("createDate"));
			region.setFinishDate(rs.getTimestamp("finishDate"));
			result.add(region);
		}
		DBUtils.closeResource(connection, pstmt, rs);
		return result;
	}

	@Override
	public List<Region> regionQueryByStatusAndModelUserId(String userId, String status) throws SQLException {
		String sql = "select * from region where modelUserId=? and status=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, status);
		List<Region> result = new ArrayList();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Region region = new Region();
			region.setId(rs.getString("id"));
			region.setRegionName(rs.getString("regionName"));
			region.setCreateDate(rs.getTimestamp("createDate"));
			region.setFinishDate(rs.getTimestamp("finishDate"));
			result.add(region);
		}
		DBUtils.closeResource(connection, pstmt, rs);
		return result;
	}

	@Override
	public List<Region> regionQueryByStatusAndOutputUserId(String userId, String status) throws SQLException {
		String sql = "select * from region where outputUserId=? and status=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, status);
		List<Region> result = new ArrayList();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Region region = new Region();
			region.setId(rs.getString("id"));
			region.setRegionName(rs.getString("regionName"));
			region.setCreateDate(rs.getTimestamp("createDate"));
			region.setFinishDate(rs.getTimestamp("finishDate"));
			result.add(region);
		}
		DBUtils.closeResource(connection, pstmt, rs);
		return result;
	}

	@Override
	public List<Region> regionQueryByStatusAndFinishUserId(String userId, String status) throws SQLException {
		String sql = "select * from region where finishUserId=? and status=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, status);
		List<Region> result = new ArrayList();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Region region = new Region();
			region.setId(rs.getString("id"));
			region.setRegionName(rs.getString("regionName"));
			region.setCreateDate(rs.getTimestamp("createDate"));
			region.setFinishDate(rs.getTimestamp("finishDate"));
			result.add(region);
		}
		DBUtils.closeResource(connection, pstmt, rs);
		return result;
	}

	@Override
	public List<Region> regionQueryByNotRetreat(String status, String step, Pagination p) throws SQLException {
		// 查询出status=1并且drawRetreat未空的region
		String sql = "select * from region where status=? and (" + step + "=? or " + step + " IS NULL) limit ?,?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, status);
		pstmt.setString(2, "");
		pstmt.setInt(3, p.getCurrentPage());
		pstmt.setInt(4, p.getRows());
		List<Region> result = new ArrayList();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Region region = new Region();
			region.setId(rs.getString("id"));
			region.setRegionName(rs.getString("regionName"));
			region.setCreateDate(rs.getTimestamp("createDate"));
			result.add(region);
		}
		DBUtils.closeResource(connection, pstmt, rs);
		return result;
	}

	@Override
	public int regionCountByNotRetreat(String status, String step) throws SQLException {
		// 查询出status=1并且drawRetreat未空的region
		String sql = "select count(*) from region where status=? and (" + step + "=? or " + step + " IS NULL)";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, status);
		pstmt.setString(2, "");
		List<Region> result = new ArrayList();
		ResultSet rs = pstmt.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("count(*)");
		}

		DBUtils.closeResource(connection, pstmt, rs);
		return count;
	}

	@Override
	public List<Region> regionQueryByMySelf(String userId, String drawRetreat, Pagination pagination, String status)
			throws SQLException {
		// 查询出status=1且drawRetreat=userId的记录
		String sql = "select * from region where status=? and " + drawRetreat + "=? limit ?,?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, status);
		pstmt.setString(2, userId);
		pstmt.setInt(3, pagination.getCurrentPage());
		pstmt.setInt(4, pagination.getRows());
		List<Region> result = new ArrayList();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Region region = new Region();
			region.setId(rs.getString("id"));
			region.setRegionName(rs.getString("regionName"));
			region.setCreateDate(rs.getTimestamp("createDate"));
			result.add(region);
		}
		DBUtils.closeResource(connection, pstmt, rs);
		return result;
	}

	@Override
	public int regionQueryByMySelf(String userId, String drawRetreat, String status) throws SQLException {
		String sql = "select count(*) from region where status=? and " + drawRetreat + "=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, status);
		pstmt.setString(2, userId);
		List<Region> result = new ArrayList();
		ResultSet rs = pstmt.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("count(*)");
		}

		DBUtils.closeResource(connection, pstmt, rs);
		return count;
	}

	@Override
	public int retreat(String regionId, String step) throws SQLException {
		// 修改step为null
		String sql = "update region set " + step + "=" + null + " where id=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, regionId);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public Region regionQueryById(String regionId) throws SQLException {
		String sql = "select * from region where id=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, regionId);
		ResultSet rs = pstmt.executeQuery();
		Region region = new Region();
		if (rs.next()) {
			region.setId(rs.getString("id"));
			region.setRegionName(rs.getString("regionName"));
			region.setCreateDate(rs.getTimestamp("createDate"));
			region.setFinishDate(rs.getTimestamp("finishDate"));
			region.setCreateUser(new User(rs.getString("createUserId")));
			region.setDrawUser(new User(rs.getString("drawUserId")));
			region.setModelUser(new User(rs.getString("modelUserId")));
			region.setOutputUser(new User(rs.getString("outputUserId")));
			region.setFinishUser(new User(rs.getString("finishUserId")));

			region.setDraw(new Draw(rs.getString("drawId")));
			region.setLocate(new Locate(rs.getString("locateId")));
			region.setModel(new Model(rs.getString("modelId")));
			region.setReport(new Report(rs.getString("reportId")));
			region.setStatus(rs.getString("status"));

			region.setDrawRetreat(new User(rs.getString("drawRetreat")));
			region.setModelRetreat(new User(rs.getString("modelRetreat")));
			region.setOutputRetreat(new User(rs.getString("outputRetreat")));
			region.setMarketExcel(rs.getString("marketExcel"));

		}
		DBUtils.closeResource(connection, pstmt, rs);

		return region;
	}

	@Override
	public int regionDeleteById(String regionId) throws SQLException {
		String sql = "delete from region where id=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, regionId);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public int rob(String userId, String regionId, String step) throws SQLException {
		String sql = "update region set " + step + "=? where id = ?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, regionId);

		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public int updateRegionStatusAndModelRob(String regionId) throws SQLException {
		String sql = "update region set status=?,modelRetreat=?,modelId=?,outputRetreat=? where id = ?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "2");
		pstmt.setString(2, null);
		pstmt.setString(3, null);
		pstmt.setString(4, null);
		pstmt.setString(5, regionId);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public List<Region> regionQueryByStatus(String status, Pagination p) throws SQLException {
		// 查询出status=1并且drawRetreat未空的region
		String sql = "select * from region where status=? limit ?,?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, status);
		pstmt.setInt(2, p.getCurrentPage());
		pstmt.setInt(3, p.getRows());
		List<Region> result = new ArrayList();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Region region = new Region();
			region.setId(rs.getString("id"));
			region.setRegionName(rs.getString("regionName"));
			region.setCreateDate(rs.getTimestamp("createDate"));
			result.add(region);
		}
		DBUtils.closeResource(connection, pstmt, rs);
		return result;
	}

	@Override
	public int regionCountByStatus(String status) throws SQLException {
		// 查询出status=1并且drawRetreat未空的region
		String sql = "select count(*) from region where status=?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, status);
		List<Region> result = new ArrayList();
		ResultSet rs = pstmt.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("count(*)");
		}

		DBUtils.closeResource(connection, pstmt, rs);
		return count;
	}

	@Override
	public int regionUpdate(String regionId, Date date, String status) throws SQLException {
		String sql = "update region set status=?,finishDate=? where id = ?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, status);
		pstmt.setTimestamp(2, new Timestamp(date.getTime()));
		pstmt.setString(3, regionId);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public int updateRegionStatusAndModelRob(String regionId,String status, String id) throws SQLException {
		String sql = "update region set status=?,outputRetreat=?,reportId=? where id = ?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "3");
		pstmt.setString(2, null);
		pstmt.setString(3, null);
		pstmt.setString(4, regionId);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public int updateRegionByDrawUpload(Draw draw, Locate locate, String status, String regionId) throws SQLException {
		String sql = "update region set drawId=?,locateId=?,status=? where id = ?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, draw.getId());
		pstmt.setString(2, locate.getId());
		pstmt.setString(3, status);
		pstmt.setString(4, regionId);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

	@Override
	public int updateRegionByModelReject(String regionId) throws SQLException {
		String sql = "update region set drawId=?,locateId=?,drawRetreat=?,modelRetreat=?,status=? where id = ?";
		Connection connection = DBUtils.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, null);
		pstmt.setString(2, null);
		pstmt.setString(3, null);
		pstmt.setString(4, null);
		pstmt.setString(5, "1");
		pstmt.setString(6, regionId);
		int result = pstmt.executeUpdate();
		DBUtils.closeResource(connection, pstmt, null);
		return result;
	}

}
