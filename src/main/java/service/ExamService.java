package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import repository.ExamDao;
import repository.model.Exam;

public class ExamService {
	
	private ExamDao examDao = new ExamDao();
	
	public int add(String username, String password, String examNo, String examFee, 
				   String paid, String examDate, String[] examPeriods, String memo) {
		// 將方法參數注入到 Exam 物件中 (注意轉型問題)
		Exam exam = new Exam();
		exam.setUsername(username);
		exam.setPassword(password);
		exam.setExamNo(examNo);
		exam.setExamFee(Integer.parseInt(examFee));
		exam.setPaid(Boolean.valueOf(paid));
		// 將 examDate 轉 Date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			exam.setExamDate(sdf.parse(examDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for(String period : examPeriods) {
			switch (period) {
				case "1":
					exam.setExamPeriod1(true);
					break;
				case "2":
					exam.setExamPeriod2(true);
					break;
				case "3":
					exam.setExamPeriod3(true);
					break;	
			}
		}
		exam.setMemo(memo);
		
		int rowcount = examDao.create(exam);
		
		return rowcount;
	}
	
	public List<Exam> queryAll() {
		return examDao.queryAllExam();
	}
	
}