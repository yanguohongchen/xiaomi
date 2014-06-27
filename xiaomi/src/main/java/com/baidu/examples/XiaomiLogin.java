package com.baidu.examples;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class XiaomiLogin {

	
	public static void main(String[] args) throws IOException, URISyntaxException {
		
		  BasicCookieStore cookieStore = new BasicCookieStore();
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setDefaultCookieStore(cookieStore)
	                .build();
		   HttpUriRequest login = RequestBuilder.post()
                   .setUri(new URI("http://localhost:8080/hadoop_result_info/login"))
                   .addParameter("username", "中文")
                   .addParameter("passwd", "password")
                   .build();
           CloseableHttpResponse response2 = httpclient.execute(login);
           Header[] heads = response2.getAllHeaders();
           System.out.println("--------------------------");
           System.out.println("回传值头信息：");
           for(Header head:heads){
           	System.out.println(head);
           }
           System.out.println("--------------------------");
           try {
               HttpEntity entity = response2.getEntity();

               System.out.println("Login form get: " + response2.getStatusLine());
               
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
		
	}
	
}
