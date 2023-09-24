package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.model.User;
import service.UserService;

@WebServlet(urlPatterns = "/servlet/login")
public class LoginServlet extends HttpServlet {
	
	private UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 建立分派器
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/loginform.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 得到前端登入表單傳來的 username 與 password
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		// 判斷登入使否成功 ?
		User user = userService.loginCheck(username, password);
		boolean check = user != null;
		// 建立分派器
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/loginresult.jsp");
		// 傳遞參數
		req.setAttribute("username", username); // 登入者
		req.setAttribute("check", check); // 是否登入成功 ?
		req.setAttribute("users", userService.findAllUser()); // 得到所有 user 資料
		rd.forward(req, resp);
	}
	
	
}