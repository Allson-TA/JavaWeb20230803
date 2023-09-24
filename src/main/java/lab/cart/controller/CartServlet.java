package lab.cart.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lab.cart.repository.model.Order;
import lab.cart.repository.model.Product;
import lab.cart.service.OrderService;
import lab.cart.service.ProductService;

@WebServlet(value = "/lab/cart")
public class CartServlet extends HttpServlet {
	
	private OrderService orderService = new OrderService();
	private ProductService productService = new ProductService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 取得登入者的 id
		HttpSession session = req.getSession(false);
 		int userId = Integer.parseInt(session.getAttribute("userid")+"");
 		
 		// 取得 action
 		String action = req.getParameter("action") + "";
 		switch (action) {
 			case "view":
 				// 檢視購物車資料
 				List<Order> orders = orderService.findByUserId(userId, 0);
 				List<Product> products = productService.findAll();
 				// 重導到購物車頁面
 				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/lab/cart/cart.jsp");
 				req.setAttribute("orders", orders);
 				req.setAttribute("products", products);
 				rd.forward(req, resp);
				break;
 			case "reduction":
 				reduceProduct(req, resp, userId);
 				break;
 			case "submit":
 				checkOut(req, resp, userId);
 				break;
 			case "empty":
 				emptyCart(req, resp, userId);
 				break;
 			case "add":
 				// 新增商品
 				addProduct(req, resp, userId, "cart.jsp");
 				break;
 			default:
 				// 新增商品
 				addProduct(req, resp, userId, "product.jsp");
 				break;
		}
 		
 		
	}
	
	// 清空購物車
	private void emptyCart(HttpServletRequest req, HttpServletResponse resp, int userId) throws ServletException, IOException {
		orderService.removeAllProductFromCart(userId);
		// 檢視購物車資料
		List<Order> orders = orderService.findByUserId(userId, 0);
		List<Product> products = productService.findAll();
		req.setAttribute("orders", orders);
		req.setAttribute("products", products);
 		req.getRequestDispatcher("/WEB-INF/jsp/lab/cart/cart.jsp").forward(req, resp);
	}
	
	// 結帳
	private void checkOut(HttpServletRequest req, HttpServletResponse resp, int userId) throws ServletException, IOException {
		orderService.checkOut(userId);
		// 檢視購物車資料
		List<Order> orders = orderService.findByUserId(userId, 0);
		List<Product> products = productService.findAll();
		req.setAttribute("orders", orders);
		req.setAttribute("products", products);
 		req.getRequestDispatcher("/WEB-INF/jsp/lab/cart/cart.jsp").forward(req, resp);
	}
	
	// 商品減量
	private void reduceProduct(HttpServletRequest req, HttpServletResponse resp, int userId) throws ServletException, IOException {
		// 取得商品 id
		int productId = Integer.parseInt(req.getParameter("product_id"));
		// 該使用者的購物車中是否有此商品 
		boolean hasProduct = orderService.hasProductInCartByUserId(userId, productId);
		if(hasProduct) {
 			orderService.reduceProductInCart(userId, productId);
 		}
		// 檢視購物車資料
		List<Order> orders = orderService.findByUserId(userId, 0);
		List<Product> products = productService.findAll();
		req.setAttribute("orders", orders);
		req.setAttribute("products", products);
 		req.getRequestDispatcher("/WEB-INF/jsp/lab/cart/cart.jsp").forward(req, resp);
	}
	
	// 新增商品
	private void addProduct(HttpServletRequest req, HttpServletResponse resp, int userId, String path) throws ServletException, IOException {
		// 取得商品 id
 		int productId = Integer.parseInt(req.getParameter("product_id"));
 		// 要購買的數量
 		int qty = 1;
 		
 		// 該使用者的購物車中是否有此商品 
 		boolean hasProduct = orderService.hasProductInCartByUserId(userId, productId);
 		if(hasProduct) {
 			orderService.modifyProductQtyInCartByProductId(userId, productId, qty);
 		} else {
 			orderService.addProductInCart(userId, productId, qty);
 		}
		
 		//resp.getWriter().print("CartServlet OK, Please check database first~");
 		//resp.sendRedirect(getServletContext().getContextPath() + path);
 		// 檢視購物車資料
		List<Order> orders = orderService.findByUserId(userId, 0);
		List<Product> products = productService.findAll();
		req.setAttribute("orders", orders);
		req.setAttribute("products", products);
 		req.getRequestDispatcher("/WEB-INF/jsp/lab/cart/" + path).forward(req, resp);
	}
	
	
	
}