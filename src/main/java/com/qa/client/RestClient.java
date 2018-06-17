package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class RestClient {

	
	//1. create a get method
//1. Get method without headers	
public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
CloseableHttpClient httpclient=	HttpClients.createDefault();
HttpGet httpget= new HttpGet(url);
CloseableHttpResponse closeablehttpResponce= httpclient.execute(httpget);
return closeablehttpResponce;
}

public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
CloseableHttpClient httpclient=	HttpClients.createDefault();
HttpGet httpget= new HttpGet(url);

for(Map.Entry<String, String> entry : headerMap.entrySet()) {
	httpget.addHeader(entry.getKey(), entry.getKey());
}
CloseableHttpResponse closeablehttpResponce= httpclient.execute(httpget);
return closeablehttpResponce;

	}
}
