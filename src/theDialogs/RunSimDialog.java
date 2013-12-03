package theDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import theDialogs.StartFormationDialog.ButtonListenerS;
import fieldFiles.Field;
import fieldFiles.Formation;

public class RunSimDialog extends JDialog {
	private Field field;
	private JTextField timeBox;
	
	public RunSimDialog(Field field) {
		this.field = field;
		setSize(new Dimension(300,100));
		setTitle("Set TimeStep for Simulation");
		
		timeBox = new JTextField();
		//timeBox.setSize(30,5);
		
		add(timeBox, BorderLayout.NORTH);
		
		JButton runButton = new JButton("Run");
		ActionListener listener = new ButtonListenerR();
		runButton.addActionListener(listener);
		
		add(runButton, BorderLayout.SOUTH);
	}
	
	class ButtonListenerR implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try {
				field.setTimestep(Integer.parseInt(timeBox.getText()));
			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(null, "Please enter an integer.", "OOPS!!", 
						JOptionPane.INFORMATION_MESSAGE);
			}
			setVisible(false);
			field.runSimulation();
		}
	}

}
