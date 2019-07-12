<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,com.book.util.*" %>
<%
String path = request.getContextPath();
DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
	String sql = " select * from booktype ";
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				ResultSet rs = pstmt1.executeQuery();
				
					String sql2 = " select * from sj ";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs2 = pstmt2.executeQuery();

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
</head>

<body>
<form method="post" action="<%=path %>/BookAddServlet" method="post" name="ThisForm" enctype="multipart/form-data">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#c9c9c9">
      <tr>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">书名</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1"> <input type="text" id="foodname" name="foodname" /></span></strong></div></td>
      </tr>
      <tr>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">图书类型</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">
           <select id="btid" name="btid">
        <%while(rs.next()){ %>
        <option value="<%=rs.getInt("id") %>"><%=rs.getString("names") %></option>
        <%} %>
        </select>
         </span></strong></div></td>
      </tr>
        <tr>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">所在书架</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">
           <select id="sjid" name="sjid">
        <%while(rs2.next()){ %>
        <option value="<%=rs2.getInt("id") %>"><%=rs2.getString("names") %></option>
        <%} %>
        </select>
         </span></strong></div></td>
      </tr>
      <tr>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">图片</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">
         
          <input type="file" name="pwd" size="20"/>
         </span></strong></div></td>
      </tr>
      <tr>
        <td height="22" bgcolor="#FFFFFF" colspan="2"><div align="center"><strong><span class="STYLE1"> <input type="submit" value="提交"></span></strong></div></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>

