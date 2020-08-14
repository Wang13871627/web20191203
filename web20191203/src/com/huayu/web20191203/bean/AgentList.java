package com.huayu.web20191203.bean;

import java.io.Serializable;

public class AgentList implements Serializable{
	private static final long serialVersionUID = 1L;
    private int aid;//代理商id
    private String aname;//代理商名称
    private int agrade;//代理商等级
    private int superior;//上级代理商编号
    private int number;//下级代理商的数量
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public int getAgrade() {
		return agrade;
	}
	public void setAgrade(int agrade) {
		this.agrade = agrade;
	}
	public int getSuperior() {
		return superior;
	}
	public void setSuperior(int superior) {
		this.superior = superior;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String toString() {
		return "AgentList [aid=" + aid + ", aname=" + aname + ", agrade=" + agrade + ", superior=" + superior
				+ ", number=" + number + "]";
	}
}
