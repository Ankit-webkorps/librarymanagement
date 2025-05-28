<!DOCTYPE html>
<html lang="en">
<head>
  <title>Add Book</title>
  <link rel="stylesheet" href="css/mystyle.css">
</head>

<body>
<a href="admin-dashboard.jsp" class="back-btn">Back to Dashboard</a>
  <form action="BookServlet" method="post">
    <h2>Add New Book</h2>
    <input type="hidden" name="action" value="add">
    <input type="text" name="name" placeholder="Book Name" required>
    <input type="text" name="author" placeholder="Author" required>
    <input type="text" name="edition" placeholder="Edition" required>
    <input type="number" name="quantity" placeholder="Quantity" required>
    <button type="submit">Add Book</button>
  </form>
</body>
</html>
