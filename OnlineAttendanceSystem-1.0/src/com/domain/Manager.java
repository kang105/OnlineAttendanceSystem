package com.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 管理员对象类
 * 
 * @author qiuli
 * @version v1.0
 */

public class Manager {
	private long id;

	@NotNull
	@Size(min = 2, max = 25)
	private String username;

	@NotNull
	@Size(min = 4, max = 25)
	private String password;

	private int delect;

	public Manager() {
	}

	/**
	 * 构造方法1 无id的
	 */
	public Manager(String username, String password) {
		this.username = username;
		this.password = password;
		this.delect = 0;
	}

	/**
	 * 构造方法2 有id的
	 */
	public Manager(long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.delect = 0;
	}

	/**
	 * 构造方法2 有id的而且要删除参数的
	 */
	public Manager(long id, String username, String password, int delect) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.delect = delect;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
