<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.book.bean.*"%>
<%@page import="com.book.util.*"%>
<%
String path = request.getContextPath();
Page pageBean = (Page)request.getAttribute("page");
int pageNo = pageBean.getPageNo();
int totlePage = pageBean.getTotlePage();
int totle = pageBean.getTotle();
List list = pageBean.getList();

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
<script>



function frist()
{
		 
	 with (document.getElementById("memberForm")) {
            method = "post";
            action = "<%=path %>/BookServlet?mode=mybrlslist&pageNoStr=1";
            submit();
        }
}

function back()
{
    var pageNo= '<%=pageNo%>';
    if(pageNo<2){
 	   alert("当前已经是第一页");
 	   return;
    }else{
 	   pageNo=Number(pageNo)-1;
    }
		 with (document.getElementById("memberForm")) {
	            method = "post";
	            action = "<%=path %>/BookServlet?mode=mybrlslist&pageNoStr="+pageNo;
	            submit();
	        }
}


function next()
{
	   var pageNo= '<%=pageNo%>';
	   var totlePage='<%=totlePage%>';
    if(pageNo==totlePage){
 	   alert("当前已经是最后一页");
 	   return;
    }else{
 	   pageNo=Number(pageNo)+1;
    }  
    with (document.getElementById("memberForm")) {
        method = "post";
        action = "<%=path %>/BookServlet?mode=mybrlslist&pageNoStr="+pageNo;
        submit();
    }
}

function last()
{ 
	   var totlePage='<%=totlePage%>';
		with (document.getElementById("memberForm")) {
            method = "post";
            action = "<%=path %>/BookServlet?mode=mybrlslist&pageNoStr="+totlePage;
            submit();
        }
	}
	
	
function checkAll() {
    for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
        document.getElementsByName("selectFlag")[i].checked = document.getElementById("ifAll").checked;
    }
}

function add() {
    window.self.location = "admin/foodAdd.jsp";
}

function modify() {
    var count = 0;
    var j = 0;
    for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
        if (document.getElementsByName("selectFlag")[i].checked) {
            j = i;
            count++;
        }
    }
    if (count == 0) {
        alert("请选择需要修改的数据！");
        return;
    }
    if (count > 1) {
        alert("一次只能修改一个数据！");
        return;
    }
    if (count == 1) {
        with (document.getElementById("memberForm")) {
            method = "post";
            action = "<%=path %>/BookServlet?mode=modifyBefore";
            submit();
        }
    }
}
function xj() {
    var flag = false;
    for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
        if (document.getElementsByName("selectFlag")[i].checked) {
            flag = true;
        }
    }
    if (!flag) {
        alert("请选择需要借阅的数据！");
        return;
    }
    if (window.confirm("确认借阅吗？")) {
        with (document.getElementById("memberForm")) {
            method = "post";
            action = "<%=path %>/BookServlet?mode=xj";
            submit();
        }
    }
}
function deletes() {
    var flag = false;
    for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
        if (document.getElementsByName("selectFlag")[i].checked) {
            flag = true;
        }
    }
    if (!flag) {
        alert("请选择需要归还的数据！");
        return;
    }
    if (window.confirm("确认归还吗？")) {
        with (document.getElementById("memberForm")) {
            method = "post";
            action = "<%=path %>/BookServlet?mode=gh";
            submit();
        }
    }
}

function search(){
        with (document.getElementById("memberForm")) {
            method = "post";
            action = "<%=path %>/BookServlet?mode=mybrlist";
            submit();
        }
}
</script>
<body>
 <form id="memberForm" name="ThisForm">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>&nbsp;我的借阅历史</td>
        <td style="padding-right:10px;"><div align="right">
          <table border="0" align="right" cellpadding="0" cellspacing="0">
            <tr>
            </tr>
          </table>
        </div></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#c9c9c9">
      <tr>
       <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1"></span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">书名</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">借阅时间</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">应该归还时间</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">续借次数</span></strong></div></td>
      </tr>
      		<%
			for(int i = 0;i<list.size();i++){
				Br bean = (Br)list.get(i);
			%>
      
      <tr>
       <td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3"><input type="checkbox" name="selectFlag" id="selectFlag" class="checkbox1"
                                       value="<%=bean.getId()%>"></span></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3"><%=ValueBean.getValueByBId(bean.getBid()) %></span></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3"><%=bean.getBtimes() %></span></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3"><%=bean.getEtimes() %></span></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3"><%=bean.getXjcs() %></span></div></td>
      </tr>
      <%} %>
      
    </table></td>
  </tr>
  <tr>
    <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="75%" valign="top" class="STYLE1"><div align="right">
            <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="62" height="22" valign="middle"><div align="right"><img src="<%=path %>/admin/images/page_first_1.gif" width="48" height="20" onclick="frist()" /></div></td>
                <td width="50" height="22" valign="middle"><div align="right"><img src="<%=path %>/admin/images/page_back_1.gif" width="55" height="20" onclick="back()"/></div></td>
                <td width="54" height="22" valign="middle"><div align="right"><img src="<%=path %>/admin/images/page_next.gif" width="58" height="20" onclick="next()"/></div></td>
                <td width="49" height="22" valign="middle"><div align="right"><img src="<%=path %>/admin/images/page_last.gif" width="52" height="20" onclick="last()"/></div></td>
              </tr>
            </table>
        </div></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>

