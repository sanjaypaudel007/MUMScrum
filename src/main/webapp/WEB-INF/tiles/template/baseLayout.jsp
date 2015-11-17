<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>MUM Scrum - 4S</title>

<!-- Bootstrap Core CSS -->
<link href="<spring:url value="/resource/sb-admin/css/bootstrap.min.css" />" rel="stylesheet">

<!-- Custom CSS -->
<link href="<spring:url value="/resource/sb-admin/css/sb-admin.css" />" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="<spring:url value="/resource/sb-admin/css/plugins/morris.css" />" rel="stylesheet">

<!-- Custom Fonts -->
<link href="<spring:url value="/resource/sb-admin/font-awesome/css/font-awesome.min.css" />"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
<!-- jQuery -->
	<script src="<spring:url value="/resource/sb-admin/js/jquery.js" />"></script>
	
</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<tiles:insertAttribute name="navigation" />
		<!-- Page Content -->
		<div id="page-wrapper">

			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<tiles:insertAttribute name="body" />
					</div>
				</div>
				<!-- /.row -->
			</div>

			<!-- /.container-fluid -->
			<tiles:insertAttribute name="footer" />
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->


	<!-- jQuery -->
	<script src="<spring:url value="/resource/sb-admin/js/jquery.js" />"></script>
	
	
	<!-- Custom jQuery -->
	<script src="<spring:url value="/resource/custom/js/custom.js" />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<spring:url value="/resource/sb-admin/js/bootstrap.min.js" />"></script>

	<!-- Morris Charts JavaScript -->
	<script src="<spring:url value="/resource/sb-admin/js/plugins/morris/raphael.min.js" />"></script>
	<script src="<spring:url value="/resource/sb-admin/js/plugins/morris/morris.min.js" />"></script>
	<script src="<spring:url value="/resource/sb-admin/js/plugins/morris/morris-data.js" />"></script>

</body>
</html>
