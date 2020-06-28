package com.web;

import com.domain.Department;

/**
 * 部门操作的辅助表单
 * @author leihaowen 
 *
 */
public class DForm {

	private String name;
	private long department;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the upperName
	 */
	public long getDepartment() {
		return department;
	}

	/**
	 * @param upperName
	 *            the upperName to set
	 */
	public void setDepartment(long upperId) {
		this.department = upperId;
	}

	/**
	 * 将表单转成Department
	 * @return
	 */
	public Department toDeperment() {
		return new Department(name, department);
	}

}
