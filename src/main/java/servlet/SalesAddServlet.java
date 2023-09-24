package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.SalesService;

@WebServlet(value = "/servlet/sales/add")
public class SalesAddServlet extends HttpServlet {
	
	private SalesService salesService = new SalesService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/salesadd.jsp");
		// 給jsp網頁的下拉選單使用
		req.setAttribute("products", salesService.findAllItems("product")); // 所有 products
		req.setAttribute("cities", salesService.findAllItems("city")); // 所有 cities
		req.setAttribute("branches", salesService.findAllItems("branch")); // 所有 branches
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String date = req.getParameter("date");
		String product = req.getParameter("product");
		String price = req.getParameter("price");
		String qty = req.getParameter("qty");
		String city = req.getParameter("city");
		String branch = req.getParameter("branch");
		String branch_newname = req.getParameter("branch_newname");
		if(branch.equals("")) {
			branch = branch_newname;
		}
		int rowcount = salesService.add(date, product, price, qty, city, branch);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/salesresponse.jsp");
		req.setAttribute("action", "新增");
		req.setAttribute("rowcount", rowcount);
		rd.forward(req, resp);
		
	}
	
	
}