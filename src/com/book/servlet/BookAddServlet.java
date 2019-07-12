package com.book.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.util.DBUtil;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class BookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String path = "files";

	/**
	 * Constructor of the object.
	 */
	public BookAddServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String gurl = "/files/";
		String loadpath = this.getServletConfig().getServletContext()
				.getRealPath("/");
		String fullPath = loadpath + path;
		File fullDir = new File(fullPath);
		if (!fullDir.exists()) {
			fullDir.mkdirs();
		}
		SmartUpload smartUpload = new SmartUpload();

		long maxSize = 10 * 1024 * 1024;// 每个文件允许上传的大小
		String allowFileExtList = "jar,exe,doc,docx,txt,html,xml,xls,pdf,jpg,png,gif";
		smartUpload.initialize(getServletConfig(), request, response);
		smartUpload.setMaxFileSize(maxSize);
		smartUpload.setAllowedFilesList(allowFileExtList);
		String foodname = "";
		String sjid = "";
		String btid = "";
		try {
			smartUpload.upload();
			foodname = smartUpload.getRequest().getParameter("foodname");
			sjid = smartUpload.getRequest().getParameter("sjid");
			btid = smartUpload.getRequest().getParameter("btid");
			Files allFiles = smartUpload.getFiles();
			Enumeration fileEnum = allFiles.getEnumeration();
			while (fileEnum.hasMoreElements()) {
				com.jspsmart.upload.File smartFile = (com.jspsmart.upload.File) fileEnum
						.nextElement();
				if (!smartFile.isMissing()) {
					String fileName = smartFile.getFileName();
					String type = fileName.substring(fileName.lastIndexOf("."));
					Random rnd = new Random();
					int r = rnd.nextInt(100);
					Date date2 = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyyMMddHHmmss");
					String strDate2 = formatter.format(date2);
					fileName = strDate2 + r + type;
					response.setContentType("text/html;charset=gbk");// 解决中文乱码问题,页面中也要为gbk
					smartFile.saveAs(fullPath + "//" + fileName,
							com.jspsmart.upload.File.SAVEAS_PHYSICAL);
					gurl += fileName;
				}
				break;
			}

		} catch (SmartUploadException e) {
			e.printStackTrace();
		}

		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			String sql = "insert into books(gname,gurl,sjid,btid,states) values('"
					+ foodname
					+ "','"
					+ gurl
					+ "',"
					+ sjid
					+ ","
					+ btid
					+ ",1)";

			System.out.println(sql);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		util.closeConn(conn);
		request.getRequestDispatcher("BookServlet?mode=list").forward(request,
				response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
