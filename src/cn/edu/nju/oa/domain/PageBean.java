package cn.edu.nju.oa.domain;

import java.util.List;

public class PageBean {

	// 通过页面参数传递
	private int currentPage; // 当前页
	private int pageSize;// 每页显示的条数

	// 查询数据库得出
	private List recordList;// 本页的数据列表
	private int recordCount;// 总记录数

	// 计算得出
	private int beginPageIndex;// 页码列表的开始索引
	private int endPageIndex;// 页码列表的结束索引
	private int pageCount; // 总页数

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setPageNum(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordNum(int recordCount) {
		this.recordCount = recordCount;
	}

	public PageBean(int currentPage, int pageSize, List recordList, int recordNum) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordList = recordList;
		this.recordCount = recordNum;

		pageCount = (recordNum + pageSize - 1) / pageSize;
		// 如果页数不足十页，显示全部
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		} else {
			// 如果超过十页，则显示当前页的前四页与后五页和当前页
			beginPageIndex = currentPage - 4;
			endPageIndex = currentPage + 5;
			// 如果当前页前面不足四页，则显示前十页
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// 如果当前页的后面不足五页，则显示后十页
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 9;
			}
		}

	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

}
