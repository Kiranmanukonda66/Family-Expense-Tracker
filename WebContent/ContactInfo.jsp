<%@page import="com.rs.fer.bean.User"%>
<%@page import="com.rs.fer.service.FERService"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>

<jsp:include page="Layout/Header_LeftFrame.jsp" />
<link rel="stylesheet" href="./CSS/FormStyle.css">

<script>
	function validateContactInfoForm() {
		//1.Get the form object and instantiate the errors object
		var form = document.DashboardForm;
		var isValid = true;

		if (form.email.value.trim() === '') {
			form.email.classList.add("error");
			form.email.placeholder = "Please enter Email";
			isValid = false;
		}

		if (form.mobile.value.trim() === '') {
			form.mobile.classList.add("error");
			form.mobile.placeholder = "Please enter Mobile";
			isValid = false;
		}

		if (isValid) {
			submitForm('AddressInfo.jsp');
		}
	}

	function removeError(input) {
		input.classList.remove("error");
		input.placeholder = ""; // Clear placeholder on focus
	}
</script>

<%
	// 1.get the user oobject
User user = (User) session.getAttribute("user");

// 2.update name feilds
user.setFirstName(request.getParameter("firstName"));
user.setMiddleName(request.getParameter("middleName"));
user.setLastName(request.getParameter("lastName"));
%>

<div class="form-container">
	<h2>Contact Information</h2>
	<div class="form-group">
		<label>Email<font color='red'>*</font></label> <input type='text'
			name='email' value='<%=user.getEmail()%>' placeholder=""
			onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>Mobile<font color='red'>*</font></label> <input type='text'
			name='mobile' value='<%=user.getMobile()%>' placeholder=""
			onfocus="removeError(this)">
	</div>
	<div class="form-actions">
		<input type='button' value='Next'
			onclick="javascript: validateContactInfoForm()">
	</div>
</div>

<jsp:include page="Layout/Footer.jsp" />