package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.IntSummaryStatistics;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * ..../servlet/many/score 		 	 		  印出:請輸入分數
 * ..../servlet/many/score?score=100 		  印出:總分100，平均100
 * ..../servlet/many/score?score=100&score=80 印出:總分180，平均90
 */

@WebServlet(urlPatterns = "/servlet/many/score")	//	可以自由組合
public class ManyScoreServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//	1. 設定編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");	//	文件格式，給瀏覽器看的
		//	resp.setContentType("text/plain;charset=UTF-8");	//	plain : 會變成純文字方式呈現
			
		PrintWriter out = resp.getWriter();	//	回應給瀏覽器物件(生成新頁面)
			
		//	2. 取得 score 的參數，因為score參數可能會有很多所以要用getParameterValues()
		String[] scores = req.getParameterValues("score");
		if(scores == null) {
			out.print("請在網址列上輸入分數");
			return;
		}
		
		//	3. 計算總分 & 平均 (Java8)
		IntSummaryStatistics stat = Arrays.stream(scores).mapToInt(Integer::parseInt).summaryStatistics();
		out.print(String.format("總分:%d, 平均:%.1f", stat.getSum(), stat.getAverage()));
		
	}

}
