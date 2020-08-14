package com.huayu.web20191203.controcler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huayu.web20191203.conn.Fangfa;
@WebServlet("/Dservlet")
public class Dservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Dservlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id=Integer.valueOf(request.getParameter("id"));
		Fangfa f=new Fangfa();
		f.dddc(id);
		response.sendRedirect("ServletDemo");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
