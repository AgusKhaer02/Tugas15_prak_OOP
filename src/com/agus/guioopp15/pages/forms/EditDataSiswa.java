package com.agus.guioopp15.pages.forms;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.agus.guioopp15.actions.LoginActions;
import com.agus.guioopp15.actions.SiswaActions;
import com.agus.guioopp15.config.GhostText;
import com.agus.guioopp15.config.GuiWidgets;
import com.agus.guioopp15.config.RoundedJTextField;
import com.agus.guioopp15.config.StyleConstants;
import com.agus.guioopp15.pages.AdminPage;

public class EditDataSiswa extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LoginActions loginActions = new LoginActions();
	StyleConstants style = new StyleConstants();
	SiswaActions actions = new SiswaActions();
	GuiWidgets widgets = new GuiWidgets();
	AdminPage adminPage = new AdminPage();
	
	JLabel lblJudul = new JLabel("Add Data Siswa");
	JLabel lblNIM = new JLabel("NIM");
	JLabel lblNama = new JLabel("Nama");
	JLabel lblFakultas = new JLabel("Fakultas");
	JLabel lblJurusan = new JLabel("Jurusan");
	JLabel lblKelas = new JLabel("Kelas");

	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox txtNIM = new JComboBox(actions.getOnlyNIM().toArray());
	RoundedJTextField txtNama = new RoundedJTextField(15);
	GhostText namaPlaceholder = new GhostText(txtNama, "nama...");
	RoundedJTextField txtFakultas = new RoundedJTextField(15);
	GhostText fakultasPlaceholder = new GhostText(txtFakultas, "fakultas...");
	RoundedJTextField txtJurusan = new RoundedJTextField(15);
	GhostText jurusanPlaceholder = new GhostText(txtJurusan, "jurusan...");
	RoundedJTextField txtKelas = new RoundedJTextField(15);
	GhostText kelasPlaceholder = new GhostText(txtKelas, "Kelas...");
	HashMap<String,String> dataSiswa = new HashMap<String, String>();
	
	JButton btnSubmit = new JButton("Submit");
	public EditDataSiswa(){
		setTitle("Edit data siswa");
		setLocation(300, 150);
		setSize(680, 400);
		setBackground(Color.gray);
	}
	
	private void putHashMapDataSiswa() {
		dataSiswa.put("nim", String.valueOf(txtNIM.getSelectedItem()));
		dataSiswa.put("nama", txtNama.getText());
		dataSiswa.put("fakultas", txtFakultas.getText());
		dataSiswa.put("jurusan", txtJurusan.getText());
		dataSiswa.put("kelas", txtKelas.getText());
	}
	
	private boolean checkAllRequiredInputs() {
		if(txtNIM != null && txtNama != null && txtFakultas != null && txtJurusan != null && txtKelas != null) {
//			do nothing
			return true;
		}else {
			widgets.showMessage("Mohon masukan data yang lengkap!");
			return false;
		}
	}
	
	public void Action() {
		btnSubmit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				putHashMapDataSiswa();

				int confirm = widgets.showMessageConfirm("Apakah anda ingin mengubah data ini?", "PERINGATAN edit data", JOptionPane.YES_NO_CANCEL_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					
					if(checkAllRequiredInputs()) {
						boolean executeEdit = actions.editSiswa(dataSiswa);
						if(executeEdit) {
							dispose();
							adminPage.refreshData();
						}
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
		
		btnSubmit.setBounds(15, 305, 100, 40);
		btnSubmit.setBackground(Color.green);
		btnSubmit.setForeground(Color.black);
		
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
		add(btnSubmit);
		setLayout(null);
		setVisible(true);
	}
}
