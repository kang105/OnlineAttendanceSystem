package Tag;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.domain.Attendance;

/**
 * 打卡本月日历标签类实现
 * 
 * @author liuShuai
 * @version v1.0
 */
public class DaterTag extends SimpleTagSupport {

	private List<Attendance> attendances;
	private Date date;

	public void setAttendances(List<Attendance> sList) {
		this.attendances = sList;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public void doTag() throws JspException, IOException { // 执行Tag类

		List<Date> attendanceDays = new ArrayList<Date>();
		Iterator<Attendance> it = attendances.iterator();
		while (it.hasNext()) {
			Attendance attendance = (Attendance) it.next();
			attendanceDays.add(attendance.getAttendDate());
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		JspContext pageContext = this.getJspContext();
		int current_year = calendar.get(Calendar.YEAR);
		int current_month = calendar.get(Calendar.MONTH) + 1;
		// 当前时间的日期
		int current_day = calendar.get(Calendar.DAY_OF_MONTH);
		JspWriter print_writer = pageContext.getOut();

		print_writer.print("<br>");
		print_writer.print("<hr>");
		print_writer.print("本月打卡情况");
		print_writer.print("<br>");
		print_writer.print("<hr>");
		boolean flag = false;

		String year_month = current_year + "-" + current_month;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			calendar.setTime(sdf.parse(year_month));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		// 获取当前月份的天数
		int number_of_current_month_days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		String year_month_day = current_year + "-" + current_month + "-1";
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			calendar.setTime(sdf.parse(year_month_day));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		// 获取当前月份的第一天是星期几
		int current_month_first_day_week = calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7
				: calendar.get(Calendar.DAY_OF_WEEK) - 1;
		// 获取上一个月份共有多少天
		int last_month = current_month - 1 == 0 ? 12 : current_month - 1;
		int year = current_month - 1 == 0 ? current_year - 1 : current_year;
		String year_last_month = year + "-" + last_month;
		sdf = new SimpleDateFormat("yyyy-MM");
		try {
			calendar.setTime(sdf.parse(year_last_month));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int number_of_last_month_days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		print_writer.print("<div class=\"container\"><div class=\"row\"><div class=\"col-sm\">");
		print_writer.print("</div>");
		print_writer.print("<div class=\"col-sm\">");
		print_writer.print("<table class=\"table table-bordered\"><thead class=\"thead-dark\"><tr>");
		// 输出表头
		for (int i = 1; i < 8; i++) {

			switch (i) {
			case 1:
				print_writer.print("<th scope=\"col\">");
				print_writer.print("一");
				print_writer.print("</th>");
				break;
			case 2:
				print_writer.print("<th scope=\"col\">");
				print_writer.print("二");
				print_writer.print("</th>");
				break;
			case 3:
				print_writer.print("<th scope=\"col\">");
				print_writer.print("三");
				print_writer.print("</th>");
				break;
			case 4:
				print_writer.print("<th scope=\"col\">");
				print_writer.print("四");
				print_writer.print("</th>");
				break;
			case 5:
				print_writer.print("<th scope=\"col\">");
				print_writer.print("五");
				print_writer.print("</th>");
				break;
			case 6:
				print_writer.print("<th scope=\"col\">");
				print_writer.print("六");
				print_writer.print("</th>");
				break;
			case 7:
				print_writer.print("<th scope=\"col\">");
				print_writer.print("日");
				print_writer.print("</th>");
				break;
			}

		}
		print_writer.print("</tr>");
		print_writer.print("</thead>");
		// 日历的表头绘制完毕
		print_writer.print("<tbody>");
		print_writer.print("<tr>");
		// 上个月的日期标灰
		for (int i = current_month_first_day_week - 1, j = number_of_last_month_days - current_month_first_day_week
				+ 2; i > 0; i--, j++) {
			print_writer.print("<td>");
			print_writer.print("<font color=\"grey\">");
			print_writer.print(j);
			print_writer.print("</font");
			print_writer.print("</td>");
		}
		// 该变量用于记录第一行绘制完毕后，当前月份绘制到了第几天
		int next_line_start = 0;
		for (int i = current_month_first_day_week, j = 1; i < 8; i++) {
			flag = isAttend(j, attendanceDays);
			if (flag) { // 标红
				print_writer.print("<td>");
				print_writer.print("<font color=\"red\">");
				print_writer.print(j++);
				print_writer.print("√");
				print_writer.print("</font>");
				print_writer.print("</td>");
			} else {
				print_writer.print("<td>");
				print_writer.print(j++);
				print_writer.print("</td>");
			}
			next_line_start = j;
		}
		print_writer.print("</tr>");

		int day_in_current_month = next_line_start;
		boolean curren_month_done = false;
		int number_of_remained_blanks = 0;
		int day_in_next_month = 1;
		// 绘制当前月份剩下的日期
		while (true) {
			print_writer.print("<tr>");
			for (int i = 1; i < 8; i++) {

				print_writer.print("<td>");
				flag = isAttend(day_in_current_month, attendanceDays);
				if (flag) {
					// 标红
					print_writer.print("<font color=\"red\">");
					print_writer.print(day_in_current_month++);
					print_writer.print("√");
					print_writer.print("</font>");
					number_of_remained_blanks = 7 - i;
				} else {
					print_writer.print(day_in_current_month++);
					number_of_remained_blanks = 7 - i;
				}
				print_writer.print("</td>");
				if (day_in_current_month > number_of_current_month_days) {
					number_of_remained_blanks = 7 - i;
					curren_month_done = true;
					break;
				}
			}
			if (curren_month_done) {
				for (int i = number_of_remained_blanks; i > 0; i--) {
					print_writer.print("<td>");
					print_writer.print("<font color=\"grey\">");
					print_writer.print(day_in_next_month++);
					print_writer.print("</font");
					print_writer.print("</td>");
				}
				print_writer.print("</tr>");
				break;
			}
			print_writer.print("</tr>");
		}

		// 一共是7行7列，看看还剩余多少行，使用下个月份的日期进行填充
		// 计算之前占用了多少行，因为星期需（1-7）要占一行，所以是6行
		int number_of_remained_rows = 6
				- (current_month_first_day_week - 1 + number_of_current_month_days + number_of_remained_blanks) / 7;
		for (int i = 0; i < number_of_remained_rows; i++) {
			print_writer.print("<tr>");
			for (i = 1; i < 8; i++) {
				print_writer.print("<td>");
				print_writer.print("<font color=\"grey\">");
				print_writer.print(day_in_next_month++);
				print_writer.print("</font");
				print_writer.print("</td>");
			}
			print_writer.print("</tr>");
		}
		print_writer.print("</tbody>");
		print_writer.print("</table>");
		print_writer.print("</div>");
		print_writer.print("<div class=\"col-sm\">");
		print_writer.print("</div>");
		print_writer.print("</div>");
		print_writer.print("</div>");
	}

	// 判断这一天是否在打卡记录中
	private boolean isAttend(int j, List<Date> attendanceDays) {
		Iterator<Date> it = attendanceDays.iterator();
		Date cur = new Date();
		while (it.hasNext()) { // 遍历本月打卡记录
			cur = it.next();
			if (j == cur.getDate())
				return true;
		}
		return false;
	}

}