package com.ly.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ly.domain.Admin;
import com.ly.domain.Book;
import com.ly.domain.User;


public class ConnectionFactory {
	private static final String url = "jdbc:mysql://localhost:3306/Library";
	private static final String username = "root";
	private static final String password = "root";

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
//注册
	public static boolean insert(User user) {
		Connection conn;
		int i = 0;
		try {
			conn = getConnection();
			PreparedStatement pre = null;
			String sql = "insert into t_user(UserName,PassWord,IndentityId,Year,Gender,Collage) values(?,?,?,?,?,?)";
			pre = conn.prepareStatement(sql);
			pre.setString(1, user.getUserName());
			pre.setString(2, user.getPassWord());
			pre.setString(4, user.getYear());
			pre.setString(5, user.getGender());
			pre.setString(6, user.getCollage());
			pre.setString(3, user.getIndentityId());
			i = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i>0){
			return true;
		}else{
			return false;
		}

	}
//用户登陆
	public static User getOneUser(String userName,String passWord) throws SQLException{	
		Connection conn=null;
		conn=getConnection();
		String sql="select * from t_user where UserName=? and PassWord=?";
		PreparedStatement st=null;
		ResultSet rs=null;
		st=conn.prepareStatement(sql);
		st.setString(1, userName);
		st.setString(2, passWord);
		rs=st.executeQuery();
		User user=new User();
		while(rs.next()) {
			user.setUserName(rs.getString("userName"));
			user.setPassWord(rs.getString("passWord"));
			return user;
		}
		return null;	
	}
//管理员登陆
	public static Admin getAdmin(String adminName,String passWord) throws SQLException{	
		Connection conn=null;
		conn=getConnection();
		String sql="select * from t_admin where AdminName=? and PassWord=?";
		PreparedStatement st=null;
		ResultSet rs=null;
		st=conn.prepareStatement(sql);
		st.setString(1, adminName);
		st.setString(2, passWord);
		rs=st.executeQuery();
		Admin admin=new Admin();
		while(rs.next()) {
			admin.setAdminName(rs.getString("adminName"));
			admin.setPassWord(rs.getString("passWord"));
			return admin;
		}
		return null;	
	}
//添加图书	
	public static boolean insertBook(Book book) {
		Connection conn;
		int i = 0;
		try {
			conn = getConnection();
			PreparedStatement pre = null;
			String sql = "insert into t_book(BookName,BookAuthor,PhName,Location,Category,Status,RentTime) values(?,?,?,?,?,?,?)";
			pre = conn.prepareStatement(sql);
			pre.setString(1, book.getBookName());
			pre.setString(2, book.getBookAuthor());
			pre.setString(3, book.getPhName());
			pre.setString(4, book.getLocation());
			pre.setString(5, book.getCategory());
			pre.setString(6, book.getStatus());
			pre.setString(7, book.getRentTime());
			i = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i>0){
			return true;
		}else{
			return false;
		}

	}
//查询图书	
	public static Book getOneBook(String bookName,String bookAuthor) throws SQLException{	
		Connection conn=null;
		conn=getConnection();
		String sql="select * from t_book where BookName=? and BookAuthor=?";
		PreparedStatement pre=null;
		ResultSet rs=null;
		pre=conn.prepareStatement(sql);
		pre.setString(1, bookName);
		pre.setString(2, bookAuthor);
		rs=pre.executeQuery();
				
		Book book=new Book();
		while(rs.next()){
			book.setId(Integer.parseInt(rs.getString("id")));
			book.setBookName(rs.getString("bookName"));
			book.setBookAuthor(rs.getString("bookAuthor"));
			book.setCategory(rs.getString("category"));
			book.setLocation(rs.getString("location"));
			book.setPhName(rs.getString("phName"));
			book.setRentTime(rs.getString("rentTime"));
			book.setStatus(rs.getString("status"));
			return book;
		}
		return null;	
	}
//管理员查询某个学生	
	public static User findOneUser(String userName) throws SQLException{	
		Connection conn=null;
		conn=getConnection();
		String sql="select * from t_user where UserName=?";
		PreparedStatement pre=null;
		ResultSet rs=null;
		pre=conn.prepareStatement(sql);
		pre.setString(1, userName);
		rs=pre.executeQuery();
		
		User user=new User();
		while(rs.next()){
			user.setUserName(rs.getString("userName"));
	//		user.setPassWord(rs.getString("passWord"));
			user.setGender(rs.getString("gender"));
			user.setCollage(rs.getString("collage"));
			user.setIndentityId(rs.getString("indentityId"));
			user.setYear(rs.getString("year"));
			return user;
		}
		return null;		
	}
	
//查询所有学生
	public static List<User> findAllUser() throws SQLException{
		Connection conn=getConnection();
		PreparedStatement pre=null;
		ResultSet rs=null;
		String sql="select * from t_user";
		List<User> list=new ArrayList<User>();
		pre=conn.prepareStatement(sql);
		rs=pre.executeQuery();
		while(rs.next()){
			User user=new User();
			user.setId(new Integer(rs.getString("id")));
			user.setCollage(rs.getString("collage"));
			user.setGender(rs.getString("gender"));
			user.setIndentityId(rs.getString("indentityId"));
			user.setPassWord(rs.getString("passWord"));
			user.setUserName(rs.getString("userName"));
			user.setYear(rs.getString("year"));
			list.add(user);
		}
		if(conn!=null){
			conn.close();
		}
		if(pre!=null){
			pre.close();
		}
		if(rs!=null){
			rs.close();
		}
		return list;	
	}

//删除图书
		public static boolean deleteBook(String bookName) throws SQLException{
			Connection conn=getConnection();
			PreparedStatement pre=null;
			String sql="delete from t_book where BookName=?";
			pre=conn.prepareStatement(sql);
			pre.setString(1, bookName);
			int i=pre.executeUpdate();
			if(i>0){
				return true;
			}else{
				return false;
			}	
		}
		
//删除用户
		public static boolean deleteUser(String userName) throws SQLException{
			Connection conn=getConnection();
			PreparedStatement pre=null;
			String sql="delete from t_user where UserName=?";
			pre=conn.prepareStatement(sql);
			pre.setString(1, userName);
			int i=pre.executeUpdate();
			if(i>0){
				return true;
			}else{
				return false;
			}	
		}
//更新图书信息
		public static boolean updateBook(int id,Book book) throws SQLException{
			Connection conn=getConnection();
			PreparedStatement pre=null;
	//		ResultSet rs=null;
			String sql="update t_book set BookName=?,BookAuthor=?,PhName=?,Location=?,Category=?,Status=?,RentTime=? where id=?";
			pre=conn.prepareStatement(sql);
			pre.setString(1, book.getBookName());
			pre.setString(2, book.getBookAuthor());
			pre.setString(3, book.getPhName());
			pre.setString(4, book.getLocation());
			pre.setString(5, book.getCategory());
			pre.setString(6, book.getStatus());
			pre.setString(7, book.getRentTime());
			pre.setInt(8, book.getId());
	//		rs=pre.executeQuery();
			int i=pre.executeUpdate();
			if(i>0){
				return true;
			}else{
				return false;
			}
			
			
		}
		
}

