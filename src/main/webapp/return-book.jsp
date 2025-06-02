<%@ page language="java"
    import="java.util.*,com.lib.dao.StudentDao,com.lib.entities.IssuedBook,com.lib.helper.Connectionprovider"%>
<%
    Integer studentId = (Integer) session.getAttribute("studentId");
    if (studentId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    StudentDao dao = new StudentDao(Connectionprovider.getConnection());
    List<IssuedBook> issuedBooks = dao.getIssuedBooks(studentId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Return Book</title>
    <link rel="stylesheet" href="css/mystyle.css">
</head>
<body>

<div class="return-book-container">
    <a href="student-dashboard.jsp" class="return-back-btn">Back to Dashboard</a>
    <h2 class="return-book-title">Return Book</h2>

    <%
        if (issuedBooks.isEmpty()) {
    %>
        <p align="center">No books available for return.</p>
    <%
        } else {
    %>
        <table class="return-book-table">
            <tr>
                <th>Issue ID</th>
                <th>Book Name</th>
                <th>Issue Date</th>
                <th>Return Date</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
            <%
                for (IssuedBook book : issuedBooks) {
            %>
            <tr>
                <td><%= book.getId() %></td>
                <td><%= book.getBookTitle() %></td>
                <td><%= book.getIssueDate() %></td>
                <td><%= book.getReturnDate() %></td>
                <td><%= book.getQuantity() %></td>
                <td>
                    <form action="ReturnBookServlet" method="post" class="return-book-form">
                        <input type="hidden" name="issueId" value="<%= book.getId() %>">
                        <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
                        <input type="hidden" name="quantity" value="<%= book.getQuantity() %>">
                        <button type="submit">Return</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
    <% } %>

</div>

</body>
</html>
