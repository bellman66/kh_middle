package com.kh.middle.bean.api.opNet;

public class avgRecentPrice_obj {

	private String DATE ;
	private String PRODCD;
	private String PRICE;
	
	public avgRecentPrice_obj() {
		// TODO Auto-generated constructor stub
		this.DATE = "";
		this.PRODCD = "";
		this.PRICE = "";
	}

	public avgRecentPrice_obj(String dATE, String pRODCD, String pRICE) {
		super();
		DATE = dATE;
		PRODCD = pRODCD;
		PRICE = pRICE;
	}

	@Override
	public String toString() {
		return "avgRecentPrice_obj [DATE=" + DATE + ", PRODCD=" + PRODCD + ", PRICE=" + PRICE + "]";
	}

	public String getDATE() {
		return DATE;
	}

	public void setDATE(String dATE) {
		DATE = dATE;
	}

	public String getPRODCD() {
		return PRODCD;
	}

	public void setPRODCD(String pRODCD) {
		PRODCD = pRODCD;
	}

	public String getPRICE() {
		return PRICE;
	}

	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}
	
}
