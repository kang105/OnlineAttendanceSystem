package com.db;

import com.domain.Manager;


/**
 * 管理员内容资源库接口
 * 
 * @author qiuli
 * @version v1.0
 */

public interface ManagerRepository {

	/**
	 * 新建一个管理员
	 * 
	 * @param manamger
	 *            管理员
	 * @return 管理员
	 */
	Manager save(Manager manager);

	/**
	 * 查找指定的管理员
	 * 
	 * @param id
	 *            管理员ID
	 */
	Manager findByUsername(String userName, String password);

	/**
	 * 检查管理员是否重名
	 * 
	 * @param id
	 *            管理员ID
	 */
	Manager checkUsername(Manager manager);

	/**
	 * 查找管理员
	 * 
	 * @param id
	 *            管理员ID
	 */
	Manager findByUserName(String username);

	/**
	 * 取得指定管理员
	 * 
	 * @param id
	 * @return
	 */
	Manager findOne(long id);
}
