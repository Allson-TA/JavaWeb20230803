package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import repository.model.Sales;

public class SalesDao {
	private Connection conn;
	
	public SalesDao() {
		MySQL mySQL = MySQL.getInstance();
		conn = mySQL.getConnection();
	}
	
	// 查詢所有 branch
	public List<String> queryAllBranch() {
		List<String> branches = new ArrayList<>();
		String sql = "select distinct branch from sales_data where branch is not null";
		try(Statement stmt = MySQL.getInstance().getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);) {
			
			while (rs.next()) {
				branches.add(rs.getString("branch"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return branches;
	}
	
	// 查詢所有 city
	public List<String> queryAllCity() {
		List<String> cities = new ArrayList<>();
		String sql = "select distinct city from sales_data where city is not null";
		try(Statement stmt = MySQL.getInstance().getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);) {
			
			while (rs.next()) {
				cities.add(rs.getString("city"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cities;
	}
	
	// 查詢所有 product
	public List<String> queryAllProduct() {
		List<String> products = new ArrayList<>();
		String sql = "select distinct product from sales_data where product is not null";
		try(Statement stmt = MySQL.getInstance().getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);) {
			
			while (rs.next()) {
				products.add(rs.getString("product"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	// 查詢所有資料
	public List<Sales> queryAll() {
		// 收集所有 sales_data 資料的容器
		List<Sales> salesList = new ArrayList<>();
		
		String sql = "select id, date, product, price, qty, city, branch from sales_data";
		try(Statement stmt = MySQL.getInstance().getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);) {
			
			// 將資料逐筆抓出並加入到 salesList 中
			while (rs.next()) {
				Sales sales = new Sales();
				sales.setId(rs.getInt("id"));
				sales.setDate(rs.getString("date"));
				sales.setProduct(rs.getString("product"));
				sales.setPrice(rs.getInt("price"));
				sales.setQty(rs.getInt("qty"));
				sales.setCity(rs.getString("city"));
				sales.setBranch(rs.getString("branch"));
				// 加入到集合
				salesList.add(sales);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return salesList;
	}
	
	// 查詢單筆(根據id)
	public Sales getById(Integer id) {
		Sales sales = null;
		String sql = "select id, date, product, price, qty, city, branch from sales_data where id=?";
		try(PreparedStatement pstmt = MySQL.getInstance().getConnection().prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) { // 是否有找到紀錄
				// 將紀錄資料注入到 Sales 物件中
				sales = new Sales();
				sales.setId(rs.getInt("id"));
				sales.setDate(rs.getString("date"));
				sales.setProduct(rs.getString("product"));
				sales.setPrice(rs.getInt("price"));
				sales.setQty(rs.getInt("qty"));
				sales.setCity(rs.getString("city"));
				sales.setBranch(rs.getString("branch"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sales;
	}
	
	// 新增
	public int create(Sales sales) {
		String sql = "insert into sales_data(date, product, price, qty, city, branch) values(?, ?, ?, ?, ?, ?)";
		int rowcount = 0;
		try(PreparedStatement pstmt = MySQL.getInstance().getConnection().prepareStatement(sql);) {
			pstmt.setString(1, sales.getDate());
			pstmt.setString(2, sales.getProduct());
			pstmt.setInt(3, sales.getPrice());
			pstmt.setInt(4, sales.getQty());
			pstmt.setString(5, sales.getCity());
			pstmt.setString(6, sales.getBranch());
			rowcount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowcount;
	}
	
	// 修改
	public int update(Sales sales) {
		String sql = "update sales_data set date=?, product=?, price=?, qty=?, city=?, branch=? where id=?";
		int rowcount = 0;
		try(PreparedStatement pstmt = MySQL.getInstance().getConnection().prepareStatement(sql);) {
			pstmt.setString(1, sales.getDate());
			pstmt.setString(2, sales.getProduct());
			pstmt.setInt(3, sales.getPrice());
			pstmt.setInt(4, sales.getQty());
			pstmt.setString(5, sales.getCity());
			pstmt.setString(6, sales.getBranch());
			pstmt.setInt(7, sales.getId());
			rowcount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowcount;
	}
	
	// 刪除
	public int delete(Integer id) { 
		String sql = "delete from sales_data where id=?";
		int rowcount = 0;
		try(PreparedStatement pstmt = MySQL.getInstance().getConnection().prepareStatement(sql);) {
			pstmt.setInt(1, id);
			rowcount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowcount;
	}
}
