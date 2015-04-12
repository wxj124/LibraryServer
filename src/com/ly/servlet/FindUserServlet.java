package com.ly.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.domain.User;
import com.ly.jdbc.ConnectionFactory;
import com.ly.utils.XmlUtils;

public class FindUserServlet extends HttpServlet {

	
	public FindUserServlet() {
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
		
		String userName = req.getParameter("userName");		
		PrintWriter out=resp.getWriter();
		User user=new User();
//		user.setPassWord(passWord);
		
		try {
			user=ConnectionFactory.findOneUser(userName);
			List<User> list = new ArrayList<User>();
			if(user!=null){
/*				out.println("��ѧ����Ϊ"+user.getUserName());
				out.println("�Ա�Ϊ"+user.getGender());
				out.println("���֤��Ϊ"+user.getIndentityId());
				out.println("��ѧ���Ϊ"+user.getYear());
				out.println("ѧԺΪ"+user.getCollage());*/
				list.add(user);
				String xmlStr = XmlUtils.makeUserXml(list);
				out.write(xmlStr);
				out.flush();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
