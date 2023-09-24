package lab.cart.repository.model;

import java.util.Date;

//對應的就是資料表 orders 的欄位
public class Order {
	private Integer id;
	private Integer productId;
	private Integer userId;
	private Integer orderQty;
	private Integer orderStatus;
	private Date orderTs;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getOrderTs() {
		return orderTs;
	}
	public void setOrderTs(Date orderTs) {
		this.orderTs = orderTs;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", productId=" + productId + ", userId=" + userId + ", orderQty=" + orderQty
				+ ", orderStatus=" + orderStatus + ", orderTs=" + orderTs + "]";
	}
	
	
}