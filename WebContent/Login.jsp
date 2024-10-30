<html>

<head>
<title>FER - Login</title>
<link rel="stylesheet" href="./CSS/LoginStyle.css">
<script>
	function validateLoginForm(event) {
		event.preventDefault(); // Prevent form from submitting

		var form = document.LoginForm;
		var username = form.username;
		var password = form.password;
		var isValid = true;

		// Clear any previous error state
		username.classList.remove("error");
		password.classList.remove("error");

		// Validate username
		if (username.value.trim() === '') {
			username.placeholder = "Please enter Username";
			username.classList.add("error");
			isValid = false;
		}

		// Validate password
		if (password.value.trim() === '') {
			password.placeholder = "Please enter Password";
			password.classList.add("error");
			isValid = false;
		}

		// If the form is valid, submit it
		if (isValid) {
			form.submit();
		}
	}

	// Remove error state when the user focuses on the input field
	function removeError(input) {
		input.classList.remove("error");
		input.placeholder = "";
	}
</script>

</head>

<body>
	<form action='LoginPost.jsp' method='post' name="LoginForm">
		<div id="box1">
			<h2>Family Expense Report</h2>
			<div id="input-box">
				<!-- onfocus event to clear error on focus -->
				<input type="text" name="username" placeholder="Enter Username" onfocus="removeError(this)">
				<input type="password" name="password" placeholder="Enter password" onfocus="removeError(this)">
			</div>
			<button type="submit" onclick="validateLoginForm(event)">Log in</button>
		</div>
		<div id="box2">
			<p id="signupMessage">
                Don't have an account? <a href='Registration.jsp'>Sign up</a>
            </p>
            <p id="successMessage" style="display: none; color: green;">
                User registered successfully...!
                 <a href='Registration.jsp'>Sign up</a>
            </p>
		</div>
	</form>
	<%
        if (request.getAttribute("registrationSuccess") != null) {
    %>
        <script>
            document.getElementById("signupMessage").style.display = "none";
            document.getElementById("successMessage").style.display = "block";
        </script>
    <%
        }
    %>
    <%
        if (request.getAttribute("message") != null) {
    %>
        <script>
	        var signupMessage = document.getElementById("signupMessage");
	        signupMessage.innerHTML = "Please enter valid username and password..!&nbsp;<a href='Registration.jsp'>Sign up</a>";
	        signupMessage.style.color = "red";
        </script>
    <%
        }
    %>
</body>
</html>
