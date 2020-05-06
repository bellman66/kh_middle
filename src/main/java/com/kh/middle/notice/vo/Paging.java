package com.kh.middle.notice.vo;

public class Paging {

	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int allPageCnt;

	private int total;
	private Notice notice;

	private PageDefault pageDefault;
 
	// 매개변수로 객체와 총게시물수
	public Paging(PageDefault pageDefault, int total) {
		this.pageDefault = pageDefault;
		//총 게시물 갯수
		this.total = total;
		
		//총 페이지 갯수
		this.allPageCnt = (int) Math.ceil(total / pageDefault.getAmount());

		// 게시물 번호 /10.0 올림처리후 *10
		// 페이지 갯수 10개씩 보여준다는 전제하에
		this.endPage = (int) Math.ceil(pageDefault.getPageNum() / 10.0) * 10;
		// 끝페이지 - (보여줄 페이지 갯수 - 1)
		this.startPage = this.endPage - 9;
		// 진짜 끝 페이지
		int realEnd = (int) (Math.ceil(total * 1.0) / pageDefault.getAmount());
		if (realEnd <= this.endPage) {
			this.endPage = realEnd;
		}

		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		
	}

	public int getAllPageCnt() {
		return allPageCnt;
	}


	public void setAllPageCnt(int allPageCnt) {
		this.allPageCnt = allPageCnt;
	}



	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public PageDefault pageDefault() {
		return pageDefault;
	}
	

	public PageDefault getPageDefault() {
		return pageDefault;
	}

	public void setPageDefault(PageDefault pageDefault) {
		this.pageDefault = pageDefault;
	}
	
	@Override
	public String toString() {
		return "Paging [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", notice=" + notice + "]";
	}

}
