package com.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * 请假对象类
 * 
 * @author liuShuai
 * @version v1.1
 */
public class Member {
	private Long id;

	@NotNull
	@Size(min = 2, max = 25)
	private String userName;

	@NotNull
	@Size(min = 3, max = 25)
	private String password;

	@Size(min = 4, max = 16)
	@NotNull
	private String phone; // v1.1 by ls

	@NotNull
	@Email
	private String email;

	@NotNull
	private String sex;

	private Department department;

	private int delect;

	public Member(long id, Department department) {
		this.id = id;
		this.department = department;
	}

	/**
	 * 构造方法4 有部门的 无需删除参数的，但又部门参数
	 * 
	 * @param userName
	 *            用户名（登录名）
	 * @param password
	 *            密码
	 * @param phone
	 *            电话
	 * @param email
	 *            邮箱
	 * @param department
	 *            部门
	 * @param sex
	 *            性别
	 */
	public Member(String userName, String password, String phone, String email, String sex, Department department) {

		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.sex = sex;
		this.department = department;
		this.delect = 0;
	}

	/**
	 * 构造方法5 有部门的 无删除参数且无部门参数
	 * 
	 * @param userName
	 *            用户名（登录名）
	 * @param password
	 *            密码
	 * @param phone
	 *            电话
	 * @param email
	 *            邮箱
	 * 
	 * @param sex
	 *            性别
	 */
	public Member(String userName, String password, String phone, String email, String sex) {
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.sex = sex;
		this.department = null;
		this.delect = 0;
	}

	public Member(String userName, String password, String phone, String email, String sex, long department) {
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.sex = sex;
		this.department = new Department(department, null, (Department) null);
		this.delect = 0;
	}

	public Member(long id, String username, String phone, String email, String sex, Department department) {
		this.id = id;
		this.userName = username;
		this.phone = phone;
		this.email = email;
		this.sex = sex;
		this.department = department;
		this.delect = 0;
	}

	public Member() {
		this.department = null;
	}

	/**
	 * 构造方法1 无部门的 无需删除参数和部门参数的
	 * 
	 * @param userName
	 *            用户名（登录名）
	 * @param password
	 *            密码
	 * @param phone
	 *            电话
	 * @param email
	 *            邮箱
	 * @param department
	 *            部门
	 * @param sex
	 *            性别
	 */
	public Member(long id, String userName, String password, String phone, String email, String sexual) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.sex = sexual; // v1.1 by ls
		this.department = null;
		this.delect = 0;
	}

	/**
	 * 构造方法2 有部门的 无需删除参数的，但又部门参数
	 * 
	 * @param userName
	 *            用户名（登录名）
	 * @param password
	 *            密码
	 * @param phone
	 *            电话
	 * @param email
	 *            邮箱
	 * @param department
	 *            部门
	 * @param sex
	 *            性别
	 */
	public Member(long id, String userName, String password, String phone, String email, String sex,
			Department department) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.sex = sex;
		this.department = department;
		this.delect = 0;
	}

	/**
	 * 构造方法3 需要删除参数和部门参数的
	 * 
	 * @param userName
	 *            用户名（登录名）
	 * @param password
	 *            密码
	 * @param phone
	 *            电话
	 * @param email
	 *            邮箱
	 * @param department
	 *            部门
	 * @param sex
	 *            性别
	 */
	public Member(long id, String userName, String password, String phone, String email, String sex, Department department,
			int delect) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.sex = sex;
		this.department = department;
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
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
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

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}
}
