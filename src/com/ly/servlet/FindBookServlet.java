package com.ly.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.domain.Book;
import com.ly.domain.User;
import com.ly.jdbc.ConnectionFactory;
import com.ly.utils.XmlUtils;

public class FindBookServlet extends HttpServlet {

	
	public FindBookServlet() {
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
		String bookName = req.getParameter("bookName");	
		String bookAuthor = req.getParameter("bookAuthor");
		PrintWriter out=resp.getWriter();
		resp.setCharacterEncoding("utf-8");
		Book book=new Book();
		try {
			book=ConnectionFactory.getOneBook(bookName, bookAuthor);
			
			if(book!=null){
//				out.println("����idΪ"+book.getId());
//				out.println("������Ϊ"+book.getBookName());
//				out.println("��������"+book.getBookAuthor());
//				out.println("�������������Ϊ"+book.getPhName());
//				out.println("��������λ��"+book.getLocation());
//				out.println("��������Ϊ"+book.getCategory());
//				out.println("����״̬Ϊ"+book.getStatus());
//				out.println("����������ʱ��"+book.getRentTime());
				
				String xmlStr = XmlUtils.makeBookXml(book);
				out.write(xmlStr);
				out.flush();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
