package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 利用 SingleTon 模式
public class MySQL {
	
	private static MySQL _instance = new MySQL();
	
	private Connection conn;
	private String dbUrl = "jdbc:mysql://localhost:3306/web?zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Taipei&characterEncoding=utf-8&useUnicode=true";
	private String username = "root";
	private String password = "allson";
	
	private MySQL() {
		try {
			// 1.mysql driver 註冊
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2.建立 mysql 連線
			conn = DriverManager.getConnection(dbUrl, username, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MySQL getInstance() {
		return _instance;
	}
	
	// 取得資料庫連線
	public Connection getConnection() {
		try {
			// 檢查是否連線已經關閉, 若有關閉就重新建立連線
			if(conn.isClosed()) {
				conn = DriverManager.getConnection(dbUrl, username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
}