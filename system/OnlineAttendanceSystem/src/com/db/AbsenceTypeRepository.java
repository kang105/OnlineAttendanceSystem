package com.db;

import java.util.List;
import java.util.Map;

import com.domain.AbsenceType;
import com.web.PaginationSupport;

/**
 * 请假类型资源库接口
 * 
 * @author qiuli
 * @version v1.0
 */

public interface AbsenceTypeRepository {

	/**
	 * 取得请假类型数量
	 * 
	 * @return
	 */
	long count();

	/**
	 * 新建一个请假类型
	 * 
	 * @param spitter
	 *            新建的请假类型
	 * @return 请假类型
	 */
	AbsenceType save(AbsenceType absenceType);

	/**
	 * 删除一个请假类型
	 * 
	 */
	void delect(AbsenceType absenceType);

	/**
	 * 删除一个请假类型
	 * 
	 */
	void delect(String id);

	/**
	 * 修改一个请假类型
	 * 
	 * @param spitter
	 *            修改的请假类型
	 * @return 请假类型
	 */
	AbsenceType change(AbsenceType absenceType);

	/**
	 * 依据id查找请假类型
	 * 
	 * @param id
	 *            请假类型ID
	 * @return 请假类型
	 */
	AbsenceType findOne(String id);

	/**
	 * 取得指定id区间(start到end区间的id)的请假类型
	 * 
	 * @param count
	 *            请假类型
	 * @return 请假类型
	 */
	List<AbsenceType> findRecent(int start, int end);

	/**
	 * 取得全部请假类型
	 * 
	 * @return 全部请假类型
	 */
	List<AbsenceType> findAll();

	/**
	 * 取得全部请假类型
	 * 
	 * @return 全部请假类型
	 */
	PaginationSupport<AbsenceType> findAll(int pageNo, int pageSize);

	/**
	 * 通过type查找
	 * 
	 * @param type
	 * @return AbsenceType
	 */
	AbsenceType findByType(String type);

	/**
	 * 取得全部请假类型
	 * 
	 * @return 全部请假类型
	 */
	Map<Integer, String> getAbsenceType();

}
