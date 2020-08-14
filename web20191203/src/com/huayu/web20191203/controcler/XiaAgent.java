package com.huayu.web20191203.controcler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/XiaAgent")
public class XiaAgent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public XiaAgent() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag="";
		String num=request.getParameter("num");
		if(num.equals("0")){
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
