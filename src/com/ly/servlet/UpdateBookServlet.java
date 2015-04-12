package com.ly.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.domain.Book;
import com.ly.jdbc.ConnectionFactory;

public class UpdateBookServlet extends HttpServlet {

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
		int id=Integer.parseInt(req.getParameter("id"));
		String bookName=req.getParameter("bookName");
		String bookAuthor=req.getParameter("bookAuthor");
		String phName=req.getParameter("phName");
		String location=req.getParameter("location");
		String category=req.getParameter("category");
		String status=req.getParameter("status");
		String rentTime=req.getParameter("rentTime");
		PrintWriter out=resp.getWriter();
		Book book=new Book();
		book.setId(id);
		book.setBookName(bookName);
		book.setBookAuthor(bookAuthor);
		book.setPhName(phName);
		book.setLocation(location);
		book.setCategory(category);
		book.setStatus(status);
		book.setRentTime(rentTime);
		try {
			boolean flag=ConnectionFactory.updateBook(id, book);
			if(flag){
				out.print("update success!");
			}else{
				out.print("update fail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
