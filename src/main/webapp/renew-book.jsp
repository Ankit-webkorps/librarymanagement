<%@ page import="java.util.*, java.time.*, com.lib.entities.*, com.lib.dao.StudentDao, com.lib.helper.Connectionprovider" %>
<%

    User user = (User) session.getAttribute("user");
    if (user == null || !"Student".equalsIgnoreCase(user.getRole())) 
     {
        response.sendRedirect("login.jsp");
        return;
    }
    StudentDao dao = new StudentDao();
    List<IssuedBook> renewList = dao.getRenewableBooks(user.getId());  
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Renew Book</title>
    <link rel="stylesheet" href="css/mystyle.css">
</head>
<body>

<div class="renew-book-container">
    <a href="student-dashboard.jsp" class="renew-back-btn">Back to Dashboard</a>

    <h2 class="renew-book-title">Renew Book</h2>

    <%
        if (renewList.isEmpty()) {
    %>
        <p align="center">No books available for renewal.</p>
    <%
        } else {
    %>
        <table class="renew-book-table">
            <tr>
                <th>Book Title</th>
                <th>Return Date</th>
                <th>Action</th>
            </tr>
            <%
                for (IssuedBook book : renewList) {
            %>
            <tr>
                <td><%= book.getBookTitle() %></td>
                <td><%= book.getReturnDate() %></td>
                <td>
                    <form action="RenewBookServlet" method="post" class="renew-book-form">
                        <input type="hidden" name="issuedBookId" value="<%= book.getId() %>">
                        <button type="submit">Renew</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
    <% 
    }
    %>

</div>

</body>
</html>
