package com.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.Cookie;
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

import com.db.ManagerRepository;
import com.db.MemberRepository;
import com.domain.Manager;
import com.domain.Member;

/**
 * 系统控制器类
 * 
 * @author qiuli
 * @version v1.0
 */

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private ManagerRepository managerRepository;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		session.removeAttribute("member");
		session.removeAttribute("manager");
		session.removeAttribute("headerInfo");
		session.removeAttribute("error_1");
		session.removeAttribute("error_2");
		return "home";
	}

	/**
	 * 管理员登陆
	 * 
	 * @return
	 */
	@RequestMapping(value = "/managerLogin", method = GET)
	public String managerLogin(HttpSession session) {
		session.removeAttribute("loginError");
		return "manager.jsp/managerLogin";
	}

	/**
	 * 管理员提交登陆表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/managerLogin", method = POST)
	public String managerLogIn(@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "password", defaultValue = "") String password, HttpSession session) {
		session.setAttribute("error_1","");
		Manager manager = null;
		manager = managerRepository.findByUsername(userName, password);
		if (manager != null && manager.getDelect() != 1) {
			session.removeAttribute("manager");
			session.setAttribute("manager", manager);
			return "redirect:/manager";
		}
		session.setAttribute("error_1","登录失败，请确认身份、用户名和密码后重试");
		return "manager.jsp/managerLogin";
	}

	/**
	 * 员工登陆
	 * 
	 * @return
	 */
	@RequestMapping(value = "/memberLogin", method = GET)
	public String memberLogin(HttpSession session, HttpServletRequest req) {
		session.setAttribute("error_1","");
		// 给name、password设定初始
		String name = null;
		String pass = null;
		// 获取cookies
		Cookie cookies[] = req.getCookies();
		// 如果cookies有值，查找里面的name和password属性，取得其值
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("name")) {
					name = cookies[i].getValue();
				}
				if (cookies[i].getName().equals("password")) {
					pass = cookies[i].getValue();
				}
			}
		}
		// name和password都得到，自动登录
		if (name != null && pass != null) {
			Member member = null;
			member = memberRepository.findByUsername(name, pass);
			if (member != null) {
				if (member.getDelect() != 1) {
					session.removeAttribute("member");
					session.setAttribute("member", member);
					return "redirect:/member";
				}
			}
		}
		return "member.jsp/memberLogin";
	}

	/**
	 * 员工提交登陆表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/memberLogin", method = POST)
	public String memberLogIn(@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "password", defaultValue = "") String password,
			@RequestParam(value = "auto", defaultValue = "") boolean auto, HttpSession session, HttpServletRequest req,
			HttpServletResponse res) {
		session.setAttribute("error_1","");
		Member member = null;
		member = memberRepository.findByUsername(userName, password);
		if (member != null) {
			if (member.getDelect() != 1) {
				if (auto) {
					// 得到输入的用户名和密码
					String name = member.getUserName();
					String pass = member.getPassword();
					boolean judge = true;
					// 获取cookies
					Cookie cookies[] = req.getCookies();
					// 往cookies中写入用户名和密码
					if (cookies != null) {
						for (int i = 0; i < cookies.length; i++) {
							if (cookies[i].getName().equals("name")) {
								cookies[i].setValue(name);
								// 把更新后的cookies加进去覆盖掉原来的
								res.addCookie(cookies[i]);
								judge = false;
							}
							if (cookies[i].getName().equals("password")) {
								cookies[i].setValue(pass);
								// 把更新后的cookies加进去覆盖掉原来的
								res.addCookie(cookies[i]);
								judge = false;
							}
						}
					}

					// 第一次的时候创建cookies，把用户名和密码写入
					if (judge) {
						Cookie cookie1 = new Cookie("name", name);
						Cookie cookie2 = new Cookie("password", pass);
						// 不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
						cookie1.setMaxAge(24 * 60 * 60);
						cookie2.setMaxAge(24 * 60 * 60);
						res.addCookie(cookie1);
						res.addCookie(cookie2);
					}
				} else {
					// 清空cookies，设name和password为空“”
					Cookie cookie1 = new Cookie("name", "");
					Cookie cookie2 = new Cookie("password", "");
					// 不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
					cookie1.setMaxAge(24 * 60 * 60);
					cookie2.setMaxAge(24 * 60 * 60);
					res.addCookie(cookie1);
					res.addCookie(cookie2);
				}
				session.removeAttribute("member");
				session.setAttribute("member", member);
				return "redirect:/member";
			}
		}
		session.setAttribute("error_1","登陆失败，请确认身份、用户名和密码后重试");
		return "member.jsp/memberLogin";
	}

	/**
	 * 管理员注册
	 * 
	 * @return
	 */
	@RequestMapping(value = "/managerRegister", method = GET)
	public String managerRegister(Model model, HttpSession session) {
		model.addAttribute(new Manager());
		session.setAttribute("error_1","");
		return "manager.jsp/managerRegister";
	}

	/**
	 * 管理员提交注册表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/managerRegister", method = POST)
	public String managerRegistion(@Valid Manager manager, Errors errors, HttpSession session, Model model) {
		session.setAttribute("error_1","");
		if (errors.hasErrors()) {
			return "manager.jsp/managerRegister";
		}
		Manager m = null;
		m = managerRepository.checkUsername(manager);
		if (m != null) {
			if (m.getDelect() == 0) {
				session.setAttribute("error_1","注册失败，已有该用户名，请重新输入新的用户名");
				return "manager.jsp/managerRegister";
			}
		}
		managerRepository.save(manager);
		model.addAttribute(manager);
		return "manager.jsp/manager_profile";
	}

	/**
	 * 员工注册
	 * 
	 * @return
	 */
	@RequestMapping(value = "/memberRegister", method = GET)
	public String memberRegister(Model model, HttpSession session) {
		session.setAttribute("error_1","");
		model.addAttribute(new Member());
		String[] sex = new String[] { "", "男", "女" };
		session.setAttribute("sex", sex);
		return "member.jsp/memberRegister";
	}

	/**
	 * 员工提交注册表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/memberRegister", method = POST)
	public String memberRegistion(@Valid Member member, Errors errors, Model model, HttpSession session) {
		session.setAttribute("error_1","");
		if (member.getSex().equals("")) {
			member.setSex("保密");
		}
		if (errors.hasErrors()) {
			return "member.jsp/memberRegister";
		}
		Member m = null;
		m = memberRepository.checkUsername(member);
		if (m != null) {
			if (m.getDelect() == 0) {
				session.setAttribute("error_1","注册失败，已有该用户名，请重新输入新的用户名");
				return "member.jsp/memberRegister";
			}
		}
		memberRepository.save(member);
		model.addAttribute(member);
		return "member.jsp/member_profile";
	}

}
