<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<form role="form" name="frmlogin" id="frmlogin"
	action="<spring:url value="/postLogin" />" method="post">
	
	<p class="text-danger"><c:if test="${not empty error}">Unable to login: ${error}</c:if></p>
	<p class="text-danger">  <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></p>
	
	
	<fieldset>
		<div>
			<input placeholder="Username" name="username" class="form-control"
				type="text" autofocus>
		</div>
		<div>
			<input placeholder="Password" name="password" class="form-control"
				type="password" value="">
		</div>
		<!-- div class="checkbox">
			<label> <input name="remember" type="checkbox"
				value="Remember Me">Remember Me
			</label>
		</div> -->
		<!-- Change this to a button or input when using this as a form -->
		<input type="submit" name="btnLoginSubmit" value="Login" class="btn btn-success btn-block"/>
		<input type="reset" name="btnLoginReset" value="Reset" class="btn btn-danger btn-block"/>
	</fieldset>

</form>