<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String realname = request.getSession().getAttribute("username").toString();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
<link href="images/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="top">
	      <div id="logo"><h1>图书管理系统</h1></div>
		  <div id="user">欢迎您:<%=realname %> </div>	 
	 </div>
</body>
</html>
