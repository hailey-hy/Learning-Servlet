package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		PrintWriter out = response.getWriter();
		
		String v_ = request.getParameter("value");
		String bt = request.getParameter("button");
		
		int v = 0;
		
		if (!v_.equals("")) v = Integer.parseInt(v_);
		
		if(bt.equals("=")) {
			
//			int x = (Integer)session.getAttribute("value");
//			int x = (Integer)application.getAttribute("value");
			int x = 0;
			for(Cookie c : cookies) {
			if(c.getName().equals("value"))
				x = Integer.parseInt(c.getValue());
				break;
				}
			int y = v;
			
//			String button = (String)session.getAttribute("button");
//			String button = (String)application.getAttribute("button");
			
			String button = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("button"))
					button = c.getValue();
					break;
					}
			
			int total = 0;
			if(button.equals("+")) total = x + y;
			if(button.equals("-")) total = x - y;
			
			response.getWriter().printf("total is %d\n", total);
		}
		else {
//			application.setAttribute("value", v);
//			application.setAttribute("button", bt);
			
//			session.setAttribute("value", v);
//			session.setAttribute("button", bt);
			
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie btCookie = new Cookie("button", bt);
			valueCookie.setPath("/calc2");
			btCookie.setPath("/calc2");
			response.addCookie(valueCookie);
			response.addCookie(btCookie);
			
		}
	}
}
