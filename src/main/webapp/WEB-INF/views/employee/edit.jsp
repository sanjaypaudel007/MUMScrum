<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/employee/edit/${id}" var="empUrl" />

<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">Employee Update</h4>
	</div>
	<ol class="breadcrumb">
	<li><i class="fa fa-users"></i><a href="<spring:url value="/employee" />" type="submit"> Employee</a></li>
		 
		<li class="active"> Edit</li>
	</ol>
</div>

<div class="row">
	<div class="col-lg-12">


		<form:form method="post" commandName="employee"
	enctype="multipart/form-data" autocomplete="off" class="form">

			<%-- <c:set var="validationErrors">
				<form:errors path="*" />
			</c:set> --%>
			<c:if test="${not empty validationErrors}">
				<c:forEach items="${validationErrors}" var="item">
					<p>${item}</p>
				</c:forEach>
			</c:if>

			<div class="row">
				<div class="col-lg-10">
					<spring:bind path="firstName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<div class="col-lg-2">
								<label class="control-label">First Name: </label>
							</div>
							<div class="col-lg-6">
								<form:input path="firstName" placeholder="First Name"
									class="form-control" />
							</div>
							<form:errors path="firstName" class="help-block"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-10">
					<spring:bind path="lastName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<div class="col-lg-2">
								<label class="control-label">Last Name: </label>
							</div>
							<div class="col-lg-6">
								<form:input path="lastName" placeholder="Last Name"
									class="form-control" />
							</div>
							<form:errors path="lastName" class="help-block"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-10">
					<spring:bind path="dob">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<div class="col-lg-2">
								<label class="control-label">Date of Birth: </label>
							</div>
							<div class="col-lg-6">
								<div class='input-group date' id="dobdate-picker">
									<form:input path="dob" placeholder="DD/MM/YYYY"
										class="form-control" />
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<form:errors path="dob" class="help-block"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-10">
					<spring:bind path="email">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<div class="col-lg-2">
								<label class="control-label">Email: </label>
							</div>
							<div class="col-lg-6">
								<form:input path="email" placeholder="Email"
									class="form-control" />
							</div>
							<form:errors path="email" class="help-block"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-10">
					<spring:bind path="phone">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<div class="col-lg-2">
								<label class="control-label">Phone: </label>
							</div>
							<div class="col-lg-6">
								<form:input path="phone" placeholder="Phone"
									class="form-control" />
							</div>
							<form:errors path="phone" class="help-block"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-10">
					<spring:bind path="address">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<div class="col-lg-2">
								<label class="control-label">Address: </label>
							</div>
							<div class="col-lg-6">
								<form:textarea path="address" placeholder="Address"
									multiple="true" class="form-control" />
							</div>
							<form:errors path="lastName" class="help-block"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-10">
					<spring:bind path="image">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<div class="col-lg-2">
								<label class="control-label">Image: </label>
							</div>
							<div class="col-lg-6">
								<form:input path="image" type="file" />
							</div>
							<form:errors path="image" class="help-block"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-10">
					<spring:bind path="roles">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<div class="col-lg-2">
								<label class="control-label">Role: </label>
							</div>
							<div class="col-lg-6">
								<c:forEach items="${roleList}" var="role">

									<form:checkbox path="roles" value="${role.id}"
										label="${role.role}" />
								</c:forEach>
							</div>
							<form:errors path="roles" class="help-block"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-10">
					<spring:bind path="username">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<div class="col-lg-2">
								<label class="control-label">Username: </label>
							</div>
							<div class="col-lg-6">
								<form:input path="username" placeholder="Username"
									class="form-control" />
							</div>
							<form:errors path="username" class="help-block"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>
			<div class="row hide" >
				<div class="col-lg-10">
					<spring:bind path="password">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<div class="col-lg-2">
								<label class="control-label">Password: </label>
							</div>
							<div class="col-lg-6">
								<form:input path="password" placeholder="Password"
									type="password" class="form-control" />
							</div>
							<form:errors path="password" class="help-block"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>
			<div class="row hide">
				<div class="col-lg-10">
					<spring:bind path="rePassword">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<div class="col-lg-2">
								<label class="control-label">Retype Password: </label>
							</div>
							<div class="col-lg-6">
								<form:input path="rePassword" placeholder="Retype Password"
									type="password" class="form-control" />
							</div>
							<form:errors path="rePassword" class="help-block"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-10 col-lg-offset-2">
					<input class="btn btn-success" type="submit" value="Update" /> <input
						type="button"
						onclick="document.location.href='<spring:url value="/employee" />'"
						class="btn btn-danger" value=" Cancel ">
				</div>

			</div>

		</form:form>


	</div>
</div>

<!-- datetimepicker CSS -->
<link
	href="<spring:url value="/resource/bootstrap-datepicker/bootstrap-datetimepicker.css" />"
	rel="stylesheet" type="text/css" />
<!-- datepicker js -->
<script type="text/javascript"
	src="<spring:url value="/resource/bootstrap-datepicker/moment-with-locales.js" />"></script>

<script type="text/javascript"
	src="<spring:url value="/resource/bootstrap-datepicker/bootstrap-datetimepicker.js" />"></script>

<script>
	$(document).ready(function() {

		$("#enablePasswordChange").on("change", function() {
			if ($(this).is(":checked")) {
				$("#password, #rePassword").removeAttr("disabled");
				$("#password, #rePassword").removeAttr("readonly");
			} else {

				$("#password, #rePassword").attr("disabled", "disabled");
				$("#password, #rePassword").attr("readonly", "readonly");

			}

		}).change();
		$(function() {
			$(function() {
				$('#dobdate-picker').datetimepicker({
					format : 'DD/MM/YYYY'

				});
			});
		});

	});
</script>

