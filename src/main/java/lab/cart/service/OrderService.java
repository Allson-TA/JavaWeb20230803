package lab.cart.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lab.cart.repository.OrderDao;
import lab.cart.repository.model.Order;
import repository.model.User;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	
	public List<Order> findByUserId(int userId, int orderStatus) {
		return orderDao.findByUserId(userId, orderStatus);
	}
	
	// 該使用者的購物車中是否有此商品
	public boolean hasProductInCartByUserId(int userId, int productId) {
		return orderDao.hasProductInCartByUserId(userId, productId);
	}
	
	// 新增商品到購物車中
	public boolean addProductInCart(int userId, int productId, int qty) {
		return orderDao.addProductInCart(userId, productId, qty);
	}
	
	// 購物車中的商品減量
	public boolean reduceProductInCart(int userId, int productId) {
		//boolean check = orderDao.reduceProductInCart(userId, productId);
		//orderDao.removeProductFromCartIfOutOfStock();
		//return check;
		return modifyProductQtyInCartByProductId(userId, productId, -1);
	}
	
	// 修改購物車中該商品的數量
	// 根據 qty 的正負值來修正數量
	public boolean modifyProductQtyInCartByProductId(int userId, int productId, int qty) {
		boolean check =  orderDao.modifyProductQtyInCartByProductId(userId, productId, qty);
		if(qty < 0) {
			orderDao.removeProductFromCartIfOutOfStock();
		}
		return check;
	}
	
	// 刪除該使用者購物車的所有資料紀錄
	public void removeAllProductFromCart(int userId) {
		orderDao.removeAllProductFromCart(userId);
	}
	
	// 使用者進行結帳
	public void checkOut(int userId) {
		orderDao.checkOut(userId);
	}
	
}