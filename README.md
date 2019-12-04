# 图书管理系统

#### 工程结构
com.book<br>
	bean					实体包<br>
		Admin.java			管理员实体<br>
		Book.java			图书实体<br>
		BookType.java		图书类型实体<br>
		Br.java				借阅实体(正在借阅/借阅历史)<br>
		Jb.java				基本信息实体<br>
		Sj.java				书架实体<br>
		User.java			用户实体<br>
		UserType.java		用户类型实体<br>
	servlet					请求处理包<br>
		AdminServlet.java	管理员请求,包括新增修改查询列表等,带分页<br>
		BookAddServelet.java添加图书的请求<br>
		BookServlet.java	其他图书请求<br>
		BookTypeServlet.java添加,查询图书类型的请求<br>
		JbServlet.java		基本信息的处理请求<br>
		LoginOutServlet.java登出请求<br>
		LonginServlet.java	登陆请求<br>
		SjServlet.java		书架管理请求<br>
		UserServlet.java	用户管理请求<br>
		UserTypeServlet.java用户类型请求<br>
	util					工具类包<br>
		DBUtil.java			数据库操作工具类,用来执行sql语句<br>
		LoadPropertiesFile.java系统属性工具类,用来加载配置信息<br>
		Page.java			分页工具类<br>
	app.config				系统配置信息<br>
	
####web页面结构
WebRoot					页面跟目录<br>
	css 				css样式<br>
	images				图片信息<br>
	META-INf			jar包入口<br>
	WEB-INF				jar包<br>
		web.xml			web配置信息<br>
		index.jsp		默认主页<br>
		login.jsp		登陆页<br>
	admin				页面<br>
		
	
	
		
		
		
		