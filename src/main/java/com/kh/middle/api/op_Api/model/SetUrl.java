package com.kh.middle.api.op_Api.model;

public class SetUrl {
	
	private final static String INIT_URL = "http://www.opinet.co.kr/api/";
	private final static String KEY = "F691200220";
	
	public String avgAllPriceURL(String out) {
		//	 xml , json 둘 중하나.
        String url = INIT_URL + "avgAllPrice.do?"
        					  + "code=" + KEY 
        					  + "&out=" + out;

        return url;
    }

	public String avgSidoPriceURL(String out) {
		//   xml , json 둘 중하나.
        String url = INIT_URL + "avgSidoPrice.do?"
        			 		  + "code=" + KEY 
        			 		  + "&out=" + out;

        return url;
	}

	public String avgSigunPriceURL(String out , String sido) {
		// TODO Auto-generated method stub
		String url = INIT_URL + "avgSigunPrice.do?"
							  + "code=" + KEY 
							  + "&out=" + out
							  + "&sido=" + sido;

		return url;
	}

	public String avgRecentPriceURL(String out) {
		// TODO Auto-generated method stub
		String url = INIT_URL + "avgRecentPrice.do?"
		 		  + "code=" + KEY 
		 		  + "&out=" + out;

		return url;
	}
}
