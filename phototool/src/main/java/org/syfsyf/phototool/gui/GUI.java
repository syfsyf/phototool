package org.syfsyf.phototool.gui;

import java.awt.EventQueue;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import java.util.Formatter;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JFormattedTextField;

public class GUI {

	private JFrame frmPhotoTool;
	private JLabel dirLabel;
	private JLabel outDirLabel;
	private JLabel numOfFileLabel;
	private JProgressBar progressBar;
	private JButton runButton;
	private JLabel progresLabel;
	private JLabel errorLabel;
	private JPanel optionsPanel;
	private JPanel panel_1;
	private JCheckBox autoLevelCheckBox;
	private JPanel panel_2;
	private JCheckBox borderCheckBox;
	private JFormattedTextField borderSize;
	private JLabel label_1;
	private JPanel panel_3;
	private JCheckBox resizeCheckBox;
	private JLabel label_2;
	private JFormattedTextField resizeWidth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmPhotoTool.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		NumberFormat format = NumberFormat.getIntegerInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(1);
		formatter.setMaximum(10000);
		formatter.setCommitsOnValidEdit(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPhotoTool = new JFrame();
		frmPhotoTool.setTitle("Photo Tool");
		frmPhotoTool.setBounds(100, 100, 749, 270);
		frmPhotoTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmPhotoTool.setJMenuBar(menuBar);

		JMenu mnOProgramie = new JMenu("Pomoc");
		menuBar.add(mnOProgramie);

		JMenuItem mntmOProgramie = new JMenuItem("O programie");
		mntmOProgramie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showInfoDialog();
			}
		});
		mnOProgramie.add(mntmOProgramie);
		frmPhotoTool.getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel = new JLabel("Katalog");
		frmPhotoTool.getContentPane().add(lblNewLabel, "2, 2");

		dirLabel = new JLabel("dirLabel");
		frmPhotoTool.getContentPane().add(dirLabel, "4, 2, fill, default");

		JLabel lblPlik = new JLabel("Plików");
		frmPhotoTool.getContentPane().add(lblPlik, "2, 4");

		numOfFileLabel = new JLabel("numOfFileLabel");
		frmPhotoTool.getContentPane().add(numOfFileLabel, "4, 4");

		optionsPanel = new JPanel();
		frmPhotoTool.getContentPane().add(optionsPanel, "4, 6, left, top");
		optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		optionsPanel.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		resizeCheckBox = new JCheckBox("resizować?");
		resizeCheckBox.setSelected(true);
		panel_3.add(resizeCheckBox);

		resizeWidth = new JFormattedTextField(createIntegerFormatter(1, 10000));
		resizeWidth.setColumns(5);
		panel_3.add(resizeWidth);

		label_2 = new JLabel("px");
		panel_3.add(label_2);

		panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		optionsPanel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		borderCheckBox = new JCheckBox("ramka?");
		borderCheckBox.setSelected(true);
		panel_2.add(borderCheckBox);

		borderSize = new JFormattedTextField();
		borderSize.setColumns(3);
		panel_2.add(borderSize);

		label_1 = new JLabel("px");
		panel_2.add(label_1);

		panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		optionsPanel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		autoLevelCheckBox = new JCheckBox("autoLevel?");
		autoLevelCheckBox.setSelected(true);
		panel_1.add(autoLevelCheckBox);

		JLabel lblNewLabel_1 = new JLabel("Out");
		frmPhotoTool.getContentPane().add(lblNewLabel_1, "2, 8");

		outDirLabel = new JLabel("outDirLabel");
		frmPhotoTool.getContentPane().add(outDirLabel, "4, 8, fill, default");

		progressBar = new JProgressBar();
		frmPhotoTool.getContentPane().add(progressBar, "4, 10");

		progresLabel = new JLabel("progresLabel");
		frmPhotoTool.getContentPane().add(progresLabel, "4, 12, center, default");

		errorLabel = new JLabel("errorLabel");
		frmPhotoTool.getContentPane().add(errorLabel, "4, 14, center, default");

		runButton = new JButton("Go!");
		frmPhotoTool.getContentPane().add(runButton, "4, 16, right, default");

	}

	protected AbstractFormatter createIntegerFormatter(int min, int max) {
		NumberFormat format = NumberFormat.getIntegerInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(min);
		formatter.setMaximum(max);
		formatter.setCommitsOnValidEdit(true);
		return formatter;
	}

	protected void showInfoDialog() {

		InfoDialog.main(null);

	}

	public JFrame getFrmPhotoTool() {
		return frmPhotoTool;
	}

	public JLabel getDirLabel() {
		return dirLabel;
	}

	public JLabel getOutDirLabel() {
		return outDirLabel;
	}

	public JLabel getNumOfFileLabel() {
		return numOfFileLabel;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public JButton getRunButton() {
		return runButton;
	}

	public JLabel getProgresLabel() {
		return progresLabel;
	}

	public JLabel getErrorLabel() {
		return errorLabel;
	}

	public JFormattedTextField getResizeWidth() {
		return resizeWidth;
	}

	public JFormattedTextField getBorderSize() {
		return borderSize;
	}

	public JCheckBox getAutoLevelCheckBox() {
		return autoLevelCheckBox;
	}

	public JCheckBox getResizeCheckBox() {
		return resizeCheckBox;
	}
}
