<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript" src="<spring:url value="/resource/dist/js/items_list.js" />"></script>
<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">Sprint Detail</h4>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-6">
		<div class="panel panel-default">
			<div class="panel-heading"><i class="fa fa-list fa-fw"></i>Detail</div>
			<div class="panel-body">
				<dl class="dl-horizontal">
				  <dt>Name: </dt><dd><c:out value="${sprint.name}"></c:out></dd>
				  <dt>Description: </dt><dd><c:out value="${sprint.description}"></c:out></dd>
				   <dt>Date: </dt><dd><fmt:formatDate value="${sprint.startDate }" pattern="MM/dd/yyyy"/>  - <fmt:formatDate value="${sprint.endDate }" pattern="MM/dd/yyyy"/></dd>
				   <dt>Status:</dt><dd><c:out value="${sprint.status }"></c:out></dd>
				</dl>
				<security:authorize access="hasRole('SCRUM_MASTER')"> 
					<input type="button" onclick="document.location.href='<spring:url value="/sprint/edit/${sprint.id}" />'" class="btn btn-outline btn-default btn-xs" value=" Edit ">
					<input type="button" onclick="document.location.href='<spring:url value="/sprint/startwork/${sprint.id}" />'" class="btn btn-outline btn-default btn-xs" value="Start Work">
				</security:authorize>

				<input type="button" onclick="document.location.href='<spring:url value="/sprint/list" />'" class="btn btn-outline btn-default btn-xs" value=" List ">
  
			</div>
		</div>
	</div>
	</div>
		 <div class="col-lg-6">
		<div class="panel panel-info">
		<div class="panel-heading" >
		<i class="fa fa-list fa-fw"></i>User Stories</div>
			<div class="panel-body">
				 <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Name</th>
                                            <th>Status</th>
                                            <th>Priority</th>
                                            <th align="center">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<tr><td colspan="5"><i><b>In sprint</b></i></td></tr>
                                    	<c:forEach items="${addedUserStories}" var="item" varStatus="count">
										    <tr id="row-${count.count}" style="background-color:CCFFFF;">
										      <td>${count.count}</td>
										      <td>${item.name}</td>
										      <td>${item.status}</td>
										      <td>${item.priority }</td>
										      <td align="right">
										      <a href="<spring:url value="/userstory/detail/${item.id}" />" title="Detail"><i class="fa fa-eye fa-fw"></i></a>
										      <security:authorize access="hasRole('SCRUM_MASTER')">
										      	<c:if test="${item.status == 'IN_PROGRESS'}">
											      	<a title="Remove from Sprint" onclick="return confirm('Are you sure to remove User Story from Sprint?');" href="<spring:url value="/sprint/${sprint.id}/removeus/${item.id}" />"><i class="fa  fa-remove fa-fw"></i></a>
											    </c:if>
										      </security:authorize>									      	
										      </td>										   
										    </tr>										    
										    </c:forEach>
										    <tr><td colspan="5"><i><b>In release backlog</b></i></td></tr>
										    <c:forEach items="${notAddedUserStories}" var="item" varStatus="count">
										    <tr id="row-${count.count}">
										      <td>${count.count}</td>
										      <td>${item.name}</td>
										      <td>${item.status}</td>
										      <td>${item.priority }</td>
										      <td align="right">
										      <a href="<spring:url value="/userstory/detail/${item.id}" />" title="Detail"><i class="fa fa-eye fa-fw"></i></a>
										      	<security:authorize access="hasRole('SCRUM_MASTER')">
										      		<c:if test="${item.status == 'ESTIMATED'}">
										      			<a href="<spring:url value="/sprint/${sprint.id}/addus/${item.id}" />" title="Add to Sprint"><i class="fa fa-plus fa-fw"></i></a>
										      		</c:if>
										      	</security:authorize>								      	
										      	</td>
										   
										    </tr>
										    </c:forEach>
                                        
                                    </tbody>
                                </table>
			</div>
		</div>
	 </div>
	<!-- <div class="col-lg-6">
		<div class="panel panel-info">
		<div class="panel-heading"><i class="fa fa-list fa-fw"></i>User Stroies</div>
			<div class="panel-body">
			
			</div>
		</div>
	 </div> -->
	 <%--   <div class="col-lg-6">
		<div class="panel panel-info">
		<div class="panel-heading" ><!-- onclick="showSprintList('${releaseBacklog.id}')"> -->
		<i class="fa fa-list fa-fw"></i>UserStories</div>
			<div class="panel-body">
				<div class="pull-right"><a href="<spring:url value="/userStory/add/${ sprint.id}" />" class="btn btn-outline btn-primary btn-xs" type="submit">Add</a></div>
				 <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <!--<th>Date</th> -->
                                            <th align="center">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${userStories}" var="item" varStatus="count">
										    <tr id="row-${count.count}">
										      <td>${count.count}</td>
										      <td>${item.name}</td>
										      <td>${item.description}</td>
										     <!--  <td><fmt:formatDate value="${item.startDate }" pattern="MM/dd/yyyy"/>  - <fmt:formatDate value="${item.endDate }" pattern="MM/dd/yyyy"/></td> -->
										      <td align="right">
										      	<a href="<spring:url value="/userStory/edit/${item.id}" />" title="Edit"><i class="fa fa-edit fa-fw"></i></a>
										      	<a href="<spring:url value="/userStory/detail/${item.id}" />" title="Detail"><i class="fa fa-eye fa-fw"></i></a>
										      	<a title="Delete" onclick="return false;" data-action="delete-toggle" data-target="row-${count.count}"
										      	href="<spring:url value="/userStory/delete/${item.id}" />"><i class="fa  fa-trash-o fa-fw"></i></a></td>
										   
										    </tr>
										    </c:forEach>
                                        
                                    </tbody>
                                </table>
                                 
			</div>
		</div> 
	 </div> 
 </div> 
	  
 --%>