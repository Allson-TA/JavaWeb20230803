package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/servlet/bmi")
public class BMIServlet extends HttpServlet {

	private void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 設定編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8"); // 給瀏覽器看的
		
		PrintWriter out = resp.getWriter();
		
		// 2. 取得 h, w 的參數
		String hStr = req.getParameter("h");
		String wStr = req.getParameter("w");
		
		if(hStr == null || wStr == null) {
			// 回應內容
			out.print("請在網址列上輸入身高與體重");
			return;
		}
		
		// 3. 計算 BMI
		double h = Double.parseDouble(hStr);
		double w = Double.parseDouble(wStr);
		double bmi = w / Math.pow(h/100, 2);
		
		// 4. 回應內容
		out.print("BMI = " + bmi);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
}

		

	
		//	http://localhost:8080/JavaWeb20230803/servlet/bmi
		//	要加上? 才會帶參數
		//	http://localhost:8080/JavaWeb20230803/servlet/bmi?h=170&w=60


	

