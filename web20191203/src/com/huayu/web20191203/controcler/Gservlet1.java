package com.huayu.web20191203.controcler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Gservlet1")
public class Gservlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Gservlet1() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=(String)request.getAttribute("name");
		String grade=(String)request.getAttribute("grade");
		String grades=(String)request.getAttribute("grade1");
		String flag="";
		if(null==name && "".equals(name)){
			flag="1";
		}else if(null==grade && "".equals(grade)){
			flag="2";
		}else if(null==grades && "".equals(grades)){
			flag="3";
		}else{
			flag="4";
		}
		System.out.println(flag);
		response.getWriter().write(flag);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
