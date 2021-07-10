package com.agus.guioopp15.actions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.agus.guioopp15.config.GuiWidgets;
import com.agus.guioopp15.config.MysqlConn;
import com.agus.guioopp15.pages.AdminPage;

public class LoginActions {
	GuiWidgets guiWidgets = new GuiWidgets();
	MysqlConn conn = new MysqlConn();
	
	public boolean loginAdmin(String username, String password) {
		try {
			Statement stmt =conn.getConnection().createStatement();
			String sql = "SELECT * FROM admin_201110199 WHERE username='"+username+"' AND password=md5('"+password+"')";
			ResultSet res = stmt.executeQuery(sql);
			int baris= 0;
 
			while(res.next()) {
				baris= res.getRow();
			}	
			
			if(baris > 0) {
				AdminPage admin_page = new AdminPage();
				admin_page.Layout();
				admin_page.Action();
				return true;
			}else {
				guiWidgets.showMessage("Login failed, \nplease check your username and password properly!");
				return false;
			}
		}catch(SQLException sqlex) {
			guiWidgets.showMessage(sqlex.getMessage());
			return false;
		}
	}
}
