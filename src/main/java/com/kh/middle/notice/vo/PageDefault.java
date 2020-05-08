package com.kh.middle.notice.vo;

public class PageDefault {

	// 페이지 번호
	// 게시물의 번호
	private int pageNum;
	// 한 페이지에 표현될 게시물 수
	private int amount;
	
	public int getPageStart() {
		return (this.pageNum -1) * this.amount;
	}
 
	public PageDefault() {
		// 기본값 세팅
		this(1, 5);
	}

	public PageDefault(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PagingDefault [pageNum=" + pageNum + ", amount=" + amount + "]";
	}

}
