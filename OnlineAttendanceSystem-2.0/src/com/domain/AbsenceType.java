package com.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 请假类型对象类
 * 
 * @author qiuli
 * @version v1.0
 */
public class AbsenceType {

	private long id;

	@NotNull
	@Size(min = 2, max = 20)
	private String type;
	private int delect;

	public AbsenceType() {
	}

	/**
	 * 构造方法1 不用id参数的
	 * 
	 * @param type
	 *            请假类型
	 */
	public AbsenceType(String type, int delect) {
		this.type = type;
		this.delect = delect;
	}

	/**
	 * 构造方法2
	 * 
	 * @param type
	 *            请假类型
	 */
	public AbsenceType(long id, String type, int delect) {
		this.id = id;
		this.type = type;
		this.delect = delect;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the delect
	 */
	public int getDelect() {
		return delect;
	}

	/**
	 * @param delect
	 *            the delect to set
	 */
	public void setDelect(int delect) {
		this.delect = delect;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
