package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * .../servlet/shoppingcar?name=coffee&coffee_price=100&coffee_price=80&name=cake&cake_price=50&cake_price=40&cake_price=30
 * 請印出總共買了那些商品, 每個商品各買了多少, 總金額為多少
 * 
 * 商品:coffee 數量:2 小計:180
 * 商品:cake 數量:3 小計:120
 * 總金額: 300
 * 
 * 注意: 有可能只買 coffee 沒有買 cake 或 coffee 與 cake 都沒買的狀況
 * 
 * 分析參數:
 * name=coffee
 * coffee_price=100
 * coffee_price=80
 * name=cake
 * cake_price=50
 * cake_price=40
 * cake_price=30
 * */

@WebServlet(value = {"/servlet/shoppingcar", "/servlet/shopping/shoppingcar"})
public class ShoppingCarServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 設定編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8"); // 給瀏覽器看的
		
		PrintWriter out = resp.getWriter();
				
		String[] names = req.getParameterValues("name");
		int total = 0; // 總金額
		StringBuilder sb = new StringBuilder();
		sb.append("購物車資料如下<br>");
		if(names != null && names.length > 0) {
			
			for(String name : names) { // coffee 與 cake
				String[] prices = req.getParameterValues(name + "_price");
				if(prices != null) {
					int subTotal = Arrays.stream(prices).mapToInt(Integer::parseInt).sum(); // 小計
					sb.append(String.format("商品: %s 數量: %d 小計: %d<br>", name, prices.length, subTotal));
					total += subTotal;
				}
			}
			
			sb.append(String.format("總金額: %d", total));
			out.print(sb);
		} else {
			out.print("購物車無資料");
		}
		
	}
	
}