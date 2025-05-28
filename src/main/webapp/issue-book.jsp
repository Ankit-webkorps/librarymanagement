<%@ page language="java"
	import="java.util.*,com.lib.dao.BookDao,com.lib.entities.Book,com.lib.helper.Connectionprovider"%>
<%
BookDao dao = new BookDao(Connectionprovider.getConnection());
List<Book> books = dao.getAllBooks();
Integer studentId = (Integer) session.getAttribute("studentId");
%>

<!DOCTYPE html>
<html>
<head>
<title>Issue Book</title>
<link rel="stylesheet" href="css/mystyle.css">

</head>
<body>

	<a href="student-dashboard.jsp" class="back-btn"> Back to Dashboard</a>

	<h2>Available Books</h2>

	<div class="card-container">
		<%
		for (Book b : books) {
		%>
		<div class="book-card">
			<h3><%=b.getName()%></h3>
			<p>
				<strong>Author:</strong>
				<%=b.getAuthor()%></p>
			<p>
				<strong>Edition:</strong>
				<%=b.getEdition()%></p>
			<p>
				<strong>Available Quantity:</strong>
				<%=b.getQuantity()%></p>

			<form action="IssueBookServlet" method="post">
				<input type="hidden" name="bookId" value="<%=b.getId()%>">
				<input type="hidden" name="studentId"
					value="<%=studentId != null ? studentId : ""%>"> <label>Return
					Date:</label> <input type="date" name="returnDate" required> <label>Quantity:</label>
				<input type="number" name="quantity" value="1" min="1"
					max="<%=b.getQuantity()%>" required>

				<button type="submit">Issue Book</button>
			</form>
		</div>
		<%
		}
		%>
	</div>

</body>
</html>
