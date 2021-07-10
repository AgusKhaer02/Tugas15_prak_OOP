package com.agus.guioopp15.config;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;
import javax.swing.JButton;

public class RoundedButton extends JButton {
	
	Color colorReleased;
	Color colorPressed;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RoundedButton(String label, Color colorReleased, Color colorPressed) {
		super(label);
		setRoundedButton(colorReleased,colorPressed);
	}
	public RoundedButton(Icon label, Color colorReleased, Color colorPressed) {
		super(label);
		setRoundedButton(colorReleased,colorPressed);
	}
	
	private void setRoundedButton(Color colorReleased, Color colorPressed) {
		
		this.colorReleased = colorReleased;
		this.colorPressed = colorPressed;
		
		setBackground(this.colorReleased);
		setFocusable(false);

		/*
		 * These statements enlarge the button so that it becomes a circle rather than
		 * an oval.
		 */
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);

		/*
		 * This call causes the JButton not to paint the background. This allows us to
		 * paint a round background.
		 */
		setContentAreaFilled(false);
	}

	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			g.setColor(this.colorPressed);
		} else {
			g.setColor(getBackground());
		}
		g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

		super.paintComponent(g);
	}

	protected void paintBorder(Graphics g) {
		g.setColor(this.colorPressed);
		g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
	}

	// Hit detection.
	Shape shape;

	public boolean contains(int x, int y) {
		// If the button has changed size, make a new shape object.
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}
}
