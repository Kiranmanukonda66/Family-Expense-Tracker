<jsp:include page="Layout/Header_LeftFrame.jsp" />

<link rel="stylesheet" href="./CSS/FormStyle.css">

<script>

	function validateResetPasswordForm() {
		//1.Get the form object and instantiate the errors object
		var form = document.DashboardForm;
		var isValid = true;

		if (form.currentPassword.value.trim() === '') {
			form.currentPassword.classList.add("error");
			form.currentPassword.placeholder = "Please enter Current Password";
			isValid = false;
		}

		if (form.newPassword.value.trim() === '') {
			form.newPassword.classList.add("error");
			form.newPassword.placeholder = "Please enter New Password";
			isValid = false;
		}
		
		if (form.confirmNewPassword.value.trim() === '') {
			form.confirmNewPassword.classList.add("error");
			form.confirmNewPassword.placeholder = "Please enter Confirm NewPassword";
			isValid = false;
		}
		
		if(form.newPassword.value.trim()!= form.confirmNewPassword.value.trim()){
			error = "NewPassword and ConfirmNewPassword should match";
			document.getElementById("error-message").innerText = error;
			isValid = false;
		}

		if (isValid) {
			submitForm('ResetPasswordPost.jsp');
		}
	}

	function removeError(input) {
		input.classList.remove("error");
		input.placeholder = ""; // Clear placeholder on focus
	}
</script>

<div class="form-container">
	<h2>Reset Password</h2>
	<div class="form-group">
		<label>Current Password<font color='red'>*</font></label> <input
			type='text' name='currentPassword' placeholder="" onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>New Password<font color='red'>*</font></label> <input type='text'
			name='newPassword' placeholder="" onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>Confirm NewPassword<font color='red'>*</font></label> <input type='text'
			name='confirmNewPassword' placeholder="" onfocus="removeError(this)">
	</div>
	<div id='error-message'></div><br>
	<div class="form-actions">
		<input type='button' value='Reset'
			onclick="javascript: validateResetPasswordForm()">
	</div>
</div>

<jsp:include page="Layout/Footer.jsp" />