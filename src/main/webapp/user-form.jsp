<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Customer Management Application</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>

<!-- Header Section -->
<header>
    <!-- Navigation Bar -->
    <nav class="navbar navbar-dark bg-primary" style="background-color: #e3f2fd;">
        <div class="container-fluid">
            <!-- Application Brand -->
            <a class="navbar-brand">Customer Management App</a>
            <div class="d-flex">
                <!-- Navigation Links -->
                <ul class="navbar-nav">
                    <li ><a href="<%=request.getContextPath()%>/list"
                            class="nav-link">Users</a></li>
                </ul>&nbsp &nbsp &nbsp
                <!-- Logout Button -->
                <button class="btn btn-dark" type="submit"><a href="login.jsp">Logout</a></button>
            </div>
        </div>
    </nav>
</header>

<br>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <!-- Check if a user is present for editing or it's a new user creation -->
            <c:if test="${user != null}"><form action="update" method="post"> </c:if>
            <c:if test="${user == null}"><form action="insert" method="post"> </c:if>

            <!-- Form Title -->
            <caption>
                <h2 class="h22"> <c:if test="${user != null}">Edit User </c:if>
                    <c:if test="${user == null}">Add New User</c:if></h2>
            </caption>

            <!-- Hidden input for user ID if editing an existing user -->
            <c:if test="${user != null}">
                <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
            </c:if>

            <!-- Form Fields -->
            <fieldset class="form-group">
                <label>First Name*</label>
                <input type="text"  value="<c:out value='${user.fname}' />" class="form-control" name="fname" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Last Name*</label>
                <input type="text" value="<c:out value='${user.lname}' />" class="form-control" name="lname" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Street*</label>
                <input type="text" value="<c:out value='${user.street}' />" class="form-control" name="street" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Address</label>
                <input type="text"  value="<c:out value='${user.address}' />" class="form-control"  name="address" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>City*</label>
                <input type="text"  value="<c:out value='${user.city}' />" class="form-control" name="city" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>State*</label>
                <input type="text" value="<c:out value='${user.state}' />" class="form-control" name="state" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Email*</label>
                <input type="email" value="<c:out value='${user.email}' />" class="form-control" name="email" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Phone*</label>
                <input type="number" value="<c:out value='${user.phone}' />" class="form-control" name="phone" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Password*</label>
                <input type="text" value="<c:out value='${user.password}' />" class="form-control" name="password" required="required">
            </fieldset>

            <!-- Submit Button -->
            <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
