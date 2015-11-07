<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/employee/register" var="empUrl"/>
<form:form method="post" commandName="employee"
	enctype="multipart/form-data" autocomplete="off" class="form" action="${empUrl}">

	<label>First Name: </label>
	<form:input path="firstName" placeholder="First Name" />

	<label class="control-label">Last Name: </label>
	<form:input path="lastName" placeholder="Last Name" />

	<label class="control-label">DOB: </label>
	<form:input path="dob" placeholder="DOB dd/mm/yyyy" />

	<label class="control-label">Email: </label>
	<form:input path="email" placeholder="Email" />
	<label class="control-label">Phone: </label>
	<form:input path="phone" placeholder="Phone" />
	<label class="control-label">Address: </label>
	<form:textarea path="address" placeholder="Address" />
	<label class="control-label">Image: </label>
	<form:input path="image" type="file" />
	<label class="control-label">Role: </label>
	<form:select id="roleid" path="role.id" items="${roleList}"
		itemValue="id" itemLabel="name"></form:select>
	<label class="control-label">Username: </label>
	<form:input path="username" placeholder="Username" />
	<label class="control-label">Password: </label>
	<form:password path="password" placeholder="Password" />
	<label class="control-label">Re-Password: </label>
	<form:password path="rePassword" placeholder="Re-Password" />
	<input type="submit" value="Register" />
	<input type="button"
		onclick="document.location.href='<spring:url value="/employee" />'"
		value=" Cancel ">
</form:form>
