package repository.model;

public class Sales {
	private Integer id;
	private String date;
	private String product;
	private Integer price;
	private Integer qty;
	private String city;
	private String branch;
	
	public Sales() {
		
	}
	
	public Sales(String date, String product, Integer price, Integer qty, String city, String branch) {
		this.date = date;
		this.product = product;
		this.price = price;
		this.qty = qty;
		this.city = city;
		this.branch = branch;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "Sales [id=" + id + ", date=" + date + ", product=" + product + ", price=" + price + ", qty=" + qty
				+ ", city=" + city + ", branch=" + branch + "]";
	}

	
	
}