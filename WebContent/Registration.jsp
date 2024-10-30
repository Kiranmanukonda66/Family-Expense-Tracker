<html>
<head>
    <title>FER - Registration</title>
    
  	<link rel="stylesheet" href="./CSS/RegistrationStyle.css">

    <script>
    
        function validateRegistrationForm() {
     
            var form = document.RegistrationForm;
            var isValid = true; 


            // Validate First Name
            if (form.firstName.value.trim() === '') {
            	form.firstName.classList.add("error");
            	form.firstName.placeholder = "Please enter First Name"; 
                isValid = false;
            }

            // Validate Last Name
            if (form.lastName.value.trim() === '') {
            	form.lastName.classList.add("error");
            	form.lastName.placeholder = "Please enter Last Name"; 
                isValid = false;
            }

            // Validate Email
            if (form.email.value.trim() === '') {
            	form.email.classList.add("error");
            	form.email.placeholder = "Please enter Email"; 
                isValid = false;
            }

            // Validate Username
            if (form.username.value.trim() === '') {
            	form.username.classList.add("error");
            	form.username.placeholder = "Please enter Username"; 
                isValid = false;
            }

            // Validate Password
            if (form.password.value.trim() === '') {
            	form.password.classList.add("error");
            	form.password.placeholder = "Please enter Password"; 
                isValid = false;
            }

            // Validate Mobile
            if (form.mobile.value.trim() === '') {
            	form.mobile.classList.add("error");
            	form.mobile.placeholder = "Please enter Mobile"; 
                isValid = false;
            }

            // Submit the form if valid
            if (isValid) {
                form.submit();
            }
        }
        
        function removeError(input) {
            input.classList.remove("error");
            input.placeholder = ""; // Clear placeholder on focus
        }
    </script>
</head>

<body>
    <div class="registration-container">
        <h2 class="registration-header">Family Expense Report - Registration</h2>

        <form action='RegistrationPost.jsp' method="post" name="RegistrationForm">
            <table>
                <tr>
                    <td>First Name <font color='red'>*</font></td>
                    <td>
                        <input type='text' name='firstName' placeholder="" onfocus="removeError(this)"/>
                    </td>
                </tr>

                <tr>
                    <td>Middle Name</td>
                    <td>
                        <input type='text' name='middleName' placeholder="" onfocus="removeError(this)"/>
                    </td>
                </tr>

                <tr>
                    <td>Last Name <font color='red'>*</font></td>
                    <td>
                        <input type='text' name='lastName' placeholder="" onfocus="removeError(this)"/>
                    </td>
                </tr>

                <tr>
                    <td>Email <font color='red'>*</font></td>
                    <td>
                        <input type='text' name='email' placeholder="" onfocus="removeError(this)"/>
                    </td>
                </tr>

                <tr>
                    <td>Username <font color='red'>*</font></td>
                    <td>
                        <input type='text' name='username' placeholder="" onfocus="removeError(this)"/>
                    </td>
                </tr>

                <tr>
                    <td>Password <font color='red'>*</font></td>
                    <td>
                        <input type='password' name='password' placeholder="" onfocus="removeError(this)"/>
                    </td>
                </tr>

                <tr>
                    <td>Mobile <font color='red'>*</font></td>
                    <td>
                        <input type='text' name='mobile' placeholder="" onfocus="removeError(this)"/>
                    </td>
                </tr>

                <tr>
                    <td colspan='2' align='center'>
                        <input type="button" value='Register' onclick="validateRegistrationForm()">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
