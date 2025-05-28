<%@ page import="com.lib.entities.User"%>
<%@ page session="true"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Student Dashboard</title>
<link rel="stylesheet" href="css/mystyle.css">

</head>
<body>
	<h2>
		Welcome,
		<%= user.getName() %>
		(Student)
	</h2>
	<a class="logout-btn" href="LogoutServlet">Logout</a>
	<hr>
	<ul>
		<li><a href="issued-books.jsp"> Books Issued</a></li>
		<li><a href="search.jsp"> Search Books</a></li>
		<li><a href="issue-book.jsp"> Issue Book</a></li>
		<li><a href="renew-book.jsp"> Renew Book</a></li>
		<li><a href="return-book.jsp"> Return Book</a></li>
	</ul>
</body>
</html>
 