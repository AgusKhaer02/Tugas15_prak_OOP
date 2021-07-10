package com.agus.guioopp15.pages;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import com.agus.guioopp15.actions.LoginActions;
import com.agus.guioopp15.config.GhostText;
import com.agus.guioopp15.config.RoundedJTextField;
import com.agus.guioopp15.config.StyleConstants;

public class LoginPage extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	LoginActions loginActions = new LoginActions();
	StyleConstants style = new StyleConstants();
	
	
	JLabel lblJudul = new JLabel("Login as Admin");
	JLabel lblUsername = new JLabel("Username");
	JLabel lblPassword = new JLabel("Password");
	
	RoundedJTextField txtUsername = new RoundedJTextField(15);
	GhostText usernamePlaceholder = new GhostText(txtUsername, "Username...");
	JPasswordField txtPassword = new JPasswordField();
	GhostText passwordPlaceholder = new GhostText(txtPassword, "Password...");
	
	JButton btnLogin = new JButton("Submit");
	LoginPage(){
		setTitle("Login as Admin");
		setLocation(300, 150);
		setSize(680, 300);
		setBackground(Color.gray);
	}
	void Action() {
		btnLogin.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				boolean loginAct = loginActions.loginAdmin(txtUsername.getText(),txtPassword.getText());
				if(loginAct) {
					dispose();
				}
			}
			
		});
		
	}
	void Layout() {
		lblJudul.setBounds(15, 0, 200, 20);
		lblJudul.setFont(style.subTitle);
		
		lblUsername.setBounds(15, 20 + 10, 100, 20);
		txtUsername.setBounds(15, 45 + 10, 200, 30);
		txtUsername.setFont(style.subTitle);
		
		lblPassword.setBounds(15, 70 + 10, 100, 20);
		txtPassword.setBounds(15, 95 + 10, 150, 30);
		txtPassword.setFont(style.subTitle);
		
		btnLogin.setBounds(15, 130 + 10, 100, 40);
		btnLogin.setBackground(Color.green);
		btnLogin.setForeground(Color.black);
		
		add(lblJudul);
		add(lblUsername);
		add(lblPassword);
		add(txtUsername);
		add(txtPassword);
		add(btnLogin);
		setLayout(null);
		setVisible(true);
	}

}
