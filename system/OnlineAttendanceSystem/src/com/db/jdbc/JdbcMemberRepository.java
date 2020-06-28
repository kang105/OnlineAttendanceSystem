package com.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.db.AbsenceTypeRepository;
import com.db.MemberRepository;
import com.domain.Absence;
import com.domain.AbsenceType;
import com.domain.Department;
import com.domain.Member;
import com.web.PaginationSupport;

/**
 * 员工库接口的jdbc实现类
 * 
 * @author LiuShuai
 * @version v1.0
 */
// 注解类作为DAO对象（数据访问对象，Data Access Objects），这些类可以直接对数据库进行操作
// 有这些分层操作的话，代码之间就实现了松耦合，代码之间的调用也清晰明朗，便于项目的管理；
@Repository
public class JdbcMemberRepository implements MemberRepository {

	private static final String SELECT_PAGE_MEMBER = "select m.id, m.username, m.phone, m.email, m.sex, m.department_id  , d.department from member m, department d where m.department_id = d.id and m.delect = 0 order by m.id limit ? offset ?";
	// by lhw
	private JdbcTemplate jdbc;

	@Autowired
	public JdbcMemberRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Member checkUsername(Member member) {
		Member m = null;
		try {
			m = jdbc.queryForObject(SELECT_MEMBER + " and m.username = ? ", new MemberRowMapper(),
					member.getUserName());
		} catch (DataAccessException e) {
		}
		return m;
	}

	@Override
	public Member findByUsername(String userName, String password) {
		Member member = null;
		if (userName.equals("") || password.equals("")) {
			return member;
		}
		try {
			member = jdbc.queryForObject(SELECT_MEMBER_BY_USERNAME, new MemberRowMapper(), userName, password);
		} catch (DataAccessException e) {
		}
		return member;
	}

	@Override
	public Member findByUserName(String userName) {
		Member member = null;
		try {
			member = jdbc.queryForObject(SELECT_MEMBER + " and m.username = ? ", new MemberRowMapper(), userName);
		} catch (DataAccessException e) {
		}
		return member;
	}

	@Override
	public Member findOneByUserName(String userName) {// lhw
		// Member member = new Member();
		Member member = null;
		try {
			member = jdbc.queryForObject(SELECT_MEMBER_BY_USERNAME_1, new MemberRowMapper(), "\"" + userName + "\"");
		} catch (DataAccessException e) {
		}
		System.out.println("\"" + userName + "\"");
		System.out.println(SELECT_MEMBER_BY_USERNAME_1 + "\"" + userName + "\"");
		System.out.println(member == null);
		return member;
	}

	@Override
	public Member findOneByUserNameAndId(long id, String userName) {// lhw
		Member member = null;
		try {
			member = jdbc.queryForObject(SELECT_MEMBER + " and m.username = ? ", new MemberRowMapper(), userName);
		} catch (DataAccessException e) {
		}
		if (member != null && member.getId() != id) {
			member = null;
		}
		return member;
	}

	private long insertMemberAndReturnId(Member member) {// by lhw
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbc).withTableName("member");
		jdbcInsert.setGeneratedKeyName("id");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("username", member.getUserName());
		args.put("password", member.getPassword());
		args.put("phone", member.getPhone());
		args.put("email", member.getEmail());
		args.put("sex", member.getSex());
		args.put("department_id", member.getDepartment().getId());
		args.put("delect", false);
		long member_id = jdbcInsert.executeAndReturnKey(args).longValue();

		return member_id;
	}

	@Override
	public Member findOne(long id) { // v1.0
		Member member = null;
		try {
			System.out.println(SELECT_MEMBER_BY_ID + id);
			member = jdbc.queryForObject(SELECT_MEMBER_BY_ID, new MemberRowMapper(), id);
			System.out.println(SELECT_MEMBER_BY_ID + id);
		} catch (DataAccessException e) {
		}
		return member;
	}

	@Override
	public Member save(Member m) {
		Department d = null;
		d = m.getDepartment();
		long i = 1;
		if (d != null) {
			i = d.getId();
		}
		jdbc.update(INSERT_MEMBER, m.getUserName(), m.getPassword(), m.getPhone(), m.getEmail(), m.getSex(), i,
				m.getDelect());
		return m;
	}

	@Override
	public void delete(long id) {
		jdbc.update("update member set delect = '1' where id = " + id);
	}

	@Override
	public Member update(Member member, long id) {
		jdbc.update(UPDATE_MEMBER + " where id=?", member.getUserName(), member.getPassword(), member.getPhone(),
				member.getEmail(), member.getSex(), member.getDepartment().getId(), id);
		member.setId(id);
		member = this.findOne(id);
		return member;
	}

	/**
	 * TODO to do 修改部门
	 * 
	 * @return
	 */

	@Override
	public PaginationSupport<Member> findPage(int pageNo, int pageSize) throws NullPointerException {// by
																										// lhw
		int totalCount = (int) jdbc.queryForLong("select count(id) from member where delect=0");
		;
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Member>(new ArrayList<Member>(0), 0);
		List<Member> items = jdbc.query(SELECT_PAGE_MEMBER, new MemberRowMapper2(), pageSize, startIndex);
		PaginationSupport<Member> pm = new PaginationSupport<Member>(items, totalCount, pageSize, startIndex);
		return pm;
	}

	private static class MemberRowMapper implements RowMapper<Member> {
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			Department department = new Department(rs.getLong("departmentId"), rs.getString("d.department"),
					(Department) null);
			return new Member(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getString("phone"),
					rs.getString("email"), rs.getString("sex"), department, rs.getInt("delect"));

		}
	}

	private static class MemberRowMapper2 implements RowMapper<Member> {
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException, NullPointerException {
			Department department = new Department((long) rs.getInt("department_id"), rs.getString("department"),
					(Department) null);
			return new Member(rs.getLong("id"), rs.getString("username"), rs.getString("phone"), rs.getString("email"),
					rs.getString("sex"), department);
		}
	}

	private static class MemberRowMapper1 implements RowMapper<Member> {// by
																		// lhw
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException, NullPointerException {
			Department department = new Department((long) rs.getInt("department_id"), rs.getString("department"),
					(Department) null);
			return new Member(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getString("phone"),
					rs.getString("email"), rs.getString("sex"), department);
		}
	}
	
	@Override
	public List<Member> findAll() {
		return jdbc.query("select id, department_id from Member", new MemberRowMapper3());

	}

	@Override
	public void setD(long userId, long newDId) {
		jdbc.update("update MEMBER set department_id = " + newDId + " where id = " + userId);

	}


	private static class MemberRowMapper3 implements RowMapper<Member> {
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException, NullPointerException {
			Department department = new Department((long) rs.getInt("department_id"), null, (Department) null);
			return new Member(rs.getLong("id"), department);
		}
	}
	
	private static final String SELECT_MEMBER = "select m.id, m.username, m.password, m.phone, m.email, m.sex, d.id as departmentId, d.department, d.upperDepartment_id, m.delect from Member m, Department d where m.department_id = d.id and m.delect=0";
	private static final String SELECT_MEMBER_BY_ID = SELECT_MEMBER + " and m.id = ? ";
	private static final String UPDATE_MEMBER = "update MEMBER SET userName = ?, password = ?, phone = ?, email = ?, sex = ?, department_id = ?";
	private static final String SELECT_MEMBER_BY_USERNAME = SELECT_MEMBER + " and m.username = ? and m.password = ?";
	private static final String INSERT_MEMBER = "insert into Member(username, password, phone, email, sex, department_id, delect) values (?, ?, ?, ?, ? ,? ,?)";
	// by lhw
	private static final String SELECT_MEMBER_BY_USERNAME_1 = SELECT_MEMBER + " and m.username = ?";
	
}
