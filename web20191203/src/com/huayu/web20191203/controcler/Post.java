package com.huayu.web20191203.controcler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huayu.web20191203.bean.Comments;
import com.huayu.web20191203.bean.Page;
import com.huayu.web20191203.bean.PostTable;
import com.huayu.web20191203.conn.Fangfa;
@WebServlet("/Post")
public class Post extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Post() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hui=request.getParameter("hui");
		Fangfa f=new Fangfa();
		String page1=request.getParameter("page");
		List<PostTable> list=f.SelectPost(page1);
		List<Comments> listm=f.SelectComments();
		if(null!=list && list.size()>0){
			request.setAttribute("post", list);//帖子
			Page p=f.p();
			request.setAttribute("page", p);//分页
			request.setAttribute("pinglun", listm);//评论
			if(null!=hui && !"".equals(hui)){
				request.setAttribute("hf", 1);//评论
			}
			request.getRequestDispatcher("jsp/postBar.jsp").forward(request, response);
		}else{
			request.setAttribute("post", list);
			request.getRequestDispatcher("jsp/postBar.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
