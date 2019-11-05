package rtn26hcdrivers;

public class Response {
	public int statusCode;
	public String statusDescription;
	public String body;
	public String contentType;
	public int contentLength;
	public int requestResponseTime;
	
	protected Response(int statusCode, String statusDescription, String contentType, int contentLength, int requestResponseTime, String body){
		 this.statusCode = statusCode;
		 this.statusDescription = statusDescription;
		 this.contentType = contentType;
		 this.contentLength = contentLength;
		 this.body = body;
		 this.requestResponseTime = requestResponseTime;
	}	
}
