package com.db;

import java.util.Date;
import java.util.List;

import com.web.PaginationSupport;
import com.domain.Attendance;

/**
 * 考勤内容资源库接口
 * 
 * @author liuShuai
 * @version v1.2
 */

public interface AttendanceRepository {

	/**
	 * 取得考勤数量
	 * 
	 * @return 考勤数量
	 */
	long count();

	/**
	 * 取得考勤数量
	 * 
	 * @param id
	 *            用户id
	 * @return 考勤数量
	 */
	long count(long id);

	/**
	 * 新建一个考勤
	 * 
	 * @param attendance
	 *            考勤
	 * @return 考勤
	 */
	Attendance save(Attendance attendance); // v1.1

	/**
	 * 取得员工今天是否打卡
	 * 
	 * @return boolean
	 */
	boolean isAttend(Long id, Date date); // v1.1

	/**
	 * 取得考勤分页
	 * 
	 * @param page
	 * @param pageSize
	 * @param id
	 *            用户id
	 * @return PaginationSupport
	 */
	PaginationSupport<Attendance> findPage(int pageNo, int pageSize, long id); // v1.1

	/**
	 * 取得指定月份的考勤
	 * 
	 * @return 考勤
	 */
	List<Attendance> findByMonth(Long id, Date date); // v1.2

	/**
	 * 取得查找日期范围考勤分页
	 *
	 * @return PaginationSupport
	 */
	PaginationSupport<Attendance> findPage(int pageNo, int pageSize, Date bdate, Date edate);

	/**
	 * 取得某一员工的所有考勤记录
	 * 
	 * @return 考勤
	 */
	List<Attendance> findAll(long id);

}
