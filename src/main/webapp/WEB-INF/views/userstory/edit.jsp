<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">User Story ${title}</h4>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">


	<form:form modelAttribute="userStory" method="post" class="form">
	
	<c:set var="validationErrors"><form:errors path="*"/></c:set>
	<c:if test="${not empty validationErrors}">
	<c:forEach items="${validationErrors}" var="item" >
		<p>${item}</p>
	</c:forEach>
	</c:if> 	 
	
		<div class="row">
           <div class="col-lg-6">
        	   <spring:bind path="productBacklog.id">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Product Backlog: </label>
						<form:select id="productBacklog" name="productBacklog.id" path="productBacklog.id" 
						class="form-control cascadeDropdownn" >
						    <option value="">-- Select --</option>
						    <c:forEach var="item" items="${listProductBackLog}">
						        <form:option value="${item.id}"><c:out value="${item.name}"/></form:option>
						    </c:forEach>
						</form:select>
					    <form:errors path="productBacklog.id" class="help-block"></form:errors>
					</div>
				</spring:bind>
				<!--<spring:bind path="releaseBacklog.id">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Release Backlog: </label>
						<form:select id="releaseBacklog" name="releaseBacklog.id" path="releaseBacklog.id" class="form-control">
						    <option value="">-- Select --</option>
						    <c:forEach var="item" items="${listReleaseBackLog}">
						        <form:option value="${item.id}"><c:out value="${item.name}"/></form:option>
						    </c:forEach>
						</form:select>
					    <form:errors path="releaseBacklog.id" class="help-block"></form:errors>
					</div>
				</spring:bind>-->
           		<spring:bind path="name">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Name: </label>
					    <form:input path="name" placeholder="Name" class="form-control" />
					    <form:errors path="name" class="help-block"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="description">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Description: </label>
					    <form:textarea path="description" placeholder="Description" class="form-control" />
					    <form:errors path="description" class="help-block"></form:errors>
					</div>
				
				</spring:bind>
				    <form:hidden path="testingEstimate" value="0" />
					<form:hidden path="developmentEstimate" value="0" />
        	</div>
        </div>
		<input class="btn btn-primary" type="submit" value="${buttonName}"/>
		<input type="button" onclick="document.location.href='<spring:url value="/productbacklog" />'" 
		class="btn btn-warning" value=" Cancel ">
    
	</form:form>


	</div>
</div>
<script type="text/javascript">
$().ready(function(){
	$("#productBackloggg").on("change",function(){
		var target = "releaseBacklog";
		var url = "<spring:url value="/releasebacklog/optionlist" />"+"/"+$(this).val();
		$.ajax({
			url: url,
			type:"get",
			dataType:"json",
			async: true,
			success:function(response)
			{
				$("#"+target).empty();
				$("#"+target).append($('<option></option>').attr("value", "").text("-- Select --"));
				$.each(response, function(index, item){
					$("#"+target).append($('<option></option>').attr("value", item.key).text(item.value));
				});
			},
			error:function(result)
			{
				alert(result)
			}
		});
		
	}).change();
	
});
</script>

