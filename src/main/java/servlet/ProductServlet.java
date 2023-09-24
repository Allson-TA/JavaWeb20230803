package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = {"/servlet/product", "/servlet/shopping/product"})
public class ProductServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		// 初始 ProductServlet 物件時要做的事, 只會執行一次
		Map<String, String> productNames = new LinkedHashMap<>(); // 商品名稱
		productNames.put("P01", "宮保雞丁");
		productNames.put("P02", "番茄炒蛋");
		productNames.put("P03", "糖醋排骨");
		Map<String, Integer> products = new LinkedHashMap<>(); // 商品庫存
		products.put("P01", 10);
		products.put("P02", 20);
		products.put("P03", 30);
		// 將商品資料放入到公有變數中讓每一個人都可以看到最新商品與庫存資訊
		getServletContext().setAttribute("productNames", productNames);
		getServletContext().setAttribute("products", products);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/product.jsp").forward(req, resp);
	}
	
}