package com.qa.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.Base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest extends TestBase {
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	
	@BeforeTest
	public void setUp()
	{
		testBase = new TestBase();
		serviceUrl= prop.getProperty("URL");
		apiUrl= prop.getProperty("serviceURL");
		//https://reqres.in/api/users
		url= serviceUrl+apiUrl;
	}

	@Test
	public void getTest() throws ClientProtocolException, IOException
	{
	    restClient= new RestClient();
		restClient.get(url);
	}
}
