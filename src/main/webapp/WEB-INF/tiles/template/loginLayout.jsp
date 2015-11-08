<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><tiles:insertAttribute name="title" /></title>
	<link rel="icon" type="image/png" href="<spring:url value="/resource/images/favicon.png" />"/>

     <!-- Bootstrap Core CSS -->
    <link href="<spring:url value="/resource/bootstrap/dist/css/bootstrap.min.css" />" rel="stylesheet" type="text/css" />
    <!-- MetisMenu CSS -->
    <link href="<spring:url value="/resource/metisMenu/dist/metisMenu.min.css" />" rel="stylesheet" type="text/css" />

    <!-- Custom CSS -->
    <link href="<spring:url value="/resource/dist/css/sb-admin-2.css" />" rel="stylesheet" type="text/css" />
    

    <!-- Custom Fonts -->
    <link href="<spring:url value="/resource/font-awesome/css/font-awesome.min.css" />" rel="stylesheet" type="text/css" />
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery -->
    <script type="text/javascript" src="<spring:url value="/resource/jquery/dist/jquery.min.js" />"></script>
    

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <tiles:insertAttribute name="body" />
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap Core JavaScript -->
    <script type="text/javascript" src="<spring:url value="/resource/bootstrap/dist/js/bootstrap.min.js" />"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script type="text/javascript" src="<spring:url value="/resource/metisMenu/dist/metisMenu.min.js" />"></script>

    <!-- Custom Theme JavaScript -->
    <script type="text/javascript" src="<spring:url value="/resource/dist/js/sb-admin-2.js" />"></script>
   

</body>

</html>
