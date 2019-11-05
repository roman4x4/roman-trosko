package bestbuyentities;

import org.json.JSONObject;

public class CategoryObject {
	public String id;
	public String name;
	
	public String createdAt;
	public String updatedAt;
	
	public CategoryObject() {
	}
	
	public CategoryObject(JSONObject product) {
		this.id = product.getString("id");
		this.name = product.getString("name");
	}
	
	public JSONObject getProductJSONObject() {
		JSONObject category = new JSONObject();
		category.put("id", this.id);
		category.put("name", this.name);
		return category;
	}
}
