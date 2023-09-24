package lab.cart.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.cart.repository.model.Product;
import lab.cart.service.ProductService;

@WebServlet(value = "/lab/product")
public class ProductServlet extends HttpServlet {
	
	private ProductService productService = new ProductService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> products = productService.findAll();
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/lab/cart/product.jsp");
		req.setAttribute("products", products);
		rd.forward(req, resp);
	}
	
	
	
}