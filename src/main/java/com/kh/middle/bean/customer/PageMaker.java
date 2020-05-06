package com.kh.middle.bean.customer;

import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	
	//PageMaker : 페이지를 생성하고, 계산을 하는 클래스
	
	private Criteria cri;
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = 10;

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public int getTotalCount() {
    return totalCount;
  }

	/* 총 게시글 수를 셋팅할때 calcData() 메서드를 호출하여 페이징 관련 버튼 계산 */
  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
    calcData();
  }

  /* 페이징의 버튼들을 생성하는 계산식을 만들었다. 끝 페이지 번호, 시작 페이지 번호, 이전, 다음 버튼들을 구함
   * @cri.getPage() 현재 페이지 번호
   * @cri.getPerPageNum() : 한 페이지당 보여줄 게시글의 갯수
   * 끝 페이지 번호 = (현재 페이지 번호 / 화면에 보여질 페이지 번호의 갯수) * 화면에 보여질 페이지 번호의 갯수
   * 마지막 페이지 번호 = 총 게시글 수 / 한 페이지당 보여줄 게시글의 갯수
   */
  private void calcData() {
    endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
    int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
    if (endPage > tempEndPage) {
      endPage = tempEndPage;
    }

    startPage = (endPage - displayPageNum) + 1;
    if(startPage <= 0) startPage = 1 ;

    prev = startPage == 1 ? false : true;
    next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
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

  public int getDisplayPageNum() {
      return displayPageNum;
  }

  public void setDisplayPageNum(int displayPageNum) {
      this.displayPageNum = displayPageNum;
  }

  /**
   *  검색 조건과 검색 키워드 처리
   *  URI 자동 생성 메서드
   *  페이지와 페이지번호, 검색 타입과 키워드 조건이 URI에 붙어서 간다.
   */
  public String makeSearch(int page) {
    UriComponents uriComponents = UriComponentsBuilder.newInstance()
      .queryParam("page", page)
      .queryParam("pagePageNum", cri.getPerPageNum())
      .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
      .queryParam("keyword", encoding(((SearchCriteria)cri).getKeyword()))
      .build();

    return uriComponents.toUriString();
  }

  /* 검색 키워드 인코딩 처리 */
  public String encoding(String keyword) {
    if(keyword == null || keyword.trim().length() == 0) return "";
    try {
      return URLEncoder.encode(keyword, "UTF-8");
    } catch(Exception e) {
      return "";
    }
  }
}
