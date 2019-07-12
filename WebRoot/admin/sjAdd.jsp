<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<style type="text/css">
<!--
.STYLE1 {font-size: 12px}
.STYLE3 {color: #707070; font-size: 12px; }
.STYLE5 {color: #0a6e0c; font-size: 12px; }
body {
	margin-top: 0px;
	margin-bottom: 0px;
}
.STYLE7 {font-size: 12}
-->
</style>

<script>
function ok(){
	
	var username = document.getElementById("username").value;
	if(username==''){
		alert("书架号不能为空！");
		return;
	}
	
	document.ThisForm.submit();
	
}


</script>



</head>

<body>
<form method="post" action="<%=path %>/SjServlet?mode=add" method="post" name="ThisForm">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#c9c9c9">
      <tr>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">书架号</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1"> <input type="text" id="username" name="username" /></span></strong></div></td>
      </tr>
      <tr>
        <td height="22" bgcolor="#FFFFFF" colspan="2"><div align="center"><strong><span class="STYLE1"> <input type="button" value="提交" onclick="ok()"></span></strong></div></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>

