package com.kh.middle.api.op_Api.model;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.kh.middle.bean.api.opNet.aroundAll_obj;
import com.kh.middle.bean.api.opNet.avgSigunPrice_obj;

public class aroundAll {

	
	private URL url = null;
	private XmlPullParserFactory factory = null;
	private XmlPullParser xpp = null;
	private BufferedInputStream bis = null;
	
	private static SetUrl seturl = new SetUrl();
	 
	public aroundAll() throws Exception {
		this.factory = XmlPullParserFactory.newInstance();
        this.factory.setNamespaceAware(true);
        this.xpp = factory.newPullParser();
	}
	
	public static void main(String[] args) throws Exception {
		aroundAll al = new aroundAll();
		al.getaroundAll(315179.00000 , 544445.00000);
	}
	
	// url입력시 세팅 메소드.
	private void SettingParser(URL url) throws Exception {
        this.bis = new BufferedInputStream(url.openStream());
        this.xpp.setInput(bis, "utf-8");
	}

	// ======================================================================
	// 1. 평균가격 주유소.
	
	public ArrayList<aroundAll_obj> getaroundAll(double x , double y) throws Exception {	

        URL url = new URL(seturl.aroundAll("Json" , x , y ));	// Json으로 뽑아냄.
 
        SettingParser(url);	// url로 세팅.
        
        ArrayList<aroundAll_obj> list = new ArrayList<aroundAll_obj>();
        
        int event_type = xpp.getEventType();
        String tag_name = "";
        
        String UNI_ID = "";
   	 	String POLL_DIV_CO = "";
   	 	String OS_NM = "";
   	 	int PRICE = 0;
   	 	float DISTANCE = 0;
   	 	double GIS_X_COOR = 0;
   	 	double GIS_Y_COOR = 0;

    	while(event_type != xpp.END_DOCUMENT) {
    		
    		switch (event_type) {
    		
			case XmlPullParser.START_TAG:
				tag_name = xpp.getName().trim();
				break;
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.END_TAG:
				if(xpp.getName().equals("OIL")) {
					list.add(new aroundAll_obj(UNI_ID , 
							POLL_DIV_CO ,
							OS_NM ,
							PRICE ,
							DISTANCE ,
							GIS_X_COOR ,
							GIS_Y_COOR));
				}
				break;
			case XmlPullParser.END_DOCUMENT:
				break;
			case XmlPullParser.TEXT:
				String s_copy = new String(String.valueOf(xpp.getText().trim()));
				
				if (s_copy.length() > 0) 
				{
					if (tag_name.equals("UNI_ID")) {
						UNI_ID = s_copy;
					} else if (tag_name.equals("POLL_DIV_CO")) {
						POLL_DIV_CO = s_copy;
					} else if (tag_name.equals("OS_NM")) {
						OS_NM = s_copy;
					} else if (tag_name.equals("PRICE")) {
						PRICE = Integer.parseInt(s_copy);
					} else if (tag_name.equals("DISTANCE")) {
						DISTANCE = Float.parseFloat(s_copy);
					} else if (tag_name.equals("GIS_X_COOR")) {
						GIS_X_COOR = Double.parseDouble(s_copy);
					} else if (tag_name.equals("GIS_Y_COOR")) {
						GIS_Y_COOR = Double.parseDouble(s_copy);
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
	
	private void printList(ArrayList<aroundAll_obj> list){
		int cnt = 1;
        for(aroundAll_obj entity : list){
        	
            System.out.println(cnt +  " : " + entity);
            cnt++;
        }
    }

}
