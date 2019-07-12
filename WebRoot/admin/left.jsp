<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String types = request.getSession().getAttribute("roles").toString();
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="images/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="left">
		<div id="left_menu"></div>
		<div id="left_tree">
			<div id="tree_text">
				<li class="tree_li"><span class="list_img"><img
						src="images/list_img.gif" />
				</span><a href="<%=path%>/admin/adminPwdUpdate.jsp" target="right">密码修改</a>
				</li>
				
				<%if(types.equals("1")){ %>
				
				
				 <li class="tree_li"><span class="list_img"><img
						src="images/list_img.gif" />
				</span><a href="<%=path%>/AdminServlet?mode=list" target="right">管理员管理</a>
				</li>
				<li class="tree_li"><span class="list_img"><img
						src="images/list_img.gif" />
				</span><a href="<%=path%>/SjServlet?mode=list" target="right">书架管理</a>
				</li>
				<li class="tree_li"><span class="list_img"><img
						src="images/list_img.gif" />
				</span><a href="<%=path%>/JbServlet?mode=list" target="right">基本信息设置</a>
				</li>
				<li class="tree_li"><span class="list_img"><img
						src="images/list_img.gif" />
				</span><a href="<%=path%>/BookTypeServlet?mode=list" target="right">图书类型管理</a>
				</li>
					<li class="tree_li"><span class="list_img"><img
						src="images/list_img.gif" />
				</span><a href="<%=path%>/UserTypeServlet?mode=list" target="right">读者类型管理</a>
				</li>
				<li class="tree_li"><span class="list_img"><img
						src="images/list_img.gif" />
				</span><a href="<%=path%>/UserServlet?mode=list" target="right">读者管理</a>
				</li>
				
				<li class="tree_li"><span class="list_img"><img
						src="images/list_img.gif" />
				</span><a href="<%=path%>/BookServlet?mode=list" target="right">图书管理 </a>
				</li>
				<li class="tree_li"><span class="list_img"><img
						src="images/list_img.gif" />
				</span><a href="<%=path%>/BookServlet?mode=brlist2" target="right">正在借阅 </a>
				</li>
				<li class="tree_li"><span class="list_img"><img
						src="images/list_img.gif" />
				</span><a href="<%=path%>/BookServlet?mode=brlslist" target="right">借阅历史 </a>
				</li>
				<%}else{ %>
				
				<li class="tree_li"><span class="list_img"><img
						src="images/list_img.gif" />
				</span><a href="<%=path%>/BookServlet?mode=brlist" target="right">图书借阅 </a>
				</li>
				
				<li class="tree_li"><span class="list_img"><img
						src="images/list_img.gif" />
				</span><a href="<%=path%>/BookServlet?mode=mybrlist" target="right">我的借阅 </a>
				</li>
				<li class="tree_li"><span class="list_img"><img
						src="images/list_img.gif" />
				</span><a href="<%=path%>/BookServlet?mode=mybrlslist" target="right">我的借阅历史 </a>
				</li>
				<%} %>
			</div>

		</div>
		<div id="tree_down"></div>
</body>
</html>
