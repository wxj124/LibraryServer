package com.ly.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.domain.Book;
import com.ly.domain.User;
import com.ly.jdbc.ConnectionFactory;

public class SaveBookServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SaveBookServlet() {
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
		String bookName=req.getParameter("BookName");
		String bookAuthor=req.getParameter("BookAuthor");
		String phName=req.getParameter("PhName");
		String location=req.getParameter("Location");
		String category=req.getParameter("Category");
		String status=req.getParameter("Status");
		String rentTime=req.getParameter("RentTime");
		
		resp.setCharacterEncoding("utf-8");
		PrintWriter out=resp.getWriter();
		Book book=new Book();
		book.setBookName(bookName);
		book.setBookAuthor(bookAuthor);
		book.setPhName(phName);
		book.setLocation(location);
		book.setCategory(category);
		book.setStatus(status);
		book.setRentTime(rentTime);
		
		boolean flag=ConnectionFactory.insertBook(book);
		if(flag){
			out.print("success");
		}else{
			out.print("failed");
		}
	}

}
