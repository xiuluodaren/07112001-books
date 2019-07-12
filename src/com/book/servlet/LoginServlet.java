package com.book.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.util.DBUtil;
import com.book.util.ValueBean;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		String sql = "select * from admins where username= '" + username
				+ "' and password='" + password + "'";
		System.out.println(sql);
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int index = 0;
			while (rs.next()) {
				session.setAttribute("id", rs.getInt("id"));
				session.setAttribute("username", rs.getString("username"));
				session.setAttribute("password", rs.getString("password"));
				session.setAttribute("types", "1");
				session.setAttribute("roles", "1");
				index++;
				break;
			}
			if (index > 0) {
				response.sendRedirect("loginSuccess.jsp");
			} else {
				String sql2 = "select * from users where username= '" + username
				+ "' and password='" + password + "'";
				System.out.println("------------   "+sql2);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs2 = pstmt2.executeQuery();
				int index2 = 0;
				int uid= 0;
				while (rs2.next()) {
					uid = rs2.getInt("id");
					session.setAttribute("id", uid);
					session.setAttribute("username", rs2.getString("username"));
					session.setAttribute("password", rs2.getString("password"));
					session.setAttribute("types", rs2.getInt("utid"));
					session.setAttribute("roles", "2");
					index2++;
					break;
				}
				System.out.println("------index2------   "+index2);
				if (index2 > 0) {
					
					//提示借阅到期日期
					
					String sql3 = " select * from br where myid="+uid+" and states =1 order by etimes desc ";
					PreparedStatement pstmt3 = conn.prepareStatement(sql3);
					ResultSet rs3 = pstmt3.executeQuery();
					int bookid = 0;
					Date etimes = null;
					while (rs3.next()) {
						bookid = rs3.getInt("bid");
						etimes = rs3.getDate("btimes");
						break;
					}
					
			         Calendar d1 = new GregorianCalendar();
			         d1.setTime(new Date());
			         if(etimes!=null){
			        	 Calendar d2 = new GregorianCalendar();
					        d2.setTime(etimes);
					        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
							
					        String str="您借阅的图书 "+ValueBean.getValueByBId(bookid)+"  还有"+days+" 天到期！";
					        System.out.println("#########   "+str);
					        session.setAttribute("str", str);
							response.sendRedirect("loginSuccess.jsp");
			         }else{
			        		response.sendRedirect("loginSuccess.jsp");
			         }
			        
				} else {
					response.sendRedirect("login.jsp");
				}
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}

}
