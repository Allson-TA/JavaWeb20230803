package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//	網址: ..../servlet/lotto?count=5 表示要得到5個幸運數字	(1~10的幸運數字) 不重複
//	網址: ..../servlet/lotto?count=2 表示要得到2個幸運數字	(1~10的幸運數字) 不重複
//	網址: ..../servlet/lotto?count=0 表示要得到1個幸運數字	(1~10的幸運數字)
//	網址: ..../servlet/lotto?count=1 表示要得到1個幸運數字	(1~10的幸運數字)
//	網址: ..../servlet/lotto		    表示要得到1個幸運數字	(1~10的幸運數字)

@WebServlet(urlPatterns = "/servlet/lotto")
public class LottoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//	1. 設定編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");	//	文件格式，給瀏覽器看的
		//	resp.setContentType("text/plain;charset=UTF-8");	//	plain : 會變成純文字方式呈現
			
		PrintWriter out = resp.getWriter();	//	回應給瀏覽器物件
			
		//	2. 取得 counStr 的參數	(一律只能用字串接收)
		String counStr = req.getParameter("count");
		int count = 1;	//	預設最少都有一個數字
		
		if(counStr != null) {
			count = Integer.parseInt(counStr);
			if(count == 0) {	//	假設是0
				count = 1;
			}
		}
        
		//	3. 取得count 個(1~10)不重覆數字
		Random random = new Random();
		Set<Integer> nums = new LinkedHashSet<>();	//	有排序
		while(nums.size() < count) {	//	size : 集合內容的長度-->因為不超過10
			nums.add(random.nextInt(10)+1);	//	0~10
		}
		
		//	4. 回應
		//	String.format -->跟printf一樣
        out.print(String.format("%d 個幸運數字 %s",count,nums));


			
	}
	
	
	
	
}
