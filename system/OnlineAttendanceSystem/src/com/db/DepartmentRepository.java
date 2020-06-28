package com.db;

import java.util.List;

import com.domain.Department;

/**
 * 部门内容资源库接口
 * 
 * @author liuShuai
 * @version v1.1
 */

public interface DepartmentRepository {

	/**
	 * 新建一个部门
	 * 
	 * @param spittle
	 *            部门
	 * @return 部门
	 */
	Department save(Department department);

	/**
	 * 取得部门
	 * 
	 * @return 部门
	 */
	List<Department> findAll();

	/**
	 * 通过部门id删除部门
	 * 
	 */
	void delect(long id);

	/**
	 * 通过部门名字找到特定的部门
	 * 
	 */
	Department findOne(String name);

	/**
	 * 通过部门id找到特定的部门
	 * 
	 */
	Department findOne_BY_Id(long id);

	/**
	 * 更改部门的上级部门
	 * 
	 */
	Department changeUpperDepartment_By_Id(long id, long newUpperId);

	/**
	 * 更改部门（名字）
	 * 
	 */
	Department change(Department department, long id);

	/**
	 * 添加新的部门
	 * 
	 */
	void add(Department deperment);
	
	/**
	 * 检查防止修改上级部门信息后形成环状  by邱立
	 * 
	 * @return boolean
	 */
	boolean findAllLower(long id, long upper );
	/**
	 * 删除此节点以及所有子节点
	 * 
	 */
	public void deletAllLower(long id) ;
	
}
