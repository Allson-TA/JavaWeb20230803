package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.model.Sales;
import service.SalesService;

@WebServlet(value = "/servlet/sales/delete")
public class SalesDeleteServlet extends HttpServlet {
	private SalesService salesService = new SalesService();
	
	// 根據 id 刪除紀錄
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		int rowcount = salesService.remove(id);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/salesresponse.jsp");
		req.setAttribute("action", "刪除");
		req.setAttribute("rowcount", rowcount);
		rd.forward(req, resp);
	}
	
	
	
}