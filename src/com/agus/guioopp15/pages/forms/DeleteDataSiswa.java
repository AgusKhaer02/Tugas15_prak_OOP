package com.agus.guioopp15.pages.forms;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.agus.guioopp15.actions.LoginActions;
import com.agus.guioopp15.actions.SiswaActions;
import com.agus.guioopp15.config.GuiWidgets;
import com.agus.guioopp15.config.RoundedJTextField;
import com.agus.guioopp15.config.StyleConstants;
import com.agus.guioopp15.pages.AdminPage;

public class DeleteDataSiswa extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	LoginActions loginActions = new LoginActions();
	SiswaActions actions = new SiswaActions();
	StyleConstants style = new StyleConstants();
	GuiWidgets widgets = new GuiWidgets();
	AdminPage adminPage = new AdminPage();
	
	JLabel lblJudul = new JLabel("Delete Data Siswa");
	JLabel lblNIM = new JLabel("NIM");
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox txtNIM = new JComboBox(actions.getOnlyNIM().toArray());
	RoundedJTextField txtNama = new RoundedJTextField(15);
	
	JButton btnLogin = new JButton("Submit");
	public DeleteDataSiswa(){
		setTitle("Delete data siswa");
		setLocation(300, 150);
		setSize(680, 300);
		setBackground(Color.gray);
	}
	public void Action() {
		btnLogin.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = widgets.showMessageConfirm("Apakah anda ingin menghapus data ini?", "PERINGATAN hapus data", JOptionPane.YES_NO_CANCEL_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					boolean executeDelete = actions.deleteSiswa(String.valueOf(txtNIM.getSelectedItem()));
					if(executeDelete) {
						dispose();
						adminPage.refreshData();
					}
				}
				
				
			}
			
		});
		
	}
	public void Layout() {
		lblJudul.setBounds(15, 0, 200, 20);
		lblJudul.setFont(style.title);
		
		lblNIM.setBounds(15, 30 , 100, 20);
		txtNIM.setBounds(15, 55, 205, 30);
		txtNIM.setFont(style.txtFieldStyle);
		
		btnLogin.setBounds(15, 100, 100, 40);
		btnLogin.setBackground(Color.green);
		btnLogin.setForeground(Color.black);
		
		add(lblJudul);
		add(lblNIM);
		add(txtNIM);
		add(btnLogin);
		setLayout(null);
		setVisible(true);
	}
}
