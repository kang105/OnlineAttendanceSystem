package com.domain;

import java.util.Date;

/**
 * 考勤对象类
 * 
 * @author qiuli
 * @version v1.0
 */
public class Attendance {
	private final Long id;
	private final Member member;
	private final Date attendDate;

	/**
	 * 构造方法
	 * 
	 */
	public Attendance(Long id, Member member, Date attendDate) {
		this.id = id;
		this.member = member;
		this.attendDate = attendDate;
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
	 * @return the attendDate
	 */
	public Date getAttendDate() {
		return attendDate;
	}

}
