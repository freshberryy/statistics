package com.demo.comentoStatistic.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class HolidayService {

    private static final String SERVICE_KEY = "gAWI9ZIY8UrrxtHtTZ%2BpPPGZ3C01BhcRLZYNe78380di6ssw343BnkEMV3GZhoSJUvFhUNV9QMsFnr54UTckmg%3D%3D";
    private static final String API_URL = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo";

    public List<String> getHolidays(int year) {
        List<String> holidays = new ArrayList<>();
        try {
            for (int month = 1; month <= 12; month++) {
                String monthStr = String.format("%02d", month);
                StringBuilder urlBuilder = new StringBuilder(API_URL);
                urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + SERVICE_KEY);
                urlBuilder.append("&" + URLEncoder.encode("solYear", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(year), "UTF-8"));
                urlBuilder.append("&" + URLEncoder.encode("solMonth", "UTF-8") + "=" + URLEncoder.encode(monthStr, "UTF-8"));

                URL url = new URL(urlBuilder.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader rd;
                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
                }

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
                conn.disconnect();

                // xml 파싱
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                InputSource is = new InputSource(new StringReader(sb.toString()));
                Document doc = builder.parse(is);
                NodeList locdateList = doc.getElementsByTagName("locdate");

                for (int i = 0; i < locdateList.getLength(); i++) {
                    String locdate = locdateList.item(i).getTextContent();
                    String formatted = locdate.substring(0,4) + "-" + locdate.substring(4,6) + "-" + locdate.substring(6);
                    holidays.add(formatted);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return holidays;
    }
}

