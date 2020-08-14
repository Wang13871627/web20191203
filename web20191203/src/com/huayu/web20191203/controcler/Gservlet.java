package com.huayu.web20191203.controcler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huayu.web20191203.bean.AgentList;
@WebServlet("/Gservlet")
public class Gservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Gservlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id=Integer.valueOf(request.getParameter("id"));
		String name=(String)request.getParameter("name");
		Integer grade=Integer.valueOf(request.getParameter("grade"));
		Integer grades=Integer.valueOf(request.getParameter("grades"));
		AgentList list=new AgentList();
		list.setAid(id);
		list.setAname(name);
		list.setAgrade(grade);
		list.setSuperior(grades);
		if(null!=list){
        	request.setAttribute("post", list);
			request.getRequestDispatcher("jsp/xiu.jsp").forward(request, response);
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
