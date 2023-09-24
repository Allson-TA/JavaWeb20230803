package servlet;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TransferService;

@WebServlet(urlPatterns = "/servlet/transfer")
public class TransferServlet extends HttpServlet {

	//	建立翻譯服務物件
	private TransferService transferService = new TransferService();
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> dict = transferService.findAll();
		// (V) 建立分派器並指向指定 jsp (view)
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/dictlist.jsp");
		// 建立要傳遞給 jsp 的資料
		req.setAttribute("dict", dict);
		// 傳遞
		rd.forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//	得到要翻譯的英文
		String word = req.getParameter("word");

		//	得到要翻譯的中文結果
		//	(M) 透過翻譯服務物件來得到所需要的結果
		String result = transferService.transfer(word);
		
		//	(V)建立分派器指向jsp (view)
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/transferresult.jsp");
		
		//	建立要傳遞給jsp的資料
		req.setAttribute("word", word);
		req.setAttribute("result", result);
		
		//	傳遞
		rd.forward(req, resp);
		
	}
	
	
	
	
}
