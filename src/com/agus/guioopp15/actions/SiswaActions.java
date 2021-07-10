package com.agus.guioopp15.actions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.agus.guioopp15.config.GuiWidgets;
import com.agus.guioopp15.config.MysqlConn;

public class SiswaActions {

	
	MysqlConn mysql = new MysqlConn();
	Connection getConn = mysql.getConnection();
	GuiWidgets guiWidgets = new GuiWidgets();
	
	String nim,
	nama,
	fakultas,
	jurusan,
	kelas;
	
	private void breakHashMapDataSiswa(HashMap<String,String> dataSiswa) {
		this.nim = dataSiswa.get("nim");
		this.nama = dataSiswa.get("nama");
		this.fakultas = dataSiswa.get("fakultas");
		this.jurusan = dataSiswa.get("jurusan");
		this.kelas = dataSiswa.get("kelas");
	}
	
	public boolean addSiswa(HashMap<String,String> dataSiswa) {
		breakHashMapDataSiswa(dataSiswa);
		try {
			Statement stmt = getConn.createStatement();
			String sql = "INSERT INTO siswa_201110199 VALUES ('"
					+ this.nim +"','"
					+ this.nama +"','"
					+ this.fakultas +"','"
					+ this.jurusan +"','"
					+ this.kelas +"');";
			
			int execute = stmt.executeUpdate(sql);
			if(execute == 1) {
				guiWidgets.showMessage("Data berhasil disimpan");
				return true;
			}
		}catch(SQLException sqlex) {
			guiWidgets.showMessage(sqlex.getMessage());
			return false;
		}
		return false;
	}
	
	public boolean editSiswa(HashMap<String,String> dataSiswa) {
		breakHashMapDataSiswa(dataSiswa);
		
		try {
			Statement stmt = getConn.createStatement();
			String sql = "UPDATE siswa_201110199 SET"
					+" nama='" + this.nama +"'"
					+", fakultas='"+ this.fakultas +"'"
					+", jurusan='"+ this.jurusan +"'"
					+", kelas='"+ this.kelas +"'"
					+" WHERE nim='"+ this.nim +"'";
			int execute = stmt.executeUpdate(sql);
			if(execute == 1) {
				guiWidgets.showMessage("Data berhasil diedit");
				return true;
			}
		}catch(SQLException sqlex) {
			guiWidgets.showMessage(sqlex.getMessage());
			return false;
		}
		
		return false;
	}
	
	public boolean deleteSiswa(String nimSiswa) {
		try {
			Statement stmt = getConn.createStatement();
			String sql = "DELETE FROM siswa_201110199"
					+" WHERE nim='"+ nimSiswa +"'";
			int execute = stmt.executeUpdate(sql);
			if(execute == 1) {
				guiWidgets.showMessage("Data berhasil dihapus");
				return true;
			}
		}catch(SQLException sqlex) {
			guiWidgets.showMessage(sqlex.getMessage());
			return false;
		}
		return false;
	}
	
	public ArrayList<String> getOnlyNIM(){
		ArrayList<String> dataNIM = new ArrayList<String>();
		try {
			Statement stmt = getConn.createStatement();
			String sql = "SELECT NIM FROM siswa_201110199";
			
			ResultSet res= stmt.executeQuery(sql);
			
			int baris= 0;
			

			while(res.next()) {
				baris= res.getRow();
			}
			
			System.out.println(baris);
			
	
			int index = 0;
			res.beforeFirst();
			while(res.next()) {
				dataNIM.add(res.getString("NIM"));
				index++;
			}
		}catch(SQLException sqlex) {
			guiWidgets.showMessage(sqlex.getMessage());
		}
		
		return dataNIM;
	}
}
