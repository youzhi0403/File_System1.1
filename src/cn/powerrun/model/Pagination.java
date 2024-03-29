package cn.powerrun.model;

/**
 * 
 * @author Admin
 * 用于完成分页的对象
 */
public class Pagination {
	private int page;    
	private int rows;

	public Pagination(int page, int rows) {
		super();
		this.page = page;
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCurrentPage() {
		return (page - 1) * rows;
	}

}
