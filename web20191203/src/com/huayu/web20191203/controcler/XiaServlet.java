package com.huayu.web20191203.controcler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huayu.web20191203.bean.AgentList;
import com.huayu.web20191203.conn.Fangfa;
@WebServlet("/XiaServlet")
public class XiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String LINE_SEPRETOR=System.getProperty("line.separator");
    public XiaServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Fangfa f=new Fangfa();
		List<AgentList> list=f.city1();
		StringBuffer buff=new StringBuffer();
		buff.append("代理商id|"+"代理商名称|"+"代理商等级|"+"上级代理商id"+LINE_SEPRETOR);
		for(AgentList a:list){
			buff.append(a.getAid()+"|"+a.getAname()+"|"+a.getAgrade()+"|"+a.getSuperior()+LINE_SEPRETOR);
		}
		PrintWriter p=response.getWriter();
		p.write(buff.toString());
		Date now=new Date();
		SimpleDateFormat simple=new SimpleDateFormat("yyyyMMddHHmmss");
		String time=simple.format(now);
		String filename="代理"+time+".txt";
		filename=URLEncoder.encode(filename, "utf-8");
		response.setHeader("Content-Disposition","attachment;filename="+filename);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
