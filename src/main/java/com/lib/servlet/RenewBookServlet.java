package com.lib.servlet;

import java.io.IOException;

import com.lib.dao.StudentDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RenewBookServlet")
public class RenewBookServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int issuedBookId = Integer.parseInt(request.getParameter("issuedBookId"));

		StudentDao dao = new StudentDao();
		boolean result = dao.renewBook(issuedBookId);

		if (result) {
			response.sendRedirect("renew-book.jsp");
		} else {
			response.sendRedirect("renew-book.jsp");
		}
	}
}
