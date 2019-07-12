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

import com.book.bean.User;
import com.book.util.DBUtil;
import com.book.util.Page;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
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
//				String sql = " SELECT TOP "
//						+ n
//						+ "* FROM ("
//						+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM users ) A"
//						+ " WHERE  RowNumber > " + m;
				String sql = "select * from users limit " + m + "," + n;
				String sql2 = "select count(*) from users ";

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

				List<User> list = new ArrayList<User>();
				while (rs.next()) {
					User bean = new User();
					bean.setId(rs.getInt("id"));
					bean.setusername(rs.getString("username"));
					bean.setPassword(rs.getString("password"));
					bean.setAddress(rs.getString("address"));
					bean.setPhone(rs.getString("phone"));
					bean.setUtid(rs.getInt("utid"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
				request.getRequestDispatcher("admin/userList.jsp").forward(
						request, response);
			}

			if (mode != null && mode.equals("add")) {

				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String phone = request.getParameter("phone");
				String address = request.getParameter("address");
				String utid = request.getParameter("utid");
				String sqlAdd = "insert into users(username,password,phone,address,utid) values('"
						+ username
						+ "','"
						+ password
						+ "','"
						+ phone
						+ "','"
						+ address + "'," + utid + ")";
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sqlAdd);

//				String sql = " SELECT TOP "
//						+ n
//						+ "* FROM ("
//						+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM users ) A"
//						+ " WHERE  RowNumber > " + m;
				String sql = "select * from users limit " + m + "," + n;
				String sql2 = "select count(*) from users ";
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

				List<User> list = new ArrayList<User>();
				while (rs.next()) {
					User bean = new User();
					bean.setId(rs.getInt("id"));
					bean.setusername(rs.getString("username"));
					bean.setPassword(rs.getString("password"));
					bean.setAddress(rs.getString("address"));
					bean.setPhone(rs.getString("phone"));
					bean.setUtid(rs.getInt("utid"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
				request.getRequestDispatcher("admin/userList.jsp").forward(
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

			if (mode != null && mode.equals("deletes")) {

				String[] selectFlag = request.getParameterValues("selectFlag");

				System.out.println("================  " + selectFlag.length);
				for (int i = 0; i < selectFlag.length; i++) {
					String sql = "delete from  users where id=" + selectFlag[i];
					System.out.println("================  " + sql);
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sql);
				}

//				String sql = " SELECT TOP "
//						+ n
//						+ "* FROM ("
//						+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM users ) A"
//						+ " WHERE  RowNumber > " + m;
				String sql = "select * from users limit " + m + "," + n;
				String sql2 = "select count(*) from users ";
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

				List<User> list = new ArrayList<User>();
				while (rs.next()) {
					User bean = new User();
					bean.setId(rs.getInt("id"));
					bean.setusername(rs.getString("username"));
					bean.setPassword(rs.getString("password"));
					bean.setAddress(rs.getString("address"));
					bean.setPhone(rs.getString("phone"));
					bean.setUtid(rs.getInt("utid"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
				request.getRequestDispatcher("admin/userList.jsp").forward(
						request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}

}
