package com.baidu.examples;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author sea
 * 
 */
public class SimpleTest {

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/hadoop_result_info");
		CloseableHttpResponse response = httpclient.execute(httpGet);
		try{
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			System.out.println(entity);
			EntityUtils.consume(entity);
		}finally{
			response.close();
		}
		
		
		
	}

}
