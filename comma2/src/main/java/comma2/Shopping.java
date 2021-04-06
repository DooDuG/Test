package comma2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class Shopping {

	public List<Map<String, Object>> apiAction(String keyword){
		
		String clientId = "ItNN4ZgCWKuWMcUfVm9_"; // 애플리케이션 클라이언트 아이디
		String clientSecret = "8axCKbFPUO"; // 애플리케이션 클라이언트 시크릿

		String search = keyword;
		String text = null;
		try {
			text = URLEncoder.encode(search, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어를 입력해주세요", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/shop.json?display=100&query=" + text; // json 방식
		// String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
		// // xml 방식

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		List<Map<String, Object>> responseBody = get(apiURL, requestHeaders);
		
		return responseBody;
	}

	private static List<Map<String, Object>> get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상응답
				return readBody(con.getInputStream());
			} else { // 에러응답
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static List<Map<String, Object>> readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {

			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}
			// json 
			Map<String, Object> totalValue = new ObjectMapper().readValue(responseBody.toString(), Map.class);
			List<Map<String, Object>> items = (List<Map<String, Object>>) totalValue.get("items");
			System.out.println(totalValue.toString());
			
			
			
			// 각 items에서 key값으로 값 추출
			for (Map<String, Object> map : items) {						
				String title = (String) map.get("title");							
				title = title.replace("<b>", "");
				title = title.replace("</b>", "");
				map.put("title", title);
			}

			//return responseBody.toString();
			return items;
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}

}