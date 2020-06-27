package com.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.db.AbsenceRepository;
import com.domain.Absence;
import com.domain.AbsenceType;
import com.domain.Manager;
import com.domain.Member;
import com.web.PaginationSupport;

/**
 * 请假资源库接口的jdbc实现类
 * 
 * @author liuShuai
 * @version v1.0
 */
// 注解类作为DAO对象（数据访问对象，Data Access Objects），这些类可以直接对数据库进行操作
// 有这些分层操作的话，代码之间就实现了松耦合，代码之间的调用也清晰明朗，便于项目的管理；
@Repository
public class JdbcAbsenceRepository implements AbsenceRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcAbsenceRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public long count() {
		return jdbc.queryForLong("select count(id) from Absence");
	}

	@Override
	public long count(long id) {
		String query = "select count(id) from Absence where member_id = " + id;
		return jdbc.queryForLong(query);
	}

	@Override
	public Absence save(Absence absence, long id) {
		System.out.println(absence.getDecription());
		jdbc.update(INSERT_ABSENCE, id, absence.getAbsenceType().getId(), absence.getDecription(),
				absence.getAbsenceTime(), false);
		return absence;
	}

	/**
	 * 批准请假申请
	 */
	@Override
	public void allowAbsence(String id, String manageId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		jdbc.update("update Absence set isChecked='1',checker_id='" + manageId + "',check_time='"
				+ sdf.format(new Date()) + "' where id=?", id);
		Absence absence = jdbc.queryForObject(SELECT_ABSENCE + " and a.id=?", new AbsenceRowMapper(), id);
		Date date = absence.getCheck_time();
		System.out.println(date);
	}

	/**
	 * 拒绝请假申请
	 */
	@Override
	public void refuseAbsence(String id, String manageId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		jdbc.update("update Absence set checker_id='" + manageId + "',check_time='" + sdf.format(new Date())
				+ "' where id=?", id);
	}

	@Override
	public boolean isAbsence(Long memberID, Date date) { // v1.0 by ls
		Absence absence = null;
		try {
			absence = jdbc.queryForObject(
					SELECT_ABSENCE_UNCHECKED + " and a.member_id=? and to_days(a.date) = to_days(?)",
					new AbsenceRowMapper(), memberID, date);
		} catch (DataAccessException e) {
		}
		if (absence != null)
			return true;
		else {
			try {
				absence = jdbc.queryForObject(
						SELECT_ABSENCE_CHECKED
								+ " and a.member_id=? and to_days(a.date) = to_days(?) and a.isChecked = 1",
						new AbsenceRowMapper(), memberID, date);
			} catch (DataAccessException e) {
			}
		}
		if (absence != null)
			return true;
		else
			return false;
	}

	@Override
	public Absence findOne(Long memberID, Date date) { // v1.0 by ls
		Absence absence = null;
		try {
			absence = jdbc.queryForObject(
					SELECT_ABSENCE_UNCHECKED + " and a.member_id=? and to_days(a.date) = to_days(?)",
					new AbsenceRowMapper(), memberID, date);
		} catch (DataAccessException e) {
		}
		if (absence != null)
			return absence;
		else {
			try {
				absence = jdbc.queryForObject(
						SELECT_ABSENCE_CHECKED + " and a.member_id=? and to_days(a.date) = to_days(?)",
						new AbsenceRowMapper(), memberID, date);
			} catch (DataAccessException e) {
			}
		}
		if (absence != null)
			return absence;
		else
			return null;
	}

	@Override
	public PaginationSupport<Absence> findPage(int pageNo, int pageSize, long id) { // v1.1
																					// by
																					// ls
		int totalCount = (int) count(id);
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Absence>(new ArrayList<Absence>(0), 0, pageSize, startIndex);

		List<Absence> items = jdbc.query(SELECT_PAGE_ABSENCE, new AbsenceRowMapper(), id, pageSize, startIndex);
		items.toString();
		System.out.println("test");
		PaginationSupport<Absence> ps = new PaginationSupport<Absence>(items, totalCount, pageSize, startIndex);
		return ps;
	}

	@Override
	public PaginationSupport<Absence> findAllUncheck(int pageNo, int pageSize) { // v1.1
																					// by
																					// ls
		int totalCount = (int) jdbc
				.queryForLong("select count(id) from Absence where check_time = '0000-00-00 00:00:00'");
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Absence>(new ArrayList<Absence>(0), 0, pageSize, startIndex);
		List<Absence> items = jdbc.query(SELECT_ABSENCE_UNCHECKED + "order by a.date desc limit ? offset  ?",
				new AbsenceRowMapper(), pageSize, startIndex);
		items.toString();
		System.out.println("test");
		PaginationSupport<Absence> ps = new PaginationSupport<Absence>(items, totalCount, pageSize, startIndex);
		return ps;
	}

	@Override
	public PaginationSupport<Absence> findAllCheck(int pageNo, int pageSize) {

		int totalCount = (int) jdbc
				.queryForLong("select count(id) from Absence where check_time != '0000-00-00 00:00:00'");
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Absence>(new ArrayList<Absence>(0), 0, pageSize, startIndex);
		List<Absence> items = jdbc.query(SELECT_ABSENCE_CHECKED + "order by a.date desc limit ? offset  ?",
				new AbsenceRowMapper(), pageSize, startIndex);
		items.toString();
		System.out.println("test");
		PaginationSupport<Absence> ps = new PaginationSupport<Absence>(items, totalCount, pageSize, startIndex);
		return ps;
	}

	@Override
	public List<Date> findAbledate() { // v1.1 by ls

		List<Date> ableDate = new ArrayList<Date>();
		for (int i = 0; i < 31; i++) {
			Date dat = null;
			Calendar cd = Calendar.getInstance();
			cd.add(Calendar.DATE, i);
			dat = cd.getTime();
			DateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			try {
				date = dformat.parse(dformat.format(dat));
				ableDate.add(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return ableDate;
	}
	
	private static class AbsenceRowMapper implements RowMapper<Absence> {
		public Absence mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member m = new Member(rs.getLong("m.id"), rs.getString("m.username"), null,"0", null, (String) null);
			AbsenceType at = new AbsenceType(rs.getLong("at.id"), rs.getString("at.type"), rs.getInt("at.delect"));
			if (rs.getDate("a.check_time") != null) {
				Manager man = new Manager(rs.getLong("man.id"), rs.getString("man.username"), null);
				return new Absence(rs.getLong("a.id"), m, at, rs.getString("a.decription"), rs.getDate("a.date"),
						rs.getBoolean("a.isChecked"), man, rs.getDate("a.check_time"));
			}
			return new Absence(rs.getLong("a.id"), m, at, rs.getString("a.decription"), rs.getDate("a.date"), false,
					null, null);
		}
	}
	
	private static final String INSERT_ABSENCE = "insert into Absence (member_id, type_id, decription, date, isChecked,check_time) values (?, ?, ?, ? ,?, '0000-00-00 00:00:00')";
	private static final String SELECT_ABSENCE = "select m.id, m.username,a.id, a.member_id, a.type_id, a.decription, a.date, a.isChecked, a.checker_id, a.check_time, at.id, man.id, man.username, at.type, at.delect from Absence a LEFT JOIN Manager man ON a.checker_id = man.id, Absencetype at, Member m where at.id = a.type_id and m.id = a.member_id ";
	private static final String SELECT_ABSENCE_UNCHECKED = SELECT_ABSENCE + "and a.check_time = '0000-00-00 00:00:00' ";
	private static final String SELECT_ABSENCE_CHECKED = "select m.id, m.username,a.id, a.member_id, a.type_id, a.decription, a.date, a.isChecked, a.checker_id, a.check_time, at.id, man.id, man.username, at.type, at.delect from Absence a, Absencetype at, Member m, Manager man where at.id = a.type_id and m.id = a.member_id and a.checker_id = man.id ";
	private static final String SELECT_PAGE_ABSENCE = SELECT_ABSENCE
			+ "and a.member_id=? order by a.date desc limit ? offset  ?";

}
