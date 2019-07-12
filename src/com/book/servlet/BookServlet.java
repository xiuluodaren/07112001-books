package com.book.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.bean.Book;
import com.book.bean.Br;
import com.book.util.DBUtil;
import com.book.util.Page;

public class BookServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BookServlet() {
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
		String sname = request.getParameter("sname");

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
		HttpSession session = request.getSession();
		String myid = session.getAttribute("id").toString();
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			if (mode != null && mode.equals("list")) {

//				String sql = " SELECT TOP "
//						+ n
//						+ "* FROM ("
//						+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM books where 1=1 ";
				String sql = "select * from books where 1=1 ";
				String sql2 = "select count(*) from books where 1=1 ";
				if (sname != null && !sname.equals("")) {
					sql += " and gname like '%" + sname + "%' ";
					sql2 += " and gname like '%" + sname + "%' ";
				}
//				sql += " ) A" + " WHERE  RowNumber > " + m;
				sql += " limit " + m + "," + n;
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

				List<Book> list = new ArrayList<Book>();
				while (rs.next()) {
					Book bean = new Book();
					bean.setId(rs.getInt("id"));
					bean.setGname(rs.getString("gname"));
					bean.setGurl(rs.getString("gurl"));
					bean.setBtid(rs.getInt("btid"));
					bean.setSjid(rs.getInt("sjid"));
					bean.setStates(rs.getInt("states"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("admin/bookList.jsp").forward(
						request, response);
			}
			if (mode != null && mode.equals("brlist")) {

//				String sql = " SELECT TOP "
//						+ n
//						+ "* FROM ("
//						+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM books where 1=1 ";
				String sql = "select * from books where 1=1 ";
				String sql2 = "select count(*) from books where 1=1 ";
				if (sname != null && !sname.equals("")) {
					sql += " and gname like '%" + sname + "%' ";
					sql2 += " and gname like '%" + sname + "%' ";
				}
//				sql += " ) A" + " WHERE  RowNumber > " + m;
				sql += " limit " + m + "," + n;

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

				List<Book> list = new ArrayList<Book>();
				while (rs.next()) {
					Book bean = new Book();
					bean.setId(rs.getInt("id"));
					bean.setGname(rs.getString("gname"));
					bean.setGurl(rs.getString("gurl"));
					bean.setBtid(rs.getInt("btid"));
					bean.setSjid(rs.getInt("sjid"));
					bean.setStates(rs.getInt("states"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("admin/bookbrList.jsp").forward(
						request, response);
			}

			if (mode != null && mode.equals("deletes")) {

				String[] selectFlag = request.getParameterValues("selectFlag");

				System.out.println("================  " + selectFlag.length);
				for (int i = 0; i < selectFlag.length; i++) {
					String sql = "delete from  books where id=" + selectFlag[i];
					System.out.println("================  " + sql);
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sql);
				}
//				String sql = " SELECT TOP "
//						+ n
//						+ "* FROM ("
//						+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM books where 1=1 ";
				String sql = "select * from books where 1=1 ";
				String sql2 = "select count(*) from books where 1=1 ";
//				sql += " ) A" + " WHERE  RowNumber > " + m;
				sql += " limit " + m + "," + n;
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

				List<Book> list = new ArrayList<Book>();
				while (rs.next()) {
					Book bean = new Book();
					bean.setId(rs.getInt("id"));
					bean.setGname(rs.getString("gname"));
					bean.setGurl(rs.getString("gurl"));
					bean.setBtid(rs.getInt("btid"));
					bean.setSjid(rs.getInt("sjid"));
					bean.setStates(rs.getInt("states"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("admin/bookList.jsp").forward(
						request, response);
			}

			if (mode != null && mode.equals("br")) {

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date d = new Date();
				long time = d.getTime();
				int kj = 0;

				String[] selectFlag = request.getParameterValues("selectFlag");

				String utid = session.getAttribute("types").toString();

				String sql = "SELECT kj FROM usertype WHERE id= " + utid;
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				ResultSet rs = pstmt1.executeQuery();
				rs.next();
				int mykj = rs.getInt(1);

				for (int i = 0; i < selectFlag.length; i++) {

					String sql2 = " SELECT bt.kj AS kj FROM books b,booktype bt WHERE bt.id=b.btid AND b.id="
							+ selectFlag[i];
					PreparedStatement pstmt2 = conn.prepareStatement(sql2);
					ResultSet rs2 = pstmt2.executeQuery();
					rs2.next();
					int bkj = rs2.getInt(1);
					if (mykj > bkj) {
						kj = bkj;
					} else {
						kj = mykj;
					}
					long day = kj * 24 * 60 * 60 * 1000;
					time += day;

					Date newDate = new Date(time);

					String etimes = sdf.format(newDate);
					String sqladd = "insert into br(myid,bid,btimes,etimes,xjcs,states)values("
							+ myid
							+ ","
							+ selectFlag[i]
							+ ",now(),'"
							+ etimes + "',0,1)";

					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sqladd);

					String sqlupdate = "update books set states=0 where id="
							+ selectFlag[i];

					Statement stmt2 = conn.createStatement();
					stmt2.executeUpdate(sqlupdate);

				}

//				String sql3 = " SELECT TOP "
//						+ n
//						+ "* FROM ("
//						+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM br where 1=1 and states=1  and myid="
//						+ myid;
				String sql3 = "select * from br where 1=1 and states=1  and myid=" + myid;
				String sql2 = "select count(*) from br where 1=1 and states=1 and myid="
						+ myid;
//				sql3 += " ) A" + " WHERE  RowNumber > " + m;
				sql3 += " limit " + m + "," + n;

				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs3 = pstmt3.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				totle = rs2.getInt(1);
				int totlePage = totle / n;
				int totlePageY = totle % n;
				if (totlePageY != 0) {
					totlePage = totlePage + 1;
				}

				List<Br> list = new ArrayList<Br>();
				while (rs3.next()) {
					Br bean = new Br();
					bean.setId(rs3.getInt("id"));
					bean.setStates(rs3.getInt("states"));
					bean.setBid(rs3.getInt("bid"));
					bean.setBtimes(rs3.getDate("btimes"));
					bean.setEtimes(rs3.getDate("etimes"));
					bean.setMyid(rs3.getInt("myid"));
					bean.setXjcs(rs3.getInt("xjcs"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("admin/mybrList.jsp").forward(
						request, response);
			}

			if (mode != null && mode.equals("xj")) {

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date d = new Date();
				long time = d.getTime();
				int kj = 0;

				String[] selectFlag = request.getParameterValues("selectFlag");

				String utid = session.getAttribute("types").toString();

				String sql = "SELECT kj FROM usertype WHERE id= " + utid;
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				ResultSet rs = pstmt1.executeQuery();
				rs.next();
				int mykj = rs.getInt(1);

				for (int i = 0; i < selectFlag.length; i++) {

					String sql22 = " select * from br where id="
							+ selectFlag[i];
					PreparedStatement pstmt22 = conn.prepareStatement(sql22);
					ResultSet rs22 = pstmt22.executeQuery();
					rs22.next();
					// Date d = rs22.getDate("btimes");
					int bid = rs22.getInt("bid");
					// long time = d.getTime();

					String sql2 = " SELECT bt.kj AS kj FROM books b,booktype bt WHERE bt.id=b.btid AND b.id="
							+ bid;
					PreparedStatement pstmt2 = conn.prepareStatement(sql2);
					ResultSet rs2 = pstmt2.executeQuery();
					rs2.next();
					int bkj = rs2.getInt(1);

					if (mykj > bkj) {
						kj = bkj;
					} else {
						kj = mykj;
					}
					long day = kj * 24 * 60 * 60 * 1000;
					time += day;

					Date newDate = new Date(time);

					String etimes = sdf.format(newDate);
					String sqladd = "update br set etimes='" + etimes
							+ "',xjcs=xjcs+1 where id=" + selectFlag[i];

					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sqladd);

				}

//				String sql3 = " SELECT TOP "
//						+ n
//						+ "* FROM ("
//						+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM br where 1=1 and states=1  and myid="
//						+ myid;
				String sql3 = "select * from br where 1=1 and states=1  and myid=" + myid;
				String sql2 = "select count(*) from books where 1=1 and states=1 and myid="
						+ myid;
//				sql3 += " ) A" + " WHERE  RowNumber > " + m;
				sql3 += " limit " + m + "," + n;
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs3 = pstmt3.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				totle = rs2.getInt(1);
				int totlePage = totle / n;
				int totlePageY = totle % n;
				if (totlePageY != 0) {
					totlePage = totlePage + 1;
				}

				List<Br> list = new ArrayList<Br>();
				while (rs3.next()) {
					Br bean = new Br();
					bean.setId(rs3.getInt("id"));
					bean.setStates(rs3.getInt("states"));
					bean.setBid(rs3.getInt("bid"));
					bean.setBtimes(rs3.getDate("btimes"));
					bean.setEtimes(rs3.getDate("etimes"));
					bean.setMyid(rs3.getInt("myid"));
					bean.setXjcs(rs3.getInt("xjcs"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("admin/mybrList.jsp").forward(
						request, response);
			}
			if (mode != null && mode.equals("gh")) {

				String[] selectFlag = request.getParameterValues("selectFlag");

				for (int i = 0; i < selectFlag.length; i++) {

					String sql22 = " select * from br where id="
							+ selectFlag[i];
					PreparedStatement pstmt22 = conn.prepareStatement(sql22);
					ResultSet rs22 = pstmt22.executeQuery();
					rs22.next();
					// Date d = rs22.getDate("btimes");
					int bid = rs22.getInt("bid");
					// long time = d.getTime();

					String sqladd = "update br set states=0 where id="
							+ selectFlag[i];

					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sqladd);

					String sqlupdate = "update books set states=1 where id="
							+ bid;

					Statement stmt2 = conn.createStatement();
					stmt2.executeUpdate(sqlupdate);

				}

//				String sql3 = " SELECT TOP "
//						+ n
//						+ "* FROM ("
//						+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM br where 1=1 and states=0  and myid="
//						+ myid;

				String sql3 = "select * from br where 1=1 and states=0  and myid=" + myid;
				String sql2 = "select count(*) from br where 1=1 and states=0 and myid="
						+ myid;
//				sql3 += " ) A" + " WHERE  RowNumber > " + m;
				sql3 += " limit " + m + "," + n;

				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs3 = pstmt3.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				totle = rs2.getInt(1);
				int totlePage = totle / n;
				int totlePageY = totle % n;
				if (totlePageY != 0) {
					totlePage = totlePage + 1;
				}

				List<Br> list = new ArrayList<Br>();
				while (rs3.next()) {
					Br bean = new Br();
					bean.setId(rs3.getInt("id"));
					bean.setStates(rs3.getInt("states"));
					bean.setBid(rs3.getInt("bid"));
					bean.setBtimes(rs3.getDate("btimes"));
					bean.setEtimes(rs3.getDate("etimes"));
					bean.setMyid(rs3.getInt("myid"));
					bean.setXjcs(rs3.getInt("xjcs"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("admin/mybrlsList.jsp").forward(
						request, response);
			}
			if (mode != null && mode.equals("mybrlist")) {

//				String sql3 = " SELECT TOP "
//						+ n
//						+ "* FROM ("
//						+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM br where 1=1 and states=1  and myid="
//						+ myid;

				String sql3 = "select * from br where 1=1 and states=1  and myid=" + myid;
				String sql2 = "select count(*) from br where 1=1 and states=1 and myid="
						+ myid;
//				sql3 += " ) A" + " WHERE  RowNumber > " + m;
				sql3 += " limit " + m + "," + n;
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs3 = pstmt3.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				totle = rs2.getInt(1);
				int totlePage = totle / n;
				int totlePageY = totle % n;
				if (totlePageY != 0) {
					totlePage = totlePage + 1;
				}

				List<Br> list = new ArrayList<Br>();
				while (rs3.next()) {
					Br bean = new Br();
					bean.setId(rs3.getInt("id"));
					bean.setStates(rs3.getInt("states"));
					bean.setBid(rs3.getInt("bid"));
					bean.setBtimes(rs3.getDate("btimes"));
					bean.setEtimes(rs3.getDate("etimes"));
					bean.setMyid(rs3.getInt("myid"));
					bean.setXjcs(rs3.getInt("xjcs"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("admin/mybrList.jsp").forward(
						request, response);
			}
			if (mode != null && mode.equals("brlist2")) {

//				String sql3 = " SELECT TOP "
//						+ n
//						+ "* FROM ("
//						+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM br where 1=1 and states=1 ";

				String sql3 = "select * from br where 1=1 and states=1 ";
				String sql2 = "select count(*) from books where 1=1 and states=1 ";
//				sql3 += " ) A" + " WHERE  RowNumber > " + m;
				sql3 += " limit " + m + "," + n;
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs3 = pstmt3.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				totle = rs2.getInt(1);
				int totlePage = totle / n;
				int totlePageY = totle % n;
				if (totlePageY != 0) {
					totlePage = totlePage + 1;
				}

				List<Br> list = new ArrayList<Br>();
				while (rs3.next()) {
					Br bean = new Br();
					bean.setId(rs3.getInt("id"));
					bean.setStates(rs3.getInt("states"));
					bean.setBid(rs3.getInt("bid"));
					bean.setBtimes(rs3.getDate("btimes"));
					bean.setEtimes(rs3.getDate("etimes"));
					bean.setMyid(rs3.getInt("myid"));
					bean.setXjcs(rs3.getInt("xjcs"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("admin/brList.jsp").forward(
						request, response);
			}
			if (mode != null && mode.equals("mybrlslist")) {

//				String sql3 = " SELECT TOP "
//						+ n
//						+ "* FROM ("
//						+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM br where 1=1 and states=0  and myid="
//						+ myid;

				String sql3 = "select * from br where 1=1 and states=0  and myid=" + myid;
				String sql2 = "select count(*) from br where 1=1 and states=0 and myid="
						+ myid;
//				sql3 += " ) A" + " WHERE  RowNumber > " + m;
				sql3 += " limit " + m + "," + n;
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs3 = pstmt3.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				totle = rs2.getInt(1);
				int totlePage = totle / n;
				int totlePageY = totle % n;
				if (totlePageY != 0) {
					totlePage = totlePage + 1;
				}

				List<Br> list = new ArrayList<Br>();
				while (rs3.next()) {
					Br bean = new Br();
					bean.setId(rs3.getInt("id"));
					bean.setStates(rs3.getInt("states"));
					bean.setBid(rs3.getInt("bid"));
					bean.setBtimes(rs3.getDate("btimes"));
					bean.setEtimes(rs3.getDate("etimes"));
					bean.setMyid(rs3.getInt("myid"));
					bean.setXjcs(rs3.getInt("xjcs"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("admin/mybrlsList.jsp").forward(
						request, response);
			}

			if (mode != null && mode.equals("brlslist")) {

//				String sql3 = " SELECT TOP "
//						+ n
//						+ "* FROM ("
//						+ " SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM br where 1=1 and states=0 ";

				String sql3 = "select * from br where 1=1 and states=0";
				String sql2 = "select count(*) from books where 1=1 and states=0 ";
//				sql3 += " ) A" + " WHERE  RowNumber > " + m;
				sql3 += " limit " + m + "," + n;
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs3 = pstmt3.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				totle = rs2.getInt(1);
				int totlePage = totle / n;
				int totlePageY = totle % n;
				if (totlePageY != 0) {
					totlePage = totlePage + 1;
				}

				List<Br> list = new ArrayList<Br>();
				while (rs3.next()) {
					Br bean = new Br();
					bean.setId(rs3.getInt("id"));
					bean.setStates(rs3.getInt("states"));
					bean.setBid(rs3.getInt("bid"));
					bean.setBtimes(rs3.getDate("btimes"));
					bean.setEtimes(rs3.getDate("etimes"));
					bean.setMyid(rs3.getInt("myid"));
					bean.setXjcs(rs3.getInt("xjcs"));
					list.add(bean);
				}
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setTotlePage(totlePage);
				page.setTotle(totle);
				page.setList(list);
				request.setAttribute("page", page);
				request.getRequestDispatcher("admin/brlsList.jsp").forward(
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
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
