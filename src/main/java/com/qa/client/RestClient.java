package com.qa.client;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	
	//1. create a get method
	
public void get(String url) throws ClientProtocolException, IOException {
CloseableHttpClient httpclient=	HttpClients.createDefault();
HttpGet httpget= new HttpGet(url);
CloseableHttpResponse closeablehttpResponce= httpclient.execute(httpget);

//2.Status code
int statusCode=closeablehttpResponce.getStatusLine().getStatusCode();
System.out.println("The staus code==>"+statusCode);

// Json String
String responceString=EntityUtils.toString(closeablehttpResponce.getEntity(), Charset.forName("UTF-8"));
JSONObject responsejson= new JSONObject(responceString);
System.out.println("Responce JSON from API---->"+responsejson);

// All Headers
Header[] headersArray= closeablehttpResponce.getAllHeaders();
HashMap<String, String> allHeaders= new HashMap<String, String>();
for(Header header: headersArray)
{
	allHeaders.put(header.getName(), header.getValue());
}
System.out.println("Headers array----->"+allHeaders);
	}
}
