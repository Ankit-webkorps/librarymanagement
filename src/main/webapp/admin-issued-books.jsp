<%@ page language="java" import="java.util.*, com.lib.entities.IssuedBook, com.lib.dao.StudentDao, com.lib.helper.Connectionprovider" %>

<%
    StudentDao dao = new StudentDao();
    List<IssuedBook> issuedBooks = dao.getAllIssuedBooksWithDetails();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Issued Books</title>
    <link rel="stylesheet" href="css/mystyle.css">
</head>
<body>

<div class="issued-books-container">
    <a href="admin-dashboard.jsp" class="back-btn">Back to Dashboard</a>
    <h2 class="issued-books-title">All Issued Books</h2>

    <table class="issued-books-table">
        <thead>
            <tr>
                <th>Issue ID</th>
                <th>Book Title</th>
                <th>Student Name</th>
                <th>Issue Date</th>
                <th>Return Date</th>
                <th>Quantity</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (IssuedBook ib : issuedBooks) {
            %>
            <tr>
                <td><%= ib.getId() %></td>
                <td><%= ib.getBookTitle() %></td>
                <td><%= ib.getStudentName() %></td>
                <td><%= ib.getIssueDate() %></td>
                <td><%= ib.getReturnDate() %></td>
                <td><%= ib.getQuantity() %></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

</body>
</html>
