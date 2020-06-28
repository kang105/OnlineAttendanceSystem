package com.web;

import java.util.List;

/**
 * 分页辅助类
 * 
 * @author liuShuai
 * @version 1.0
 */
public class PaginationSupport<T> {

	/**
	 * 页面默认大小
	 */
	public final static int PAGESIZE = 10;

	/**
	 * 页面大小
	 */
	private int pageSize = PAGESIZE;

	/**
	 * 全部记录
	 */
	private List<T> items;

	/**
	 * 总页面数
	 */
	private int totalCount;

	/**
	 * 每页起始记录索引数组
	 */
	private int[] indexes = new int[0];

	/**
	 * 起始记录索引
	 */
	private int startIndex = 0;

	/**
	 * 创建分页构造方法。
	 * 
	 * @param items
	 *            数据记录。
	 * @param totalCount
	 *            总记录数。
	 */
	public PaginationSupport(List<T> items, int totalCount) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(0);
	}

	/**
	 * 创建分页构造方法。
	 * 
	 * @param items
	 *            数据记录。
	 * @param totalCount
	 *            总记录数。
	 * @param startIndex
	 *            起始记录索引。
	 */
	public PaginationSupport(List<T> items, int totalCount, int startIndex) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(startIndex);
	}

	/**
	 * 创建分页构造方法。
	 * 
	 * @param items
	 *            数据记录。
	 * @param totalCount
	 *            总记录数。
	 * @param pageSize
	 *            页面大小。
	 * @param startIndex
	 *            起始记录索引。
	 */
	public PaginationSupport(List<T> items, int totalCount, int pageSize, int startIndex) {
		setPageSize(pageSize);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(startIndex);
	}

	/**
	 * 将页码转换为列表的startIndex，页大小为默认大小。
	 * 
	 * @param pageNo
	 *            页码。
	 * @return 起始记录索引号。
	 */
	public static int convertFromPageToStartIndex(int pageNo) {
		return (pageNo - 1) * PAGESIZE;
	}

	/**
	 * 将页码转换为列表的startIndex。
	 * 
	 * @param pageNo
	 *            页码。
	 * @param pageSize
	 *            页面大小。
	 * @return 起始记录索引号。
	 */
	public static int convertFromPageToStartIndex(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 取得全部数据记录。
	 * 
	 * @return 全部数据记录。
	 */
	public List<T> getItems() {
		return items;
	}

	/**
	 * 设置全部数据记录为指定值。
	 * 
	 * @param items
	 *            全部数据记录。
	 */
	public void setItems(List<T> items) {
		this.items = items;
	}

	/**
	 * 取得页面大小。
	 * 
	 * @return 页面大小。
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置页面大小为指定值。
	 * 
	 * @param pageSize
	 *            页面大小。
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 取得总记录数。
	 * 
	 * @return 总记录数。
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置数据总数为指定值，并计算各页起始位置。
	 * 
	 * @param totalCount
	 *            总记录数
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			int count = totalCount / pageSize;
			if (totalCount % pageSize > 0)
				count++;
			indexes = new int[count];
			for (int i = 0; i < count; i++) {
				indexes[i] = pageSize * i;
			}
		} else {
			this.totalCount = 0;
		}
	}

	/**
	 * 取得每页起始记录索引数组。
	 * 
	 * @return 每页起始记录索引数组。
	 */
	public int[] getIndexes() {
		return indexes;
	}

	/**
	 * 设置每页起始记录索引数组为指定值。
	 * 
	 * @param indexes
	 *            每页起始记录索引数组。
	 */
	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}

	/**
	 * 取得起始记录索引。
	 * 
	 * @return 起始记录索引
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * 设置起始记录索引为指定值，设置当前起始位置。
	 * 
	 * @param startIndex
	 *            起始记录索引。
	 */
	public void setStartIndex(int startIndex) {
		if (totalCount <= 0)
			this.startIndex = 0;
		else if (startIndex >= totalCount)
			this.startIndex = indexes[indexes.length - 1];
		else if (startIndex < 0)
			this.startIndex = 0;
		else {
			this.startIndex = indexes[startIndex / pageSize];
		}
	}

	/**
	 * 获得下页起始位置。
	 * 
	 * @return 下页起始记录索引。
	 */
	public int getNextIndex() {
		int nextIndex = getStartIndex() + pageSize;
		if (nextIndex >= totalCount)
			return getStartIndex();
		return nextIndex;
	}

	/**
	 * 获得上页起始位置。
	 * 
	 * @return 上页起始记录索引。
	 */
	public int getPreviousIndex() {
		int previousIndex = getStartIndex() - pageSize;
		if (previousIndex < 0)
			return 0;
		return previousIndex;
	}

	/**
	 * 取总页数。
	 * 
	 * @return 总页数。
	 */
	public long getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		return totalCount / pageSize + 1;
	}

	/**
	 * 取该页当前页码,页码从1开始。
	 * 
	 * @return 当前页码。
	 */
	public long getCurrentPageNo() {
		return startIndex / pageSize + 1;
	}

	/**
	 * 该页是否有下一页。
	 * 
	 * @return true有下一页，false没有下一页。
	 */
	public boolean isNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount();
	}

	/**
	 * 该页是否有上一页。
	 * 
	 * @return true有上一页，false没有上一页。
	 */
	public boolean isPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}
}
