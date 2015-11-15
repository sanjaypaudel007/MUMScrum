<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript" src="<spring:url value="/resource/dist/js/items_list.js" />"></script>
<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">Release Backlog</h4>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-6">
		<div class="panel panel-default">
			<div class="panel-heading"><i class="fa fa-list fa-fw"></i>Start working in Release</div>
			<div class="panel-body">
				<form:form modelAttribute="releaseBacklog">
					<dl class="dl-horizontal">
					  <dt>Name: </dt><dd><c:out value="${releaseBacklog.name}"></c:out></dd>
					  <dt>Description: </dt><dd><c:out value="${releaseBacklog.description}"></c:out></dd>
					  <dt>Start Date:  </dt><dd><form:input path="startDate"/></dd>
					  <dt>End Date: </dt><dd><form:input path="deadline"/></dd>
					  <dt>Scrum Master: </dt>
					  <dd>
					  	<form:select path="scrumMaster.id">
					  		<form:option value="-10">--Select Scrum Master --</form:option>
					  		<form:options items="${scrumMasters }" itemValue="id" itemLabel="fullName"></form:options>
					  	</form:select>			  				  
					  </dd>
					  <dt>Product Backlog: </dt><dd><c:out value="${releaseBacklog.productBacklog.name}"></c:out></dd>
					</dl>
					<input type="submit" value="Start" class="btn btn-outline btn-default btn-xs"/>
					<input type="button" onclick="document.location.href='<spring:url value="/releasebacklog/detail/${releaseBacklog.id }" />'" 
						class="btn btn-outline btn-default btn-xs" value="Back">
				</form:form>
			</div>			
		</div>
	</div>
 </div>
	 <!-- datetimepicker CSS -->
 <link href="<spring:url value="/resource/bootstrap-datepicker/bootstrap-datetimepicker.css" />" rel="stylesheet" type="text/css" />
  <!-- datepicker js -->
  <script type="text/javascript" src="<spring:url value="/resource/bootstrap-datepicker/moment-with-locales.js" />"></script>
 
 <script type="text/javascript" src="<spring:url value="/resource/bootstrap-datepicker/bootstrap-datetimepicker.js" />"></script>
 
<script>
$(document).ready(function(){
	
	$(function() {
		 $(function () {
             $('#startDate,#deadline').datetimepicker({
            	 format: 'MM/DD/YYYY'
            	 
             });
         });
	});
	
	
});
</script>  
