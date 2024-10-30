<html>
<head>
<title>FER - Dashboard</title>
<!--  <link rel="stylesheet" href="../CSS/Header_LeftFrameStyle.css" />  -->
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #f4f4f9;
	margin: 0;
	padding: 0;
}

.container {
	width: 100%;
	max-width: 1000px;
	margin: 50px auto;
	background-color: #ffffff;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	overflow: hidden;
}

.header-container {
	display: flex;
	align-items: center;
	background-color: #03346E;
	padding: 20px;
	color: white;
}

.image {
	border-radius: 50%;
	width: 75px;
	height: 75px;
	object-fit: cover;
	margin-right: 20px;
}

.text-container {
	flex: 1;
	text-align: center;
	font-size: 22px;
	font-weight: bold;
}

.sidebar {
	width: 180px;
	background-color: #03346E;
	padding: 20px;
	height: auto;
	flex-shrink: 0;
}

.sidebar ul {
	list-style: none;
	padding: 0;
}

.sidebar ul li {
	margin-bottom: 15px;
}

.sidebar ul li a {
	color: white;
	text-decoration: none;
	font-weight: bold;
	display: block;
	padding: 10px;
	border-radius: 5px;
	background: linear-gradient(to right, #03346E, #006396);
	transition: background 0.3s;
}

.sidebar ul li a:hover {
	background: #006396;
}

.content {
	flex: 1; /* Let content take remaining space */
	max-width: 700px; /* Limit max width of the content */
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	padding: 30px;
	text-align: center;
	font-size: 18px;
	color: #333;
	height: 400px;
}

/* Dashboard footer */
.footer {
	background-color: #f4f4f9;
	padding: 20px;
	text-align: center;
	color: #666;
	font-size: 16px;
	font-weight: bold;
}
</style>
<script>
	function submitForm(path) {
		var form = document.DashboardForm;
		form.action = path;

		if (path === 'UploadImagePost.jsp') {
			form.enctype = "multipart/form-data";
		}

		form.submit();
	}
	history.go(1);
</script>
</head>

<body>
	<form name='DashboardForm' method='post'>
		<div class="container">
			<!-- Header -->
			<div class="header-container">
				<%
					String uploadedImage = (String) session.getAttribute("uploadedImage");
				if (uploadedImage != null && !uploadedImage.isEmpty()) {
				%>

				<img src="uploads/${userId }/<%=uploadedImage%>" class="image" />

				<%
					}
				%>
				<div class="text-container">
					Family Expense Report
					<div>User: 12</div>
				</div>
			</div>

			<div style="display: flex;">
				<!-- Sidebar Menu -->
				<div class="sidebar">
					<ul>
						<li><a href="javascript: submitForm('AddExpense.jsp')">Add
								Expense</a></li>
						<li><a
							href="javascript: submitForm('EditExpenseOptions.jsp')">Edit
								Expense</a></li>
						<li><a
							href="javascript: submitForm('DeleteExpenseOptions.jsp')">Delete
								Expense</a></li>
						<li><a
							href="javascript: submitForm('ExpenseReportSelection.jsp')">Expense
								Report</a></li>
						<li><a href="javascript: submitForm('ResetPassword.jsp')">Reset
								Password</a></li>
						<li><a href="javascript: submitForm('NameInfo.jsp')">Update
								Profile</a></li>
						<li><a href="javascript: submitForm('UploadImage.jsp')">Upload
								Image</a></li>
						<li><a href="javascript: submitForm('Login.jsp')">Log Out</a></li>
					</ul>
				</div>

				<!-- Content Area -->
				<div class="content">