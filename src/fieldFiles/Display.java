package fieldFiles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import theDialogs.RunSimDialog;
import theDialogs.StartFormationDialog;

public class Display extends JFrame {
	
	private Field field;
	private StartFormationDialog startFormDialog;
	private RunSimDialog runSimDialog;
	
	private JMenu createFileMenu(){
		JMenu menu = new JMenu("File");
		menu.add(createRunItem());
		menu.add(createFileExitItem());

		return menu;
	}
	private JMenu createModeMenu(){
		JMenu menu = new JMenu("Mode"); 
		menu.add(createStartFormation());
		menu.add(createCornerKick());

		return menu;
	}
	private JMenuItem createRunItem() {
		JMenuItem item = new JMenuItem("Run Simulation");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				runSimDialog.setVisible(true);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	private JMenuItem createFileExitItem(){
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	
	private JMenuItem createStartFormation() {
		JMenuItem startFormSetup = new JMenuItem("Start Formation");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e)
			{
				startFormDialog.setVisible(true);
			}
		}
		startFormSetup.addActionListener(new MenuItemListener());
		return startFormSetup;
	}
	
	private JMenuItem createCornerKick() {
		JMenuItem cornKickForm = new JMenuItem("Corner Kick");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e)
			{
				//[somedialog].setVisible(true);
			}
		}
		cornKickForm.addActionListener(new MenuItemListener());
		return cornKickForm;
	}
	
	
	public Display(String file) {
		field = new Field(file);
		setSize(field.getXdim()+30, field.getYdim()+60);
		setLayout(new BorderLayout());
		add(field, BorderLayout.CENTER);
		
		//File menu
				JMenuBar menuBar = new JMenuBar();
				setJMenuBar(menuBar);
				menuBar.add(createFileMenu());
				menuBar.add(createModeMenu());
				
				startFormDialog = new StartFormationDialog(field.getFormationList(), field);
				runSimDialog = new RunSimDialog(field);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Display gui = new Display("startFormsConfig.txt");
		gui.setVisible(true);

	}

}
