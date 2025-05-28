<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Student Registration</title>
<link rel="stylesheet" href="css/mystyle.css">
</head>
<body>
	<form action="Register" method="post">
		<h2>Student Registration</h2>

		<label for="name">Name</label> <input type="text" id="name"
			name="name" placeholder="Name" required> <label for="email">Email</label>
		<input type="email" id="email" name="email" placeholder="Email"
			required> <label for="role">Role</label> <input type="text"
			id="role" name="role" value="Student" readonly> <label
			for="password">Password</label> <input type="password" id="password"
			name="password" placeholder="Password"
			pattern="(?=.*[!@#$%^&*]).{8,}"
			title="Password must be at least 8 characters long and include a special character."
			required>

		<button type="submit">Register</button>
			 <a href="login.jsp">Login</a>
	</form>
</body>
</html>
