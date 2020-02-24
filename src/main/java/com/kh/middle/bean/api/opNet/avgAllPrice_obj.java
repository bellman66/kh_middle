package com.kh.middle.bean.api.opNet;

public class avgAllPrice_obj implements Cloneable{
	
	private String TRADE_DT;
	private String PRODCD;
	private String PRODNM;
	private String PRICE;
	private String DIFF;
	
	public avgAllPrice_obj() {
		// TODO Auto-generated constructor stub
		this.TRADE_DT = "";
		this.PRODCD = "" ;
		this.PRODNM = "" ;
		this.PRICE = "" ;
		this.DIFF = "";
	}

	public avgAllPrice_obj(String tRADE_DT, String pRODCD, String pRODNM, String pRICE, String dIFF) {
		super();
		TRADE_DT = tRADE_DT;
		PRODCD = pRODCD;
		PRODNM = pRODNM;
		PRICE = pRICE;
		DIFF = dIFF;
	}

	@Override
	public String toString() {
		return "avgAllPrice [TRADE_DT=" + TRADE_DT + ", PRODCD=" + PRODCD + ", PRODNM=" + PRODNM + ", PRICE=" + PRICE
				+ ", DIFF=" + DIFF + "]";
	}

	public String getTRADE_DT() {
		return TRADE_DT;
	}

	public void setTRADE_DT(String tRADE_DT) {
		this.TRADE_DT = tRADE_DT;
	}

	public String getPRODCD() {
		return PRODCD;
	}

	public void setPRODCD(String pRODCD) {
		this.PRODCD = pRODCD;
	}

	public String getPRODNM() {
		return PRODNM;
	}

	public void setPRODNM(String pRODNM) {
		this.PRODNM = pRODNM;
	}

	public String getPRICE() {
		return PRICE;
	}

	public void setPRICE(String pRICE) {
		this.PRICE = pRICE;
	}

	public String getDIFF() {
		return DIFF;
	}

	public void setDIFF(String dIFF) {
		this.DIFF = dIFF;
	}
	
	public void removeall() {
		TRADE_DT = "";
		PRODCD = "";
		PRODNM = "";
		PRICE = "";
		DIFF = "";
	}
	
	@Override
	public avgAllPrice_obj clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		avgAllPrice_obj aap = new avgAllPrice_obj();
		
		aap = (avgAllPrice_obj) super.clone();
		// aap = new avgAllPrice(this.TRADE_DT , this.PRODCD , this.PRODNM , this.PRICE , this.DIFF);
		
		return aap;
	}
	
	
}
