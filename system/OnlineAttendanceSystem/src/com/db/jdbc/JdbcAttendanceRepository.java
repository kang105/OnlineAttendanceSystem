package com.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.db.AttendanceRepository;
import com.domain.Attendance;
import com.domain.Member;
import com.web.PaginationSupport;

/**
 * 考勤资源库接口的jdbc实现类
 * 
 * @author LiuShuai
 * @version v1.2
 */
// 注解类作为DAO对象（数据访问对象，Data Access Objects），这些类可以直接对数据库进行操作
// 有这些分层操作的话，代码之间就实现了松耦合，代码之间的调用也清晰明朗，便于项目的管理；
@Repository
public class JdbcAttendanceRepository implements AttendanceRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcAttendanceRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Attendance save(Attendance attendance) { // v1.1 by ls
		jdbc.update(INSERT_ATTENDANCE, attendance.getMember().getId(), attendance.getAttendDate());
		return attendance;
	}

	@Override
	public long count() {
		return jdbc.queryForLong("select count(id) from Attendance");
	}

	@Override
	public long count(long id) {
		String query = "select count(id) from Attendance where member_id = " + id;
		return jdbc.queryForLong(query);
	}

	@Override
	public List<Attendance> findAll(long id) {
		List<Attendance> attendance = null;
		try {
			attendance = jdbc.query(SELECT_ATTENDANCE + " and a.member_id=?", new AttendanceRowMapper(), id);
		} catch (DataAccessException e) {
		}
		return attendance;
	}

	@Override
	public boolean isAttend(Long id, Date date) { // v1.1 by ls
		Attendance attendance = null;
		try {
			attendance = jdbc.queryForObject(
					SELECT_ATTENDANCE + " and a.member_id=? and to_days(a.date) = to_days(?) order by a.date ",
					new AttendanceRowMapper(), id, date);
		} catch (DataAccessException e) {
		}
		if (attendance != null)
			return true;
		else
			return false;
	}

	@Override
	public List<Attendance> findByMonth(Long id, Date date) { // v1.2 by ls
		List<Attendance> attendance = null;
		try {
			attendance = jdbc.query(
					SELECT_ATTENDANCE
							+ " and a.member_id=? and DATE_FORMAT( a.date,'%Y%m') = DATE_FORMAT(?,'%Y%m') order by a.date ",
					new AttendanceRowMapper(), id, date);
		} catch (DataAccessException e) {
		}
		return attendance;
	}

	@Override
	public PaginationSupport<Attendance> findPage(int pageNo, int pageSize, long id) { // v1.1
																						// by
																						// ls
		int totalCount = (int) count(id);
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Attendance>(new ArrayList<Attendance>(0), 0);

		List<Attendance> items = jdbc.query(SELECT_PAGE_ATTENDANCE, new AttendanceRowMapper(), id, pageSize,
				startIndex);
		PaginationSupport<Attendance> ps = new PaginationSupport<Attendance>(items, totalCount, pageSize, startIndex);
		return ps;
	}
	private static class AttendanceRowMapper implements RowMapper<Attendance> {
		public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member m = new Member(rs.getLong("m.id"), rs.getString("m.username"), null, "0", null, (String)null);
			return new Attendance(rs.getLong("a.id"), m, rs.getDate("a.date"));
		}
	}
	
	@Override
	public PaginationSupport<Attendance> findPage(int pageNo, int pageSize, Date bdate, Date edate) { // v1.1
																										// by kang
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String begin = sf.format(bdate) + " 00:00:00";
		String end = sf.format(edate) + " 23:59:59";
		List<Attendance> attendance = jdbc.query(SELECT_ATTENDANCE + " and date > ? and date < ?", new AttendanceRowMapper(), begin,
				end);
		int totalCount = attendance.size();
		System.out.println(totalCount);
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Attendance>(new ArrayList<Attendance>(0), pageSize);
		List<Attendance> items = jdbc.query(SELECT_ATTENDANCE + " and date > ? and date < ? order by a.date desc limit ? offset  ?", 
				new AttendanceRowMapper(), begin,end,pageSize,startIndex);

		PaginationSupport<Attendance> ps = new PaginationSupport<Attendance>(items, totalCount, pageSize, startIndex);
		System.out.println(items.size());
		return ps;
	}

	private static final String SELECT_ATTENDANCE = "select a.id, a.member_id, a.date, m.id, m.username from Attendance a, Member m where m.id = a.member_id ";
	private static final String INSERT_ATTENDANCE = "insert into Attendance (member_id, date) values (?, ?)";
	private static final String SELECT_PAGE_ATTENDANCE = SELECT_ATTENDANCE
			+ "and a.member_id=? order by a.date desc limit ? offset  ?";

}
