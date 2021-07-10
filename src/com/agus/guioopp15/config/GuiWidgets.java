package com.agus.guioopp15.config;

import javax.swing.JOptionPane;

public class GuiWidgets {

	
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public int showMessageConfirm(String message, String title, int options) {
//	    YES_OPTION = 0;
//	    NO_OPTION = 1;
//	    CANCEL_OPTION = 2;
//	    OK_OPTION = 0;
		return JOptionPane.showConfirmDialog(null, message, title, options);
	}
}
