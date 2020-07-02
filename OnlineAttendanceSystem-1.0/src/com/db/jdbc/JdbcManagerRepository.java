package com.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.db.ManagerRepository;
import com.domain.Manager;

/**
 * 员工库接口的jdbc实现类
 * 
 * @author LiuShuai
 * @version v1.0
 */
// 注解类作为DAO对象（数据访问对象，Data Access Objects），这些类可以直接对数据库进行操作
// 有这些分层操作的话，代码之间就实现了松耦合，代码之间的调用也清晰明朗，便于项目的管理；
@Repository
public class JdbcManagerRepository implements ManagerRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcManagerRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Manager findByUsername(String userName, String password) {
		Manager manager = null;
		try {
			manager = jdbc.queryForObject(SELECT_MANAGER + " where username=? and password=? ", new ManagerRowMapper(),
					userName, password);
		} catch (DataAccessException e) {
		}
		return manager;
	}

	@Override
	public Manager save(Manager manager) {
		jdbc.update("insert into Manager(username, password, delect) values (?, ?, ?)", manager.getUsername(),
				manager.getPassword(), manager.getDelect());
		return manager;
	}

	@Override
	public Manager checkUsername(Manager manager) {
		Manager m = null;
		try {
			m = jdbc.queryForObject(SELECT_MANAGER + " where username=?", new ManagerRowMapper(),
					manager.getUsername());
		} catch (DataAccessException e) {
		}
		return m;
	}

	@Override
	public Manager findByUserName(String username) {
		Manager manager = null;
		manager = jdbc.queryForObject(SELECT_MANAGER + " where username=?", new ManagerRowMapper(), username);
		return manager;
	}

	@Override
	public Manager findOne(long id) { // v1.0
		Manager manager = null;
		try {
			System.out.println(SELECT_MANAGER_BY_ID + id);
			manager = jdbc.queryForObject(SELECT_MANAGER_BY_ID, new ManagerRowMapper(), id);
			System.out.println(SELECT_MANAGER_BY_ID + id);
		} catch (DataAccessException e) {
		}
		return manager;
	}

	private static class ManagerRowMapper implements RowMapper<Manager> {
		public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
			Manager manager = null;
			manager = new Manager(rs.getLong("id"), rs.getString("username"), rs.getString("password"),
					rs.getInt("delect"));
			return manager;
		}
	}
	
	private static final String SELECT_MANAGER = "select id, username, password, delect from Manager";
	private static final String SELECT_MANAGER_BY_ID = SELECT_MANAGER + " and m.id = ? ";

}
