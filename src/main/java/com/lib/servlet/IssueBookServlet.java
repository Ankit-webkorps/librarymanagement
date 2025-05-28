package com.lib.servlet;

import com.lib.dao.StudentDao;
import com.lib.dao.BookDao;
import com.lib.entities.Book;
import com.lib.helper.Connectionprovider;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/IssueBookServlet")
public class IssueBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			int bookId = Integer.parseInt(request.getParameter("bookId"));
			int studentId = Integer.parseInt(request.getParameter("studentId"));
			String returnDateStr = request.getParameter("returnDate");
			int quantity = Integer.parseInt(request.getParameter("quantity"));

			LocalDate returnDate = LocalDate.parse(returnDateStr);
			LocalDate currentDate = LocalDate.now();

			if (!returnDate.isAfter(currentDate)) {
				response.getWriter().println("Return date must be after today's date.");
				return;
			}

			BookDao bookDao = new BookDao(Connectionprovider.getConnection());
			StudentDao studentDao = new StudentDao();

			Book book = bookDao.getBookById(bookId);

			if (book != null) {
				if (book.getQuantity() >= quantity) {
					boolean result = studentDao.issueBook(bookId, studentId, returnDateStr, quantity);
					if (result) {
						bookDao.updateQuantity(bookId, book.getQuantity() - quantity);
						response.sendRedirect("issue-book.jsp");
					} else {
						response.getWriter().println("Failed to issue book. Please try again.");
					}
				} else {
					response.getWriter().println("Not enough books available to issue.");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Error: " + e.getMessage());
		}
	}
}
