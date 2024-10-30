<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>
<%@page import="com.rs.fer.bean.User"%>
<%@page import="com.rs.fer.service.FERService"%>

<%!FERService ferService = new FERServiceImpl();%>

<jsp:include page="Layout/Header_LeftFrame.jsp" />

<link rel="stylesheet" href="./CSS/FormStyle.css">

<script>
	function validateNameInfoForm() {
		//1.Get the form object and instantiate the errors object
		var form = document.DashboardForm;
		var isValid = true;

		if (form.firstName.value.trim() === '') {
			form.firstName.classList.add("error");
			form.firstName.placeholder = "Please enter First Name";
			isValid = false;
		}

		if (form.lastName.value.trim() === '') {
			form.lastName.classList.add("error");
			form.lastName.placeholder = "Please enter Last Name";
			isValid = false;
		}

		if (isValid) {
			submitForm('ContactInfo.jsp');
		}
	}

	function removeError(input) {
		input.classList.remove("error");
		input.placeholder = ""; // Clear placeholder on focus
	}
</script>

<%
	
	// 1.1 get userId by session object
	int userId = Integer.parseInt(session.getAttribute("userId").toString());
	
	// 2.call the service method for business logic
	User user = ferService.getUser(userId);
	session.setAttribute("user", user);

%>
	
<div class="form-container">
	<h2>Persoanl Information</h2>
	<div class="form-group">
		<label>First Name<font color='red'>*</font></label> <input
			type='text' name='firstName' value='<%=user.getFirstName()%>'
			placeholder="" onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>Middle Name</label> <input type='text'
			name='middleName' value='<%=user.getMiddleName()%>' placeholder=""
			onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>Last Name<font color='red'>*</font></label> <input type='text'
			name='lastName' value='<%=user.getLastName()%>' placeholder=""
			onfocus="removeError(this)">
	</div>
	<div class="form-actions">
		<input type='button' value='Next'
			onclick="javascript: validateNameInfoForm()">
	</div>
</div>

<jsp:include page="Layout/Footer.jsp" />