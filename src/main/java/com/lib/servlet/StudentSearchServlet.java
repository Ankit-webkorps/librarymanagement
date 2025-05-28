package com.lib.servlet;


import com.lib.dao.StudentDao;
import com.lib.entities.Book;
import com.lib.helper.Connectionprovider;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/StudentSearchServlet")
public class StudentSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        StudentDao dao = new StudentDao();
        List<Book> books = dao.searchBooks(keyword);

        request.setAttribute("books", books);
        request.getRequestDispatcher("search-books.jsp").forward(request, response);
    }
}

	