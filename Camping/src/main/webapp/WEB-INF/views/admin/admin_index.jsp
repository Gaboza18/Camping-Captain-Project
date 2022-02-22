<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="admin_header.jsp" %>

<c:choose>
	<c:when test="${loginAdmin.status eq 1}">
		<%@ include file="master_admin_menu.jsp" %>
	</c:when>
	
	<c:when test="${loginAdmin.status eq 2}">
		<%@ include file="manager_admin_menu.jsp" %>
	</c:when>
</c:choose> 

<%@ include file="admin_footer.jsp" %>

