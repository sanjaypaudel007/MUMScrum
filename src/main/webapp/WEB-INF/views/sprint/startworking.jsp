<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript" src="<spring:url value="/resource/dist/js/items_list.js" />"></script>
<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">Sprint</h4>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-6">
		<div class="panel panel-default">
			<div class="panel-heading"><i class="fa fa-list fa-fw"></i>Start working in Sprint</div>
			<div class="panel-body">
				<form:form modelAttribute="sprint">
					
					<dl class="dl-horizontal">
					  <dt>Name: </dt><dd><c:out value="${sprint.name}"></c:out></dd>
					  <dt>Description: </dt><dd><c:out value="${sprint.description}"></c:out></dd>
					  <dt>Start Date:  </dt><dd><form:input path="startDate"/></dd>
					  <dt>End Date: </dt><dd><form:input path="endDate"/></dd>
					</dl>
					<div class="panel-body">
						 <table class="table">
						 	<thead>
                                <tr>
                                    <th>#</th>
                                    <th>User Story</th>
                                    <th>Priority</th>
                                    <th>Developer</th>
                                    <th>Tester</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${sprint.userStories}" var="item" varStatus="count">
                            		<tr id="row-${count.count}" style="background-color:CCFFFF;">
                            			<td>${count.count}</td>
                            			<td>
                            				${item.name}
                            				<form:hidden path="userStories[${count.index }].id"/>
                            			</td>
										<td>${item.priority }</td>
										<td>
											<form:select path="userStories[${count.index }].developer.id">
												<c:forEach items="${developers }" var="dev" varStatus="devCount">
													<c:choose>
														<c:when test="${devCount.count == 1 }">
															<option value="${dev.id }" selected>${dev.fullName }</option>
														</c:when>
														<c:otherwise>
															<option value="${dev.id }">${dev.fullName }</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</form:select>
										</td>
										<td>
											<form:select path="userStories[${count.index }].tester.id" >
												<c:forEach items="${testers }" var="test" varStatus="testCount">
													<c:choose>
														<c:when test="${testCount.count == 1 }">
															<option value="${test.id }" selected>${test.fullName }</option>
														</c:when>
														<c:otherwise>
															<option value="${test.id }">${test.fullName }</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</form:select>
										</td>
                            		</tr>
                            	</c:forEach>
                            </tbody>                            
						 </table>
					 </div>
					<input type="submit" value="Start" class="btn btn-outline btn-default btn-xs"/>
					<input type="button" onclick="document.location.href='<spring:url value="/sprint/detail/${sprint.id }" />'" 
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
             $('#startDate,#endDate').datetimepicker({
            	 format: 'MM/DD/YYYY'
            	 
             });
         });
	});
	
	
});
</script>  	  
