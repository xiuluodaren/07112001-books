package com.book.servlet;

import java.io.IOException;
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
import com.book.bean.Sj;
import com.book.util.DBUtil;
import com.book.util.Page;

public class SjServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SjServlet() {
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
		int n = 30;// ÿҳ��С
		int totle = 0;
		int pageNo = Integer.parseInt(pageNoStr);
		if (pageNo == 1) {// ��ҳ

		} else {
			m = (pageNo - 1) * n;
		}

		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			if (mode != null && mode.equals("list")) {
				
//				String sql = " SELECT TOP "
//					+ n
//					+ "* FROM ("
//					+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM sj ) A"
//					+ " WHERE  RowNumber > " + m;
			String sql = "select * from sj limit " + m + "," + n;
			String sql2 = "select count(*) from sj ";
				
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs = pstmt1.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				totle = rs2.getInt(1);
				int totlePage = totle / n;
				int totlePageY = totle % n;
				if (totlePageY != 0) {
					totlePage = totlePage + 1;
				}

				List<Sj> list = new ArrayList<Sj>();
				while (rs.next()) {
					Sj bean = new Sj();
					bean.setId(rs.getInt("id"));
					bean.setNames(rs.getString("names"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
				request.getRequestDispatcher("admin/sjList.jsp").forward(
						request, response);
			}

			if (mode != null && mode.equals("add")) {

				String username = request.getParameter("username");
				String sqlAdd = "insert into sj(names) values('"
						+ username  + "')";
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sqlAdd);

				
//				String sql = " SELECT TOP "
//					+ n
//					+ "* FROM ("
//					+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM sj ) A"
//					+ " WHERE  RowNumber > " + m;
			String sql = "select * from sj limit " + m + "," + n;
			String sql2 = "select count(*) from sj ";
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs = pstmt1.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				totle = rs2.getInt(1);
				int totlePage = totle / n;
				int totlePageY = totle % n;
				if (totlePageY != 0) {
					totlePage = totlePage + 1;
				}

				List<Sj> list = new ArrayList<Sj>();
				while (rs.next()) {
					Sj bean = new Sj();
					bean.setId(rs.getInt("id"));
					bean.setNames(rs.getString("names"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
				request.getRequestDispatcher("admin/sjList.jsp").forward(
						request, response);
			}

			if (mode != null && mode.equals("adminPwdUpdate")) {

				String id = request.getParameter("id");
				String password = request.getParameter("password");
				String sqlAdd = "update admins set password='" + password
						+ "' where id=" + id;
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sqlAdd);

				HttpSession session = request.getSession();
				session.setAttribute("password", password);
				request.getRequestDispatcher("admin/adminPwdUpdate.jsp")
						.forward(request, response);

			}

			if (mode != null && mode.equals("adminUpdate")) {

				String id = request.getParameter("id");
				String password = request.getParameter("password");
				String sqlAdd = "update admins set password='" + password
						+ "' where id=" + id;
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sqlAdd);

				String sql = " select * from admins limit " + m + "," + n;
				String sql2 = "select count(*) from admins ";
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs = pstmt1.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				totle = rs2.getInt(1);
				int totlePage = totle / n;
				int totlePageY = totle % n;
				if (totlePageY != 0) {
					totlePage = totlePage + 1;
				}

				List<Admin> list = new ArrayList<Admin>();
				while (rs.next()) {
					Admin bean = new Admin();
					bean.setId(rs.getInt("id"));
					bean.setusername(rs.getString("username"));
					bean.setPassword(rs.getString("password"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
				request.getRequestDispatcher("admin/adminList.jsp").forward(
						request, response);

			}

			if (mode != null && mode.equals("deletes")) {

				String[] selectFlag = request.getParameterValues("selectFlag");

				for (int i = 0; i < selectFlag.length; i++) {
					String sql = "delete from  sj where id="
							+ selectFlag[i];
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sql);
				}

				
//			String sql = " SELECT TOP "
//				+ n
//				+ "* FROM ("
//				+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM sj ) A"
//				+ " WHERE  RowNumber > " + m;
			String sql = "select * from sj limit " + m + "," + n;
			String sql2 = "select count(*) from sj ";
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs = pstmt1.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				totle = rs2.getInt(1);
				int totlePage = totle / n;
				int totlePageY = totle % n;
				if (totlePageY != 0) {
					totlePage = totlePage + 1;
				}

				List<Sj> list = new ArrayList<Sj>();
				while (rs.next()) {
					Sj bean = new Sj();
					bean.setId(rs.getInt("id"));
					bean.setNames(rs.getString("names"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
				request.getRequestDispatcher("admin/sjList.jsp").forward(
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
