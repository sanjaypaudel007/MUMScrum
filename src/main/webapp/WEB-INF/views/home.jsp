<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<html>
<head>
<title>MUM Scrum</title>
</head>
<body>
	<h1 class="page-header">
		Dashboard <small>${username}</small>
	</h1>
	<ol class="breadcrumb">
		<li class="active"><i class="fa fa-dashboard"></i> Dashboard</li>
	</ol>

	<div class="row">
		<security:authorize access="hasRole('ADMIN')">
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-users fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div class="huge"></div>
								<br>
								<div>Employee Management</div>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<a href="<spring:url value="/employee" />"> <span
							class="pull-left">View Details</span> <span class="pull-right"><i
								class="fa fa-arrow-circle-right"></i></span></a>
						<div class="clearfix"></div>
					</div>

				</div>
			</div>
		</security:authorize>

		<security:authorize
			access="hasAnyRole('SCRUM_MASTER', 'DEVELOPER', 'TESTER')">
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-green">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-files-o fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div class="huge"></div>
								<br>
								<div>Sprints</div>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<a href="<spring:url value="/sprint/" />"> <span
							class="pull-left">View Details</span> <span class="pull-right"><i
								class="fa fa-arrow-circle-right"></i></span>
						</a>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-yellow">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-list-ol fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div class="huge"></div>
								<br>
								<div>User Story</div>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<a href="<spring:url value="/userstory/" />"> <span
							class="pull-left">View Details</span> <span class="pull-right"><i
								class="fa fa-arrow-circle-right"></i></span>
						</a>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</security:authorize>

		<security:authorize access="hasRole('SCRUM_MASTER')">
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-red">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-sitemap fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div class="huge"></div>
								<br>
								<div>Release Backlogs</div>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<a href="<spring:url value="/releasebacklog/list" />"> <span
							class="pull-left">View Details</span> <span class="pull-right"><i
								class="fa fa-arrow-circle-right"></i></span>
						</a>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</security:authorize>
	</div>
	<!-- /.row -->

</body>
</html>
