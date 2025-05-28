package com.lib.servlet;

import com.lib.dao.BookDao;
import com.lib.entities.Book;
import com.lib.helper.Connectionprovider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String edition = request.getParameter("edition");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Book updatedBook = new Book();
        updatedBook.setId(id);
        updatedBook.setName(name);
        updatedBook.setAuthor(author);
        updatedBook.setEdition(edition);
        updatedBook.setQuantity(quantity);

        BookDao dao = new BookDao(Connectionprovider.getConnection());
        boolean updated = dao.updateBook(updatedBook);

        if (updated) {
            response.sendRedirect("update-book.jsp");
        } else {
            response.getWriter().println("Failed to update book.");
        }
    }
}
