package com.huayu.web20191203.bean;

import java.io.Serializable;

public class PostTable implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer usernameId;
    private String username;
    private String title;
    private String content;
    private String time;
    private Integer audit;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUsernameId() {
		return usernameId;
	}
	public void setUsernameId(Integer usernameId) {
		this.usernameId = usernameId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getAudit() {
		return audit;
	}
	public void setAudit(Integer audit) {
		this.audit = audit;
	}
	public String toString() {
		return "PostTable [id=" + id + ", usernameId=" + usernameId + ", username=" + username + ", title=" + title
				+ ", content=" + content + ", time=" + time + ", audit=" + audit + "]";
	}
}
