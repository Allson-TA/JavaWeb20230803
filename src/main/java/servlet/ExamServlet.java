package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ExamService;

@WebServlet("/servlet/exam")
public class ExamServlet extends HttpServlet {
	
	private ExamService examService = new ExamService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8"); // 給瀏覽器看的
		
		// 重導到查詢頁
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/examview.jsp");
		req.setAttribute("exams", examService.queryAll());
		rd.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 設定編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8"); // 給瀏覽器看的
		
		// 設定回應物件
		PrintWriter out = resp.getWriter();
		
		// 2. 取得參數
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String examNo = req.getParameter("examNo");
		String examFee = req.getParameter("examFee");
		String paid = req.getParameter("paid");
		String examDate = req.getParameter("examDate");
		String[] examPeriods = req.getParameterValues("examPeriod");
		String memo = req.getParameter("memo");
		
		// 3. 傳給 examService 進行資料新增
		int rowcount = examService.add(username, password, examNo, examFee, paid, examDate, examPeriods, memo);
		
		// 4. 重導到 examresult.jsp
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/examresult.jsp");
		req.setAttribute("action", "新增");
		req.setAttribute("result", rowcount);
		rd.forward(req, resp);
		
	}
	
	
	
}