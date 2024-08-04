<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Maze Registration</title>
		<style><%@include file="/resources/css/registermaze_style.css"%></style>
	</head>
	<body>
		<% ResourceBundle resource = ResourceBundle.getBundle("resource");
			String url = resource.getString("url");
			String port = resource.getString("port");
			String directory = resource.getString("directory");
		%>
		<h1>Maze Registration</h1>
		<f:form method="post" action="${url}${port}${directory}save" modelAttribute="maze">
			<f:hidden path="mazeID"></f:hidden>
			<p>CREATION/EDIT DATE SET AUTOMATICALLY:<f:radiobutton path="mazeDate" value="<%=new SimpleDateFormat(\"MM-dd, yyyy @ hh:mm:ss a, zzzz\").format(new java.util.Date())%>" checked="checked" disabled="disabled"></f:radiobutton></p>
			<f:select path="size.sizeID">    
				<option value="-1">Select Size:</option>
				<c:forEach items="${sizes}" var="size">
					<option value="${size.getSizeID()}" ${maze.getSize().getSizeID() == size.getSizeID() ? 'selected="selected"' : ''}>${size.getSizeAlias()}</option>
				</c:forEach>
			</f:select>
			<f:select path="theme.themeID">    
				<option value="-1">Select Theme:</option>
				<c:forEach items="${themes}" var="theme">
					<option value="${theme.getThemeID()}" ${maze.getTheme().getThemeID() == theme.getThemeID() ? 'selected="selected"' : ''}>${theme.getThemeAlias()}</option>
				</c:forEach>
			</f:select>
			<f:hidden path="solvedImage"></f:hidden>
			<f:hidden path="unsolvedImage"></f:hidden>
			<Button type="Submit">Submit</Button>
		</f:form>
	</body>
</html>