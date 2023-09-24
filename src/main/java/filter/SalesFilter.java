package filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repository.model.User;
import service.UserService;

@WebFilter(value = {"/servlet/sales/*", "/servlet/shopping/*", "/lab/*"})
public class SalesFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username != null && password != null) {
			// 有帶 username 與 password 的參數
			case2(request, response, chain, username, password);
		} else {
			// 沒有帶 username 與 password 的參數
			case1(request, response, chain);
		}
		
	}
	
	// 有帶 username 與 password 的參數
	private void case2(HttpServletRequest request, HttpServletResponse response, FilterChain chain, 
						String username, String password) throws IOException, ServletException {
		UserService userService = new UserService();
		User user = userService.loginCheck(username, password);
		boolean pass = user != null; 
		if(pass) {
			// 建立 session 並將 true 設定給 login_status
			HttpSession session = request.getSession(); // 相當於 getSession(true)
			session.setAttribute("login_status", "true");
			session.setAttribute("username", username);
			session.setAttribute("userid", user.getId());
			
			// 放行
			if("POST".equalsIgnoreCase(request.getMethod())) {
				HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request) {
					@Override
					public String getMethod() {
						return "GET";
					}
				};
				chain.doFilter(wrapper, response);
			} else {
				chain.doFilter(request, response);
			}
			
		} else {
			// 導向登入頁面給前端
			request.getRequestDispatcher("/WEB-INF/jsp/salesloginform.jsp").forward(request, response);
		}
	}
	
	// 沒有帶 username 與 password 的參數
	private void case1(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		// 判斷是否有登入的 session 屬性資料
		HttpSession session = request.getSession(false);
		// 取得 session 的登入狀態紀錄 login_status
		if(session != null) { 
			String login_status = session.getAttribute("login_status") + "";
			if(login_status.equals("true")) {
				chain.doFilter(request, response); // 放行
			} else {
				// 導向登入頁面給前端
				request.getRequestDispatcher("/WEB-INF/jsp/salesloginform.jsp").forward(request, response);
			}
		} else {
			// 導向登入頁面給前端
			request.getRequestDispatcher("/WEB-INF/jsp/salesloginform.jsp").forward(request, response);
		}
	}
	
}