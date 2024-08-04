<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Size Registration</title>
		<style><%@include file="/resources/css/registersize_style.css"%></style>
	</head>
	<body>
		<h1>Size Registration</h1>
		<f:form method="post" action="http://72.189.233.48:8081/MazeGeneratorLab/sizes/save" modelAttribute="size">
			
			<f:hidden path="sizeID"></f:hidden>
			<p>Size Name:<f:input path="sizeAlias" type="text" required="true"></f:input></p>
			<p>CREATION/EDIT DATE SET AUTOMATICALLY:<f:radiobutton path="sizeDate" value="<%=new SimpleDateFormat(\"MM-dd, yyyy @ hh:mm:ss a, zzzz\").format(new java.util.Date())%>" checked="checked" disabled="disabled"></f:radiobutton></p>
			
			<p>
				Number of Columns (x Value):
				<f:select path="columnCount"  required="true">
					<f:option value="">Select Value</f:option>
					<c:forEach begin="5" end="250" step="5" varStatus="columnCount_loop">
						<f:option value="${columnCount_loop.index}">${columnCount_loop.index}</f:option>
					</c:forEach>
				</f:select>
			</p>
			<p>
				Number of Rows (y Value):
				<f:select path="rowCount" required="true">
					<f:option value="">Select Value</f:option>
					<c:forEach begin="5" end="250" step="5" varStatus="rowCount_loop">
						<f:option value="${rowCount_loop.index}">${rowCount_loop.index}</f:option>
					</c:forEach>
				</f:select>
			</p>
			
			<p>
				Thickness of Walls (pixels):
				<f:select path="wallSize" required="true">
					<f:option value="">Select Value</f:option>
					<c:forEach begin="1" end="10" step="1" varStatus="wallSize_loop">
						<f:option value="${wallSize_loop.index}">${wallSize_loop.index}</f:option>
					</c:forEach>
				</f:select>
			</p>
			<p>
				Thickness of Cells (pixels):
				<f:select path="cellSize" required="true">
					<f:option value="">Select Value</f:option>
					<c:forEach begin="10" end="100" step="1" varStatus="cellSize_loop">
						<f:option value="${cellSize_loop.index}">${cellSize_loop.index}</f:option>
					</c:forEach>
				</f:select>
			</p>
			<p><Button type="Submit">Submit</Button></p>
		</f:form>
	</body>
</html>