package lab.cart.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.cart.repository.model.Product;
import lab.cart.service.ProductService;

// 確認商品庫存量的 Filter
@WebFilter(value = "/lab/cart")
public class CheckProductStockFilter extends HttpFilter {
	
	private ProductService productService = new ProductService();
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String action = request.getParameter("action") + "";
		int productId = 0;
		if(request.getParameter("product_id") != null) {
			productId = Integer.parseInt(request.getParameter("product_id"));
		}
		String errorMsg = null;
		switch (action) {
			case "add": // 增量
			case "null": // 沒有傳入 action 參數
				// 取得商品庫存量
				int productQty = getProductStockById(productId);
				System.out.println("productQty = " +productQty);
				// 確認是否有足夠的庫存量 ?
				if(productQty < 1) { // 庫存不足
					errorMsg = "庫存不足";
				}
			break;
		}
		
		if(errorMsg == null) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("/WEB-INF/jsp/lab/cart/error.jsp").forward(request, response);
		}
	}
	
	// 確認商品庫存量
	private int getProductStockById(int productId) {
		Product product = productService.getProductById(productId);
		return product == null ? 0 : product.getProductQty();
	}
	
	
	
}