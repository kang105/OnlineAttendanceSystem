package com.web;
/**
 * 
 * @author leihaowen
 * 读取部门形成树形图的处理类
 * 将数据库里的department的表格转成树状形式
 * 输出Json格式
 * 部门辅助表单
 *
 */

import java.util.ArrayList;
import java.util.List;

import com.domain.Department;

public class FormatDepartments {
	private List<DepartmentForm> departmentsForm;
	private List<DepartmentForm> backUpDepartmentsForm;

	/**
	 * 将数据库传出来的数据转成List<DepartmentForm>
	 * @param depatrments
	 */
	public FormatDepartments(List<Department> depatrments) {
		List<DepartmentForm> df = new ArrayList<>();
		for (Department d : depatrments) {
			df.add(new DepartmentForm(d));
		}
		this.departmentsForm = df;
		this.backUpDepartmentsForm = df;
	}

	/**
	 * 先找到根id再通过根id处理遍历数据变成树状Json
	 * 
	 * @return 树状Json
	 */
	public String format() {
		long rootId = this.findRootId();

		String data = "";
		data = toJson(data, rootId);

		return data;
	}

	/**
	 * 通过根id==upperId遍历找到根节点
	 * @return 根节点
	 */
	public long findRootId() {
		for (DepartmentForm d : departmentsForm) {
			if (d.getId() == d.getUpperDepartment().getId()) {

				return d.getId();
			}
		}
		return 0;
	}

	/**
	 * 找到结点在第几层
	 * 
	 * @param rootId
	 * @return 最底层的层数
	 */
	public int setDepartmentLevel(long rootId) {

		long[] a = new long[departmentsForm.size() + 1];
		a[0] = rootId;
		int c = 0;
		int index = 1;
		while (!departmentsForm.isEmpty()) {
			int flag = index;
			for (DepartmentForm d : departmentsForm) {
				d.addLevel();
				c++;
				if (dInA(a, d.getUpperDepartment().getId(), index)) {
					a[flag] = d.getId();
					flag++;
					departmentsForm.remove(d);
				}
			}
			index = flag;
		}
		return c;

	}

	/**
	 * 
	 * @param a
	 * @param d
	 * @param index
	 * @return 如果部门d的上级部门在数组a里面，就返回真，否则返回假
	 */
	public boolean dInA(long[] a, long d, int index) {
		boolean flag = false;
		for (int i = 0; i < index; i++) {
			if (d == a[i]) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 将空的字符串和根节点id输入
	 * @param data
	 * @param 输入根结点id upperId
	 * @return 数据最后的json格式
	 */
	public String toJson(String data, long upperId) {

		data = "[{";
		for (DepartmentForm d : backUpDepartmentsForm) {
			if (d.getId() == upperId) {
				
				data += "title:\'";
				data += d.getDepartment();
				data += "\',id:";
				data += d.getId();
		//		if(d.getId() == 1) data += ",disabled: true";
				data = toJson2(data, upperId);

			}
		}
		return data + "}]";
	}

	/**
	 * 使用递归调用，先找子节点，再找兄弟节点，将他们形成Json格式
	 * @param data
	 * @param upperId
	 * @return json格式数据
	 * 
	 */
	public String toJson2(String data, long upperId) {
		boolean flag = false;
		int i = 0;
		for (DepartmentForm d : backUpDepartmentsForm) {
			if (d.getUpperDepartment().getId() == upperId && d.getId() != upperId) {

				flag = true;
			}
		}
		if (flag)
			data += ",children: [";
		for (DepartmentForm d : backUpDepartmentsForm) {
			if (d.getUpperDepartment().getId() == upperId && d.getId() != upperId) {

				if (i == 0) {
					data += "{";
				} else {
					data += ",{";
				}
				i++;
				data += "title:\'";
				data += d.getDepartment();
				data += "\',id:";
				data += d.getId();
				data = toJson2(data, d.getId());
				data += "}";
			}
		}
		if (flag)
			data += "]";
		return data;
	}

}
