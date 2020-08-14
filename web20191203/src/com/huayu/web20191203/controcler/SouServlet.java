package com.huayu.web20191203.controcler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.huayu.web20191203.bean.AgentList;
import com.huayu.web20191203.conn.Fangfa;
@WebServlet("/SouServlet")
public class SouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SouServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ff=request.getParameter("f");
		Fangfa f=new Fangfa();
		List<AgentList> list=f.sou(ff);
		String str=JSON.toJSONString(list);
		response.getWriter().write(str);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
