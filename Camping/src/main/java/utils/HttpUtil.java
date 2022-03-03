package utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
	 * @param targetURL JSP�� action�� �ش��ϴ� url
	 * @param parameters JSP�� form ���ο� ���� input �Ķ���͵�
	 * @return
	 */
	public static String sendRequest(String targetURL, List<NameValuePair> parameters) {
		  String strResponse ="";
		  try {
			  HttpClient httpClient = HttpClients.createDefault(); // ��ü����
			  HttpPost post = new HttpPost(targetURL); // post Method ����
			  post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8")); // Request form ����
			  
			  HttpResponse response = httpClient.execute(post); // submit
			  HttpEntity entity = response.getEntity();  // ���� ����� HttpEntity ��ü�� ���·� �ޱ�
			  strResponse = EntityUtils.toString(entity, "UTF-8"); // HttpEntity�� String���·� ��ȯ
		    //Create connection
		  }
		  catch(Exception ex) {
			  ex.printStackTrace();
		  }
		  
		  return strResponse;
		}
}
