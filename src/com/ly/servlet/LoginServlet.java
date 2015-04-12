package com.ly.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.domain.Admin;
import com.ly.domain.User;
import com.ly.jdbc.ConnectionFactory;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获得帐号
//		String id=req.getParameter("id").trim();
		String userName = req.getParameter("userName");
		String passWord=req.getParameter("passWord");
		String type=req.getParameter("type");
		PrintWriter out=resp.getWriter();
		if(type!=null && type.equals("学生")){
			User user=new User();
			try {
				user=ConnectionFactory.getOneUser(userName,passWord);
				
				if(user!= null){
					out.print("login success!");
					out.print("此登陆学生姓名为"+user.getUserName());
				}else{
					out.print("login error!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			Admin admin=new Admin();
			try {
				admin=ConnectionFactory.getAdmin(userName, passWord);
				if(admin!=null){
					out.print("login success!");
				}else{
					out.print("login error!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
