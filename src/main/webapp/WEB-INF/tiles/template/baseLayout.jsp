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
   
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <tiles:insertAttribute name="navigation" />
        <div id="page-wrapper">
            <div class="row">
                	<div class="col-lg-12">
                		<tiles:insertAttribute name="body" />
                	</div>
            </div>
        </div>

    </div>

</body>

</html>
