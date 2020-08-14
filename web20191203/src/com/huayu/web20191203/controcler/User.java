package com.huayu.web20191203.controcler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huayu.web20191203.bean.UserTable;
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public User() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserTable u=(UserTable)request.getSession().getAttribute("usertable");
		String flag="";
		if(null!=u){
			flag="1";
		}else{
			flag="2";
		}
		response.getWriter().write(flag);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
