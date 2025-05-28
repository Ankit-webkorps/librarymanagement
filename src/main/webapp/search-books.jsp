<%@ page language="java" import="java.util.*,com.lib.entities.Book"%>
<html>
<head>
    <title>Search Results</title>
    <link rel="stylesheet" href="css/mystyle.css">
</head>
<body>

<div class="search-results-container">
    <a href="student-dashboard.jsp" class="search-back-btn">Back to Dashboard</a>

    <h2 class="search-results-title">Search Results</h2>

    <%
          List<Book> books = (List<Book>) request.getAttribute("books");
        if(books != null) {
    %>
        <table class="search-results-table">
            <tr>
                <th>Name</th>
                <th>Author</th>
                <th>Edition</th>
                <th>Quantity</th>
            </tr>
            <%
                for (Book book : books) {
            %>
            <tr>
                <td><%= book.getName() %></td>
                <td><%= book.getAuthor() %></td>
                <td><%= book.getEdition() %></td>
                <td><%= book.getQuantity() %></td>
            </tr>
            <% 
                }
            %>
        </table>
    <%
        } else {
    %>
        <p>No books found.</p>
    <%
        }
    %>
</div>

</body>
</html>
