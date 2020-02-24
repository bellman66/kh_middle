package com.kh.middle.api.op_Api.model;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.kh.middle.bean.api.opNet.avgSidoPrice_obj;
import com.kh.middle.bean.api.opNet.avgSigunPrice_obj;

public class avgSigunPrice {
	
	private URL url = null;
	private XmlPullParserFactory factory = null;
	private XmlPullParser xpp = null;
	private BufferedInputStream bis = null;
	
	private static SetUrl seturl = new SetUrl();
	 
	public avgSigunPrice() throws Exception {
		this.factory = XmlPullParserFactory.newInstance();
        this.factory.setNamespaceAware(true);
        this.xpp = factory.newPullParser();
	}
	
	public static void main(String[] args) throws Exception {
		avgSigunPrice asp = new avgSigunPrice();
		ArrayList<avgSigunPrice_obj> ww = asp.getavgSigunPrice("01");
	}
	
	// url입력시 세팅 메소드.
	private void SettingParser(URL url) throws Exception {
        this.bis = new BufferedInputStream(url.openStream());
        this.xpp.setInput(bis, "utf-8");
	}

	// ======================================================================
	// 1. 평균가격 주유소.
	
	public ArrayList<avgSigunPrice_obj> getavgSigunPrice(String sido) throws Exception {	

        URL url = new URL(seturl.avgSigunPriceURL("Json" , sido ));	// Json으로 뽑아냄.
 
        SettingParser(url);	// url로 세팅.
        
        ArrayList<avgSigunPrice_obj> list = new ArrayList<avgSigunPrice_obj>();
        
        int event_type = xpp.getEventType();
        String tag_name = "";
        
        String SIGUNCD = "";
   	 	String SIGUNNM = "";
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
					list.add(new avgSigunPrice_obj(SIGUNCD , 
												   SIGUNNM ,
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
					if (tag_name.equals("SIGUNCD")) {
						SIGUNCD = s_copy;
					} else if (tag_name.equals("SIGUNNM")) {
						SIGUNNM = s_copy;
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
	
	private void printList(ArrayList<avgSigunPrice_obj> list){
		int cnt = 1;
        for(avgSigunPrice_obj entity : list){
        	
            System.out.println(cnt +  " : " + entity);
            cnt++;
        }
    }
}
