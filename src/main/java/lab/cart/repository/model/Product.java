package lab.cart.repository.model;

// 對應的就是資料表 products 的欄位
public class Product {
	private Integer id;
	private String productCode;
	private String productName;
	private Integer productQty;
	private Integer productPrice;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductQty() {
		return productQty;
	}
	public void setProductQty(Integer productQty) {
		this.productQty = productQty;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productCode=" + productCode + ", productName=" + productName + ", productQty="
				+ productQty + ", productPrice=" + productPrice + "]";
	}
	
	
}