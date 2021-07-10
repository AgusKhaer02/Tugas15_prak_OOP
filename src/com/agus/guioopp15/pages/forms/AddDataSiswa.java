package com.agus.guioopp15.pages.forms;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.agus.guioopp15.actions.LoginActions;
import com.agus.guioopp15.actions.SiswaActions;
import com.agus.guioopp15.config.GhostText;
import com.agus.guioopp15.config.RoundedJTextField;
import com.agus.guioopp15.config.StyleConstants;
import com.agus.guioopp15.pages.AdminPage;

public class AddDataSiswa extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LoginActions loginActions = new LoginActions();
	StyleConstants style = new StyleConstants();
	SiswaActions actions = new SiswaActions();
	AdminPage adminPage = new AdminPage();
	
	JLabel lblJudul = new JLabel("Add Data Siswa");
	JLabel lblNIM = new JLabel("NIM");
	JLabel lblNama = new JLabel("Nama");
	JLabel lblFakultas = new JLabel("Fakultas");
	JLabel lblJurusan = new JLabel("Jurusan");
	JLabel lblKelas = new JLabel("Kelas");
	
	RoundedJTextField txtNIM = new RoundedJTextField(15);
	GhostText nimPlaceholder = new GhostText(txtNIM, "NIM...");	
	RoundedJTextField txtNama = new RoundedJTextField(15);
	GhostText namaPlaceholder = new GhostText(txtNama, "nama...");
	RoundedJTextField txtFakultas = new RoundedJTextField(15);
	GhostText fakultasPlaceholder = new GhostText(txtFakultas, "fakultas...");
	RoundedJTextField txtJurusan = new RoundedJTextField(15);
	GhostText jurusanPlaceholder = new GhostText(txtJurusan, "jurusan...");
	RoundedJTextField txtKelas = new RoundedJTextField(15);
	GhostText kelasPlaceholder = new GhostText(txtKelas, "Kelas...");
	HashMap<String,String> dataSiswa = new HashMap<String, String>();
	
	JButton btnLogin = new JButton("Submit");
	public AddDataSiswa(){
		setTitle("Add data siswa");
		setLocation(300, 150);
		setSize(680, 400);
		setBackground(Color.gray);
	}
	
	private void putHashMapDataSiswa() {
		dataSiswa.put("nim", txtNIM.getText());
		dataSiswa.put("nama", txtNama.getText());
		dataSiswa.put("fakultas", txtFakultas.getText());
		dataSiswa.put("jurusan", txtJurusan.getText());
		dataSiswa.put("kelas", txtKelas.getText());
	}
	public void Action() {
		btnLogin.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				putHashMapDataSiswa();
				boolean executeAdd = actions.addSiswa(dataSiswa);
				if(executeAdd) {
					dispose();
					adminPage.refreshData();
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
		
		lblNama.setBounds(15, 80, 100, 20);
		txtNama.setBounds(15, 105, 205, 30);
		txtNama.setFont(style.txtFieldStyle);
		
		lblFakultas.setBounds(15, 130, 100, 20);
		txtFakultas.setBounds(15, 155, 205, 30);
		txtFakultas.setFont(style.txtFieldStyle);
		
		lblJurusan.setBounds(15, 180, 100, 20);
		txtJurusan.setBounds(15, 205, 205, 30);
		txtJurusan.setFont(style.txtFieldStyle);
		
		lblKelas.setBounds(15, 230, 100, 20);
		txtKelas.setBounds(15, 250, 205, 30);
		txtKelas.setFont(style.txtFieldStyle);
		
		btnLogin.setBounds(15, 305, 100, 40);
		btnLogin.setBackground(Color.green);
		btnLogin.setForeground(Color.black);
		
		add(lblJudul);
		add(lblNIM);
		add(lblNama);
		add(lblFakultas);
		add(lblJurusan);
		add(lblKelas);
		add(txtNIM);
		add(txtNama);
		add(txtFakultas);
		add(txtJurusan);
		add(txtKelas);
		add(btnLogin);
		setLayout(null);
		setVisible(true);
	}
	
	
}
