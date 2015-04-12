package com.ly.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.domain.User;
import com.ly.jdbc.ConnectionFactory;

public class RegisterServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
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
		req.setCharacterEncoding("utf-8");
		String userName=req.getParameter("UserName");
		String passWord=req.getParameter("PassWord");
		String indentityId=req.getParameter("IndentityId");
		String year=req.getParameter("Year");
		String gender=req.getParameter("Gender");
		String collage=req.getParameter("Collage");
		
		resp.setCharacterEncoding("utf-8");
//		resp.setContentType("text/html,charset=utf-8");
		PrintWriter out=resp.getWriter();
		
		User user=new User();
		user.setUserName(userName);
		user.setPassWord(passWord);
		user.setIndentityId(indentityId);
		user.setYear(year);
		user.setGender(gender);
		user.setCollage(collage);
		
		boolean flag=ConnectionFactory.insert(user);
		if(flag){
			out.print("success");
		}else{
			out.print("failed");
		}
	}

	
}
