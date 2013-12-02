package theDialogs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import fieldFiles.Formation;

public class CompPanel extends JPanel{
	private Map<String, JRadioButton> compButtons;
	private ButtonGroup bGroup;
	private String name;
	
	public CompPanel(Map<String,Formation> startFormations) {
		name = "";
		compButtons = new HashMap<String, JRadioButton>();
		bGroup = new ButtonGroup();
		ActionListener listener = new ButtonListenerC();
		for(String key : startFormations.keySet()){
			compButtons.put(key, new JRadioButton(key));
		}
		setBorder(new TitledBorder(new EtchedBorder(), "Computer"));

		setLayout(new GridLayout(0, 1));
		//setPreferredSize(new Dimension(150,150));
		for(String key : compButtons.keySet()){
			add(compButtons.get(key));
			compButtons.get(key).addActionListener(listener);
			bGroup.add(compButtons.get(key));
			compButtons.get(key).setName(key);
		}
	}
	

	public String getName() {
		return name;
	}
	
	class ButtonListenerC implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			name = ((Component) e.getSource()).getName();
		}
	}
}

