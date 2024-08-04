<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Sizes List</title>
		<style><%@include file="/resources/css/listsizes_style.css"%></style>
	</head>
	<body>
		<% ResourceBundle resource = ResourceBundle.getBundle("resource");
			String url = resource.getString("url");
			String port = resource.getString("port");
			String directory = resource.getString("directory");
		%>
		<p><a href="<%=url%><%=port%><%=directory%>">Home</a></p>
		<h1>Sizes List</h1>
		<p><a href="add">Add New Size</a></p>
		<table width="50%" border="1" cellspacing="1" cellpadding="10">
			<tr>
				<th>Size ID</th>
				<th>Size Name</th>
				<th>Size Creation/Edit Date</th>
				<th>Number of Columns</th>
				<th>Number of Rows</th> 
				<th>Thickness of Walls (PIXELS)</th>
				<th>Thickness of Cells (PIXELS)</th>
				<th>Action</th> 
			</tr>
			<c:forEach var="vsize" items="${lstsizes}">
				<tr>
					<td>${vsize.getSizeID()}</td>
					<td>${vsize.getSizeAlias()}</td>
					<td>${vsize.getSizeDate()}</td>
					<td>${vsize.getColumnCount()}</td>
					<td>${vsize.getRowCount()}</td>
					<td>${vsize.getWallSize()}</td>
					<td>${vsize.getCellSize()}</td>
					<td><a href="edit/${vsize.getSizeID()}">Edit</a> | <a href="delete/${vsize.getSizeID()}" onclick="return confirm('Are you Sure you want to delete this record?')">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>