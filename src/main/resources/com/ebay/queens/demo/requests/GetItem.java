
public class GetItem {
	// Build your request object
	YourRequestType reqType = new YourRequestType();
	reqType.set...(...)
	reqType.getAttributes.set...(...)
	...etc...
	// Create an object mapper and use it to write your object as a JSON string
	final ObjectMapper mapper = new ObjectMapper();
	StringEntity body = new StringEntity(mapper.writeValueAsString(reqType));
	// Send the request
	HttpPost request = new HttpPost("http://yoururl");
	request.setEntity(body);
	request.addHeader(...);
	request.addHeader(...);
	// Get the response
	HttpResponse response = httpClient.execute(request);
}
