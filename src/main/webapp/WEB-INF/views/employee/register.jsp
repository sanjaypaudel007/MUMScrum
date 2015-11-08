<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/employee/register" var="empUrl" />
<form:form method="post" commandName="employee"
	enctype="multipart/form-data" autocomplete="off" class="form"
	action="${empUrl}">
	<ul>
		<li><label>First Name: </label> <form:input path="firstName"
				placeholder="First Name" /></li>

		<li><label>Last Name: </label> <form:input path="lastName"
				placeholder="Last Name" /></li>

		<li><label>DOB: </label> <form:input path="dob"
				placeholder="DOB dd/mm/yyyy" /></li>

		<li><label>Email: </label> <form:input path="email"
				placeholder="Email" /></li>
		<li><label>Phone: </label> <form:input path="phone"
				placeholder="Phone" /></li>
		<li><label>Address: </label> <form:textarea path="address"
				placeholder="Address" /></li>
		<li><label>Image: </label> <form:input path="image" type="file" /></li>
		<li><label>Role: </label> <form:select id="roleid" path="role.id"
				items="${roleList}" itemValue="id" itemLabel="name"></form:select></li>
		<li><label>Username: </label> <form:input path="username"
				placeholder="Username" /></li>
		<li><label>Password: </label> <form:password path="password"
				placeholder="Password" /></li>
		<li><label>Re-Password: </label> <form:password path="rePassword"
				placeholder="Re-Password" /></li>
	</ul>
	<input type="submit" value="Register" />
	<input type="button"
		onclick="document.location.href='<spring:url value="/employee" />'"
		value=" Cancel ">
</form:form>
