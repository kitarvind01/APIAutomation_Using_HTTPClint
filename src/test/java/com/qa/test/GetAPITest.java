package com.qa.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.Base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeablehttpResponce;
	
	@BeforeTest
	public void setUp()
	{
		testBase = new TestBase();
		serviceUrl= prop.getProperty("URL");
		apiUrl= prop.getProperty("serviceURL");
		//https://reqres.in/api/users
		url= serviceUrl+apiUrl;
	}

	@Test(priority=1)
	public void getTestWithoutHeader() throws ClientProtocolException, IOException
	{
	    restClient= new RestClient();
	    closeablehttpResponce=restClient.get(url);
		int statusCode=closeablehttpResponce.getStatusLine().getStatusCode();
		System.out.println("The staus code==>"+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
		
        //----------------------------------------------------------------------------------
		// Json String
		String responceString=EntityUtils.toString(closeablehttpResponce.getEntity(), Charset.forName("UTF-8"));
		JSONObject responsejson= new JSONObject(responceString);
		System.out.println("Responce JSON from API---->"+responsejson);
		String perPageValue=TestUtil.getValueByJPath(responsejson, "/per_page");
		System.out.println("value of per page is====>"+perPageValue);
		Assert.assertEquals(perPageValue,"3");
		
		String totalValue=TestUtil.getValueByJPath(responsejson, "/total");
		System.out.println("value of total is====>"+totalValue);
		Assert.assertEquals(totalValue,"12");
		
		
		String lastName=TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
		String id=TestUtil.getValueByJPath(responsejson, "/data[0]/id");
		String avatar=TestUtil.getValueByJPath(responsejson, "/data[0]/avatar");
		String firstName=TestUtil.getValueByJPath(responsejson, "/data[0]/first_name");
		Assert.assertEquals(lastName, "Bluth");
		Assert.assertEquals(id, "1");
		Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
		Assert.assertEquals(firstName, "George");
		
		//--------------------------------------------------------------------------

		// All Headers
		Header[] headersArray= closeablehttpResponce.getAllHeaders();
		HashMap<String, String> allHeaders= new HashMap<String, String>();
		for(Header header: headersArray)
		{
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers array----->"+allHeaders);
	}



@Test(priority=2)
public void getTestWithHeader() throws ClientProtocolException, IOException
{
    restClient= new RestClient();
    
    HashMap<String, String> headerMap= new HashMap<String, String>();
    headerMap.put("Content-Type", "application/json");
//    headerMap.put("UserId", "arvind@amazon.com");
//    headerMap.put("Password", "arvind@123");
//    headerMap.put("Auth token", "123344");
    //Status code
    closeablehttpResponce=restClient.get(url,headerMap);
	int statusCode=closeablehttpResponce.getStatusLine().getStatusCode();
	System.out.println("The staus code==>"+statusCode);
	Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
	
    //----------------------------------------------------------------------------------
	// Json String
	String responceString=EntityUtils.toString(closeablehttpResponce.getEntity(), Charset.forName("UTF-8"));
	JSONObject responsejson= new JSONObject(responceString);
	System.out.println("Responce JSON from API---->"+responsejson);
	String perPageValue=TestUtil.getValueByJPath(responsejson, "/per_page");
	System.out.println("value of per page is====>"+perPageValue);
	Assert.assertEquals(perPageValue,"3");
	
	String totalValue=TestUtil.getValueByJPath(responsejson, "/total");
	System.out.println("value of total is====>"+totalValue);
	Assert.assertEquals(totalValue,"12");
	
	
	String lastName=TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
	String id=TestUtil.getValueByJPath(responsejson, "/data[0]/id");
	String avatar=TestUtil.getValueByJPath(responsejson, "/data[0]/avatar");
	String firstName=TestUtil.getValueByJPath(responsejson, "/data[0]/first_name");
	Assert.assertEquals(lastName, "Bluth");
	Assert.assertEquals(id, "1");
	Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
	Assert.assertEquals(firstName, "George");
	
	//--------------------------------------------------------------------------

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
