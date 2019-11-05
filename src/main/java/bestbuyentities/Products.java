package bestbuyentities;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import rtn26hcdrivers.RTn26Base;

public class Products extends RTn26Base{
	private String productMethod = "/products";
	public ProductObject product;
	public List<ProductObject> productsList;
	public int total = 0;
	public int limit = 0;
	public int skip = 0;
		
	public Products() {
	}
	
	public String getProducts(String parameters) {
		http.sendRequest(bbApiBaseUrl + productMethod + parameters, "GET", null);
		
		if (http.response.statusCode == 200) {
			String body = http.response.body;
			JSONObject productsJSON = (JSONObject) new JSONTokener(body).nextValue();
			total = productsJSON.getInt("total");
			limit = productsJSON.getInt("limit");
			skip = productsJSON.getInt("skip");
			JSONArray data = productsJSON.getJSONArray("data");
			productsList = new ArrayList<>();
			
			for (int index = 0; index < data.length(); index ++) {
				productsList.add(new ProductObject(data.getJSONObject(index)));
			}
			return "200";
		}
		return parseResponseError(http.response.statusCode, http.response.body);
	}
	
	public String getProducts(int limit, int skip) {
		String parameters = "?$limit=" + limit + "&$skip=" + skip;
		return getProducts(parameters);
	}
	
	public String getProductByID(long id) {
		return getProductByID(String.valueOf(id));
	}
	
	public String getProductByID(String id) {
		http.sendRequest(bbApiBaseUrl + productMethod + "/" + id, "GET", null);
		
		if (http.response.statusCode == 200) {
			String body = http.response.body;
			JSONObject productJSON = (JSONObject) new JSONTokener(body).nextValue();
			this.product = new ProductObject(productJSON);
			return "200";
		}
		return parseResponseError(http.response.statusCode, http.response.body);
	}
	
	public String addProduct(ProductObject product) {
		http.sendRequest(bbApiBaseUrl + productMethod, "POST", product.getProductJSONObject().toString());
				
		if (http.response.statusCode == 201) {
			String body = http.response.body;
			JSONObject productJSON = (JSONObject) new JSONTokener(body).nextValue();
			this.product = new ProductObject(productJSON);
			return "201";
		}
		return parseResponseError(http.response.statusCode, http.response.body);
	}
	
	public String deleteProductByID(long id) {
		return deleteProductByID(String.valueOf(id));
	}
	
	public String deleteProductByID(String id) {
		http.sendRequest(bbApiBaseUrl + productMethod + "/" + id, "DELETE", null);
		
		if (http.response.statusCode == 200) {
			this.product = null;
			return "200";
		}
		return parseResponseError(http.response.statusCode, http.response.body);
	}
	
	public ProductObject generateRandomProductObject() {
		String name = data.randomAlphabeticalString(100);
		String type = data.randomAlphabeticalString(30); 
		double price = data.randomNumber(10000);
		double shipping = data.randomNumber(1000);
		String upc = data.randomAlphabeticalString(15);
		String description = data.randomAlphabeticalString(100);
		String manufacturer = data.randomAlphabeticalString(50);
		String model = data.randomAlphabeticalString(25);
		String url = data.randomAlphabeticalString(500);
		String image = data.randomAlphabeticalString(500);
		return new ProductObject(name,  type, price, shipping, upc, description, manufacturer, model, url, image);
	}
	//returns errors list in case response code is 400
	//or Response Code
	public String parseResponseError(int statusCode, String body) {
		switch (statusCode) {
		case 400: {
			if (body.equals("invalid_json"))
				return body;
			JSONObject response = (JSONObject) new JSONTokener(body).nextValue();
			return response.get("errors").toString();
		}
		case 401:
			return "401";
		case 403:
			return "403";
		case 404:
			return "404";
		default:
			return "unknown error";
		}
	}
	
}
