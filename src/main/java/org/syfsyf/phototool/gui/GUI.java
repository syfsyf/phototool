package org.syfsyf.phototool.gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.text.NumberFormatter;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import java.awt.Color;
import javax.swing.JSpinner;
import java.awt.Dimension;
import javax.swing.JTextField;

public class GUI {

	private BindingGroup m_bindingGroup;
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
	private JSpinner borderSize;
	private JLabel label_1;
	private JPanel panel_3;
	private JCheckBox resizeCheckBox;
	private JLabel label_2;
	private JFormattedTextField resizeWidth;
	private ColorChooserButton borderColor;
	/**
	 * @wbp.nonvisual location=67,239
	 */
	private ViewModel viewModel;
	private JPanel panel_4;
	private JCheckBox chckbxDodajPodpis;
	private JLabel lblNewLabel_2;
	private JTextField sigFile;
	private JLabel lblNewLabel_3;
	private JTextField sigGravity;
	private JLabel lblNewLabel_4;
	private JTextField sigGeometry;
	private JLabel lblNewLabel_5;
	private JTextField sigResize;
	private JPanel additionalParametersPanel;
	private JLabel lblDodatkoweParametry;
	private JTextField customParams;
	private JPanel geoTagsPanel;
	private JCheckBox addGeoTagCheckBox;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField textField_2;
	private JTextField textField_3;

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
		frmPhotoTool.setBounds(100, 100, 879, 663);
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
				new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));

		JLabel lblNewLabel = new JLabel("Katalog");
		frmPhotoTool.getContentPane().add(lblNewLabel, "2, 2");

		dirLabel = new JLabel("dirLabel");
		frmPhotoTool.getContentPane().add(dirLabel, "4, 2, fill, default");

		JLabel lblPlik = new JLabel("Plików");
		frmPhotoTool.getContentPane().add(lblPlik, "2, 4");

		numOfFileLabel = new JLabel("numOfFileLabel");
		frmPhotoTool.getContentPane().add(numOfFileLabel, "4, 4");

		optionsPanel = new JPanel();
		frmPhotoTool.getContentPane().add(optionsPanel, "4, 6, left, fill");
		FlowLayout fl_optionsPanel = new FlowLayout(FlowLayout.LEFT, 5, 5);
		optionsPanel.setLayout(fl_optionsPanel);

		panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		optionsPanel.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

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
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		borderCheckBox = new JCheckBox("ramka?");
		borderCheckBox.setSelected(true);
		panel_2.add(borderCheckBox);

		borderSize = new JSpinner();
		borderSize.setPreferredSize(new Dimension(40, 18));
		panel_2.add(borderSize);

		label_1 = new JLabel("px");
		panel_2.add(label_1);
		
		borderColor = new ColorChooserButton("New button");
		borderColor.setLabel("...");
		borderColor.setBackground(UIManager.getColor("Button.focus"));
		panel_2.add(borderColor);

		panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		optionsPanel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		autoLevelCheckBox = new JCheckBox("autoLevel?");
		autoLevelCheckBox.setSelected(true);
		panel_1.add(autoLevelCheckBox);
		
		panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(500, 160));
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		optionsPanel.add(panel_4);
		panel_4.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.PREF_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("pref:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		chckbxDodajPodpis = new JCheckBox("dodaj podpis");
		panel_4.add(chckbxDodajPodpis, "2, 2, left, top");
		
		lblNewLabel_2 = new JLabel("Plik z podpisem");
		panel_4.add(lblNewLabel_2, "2, 4, right, default");
		
		sigFile = new JTextField();
		panel_4.add(sigFile, "4, 4, fill, default");
		sigFile.setColumns(10);
		
		lblNewLabel_3 = new JLabel("gravity");
		panel_4.add(lblNewLabel_3, "2, 6, right, default");
		
		sigGravity = new JTextField();
		panel_4.add(sigGravity, "4, 6, fill, default");
		sigGravity.setColumns(10);
		
		lblNewLabel_4 = new JLabel("geometry");
		panel_4.add(lblNewLabel_4, "2, 8, right, default");
		
		sigGeometry = new JTextField();
		panel_4.add(sigGeometry, "4, 8, fill, default");
		sigGeometry.setColumns(10);
		
		lblNewLabel_5 = new JLabel("resize");
		panel_4.add(lblNewLabel_5, "2, 10, right, default");
		
		sigResize = new JTextField();
		panel_4.add(sigResize, "4, 10, fill, default");
		sigResize.setColumns(10);
		
		geoTagsPanel = new JPanel();
		geoTagsPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		geoTagsPanel.setPreferredSize(new Dimension(500, 120));
		optionsPanel.add(geoTagsPanel);
		geoTagsPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.PREF_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default"),}));
		
		addGeoTagCheckBox = new JCheckBox("dodać geo tag?");
		geoTagsPanel.add(addGeoTagCheckBox, "2, 2");
		
		lblNewLabel_6 = new JLabel("New label");
		geoTagsPanel.add(lblNewLabel_6, "2, 4, right, default");
		
		textField_2 = new JTextField();
		geoTagsPanel.add(textField_2, "4, 4, fill, default");
		textField_2.setColumns(15);
		
		lblNewLabel_7 = new JLabel("New label");
		geoTagsPanel.add(lblNewLabel_7, "2, 6, right, default");
		
		textField_3 = new JTextField();
		geoTagsPanel.add(textField_3, "4, 6, fill, default");
		textField_3.setColumns(10);
		
		additionalParametersPanel = new JPanel();
		additionalParametersPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		additionalParametersPanel.setPreferredSize(new Dimension(500, 50));
		optionsPanel.add(additionalParametersPanel);
		additionalParametersPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		lblDodatkoweParametry = new JLabel("Dodatkowe parametry");
		additionalParametersPanel.add(lblDodatkoweParametry, "2, 2, right, default");
		
		customParams = new JTextField();
		additionalParametersPanel.add(customParams, "4, 2, fill, default");
		customParams.setColumns(10);

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
		
		if (viewModel != null) {
			m_bindingGroup = initDataBindings();
		}

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

	

	public JCheckBox getAutoLevelCheckBox() {
		return autoLevelCheckBox;
	}

	public JCheckBox getResizeCheckBox() {
		return resizeCheckBox;
	}
	public ColorChooserButton getBorderColor() {
		return borderColor;
	}
	
	public ViewModel getViewModel() {
		return viewModel;
	}

	public void setViewModel(ViewModel newViewModel) {
		setViewModel(newViewModel, true);
	}

	public void setViewModel(ViewModel newViewModel, boolean update) {
		viewModel = newViewModel;
		if (update) {
			if (m_bindingGroup != null) {
				m_bindingGroup.unbind();
				m_bindingGroup = null;
			}
			if (viewModel != null) {
				m_bindingGroup = initDataBindings();
			}
		}
	}
	public JTextField getSigFile() {
		return sigFile;
	}
	public JTextField getSigGravity() {
		return sigGravity;
	}
	public JTextField getSigGeometry() {
		return sigGeometry;
	}
	public JTextField getSigResize() {
		return sigResize;
	}
	public JCheckBox getChckbxDodajPodpis() {
		return chckbxDodajPodpis;
	}
	protected BindingGroup initDataBindings() {
		BeanProperty<ViewModel, Boolean> viewModelBeanProperty = BeanProperty.create("autolevel");
		BeanProperty<JCheckBox, Boolean> jCheckBoxBeanProperty = BeanProperty.create("selected");
		AutoBinding<ViewModel, Boolean, JCheckBox, Boolean> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty, autoLevelCheckBox, jCheckBoxBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<ViewModel, Boolean> viewModelBeanProperty_1 = BeanProperty.create("border");
		AutoBinding<ViewModel, Boolean, JCheckBox, Boolean> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_1, borderCheckBox, jCheckBoxBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<ViewModel, Boolean> viewModelBeanProperty_4 = BeanProperty.create("resize");
		AutoBinding<ViewModel, Boolean, JCheckBox, Boolean> autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_4, resizeCheckBox, jCheckBoxBeanProperty);
		autoBinding_4.bind();
		//
		BeanProperty<ViewModel, Integer> viewModelBeanProperty_5 = BeanProperty.create("resizeWidth");
		BeanProperty<JFormattedTextField, Object> jFormattedTextFieldBeanProperty = BeanProperty.create("value");
		AutoBinding<ViewModel, Integer, JFormattedTextField, Object> autoBinding_5 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_5, resizeWidth, jFormattedTextFieldBeanProperty);
		autoBinding_5.bind();
		//
		BeanProperty<ViewModel, String> viewModelBeanProperty_6 = BeanProperty.create("outDirLabel");
		BeanProperty<JLabel, String> jLabelBeanProperty = BeanProperty.create("text");
		AutoBinding<ViewModel, String, JLabel, String> autoBinding_6 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_6, outDirLabel, jLabelBeanProperty);
		autoBinding_6.bind();
		//
		BeanProperty<ViewModel, Color> viewModelBeanProperty_2 = BeanProperty.create("borderColor");
		BeanProperty<ColorChooserButton, Color> colorChooserButtonBeanProperty_1 = BeanProperty.create("color");
		AutoBinding<ViewModel, Color, ColorChooserButton, Color> autoBinding_7 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_2, borderColor, colorChooserButtonBeanProperty_1);
		autoBinding_7.bind();
		//
		BeanProperty<JFormattedTextField, Boolean> jFormattedTextFieldBeanProperty_1 = BeanProperty.create("enabled");
		AutoBinding<ViewModel, Boolean, JFormattedTextField, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_4, resizeWidth, jFormattedTextFieldBeanProperty_1);
		autoBinding_2.bind();
		//
		BeanProperty<ColorChooserButton, Boolean> colorChooserButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<ViewModel, Boolean, ColorChooserButton, Boolean> autoBinding_9 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_1, borderColor, colorChooserButtonBeanProperty);
		autoBinding_9.bind();
		//
		BeanProperty<ViewModel, Integer> viewModelBeanProperty_3 = BeanProperty.create("borderSize");
		BeanProperty<JSpinner, Object> jSpinnerBeanProperty = BeanProperty.create("value");
		AutoBinding<ViewModel, Integer, JSpinner, Object> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_3, borderSize, jSpinnerBeanProperty);
		autoBinding_3.bind();
		//
		BeanProperty<ViewModel, String> viewModelBeanProperty_7 = BeanProperty.create("errorLabel");
		AutoBinding<ViewModel, String, JLabel, String> autoBinding_8 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_7, errorLabel, jLabelBeanProperty);
		autoBinding_8.bind();
		//
		BeanProperty<ViewModel, String> viewModelBeanProperty_8 = BeanProperty.create("progressLabel");
		AutoBinding<ViewModel, String, JLabel, String> autoBinding_10 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_8, progresLabel, jLabelBeanProperty);
		autoBinding_10.bind();
		//
		BeanProperty<ViewModel, String> viewModelBeanProperty_9 = BeanProperty.create("dirLabel");
		AutoBinding<ViewModel, String, JLabel, String> autoBinding_11 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_9, dirLabel, jLabelBeanProperty);
		autoBinding_11.bind();
		//
		BeanProperty<ViewModel, String> viewModelBeanProperty_10 = BeanProperty.create("numOfFiles");
		AutoBinding<ViewModel, String, JLabel, String> autoBinding_12 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_10, numOfFileLabel, jLabelBeanProperty);
		autoBinding_12.bind();
		//
		BeanProperty<ViewModel, Integer> viewModelBeanProperty_11 = BeanProperty.create("progressValue");
		BeanProperty<JProgressBar, Integer> jProgressBarBeanProperty = BeanProperty.create("value");
		AutoBinding<ViewModel, Integer, JProgressBar, Integer> autoBinding_13 = Bindings.createAutoBinding(UpdateStrategy.READ, viewModel, viewModelBeanProperty_11, progressBar, jProgressBarBeanProperty);
		autoBinding_13.bind();
		//
		BeanProperty<JProgressBar, Integer> jProgressBarBeanProperty_1 = BeanProperty.create("maximum");
		AutoBinding<ViewModel, String, JProgressBar, Integer> autoBinding_14 = Bindings.createAutoBinding(UpdateStrategy.READ_ONCE, viewModel, viewModelBeanProperty_10, progressBar, jProgressBarBeanProperty_1);
		autoBinding_14.bind();
		//
		BeanProperty<ViewModel, Boolean> viewModelBeanProperty_12 = BeanProperty.create("addSignature");
		AutoBinding<ViewModel, Boolean, JCheckBox, Boolean> autoBinding_15 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_12, chckbxDodajPodpis, jCheckBoxBeanProperty);
		autoBinding_15.bind();
		//
		BeanProperty<ViewModel, String> viewModelBeanProperty_13 = BeanProperty.create("sigFile");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<ViewModel, String, JTextField, String> autoBinding_16 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_13, sigFile, jTextFieldBeanProperty);
		autoBinding_16.bind();
		//
		BeanProperty<ViewModel, String> viewModelBeanProperty_14 = BeanProperty.create("sigGravity");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<ViewModel, String, JTextField, String> autoBinding_17 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_14, sigGravity, jTextFieldBeanProperty_1);
		autoBinding_17.bind();
		//
		BeanProperty<ViewModel, String> viewModelBeanProperty_15 = BeanProperty.create("sigGeometry");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_2 = BeanProperty.create("text");
		AutoBinding<ViewModel, String, JTextField, String> autoBinding_18 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_15, sigGeometry, jTextFieldBeanProperty_2);
		autoBinding_18.bind();
		//
		BeanProperty<ViewModel, String> viewModelBeanProperty_16 = BeanProperty.create("sigResize");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_3 = BeanProperty.create("text");
		AutoBinding<ViewModel, String, JTextField, String> autoBinding_19 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_16, sigResize, jTextFieldBeanProperty_3);
		autoBinding_19.bind();
		//
		BeanProperty<ViewModel, String> viewModelBeanProperty_17 = BeanProperty.create("customParams");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_4 = BeanProperty.create("text");
		AutoBinding<ViewModel, String, JTextField, String> autoBinding_20 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, viewModel, viewModelBeanProperty_17, customParams, jTextFieldBeanProperty_4);
		autoBinding_20.bind();
		//
		BindingGroup bindingGroup = new BindingGroup();
		//
		bindingGroup.addBinding(autoBinding);
		bindingGroup.addBinding(autoBinding_1);
		bindingGroup.addBinding(autoBinding_4);
		bindingGroup.addBinding(autoBinding_5);
		bindingGroup.addBinding(autoBinding_6);
		bindingGroup.addBinding(autoBinding_7);
		bindingGroup.addBinding(autoBinding_2);
		bindingGroup.addBinding(autoBinding_9);
		bindingGroup.addBinding(autoBinding_3);
		bindingGroup.addBinding(autoBinding_8);
		bindingGroup.addBinding(autoBinding_10);
		bindingGroup.addBinding(autoBinding_11);
		bindingGroup.addBinding(autoBinding_12);
		bindingGroup.addBinding(autoBinding_13);
		bindingGroup.addBinding(autoBinding_14);
		bindingGroup.addBinding(autoBinding_15);
		bindingGroup.addBinding(autoBinding_16);
		bindingGroup.addBinding(autoBinding_17);
		bindingGroup.addBinding(autoBinding_18);
		bindingGroup.addBinding(autoBinding_19);
		bindingGroup.addBinding(autoBinding_20);
		return bindingGroup;
	}
}
