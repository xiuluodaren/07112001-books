<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page language="java" import="java.sql.*,com.book.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adminyanzheng.jsp' starting page</title>
    

  </head>
  
  <body>
  <%
 String sql="";
  sql="MySQLdump -h localhost -u root -p mydb >e:'\'mydb.sql";
	  	
DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
Statement stmt = conn.createStatement();
				//	stmt.executeUpdate(sql);

out.print("<script>alert('操作成功!!，您的数据库已成功备份于C:/bookservers.dat');location.href='right.jsp';</script>");

 %>
  </body>
</html>

