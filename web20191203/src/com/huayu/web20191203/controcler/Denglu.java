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
@WebServlet("/Denglu")
public class Denglu extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Denglu() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username="";
		String password="";
		String data="";
		for(Enumeration<String> enu=request.getParameterNames();enu.hasMoreElements();){
			String name=enu.nextElement();
			String value=request.getParameter(name);
			if(name.equals("username")){
				username=value;
			}else if(name.equals("password")){
				password=value;
			}
		}
		if(null!=username && !"".equals(username) && null!=password && !"".equals(password)){
			Fangfa f=new Fangfa();
			ResultSet res=f.sUser(username, password);
			try {
				if(null!=res){
					UserTable u=new UserTable();
					boolean flag=false;
					while(res.next()){
						u.setId(res.getInt(1));
						u.setUsername(res.getString(2));
						u.setPassword(res.getString(3));
						u.setRole(res.getString(4));
						flag=true;
					}
					if(flag){
						request.getSession().setAttribute("usertable", u);
						data="1";
					}else{
						data="2";
					}
				}else{
					data="2";
				}
			} catch (SQLException e) {
				e.printStackTrace();
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
