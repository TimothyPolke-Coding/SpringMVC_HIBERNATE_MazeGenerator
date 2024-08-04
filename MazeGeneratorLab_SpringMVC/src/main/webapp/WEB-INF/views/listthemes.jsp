<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Themes List</title>
		<style><%@include file="/resources/css/listthemes_style.css"%></style>
	</head>
	<body>
		<% ResourceBundle resource = ResourceBundle.getBundle("resource");
			String url = resource.getString("url");
			String port = resource.getString("port");
			String directory = resource.getString("directory");
		%>
		<p><a href="<%=url%><%=port%><%=directory%>">Home</a></p>
		<h1>Themes List</h1>
		<p><a href="add">Add New Theme</a></p>
		<table width="50%" border="1" cellspacing="1" cellpadding="10">
			<tr>
				<th>Theme ID</th>
				<th>Theme Name</th>
				<th>Theme Creation/Edit Date</th>	
				<th>Wall Color (HEXIDECIMAL)</th>
				<th>Background Color (HEXIDECIMAL)</th> 
				<th>Highlight Color (HEXIDECIMAL)</th>
				<th>Action</th> 
			</tr>
			<c:forEach var="vtheme" items="${lstthemes}">
				<tr>
					<td>${vtheme.getThemeID()}</td>
					<td>${vtheme.getThemeAlias()}</td>
					<td>${vtheme.getThemeDate()}</td>
					<td>#${StringUtils.leftPad(Integer.toString(vtheme.getForeground_red(),16),2,"0").toUpperCase()}${StringUtils.leftPad(Integer.toString(vtheme.getForeground_green(),16),2,"0").toUpperCase().toUpperCase().toUpperCase()}${StringUtils.leftPad(Integer.toString(vtheme.getForeground_blue(),16),2,"0").toUpperCase().toUpperCase()}</td>
					<td>#${StringUtils.leftPad(Integer.toString(vtheme.getBackground_red(),16),2,"0").toUpperCase()}${StringUtils.leftPad(Integer.toString(vtheme.getBackground_green(),16),2,"0").toUpperCase()}${StringUtils.leftPad(Integer.toString(vtheme.getBackground_blue(),16),2,"0").toUpperCase()}</td>
					<td>#${StringUtils.leftPad(Integer.toString(vtheme.getHighlight_red(),16),2,"0").toUpperCase()}${StringUtils.leftPad(Integer.toString(vtheme.getHighlight_green(),16),2,"0").toUpperCase()}${StringUtils.leftPad(Integer.toString(vtheme.getHighlight_blue(),16),2,"0").toUpperCase()}</td>
					<td><a href="edit/${vtheme.getThemeID()}">Edit</a> | <a href="delete/${vtheme.getThemeID()}" onclick="return confirm('Are you Sure you want to delete this record?')">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>