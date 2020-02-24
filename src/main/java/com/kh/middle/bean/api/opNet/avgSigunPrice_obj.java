package com.kh.middle.bean.api.opNet;

public class avgSigunPrice_obj {
	
	private String SIGUNCD ;
	private String SIGUNNM ;
	private String PRODCD ;
	private String PRICE ;
	private String DIFF ;
	
	public avgSigunPrice_obj() {
		// TODO Auto-generated constructor stub
		this.SIGUNCD = "";
		this.SIGUNNM = "";
		this.PRODCD = "";
		this.PRICE = "";
		this.DIFF = "";
	}

	public avgSigunPrice_obj(String sIGUNCD, String sIGUNNM, String pRODCD, String pRICE, String dIFF) {
		super();
		this.SIGUNCD = sIGUNCD;
		this.SIGUNNM = sIGUNNM;
		this.PRODCD = pRODCD;
		this.PRICE = pRICE;
		this.DIFF = dIFF;
	}


	@Override
	public String toString() {
		return "avgSigunPrice_obj [SIGUNCD=" + SIGUNCD + ", SIGUNNM=" + SIGUNNM + ", PRODCD=" + PRODCD + ", PRICE="
				+ PRICE + ", DIFF=" + DIFF + "]";
	}

	public String getSIGUNCD() {
		return SIGUNCD;
	}

	public void setSIGUNCD(String sIGUNCD) {
		SIGUNCD = sIGUNCD;
	}

	public String getSIGUNNM() {
		return SIGUNNM;
	}

	public void setSIGUNNM(String sIGUNNM) {
		SIGUNNM = sIGUNNM;
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
	
	
}
