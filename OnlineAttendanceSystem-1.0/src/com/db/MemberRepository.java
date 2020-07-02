package com.db;

import java.util.List;

import com.domain.Member;
import com.web.PaginationSupport;

/**
 * 员工内容资源库接口
 * 
 * @author liuShuai
 * @version v1.1
 */

public interface MemberRepository {
	/**
	 * 检查员工用户名
	 * 
	 * @param member
	 * @param memberID
	 * 
	 */
	Member checkUsername(Member member);

	/**
	 * 根据用户名查找员工
	 * 
	 * @param member
	 * @param memberID
	 * 
	 */
	Member findByUsername(String userName, String password);

	/**
	 * 根据员工用户名查找
	 * 
	 * @param member
	 * @param memberID
	 * 
	 */
	Member findByUserName(String userName);


	/**
	 * 取得指定的员工
	 * 
	 * @param count
	 *            员工
	 * @return 员工
	 */
	Member findOne(long id);

	/**
	 * 新建一个员工
	 * 
	 * @param manamger
	 *            员工
	 * @return 员工
	 */
	Member save(Member member);

	/**
	 * 删除指定ID的员工
	 * 
	 * @param id
	 *            员工ID
	 */
	void delete(long id);

	/**
	 * 更新员工
	 * 
	 * @param member
	 * @param memberID
	 * 
	 */
	Member update(Member member, long memberID); // v1.1 by ls

	/**
	 * 取得指定的员工
	 * 
	 * @param userName
	 * @return 员工
	 */
	Member findOneByUserName(String userName);// by lhw

	/**
	 * 
	 * @param id
	 * @param userName
	 * @return 员工
	 */
	Member findOneByUserNameAndId(long id, String userName);// by lhw

	/**
	 * 找到一页
	 * 
	 * @param pageNo
	 * @param pageSize
	 */
	PaginationSupport<Member> findPage(int pageNo, int pageSize);// by lhw
	
	/**
	 * 取得全部的员工
	 * 
	 * @param userName
	 * @return 员工
	 */
	List<Member> findAll();

	/**
	 * 更改员工部门
	 * 
	 */
	void setD(long userId, long newDId);
}
