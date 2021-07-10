package com.agus.guioopp15.pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.agus.guioopp15.config.ColorConstants;
import com.agus.guioopp15.config.GhostText;
import com.agus.guioopp15.config.GuiWidgets;
import com.agus.guioopp15.config.MysqlConn;
import com.agus.guioopp15.config.RoundedButton;
import com.agus.guioopp15.config.RoundedJTextField;
import com.agus.guioopp15.config.StyleConstants;

public class HomePage extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel tabModel;
	MysqlConn conn = new MysqlConn();
	GuiWidgets guiWidgets = new GuiWidgets();
	StyleConstants style = new StyleConstants();
	ColorConstants colorCons = new ColorConstants();
	
	
	Icon iconRefresh = new ImageIcon("res//icons/ic_refresh.png");
	String[] header= {"NIM","Nama","Fakultas","Jurusan"};
	JTable table_mhs= new JTable();
	JScrollPane scrollTable= new JScrollPane();
	public Object[][] isiTable= null;
	JLabel title = new JLabel("PENDAFTARAN SISWA BARU");
	JLabel subtitle = new JLabel("SELAMAT DATANG DI APLIKASI PENDAFTARAN SISWA BARU!");
	JLabel note = new JLabel("*Silahkan cek nama anda apakah sudah terdaftar dalam sistem.");
	JButton btnRefresh = new RoundedButton(iconRefresh, colorCons.blue, colorCons.blueDark);
	RoundedJTextField txtSearch = new RoundedJTextField(15);
	GhostText ghostText = new GhostText(txtSearch, "Cari NIM anda...");
	
	JMenuBar menuAgus = new JMenuBar();
	
//	Menu More
	JMenu menu_more = new JMenu("More");
	JMenuItem menu_item_about = new JMenuItem("About Me");
//	#===========
	
//	Menu Login
	JMenu menu_login = new JMenu("Login");
	JMenuItem menu_item_login_admin = new JMenuItem("Login As Admin");
//	#===========
	
	
	HomePage(){
		showData(conn.getConnection(), null);
	}
	
	private void refreshData() {
		showData(conn.getConnection(), null);
	}
	void Action() {
		AbstractAction txtSearchOnEnterListener = new AbstractAction()
		{
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				showData(conn.getConnection(), txtSearch.getText());
		    }
		};
		txtSearch.addActionListener(txtSearchOnEnterListener);
		
		menu_item_about.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				AboutPage aboutPage = new AboutPage();
				aboutPage.Layout();
			}
			
		});
		
		menu_item_login_admin.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage();
				loginPage.Layout();
				loginPage.Action();
			}
			
		});
		
		btnRefresh.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				refreshData();
			}
			
		});
		
		
	}
	
	void setMenu() {
		setJMenuBar(menuAgus);
		
		menuAgus.add(menu_more);
		menu_more.add(menu_item_about);
		
		menuAgus.add(menu_login);
		menu_login.add(menu_item_login_admin);
	}
	
	void Layout() {
		
		title.setBounds(20,20,400,30);
		title.setFont(style.title);
		
		subtitle.setBounds(20,40,600,30);
		subtitle.setFont(style.subTitle);
		
		note.setBounds(20,60,600,30);
		
		btnRefresh.setBounds(20,100,45,45);
		txtSearch.setBounds(80,105,140,30);
		scrollTable.setBounds(20,160,400,200);
		add(scrollTable);
		add(btnRefresh);
		add(txtSearch);
		add(title);
		add(subtitle);
		add(note);
		setMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Home Page");
		setLocation(20, 20);
		setSize(800,500);
		setLayout(null);
		setVisible(true);
	}
	
	
	void showData(Connection mysql,String NIM) {

		try {
			String sql;
			if(NIM != null) {
				sql= "SELECT * FROM siswa_201110199 WHERE nim="+NIM;
			}else {
				sql= "SELECT * FROM siswa_201110199";
			}
			Statement stmt= mysql.createStatement();
			
			ResultSet res= stmt.executeQuery(sql);
			ResultSetMetaData meta= res.getMetaData();
			
			int kolom= meta.getColumnCount(); 
			int baris= 0;
			

			while(res.next()) {
				baris= res.getRow();
			}	

			isiTable = null;
			isiTable= new Object[baris][kolom];
			
			int x= 0;
			
			res.beforeFirst();
			while(res.next()) {
				isiTable[x][0] = res.getString("NIM");
				isiTable[x][1] = res.getString("nama");
				isiTable[x][2] = res.getString("fakultas");
				isiTable[x][3] = res.getString("jurusan");
				x++;
			}
			scrollTable.setViewportView(table_mhs);
			table_mhs.setModel(new DefaultTableModel(isiTable, header));

			
			stmt.close();
			res.close();
			
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		HomePage home_page = new HomePage();
		home_page.Layout();
		home_page.Action();
	}

}
