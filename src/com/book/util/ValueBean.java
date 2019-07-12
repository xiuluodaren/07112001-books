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
			result="ϵͳ����Ա";
		}else if(id==2){
			result="��������";
		}else{
			result="��ͨԱ��";
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
			result="δ����";
		}else{
			result="�ѽ���";
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
			result="��Ԥ֧";
		}else{
			result="Ԥ֧";
		}
		return result;
	}
	
	public static String getState(int id){
		String result = "";
		if(id==1){
			result="δ�ύ";
		}else if(id==2){
			result="�����";
		}else if(id==3){
			result="�����";
		}else if(id==4){
			result="�ѽ���";
		}else{
			result="����";
		}
		return result;
	}
	
}
