package utils;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	/**
	 * HttpRequest
	 * @param targetURL JSP의 action에 해당하는 url
	 * @param parameters JSP의 form 내부에 들어가는 input 파라미터들
	 * @return
	 */
	public String sendRequest(String targetURL, List<NameValuePair> parameters) {
		  String strResponse ="";
		  try {
			  HttpClient httpClient = HttpClients.createDefault(); // 객체생성
			  HttpPost post = new HttpPost(targetURL); // post Method 설정
			  post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8")); // Request form 설정
			  
			  HttpResponse response = httpClient.execute(post); // submit
			  HttpEntity entity = response.getEntity();  // 응답 결과를 HttpEntity 객체의 형태로 받기
			  strResponse = EntityUtils.toString(entity, "UTF-8"); // HttpEntity를 String형태로 변환
		    //Create connection
		  }
		  catch(Exception ex) {
			  ex.printStackTrace();
		  }
		  
		  return strResponse;
	}
	
}
