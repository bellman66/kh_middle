package com.kh.middle.bean.api.opNet;

public class aroundAll_obj {
	
	private String UNI_ID ;
	private String POLL_DIV_CO;
	private String OS_NM;
	private int PRICE ;
	private float DISTANCE;
	private double GIS_X_COOR;
	private double GIS_Y_COOR;
	
	public aroundAll_obj() {
		// TODO Auto-generated constructor stub
		UNI_ID = "";
		POLL_DIV_CO = "";
		OS_NM = "";
		PRICE = 0;
		DISTANCE = 0;
		GIS_X_COOR = 0;
		GIS_Y_COOR = 0;
	}

	
	public aroundAll_obj(String uNI_ID, String pOLL_DIV_CO, String oS_NM, int pRICE, float dISTANCE, double gIS_X_COOR,
			double gIS_Y_COOR) {
		super();
		UNI_ID = uNI_ID;
		POLL_DIV_CO = pOLL_DIV_CO;
		OS_NM = oS_NM;
		PRICE = pRICE;
		DISTANCE = dISTANCE;
		GIS_X_COOR = gIS_X_COOR;
		GIS_Y_COOR = gIS_Y_COOR;
	}


	@Override
	public String toString() {
		return "aroundAll_obj [UNI_ID=" + UNI_ID + ", POLL_DIV_CO=" + POLL_DIV_CO + ", OS_NM=" + OS_NM + ", PRICE="
				+ PRICE + ", DISTANCE=" + DISTANCE + ", GIS_X_COOR=" + GIS_X_COOR + ", GIS_Y_COOR=" + GIS_Y_COOR + "]";
	}

	public String getUNI_ID() {
		return UNI_ID;
	}

	public void setUNI_ID(String uNI_ID) {
		UNI_ID = uNI_ID;
	}

	public String getPOLL_DIV_CD() {
		return POLL_DIV_CO;
	}

	public void setPOLL_DIV_CD(String pOLL_DIV_CD) {
		POLL_DIV_CO = pOLL_DIV_CD;
	}

	public String getOS_NM() {
		return OS_NM;
	}

	public void setOS_NM(String oS_NM) {
		OS_NM = oS_NM;
	}

	public int getPRICE() {
		return PRICE;
	}

	public void setPRICE(int pRICE) {
		PRICE = pRICE;
	}

	public float getDISTANCE() {
		return DISTANCE;
	}

	public void setDISTANCE(float dISTANCE) {
		DISTANCE = dISTANCE;
	}

	public double getGIS_X_COOR() {
		return GIS_X_COOR;
	}

	public void setGIS_X_COOR(double gIS_X_COOR) {
		GIS_X_COOR = gIS_X_COOR;
	}

	public double getGIS_Y_COOR() {
		return GIS_Y_COOR;
	}

	public void setGIS_Y_COOR(double gIS_Y_COOR) {
		GIS_Y_COOR = gIS_Y_COOR;
	}

	
}
