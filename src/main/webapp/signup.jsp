<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Meta tags for character set and responsive design -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form</title>

    <!-- Link to custom CSS stylesheet for styling the form -->
	<link rel="stylesheet" href="css/signUp.css">

    <!-- Include SweetAlert2 script for displaying alerts -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
    <!-- Hidden field to store the status attribute from the server -->
    <input type="hidden" id="status" value="<%=request.getAttribute("status")%>">

	<div class="main">
		<section class="signup">
            <div class="container">
                <div class="signup-content">
                    <!-- Form for user registration -->
                    <div class="signup-form">
                        <h2 class="form-title">Sign up</h2>
						<form method="post" action="SignupServlet">
                            <!-- Input field for user ID -->
                            <div class="form-group">
                                <label for="id"></label> 
                                <input type="number" name="id" id="id" placeholder="Enter ID" />
                            </div>
                            <!-- Input field for user Name -->
                            <div class="form-group">
                                <label for="name"></label> 
                                <input type="text" name="name" id="name" placeholder="Enter Name" />
                            </div>
                            <!-- Input field for user Email -->
                            <div class="form-group">
                                <label for="email"></label> 
                                <input type="email" name="email" id="email" placeholder="Enter Email" />
                            </div>
                            <!-- Input field for user Password -->
                            <div class="form-group">
                                <label for="password"></label> 
                                <input type="password" name="password" id="password" placeholder="Create Password" />
                            </div>
                            <!-- Submit button for user registration -->
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="SignUp" />
                            </div>
                        </form>
                    </div>
                    <!-- Link to the login page for existing users -->
                    <div class="signup-image">
                     	<a href="login.jsp" class="signup-image-link">I am already a member</a>
                    </div>
                </div>
            </div>
        </section>
	</div>

    <!-- Script to check the status and display SweetAlert if registration is successful -->
    <script type="text/javascript">
        var status = document.getElementById("status").value;
        if (status == "success") {
            // Display SweetAlert for successful account creation
            Swal.fire({
                icon: 'success',
                title: 'Account Created Successfully!',
                text: 'You can now log in with your credentials.',
            });
        }
    </script>
</body>
</html>
