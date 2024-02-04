<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>

<html lang="en">
<head>
    <!-- Meta tags for responsive design -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>

    <!-- Link to the external stylesheet -->
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link href="css/style.css" rel="stylesheet" >

    <!-- Google Sign-In client ID for authentication -->
    <meta name="google-signin-client_id" content="820932539612-nq2mrdlcrcbig5rhr610is71927patu5.apps.googleusercontent.com">
    
    <!-- Include SweetAlert2 and Google Sign-In scripts -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
</head>

<body>
    <!-- Hidden input to store login status from the server -->
    <input type="hidden" id="status" value="<%=request.getAttribute("status")%>">

    <!-- Login form -->
    <form method="post" action="log">
        <h3>Login Here</h3>
        
        <!-- Input fields for email and password -->
        <label for="username">Username</label>
        <input type="email" name="email" id="email" placeholder="Your Email">
        <label for="password">Password</label>
        <input type="password" name="password" id="password" placeholder="Password" />
        
        <!-- Login button -->
        <div class="button">
            <input type="submit" name="signin" id="signin" value="Log in" />
        </div>
  
        <!-- Social login options -->
        <div class="social">
            <!-- Google Sign-In button -->
            <div class="g-signin2" data-onsuccess="onSignIn"></div>
            
            <!-- Link to create a new account -->
            <div class="create">
                <a href="signup.jsp" class="signup-image-link">Create an account</a>
            </div>
        </div>
    </form>

    <!-- JavaScript code for handling Google Sign-In and displaying login status -->
    <script type="text/javascript">
        // Function to handle Google Sign-In
        function onSignIn(googleUser) {
            var profile = googleUser.getBasicProfile();
            console.log('ID: ' + profile.getId());
            console.log('Name: ' + profile.getName());
            console.log('Image URL: ' + profile.getImageUrl());
            console.log('Email: ' + profile.getEmail());
        }

        // Check the login status from the server and display a SweetAlert if login failed
        var status = document.getElementById("status").value;
        if (status == "failed") {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Wrong Username or Password!',
            });
        }
    </script>
</body>
</html>
