package com.domain;

/**
 * 部门对象类
 * 
 * @author liuShuai
 * @version v1.1
 * 
 *          去除所有final标识 添加对应的set方法
 */
public class Department {
	private Long id;
	private String department;
	private Department upperDepartment;

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @param upperDepartment
	 *            the upperDepartment to set
	 */
	public void setUpperDepartment(Department upperDepartment) {
		this.upperDepartment = upperDepartment;
	}

	public Department() {
	}

	/**
	 * 构造方法
	 * 
	 */

	public Department(Long id, String department, String uppername) {
		this.id = id;
		this.department = department;
		this.upperDepartment = new Department(null, uppername, (Department) null);
	}

	/**
	 * 构造方法
	 * 
	 */
	public Department(Long id, String department, Department upperDepartment) {
		this.id = id;
		this.department = department;
		this.upperDepartment = upperDepartment;
	}

	public Department(Long id, String department, Long upperDepartmentId) {// by
																			// lhw
		this.id = id;
		this.department = department;
		this.upperDepartment = new Department(upperDepartmentId, null, (Department) null);
	}

	public Department(String name, long upperId) {
		this.department = name;
		this.upperDepartment = new Department(upperId, null, (Department) null);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @return the upperDepartment
	 */
	public Department getUpperDepartment() {
		return upperDepartment;
	}

}
