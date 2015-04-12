package com.ly.utils;

import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import com.ly.domain.Book;
import com.ly.domain.User;

public class XmlUtils {
	
	public static String makeUserXml(List<User> users) {		
		String result = "";
		Element root = new Element("Users");
		Document doc = new Document(root);

		for (User user : users) {
			Element element = new Element("user");
			element.setAttribute(new Attribute("id", user.getId() + ""));
			element.addContent(new Element("userName").setText(user
					.getUserName()));			
			element.addContent(new Element("passWord").setText(user
					.getPassWord()));
			element.addContent(new Element("indentityId").setText(user
					.getIndentityId()));
			element.addContent(new Element("year").setText(user
					.getYear()));
			element.addContent(new Element("gender").setText(user
					.getGender()));
			element.addContent(new Element("collage").setText(user
					.getCollage()));
			root.addContent(element);
		}
		XMLOutputter xmlOut = new XMLOutputter();
		result = xmlOut.outputString(doc);
		return result;
	}
	
	public static String makeBookXml(Book book){
		String result="";
		Element root=new Element("books");
		Document doc=new Document(root);
		
		Element element = new Element("book");
		element.setAttribute(new Attribute("id", book.getId()+""));
		element.addContent(new Element("bookName").setText(book.getBookName()));
		element.addContent(new Element("bookAuthor").setText(book.getBookAuthor()));
		element.addContent(new Element("phName").setText(book.getPhName()));
		element.addContent(new Element("location").setText(book.getLocation()));
		element.addContent(new Element("category").setText(book.getCategory()));
		element.addContent(new Element("status").setText(book.getStatus()));
		element.addContent(new Element("rentTime").setText(book.getRentTime()));
		
		root.addContent(element);
		
		XMLOutputter xmlOut = new XMLOutputter();
		result=xmlOut.outputString(doc);
		return result;
	
	}
}
