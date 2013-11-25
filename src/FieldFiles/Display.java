package fieldFiles;

import java.awt.*;

import javax.swing.*;

public class Display extends JFrame {
	
	private Field field;
	
	public Display(String file) {
		field = new Field(file);
		setSize(field.getXdim()+30, field.getYdim()+60);
		setLayout(new BorderLayout());
		add(field, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Display gui = new Display("startFormsConfig.txt");
		gui.setVisible(true);

	}

}
