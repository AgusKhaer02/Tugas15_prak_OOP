package com.agus.guioopp15.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConn {
	
	public Connection getConnection() {
		String database = "db_201110199";
		String url = "jdbc:mysql://localhost/"+database;
		String user = "root";
		String password = "";
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
			System.out.println("OK : \n"
					+ " - Driver Connection ditemukan \n"
					+ " - Berhasil Koneksi ke database coba_db_java1 \n");
		}catch (ClassNotFoundException ex){
			System.out.println("Error : Driver tidak ditemukan \n"+ex+"\n");
		}catch (SQLException ex) {
			System.out.println("Error : Tidak bisa koneksi ke database \n"+ex+"\n");
		}
		
		return conn;
	}
	
}
