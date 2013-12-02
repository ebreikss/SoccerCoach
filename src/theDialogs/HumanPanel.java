package theDialogs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import fieldFiles.Formation;

public class HumanPanel extends JPanel{
	private Map<String, JRadioButton> humanButtons;
	private ButtonGroup bGroup;
	private String name;
	
	public HumanPanel(Map<String,Formation> startFormations) {
		name = "";
		humanButtons = new HashMap<String, JRadioButton>();
		bGroup = new ButtonGroup();
		ActionListener listener = new ButtonListener();
		for(String key : startFormations.keySet()){
				humanButtons.put(key, new JRadioButton(key));
		}
		setBorder(new TitledBorder(new EtchedBorder(), "Human"));

		setLayout(new GridLayout(0, 1));
		//setPreferredSize(new Dimension(150,150));
		for(String key : humanButtons.keySet()){
			add(humanButtons.get(key));
			humanButtons.get(key).addActionListener(listener);
			bGroup.add(humanButtons.get(key));
			humanButtons.get(key).setName(key);
		}
	}
	
	public String getName() {
		return name;
	}
	
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			name = ((Component) e.getSource()).getName();
		}
	}
}
