package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import repository.model.User;

public class UserDao {
	
	private static Date getBirth(int yyyy, int mm, int dd) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, yyyy);
		calendar.set(Calendar.MONTH, mm-1); // 0~11
		calendar.set(Calendar.DAY_OF_MONTH, dd);
		return calendar.getTime();
	}
	
	// 查詢單筆
	public User getUserByName(String name) {
		return queryAllUser().parallelStream()
					.filter(user -> user.getUsername().equals(name))
					.findAny()
					.orElse(null);
	}
	
	// 查詢多筆
	public List<User> queryAllUser() {
		List<User> users = new ArrayList();
		// 取得 MySQL 物件
		MySQL mysql = MySQL.getInstance();
		// 進行資料庫連線作業
		String sql = "select id, username, password, birth from users";
		try(Statement stmt = mysql.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) { // 將資料列逐筆取出 
				// 將資料欄裡的資料依序取出
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Date birth = rs.getDate("birth");
				// 並注入到 user 物件
				User user = new User(id, username, password, birth);
				// 加入到 users 集合中
				users.add(user); 
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	// 新增
	public int create(User user) {
		// 取得 MySQL 物件
		MySQL mysql = MySQL.getInstance();
		String sql = "insert into users(username, password, birth) values(?, ?, ?)";
		try(PreparedStatement pstmt = mysql.getConnection().prepareStatement(sql)) {
			
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			// java.util.Date 轉 java.sql.Date 的方法
			pstmt.setDate(3, new java.sql.Date(user.getBirth().getTime()));
			
			int rowcount = pstmt.executeUpdate();
			return rowcount;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}