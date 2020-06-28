package com.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.db.AbsenceTypeRepository;
import com.domain.AbsenceType;
import com.web.PaginationSupport;

/**
 * 请假类型资源库接口的jdbc实现类
 * 
 * @author qiuli
 * @version v1.0
 */
// 注解类作为DAO对象（数据访问对象，Data Access Objects），这些类可以直接对数据库进行操作
// 有这些分层操作的话，代码之间就实现了松耦合，代码之间的调用也清晰明朗，便于项目的管理；
@Repository
public class JdbcAbsenceTypeRepository implements AbsenceTypeRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcAbsenceTypeRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	// 请假类型个数
	@Override
	public long count() {
		return jdbc.queryForLong("select count(id) from AbsenceType where delect=0");
	}

	// 增加请假类型
	@Override
	public AbsenceType save(AbsenceType absenceType) {
		jdbc.update(INSERT_ABSENCETYPE, absenceType.getType());
		return absenceType;
	}

	// 删除请假类型
	@Override
	public void delect(AbsenceType absenceType) {
		jdbc.update("delete from AbsenceType where id=?", absenceType.getId());
	}

	// 修改请假类型
	@Override
	public AbsenceType change(AbsenceType absenceType) {
		jdbc.update("update AbsenceType set type=? where id=?", absenceType.getType(), absenceType.getId());
		return absenceType;
	}

	// 找到特定区间的id的请假类型
	@Override
	public List<AbsenceType> findRecent(int start, int end) {
		return jdbc.query(SELECT_ABSENCETYPE + " and id>=? and id<=? order by id", new AbsenceTypeRowMapper(), start,
				end);
	}

	// 找到全部的请假类型
	@Override
	public List<AbsenceType> findAll() {
		return jdbc.query(SELECT_ABSENCETYPE + " order by id", new AbsenceTypeRowMapper());
	}

	// 找到全部的请假类型
	@Override
	public Map<Integer, String> getAbsenceType() {
		List<AbsenceType> types = findAll();
		/*
		 * for( int i = 0 ; i < types.size() ; i++) {
		 * types.get(i).getId(),types.get(i).getType() }
		 */
		return null;
	}

	// 删除请假类型
	@Override
	public void delect(String id) {
		jdbc.update("update AbsenceType set delect=1 where id=?", id);
	}

	// 找到全部的请假类型
	@Override
	public PaginationSupport<AbsenceType> findAll(int pageNo, int pageSize) {
		int totalCount = (int) count();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<AbsenceType>(new ArrayList<AbsenceType>(0), 0, pageSize, startIndex);
		List<AbsenceType> items = jdbc.query(SELECT_ABSENCETYPE + " order by id limit ? offset  ?",
				new AbsenceTypeRowMapper(), pageSize, startIndex);
		items.toString();
		System.out.println("test");
		PaginationSupport<AbsenceType> ps = new PaginationSupport<AbsenceType>(items, totalCount, pageSize, startIndex);
		return ps;

	}

	//根据type查找请假类型
	@Override
	public AbsenceType findByType(String type) {
		AbsenceType absenceType = null;
		try {
			absenceType = jdbc.queryForObject(SELECT_ABSENCETYPE + " and type=?", new AbsenceTypeRowMapper(), type);
		} catch (DataAccessException e) {
		}
		return absenceType;
	}

	// id查找请假类型
	@Override
	public AbsenceType findOne(String id) {
		AbsenceType absenceType = null;
		try {
			absenceType = jdbc.queryForObject(SELECT_ABSENCETYPE + " and id=?", new AbsenceTypeRowMapper(), id);
		} catch (DataAccessException e) {
		}
		return absenceType;
	}
	
	private static class AbsenceTypeRowMapper implements RowMapper<AbsenceType> {
		public AbsenceType mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new AbsenceType(rs.getLong("id"), rs.getString("type"), rs.getInt("delect"));
		}
	}
	
	private static final String INSERT_ABSENCETYPE = "insert into AbsenceType (type, delect) values (?,0)";
	private static final String SELECT_ABSENCETYPE = "select id, type, delect from AbsenceType where delect = 0";

}
