<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("Admin")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="css/mystyle.css">
</head>
<body>  
  <h2>Admin Dashboard</h2>
  <a class="logout-btn" href="LogoutServlet">Logout</a>
  <ul>
    <li><a href="add-book.jsp">Add Book</a></li>
    <li><a href="view-books.jsp">View Books</a></li>
    <li><a href="admin-issued-books.jsp">Issued Book</a></li>
    <li><a href="delete-book.jsp">Delete</a></li>
    <li><a href="update-book.jsp">Update</a></li>
  </ul>
</body>
</html>
