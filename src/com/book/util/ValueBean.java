package com.book.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValueBean {
	
	public static String getFoodNameByGname(String  gname){
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql="select ctname from foods where gname ='"+gname+"'";
		String result="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getString("ctname");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getFoodPriceByGname(String  gname){
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql="select gprice from foods where gname ='"+gname+"'";
		
		System.out.println("------  "+sql);
		String result="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getDouble("gprice")+"";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getValueByTypeId(int id){
		String result = "";
		if(id==1){
			result="系统管理员";
		}else if(id==2){
			result="部门主管";
		}else{
			result="普通员工";
		}
		return result;
	}
	
	public static String getValueByUTypeId(int id){
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql="select names from usertype where id="+id;
		String result="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getString("names");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getValueByBTypeId(int id){
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql="select names from booktype where id="+id;
		String result="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getString("names");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getValueByBId(int id){
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql="select gname from books where id="+id;
		String result="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getString("gname");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String getValueByUId(int id){
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql="select username from users where id="+id;
		String result="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getString("username");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String getValueBySTypeId(int id){
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql="select names from sj where id="+id;
		String result="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getString("names");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String getValueByStateTypeId(int id){
		String result = "";
		if(id==1){
			result="未借阅";
		}else{
			result="已借阅";
		}
		return result;
	}
	
	
	
	public static String getusernameById(int id){
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql="select username from users where id="+id;
		String result="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getString("username");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getEmpNameByUId(int id){
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql=" select * from users u,emp e where u.empid=e.id and u.id="+id;
		String result="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getString("realname");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getYz(int id){
		String result = "";
		if(id==0){
			result="不预支";
		}else{
			result="预支";
		}
		return result;
	}
	
	public static String getState(int id){
		String result = "";
		if(id==1){
			result="未提交";
		}else if(id==2){
			result="待审核";
		}else if(id==3){
			result="已审核";
		}else if(id==4){
			result="已结束";
		}else{
			result="报销";
		}
		return result;
	}
	
}
