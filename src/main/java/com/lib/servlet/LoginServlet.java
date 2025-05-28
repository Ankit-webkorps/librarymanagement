package com.lib.servlet;

import java.io.IOException;

import com.lib.dao.UserDao;
import com.lib.entities.User;
import com.lib.helper.Connectionprovider;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String membershipNumber = request.getParameter("membershipNumber");
		String password = request.getParameter("password");

		UserDao userDao = new UserDao(Connectionprovider.getConnection());
		User user = userDao.loginUser(membershipNumber, password);

		if (user != null) {

			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("studentId", user.getId());

			if ("Admin".equalsIgnoreCase(user.getRole())) {
				response.sendRedirect("admin-dashboard.jsp");
			} else {
				response.sendRedirect("student-dashboard.jsp");
			}
		} else {
			request.setAttribute("error", "Invalid Membership Number or Password");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
