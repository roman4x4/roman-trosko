package rtn26task3;
import org.testng.annotations.Test;
import bestbuyentities.ProductObject;
import bestbuyentities.Products;
import rtn26hcdrivers.RTn26Base;

public class ProductsApiTests extends RTn26Base{
	private static Products products = new Products();
		
	@Test
	private void testGetProductByID(){
		data.message("Check existing Id returned successfully + validate all fields except categories lists");
		data.equals(products.getProductByID(43900), "200");
		
	    data.equals(products.product.name, "Duracell - AAA Batteries (4-Pack)");
	    data.equals(products.product.type, "HardGood");
	    data.equals(products.product.price, 5.49);
	    data.equals(products.product.upc, "041333424019");
	    data.equals(products.product.shipping, 0.0);
	    data.equals(products.product.description, "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack");
	    data.equals(products.product.manufacturer, "Duracell");
	    data.equals(products.product.url, "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC");
	    data.equals(products.product.image, "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg");
	    	    
	    data.message("Check search with not existing Ids");
	    data.equals(products.getProductByID(439000000), "404");
	    data.equals(products.getProductByID(-1), "404");
	    data.equals(products.getProductByID(0), "404");
	    data.equals(products.getProductByID("sometext"), "404");
	}
	
	@Test
	private void testAddProductNameValidations(){
		//generates values with max length for Strings
		String nameMaxLength = data.randomAlphabeticalString(100);
		ProductObject product = products.generateRandomProductObject();

		data.message("Check field validation errors");
		product.name = nameMaxLength + "X";
		data.contains(products.addProduct(product), "'name' should NOT be longer than 100 characters");
		product.name = "";
		data.contains(products.addProduct(product), "'name' should NOT be shorter than 1 characters");
		product.name = " ";
		data.equals(products.addProduct(product), "201");
		data.message("/n/n!!!!!Currently it is allowed to add name with single space, to check business requirements if it is allowed!!!!!/n/n");
		product.name = null;
		data.contains(products.addProduct(product), "should have required property 'name'");
		data.message("Check 'name' field validations");
		
		data.message("Check allowed 'name' values (min, max, special symbols)");
		product.name = "A";//minlength
		data.equals(products.addProduct(product), "201");
		product.name = "%& :as Ä ?//n доклад";//specialsymbols
		data.equals(products.addProduct(product), "201");
		product.name = nameMaxLength;//maxlength
		data.equals(products.addProduct(product), "201");
	}
	
	@Test
	private void testDeleteProduct(){
		ProductObject product = products.generateRandomProductObject();
		
		data.message("Add product");
		products.addProduct(product);
		int productID = products.product.id;
		
		data.message("Check product is added");
		data.equals(products.getProductByID(productID), "200");
		
		data.message("Delete product");
		data.equals(products.deleteProductByID(productID), "200");
		
		data.message("Check product is deleted");
		data.equals(products.getProductByID(productID), "404");
		
		data.message("Delete product with not existing id");
		data.equals(products.deleteProductByID(productID), "404");
		
		data.message("Delete product with empty id");
		//data.equals(products.deleteProductByID(""), "404");
		data.message("Disabled test step as this call deletes all records, no requirements if it expected behavior");
	}
	
	@Test
	private void testGetProducts(){
		data.message("Check call without parameters returns 10 records");
		products.getProducts("");
		data.equals(products.total > 50000);
		data.equals(products.limit, 10);
		data.equals(products.skip, 0);
		data.equals(products.productsList.size(), 10);
		int ProductId10th = products.productsList.get(9).id;
		
		data.message("Check call without parameters returns 15 records skipping first 8 records");
		products.getProducts(15, 8);
		data.equals(products.total > 50000);
		data.equals(products.limit, 15);
		data.equals(products.skip, 8);
		data.equals(products.productsList.size(), 15);
		data.message("Check product that was 10th in previous search is 2nd in current search as we skipped 8 records");
		data.equals(products.productsList.get(1).id, ProductId10th);
	}
}
