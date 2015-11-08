<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<nav role="navigation">
            <div>
                <a href="<spring:url value="/" />"><h1>MUM Scrum</h1></a>
            </div>
            <!-- /.navbar-header -->

           <!-- <tiles:insertAttribute name="header" /> --> 
           <%@ include file="/WEB-INF/tiles/template/header.jsp" %>
            <!-- /.navbar-top-links -->

            <div role="navigation">
                    <ul id="side-menu">
                        <li>
                            <div>
                                <input type="text" placeholder="Search...">
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="<spring:url value="/" />">Dashboard</a>
                        </li>
                        <li>
                            <a href="#">Progress</a>
                            <ul>
                               <li >                                	
                                    <a href="<spring:url value="/worklog/" />">Estimations</a>
                                </li>
                                <li >                                	
                                    <a href="<spring:url value="/sprint/" />">Burndown Chart</a>
                                </li>
                               
                            </ul>
                        </li>
                        <li>
                            <a href="<spring:url value="/userstory/" />">User Story</a>
                        </li>
                        <li>
                            <a href="<spring:url value="/releasebacklog/list" />">Release Backlog</a>
                        </li>
                        <li>
                            <a href="<spring:url value="/sprint/" />">Sprint</a>
                        </li>
                        <li>
                            <a href="<spring:url value="/employee" />">Employee</a>
                        </li>
                        
                    </ul>
            </div>
        </nav>
