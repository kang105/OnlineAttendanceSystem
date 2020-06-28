package com.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.db.MemberRepository;
import com.domain.AbsenceType;
import com.domain.Department;
import com.domain.Manager;
import com.domain.Member;

import com.db.AbsenceRepository;
import com.db.AbsenceTypeRepository;
import com.db.AttendanceRepository;
import com.db.DepartmentRepository;

import java.util.Date;
import java.io.IOException;
import java.text.ParseException;

/**
 * 管理者控制器类
 * 
 * @author leihaowen kangyanqing
 * @version v1.0
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private AbsenceTypeRepository absenceTypeRepository;
	@Autowired
	private AbsenceRepository absenceRepository;
	@Autowired
	private AttendanceRepository attendanceRepository;

	/**
	 * @return managerIndex.jsp
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String member(Model model) {
		return "manager.jsp/managerIndex";
	}

	/**
	 * 查看未审核请假列表 by kangYQ
	 * 
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/absenceList_uncheck", method = GET)
	public String showAbsenceList_uncheck(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize, HttpSession session) {

		model.addAttribute("paginationSupport", absenceRepository.findAllUncheck(pageNo, pageSize));
		return "manager.jsp/absence.jsp/absenceList_uncheck";

	}

	/**
	 * 查看已审核请假列表 by kangYQ
	 * 
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/absenceList_check", method = GET)
	public String showAbsenceList_check(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize, HttpSession session) {
		model.addAttribute("paginationSupport", absenceRepository.findAllCheck(pageNo, pageSize));
		return "manager.jsp/absence.jsp/absenceList_check";

	}

	/**
	 * 批准请假申请 by kangYQ
	 * 
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/allowAbsence")
	public String allowAbsence(HttpSession session, String id) {
		Manager manager = (Manager) session.getAttribute("manager");
		absenceRepository.allowAbsence(id, String.valueOf(manager.getId()));
		return "redirect:/manager/absenceList_uncheck";
	}

	/**
	 * 拒绝请假申请 by kangYQ
	 * 
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/refuseAbsence")
	public String refuseAbsence(HttpSession session, String id) {
		Manager manager = (Manager) session.getAttribute("manager");
		absenceRepository.refuseAbsence(id, String.valueOf(manager.getId()));
		return "redirect:/manager/absenceList_uncheck";
	}

	/**
	 * 查看所有请假类型 by kangYQ
	 * 
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/absenceTypeList", method = GET)
	public String AbsenceTypeList(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize, HttpSession session) {
		model.addAttribute("paginationSupport", absenceTypeRepository.findAll(pageNo, pageSize));
		return "manager.jsp/A-type.jsp/absenceTypeList";

	}

	/**
	 * 删除请假类型 by kangYQ
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/A-Type_delete")
	public String deleteAType(String id) {
		absenceTypeRepository.delect(id);
		return "redirect:/manager/absenceTypeList";
	}

	/**
	 * 添加请假类型 by kangYQ
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/A-Type_add", method = GET)
	public String addATypeView(Model model, HttpSession session) {
		session.setAttribute("error_1", "");
		model.addAttribute(new AbsenceType());
		return "manager.jsp/A-type.jsp/absenceType_add";
	}

	@RequestMapping(value = "/A-Type_add", method = POST)
	public String addAType(@Valid AbsenceType absenceType, Errors errors, HttpSession session) {
		session.setAttribute("error_1", "");
		if (errors.hasErrors()) {
			return "manager.jsp/A-type.jsp/absenceType_add";
		} else {
			AbsenceType absenceType1 = absenceTypeRepository.findByType(absenceType.getType());
			if (absenceType1 == null) {
				// 添加的请假类型之前没有过
				absenceTypeRepository.save(absenceType);
				return "redirect:/manager/absenceTypeList";

			} else {
				session.setAttribute("error_1", "该请假类型存在，请重新编辑请假类型");
				return "manager.jsp/A-type.jsp/absenceType_add";
			}
		}

	}

	/**
	 * 修改请假类型 by kangYQ
	 * 
	 * @param model
	 * @param request
	 * @param id
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/A-Type_modify", method = GET)
	public String modifyATypeView(Model model, HttpServletRequest request, String id, HttpSession session)
			throws Exception {
		session.setAttribute("error_2", "");
		model.addAttribute(new AbsenceType());
		session.setAttribute("error_1", "");
		request.setAttribute("modifyAbsenceType", absenceTypeRepository.findOne(id));
		return "manager.jsp/A-type.jsp/absenceType_add";
	}

	@RequestMapping(value = "/A-Type_modify", method = POST)
	public String modifyAType(@Valid AbsenceType absenceType, Errors errors, HttpSession session) {
		session.setAttribute("error_1", "");
		session.setAttribute("error_2", "");
		if (errors.hasErrors()) {
			return "manager.jsp/A-type.jsp/absenceType_add";
		} else {
			AbsenceType absenceType1 = absenceTypeRepository.findByType(absenceType.getType());
			if (absenceType1 == null || absenceType1.getId() == absenceType.getId()) {
				// 新编辑的类型不存在或未重新编辑
				absenceTypeRepository.change(absenceType);
				return "redirect:/manager/absenceTypeList";

			} else {
				session.setAttribute("error_2", "该请假类型存在，请重新编辑请假类型");
				return "manager.jsp/A-type.jsp/absenceType_add";
			}

		}
	}

	/**
	 * 查找特定的请假类型 by kangYQ
	 * 
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/A-Type_search", method = POST)
	public String searchAType(@RequestParam(value = "type", defaultValue = "") String type,
			HttpServletRequest request) {
		System.out.println("test");
		AbsenceType absenceType = absenceTypeRepository.findByType(type);
		if (absenceType != null) {
			request.setAttribute("searchAbsenceType", absenceType);
			return "manager.jsp/A-type.jsp/absenceType_search";
		} else {
			return "redirect:/manager/absenceTypeList";
		}

	}

	/**
	 * 查看员工信息列表 by leihaowen
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/MemberList", method = GET)
	public String memberList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "3") int pageSize, Model model, HttpSession session) {
		session.setAttribute("error_1", "");
		PaginationSupport<Member> pm = memberRepository.findPage(pageNo, pageSize);
		model.addAttribute("paginationSupport", pm);
		return "manager.jsp/member.jsp/memberList";
	}

	/**
	 * 
	 * 返回查询员工结果 by leihaowen
	 * 
	 * @param id
	 * @param userName
	 * @param session
	 * @param model
	 * @return managerSearchMemberInfo.jsp
	 */
	/**
	 */
	@RequestMapping(value = "/MemberList", method = POST)
	public String selectMember(@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "userName", defaultValue = "") String userName, HttpSession session, Model model) {
		if (id.equals("") && userName.equals(""))
			return "redirect:/manager/MemberList";
		else if (id.equals("") && !userName.equals("")) {
			if (memberRepository.findByUserName(userName) == null) {
				return "redirect:/manager/MemberList";
			} else {
				model.addAttribute(memberRepository.findByUserName(userName));
				return "manager.jsp/member.jsp/managerSearchMemberInfo";
			}

		} else if (!id.equals("") && userName.equals("")) {
			long l = Long.parseLong(id);
			if (memberRepository.findOne(l) == null) {
				return "redirect:/manager/MemberList";
			} else {
				model.addAttribute(memberRepository.findOne(l));
				return "manager.jsp/member.jsp/managerSearchMemberInfo";
			}

		} else {
			long l = Long.parseLong(id);
			if (memberRepository.findOneByUserNameAndId(l, userName) == null) {
				return "redirect:/manager/MemberList";
			} else {
				model.addAttribute(memberRepository.findOne(l));
				return "manager.jsp/member.jsp/managerSearchMemberInfo";
			}

		}

	}

	/**
	 * 修改员工信息 by lhw
	 * 
	 * @param id
	 * @param session
	 * @param model
	 * @return memberFixInfo
	 */
	@RequestMapping(value = "/FixMemberInfo", method = RequestMethod.GET)
	public String fixMemberInfo(@RequestParam(value = "memberId", defaultValue = "0") String id, HttpSession session,
			Model model) {
		session.setAttribute("departments", departmentRepository.findAll());
		long l = Long.parseLong(id);
		model.addAttribute(memberRepository.findOne(l));
		String[] sex = new String[] { "男", "女" };
		session.setAttribute("sex", sex);
		return "manager.jsp/member.jsp/memberFixInfo";
	}

	/**
	 * 修改员工信息 by leihaowen
	 * 
	 * @param memberForm
	 * @param session
	 * @param memberId
	 * @return MemberList
	 */
	@RequestMapping(value = "/FixMemberInfo", method = RequestMethod.POST)
	public String fixMemberInfo_POST(@Valid MemberForm memberForm, HttpSession session,
			@RequestParam(value = "memberId") int memberId) {
		session.setAttribute("error_1", "");
		Member m = null;
		m = memberRepository.checkUsername(memberForm.toMember());
		if (m != null && m.getId() != memberId) {
			if (m.getDelect() == 0) {
				session.setAttribute("error_1", "修改失败，此用户名已被注册");
				return "redirect:/manager/FixMemberInfo?memberId=" + memberId;
			}
		}
		memberRepository.update(memberForm.toMember(), memberId);
		return "redirect:/manager/MemberList";
	}

	/**
	 * 删除员工信息 by lhw
	 * 
	 * @param id
	 * @return MemberList
	 */
	@RequestMapping(value = "/DeleteCurrentMember", method = RequestMethod.GET)
	public String deleteCurrentMember(@RequestParam(value = "memberId", defaultValue = "0") String id) {
		long l = Long.parseLong(id);
		memberRepository.delete(l);
		return "redirect:/manager/MemberList";
	}

	/**
	 * 增加员工信息 by kang
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/member_add", method = GET)
	public String addMember(Model model, HttpSession session) {
		model.addAttribute(new Member());
		String[] sex = new String[] { "", "男", "女" };
		session.setAttribute("sex", sex);
		session.setAttribute("error_1", "");
		return "manager.jsp/member.jsp/member_add";
	}

	/**
	 * 增加员工信息 by kang
	 * 
	 * @return
	 */
	@RequestMapping(value = "/member_add", method = POST)
	public String addMember(@Valid Member member, Errors errors, Model model, HttpSession session) {
		session.setAttribute("error_1", "");
		if (member.getSex().equals("")) {
			member.setSex("保密");
		}
		if (errors.hasErrors()) {
			return "manager.jsp/member.jsp/member_add";
		}
		Member m = null;
		m = memberRepository.checkUsername(member);
		if (m != null) {
			if (m.getDelect() == 0) {
				session.setAttribute("error_1", "添加失败，此用户名已被注册");
				return "manager.jsp/member.jsp/member_add";
			}
		}
		memberRepository.save(member);
		model.addAttribute(member);
		return "redirect:/manager/MemberList";
	}

	// by周子泰 （由于此代码无法实现功能，他也不改，所以只能注释掉，实现功能由康艳晴写）

	// @RequestMapping(value = "/attendanceRecord", method = GET)
	// public String AttendanceRecord(Model model, @RequestParam(value =
	// "pageNo", defaultValue = "1") int pageNo,
	// @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
	// HttpServletRequest request) {
	// Date sdate = (Date) request.getAttribute("sdate");
	// Date edate = (Date) request.getAttribute("edate");
	// model.addAttribute("paginationSupport",
	// attendanceRepository.findAttendance(pageNo, pageSize, sdate, edate));
	// return "attendanceRecord";
	//
	// }
	//
	// @RequestMapping(value = "attendance_search", method = POST)
	// public String searchTime(@RequestParam(value = "sdate", defaultValue =
	// "") Date sdate,
	// @RequestParam(value = "edate", defaultValue = "") Date edate,
	// HttpServletRequest request) { //
	// List<Attendance> attendanceList = attendanceRepository.find(sdate,
	// edate);
	// // request.setAttribute("checkAttendance",attendanceList); //return
	// // "attendance_search";
	// if (sdate != null && edate != null && sdate.before(edate)) {
	// request.setAttribute("sdate", sdate);
	// request.setAttribute("edate", edate);
	// return "attendanceRecord";
	// } else {
	// return "checkError";
	// }
	//
	// }

	/**
	 * 指定日期内查看考勤 by kangYQ
	 * 
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/attend_search_time", method = GET)
	public String searchAttend(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize, HttpSession session) {
		session.setAttribute("error_1", "");
		if (session.getAttribute("btime") != null && session.getAttribute("etime") != null) {
			Date btime = (Date) session.getAttribute("btime");
			Date etime = (Date) session.getAttribute("etime");
			model.addAttribute("paginationSupport", attendanceRepository.findPage(pageNo, pageSize, btime, etime));
		}
		return "manager.jsp/attend.jsp/attendList";
	}

	@RequestMapping(value = "/attend_search_time", method = POST)
	public String searchAttend(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(value = "bdate", defaultValue = "") String bdate,
			@RequestParam(value = "edate", defaultValue = "") String edate, HttpSession session) throws ParseException {
		session.removeAttribute("btime");
		session.removeAttribute("etime");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date btime = null;
		Date etime = null;
		try {
			btime = sf.parse(bdate);
			etime = sf.parse(edate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(btime != null && etime != null);
		if (btime != null && etime != null) {
			session.setAttribute("btime", btime);
			session.setAttribute("etime", etime);
			model.addAttribute("paginationSupport", attendanceRepository.findPage(pageNo, pageSize, btime, etime));
			return "manager.jsp/attend.jsp/attendList";
		} else {
			session.setAttribute("error_1", "日期格式输入有误，应为：yyyy-MM-dd，请重新输入");
			return "manager.jsp/attend.jsp/attendList";
		}

	}

	/**
	 * 树状展示部门信息 by leihaowen
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/Department", method = RequestMethod.GET)
	public String getDepartment(Model model) {
		List<Department> departments = departmentRepository.findAll();
		FormatDepartments fd = new FormatDepartments(departments);
		String data = fd.format();

		model.addAttribute("data", data);
		return "manager.jsp/department.jsp/department";
	}

	/**
	 * 搜索部门信息 by leihaowen
	 * 
	 * @param name
	 * @param model
	 * @return managerSearchDepartment
	 */
	@RequestMapping(value = "/Department", method = RequestMethod.POST)
	public String searchDepartment(@RequestParam(value = "departmentName", defaultValue = "") String name,
			Model model) {
		if (departmentRepository.findOne(name) == null) {
			return "redirect:/manager/Department";
		} else {
			model.addAttribute(departmentRepository.findOne(name));
			return "manager.jsp/department.jsp/managerSearchDepartment";
		}
	}

	/**
	 * 新增部门 by leihaowen
	 * 
	 * @param session
	 * @param model
	 * @return departmentAdd
	 */
	@RequestMapping(value = "/addDepartment", method = RequestMethod.GET)
	public String addDepartment(HttpSession session, Model model) {
		session.setAttribute("departments", departmentRepository.findAll());
		session.setAttribute("error_1", "");
		DForm df = new DForm();
		model.addAttribute(df);
		return "manager.jsp/department.jsp/departmentAdd";
	}

	/**
	 * 新增部门 by leihaowen
	 * 
	 * @param df
	 * @param session
	 * @return Department
	 */
	@RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
	public String addDepartments(@Valid DForm df, HttpSession session) {
		Department d = null;
		d = df.toDeperment();
		String name = d.getDepartment();
		Department c = departmentRepository.findOne(name);
		if (c == null) {
			departmentRepository.add(df.toDeperment());
			return "redirect:/manager/Department";
		} else {
			session.setAttribute("error_1", "已有该部门，请确认后再输入部门名字");
			return "manager.jsp/department.jsp/departmentAdd";
		}
	}

	/**
	 * 删除当前部门 by leihaowen
	 * 
	 * @param id
	 * @return Department
	 */
	@RequestMapping(value = "/DeleteCurrentDepartment", method = RequestMethod.GET)
	public String deleteCurrentDepartment(@RequestParam(value = "departmentId") int id) {
		if (id == 1) {
			return "manager.jsp/department.jsp/delectError";
		}
		Department department = departmentRepository.findOne_BY_Id(id);
		List<Department> departments = departmentRepository.findAll();
		for (Department d : departments) {
			if (d.getUpperDepartment().getId() == department.getId()) {
				departmentRepository.changeUpperDepartment_By_Id(d.getId(), department.getUpperDepartment().getId());
			}
		}
		List<Member> M = memberRepository.findAll();
		for (Member m : M) {
			if (m.getDepartment().getId() == department.getId()) {
				memberRepository.setD(m.getId(), department.getUpperDepartment().getId());
			}
		}
		departmentRepository.delect((long) id);
		return "redirect:/manager/Department";
	}

	/**
	 * 修改部门信息 by leihaowen
	 * 
	 * @param id
	 * @param session
	 * @param model
	 * @return departmentFixInfo
	 */
	@RequestMapping(value = "/FixDepartmentInfo", method = RequestMethod.GET)
	public String fixDepartmentInfo(@RequestParam(value = "departmentId", defaultValue = "") long id,
			HttpSession session, Model model) {
		session.removeAttribute("error_1");
		session.removeAttribute("error_2");
		session.setAttribute("departments", departmentRepository.findAll());
		model.addAttribute(departmentRepository.findOne_BY_Id(id));
		DForm df = new DForm();
		model.addAttribute(df);
		return "manager.jsp/department.jsp/departmentFixInfo";
	}

	/**
	 * 修改部门信息 by leihaowen
	 * 
	 * @param df
	 * @param session
	 * @param departmentId
	 * @return departmentFixInfo
	 */
	@RequestMapping(value = "/FixDepartmentInfo", method = RequestMethod.POST)
	public String fixDepartmentInfo_POST(@Valid DForm df, HttpSession session,
			@RequestParam(value = "departmentId") int departmentId) {
		session.removeAttribute("error_1");
		session.removeAttribute("error_2");
		Department department = departmentRepository.findOne(df.getName());
		boolean check = false;
		check = departmentRepository.findAllLower((long) departmentId, df.getDepartment());
		if (check == false) {
			session.setAttribute("error_1", "不能添加其自身或自身下级作为上一级 ");
			return "manager.jsp/department.jsp/departmentFixInfo";
		}
		if (department == null || department.getId() == (long) departmentId) {
			if (df.getDepartment() != (long) departmentId) {
				departmentRepository.change(df.toDeperment(), (long) departmentId);
				return "redirect:/manager/Department";
			} else {
				session.setAttribute("error_1", "不能添加其自身或自身下级作为上级 ");
				return "manager.jsp/department.jsp/departmentFixInfo";
			}
		} else {
			session.setAttribute("error_2", "该部门名已存在 ");
			return "manager.jsp/department.jsp/departmentFixInfo";
		}

	}

	@RequestMapping(value = "/fixDepartmentTree", method = RequestMethod.POST)
	public void fixDepartmentTree(@RequestParam(value = "Id") int Id, @RequestParam(value = "newName") String newName,
			HttpServletResponse response) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("{msg :\"");
		String name = departmentRepository.findOne_BY_Id(Id).getDepartment();
		if (name.equals(newName)) {
			sb.append("-1");
		} else {
			Department department = departmentRepository.findOne_BY_Id(Id);
			boolean check = (null == departmentRepository.findOne(newName));
			if (check == false) {
				sb.append("0");
			} else {
				department.setDepartment(newName);
				departmentRepository.change(department, Id);
				sb.append("1");
			}
		}
		sb.append("\",title :\"");
		sb.append(departmentRepository.findOne_BY_Id(Id).getDepartment());
		sb.append("\"}");
		response.getWriter().write(sb.toString());
	}

	@RequestMapping(value = "/delDepartmentTree", method = RequestMethod.POST)
	public void delDepartmentTree(@RequestParam(value = "Id") int Id, HttpServletResponse response) throws IOException {
		StringBuffer sb = new StringBuffer();
		departmentRepository.deletAllLower(Id);
		boolean check = (null == departmentRepository.findOne_BY_Id(Id));
		if (check == true) {
			sb.append("0");
		} else {
			sb.append("1");
		}
		response.getWriter().write(sb.toString());
	}

}
