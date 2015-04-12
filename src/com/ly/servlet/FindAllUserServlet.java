package com.ly.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.domain.User;
import com.ly.jdbc.ConnectionFactory;
import com.ly.utils.XmlUtils;

public class FindAllUserServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FindAllUserServlet() {
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
		List<User> list;
		try {
			list = ConnectionFactory.findAllUser();
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/html;charset=utf-8");
			String xmlStr = XmlUtils.makeUserXml(list);
			out.write(xmlStr);
//			System.out.println(xmlStr);
			out.flush();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
