package com.kh.middle.bean.customer;

import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	
	//PageMaker : �������� �����ϰ�, ����� �ϴ� Ŭ����
	
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

	/* �� �Խñ� ���� �����Ҷ� calcData() �޼��带 ȣ���Ͽ� ����¡ ���� ��ư ��� */
  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
    calcData();
  }

  /* ����¡�� ��ư���� �����ϴ� ������ �������. �� ������ ��ȣ, ���� ������ ��ȣ, ����, ���� ��ư���� ����
   * @cri.getPage() ���� ������ ��ȣ
   * @cri.getPerPageNum() : �� �������� ������ �Խñ��� ����
   * �� ������ ��ȣ = (���� ������ ��ȣ / ȭ�鿡 ������ ������ ��ȣ�� ����) * ȭ�鿡 ������ ������ ��ȣ�� ����
   * ������ ������ ��ȣ = �� �Խñ� �� / �� �������� ������ �Խñ��� ����
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
   *  �˻� ���ǰ� �˻� Ű���� ó��
   *  URI �ڵ� ���� �޼���
   *  �������� ��������ȣ, �˻� Ÿ�԰� Ű���� ������ URI�� �پ ����.
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

  /* �˻� Ű���� ���ڵ� ó�� */
  public String encoding(String keyword) {
    if(keyword == null || keyword.trim().length() == 0) return "";
    try {
      return URLEncoder.encode(keyword, "UTF-8");
    } catch(Exception e) {
      return "";
    }
  }
}
