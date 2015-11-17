<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="<spring:url value="/" />">MUM Scrum
			<small> - 4S</small>
		</a>
	</div>
	<!-- Top Menu Items -->
	<ul class="nav navbar-right top-nav">
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"><i class="fa fa-user"></i> <security:authentication
					property="principal.username" /> <b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li><a href="#"><i class="fa fa-fw fa-user"></i> Profile</a></li>
				<li><a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
				</li>
				<li><a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a></li>
				<li class="divider"></li>
				<li><a href="<spring:url value="/logout" />"><i
						class="fa fa-fw fa-power-off"></i> Log Out</a></li>
			</ul></li>
	</ul>
	<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav side-nav">
			<li><a href="<spring:url value="/" />"><i
					class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
			<security:authorize
				access="hasAnyRole('SCRUM_MASTER', 'DEVELOPER', 'TESTER')">
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i>
						Progress <i class="fa fa-fw fa-caret-down"></i></a>
					<ul id="demo" class="collapse">
						<security:authorize access="hasAnyRole('DEVELOPER', 'TESTER')">
							<li><a href="<spring:url value="/worklog/add" />"><i
									class="fa fa-clock-o fa-fw"></i> Estimations</a></li>
						</security:authorize>

						<security:authorize access="hasRole('SCRUM_MASTER')">
							<li><a href="<spring:url value="/sprint/" />"><i
									class="fa fa-bar-chart-o fa-fw"></i> Burndown Chart</a></li>
						</security:authorize>
					</ul></li>
			</security:authorize>

			<security:authorize
				access="hasAnyRole('SCRUM_MASTER', 'DEVELOPER', 'TESTER')">
				<li><a href="<spring:url value="/userstory/" />"><i
						class="fa fa-list-ol fa-fw"></i> User Story</a></li>
			</security:authorize>

			<security:authorize access="hasRole('SCRUM_MASTER')">
				<li><a href="<spring:url value="/releasebacklog/list" />"><i
						class="fa fa-sitemap fa-fw"></i> Release Backlog</a></li>
			</security:authorize>

			<security:authorize
				access="hasAnyRole('SCRUM_MASTER', 'DEVELOPER', 'TESTER')">
				<li><a href="<spring:url value="/sprint/" />"><i
						class="fa fa-files-o fa-fw"></i> Sprint</a></li>
			</security:authorize>
			<security:authorize access="hasRole('ADMIN')">
				<li><a href="<spring:url value="/employee" />"><i
						class="fa fa-users fa-fw"></i> Employee</a></li>
			</security:authorize>

		</ul>
	</div>
	<!-- /.navbar-collapse -->
</nav>
