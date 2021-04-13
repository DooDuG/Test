package com.naver.searchad.api.sample;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.request.HttpRequest;
import com.naver.searchad.api.model.TestResKeysTool;
import com.naver.searchad.api.util.PropertiesLoader;
import com.naver.searchad.api.util.RestClient;

public class TestSample1 {

	public static void main(String[] args) {

		try {

			Scanner sc = new Scanner(System.in);
			String hintKeywords = sc.next();
			Properties properties = PropertiesLoader.fromResource("sample.properties");
			String baseUrl = properties.getProperty("BASE_URL");
			String apiKey = properties.getProperty("API_KEY");
			String secretKey = properties.getProperty("SECRET_KEY");
			long customerId = Long.parseLong(properties.getProperty("CUSTOMER_ID"));

			RestClient rest = RestClient.of(baseUrl, apiKey, secretKey);

			HttpRequest request = rest.get("/keywordstool", customerId).queryString("hintKeywords", hintKeywords)
					.queryString("showDetail", 1);
			rest.asObject(request.asString(), TestResKeysTool.class);

			HttpResponse<String> response = request.asString();
			String responseBody = response.getBody();

			Map<String, Object> totalValue = new ObjectMapper().readValue(responseBody.toString(), Map.class);
			List<Map<String, Object>> items = (List<Map<String, Object>>) totalValue.get("keywordList");

//			for (int i = 0; i < items.size(); i++) {
//				System.out.println(items.get(i).get("monthlyPcQcCnt"));
//			}
			for (Map<String,Object>map:items) {
				System.out.println(map.get("plAvgDepth"));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
