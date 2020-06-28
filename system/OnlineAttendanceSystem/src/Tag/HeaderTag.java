package Tag;

import java.io.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;


/**
 * 
 * @author fengjingt 欢迎员工的标签
 */
public class HeaderTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {

		JspFragment jf = this.getJspBody();
		StringWriter sw = new StringWriter();
		jf.invoke(sw); // 将数据写到了 StringWriter 流中
		String contents = sw.getBuffer().toString().trim(); // 得到写入的内容

		String str1 = contents.substring(0, contents.indexOf(","));
		String str2 = contents.substring(str1.length() + 1, contents.length());

		if (str2.trim().isEmpty()) {
			getJspContext().getOut().print("<h2>您还未登陆，请先登陆</h2></br>");
		} else {
			String[] content = contents.split(",");
			//String isAttend = content[0];
			String userName = content[1];

			// if(isAttend.equals("false")){
			// getJspContext().getOut().print("<h2>尊敬的" + userName + "
			// ，欢迎来到员工首页，您今天尚未打卡</h2><br/>");
			// }else{
			// getJspContext().getOut().print("<h2>尊敬的" + userName + "
			// ，欢迎来到员工首页，您今天已打过卡了</h2><br/>");
			// }
			getJspContext().getOut().print("<h2>尊敬的" + userName + " ，欢迎使用网卡考勤系统</h2><br/>");
		}

	}

}
