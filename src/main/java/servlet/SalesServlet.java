package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.model.Sales;
import service.SalesService;

@WebServlet(urlPatterns = "/servlet/sales")
public class SalesServlet extends HttpServlet {
	private SalesService salesService = new SalesService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Sales> salesList = salesService.findAll();
		
		// 重導到 sales.jsp
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/sales.jsp");
		// 傳遞參數
		req.setAttribute("salesList", salesList);
		rd.forward(req, resp);
		
	}
	
}