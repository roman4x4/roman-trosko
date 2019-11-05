package bestbuyentities;

import org.json.JSONObject;

public class ProductObject {
	public int id;
	public String name;
	public String type;
	public double price;
	public double shipping;
	public String upc;
	public String description;
	public String manufacturer;
	public String model;
	public String url;
	public String image;
	
	public String createdAt;
	public String updatedAt;
	
	public ProductObject() {
	}
	
	public ProductObject(String name, String type, double price, double shipping, String upc, String description, String manufacturer, String model, String url, String image) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.shipping = shipping;
		this.upc = upc;
		this.description = description;
		this.manufacturer = manufacturer;
		this.model = model;
		this.url = url;
		this.image = image;
	}
	
	public ProductObject(JSONObject product) {
		this.id = product.getInt("id");
		this.name = product.getString("name");
		this.type = product.getString("type");
		this.price = product.getDouble("price");
		this.shipping = product.getDouble("shipping");
		this.upc = product.getString("upc");
		this.description = product.getString("description");
		this.manufacturer = product.getString("manufacturer");
		this.model = product.getString("model");
		this.url = product.getString("url");
		this.image = product.getString("image");
	}
	
	public JSONObject getProductJSONObject() {
		JSONObject product = new JSONObject();
		product.put("name", this.name);
		product.put("type", this.type);
		product.put("price", this.price);
		product.put("shipping", this.shipping);
		product.put("upc", this.upc);
		product.put("description", this.description);
		product.put("manufacturer", this.manufacturer);
		product.put("model", this.model);
		product.put("url", this.url);
		product.put("image", this.image);
		return product;
	}
}
