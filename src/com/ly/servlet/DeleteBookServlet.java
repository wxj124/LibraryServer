package com.ly.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.jdbc.ConnectionFactory;

public class DeleteBookServlet extends HttpServlet {


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
		String bookName=req.getParameter("bookName");
		PrintWriter out=resp.getWriter();
		try {
			boolean flag=ConnectionFactory.deleteBook(bookName);
			if(flag){
				out.print("É¾³ý³É¹¦£¡");
			}else{
				out.print("É¾³ýÊ§°Ü!!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
}
