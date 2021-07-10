package com.agus.guioopp15.pages;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("deprecation")
public class AboutPage extends JFrame {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nmFILE= "res\\images\\my_photo.jpg";
	Image gb = Toolkit.getDefaultToolkit().getImage(nmFILE);
	
	public void paint(Graphics graphics) {
		graphics.setFont(new Font("Segoe UI", Font.BOLD, 16));
		graphics.drawImage(gb, 60, 50,250,300, this);
		graphics.setColor(Color.decode("#1A4876"));
		graphics.fillRect(310, 50, 270, 180);
		graphics.setColor(Color.white);
		graphics.drawString("About Me", 330, 100);
		graphics.drawString("NIM : 201110199", 330, 120);
		graphics.drawString("NAMA : Agus Kurniadin Khaer", 330, 140);
	}
	
	public boolean handleEvent(Event evt) {
		if(evt.id == Event.WINDOW_DESTROY) {
			dispose();
		}
		return false;
		
	}
	void Layout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("About page");
		setLocation(50, 50);
		setSize(800,500);
		setLayout(null);
		setVisible(true);
	}
	
}