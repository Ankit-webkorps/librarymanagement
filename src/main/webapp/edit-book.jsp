<%@ page
	import="com.lib.dao.BookDao, com.lib.entities.Book, com.lib.helper.Connectionprovider"%>
<%
int bookId = Integer.parseInt(request.getParameter("bookId"));
BookDao dao = new BookDao(Connectionprovider.getConnection());
Book book = dao.getBookById(bookId);
%>
<!DOCTYPE html>
<html>
<head>
<title>Edit Book</title>
<link rel="stylesheet" href="css/mystyle.css">
</head>
<body>
	<a href="admin-dashboard.jsp" class="back-btn"> Back to Dashboard</a>
	<h2>Edit Book</h2>
	<form action="UpdateBookServlet" method="post">
		<input type="hidden" name="id" value="<%=book.getId()%>">
		<p>
			Book Name: <input type="text" name="name"
				value="<%=book.getName()%>" required>
		</p>
		<p>
			Author: <input type="text" name="author"
				value="<%=book.getAuthor()%>" required>
		</p>
		<p>
			Edition: <input type="text" name="edition"
				value="<%=book.getEdition()%>" required>
		</p>
		<p>
			Quantity: <input type="number" name="quantity"
				value="<%=book.getQuantity()%>" required>
		</p>
		<button type="submit">Update</button>
	</form>
</body>
</html>
