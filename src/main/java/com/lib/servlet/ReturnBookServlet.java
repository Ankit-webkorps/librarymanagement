package com.lib.servlet;

import java.io.IOException;

import com.lib.dao.BookDao;
import com.lib.dao.StudentDao;
import com.lib.helper.Connectionprovider;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int issueId = Integer.parseInt(request.getParameter("issueId"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		StudentDao studentDao = new StudentDao();
		BookDao bookDao = new BookDao(Connectionprovider.getConnection());

		boolean success = studentDao.returnBook(issueId);
		if (success) {
			bookDao.increaseQuantity(bookId, quantity);
			response.sendRedirect("return-book.jsp");

		} else {
			response.getWriter().println("Failed to return book.");
		}
	}
}
