package repository.model;

import java.util.Date;

public class Exam {
	private Integer id;
	private String username;
	private String password;
	private String examNo;
	private Integer examFee;
	private Boolean paid = false;
	private Date examDate;
	private Boolean examPeriod1 = false;
	private Boolean examPeriod2 = false;
	private Boolean examPeriod3 = false;
	private String memo;
	
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
	public String getExamNo() {
		return examNo;
	}
	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}
	public Integer getExamFee() {
		return examFee;
	}
	public void setExamFee(Integer examFee) {
		this.examFee = examFee;
	}
	public Boolean getPaid() {
		return paid;
	}
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public Boolean getExamPeriod1() {
		return examPeriod1;
	}
	public void setExamPeriod1(Boolean examPeriod1) {
		this.examPeriod1 = examPeriod1;
	}
	public Boolean getExamPeriod2() {
		return examPeriod2;
	}
	public void setExamPeriod2(Boolean examPeriod2) {
		this.examPeriod2 = examPeriod2;
	}
	public Boolean getExamPeriod3() {
		return examPeriod3;
	}
	public void setExamPeriod3(Boolean examPeriod3) {
		this.examPeriod3 = examPeriod3;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "Exam [id=" + id + ", username=" + username + ", password=" + password + ", examNo=" + examNo
				+ ", examFee=" + examFee + ", paid=" + paid + ", examDate=" + examDate + ", examPeriod1=" + examPeriod1
				+ ", examPeriod2=" + examPeriod2 + ", examPeriod3=" + examPeriod3 + ", memo=" + memo + "]";
	}
	
	
	
}