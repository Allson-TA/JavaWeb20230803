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

@WebServlet(value = "/servlet/sales/update")
public class SalesUpdateServlet extends HttpServlet {
	private SalesService salesService = new SalesService();
	// 根據 id 得到要修改的紀錄
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Sales sales = salesService.getSales(id);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/salesupdate.jsp");
		// 將 Sales 物件傳遞
		req.setAttribute("sales", sales);
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
		String id = req.getParameter("id");
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
		int rowcount = salesService.update(id, date, product, price, qty, city, branch);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/salesresponse.jsp");
		req.setAttribute("action", "修改");
		req.setAttribute("rowcount", rowcount);
		rd.forward(req, resp);
	}
	
	
	
}