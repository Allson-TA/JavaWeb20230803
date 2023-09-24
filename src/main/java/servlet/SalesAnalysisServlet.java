package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.model.Sales;
import service.SalesService;

@WebServlet(urlPatterns = "/servlet/sales/analysis")
public class SalesAnalysisServlet extends HttpServlet {
	private SalesService salesService = new SalesService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String itemName = req.getParameter("itemName");
		String chartName = req.getParameter("chartName");
		if(itemName == null) itemName = "1";
		if(chartName == null) chartName = "Column";
		
		Map<String, Integer> salesMap = null;
		switch (itemName) {
			default:
			case "1":
				salesMap = salesService.getCitySales();
				break;
			case "2":
				salesMap = salesService.getProductSales();
				break;
			case "3":
				salesMap = salesService.getBranchSales();
				break;	
		}
		
		// 重導到 sales.jsp
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/salesanalysis.jsp");
		// 傳遞參數
		req.setAttribute("salesMap", salesMap);
		req.setAttribute("itemName", itemName);
		req.setAttribute("chartName", chartName);
		rd.forward(req, resp);
		
	}
	
}