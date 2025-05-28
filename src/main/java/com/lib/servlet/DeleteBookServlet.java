package com.lib.servlet;

import com.lib.dao.BookDao;
import com.lib.helper.Connectionprovider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        BookDao dao = new BookDao(Connectionprovider.getConnection());

        boolean deleted = dao.deleteBook(bookId);

        if (deleted) {
            response.sendRedirect("delete-book.jsp");
        } else {
            response.getWriter().println("Failed to delete book.");
        }
    }
}
