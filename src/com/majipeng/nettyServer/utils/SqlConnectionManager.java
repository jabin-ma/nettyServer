package com.majipeng.nettyServer.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnectionManager {
	private static final String mysql_url = "jdbc:mysql://192.168.199.145/mychat_db?characterEncoding=utf8&useSSL=false";
	private static final String Driver_Name = "com.mysql.jdbc.Driver";

	private static final String sUserName = "majipeng";
	private static final String sPassword = "Wo19940728";
    static {
    	try {
			Class.forName(Driver_Name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
	private SqlConnectionManager(){}
	
	public static Connection getConnection(){
		Connection con=null;
		try {
			 con = DriverManager.getConnection(mysql_url, sUserName, sPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
