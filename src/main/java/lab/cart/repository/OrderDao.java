package lab.cart.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lab.cart.repository.model.Order;
import repository.MySQL;

public class OrderDao {
	
	public List<Order> findByUserId(int userId, int orderStatus) {
		String sql = "select id, product_id, user_id, order_qty, order_status, order_ts from orders where user_id = ? and order_status = ?";
		List<Order> orders = new ArrayList<>();
		try(PreparedStatement pstmt = MySQL.getInstance().getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, orderStatus);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setProductId(rs.getInt("product_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setOrderQty(rs.getInt("order_qty"));
				order.setOrderStatus(rs.getInt("order_status"));
				order.setOrderTs(rs.getDate("order_ts"));
				
				orders.add(order);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	// 該使用者的購物車中是否有此商品
	public boolean hasProductInCartByUserId(int userId, int productId) {
		String sql = "SELECT count(*) as count FROM orders where user_id = ? and product_id = ? and order_status = 0";
		try(PreparedStatement pstmt = MySQL.getInstance().getConnection().prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, productId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int count = rs.getInt("count");
				return count >= 1;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 購物車中的商品減量
	public boolean reduceProductInCart(int userId, int productId) {
		return modifyProductQtyInCartByProductId(userId, productId, -1);
	}
	
	// 新增商品到購物車中
	public boolean addProductInCart(int userId, int productId, int qty) {
		String sql = "insert into orders(user_id, product_id, order_qty, order_status) values(?, ?, ?, ?)";
		try(PreparedStatement pstmt = MySQL.getInstance().getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, productId);
			pstmt.setInt(3, qty);
			pstmt.setInt(4, 0); // 購物車 = 0, 已結帳 = 1
			
			int rowcount = pstmt.executeUpdate(); 
			return rowcount >= 1;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 修改購物車中該商品的數量
	// 根據 qty 的正負值來修正數量
	public boolean modifyProductQtyInCartByProductId(int userId, int productId, int qty) {
		String sql = "update orders set order_qty = order_qty + ? where user_id = ? and product_id = ? and order_status = 0";
		try(PreparedStatement pstmt = MySQL.getInstance().getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, qty);
			pstmt.setInt(2, userId);
			pstmt.setInt(3, productId);
			
			int rowcount = pstmt.executeUpdate(); 
			return rowcount >= 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 刪除購物車中沒有數量的資料紀錄
	public void removeProductFromCartIfOutOfStock() {
		String sql = "delete from orders where order_qty = 0";
		try(Statement stmt = MySQL.getInstance().getConnection().createStatement()) {
			// 執行刪除
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 刪除該使用者購物車的所有資料紀錄
	public void removeAllProductFromCart(int userId) {
		String sql = "delete from orders where user_id = ? and order_status = 0";
		try(PreparedStatement pstmt = MySQL.getInstance().getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			// 執行刪除
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 使用者進行結帳
	public void checkOut(int userId) {
		String sql = "update orders set order_status = 1 where user_id = ?  and order_status = 0";
		try(PreparedStatement pstmt = MySQL.getInstance().getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			// 執行修改
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}