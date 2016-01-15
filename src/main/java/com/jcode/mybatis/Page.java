package com.jcode.mybatis;

import java.io.Serializable;

public class Page implements Serializable {

	private static final long serialVersionUID = 1L;

	private int currentPage = 1;
	private int pageSize = 22;
	private int totalSize = 0;
	private int totalPage = 0;

	public Page() {
	}

	public Page(int currentPage, int totalSzie) {
		this.currentPage = currentPage;
		this.totalSize = totalSzie;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public int getTotalPage() {
		totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public boolean isHasFirst() {
		if (currentPage == 1) {
			return false;
		}
		return true;
	}

	public boolean isHasPrevious() {
		if (isHasFirst()) {
			return true;
		}
		return false;
	}

	public boolean isHasLast() {
		if (currentPage == getTotalPage()) {
			return false;
		}
		return true;
	}

	public boolean isHasNext() {
		if (isHasLast()) {
			return true;
		}
		return false;
	}
}
