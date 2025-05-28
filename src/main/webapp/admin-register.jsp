<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Registration</title>
<link rel="stylesheet" href="css/mystyle.css">
</head>
<body>
	<form action="Register" method="post">
		<h2>Admin Registration</h2>

		<label for="name">Name</label> <input type="text" name="name"
			id="name" placeholder="Name" required> <label
			for="libraryName">Library Name</label> <input type="text"
			name="libraryName" id="libraryName" placeholder="Library Name"
			required> <label for="address">Address</label> <input
			type="text" name="address" id="address" placeholder="Address"
			required> <label for="email">Email</label> <input
			type="email" name="email" id="email" placeholder="Email" required>

		<label for="role">Role</label> <input type="text" name="role"
			id="role" value="Admin" readonly> <label for="password">Password</label>
		<input type="password" name="password" id="password"
			placeholder="Password" pattern="(?=.*[!@#$%^&*]).{8,}"
			title="Password must be at least 8 characters and include a special character."
			required>

		<button type="submit">Register</button>
			 <a href="login.jsp">Login</a>
	</form>
</body>
</html>
