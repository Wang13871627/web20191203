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
@WebServlet("/XiaAgent1")
public class XiaAgent1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public XiaAgent1() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer aid=Integer.valueOf(request.getParameter("xi"));
		Fangfa f=new Fangfa();
	    List<AgentList> list=f.xiaAgent(aid);
	    request.setAttribute("list", list);
		request.getRequestDispatcher("jsp/xia.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
