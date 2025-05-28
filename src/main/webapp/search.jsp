<!DOCTYPE html>
<html lang="en">
<head>
    <title>Search Books</title>
    <link rel="stylesheet" href="css/mystyle.css">
</head>
<body>

<div class="search-container">
    <a href="student-dashboard.jsp" class="search-back-btn">Back to Dashboard</a>

    <h2 class="search-title">Search Books</h2>

    <form action="StudentSearchServlet" method="post" class="search-form">
        <input type="text" name="keyword" placeholder="Enter book name or author" required class="search-input" />
        <input type="submit" value="Search" class="search-submit" />
    </form>
</div>

</body>
</html>
