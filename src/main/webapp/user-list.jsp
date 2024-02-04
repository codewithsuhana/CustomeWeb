<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vendor.management.model.User" %>

<html>
<head>
    <title>User Management Application</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<!-- Header Section -->
<header>
    <nav class="navbar navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand">User Management App</a>
            <div class="d-flex">
                <ul class="navbar-nav">
                    <!-- Navigation links -->
                    <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
                </ul>&nbsp &nbsp &nbsp;
                <!-- Logout button -->
                <button class="btn btn-dark" type="submit"><a href="login.jsp">Logout</a></button>
            </div>
        </div>
    </nav>
</header>

<br>

<div class="row">
    <div class="container">
        <!-- Title Section -->
        <h3 class="text-center">List of Users</h3>
        <hr>

        <!-- Add New User and Search Form -->
        <div class="container text-left mb-2">
            <div class="row">
                <div class="col-md-6 mb-2">
                    <!-- Button to add a new user -->
                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
                </div>
                <div class="col-md-6">
                    <!-- Form for searching users -->
                    <form action="<%=request.getContextPath()%>/list" method="GET" class="form-inline">
                        <select id="searchType" name="searchType" class="form-control mr-2">
                            <option value="" selected disabled>Search by</option>
                            <option value="fname">First Name</option>
                            <option value="city">City</option>
                            <option value="email">Email</option>
                            <option value="phone">Phone</option>
                        </select>
                        <input type="text" name="searchValue" placeholder="Enter search value" class="form-control mr-2">
                        <button type="submit" class="btn btn-primary">Search</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Pagination Logic -->
        <c:set var="pageSize" value="10" /> <!-- Set the number of users per page -->
        <c:set var="currentPage" value="${empty param.page ? 1 : param.page}" />
        <c:set var="startIndex" value="${(currentPage - 1) * pageSize}" />
        <c:set var="endIndex" value="${startIndex + pageSize - 1}" />
        <c:set var="totalUsers" value="${listUser.size()}" />

        <!-- User List Table -->
        <table class="table table-bordered">
            <thead style="color: white">
            <!-- Table Header -->
            <tr bgcolor="120671">
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Street</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <!-- Iterate over the list of users and display in the table -->
            <c:forEach var="user" items="${listUser}" begin="${startIndex}" end="${endIndex}">
                <tr bgcolor="#bffef4">
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.fname}" /></td>
                    <td><c:out value="${user.lname}" /></td>
                    <td><c:out value="${user.street}" /></td>
                    <td><c:out value="${user.address}" /></td>
                    <td><c:out value="${user.city}" /></td>
                    <td><c:out value="${user.state}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td><c:out value="${user.phone}" /></td>
                    <!-- Edit and Delete links for each user -->
                    <td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- Pagination Links -->
        <c:if test="${totalUsers > pageSize}">
            <ul class="pagination">
                <!-- Generate pagination links -->
                <c:forEach var="i" begin="1" end="${(totalUsers / pageSize) + 1}">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="<%=request.getContextPath()%>/list?page=${i}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
</div>

<!-- Include Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

</body>
</html>
