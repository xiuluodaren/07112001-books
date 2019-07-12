<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>图书管理系统</title>
<link href="images/login.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">

function check()
{                                                                                         
    if(document.ThisForm.username.value=="")
	 {
	 	alert("请输入用户名");
		document.ThisForm.username.focus();
		return false;
	 }
	 if(document.ThisForm.password.value=="")
	 {
	 	alert("请输入密码");
		document.ThisForm.password.focus();
		return false;
	 }
	//登入
	 document.ThisForm.submit();
}


</script>
<body>
 <form method="post" action="<%=path %>/LoginServlet" method="post" name="ThisForm">
    <div id="login">
	
	     <div id="top">
		      <div id="top_left"><img src="images/login_03.gif" /></div>
			  <div id="top_center"></div>
		 </div>
		 
		 <div id="center">
		      <div id="center_left"></div>
			  <div id="center_middle">
			       <div id="user">用 户
			         <input type="text" name="username" />
			       </div>
				   <div id="password">密   码
				     <input type="password" name="password" />
				   </div>
				   <div id="btn"><a onclick="check()" href="#">登录</a><a href="#">清空</a></div>
			  
			  </div>
			  <div id="center_right"></div>		 
		 </div>
		 <div id="down">
		      <div id="down_left">
			      <div id="inf">
                       <span class="inf_text">版本信息</span>
					   <span class="copyright">图书管理系统 2019 v1.0</span>
			      </div>
			  </div>
			  <div id="down_center"></div>		 
		 </div>

	</div>
	</form>
</body>
</html>
