package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import repository.model.Exam;

public class ExamDao {
	// 新增
	public int create(Exam exam) {
		// 取得 MySQL 物件
		MySQL mysql = MySQL.getInstance();
		String sql = "insert into exams(username, password, exam_no, exam_fee, paid, exam_date, " +
				     "exam_period_1, exam_period_2, exam_period_3, memo) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement pstmt = mysql.getConnection().prepareStatement(sql)) {
			
			pstmt.setString(1, exam.getUsername());
			pstmt.setString(2, exam.getPassword());
			pstmt.setString(3, exam.getExamNo());
			pstmt.setInt(4, exam.getExamFee());
			pstmt.setBoolean(5, exam.getPaid());
			pstmt.setDate(6, new java.sql.Date(exam.getExamDate().getTime()));
			pstmt.setBoolean(7, exam.getExamPeriod1());
			pstmt.setBoolean(8, exam.getExamPeriod2());
			pstmt.setBoolean(9, exam.getExamPeriod3());
			pstmt.setString(10, exam.getMemo());
			
			int rowcount = pstmt.executeUpdate();
			return rowcount;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	// 查詢-多筆
	public List<Exam> queryAllExam() {
		// 取得 MySQL 物件
		MySQL mysql = MySQL.getInstance();
		String sql = "select id, username, password, exam_no, exam_fee, paid, exam_date, " +
					 "exam_period_1, exam_period_2, exam_period_3, memo from exams ";
		try(Statement stat = mysql.getConnection().createStatement();
			ResultSet rs = stat.executeQuery(sql);) {
			
			List<Exam> exams = new ArrayList<>();
			
			while (rs.next()) {
				Exam exam = new Exam();
				exam.setId(rs.getInt("id"));
				exam.setUsername(rs.getString("username"));
				exam.setPassword(rs.getString("password"));
				exam.setExamNo(rs.getString("exam_no"));
				exam.setExamFee(rs.getInt("exam_fee"));
				exam.setPaid(rs.getBoolean("paid"));
				exam.setExamDate(rs.getDate("exam_date"));
				exam.setExamPeriod1(rs.getBoolean("exam_period_1"));
				exam.setExamPeriod2(rs.getBoolean("exam_period_2"));
				exam.setExamPeriod3(rs.getBoolean("exam_period_3"));
				exam.setMemo(rs.getString("memo"));
				// 加入到 exams 集合
				exams.add(exam);
			}
			return exams;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}