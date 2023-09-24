package service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import repository.UserDao;
import repository.model.User;

public class UserService {
	
	private UserDao userDao = new UserDao();
	
	// 登入確認
	public User loginCheck(String username, String password) {
		User user = userDao.getUserByName(username); // 透過 username 找到是否有此人
		if(user == null) { // 無此人
			return null;
		} if(user.getPassword().equals(password)) { // 比對 password 是否相等 
			return user; 
		} else { // password 不相等
			return null;
		}
	}
	
	// 取得所有 user
	public List<User> findAllUser() {
		return userDao.queryAllUser();
	}
	
	// 新增
	public int add(String username, String password, String birthStr) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		// 將 birthStr 轉 Date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date birth = sdf.parse(birthStr);
			user.setBirth(birth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int rowcount = userDao.create(user);
		return rowcount;
	}
	
}