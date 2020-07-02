package com.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.db.DepartmentRepository;
import com.domain.Department;

/**
 * 请假类型资源库接口的jdbc实现类
 * 
 * @author liuShuai
 * @version v1.0
 */
@Repository
public class JdbcDepartmentRepository implements DepartmentRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcDepartmentRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Department findOne(String name) {
		Department d = null;
		try {
			d = jdbc.queryForObject(SELECT_DEPARTMENT_BY_NAME, new DepartmentRowMapper2(), name);
		} catch (DataAccessException e) {
		}
		return d;
	}

	@Override
	public Department changeUpperDepartment_By_Id(long id, long newUpperId) {
		jdbc.update(UPDATE_DEPARTMENT_UPPERDEPARTMENT, newUpperId, id);
		return null;
	}

	@Override
	public Department findOne_BY_Id(long id) {
		Department d = new Department();
		try {
			d = jdbc.queryForObject(SELECT_DEPARTMENT_BY_ID, new DepartmentRowMapper(), id);
		} catch (DataAccessException e) {
		}
		return d;
	}

	private long insertDepartmentAndReturnId(Department department) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbc).withTableName("department");
		jdbcInsert.setGeneratedKeyName("id");
		Map<String, Object> args = new HashMap<String, Object>();

		args.put("department", department.getDepartment());
		args.put("upperDepartment_id", department.getUpperDepartment().getId());
		long department_Id = jdbcInsert.executeAndReturnKey(args).longValue();

		return department_Id;
	}

	@Override
	public Department save(Department department) {
		long department_Id = insertDepartmentAndReturnId(department);

		return new Department(department_Id, department.getDepartment(), department.getUpperDepartment());

	}

	@Override
	public void delect(long id) {
		jdbc.update("delete from department where id = " + id);

	}

	@Override
	public Department change(Department department, long id) {
		jdbc.update(UPDATE_DEPARTMENT + " where id = ?", department.getDepartment(),
				department.getUpperDepartment().getId(), id);

		return department;
	}
	
	public Department changeName(Department department, long id) {
		jdbc.update(UPDATE_DEPARTMENT + " where id = ?", department.getDepartment(),
				department.getUpperDepartment().getId(), id);

		return department;
	}

	@Override
	public List<Department> findAll() { // v1.0 by ls
		return jdbc.query(SELECT_DEPARTMENT + " order by id", new DepartmentRowMapper());
	}

	//检查修改后的上级节点，是否原本为其下级节点（形成环状） by邱立
	@Override
	public boolean findAllLower(long id,long upper ) { 
		Department d=null;
		System.out.print(id+" "+upper);
		//循环遍历其修改的上级部门，判断其上级部门的上级是否有该节点
		while(upper!=1){
			d=jdbc.queryForObject(SELECT_DEPARTMENT+" where id = ?", new DepartmentRowMapper(),upper);
			if(upper==id){
				return false;
			}
			System.out.print(upper);
			upper=d.getUpperDepartment().getId();
		}
		return true;
	}
	//删除此节点以及所有子节点
	public void deletAllLower(long id) {
		delect(id);
		List<Department> all = findAll();
		for (Department d : all) {
			if(d.getUpperDepartment().getId() == id) deletAllLower(d.getId());
		}
	}
	
	@Override
	public void add(Department department) {
		jdbc.update(INSERT_DEPARTMENT, department.getDepartment(), department.getUpperDepartment().getId());
	}
	
	private static class DepartmentRowMapper implements RowMapper<Department> {
		public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Department(rs.getLong("id"), rs.getString("department"), rs.getLong("upperDepartment_id"));
		}
	}

	private static class DepartmentRowMapper2 implements RowMapper<Department> {
		public Department mapRow(ResultSet rs, int rowNum) throws SQLException {

			return new Department(rs.getLong("id"), rs.getString("department"), rs.getString("upperName"));

		}
	}

	private static final String SELECT_DEPARTMENT = "select id, department, upperDepartment_id from Department";
	private static final String SELECT_DEPARTMENT1 = "select d1.id, d1.department, d1.upperDepartment_id, d2.department upperName from Department as d1, Department as d2 ";
	private static final String SELECT_DEPARTMENT_BY_NAME = SELECT_DEPARTMENT1
			+ " where d1.department = ? and d1.upperDepartment_id = d2.id";
	private static final String UPDATE_DEPARTMENT = "update department set department = ? , upperDepartment_id = ? ";
	private static final String SELECT_DEPARTMENT_BY_ID = SELECT_DEPARTMENT1
			+ " where d1.id = ? and d1.upperDepartment_id = d2.id";
	private static final String UPDATE_DEPARTMENT_UPPERDEPARTMENT = "update department set upperDepartment_id = ? where id = ?";
	private static final String INSERT_DEPARTMENT = "insert into department(department, upperDepartment_id) values(? , ? ) ";

}
