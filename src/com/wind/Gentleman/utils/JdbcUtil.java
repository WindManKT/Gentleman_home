package com.wind.Gentleman.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Jdbc工具类
 */
public class JdbcUtil {

	private JdbcUtil(){

	}

	public static ComboPooledDataSource source = new ComboPooledDataSource();
	/***
	 * 注册驱动并获取连接
	 */
	public static Connection getConn() throws Exception {
		return source.getConnection();
	}
	
	/**
	 * 释放Jdbc程序中的资源
	 * @param conn 连接对象
	 * @param stat 传输器对象
	 * @param rs 结果集对象
	 */
	public static void close(Connection conn, Statement stat, ResultSet rs) {

		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs = null;
			}
		}
		if(stat != null){
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				stat = null;
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}

	}
	
	
}



