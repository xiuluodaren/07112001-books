<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>图书管理系统</title>

</head>

<frameset rows="70,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="<%=path %>/admin/top.jsp" name="topFrame"  noresize="noresize" id="topFrame" />
  <frameset rows="*" cols="188,*" framespacing="0" frameborder="no" border="0">
    <frame src="<%=path %>/admin/left.jsp" name="leftFrame"  noresize="noresize" id="leftFrame" />
    <frameset rows="73,*" cols="*">
      <frame src="<%=path %>/admin/right_top.jsp" name="mainFrame" id="mainFrame" />
      <frame src="" name="right" id="right" />
    </frameset>
  </frameset>
</frameset>
<body>
</body>
</html>