<%@ page
	import="java.util.*, com.lib.dao.BookDao, com.lib.entities.Book, com.lib.helper.Connectionprovider"%>
<%
    BookDao dao = new BookDao(Connectionprovider.getConnection());
    List<Book> books = dao.getAllBooks();
%>
<!DOCTYPE html>
<html>
<head>
<title>Update Book</title>
<link rel="stylesheet" href="css/mystyle.css">
</head>
<body>
	<a href="admin-dashboard.jsp" class="back-btn"> Back to Dashboard</a>
	<h2>Update Book</h2>
	<table border="1" cellpadding="10">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Author</th>
			<th>Edition</th>
			<th>Quantity</th>
			<th>Action</th>
		</tr>
		<% for (Book b : books) { %>
		<tr>
			<td><%= b.getId() %></td>
			<td><%= b.getName() %></td>
			<td><%= b.getAuthor() %></td>
			<td><%= b.getEdition() %></td>
			<td><%= b.getQuantity() %></td>
			<td><a href="edit-book.jsp?bookId=<%= b.getId() %>">Edit</a></td>
		</tr>
		<% } %>
	</table>
</body>
</html>
