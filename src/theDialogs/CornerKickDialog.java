package theDialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import theDialogs.StartFormationDialog.ButtonListenerS;
import fieldFiles.Field;
import fieldFiles.Formation;

public class CornerKickDialog extends JDialog{
	//private HumanPanel hPanel;
	//private CompPanel cPanel;
	//private Map<String, JRadioButton> humanButtons;
	
	private JPanel kickingPanel, cornerPanel;
	private ButtonGroup kGroup, cGroup;
	private Field field;
	private JRadioButton kickingButton;
	private boolean kicking;
	private JRadioButton upperButton;
	private boolean top;
	
	public CornerKickDialog(Field field) {
		this.field = field;
		setSize(new Dimension(300,200));
		setTitle("Setup Corner Kick");
		
		kicking = false;
		top = true;
		
		kickingPanel = new KickingPanel();
		cornerPanel = new CornerPanel();
		
		JButton submitButton = new JButton("Submit");
		ActionListener listener = new ButtonListenerMain();
		submitButton.addActionListener(listener);
		
		add(kickingPanel, BorderLayout.WEST);
		add(cornerPanel, BorderLayout.CENTER);
		add(submitButton, BorderLayout.SOUTH);
	}
	
	class KickingPanel extends JPanel {
		private ButtonGroup bGroup;
		private JRadioButton kickingButton;
		
		public KickingPanel() {
			kickingButton = new JRadioButton("Kicking");
			kickingButton.setName("Kicking");
			JRadioButton defendingButton = new JRadioButton("Defending");
			ActionListener listener = new ButtonListenerK();
			
			setBorder(new TitledBorder(new EtchedBorder(), "You are..."));
			setLayout(new GridLayout(0, 1));
			
			kickingButton.addActionListener(listener);
			defendingButton.addActionListener(listener);
			bGroup = new ButtonGroup();
			bGroup.add(kickingButton);
			bGroup.add(defendingButton);
			
			add(kickingButton);
			add(defendingButton);
			
		}
	}
	
	class ButtonListenerK implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		    if (((Component) e.getSource()).getName() == "Kicking")
		    	kicking = true;
		    else
		    	kicking = false;
		}
	}
	
	class CornerPanel extends JPanel {
		private ButtonGroup bGroup;
		private JRadioButton upperButton;
		
		public CornerPanel() {
			upperButton = new JRadioButton("Top");
			upperButton.setName("Top");
			JRadioButton bottomButton = new JRadioButton("Bottom");
			
			ActionListener listener = new ButtonListenerC();
			
			setBorder(new TitledBorder(new EtchedBorder(), "Which Corner?"));
			setLayout(new GridLayout(0, 1));
			
			upperButton.addActionListener(listener);
			bottomButton.addActionListener(listener);
			bGroup = new ButtonGroup();
			bGroup.add(upperButton);
			bGroup.add(bottomButton);
			
			add(upperButton);
			add(bottomButton);
		}
	}
	
	class ButtonListenerC implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		    if (((Component) e.getSource()).getName() != "Top")
		    	top = false;
		    else
		    	top = true;
		}
	}
	
	class ButtonListenerMain implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		    field.setupCornerKick(kicking, top);
		}
	}
	
}