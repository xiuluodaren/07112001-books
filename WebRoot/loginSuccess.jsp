<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String types = request.getSession().getAttribute("types").toString();
String str ="";
if(!types.equals("1")){
	//str = request.getSession().getAttribute("str").toString();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
       <script type="text/javascript">
           function tiao()
           {
             var types = '<%=types%>';
			if(types!=1){
				//alert('<%=str%>');
			}
			window.location.href="<%=path %>/admin/main.jsp";
               
           }
           
           setTimeout(tiao,1300)
       </script>
       <br> <br> <br> <br> <br> <br> <br> <br> <br>
       <center><img src="<%=path %>/images/loading32.gif">页面跳转中</center>
  </body>
</html>
