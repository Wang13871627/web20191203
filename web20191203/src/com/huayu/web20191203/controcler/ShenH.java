package com.huayu.web20191203.controcler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huayu.web20191203.conn.Fangfa;
@WebServlet("/ShenH")
public class ShenH extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShenH() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String data="";
		if(null!=id && !"".equals(id)){
			Fangfa f=new Fangfa();
			Integer id1=Integer.valueOf(id);
			int flag=f.uPost(id1);
			if(flag==1){
				data="1";
			}else{
				data="审核失败";
			}
		}else{
			data="没有该帖子！";
		}
		response.getWriter().write(data);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
