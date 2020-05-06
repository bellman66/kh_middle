package com.kh.middle.bean.customer;

/* Criteria
 * Ư�� ������ ��ȸ�� ���� Ŭ����
 */
public class Criteria {

	/* ���� ������ ��ȣ */
	private int page;

	/* �� �������� ������ �Խñ��� ���� */
	private int perPageNum;

	/* Ư�� �������� �Խñ� ���۹�ȣ, �Խñ� ���� �� ��ȣ */
	/* ���� �������� �Խñ� ���� ��ȣ = (���� ������ ��ȣ - 1) * �������� ������ �Խñ� ���� */
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}

	/* �����ڷ� ������ ��ȣ��, �������� ������ �Խñ��� ���� �ʱ�ȭ */
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int pageCount) {
		int cnt = this.perPageNum;
		if (pageCount != cnt) {
			this.perPageNum = cnt;
		} else {
			this.perPageNum = pageCount;
		}
	}
}
