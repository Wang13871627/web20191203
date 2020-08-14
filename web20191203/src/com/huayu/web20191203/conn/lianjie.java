package com.huayu.web20191203.conn;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class lianjie {
   private static Connection con=null;
   static{
	   InputStream in=lianjie.class.getClassLoader().getResourceAsStream("/com/huayu/web20191203/conn/jdbc1.properties");
	   Properties p=new Properties();
	   try {
		p.load(in);
		   Class.forName(p.getProperty("Driver"));
		   con=DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
   }
   public static Connection getcon(){
	   return con;
   }
}
