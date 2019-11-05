package rtn26hcdrivers;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class HttpDriver {
	public Response response;
	private final DataDriver data;
	
	public HttpDriver() {
		this.data = new DataDriver();
	}
	
	public void sendRequest(String url, String method, String body) {
		long elapsedTime = 0;
    	try {
   		
    		URI uri = new URI(url);            
    		HttpClientBuilder builder =  HttpClientBuilder.create();            
            builder.useSystemProperties();
            HttpClient client = builder.build();
            Object httpRequest = null;
            HttpResponse response = null;
            
            //adds method and body
            switch (method){        	
        		case "GET": httpRequest = new HttpGet(uri); break;
				case "DELETE": httpRequest = new HttpDelete(uri); break;
				case "PATCH": {
					httpRequest = new HttpPatch(uri);
					StringEntity entity = new StringEntity(body);
					((HttpEntityEnclosingRequestBase) httpRequest).setEntity(entity);
					((AbstractHttpMessage) httpRequest).setHeader("Content-Type", "application/json");
					break;
				}
				case "POST": {
					httpRequest = new HttpPost(uri);
					StringEntity entity = new StringEntity(body);
					((HttpEntityEnclosingRequestBase) httpRequest).setEntity(entity);
					((AbstractHttpMessage) httpRequest).setHeader("Content-Type", "application/json");
					break;
				}
				case "PUT": {
					httpRequest = new HttpPut(uri); 
					StringEntity entity = new StringEntity(body);
					((HttpEntityEnclosingRequestBase) httpRequest).setEntity(entity);
					((AbstractHttpMessage) httpRequest).setHeader("Content-Type", "application/json");
					break;
				}
				default: break;
            }
            
            //adds default header
            ((AbstractHttpMessage) httpRequest).setHeader("Accept", "application/json");
    		
    		//Sends request and measures time
            data.logLine("");
            data.logLine("HTTP Request: " + httpRequest.toString());
            if (body != null)
            	data.logLine("HTTP Request Body: " + body);
    		long startTime = System.currentTimeMillis();
    		response = client.execute((HttpUriRequest) httpRequest);
    		elapsedTime = System.currentTimeMillis() - startTime;
			data.logLine("HTTP Response Time: " + elapsedTime);
            
			HttpEntity entity = response.getEntity();
			this.response = saveResponseContent(elapsedTime, response, entity);
			
			data.logLine("HTTP Response code: " + this.response.statusCode);
            data.logLine("HTTP Response body: " + this.response.body);
		} catch (Exception e) {
			data.logLine("Exception occured during HTTP call: " + e);
		}
	}
	
	private Response saveResponseContent(long elapsedTime, HttpResponse response, HttpEntity entity) throws IOException {
		Response resp;
		String responseStatsusDescription = response.getStatusLine().getReasonPhrase();		
		int responseStatusCode = response.getStatusLine().getStatusCode();            		
		
		String responseContentType = null;
		int responseLenght = 0;
		String responseBody = "";
		
		try {responseContentType = entity.getContentType().toString();} catch (NullPointerException e) {}
		try {responseLenght = (int)entity.getContentLength();} catch (NullPointerException e) {}				
		if(entity!= null) {
			try {responseBody = EntityUtils.toString(entity,"UTF-8");} catch (NullPointerException e) {}	
		}
		int requestResponseTime = (int)elapsedTime;                     
		resp = new Response(responseStatusCode, responseStatsusDescription, responseContentType, responseLenght, requestResponseTime, responseBody);
		return resp;
	}
}
