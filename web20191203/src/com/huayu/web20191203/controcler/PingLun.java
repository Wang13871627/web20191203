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
@WebServlet("/PingLun")
public class PingLun extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PingLun() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String remark="";//评论内容
		Integer postId=0;//帖子id
		String bcritics="";//被评论人
		Integer hui=0;//1为回复 0为评论
		UserTable u=(UserTable)request.getSession().getAttribute("usertable");
		String critic=u.getUsername();//评论人
		String data="";
		for(Enumeration<String> enu=request.getParameterNames();enu.hasMoreElements();){
			String name=enu.nextElement();
			String value=request.getParameter(name);
			if(name.equals("remark")){
				remark=value;
			}else if(name.equals("postId")){
				postId=Integer.valueOf(value);
			}else if(name.equals("bcritics")){
				bcritics=value;
			}else if(name.equals("hui")){
				hui=Integer.valueOf(value);
			}
		}
		if(null!=remark && remark.length()>0){
			Fangfa f=new Fangfa();
			if(hui==1){
				int flag=f.InsertComments(postId,critic,bcritics,remark,hui);
				if(flag>0){
					data="2";
				}else{
					data="网络异常，请稍后重试";
				}
			}else{
				int flag=f.InsertComments(postId,critic,bcritics,remark,hui);
				if(flag>0){
					data="1";
				}else{
					data="网络异常，请稍后重试";
				}
			}
		}else{
			data="您未输入评论内容，请重新输入";
		}
		response.getWriter().write(data);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
