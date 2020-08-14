package com.huayu.web20191203.bean;

import java.io.Serializable;

public class Page implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer count;//总条数
	@SuppressWarnings("unused")
	private Integer allpage;//总页数    总条数%一页显示的条数==0？总条数/一页显示的条数:总条数/一页显示的条数+1
    private Integer page=2;//一页显示2条数据
    private Integer nowpage;//当前页数
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getAllpage() {
		return count%page==0?count/page:count/page+1;
	}
	public void setAllpage(Integer allpage) {
		this.allpage = allpage;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getNowpage() {
		return nowpage;
	}
	public void setNowpage(Integer nowpage) {
		this.nowpage = nowpage;
	}
}
