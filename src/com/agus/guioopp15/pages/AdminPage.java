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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.agus.guioopp15.config.GhostText;
import com.agus.guioopp15.config.GuiWidgets;
import com.agus.guioopp15.config.MysqlConn;
import com.agus.guioopp15.config.RoundedButton;
import com.agus.guioopp15.config.RoundedJTextField;
import com.agus.guioopp15.config.StyleConstants;
import com.agus.guioopp15.pages.forms.AddDataSiswa;
import com.agus.guioopp15.pages.forms.DeleteDataSiswa;
import com.agus.guioopp15.pages.forms.EditDataSiswa;
import com.agus.guioopp15.config.ColorConstants;

public class AdminPage extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel tabModel;
	MysqlConn conn = new MysqlConn();
	GuiWidgets guiWidgets = new GuiWidgets();
	StyleConstants style = new StyleConstants();
	ColorConstants colorCons = new ColorConstants();
	
	String[] header= {"NIM","Nama","Fakultas","Jurusan","Kelas"};
	JTable table_mhs= new JTable();
	JScrollPane scrollTable= new JScrollPane();
	public Object[][] isiTable= null;
	
	Icon iconRefresh = new ImageIcon("res//icons/ic_refresh.png");
	Icon iconAddData = new ImageIcon("res//icons/ic_add_data.png");
	Icon iconEditData = new ImageIcon("res//icons/ic_edit_data.png");
	Icon iconDeleteData = new ImageIcon("res//icons/ic_delete.png");
	JLabel title = new JLabel("ADMINISTRATOR");
	JLabel subtitle = new JLabel("PENDAFTARAN SISWA BARU");
	JButton btnAdd = new RoundedButton(iconAddData, colorCons.green, colorCons.greenDark);
	JButton btnRefresh = new RoundedButton(iconRefresh, colorCons.blue, colorCons.blueDark);
	JButton btnEdit = new RoundedButton(iconEditData, colorCons.orange, colorCons.orangeDark);
	JButton btnDelete = new RoundedButton(iconDeleteData, colorCons.red, colorCons.redDark);
	RoundedJTextField txtSearch = new RoundedJTextField(15);
	GhostText ghostText = new GhostText(txtSearch, "Search by nim...");

	public AdminPage(){
		showData(conn.getConnection(), null);
	}
	
	
	public void refreshData() {
		showData(conn.getConnection(), null);
	}
	public void Action() {
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
		
		btnAdd.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				AddDataSiswa addData = new AddDataSiswa();
				addData.Layout();
				addData.Action();
				
			}
			
		});
		btnEdit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				EditDataSiswa editData = new EditDataSiswa();
				editData.Layout();
				editData.Action();
				
			}
			
		});
		btnDelete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteDataSiswa deleteData = new DeleteDataSiswa();
				deleteData.Layout();
				deleteData.Action();
				
			}
			
		});
		
		btnRefresh.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				refreshData();
			}
			
		});
		
	}
	
	public void Layout() {
		title.setBounds(20,20,200,30);
		title.setFont(style.title);
		subtitle.setBounds(20,40,300,30);
		subtitle.setFont(style.subTitle);
		btnAdd.setBounds(20 ,100,45,45);
		txtSearch.setBounds(20 + 55,105,140,30);
		txtSearch.setFont(style.txtFieldStyle);
		btnRefresh.setBounds(40 + 190,100,45,45);
		btnEdit.setBounds(40 + 240,100,45,45);
		btnDelete.setBounds(40 + 290,100,45,45);
		
		scrollTable.setBounds(20,150,450,200);
		add(scrollTable);
		add(btnAdd);
		add(btnRefresh);
		add(title);
		add(subtitle);
		add(txtSearch);
		add(btnEdit);
		add(btnDelete);
		setTitle("Admin Page");
		setLocation(300, 150);
		setSize(800,400);
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
				isiTable[x][4] = res.getString("kelas");
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
	
//	public static void main(String[] args) {
//		AdminPage adminPage = new AdminPage();
//		adminPage.Layout();
//		adminPage.Action();
//	}

}
