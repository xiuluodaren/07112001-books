<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">

function check()
{                                                                                         
    window.parent.location.href="<%=path %>/LoginOutServlet";
}


</script>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
<link href="images/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
     <div id="right_top">
			        <div id="img"><img src="images/close.gif"/></div>
			        <span class="imgtext"></span>
			   <div id="loginout">
			        <div id="loginoutimg"><img src="images/loginout.gif" /></div>
			        <span class="logintext"><a onclick="check()">退出系统</a> </span>	 
			   </div>			   		
			   </div>
			   <div id="right_font"><img src="images/main_14.gif"/> </span></div>
</body>
</html>

