package com.kh.middle.bean.api.opNet;

public class avgSidoPrice_obj {
	
	private String SIDOCD ;
	private String SIDONM ;
	private String PRODCD ;
	private String PRICE ;
	private String DIFF ;
	
	public avgSidoPrice_obj() {
		// TODO Auto-generated constructor stub
		this.SIDOCD = "";
		this.SIDONM = "";
		this.PRODCD = "";
		this.PRICE = "";
		this.DIFF = "";
	}

	public avgSidoPrice_obj(String sIDOCD, String sIDONM, String pRODCD, String pRICE, String dIFF) {
		super();
		SIDOCD = sIDOCD;
		SIDONM = sIDONM;
		PRODCD = pRODCD;
		PRICE = pRICE;
		DIFF = dIFF;
	}

	public String getSIDOCD() {
		return SIDOCD;
	}

	public void setSIDOCD(String sIDOCD) {
		SIDOCD = sIDOCD;
	}

	public String getSIDONM() {
		return SIDONM;
	}

	public void setSIDONM(String sIDONM) {
		SIDONM = sIDONM;
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

	public String getDIFF() {
		return DIFF;
	}

	public void setDIFF(String dIFF) {
		DIFF = dIFF;
	}

	@Override
	public String toString() {
		return "avgSidoPrice_obj [SIDOCD=" + SIDOCD + ", SIDONM=" + SIDONM + ", PRODCD=" + PRODCD + ", PRICE=" + PRICE
				+ ", DIFF=" + DIFF + "]";
	}


}
