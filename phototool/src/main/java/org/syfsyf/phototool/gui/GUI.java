package org.syfsyf.phototool.gui;

import java.awt.EventQueue;

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
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JProgressBar;
import javax.swing.JButton;

public class GUI {

	private JFrame frmPhotoTool;
	private JLabel dirLabel;
	private JLabel outDirLabel;
	private JLabel numOfFileLabel;
	private JProgressBar progressBar;
	private JButton runButton;
	private JTextField resizeWidthEdit;
	private JLabel progresLabel;
	private JLabel errorLabel;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPhotoTool = new JFrame();
		frmPhotoTool.setTitle("Photo Tool");
		frmPhotoTool.setBounds(100, 100, 733, 296);
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
		frmPhotoTool.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Katalog");
		frmPhotoTool.getContentPane().add(lblNewLabel, "2, 2");
		
		dirLabel = new JLabel("New labelNew labelNew labelNew labelNew labelNew labelNew label");
		frmPhotoTool.getContentPane().add(dirLabel, "4, 2, fill, default");
		
		JLabel lblNewLabel_1 = new JLabel("Out");
		frmPhotoTool.getContentPane().add(lblNewLabel_1, "2, 4");
		
		outDirLabel = new JLabel("New label");
		frmPhotoTool.getContentPane().add(outDirLabel, "4, 4, fill, default");
		
		JLabel lblPlik = new JLabel("Plików");
		frmPhotoTool.getContentPane().add(lblPlik, "2, 6");
		
		numOfFileLabel = new JLabel("New label");
		frmPhotoTool.getContentPane().add(numOfFileLabel, "4, 6");
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Resize", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmPhotoTool.getContentPane().add(panel, "4, 8, left, fill");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		resizeWidthEdit = new JTextField();
		panel.add(resizeWidthEdit);
		resizeWidthEdit.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("px");
		panel.add(lblNewLabel_3);
		
		progressBar = new JProgressBar();
		frmPhotoTool.getContentPane().add(progressBar, "4, 10");
		
		progresLabel = new JLabel("New label");
		frmPhotoTool.getContentPane().add(progresLabel, "4, 12, center, default");
		
		errorLabel = new JLabel("New label");
		frmPhotoTool.getContentPane().add(errorLabel, "4, 14, center, default");
		
		runButton = new JButton("Go!");
		frmPhotoTool.getContentPane().add(runButton, "4, 16, right, default");
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
	public JTextField getResizeWidthEdit() {
		return resizeWidthEdit;
	}
	public JLabel getProgresLabel() {
		return progresLabel;
	}
	public JLabel getErrorLabel() {
		return errorLabel;
	}

}
