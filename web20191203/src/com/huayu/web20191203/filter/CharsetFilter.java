package com.huayu.web20191203.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebFilter("/*")
public class CharsetFilter implements Filter {
    public CharsetFilter() {
    }
	public void destroy() {
		System.out.println("贺小恒睡觉了");
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		req.getServletContext().setAttribute("nn", "utf-8");
		String charset=(String)req.getServletContext().getAttribute("nn");
		req.setCharacterEncoding(charset);
		resp.setCharacterEncoding(charset);
		chain.doFilter(req, resp);
	}
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("贺小恒起床了");
	}

}
