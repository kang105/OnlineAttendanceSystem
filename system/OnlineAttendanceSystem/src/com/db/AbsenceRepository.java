package com.db;

import java.util.Date;
import java.util.List;

import com.domain.Absence;
import com.web.PaginationSupport;

/**
 * 请假内容资源库接口
 * 
 * @author liuShuai fengjingtian
 * @version v1.1
 */
public interface AbsenceRepository {

	/**
	 * 批准请假申请
	 * 
	 * @param id
	 * @param manageId
	 */
	void allowAbsence(String id, String manageId);

	/**
	 * 拒绝请假申请
	 * 
	 * @param id
	 * @param valueOf
	 */
	void refuseAbsence(String id, String manageId);

	/**
	 * 取得员工可以请假的日期
	 * 
	 * 
	 * @return List<Date>
	 */
	List<Date> findAbledate(); // v1.1

	/**
	 * 取得请假数量
	 * 
	 * @return 请假数量
	 */
	long count();

	/**
	 * 取得请假数量
	 * 
	 * @param id
	 *            用户id
	 * @return 请假数量
	 */
	long count(long id);

	/**
	 * 新建一个请假
	 * 
	 * @param absence
	 *            请假
	 * @param id
	 *            用户id
	 * @return 以保存的请假
	 */
	Absence save(Absence absence, long id);

	/**
	 * 取得员工今天是否请假 有审查并通过的请假返回true 有尚未审查的请假返回true 有审查并未通过的请假返回false 未找到请假返回false
	 * 
	 * 
	 * @return boolean
	 */
	boolean isAbsence(Long memberID, Date date);

	/**
	 * 取得员工今天的请假
	 * 
	 * 
	 * @return Absence
	 */
	Absence findOne(Long memberID, Date date);

	/**
	 * 取得请假分页
	 * 
	 * @param page
	 * @param pageSize
	 * @param id
	 *            用户id
	 * @return PaginationSupport
	 */
	PaginationSupport<Absence> findPage(int pageNo, int pageSize, long id); // v1.1

	/**
	 * 查找请假所有未审核的请假申请
	 * 
	 * @param page
	 * @param pageSize
	 * @param id
	 *            请假ID
	 * @return 请假
	 */
	PaginationSupport<Absence> findAllUncheck(int pageNo, int pageSize);

	/**
	 * 查找请假所有已审核的请假申请
	 * 
	 * @param page
	 * @param pageSize
	 *            请假ID
	 * @return 请假
	 */
	PaginationSupport<Absence> findAllCheck(int pageNo, int pageSize);

}
