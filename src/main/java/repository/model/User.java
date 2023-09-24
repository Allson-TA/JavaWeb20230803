package repository.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class User {
	
	private Integer id; // 序號
	private String username; // 帳號
	private String password; // 密碼
	private Date birth; // 生日
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public User() {
		
	}
	
	public User(Integer id, String username, String password, Date birth) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.birth = birth;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public String getBirthFormat() {
		return sdf.format(birth);
	}
	
	public int getAge() {
		return getYear(new Date()) - getYear(birth);
	}
	
	private int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", birth=" + birth + "]";
	}
	
	
	
}