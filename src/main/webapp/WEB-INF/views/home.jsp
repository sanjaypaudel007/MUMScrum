<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<title>MUM Scrum</title>
</head>
<body>
	<h1>Welcome user!</h1>

	<P>
		<a href="<spring:url value="/employee" />" >Employees</a>
	</P>
</body>
</html>
