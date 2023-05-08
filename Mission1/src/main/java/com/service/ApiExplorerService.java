package com.service;


import com.dao.wifiDAO;
import com.dto.apiwifiDTO;
import com.dto.wifiDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.sql.Connection;
import java.util.List;

import static com.db.jdbcUtil.*;


public class ApiExplorerService {
    static int totalCnt;


    public int totalCnt() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/76697a5052706f73363669456d7865/json/TbPublicWifiInfo"); /*URL*/

        int startNum = 1;
        int endNum = 1;

        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(startNum), "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(endNum), "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.


        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        JSONObject result = new JSONObject(sb.toString());
        JSONObject total = result.getJSONObject("TbPublicWifiInfo");
        int totalCnt = total.getInt("list_total_count");


        return totalCnt;
    }

    public  int downloadWifi() throws IOException {
        int start = 0;
        int end = 0;
        int total = 100;
        int result=0;
//        total = totalCnt(); //24500개의 정보
        System.out.println(total);

        int pageEnd = total / 1000;
        int EndpageContent = total % 1000;
        int limit = (int) Math.pow(10, 3);

        wifiDAO wifidao =wifiDAO.getInstance();
        Connection con = getConnection();
        wifidao.setConnection(con);

        for (int k = 0; k <= pageEnd; k++) {
            start = limit * k + 1;

            if (k == pageEnd) {
                end = start + EndpageContent - 1;
            } else {
                end = limit * (k + 1);
            }

        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/76697a5052706f73363669456d7865/json/TbPublicWifiInfo"); /*URL*/
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start), "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(end), "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
            System.out.println(start);

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
            BufferedReader rd;

            // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
//            System.out.println(sb.toString());
//            System.out.println(sb);

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
            JsonArray jsonArray = jsonObject.getAsJsonObject("TbPublicWifiInfo").getAsJsonArray("row");
            List<apiwifiDTO> apiwifiDTO = gson.fromJson(jsonArray, new TypeToken<List<apiwifiDTO>>() {}.getType());
            System.out.println(apiwifiDTO);


          result = wifidao.saveDB(apiwifiDTO);

          if (result == 1){
              result = total;
          }else{

          }
        }
        return result;
    }

}