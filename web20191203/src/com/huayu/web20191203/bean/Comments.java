package com.huayu.web20191203.bean;

import java.io.Serializable;

public class Comments implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer postId;
    private String critic;
    private String bcritics;
    private String remark;
    private String time;
    private Integer hui;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getCritic() {
		return critic;
	}
	public void setCritic(String critic) {
		this.critic = critic;
	}
	public String getBcritics() {
		return bcritics;
	}
	public void setBcritics(String bcritics) {
		this.bcritics = bcritics;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getHui() {
		return hui;
	}
	public void setHui(Integer hui) {
		this.hui = hui;
	}
	public String toString() {
		return "Comments [id=" + id + ", postId=" + postId + ", critic=" + critic + ", bcritics=" + bcritics
				+ ", remark=" + remark + ", time=" + time + ", hui=" + hui + "]";
	}
}
