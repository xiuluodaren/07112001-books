package com.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.bean.Admin;
import com.book.bean.Jb;
import com.book.bean.Sj;
import com.book.util.DBUtil;
import com.book.util.Page;

public class JbServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public JbServlet() {
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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String mode = request.getParameter("mode");
		String pageNoStr = request.getParameter("pageNoStr") == null ? "1"
				: request.getParameter("pageNoStr");
		int m = 0;
		int n = 30;// 每页大小
		int totle = 0;
		int pageNo = Integer.parseInt(pageNoStr);
		if (pageNo == 1) {// 首页

		} else {
			m = (pageNo - 1) * n;
		}

		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			if (mode != null && mode.equals("list")) {
				String sql = " select * from jb ";
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				ResultSet rs = pstmt1.executeQuery();
				while (rs.next()) {
					Jb bean = new Jb();
					bean.setId(rs.getInt("id"));
					bean.setBzf(rs.getString("bzf"));
					bean.setFkone(rs.getString("fkone"));
					bean.setYxq(rs.getString("yxq"));
					request.setAttribute("bean", bean);
				}
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
				request.getRequestDispatcher("admin/jb.jsp").forward(
						request, response);
			}

			if (mode != null && mode.equals("update")) {

				String bzf = request.getParameter("bzf");
				String fkone = request.getParameter("fkone");
				String yxq = request.getParameter("yxq");
				String sqlAdd = "update jb set bzf='"+bzf+"',fkone='"+fkone+"',yxq='"+yxq+"'";
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sqlAdd);

				String sql = " select * from jb ";
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				ResultSet rs = pstmt1.executeQuery();
				rs.next();
					Jb bean = new Jb();
					bean.setId(rs.getInt("id"));
					bean.setBzf(rs.getString("bzf"));
					bean.setFkone(rs.getString("fkone"));
					bean.setYxq(rs.getString("yxq"));
				request.setAttribute("bean", bean);
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
				request.getRequestDispatcher("admin/jb.jsp").forward(
						request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
