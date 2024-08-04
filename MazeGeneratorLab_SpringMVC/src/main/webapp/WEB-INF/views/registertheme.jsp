<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Theme Registration</title>
		<style><%@include file="/resources/css/registertheme_style.css"%></style>
	</head>
	<body>
		<h1>Theme Registration</h1>
		<f:form method="post" action="http://72.189.233.48:8081/MazeGeneratorLab/themes/save" modelAttribute="theme">
			
			<f:hidden path="themeID"></f:hidden>
			<p>Theme Name:<f:input path="themeAlias" type="text" required="true"></f:input></p>
			<p>CREATION/EDIT DATE SET AUTOMATICALLY:<f:radiobutton path="themeDate" value="<%=new SimpleDateFormat(\"MM-dd, yyyy @ hh:mm:ss a, zzzz\").format(new java.util.Date())%>" checked="checked" disabled="disabled"></f:radiobutton></p>
			
			<p>Wall Color (RGB values):</p>
			<p>
				RED:
				<f:select path="foreground_red" required="true">
					<f:option value="">Select Value</f:option>
					<c:forEach begin="0" end="255" step="1" varStatus="foreground_red_loop">
						<f:option value="${foreground_red_loop.index}">${foreground_red_loop.index}</f:option>
					</c:forEach>
				</f:select>
			
				Green: 
				<f:select path="foreground_green" required="true">
					<f:option value="">Select Value</f:option>
					<c:forEach begin="0" end="255" step="1" varStatus="foreground_green_loop">
						<f:option value="${foreground_green_loop.index}">${color_foreground_green_loop.index}</f:option>
					</c:forEach>
				</f:select>
			
				Blue:
				<f:select path="foreground_blue" required="true">
					<f:option value="">Select Value</f:option>
					<c:forEach begin="0" end="255" step="1" varStatus="foreground_blue_loop">
						<f:option value="${foreground_blue_loop.index}">${foreground_blue_loop.index}</f:option>
					</c:forEach>
				</f:select>
			</p>
			
			<p>Background Color (RGB values):</p>
			<p>
				RED:
				<f:select path="background_red" required="true">
					<f:option value="">Select Value</f:option>
					<c:forEach begin="0" end="255" step="1" varStatus="background_red_loop">
						<f:option value="${background_red_loop.index}">${background_red_loop.index}</f:option>
					</c:forEach>
				</f:select>
			
				Green:
				<f:select path="background_green" required="true">
					<f:option value="">Select Value</f:option>
					<c:forEach begin="0" end="255" step="1" varStatus="background_green_loop">
						<f:option value="${background_green_loop.index}">${background_green_loop.index}</f:option>
					</c:forEach>
				</f:select>
			
				Blue:
				<f:select path="background_blue" required="true">
					<f:option value="">Select Value</f:option>
					<c:forEach begin="0" end="255" step="1" varStatus="background_blue_loop">
						<f:option value="${background_blue_loop.index}">${background_blue_loop.index}</f:option>
					</c:forEach>
				</f:select>
			</p>
			
			<p>Highlight Color (RGB values):</p>
			<p>
				RED:
				<f:select path="highlight_red" required="true">
					<f:option value="">Select Value</f:option>
					<c:forEach begin="0" end="255" step="1" varStatus="highlight_red_loop">
						<f:option value="${highlight_red_loop.index}">${highlight_red_loop.index}</f:option>
					</c:forEach>
				</f:select>
			
				Green:
				<f:select path="highlight_green" required="true">
					<f:option value="">Select Value</f:option>
					<c:forEach begin="0" end="255" step="1" varStatus="highlight_green_loop">
						<f:option value="${highlight_green_loop.index}">${highlight_green_loop.index}</f:option>
					</c:forEach>
				</f:select>
			
				Blue:
				<f:select path="highlight_blue" required="true">
					<f:option value="">Select Value</f:option>
					<c:forEach begin="0" end="255" step="1" varStatus="highlight_blue_loop">
						<f:option value="${highlight_blue_loop.index}">${background_blue_loop.index}</f:option>
					</c:forEach>
				</f:select>
			</p>
			<p><Button type="Submit">Submit</Button></p>
		</f:form>
	</body>
</html>