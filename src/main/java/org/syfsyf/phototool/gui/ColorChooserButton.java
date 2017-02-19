package org.syfsyf.phototool.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

/**
 * Komponent do wyboru koloru.
 * 
 * @author user
 * 
 */
public class ColorChooserButton extends Button {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColorChooserButton() throws HeadlessException {
		super("...");
		init();
	}

	protected void init() {

		
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(ColorChooserButton.this, "Wybierz kolor", ColorChooserButton.this.getBackground());
				if(color!=null){
					setColor(color);
				}
			}
		});

	}

	public static Color getContrastColor(Color color) {
		if(color==null){
			return Color.WHITE;
		}
		double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
		return y >= 128 ? Color.black : Color.white;
	}

	public ColorChooserButton(String label) throws HeadlessException {
		super(label);
		init();
	}

	public Color getColor() {
		return getBackground();
	}

	public void setColor(Color color) {
		Color old = getBackground();
		setBackground(color);
		setForeground(getContrastColor(color));
		firePropertyChange("color", old, color);
	}

}
