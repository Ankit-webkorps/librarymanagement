package com.lib.servlet;

import com.lib.dao.BookDao;
import com.lib.entities.Book;
import com.lib.helper.Connectionprovider;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		BookDao dao = new BookDao(Connectionprovider.getConnection());

		if (action.equals("add")) {
			Book book = new Book();
			book.setName(request.getParameter("name"));
			book.setAuthor(request.getParameter("author"));
			book.setEdition(request.getParameter("edition"));
			book.setQuantity(Integer.parseInt(request.getParameter("quantity")));

			if (dao.addBook(book)) {
				response.sendRedirect("admin-dashboard.jsp?msg=Book+added");
			} else {
				response.sendRedirect("admin-dashboard.jsp");
			}

		}
	
}
	}
