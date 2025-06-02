<%@ page language="java" import="java.util.*, com.lib.entities.*, com.lib.dao.StudentDao, com.lib.helper.Connectionprovider" session="true" %>
<%
User user = (User) session.getAttribute("user");
if (user == null) {
    response.sendRedirect("login.jsp");
    return;
}

int studentId = (int) session.getAttribute("studentId");
    StudentDao dao = new StudentDao(Connectionprovider.getConnection());
    List<IssuedBook> issuedBooks = dao.getIssuedBooks(studentId);
%>
<html lang="en">
<head>
    <title>Issued Books</title>
    <link rel="stylesheet" href="css/mystyle.css">
</head>
<body>
<div class="issued-books-container">
    <a href="student-dashboard.jsp" class="issued-books-back-btn">Back to Dashboard</a>
    <h2 class="issued-books-title">Books Issued</h2>

    <table class="issued-books-table">
        <tr>
            <th>ID</th>
            <th>Book Title</th>
            <th>Issue Date</th>
            <th>Return Date</th>
        </tr>
        <%
            for (IssuedBook book : issuedBooks) {
        %>
        <tr>
            <td><%= book.getId() %></td>
            <td><%= book.getBookTitle() %></td>
            <td><%= book.getIssueDate() %></td>
            <td><%= book.getReturnDate() %></td>
        </tr>
        <%
            } 
        %>
    </table>
</div>
</body>
</html>
