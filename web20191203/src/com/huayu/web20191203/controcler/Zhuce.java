package com.huayu.web20191203.controcler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huayu.web20191203.bean.UserTable;
import com.huayu.web20191203.conn.Fangfa;
@SuppressWarnings("unused")
@WebServlet("/Zhuce")
public class Zhuce extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Zhuce() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username="";
		String password="";
		String password1="";
		String data="";
		for(Enumeration<String> enu=request.getParameterNames();enu.hasMoreElements();){
			String name=enu.nextElement();
			String value=request.getParameter(name);
			if(name.equals("username")){
				username=value;
			}else if(name.equals("password")){
				password=value;
			}else if(name.equals("password1")){
				password1=value;
			}
		}
		if(null!=username && !"".equals(username) && null!=password && !"".equals(password) 
		    && null!=password1 && !"".equals(password1)){
			if(password.equals(password1)){
				Fangfa f=new Fangfa();
				ResultSet res=f.sUser(username,null);
				try {
					if(null!=res && res.next()){////该用户存在
						data="3";
					}else{////用户不存在 注册用户
						int flag1=f.insertUser(username, password);
						if(flag1==2){//注册失败
							data="2";
						}else{//注册成功
							data="1";
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				data="两次密码输入不一致,请重新输入";
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
