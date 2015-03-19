package org.syfsyf.phototool.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;

public class Binding extends JDialog {

	private BindingGroup m_bindingGroup;
	private JPanel m_contentPane;
	private org.syfsyf.phototool.gui.ViewModel viewModel = new org.syfsyf.phototool.gui.ViewModel();
	private JCheckBox autolevelJCheckBox;
	private JCheckBox borderJCheckBox;
	private JSlider borderSizeJSlider;
	private JCheckBox resizeJCheckBox;
	private JSlider resizeWidthJSlider;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Binding dialog = new Binding();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Binding() {
		setBounds(100, 100, 450, 300);
		m_contentPane = new JPanel();
		setContentPane(m_contentPane);
		//
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0E-4 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };
		m_contentPane.setLayout(gridBagLayout);

		JLabel autolevelLabel = new JLabel("Autolevel:");
		GridBagConstraints labelGbc_0 = new GridBagConstraints();
		labelGbc_0.insets = new Insets(5, 5, 5, 5);
		labelGbc_0.gridx = 0;
		labelGbc_0.gridy = 0;
		m_contentPane.add(autolevelLabel, labelGbc_0);

		autolevelJCheckBox = new JCheckBox();
		GridBagConstraints componentGbc_0 = new GridBagConstraints();
		componentGbc_0.insets = new Insets(5, 0, 5, 5);
		componentGbc_0.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_0.gridx = 1;
		componentGbc_0.gridy = 0;
		m_contentPane.add(autolevelJCheckBox, componentGbc_0);

		JLabel borderLabel = new JLabel("Border:");
		GridBagConstraints labelGbc_1 = new GridBagConstraints();
		labelGbc_1.insets = new Insets(5, 5, 5, 5);
		labelGbc_1.gridx = 0;
		labelGbc_1.gridy = 1;
		m_contentPane.add(borderLabel, labelGbc_1);

		borderJCheckBox = new JCheckBox();
		GridBagConstraints componentGbc_1 = new GridBagConstraints();
		componentGbc_1.insets = new Insets(5, 0, 5, 5);
		componentGbc_1.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_1.gridx = 1;
		componentGbc_1.gridy = 1;
		m_contentPane.add(borderJCheckBox, componentGbc_1);

		JLabel borderSizeLabel = new JLabel("BorderSize:");
		GridBagConstraints labelGbc_2 = new GridBagConstraints();
		labelGbc_2.insets = new Insets(5, 5, 5, 5);
		labelGbc_2.gridx = 0;
		labelGbc_2.gridy = 2;
		m_contentPane.add(borderSizeLabel, labelGbc_2);

		borderSizeJSlider = new JSlider();
		GridBagConstraints componentGbc_2 = new GridBagConstraints();
		componentGbc_2.insets = new Insets(5, 0, 5, 5);
		componentGbc_2.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_2.gridx = 1;
		componentGbc_2.gridy = 2;
		m_contentPane.add(borderSizeJSlider, componentGbc_2);

		JLabel resizeLabel = new JLabel("Resize:");
		GridBagConstraints labelGbc_3 = new GridBagConstraints();
		labelGbc_3.insets = new Insets(5, 5, 5, 5);
		labelGbc_3.gridx = 0;
		labelGbc_3.gridy = 3;
		m_contentPane.add(resizeLabel, labelGbc_3);

		resizeJCheckBox = new JCheckBox();
		GridBagConstraints componentGbc_3 = new GridBagConstraints();
		componentGbc_3.insets = new Insets(5, 0, 5, 5);
		componentGbc_3.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_3.gridx = 1;
		componentGbc_3.gridy = 3;
		m_contentPane.add(resizeJCheckBox, componentGbc_3);

		JLabel resizeWidthLabel = new JLabel("ResizeWidth:");
		GridBagConstraints labelGbc_4 = new GridBagConstraints();
		labelGbc_4.insets = new Insets(5, 5, 5, 5);
		labelGbc_4.gridx = 0;
		labelGbc_4.gridy = 4;
		m_contentPane.add(resizeWidthLabel, labelGbc_4);

		resizeWidthJSlider = new JSlider();
		GridBagConstraints componentGbc_4 = new GridBagConstraints();
		componentGbc_4.insets = new Insets(5, 0, 5, 5);
		componentGbc_4.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_4.gridx = 1;
		componentGbc_4.gridy = 4;
		m_contentPane.add(resizeWidthJSlider, componentGbc_4);

		if (viewModel != null) {
			m_bindingGroup = initDataBindings();
		}
	}

	protected BindingGroup initDataBindings() {
		BeanProperty<org.syfsyf.phototool.gui.ViewModel, java.lang.Boolean> autolevelProperty = BeanProperty.create("autolevel");
		BeanProperty<javax.swing.JCheckBox, java.lang.Boolean> selectedProperty = BeanProperty.create("selected");
		AutoBinding<org.syfsyf.phototool.gui.ViewModel, java.lang.Boolean, javax.swing.JCheckBox, java.lang.Boolean> autoBinding = Bindings.createAutoBinding(
				AutoBinding.UpdateStrategy.READ_WRITE, viewModel, autolevelProperty, autolevelJCheckBox, selectedProperty);
		autoBinding.bind();
		//
		BeanProperty<org.syfsyf.phototool.gui.ViewModel, java.lang.Boolean> borderProperty = BeanProperty.create("border");
		BeanProperty<javax.swing.JCheckBox, java.lang.Boolean> selectedProperty_1 = BeanProperty.create("selected");
		AutoBinding<org.syfsyf.phototool.gui.ViewModel, java.lang.Boolean, javax.swing.JCheckBox, java.lang.Boolean> autoBinding_1 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, viewModel, borderProperty, borderJCheckBox, selectedProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<org.syfsyf.phototool.gui.ViewModel, java.lang.Integer> borderSizeProperty = BeanProperty.create("borderSize");
		BeanProperty<javax.swing.JSlider, java.lang.Integer> valueProperty = BeanProperty.create("value");
		AutoBinding<org.syfsyf.phototool.gui.ViewModel, java.lang.Integer, javax.swing.JSlider, java.lang.Integer> autoBinding_2 = Bindings.createAutoBinding(
				AutoBinding.UpdateStrategy.READ_WRITE, viewModel, borderSizeProperty, borderSizeJSlider, valueProperty);
		autoBinding_2.bind();
		//
		BeanProperty<org.syfsyf.phototool.gui.ViewModel, java.lang.Boolean> resizeProperty = BeanProperty.create("resize");
		BeanProperty<javax.swing.JCheckBox, java.lang.Boolean> selectedProperty_2 = BeanProperty.create("selected");
		AutoBinding<org.syfsyf.phototool.gui.ViewModel, java.lang.Boolean, javax.swing.JCheckBox, java.lang.Boolean> autoBinding_3 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, viewModel, resizeProperty, resizeJCheckBox, selectedProperty_2);
		autoBinding_3.bind();
		//
		BeanProperty<org.syfsyf.phototool.gui.ViewModel, java.lang.Integer> resizeWidthProperty = BeanProperty.create("resizeWidth");
		BeanProperty<javax.swing.JSlider, java.lang.Integer> valueProperty_1 = BeanProperty.create("value");
		AutoBinding<org.syfsyf.phototool.gui.ViewModel, java.lang.Integer, javax.swing.JSlider, java.lang.Integer> autoBinding_4 = Bindings.createAutoBinding(
				AutoBinding.UpdateStrategy.READ_WRITE, viewModel, resizeWidthProperty, resizeWidthJSlider, valueProperty_1);
		autoBinding_4.bind();
		//
		BindingGroup bindingGroup = new BindingGroup();
		bindingGroup.addBinding(autoBinding);
		bindingGroup.addBinding(autoBinding_1);
		bindingGroup.addBinding(autoBinding_2);
		bindingGroup.addBinding(autoBinding_3);
		bindingGroup.addBinding(autoBinding_4);
		//
		return bindingGroup;
	}

	public org.syfsyf.phototool.gui.ViewModel getViewModel() {
		return viewModel;
	}

	public void setViewModel(org.syfsyf.phototool.gui.ViewModel newViewModel) {
		setViewModel(newViewModel, true);
	}

	public void setViewModel(org.syfsyf.phototool.gui.ViewModel newViewModel, boolean update) {
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

}
