package com.huayu.web20191203.controcler;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huayu.web20191203.bean.UserTable;
import com.huayu.web20191203.conn.Fangfa;
@WebServlet("/InsertPost")
public class InsertPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InsertPost() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title="";
		String content="";
		String data="";
		for(Enumeration<String> enu=request.getParameterNames();enu.hasMoreElements();){
			String name=enu.nextElement();
			String value=request.getParameter(name);
			if(name.equals("title")){
				title=value;
			}else if(name.equals("content")){
				content=value;
			}
		}
		if(null!=title && !"".equals(title) && null!=content && !"".equals(content)){
			Fangfa f=new Fangfa();
			UserTable u=(UserTable)request.getSession().getAttribute("usertable");
			Integer usernameld=u.getId();
			Integer audit=0;
			if(u.getRole().equals("管理员")){
				audit=1;
				int flag1=f.insertPost(usernameld,title, content,audit);
				if(flag1==2){//发表失败
					data="2";
				}else{//发表成功
					data="1";
				}
			}else{
				audit=0;
				int flag1=f.insertPost(usernameld,title, content,audit);
				if(flag1==1){
					data="3";
				}else{
					data="2";
				}
			}
		}else{
			data="数据为空,请重新输入";
		}
		response.getWriter().write(data);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
