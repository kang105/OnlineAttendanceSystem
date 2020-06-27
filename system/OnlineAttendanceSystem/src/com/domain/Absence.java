package com.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 请假对象类
 * 
 * @author liuShuai
 * @version v1.1
 */
public class Absence {

	private Long id;
	private Member member;
	private AbsenceType absenceType;
	private String decription;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date absenceTime;
	private boolean isChecked;
	private Manager manager;
	private Date check_time;

	public Absence() { // v1.1 by ls
		this.id = null;
		this.member = null;
		this.absenceType = null;
		this.decription = null;
		this.absenceTime = null;
		this.isChecked = false;
		this.manager = null;
		this.check_time = null;
	}

	/**
	 * 构造方法1 后三项无需参数的，未审核的
	 * 
	 */
	public Absence(Long id, Member member, AbsenceType absenceType, String decription, Date absenceTime) {
		this.id = id;
		this.member = member;
		this.absenceType = absenceType;
		this.decription = decription;
		this.absenceTime = absenceTime;
		this.isChecked = false;
		this.manager = null;
		this.check_time = null;
	}

	/**
	 * 构造方法2
	 * 
	 */
	public Absence(Long id, Member member, AbsenceType absenceType, String decription, Date absenceTime,
			boolean isChecked, Manager manager, Date check_time) {
		this.id = id;
		this.member = member;
		this.absenceType = absenceType;
		this.decription = decription;
		this.absenceTime = absenceTime;
		this.isChecked = isChecked;
		this.manager = manager;
		this.check_time = check_time;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * @return the absenceType
	 */
	public AbsenceType getAbsenceType() {
		return absenceType;
	}

	/**
	 * @return the decription
	 */
	public String getDecription() {
		return decription;
	}

	/**
	 * @return the absenceTime
	 */
	public Date getAbsenceTime() {
		return absenceTime;
	}

	/**
	 * @return the isChecked
	 */
	public boolean getIsChecked() {
		return isChecked;
	}

	/**
	 * @return the manager
	 */
	public Manager getManager() {
		return manager;
	}

	/**
	 * @return the check_time
	 */
	public Date getCheck_time() {
		return check_time;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param member
	 *            the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * @param absenceType
	 *            the absenceType to set
	 */
	public void setAbsenceType(AbsenceType absenceType) {
		this.absenceType = absenceType;
	}

	/**
	 * @param decription
	 *            the decription to set
	 */
	public void setDecription(String decription) {
		this.decription = decription;
	}

	/**
	 * @param absenceTime
	 *            the absenceTime to set
	 */
	public void setAbsenceTime(Date absenceTime) {
		this.absenceTime = absenceTime;
	}

	/**
	 * @param isChecked
	 *            the isChecked to set
	 */
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	/**
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	/**
	 * @param check_time
	 *            the check_time to set
	 */
	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}
}
