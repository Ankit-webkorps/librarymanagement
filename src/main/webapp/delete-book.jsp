<%@ page import="java.util.*, com.lib.dao.BookDao, com.lib.entities.Book, com.lib.helper.Connectionprovider" %>
<%
    BookDao dao = new BookDao(Connectionprovider.getConnection());
    List<Book> books = dao.getAllBooks();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delete Book</title>
    <link rel="stylesheet" href="css/mystyle.css">
</head>
<body>

<div class="delete-book-container">
    <a href="admin-dashboard.jsp" class="delete-book-back-btn">Back to Dashboard</a>
    <h2 class="delete-book-title">Delete Book</h2>

    <table class="delete-book-table">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Author</th>
            <th>Edition</th>
            <th>Quantity</th>
            <th>Action</th>
        </tr>
        <%
            for (Book b : books) {
        %>
        <tr>
            <td><%= b.getId() %></td>
            <td><%= b.getName() %></td>
            <td><%= b.getAuthor() %></td>
            <td><%= b.getEdition() %></td>
            <td><%= b.getQuantity() %></td>
            <td>
                <form action="DeleteBookServlet" method="post">
                    <input type="hidden" name="bookId" value="<%= b.getId() %>">
                    <button type="submit" class="delete-book-btn">Delete</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
