package com.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.domain.Absence;
import com.domain.Attendance;
import com.domain.Member;

import com.db.AttendanceRepository;
import com.db.DepartmentRepository;
import com.db.AbsenceRepository;
import com.db.AbsenceTypeRepository;

/**
 * 管理者控制器类
 * 
 * @author LiuShuai FengJingtian
 * @version v1.1
 */
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private AttendanceRepository attendanceRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private AbsenceTypeRepository absenceTypeRepository;
	@Autowired
	private AbsenceRepository absenceRepository;
	@Autowired
	private DepartmentRepository departmentRepository; // v1.1

	/**
	 * "/member"默认get方法
	 * 
	 * @param
	 * 
	 * @return "member.jsp/memberIndex"
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String member(Model model, HttpSession session) {
		Date now = new Date();
		model.addAttribute("now", now);
		if (session.getAttribute("member") != null) { // 有员工session
			Member member = (Member) session.getAttribute("member");
			Long memberID = member.getId();
			model.addAttribute("attendances", attendanceRepository.findByMonth(memberID, now)); // 获取日历tag参数
																								// 本月打卡记录
			session.setAttribute("headerInfo", member.getUserName() + "的员工首页");
		}
		return "member.jsp/memberIndex"; // 返回员工首页
	}

	/**
	 * 员工进行打卡
	 * 
	 * @param
	 * @return "member.jsp/attend.jsp/attended"
	 */
	@RequestMapping(value = "/attend", method = GET)
	public String attend(Model model, HttpSession session) {
		Member temp = (Member) session.getAttribute("member");
		session.setAttribute("headerInfo", "今日" + temp.getUserName() + "的打卡");
		if (!attendanceRepository.isAttend(temp.getId(), new Date())) { // 判断今天是否打卡
			attendanceRepository.save(new Attendance(null, temp, new Date()));
			return "member.jsp/attend.jsp/attendSuccess"; // 未打卡则返回打卡成功页面
		} else {
			return "member.jsp/attend.jsp/attended"; // 已打卡则返回已打卡页面
		}
	}

	/**
	 * 员工维护个人基本信息
	 * 
	 * @param
	 * @return "member.jsp/info.jsp/info"
	 * @throws Exception
	 */
	@RequestMapping(value = "/info", method = GET)
	public String info(Model model, HttpSession session) throws Exception {
		Member temp = (Member) session.getAttribute("member");
		session.setAttribute("headerInfo", temp.getUserName() + "的个人信息"); 
		return "member.jsp/info.jsp/info";
	}

	/**
	 * 员工修改个人基本信息
	 * 
	 * @param
	 * @return "member.jsp/info.jsp/fixInfo"
	 * @throws Exception
	 */
	@RequestMapping(value = "/fixInfo", method = GET)
	public String infoFix(Model model, HttpSession session) throws Exception {
		Member temp = (Member) session.getAttribute("member");
		model.addAttribute("headerInfo", temp.getUserName() + "的个人信息");
		model.addAttribute(new Member());
		String[] s = new String[] { "", "男", "女" };
		session.setAttribute("sex", s);
		session.setAttribute("departments", departmentRepository.findAll()); // v1.1
																				// 查找所有的部门
		session.setAttribute("error_1","");
		return "member.jsp/info.jsp/fixInfo";
	}

	/**
	 * 员工修改个人基本信息 表单
	 * 
	 * @param
	 * @return "member.jsp/info.jsp/fixInfo" "member.jsp/info.jsp/info"
	 */
	@RequestMapping(value = "/fixInfo", method = POST)
	public String infoFixProcess(@Valid Member member, Errors errors, HttpSession session,
			@RequestParam(value = "memberID") int memberID) {
		if (errors.hasErrors()) { 
			return "member.jsp/info.jsp/fixInfo";
		}
		if (member.getSex().equals("")) { //设置性别默认值为“保密”
			member.setSex("保密");
		}
		Member ma = (Member) session.getAttribute("member"); //查询是否重名
		Member m = null;
		m = memberRepository.checkUsername(member);
		if (m != null && m.getId() != ma.getId()) { 
			if (m.getDelect() == 0) {
				session.setAttribute("error_1", "修改失败，已有该用户名");
				return "member.jsp/info.jsp/fixInfo";
			}
		} else {
			session.removeAttribute("repeatMember");
		}
		member = memberRepository.update(member, memberID);
		session.removeAttribute("member");
		session.removeAttribute("manager");
		session.setAttribute("member", member);
		return "member.jsp/info.jsp/info";
	}

	/**
	 * 员工查看自己的打卡信息
	 * 
	 * @param
	 * @return "member.jsp/attend.jsp/attendance"
	 */
	@RequestMapping(value = "/attendance", method = GET)
	public String attendance(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		Long memberID = member.getId();
		model.addAttribute("paginationSupport", attendanceRepository.findPage(pageNo, pageSize, memberID));
		session.setAttribute("headerInfo", member.getUserName() + "的考勤");
		return "member.jsp/attend.jsp/attendance";
	}

	/**
	 * 员工申请休假
	 * 
	 * @param
	 * @return "member.jsp/absence.jsp/takeOffForm"
	 */
	@RequestMapping(value = "/takeOff", method = GET)
	public String takeOff(Model model, HttpSession session) {
		session.setAttribute("error_1", "");
		session.setAttribute("ableDate", absenceRepository.findAbledate());
		model.addAttribute(new Absence());
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String now = df.format(d);
		session.setAttribute("absenceType", absenceTypeRepository.findAll());
		session.setAttribute("Date", now);
		Member member = (Member) session.getAttribute("member");
		session.setAttribute("headerInfo", member.getUserName() + "的请假单");
		return "member.jsp/absence.jsp/takeOffForm";
	}

	/**
	 * 员工申请休假表单
	 * 
	 * @return "member.jsp/absence.jsp/takeOffForm"
	 *         "member.jsp/absence.jsp/absenced"
	 *         "member.jsp/absence.jsp/takeOffSuccess"
	 */
	@RequestMapping(value = "/takeOff", method = POST)
	public String takeOffProcess(Model model, @Valid Absence absence, Errors errors, HttpSession session) {
		session.setAttribute("error_1", "");
		if (errors.hasErrors()) {
			return "member.jsp/absence.jsp/takeOffForm";
		}
		String s="";
		s=absence.getDecription();
		if(s.length()>48){
			session.setAttribute("error_1", "请假描述不能超过48个字");
			return "member.jsp/absence.jsp/takeOffForm";
		}
		Member member = (Member) session.getAttribute("member");
		Long memberID = member.getId();
		if (absenceRepository.isAbsence(memberID, absence.getAbsenceTime())) { // 如果已经请过当天的假（通过或者未检查）
			absence = absenceRepository.findOne(memberID, absence.getAbsenceTime());
			session.setAttribute("absence", absence);
			session.setAttribute("headerInfo", member.getUserName() + "的请假单");
			return "member.jsp/absence.jsp/absenced";
		} else { // 可以请假
			absenceRepository.save(absence, memberID);
			session.setAttribute("headerInfo", member.getUserName() + "的请假单");
			return "member.jsp/absence.jsp/takeOffSuccess";
		}

	}

	/**
	 * 查看请假审核状态
	 * 
	 * @param
	 * @return "member.jsp/absence.jsp/absences"
	 */
	@RequestMapping(value = "/absence", method = GET)
	public String absence(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		Long memberID = member.getId();
		model.addAttribute("paginationSupport", absenceRepository.findPage(pageNo, pageSize, memberID));
		session.setAttribute("headerInfo", member.getUserName() + "的请假记录");
		return "member.jsp/absence.jsp/absences";
	}

}
