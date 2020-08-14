package com.huayu.web20191203.controcler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huayu.web20191203.conn.Fangfa;
@WebServlet("/Gservlet2")
public class Gservlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Gservlet2() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=(String)request.getParameter("name");
		Integer grade=Integer.valueOf(request.getParameter("grade"));
		Integer grades=Integer.valueOf(request.getParameter("grades"));
		Integer id=Integer.valueOf(request.getParameter("id"));
		Fangfa f=new Fangfa();
		int flag=f.gddc(id, name, grade, grades);
		String ff=String.valueOf(flag);
		response.getWriter().write(ff);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
