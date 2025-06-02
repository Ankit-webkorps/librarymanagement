<%@ page import="java.sql.*, java.util.*,com.lib.helper.Connectionprovider" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Books List</title>
    <link rel="stylesheet" href="css/mystyle.css">
</head>
<body>

<div class="books-list-container">
    <a href="admin-dashboard.jsp" class="back-btn">Back to Dashboard</a>
    <h2 class="books-list-title">Book List</h2>

    <table class="books-list-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Author</th>
                <th>Edition</th>
                <th>Quantity</th>
            </tr>
        </thead>
        <tbody>
            <%
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Root");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM books");

                while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getInt("id") %></td>
                <td><%= rs.getString("name") %></td>
                <td><%= rs.getString("author") %></td>
                <td><%= rs.getString("edition") %></td>
                <td><%= rs.getInt("quantity") %></td>
            </tr>
            <%
                }
                rs.close();
                st.close();
                con.close();
            } catch (Exception e) {
                out.println("Error: " + e.getMessage());
            }
            %>
        </tbody>
    </table>
</div>
</body>
</html>
