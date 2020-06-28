package com.web;

import com.domain.Department;

/**
 * 部门处理表单
 * @author leihaowen 
 *
 */
public class DepartmentForm {
	private final Long id;
	private final String department;
	private final Department upperDepartment;
	private int level;

	/**
	 * @param id
	 * @param department
	 * @param upperDepartment
	 * @param level
	 */
	public DepartmentForm(Long id, String department, Department upperDepartment, int level) {

		this.id = id;
		this.department = department;
		this.upperDepartment = upperDepartment;
		this.level = level;
	}

	public DepartmentForm(Department d) {
		this.id = d.getId();
		this.department = d.getDepartment();
		this.upperDepartment = d.getUpperDepartment();
		this.level = 0;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
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

	public void addLevel() {
		this.level++;
	}

}
