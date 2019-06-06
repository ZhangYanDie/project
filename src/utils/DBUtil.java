package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DBUtil {
	private DBUtil(){}
	private static String url;
	private static String user;
	private static String password;
	private static String className;
	
	static{
		try {

			Properties setting=new Properties();
			InputStream in=DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
			setting.load(in);
			url=setting.getProperty("url");
			user=setting.getProperty("user");
			password=setting.getProperty("password");
			className=setting.getProperty("className");
			Class.forName(className);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static Connection getConn(){
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	

	public static void close(ResultSet rs,Connection conn,Statement stm){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stm!=null){
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
