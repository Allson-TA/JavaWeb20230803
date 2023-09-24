package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/servlet/time","/servlet/today","/servlet/foo*"})
public class TimeServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.print("Time: " + new Date());
		out.close();
		
	}
	
	//	http://localhost:8080/JavaWeb20230803/servlet/time
	//	http://localhost:8080/JavaWeb20230803/servlet/today
	//	http://localhost:8080/JavaWeb20230803/servlet/foo/(星號可以隨便打)
} 
