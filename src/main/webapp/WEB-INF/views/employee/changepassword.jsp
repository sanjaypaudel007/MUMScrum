<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<c:url value="/employee/changepassword/${id}" var="empUrl" />

<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">Change Password</h4>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">


		<form:form method="post" enctype="multipart/form-data"
			autocomplete="off" class="form">


			<h4 class="media-heading">
				<security:authentication property="principal.username" />
			</h4>
			<hr>
			<div class="row">
				<div class="col-lg-10">
					<div class="col-lg-2">
						<label class="control-label">Old Password: </label>
					</div>
					<div class="col-lg-6">
						<input placeholder="old Password" name="oldPassword"
							type="password" class="form-control" />
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-10">
					<div class="col-lg-2">
						<label class="control-label">New Password: </label>
					</div>
					<div class="col-lg-6">
						<input placeholder="Password" name="password" type="password"
							class="form-control" />
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-10">
					<div class="col-lg-2">
						<label class="control-label">Retype Password: </label>
					</div>
					<div class="col-lg-6">
						<input placeholder="Retype Password" name="rePassword"
							type="password" class="form-control" />
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-10 col-lg-offset-2">
					<input class="btn btn-success" type="submit" value="Change" /> <input
						type="button"
						onclick="document.location.href='<spring:url value="/" />'"
						class="btn btn-danger" value=" Cancel ">
				</div>

			</div>

		</form:form>


	</div>
</div>


