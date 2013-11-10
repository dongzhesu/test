package com.erp.china.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.erp.china.demo.model.User;
import com.erp.china.demo.service.UserService;
import com.erp.china.demo.util.Util;
/** * Servlet implementation class LoginServlet */
public class LoginServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(LoginServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = Util.getParameter(request, "username");
			String password = Util.getParameter(request, "password");
			UserService userService = UserService.getInstance();
			User user = userService.fetchUserLogin(username, password);
			if (user != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("currentUserLogin", user);
				response.sendRedirect("index.jsp"); //logged-in page
			} else response.sendRedirect("login.jsp"); //error page
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}