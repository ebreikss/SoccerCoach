package theDialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import fieldFiles.Field;
import fieldFiles.Formation;

public class StartFormationDialog extends JDialog {
	
	private HumanPanel hPanel;
	private CompPanel cPanel;
	private Field field;
	
	public StartFormationDialog(Map<String,Formation> startFormations, Field field) {
		this.field = field;
		setSize(new Dimension(300,200));
		setTitle("Select Starting Formations");
		
		hPanel = new HumanPanel(startFormations);
		cPanel = new CompPanel(startFormations);
		
		add(hPanel, BorderLayout.WEST);
		add(cPanel, BorderLayout.EAST);
		
		JButton submitButton = new JButton("Submit");
		ActionListener listener = new ButtonListenerS();
		submitButton.addActionListener(listener);
		
		add(submitButton, BorderLayout.SOUTH);
	}
	
	class ButtonListenerS implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		    field.switchFormation(hPanel.getName(), cPanel.getName());
		}
	}
	
}
