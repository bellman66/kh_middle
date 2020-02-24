package com.kh.middle.api.op_Api.model;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.kh.middle.bean.api.opNet.avgAllPrice_obj;
import com.kh.middle.bean.api.opNet.avgSidoPrice_obj;

public class avgSidoPrice {
	
	private URL url = null;
	private XmlPullParserFactory factory = null;
	private XmlPullParser xpp = null;
	private BufferedInputStream bis = null;
	
	private static SetUrl seturl = new SetUrl();
	 
	public avgSidoPrice() throws Exception {
		this.factory = XmlPullParserFactory.newInstance();
        this.factory.setNamespaceAware(true);
        this.xpp = factory.newPullParser();
	}
	public static void main(String[] args) throws Exception {
		avgSidoPrice asp = new avgSidoPrice();
		ArrayList<avgSidoPrice_obj> ww = asp.getavgSidoPrice();
	}
	
	// url입력시 세팅 메소드.
	private void SettingParser(URL url) throws Exception {
        this.bis = new BufferedInputStream(url.openStream());
        this.xpp.setInput(bis, "utf-8");
	}

	// ======================================================================
	// 1. 평균가격 주유소.
	
	public ArrayList<avgSidoPrice_obj> getavgSidoPrice() throws Exception {	

        URL url = new URL(seturl.avgSidoPriceURL("Json"));	// Json으로 뽑아냄.
 
        SettingParser(url);	// url로 세팅.
        
        ArrayList<avgSidoPrice_obj> list = new ArrayList<avgSidoPrice_obj>();
        
        int event_type = xpp.getEventType();
        String tag_name = "";
        
        String SIDOCD = "";
   	 	String SIDONM = "";
   	 	String PRODCD = "";
   	 	String PRICE = "";
   	 	String DIFF = "";

    	while(event_type != xpp.END_DOCUMENT) {
    		
    		switch (event_type) {
    		
			case XmlPullParser.START_TAG:
				tag_name = xpp.getName().trim();
				break;
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.END_TAG:
				if(xpp.getName().equals("OIL")) {
					list.add(new avgSidoPrice_obj(SIDOCD , 
												  SIDONM ,
												  PRODCD ,
												  PRICE ,
												  DIFF));
				}
				break;
			case XmlPullParser.END_DOCUMENT:
				break;
			case XmlPullParser.TEXT:
				String s_copy = new String(String.valueOf(xpp.getText().trim()));
				
				if (s_copy.length() > 0) 
				{
					if (tag_name.equals("SIDOCD")) {
						SIDOCD = s_copy;
					} else if (tag_name.equals("SIDONM")) {
						SIDONM = s_copy;
					} else if (tag_name.equals("PRODCD")) {
						PRODCD = s_copy;
					} else if (tag_name.equals("PRICE")) {
						PRICE = s_copy;
					} else if (tag_name.equals("DIFF")) {
						DIFF = s_copy;
					}
				}
				break;
			default:
				break;
			}

    		event_type = xpp.next();
    	}
        
        if(list.size() == 0) {
        	System.out.println("출력 리스트 없음");
        }
        else printList(list);
        
        return list;
	}
	
	private void printList(ArrayList<avgSidoPrice_obj> list){
		int cnt = 1;
        for(avgSidoPrice_obj entity : list){
        	
            System.out.println(cnt +  " : " + entity);
            cnt++;
        }
    }
    
	


}
