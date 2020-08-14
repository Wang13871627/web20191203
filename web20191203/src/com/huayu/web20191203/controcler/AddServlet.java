package com.huayu.web20191203.controcler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=(String)request.getAttribute("name");
		String grade=(String)request.getAttribute("grade");
		String grades=(String)request.getAttribute("grade1");
		String flag="";
		if(null!=name && ""!=name){
			flag="1";
		}else if(null!=grade && ""!=grade){
			flag="2";
		}else if(null!=grades && ""!=grades){
			flag="3";
		}else{
			flag="4";
		}
		response.getWriter().write(flag);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
