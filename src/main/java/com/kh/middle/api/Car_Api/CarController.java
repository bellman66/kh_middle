package com.kh.middle.api.Car_Api;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.Arrays;


public class CarController {

    @Autowired
    protected SqlSessionTemplate sqlsession;

//    public String selectData() throws Exception {
//
//        String serviceTest = "";
//
//        serviceTest = DB_Dao.selectData();
//
//        return serviceTest;
//    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Youn\\Desktop\\개발 폴더\\csv_file\\Car_Info.csv";

        try {
            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fis , "MS949");
            BufferedReader br = new BufferedReader(isr);

            String value;
            while( (value = br.readLine()) != null){
                String array[] = value.split(",");

                for (int i = 0 ; i < array.length ; i++) {
                    array[i] = array[i].replaceAll("%" , "\"").trim();
                }
                System.out.println(Arrays.toString(array));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
