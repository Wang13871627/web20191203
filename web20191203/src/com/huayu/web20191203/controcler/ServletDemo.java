package com.huayu.web20191203.controcler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huayu.web20191203.bean.AgentList;
import com.huayu.web20191203.conn.Fangfa;

@WebServlet("/ServletDemo")
public class ServletDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ServletDemo() {
    	super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Fangfa f=new Fangfa();
        List<AgentList> list=f.city1();
        if(null!=list){
        	request.setAttribute("post", list);
			request.getRequestDispatcher("jsp/dai.jsp").forward(request, response);
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
