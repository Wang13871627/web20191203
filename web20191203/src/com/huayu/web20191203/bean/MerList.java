package com.huayu.web20191203.bean;

import java.io.Serializable;

public class MerList implements Serializable{
	private static final long serialVersionUID = 1L;
    private String mid;//商户id
    private String mname;//商户名称
    private String maddress;//商户地址
    private int mgrade;//商户等级
    private String legalname;//法人姓名
    private String legalpohone;//法人电话
    private String mtime;//进件时间
    private String mstate;//审核状态
    private int agentnumber;//代理人id
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMaddress() {
		return maddress;
	}
	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}
	public int getMgrade() {
		return mgrade;
	}
	public void setMgrade(int mgrade) {
		this.mgrade = mgrade;
	}
	public String getLegalname() {
		return legalname;
	}
	public void setLegalname(String legalname) {
		this.legalname = legalname;
	}
	public String getLegalpohone() {
		return legalpohone;
	}
	public void setLegalpohone(String legalpohone) {
		this.legalpohone = legalpohone;
	}
	public String getMtime() {
		return mtime;
	}
	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	public String getMstate() {
		return mstate;
	}
	public void setMstate(String mstate) {
		this.mstate = mstate;
	}
	public int getAgentnumber() {
		return agentnumber;
	}
	public void setAgentnumber(int agentnumber) {
		this.agentnumber = agentnumber;
	}
	public String toString() {
		return "MerList [mid=" + mid + ", mname=" + mname + ", maddress=" + maddress + ", mgrade=" + mgrade
				+ ", legalname=" + legalname + ", legalpohone=" + legalpohone + ", mtime=" + mtime + ", mstate="
				+ mstate + ", agentnumber=" + agentnumber + "]";
	}
}
