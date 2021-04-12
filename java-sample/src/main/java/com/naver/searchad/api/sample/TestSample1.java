package com.naver.searchad.api.sample;

import java.util.Properties;
import java.util.Scanner;

import com.mashape.unirest.request.HttpRequest;
import com.naver.searchad.api.model.TestResKeysTool;
import com.naver.searchad.api.util.PropertiesLoader;
import com.naver.searchad.api.util.RestClient;

public class TestSample1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			Scanner sc = new Scanner(System.in);
			Properties properties = PropertiesLoader.fromResource("sample.properties");
			String baseUrl = properties.getProperty("BASE_URL");
			String apiKey = properties.getProperty("API_KEY");
			String secretKey = properties.getProperty("SECRET_KEY");
			long customerId = Long.parseLong(properties.getProperty("CUSTOMER_ID"));

			RestClient rest = RestClient.of(baseUrl, apiKey, secretKey);

			HttpRequest request = rest.get("/keywordstool", customerId).queryString("hintKeywords", sc.next())
					.queryString("showDetail", 1);
			rest.asObject(request.asString(), TestResKeysTool.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
