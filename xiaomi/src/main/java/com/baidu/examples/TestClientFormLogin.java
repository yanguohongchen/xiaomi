package com.baidu.examples;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestClientFormLogin {

	public static void main(String[] args) throws Exception {
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		try {
			HttpGet httpget = new HttpGet("http://love.91.com/index.php?c=index&a=detail&meeid=644");
			CloseableHttpResponse response1 = httpclient.execute(httpget);
			try {
				HttpEntity entity = response1.getEntity();

				System.out.println("Login form get: " + response1.getStatusLine());
				EntityUtils.consume(entity);

				System.out.println("Initial set of cookies:");
				List<Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						System.out.println("- " + cookies.get(i).toString());
					}
				}

			} finally {
				response1.close();
			}

			// while(true){

			NameValuePair nameValuePair = new BasicNameValuePair("msgid", "644");
			NameValuePair nameValuePair2 = new BasicNameValuePair("content", "周周我爱你");
			List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
			valuePairs.add(nameValuePair);
			valuePairs.add(nameValuePair2);
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(valuePairs, "utf-8");

			HttpUriRequest login = RequestBuilder.post().setUri(new URI("http://love.91.com/index.php?c=index&a=addcomment")).setEntity(formEntity).build();
			CloseableHttpResponse response2 = httpclient.execute(login);
			Header[] heads = response2.getAllHeaders();
			System.out.println("--------------------------");
			System.out.println("回传值头信息：");
			for (Header head : heads) {
				System.out.println(head);
			}

			System.out.println("--------------------------");
			try {
				HttpEntity entity = response2.getEntity();

				System.out.println("Login form get: " + response2.getStatusLine());
				System.out.println(EntityUtils.toString(entity));
				EntityUtils.consume(entity);
				System.out.println("Post logon cookies:");
				List<Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						System.out.println("- " + cookies.get(i).toString());
					}
				}

			} finally {
				response2.close();
			}
			// Thread.sleep(1000*15);
			// }

		} finally {
			httpclient.close();
		}
	}
}
