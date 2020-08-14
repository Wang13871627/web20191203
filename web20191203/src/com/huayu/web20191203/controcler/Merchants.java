package com.huayu.web20191203.controcler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huayu.web20191203.bean.MerList;
import com.huayu.web20191203.conn.Fangfa;
@WebServlet("/Merchants")
public class Merchants extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Merchants() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer aid=Integer.valueOf(request.getParameter("aid"));
	    Fangfa f=new Fangfa();
	    List<MerList> list=f.mer(aid);
	    request.setAttribute("list", list);
		request.getRequestDispatcher("jsp/mer.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
