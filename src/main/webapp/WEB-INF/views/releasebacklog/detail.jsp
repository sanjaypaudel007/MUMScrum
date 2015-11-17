<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript" src="<spring:url value="/resource/dist/js/items_list.js" />"></script>
<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">Release Backlog Detail</h4>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-6">
		<div class="panel panel-default">
			<div class="panel-heading"><i class="fa fa-list fa-fw"></i>Detail</div>
			<div class="panel-body">
				<dl class="dl-horizontal">
				  <dt>Name: </dt><dd><c:out value="${releaseBacklog.name}"></c:out></dd>
				  <dt>Description: </dt><dd><c:out value="${releaseBacklog.description}"></c:out></dd>
				  <dt>Date: </dt><dd><fmt:formatDate value="${releaseBacklog.startDate }" pattern="MM/dd/yyyy"/>  - <fmt:formatDate value="${releaseBacklog.deadline }" pattern="MM/dd/yyyy"/></dd>
				  <dt>Scrum Master: </dt><dd><c:out value="${releaseBacklog.scrumMaster == null? 'Not Assigned': releaseBacklog.scrumMaster.fullName}"></c:out></dd>
				  <%-- <dt>Product Backlog: </dt><dd><c:out value="${releaseBacklog.productBacklog.name}"></c:out></dd> --%>
				  <dt>Status: </dt><dd><c:out value="${releaseBacklog.status }"/></dd>
				</dl>
				<security:authorize access="hasRole('SCRUM_MASTER')"> 
				<input type="button" onclick="document.location.href='<spring:url value="/releasebacklog/edit/${releaseBacklog.id}" />'" 
					class="btn btn-outline btn-default btn-xs" value=" Edit ">
				<input type="button" onclick="document.location.href='<spring:url value="/releasebacklog/startwork/${releaseBacklog.id}" />'" 
					class="btn btn-outline btn-default btn-xs" value="Start Work">
				</security:authorize>
				<input type="button" onclick="document.location.href='<spring:url value="/releasebacklog/list" />'" 
					class="btn btn-outline btn-default btn-xs" value=" List ">
			</div>			
		</div>
	</div>
	
	 <div class="col-lg-6">
		<div class="panel panel-info">
		<div class="panel-heading" ><!-- onclick="showReleaseList('${productBacklog.id}')"> -->
		<i class="fa fa-list fa-fw"></i>User Stories</div>
			<div class="panel-body">
				<security:authorize access="hasRole('SCRUM_MASTER')"> 
					<div class="pull-right"><a href="<spring:url value="/userstory/add?releasebacklog=${releaseBacklog.id}" />" class="btn btn-outline btn-primary btn-xs" type="submit">Add</a></div>
				</security:authorize>
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
                                    	<c:forEach items="${addedUserStories}" var="item" varStatus="count">
										    <tr id="row-${count.count}">
										      <td>${count.count}</td>
										      <td>${item.name}</td>
										      <td>${item.status}</td>
										      <td>${item.priority }</td>
										      <td align="right">										      
											      <a href="<spring:url value="/userstory/detail/${item.id}" />" title="Detail"><i class="fa fa-eye fa-fw"></i></a>
											       <security:authorize access="hasRole('SCRUM_MASTER')">
											       		<a href="<spring:url value="/userstory/edit/${item.id}" />" title="Edit"><i class="fa fa-edit fa-fw"></i></a>
											       		<a title="Delete" onclick="return false;" data-action="delete-toggle" data-target="row-${count.count}" href="<spring:url value="/userstory/delete/${item.id}" />"><i class="fa  fa-trash-o fa-fw"></i></a>
											      </security:authorize>
										      </td>										   
										    </tr>										    
										    </c:forEach>
                                        
                                    </tbody>
                                </table>
			</div>
		</div>
	 </div>
 
	 <div class="col-lg-6">
		<div class="panel panel-info">
		<div class="panel-heading" ><!-- onclick="showReleaseList('${productBacklog.id}')"> -->
		<i class="fa fa-list fa-fw"></i>Sprints</div>
			<div class="panel-body">
			 <security:authorize access="hasRole('SCRUM_MASTER')">
				<div class="pull-right"><a href="<spring:url value="/sprint/add/${ releaseBacklog.id}" />" class="btn btn-outline btn-primary btn-xs" type="submit">Add</a></div>
				</security:authorize>
			 	 <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>Status</th>
                                            <th>Date</th>
                                            <th align="center">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${sprints}" var="item" varStatus="count">
										    <tr id="row-${count.count}">
										      <td>${count.count}</td>
										      <td>${item.name}</td>
										      <td>${item.description}</td>
										      <td>${item.status}</td>
										        <td><fmt:formatDate value="${item.startDate }" pattern="MM/dd/yyyy"/>  - <fmt:formatDate value="${item.endDate}" pattern="MM/dd/yyyy"/></td>
										      <td align="right">
										      <a href="<spring:url value="/sprint/detail/${item.id}" />" title="Detail"><i class="fa fa-eye fa-fw"></i></a>
										      			 <security:authorize access="hasRole('SCRUM_MASTER')">
										      	<a href="<spring:url value="/sprint/edit/${item.id}" />" title="Edit"><i class="fa fa-edit fa-fw"></i></a>
										      	
										      	<a title="Delete" onclick="return false;" data-action="delete-toggle" data-target="row-${count.count}"
										      	href="<spring:url value="/sprint/delete/${item.id}" />"><i class="fa  fa-trash-o fa-fw"></i></a>
										      	</security:authorize>
										      	</td>
										   
										    </tr>
										    </c:forEach>
                                        
                                    </tbody>
                                </table>
                                 
			</div>
		</div>
	 </div>
 </div>
	  
