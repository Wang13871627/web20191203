package com.huayu.web20191203.conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.huayu.web20191203.bean.AgentList;
import com.huayu.web20191203.bean.Comments;
import com.huayu.web20191203.bean.MerList;
import com.huayu.web20191203.bean.Page;
import com.huayu.web20191203.bean.PostTable;

public class Fangfa {
public Page p=new Page();
   private Connection con=null;
   private List<List<AgentList>> all=new ArrayList<List<AgentList>>();
   //当前代理商
   public AgentList fagent(int aid){
	   con=lianjie.getcon();
	   QueryRunner query=new QueryRunner();
	   String sql="select * from agent where aid=?";
	   AgentList agent=new AgentList();
	   try {
		   agent=query.query(con, sql,new BeanHandler<>(AgentList.class),aid);
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }return agent;
   }
   //代理商的孩子
   public List<AgentList> agentChild(int aid){
	   con=lianjie.getcon();
	   QueryRunner query=new QueryRunner();
	   String sql="select * from agent where superior=?";
	   try {
		  List<AgentList>  list=query.query(con, sql,new BeanListHandler<>(AgentList.class),aid);
		  return list;
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
	   return null;
   }
   //代理商孩子的孩子
   public void agentCChild(List<AgentList> agentlist){
	   for(AgentList agent:agentlist){
		   List<AgentList> list=agentChild(agent.getAid());
		   if(null!=list && list.size()>0){
			   all.add(list);
		   }
		   agentCChild(list);
	   }
   }
   //所有商户
   public List<MerList> mer(int aid){
	   AgentList f=fagent(aid);
	   List<AgentList> listc=agentChild(aid);
	   agentCChild(listc);
	   listc.add(f);
	   all.add(listc);
	   StringBuffer buff=new StringBuffer();
	   for(List<AgentList> a: all){
		   for(AgentList b:a){
			   buff.append(b.getAid()).append(",");
		   }
	   }
	   String s=buff.substring(0, buff.length()-1).toString();
	   con=lianjie.getcon();
	   List<MerList> list=new ArrayList<>();
	   String sql="select mid,mname,maddress,mgrade,legalname,legalpohone,mtime,"
	   + "case mstate when 0 then '默认状态' when 1 then '审核成功' when 2 then '审核失败' "
	   + "when 3 then '待审核' else '无数据' end,agentnumber from merchants where agentnumber in("+s+")";
	   QueryRunner query=new QueryRunner();
	   try {
		   list=query.query(con, sql, new BeanListHandler<>(MerList.class));
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
	   return list;
   }
   
   public Page p(){
	   return p;
   }
   @SuppressWarnings("unused")
   //下级代理商的数量
   public Integer num(int aid){
	   List<AgentList> listc=agentChild(aid);//直属孩子
	   agentCChild(listc);//孩子的孩子。。。。。
	   all.add(listc);
	   int num=0;
	   for(List<AgentList> a:all){
		   for(AgentList b:a){
			   num++;
		   }
	   }
	   return num;
   }
   //下级代理商的信息
   public List<AgentList> xiaAgent(int aid){
	   List<AgentList> listc=agentChild(aid);
	   agentCChild(listc);
	   all.add(listc);
	   StringBuffer buff=new StringBuffer();
	   for(List<AgentList> a:all){
		   for(AgentList b:a){
			   buff.append(b.getAid()).append(",");
		   }
	   }
	   String s=buff.substring(0, buff.length()-1).toString();
	   con=lianjie.getcon();
	   List<AgentList> list=new ArrayList<>();
	   String sql="select * from agent where aid in("+s+")";
	   QueryRunner query=new QueryRunner();
	   try {
		   list=query.query(con, sql, new BeanListHandler<>(AgentList.class));
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
	   return list;
   }
   //所有代理商的信息
   public List<AgentList> agent(){
	   con=lianjie.getcon();
	   String sql="select * from agent";
	   List<AgentList> list=new ArrayList<>();
	   QueryRunner query=new QueryRunner();
	   try {
		   list=query.query(con,sql,new BeanListHandler<>(AgentList.class));
		   for(AgentList a:list){
			   Fangfa fa=new Fangfa();
			   int num=fa.num(a.getAid());
			   a.setNumber(num);
		   }
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }return list;
   }

   public ResultSet sUser(String username,String password){
	   con=lianjie.getcon();
	   String sql="select * from usertable ";
	   if(null!=password){//登录
		   sql+="where username=? and password=?";
	   }else{//注册
		   sql+="where username=?";
	   }
	   ResultSet res=null;
		   try {
			PreparedStatement pre=con.prepareStatement(sql);
			   if(null!=password){
				   pre.setString(1, username);
				   pre.setString(2, password);
			   }else{
				   pre.setString(1, username);
			   }
			   res=pre.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}return res;
   }
   public int insertUser(String username,String password){
	   con=lianjie.getcon();
	   int flag=2;
	   String sql="insert into usertable values(null,?,?,'用户')";
	   try {
		   PreparedStatement pre=con.prepareStatement(sql);
		   pre.setString(1, username);
		   pre.setString(2, password);
		   flag=pre.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}return flag;
   }
   public int insertPost(Integer usernameId,String title,String content,Integer audit){
	   con=lianjie.getcon();
	   int flag=2;
	   String sql="insert into post values(null,?,?,?,now(),?)";
	   try {
		   PreparedStatement pre=con.prepareStatement(sql);
		   pre.setInt(1, usernameId);
		   pre.setString(2, title);
		   pre.setString(3, content);
		   pre.setInt(4, audit);
		   flag=pre.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}return flag;
   }
   public List<PostTable> SelectPost(String page1){
	   con=lianjie.getcon();
	   String sql1 = "select count(1) from post left join usertable on post.usernameId=usertable.id where audit=1";
	   List<PostTable> list=null;
	   try {
	   PreparedStatement pre1 = con.prepareStatement(sql1);
	   ResultSet res1 = pre1.executeQuery();
	   Integer page = p.getPage();//一页显示几条
	   while (null != res1 && res1.next()) {
		   p.setCount(Integer.valueOf(res1.getString(1)));// 总条数
	   }
	   Integer id;
	   if (null != page1 && !"".equals(page1)) {
		   id = Integer.valueOf(page1);
		   if (id <= 0) {
			   id = 1;
		   } else if (id >= p.getAllpage()) {
			   id = p.getAllpage();
		   }
		   p.setNowpage(id);
	   } else {
		   p.setNowpage(1);// 设置初始页为1
	   }
	   Integer nowpage = p.getNowpage();
	   String sql="select * from post left join usertable on post.usernameId=usertable.id order by post.id desc limit ?,? ";
	   list=new ArrayList<>();
	   PreparedStatement pre=con.prepareStatement(sql);
	   pre.setInt(1, page * (nowpage - 1));
	   pre.setInt(2, page);
	   ResultSet res=pre.executeQuery();
		   if(null!=res){
			   while(res.next()){
				   PostTable pp=new PostTable();
				   pp.setId(res.getInt(1));
				   pp.setUsernameId(res.getInt(7));
				   pp.setUsername(res.getString(8));
				   pp.setTitle(res.getString(3));
				   pp.setContent(res.getString(4));
				   pp.setTime(res.getString(5));
				   pp.setAudit(res.getInt(6));
				   list.add(pp);
			   }
		   }
	} catch (SQLException e) {
		e.printStackTrace();
	}return list;
   }
   public int InsertComments(Integer postId,String critic,String bcritics,String remark,Integer hui){
	   con=lianjie.getcon();
	   int flag=0;
	   String sql="insert into Comments values(null,?,?,?,?,now(),?)";
	   try {
		PreparedStatement pre=con.prepareStatement(sql);
		   pre.setInt(1, postId);//帖子id
		   pre.setString(2, critic);//评论人
		   pre.setString(3, bcritics);//被评论人
		   pre.setString(4, remark);//评论内容
		   pre.setInt(5, hui);//评论内容
		   flag=pre.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}return flag;
   }
   public List<Comments> SelectComments(){
	   con=lianjie.getcon();
	   List<Comments> list=new ArrayList<>();
	   String sql="select * from Comments";
	   try {
		   QueryRunner query=new QueryRunner();
		   //ResultSetHandler<T>接口 这个接口的实现类，转换查询结果为对象
		   list = query.query(con, sql,new BeanListHandler<Comments>(Comments.class));
		   /*PreparedStatement pre=con.prepareStatement(sql);
		   ResultSet res=pre.executeQuery();
		   while(null!=res && res.next()){
			   Comments c=new Comments();
			   c.setId(res.getInt(1));//评论id
			   c.setPostId(res.getInt(2));//帖子id
			   c.setCritic(res.getString(3));//评论人
			   c.setBcritics(res.getString(4));//被评论人
			   c.setRemark(res.getString(5));//评论内容
			   c.setTime(res.getString(6));//评论时间
			   c.setHui(res.getInt(7));//评论/回复
			   list.add(c);
		   }*/
	} catch (SQLException e) {
		e.printStackTrace();
	}return list;
   }
   public List<PostTable> postSh(){
	   con=lianjie.getcon();
	   List<PostTable> list=new ArrayList<>();
	   String sql="select * from post left join usertable on post.usernameId=usertable.id where  audit=0";
	   try {
		PreparedStatement pre=con.prepareStatement(sql);
		   ResultSet res=pre.executeQuery();
		   while(null!=res && res.next()){
			   PostTable pp=new PostTable();
			   pp.setId(res.getInt(1));
			   pp.setUsernameId(res.getInt(7));
			   pp.setUsername(res.getString(8));
			   pp.setTitle(res.getString(3));
			   pp.setContent(res.getString(4));
			   pp.setTime(res.getString(5));
			   pp.setAudit(res.getInt(6));
			   list.add(pp);
		   }
	} catch (SQLException e) {
		e.printStackTrace();
	}return list;
   }
   public int DPost(Integer id){
	   con=lianjie.getcon();
	   int flag=0;
	   String sql="delete from post where id=?";
	   QueryRunner query=new QueryRunner();
	   try {
	    flag=query.update(con, sql, id);
		/*PreparedStatement pre=con.prepareStatement(sql);
		pre.setInt(1, id);//帖子id
	    flag=pre.executeUpdate();*/
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }return flag;
   }
   public int uPost(Integer id){
	   int flag=0;
	   con=lianjie.getcon();
	   String sql="update post set audit=1 where id=?";
	   QueryRunner query=new QueryRunner();
	   try {
		   flag=query.update(con, sql, id);
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }return flag;
   }
   
   public List<AgentList> city1(){
		String sql="select * from agent";
		con=lianjie.getcon();
		List<AgentList> list=new ArrayList<>();
		try {
			PreparedStatement pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(null!=res && res.next()){
				while(res.next()){
					AgentList a=new AgentList();
					a.setAid(res.getInt(1));
					a.setAname(res.getString(2));
					a.setAgrade(res.getInt(3));
					a.setSuperior(res.getInt(4));
					list.add(a);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
    public int addc(String name,Integer grade,Integer grades){
    	String sql="insert into agent values(null,?,?,?)";
    	con=lianjie.getcon();
    	try {
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setString(1, name);
			pre.setInt(2, grade);
			pre.setInt(3, grades);
			int flag=pre.executeUpdate();
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
		}return 0;
    }
    public int dddc(Integer id){
    	String sql="delete from agent where aid=?";
    	con=lianjie.getcon();
    	try {
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setInt(1, id);
			int flag=pre.executeUpdate();
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
		}return 0;
    }
    public int gddc(Integer id,String name,Integer grade,Integer grades){
    	String sql="update agent set aname=?,agrade=?,superior=? where aid=?";
    	con=lianjie.getcon();
    	try {
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setString(1, name);
			pre.setInt(2, grade);
			pre.setInt(3, grades);
			pre.setInt(4, id);
			int flag=pre.executeUpdate();
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
		}return 0;
    }
    
    public List<AgentList> sou(String f){
    	con=lianjie.getcon();
    	List<AgentList> list=new ArrayList<>();
    	String sql="select aname,agrade from agent where aname like '%"+f+"%' or agrade like '%"+f+"%'";
    	try {
			PreparedStatement pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			if(null!=res){
				while(res.next()){
					AgentList a=new AgentList();
					a.setAname(res.getString(1));
					a.setAgrade(res.getInt(2));
					list.add(a);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}return list;
    }
}
