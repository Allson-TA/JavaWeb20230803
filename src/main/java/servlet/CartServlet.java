package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(value = {"/servlet/cart", "/servlet/shopping/cart"})
public class CartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String product = req.getParameter("product");
		
		switch (action) {
			case "add": // 新增一筆
				addToCart(req, product);
				resp.sendRedirect("./product");
				break;
			case "empty": // 清除購物車資料
				emptyToCart(req, resp);
				break;	
			case "submit": // 結帳並清除購物車資料
				submitToCart(req, resp);
				break;
			case "reduction": // 減量
				reductionToCart(req, resp, product);
				break;
			case "view": // 查看購物車
				viewCart(req, resp);
				break;
		}
		
	}
	
	private void emptyToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// 取得購物車的 session 紀錄
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
		// 取得商品資訊
		Map<String, Integer> products = (Map<String, Integer>)getServletContext().getAttribute("products");
		// 將購物車(cart)的資料加回給商品資訊(products)
		for(Map.Entry<String, Integer> entry : cart.entrySet()) {
			int total = products.get(entry.getKey()) + entry.getValue(); // 加回
			products.put(entry.getKey(), total);
		}
		// 清除購物車資料
		session.removeAttribute("cart");
		req.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(req, resp);
	}
	
	private void reductionToCart(HttpServletRequest req, HttpServletResponse resp, String product) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// 取得購物車的 session 紀錄
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
		int qty = cart.get(product); // 目前該商品的購買數量
		// 購買的商品數量 -1
		cart.put(product, qty-1);
		// 商品庫存數量 + 1
		Map<String, Integer> products = (Map<String, Integer>)getServletContext().getAttribute("products");
		int currentQty = products.get(product); // 目前該商品的庫存
		products.put(product, currentQty+1); // 將指定商品庫存 +1
		// 在判斷一次該商品的購買數量, 若為 0 則從購物車中移除
		if(cart.get(product) == 0) {
			cart.remove(product);
		}
		
		req.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(req, resp);
	}
	
	private void submitToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// 將 session 全部清除
		//session.invalidate(); // 通常該指令用於登出功能較多
		// 只清除購物車資訊
		session.removeAttribute("cart");
		
		req.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(req, resp);
	}
	
	private void viewCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 建立 Session 若 session 已經存在則會繼續使用而不會重新創建
		HttpSession session = req.getSession();
		// 取得購物車的 session 紀錄
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
		
		req.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(req, resp);
	}
	
	private void addToCart(HttpServletRequest req, String product) {
		// 檢查庫存
		Map<String, Integer> products = (Map<String, Integer>)getServletContext().getAttribute("products");
		int currentQty = products.get(product); // 目前該商品的庫存
		if(currentQty <= 0) {
			return;
		}
		//--------------------------------------------------------------------------------------------------
		// 建立 Session 若 session 已經存在則會繼續使用而不會重新創建
		HttpSession session = req.getSession();
		// 取得購物車的 session 紀錄
		// Map<String, Integer> <商品代號, 購買數量>
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
		// 判斷是否有 cart
		if(cart == null) {
			cart = new LinkedHashMap<>();
		}
		// 將商品放入到購物車中
		int qty = cart.getOrDefault(product, 0); // 取得該商品之前是否已有購買的數量,若無則得到0
		qty = qty + 1; // 累加購買一筆
		// 將最數量回寫到 cart 中
		cart.put(product, qty);
		// 將 cart 寫入到 session 變數中
		session.setAttribute("cart", cart);
		//--------------------------------------------------------------------------------------------------
		
		// 將指定商品庫存 -1
		products.put(product, currentQty-1); // 將指定商品庫存 -1
		
	}
	
	
	
}