package fieldFiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Field extends JPanel implements MouseListener{
	private final static int XDIM = 900;
	private final static int YDIM = 675;
	private final static int XPENALTYBOX = 120;
	private final static int YPENALTYBOX = 300;
	private final static int XGOALIEBOX = 60;
	private final static int YGOALIEBOX = 135;
	private final static int CENTERRAD = 100;
	
	private Ball ball;
	
	private Formation humanFormation;
	private Formation compFormation;
	private String startFormFile;
	private Map<String,Formation> startFormations;  // found out formation class will be useful so i made this hold formations
	// instead of a list of players
	private ArrayList<Player> humanTeam;
	private ArrayList<Player> compTeam;
	private Player selectedPlayer;

	public Field(String startFormFile) {
		super();
		startFormations = new HashMap<String, Formation>();
		this.startFormFile = startFormFile;
		//this.cornerFile = cornerFile;
		loadConfigFiles(startFormFile);
		switchFormation("WM", "Pyramid");
		createLayout();
		
		addMouseListener(this);

	}
	public void drag(){

	}
	
	public void createLayout() {
		setSize(XDIM, YDIM);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(44,129,61));
		g.fillRect(0, 0, XDIM+40, YDIM+40);
		g.setColor(Color.white);
		g.drawRect(0, 0, XDIM, YDIM);
		g.drawLine(XDIM/2, 0, XDIM/2, YDIM);
		// Penalty Boxes
		g.drawRect(0, YDIM/2-YPENALTYBOX/2, XPENALTYBOX, YPENALTYBOX);
		g.drawRect(XDIM-XPENALTYBOX, YDIM/2-YPENALTYBOX/2, XPENALTYBOX, YPENALTYBOX);
		// GoalieBoes
		g.drawRect(0, YDIM/2-YGOALIEBOX/2, XGOALIEBOX, YGOALIEBOX);
		g.drawRect(XDIM-XGOALIEBOX, YDIM/2-YGOALIEBOX/2, XGOALIEBOX, YGOALIEBOX);
		// arcs and circles
		g.drawArc(-10, -10, 20, 20, 270, 90);
		g.drawArc(-10, YDIM - 10, 20, 20, 360, 90);
		g.drawArc(XDIM-10, YDIM - 10, 20, 20, 90, 90);
		g.drawArc(XDIM-10, -10, 20, 20, 180, 90);
		g.drawArc(XDIM-10, -10, 20, 20, 180, 90);
		g.drawArc(2*XGOALIEBOX-CENTERRAD/2-20, (YDIM-CENTERRAD)/2, CENTERRAD, CENTERRAD, 295, 130);
		g.drawArc(XDIM - 2*XGOALIEBOX-CENTERRAD/2+20, (YDIM-CENTERRAD)/2, CENTERRAD, CENTERRAD, 115, 130);
		
		g.drawOval((XDIM-CENTERRAD)/2, (YDIM-CENTERRAD)/2, CENTERRAD, CENTERRAD);
		g.fillOval((XDIM-10)/2, (YDIM-10)/2, 10, 10);
		g.fillOval((XPENALTYBOX+XGOALIEBOX)/2 - 5, (YDIM-10)/2, 10, 10);
		g.fillOval(XDIM - (XPENALTYBOX+XGOALIEBOX)/2 - 5, (YDIM-10)/2, 10, 10);
		
		for (Player playa : humanTeam)
			playa.draw(g);
		for (Player playa : compTeam)
			playa.draw(g);
	}
	
	public void loadConfigFiles(String startFormFile){
		try{
			FileReader reader = new FileReader(startFormFile);
			Scanner in = new Scanner(reader);
			String name;
			Formation formation;
			String[] line = "nothing".split(",");
			while(in.hasNext()){
				name = in.nextLine();
				formation = new Formation(name, this);
				line = in.nextLine().split(", ");
				while(!line[0].equals("END")){
					if(!line[0].equals("END")){
						formation.getXtemplate().add(new Player(line[0], line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3])));
					}
					line = in.nextLine().split(", ");
				}
				formation.resetPlayers();
				startFormations.put(name,formation);
			}
		}
		catch(FileNotFoundException e){

		}

	}

	public void whichPlayer(int x, int y){
		
	}
	public void runSimulation(){

	}
	public void switchFormation(String humanFormation, String compFormation){
		this.humanFormation.setTeamXtemplate(startFormations.get(humanFormation).getXtemplate());
		this.compFormation.setTeamXtemplate(startFormations.get(compFormation).getXtemplate());
		this.humanFormation.resetPlayers();
		this.compFormation.resetPlayers();
		for (Player playa : compTeam) {
			playa.setComputer(true);
		}
		for (Player playa : humanTeam) {
			playa.setComputer(false);
		}
		mirror(compTeam);
		repaint();
	}
	public void setupCornerKick(boolean youAreKicking, boolean topCorner){
		Random rand = new Random();
		ArrayList<Player> kicking, defending;
		if (youAreKicking) {
			kicking = humanTeam;
			defending = compTeam;
		} else {
			kicking = compTeam;
			defending = humanTeam;
		}
		
		boolean midset = false;
		int defenders = 0;
		
		// one mid will be randomly placed out of the box to defend
		// one mid will be randomly selected to be kicking
		// two defenders on defending team will be placed in smaller goal area
		for (Player playa : defending) {
			if (playa.getPosition().equals(Player.Positron.GOALIE)) {
				playa.setxCoord(rand.nextInt(XDIM/100) + XDIM - XDIM/100);
				playa.setyCoord(rand.nextInt(10) + YDIM/2-10);
			} else if (playa.getPosition().equals(Player.Positron.BACK)) {
				if (defenders < 2) {
					playa.setxCoord(XDIM-1);
					if (defenders < 1)
						playa.setyCoord((YDIM-YGOALIEBOX)/2);
					else
						playa.setyCoord((YDIM-YGOALIEBOX)/2 + YGOALIEBOX);
					defenders++;
				} else {
					playa.setxCoord(rand.nextInt(XDIM/9) + XDIM-XDIM/9);
					playa.setyCoord(rand.nextInt(YPENALTYBOX) + (YDIM-YPENALTYBOX)/2);
				}
			} else { // MID or FORWARD
				playa.setxCoord(rand.nextInt(XPENALTYBOX) + XDIM-3/2*XPENALTYBOX);
				playa.setyCoord(rand.nextInt(YPENALTYBOX) - YPENALTYBOX/2 + (YDIM - YPENALTYBOX));
			} 
		}
		
		for (Player playa : kicking) {
			if (playa.getPosition().equals(Player.Positron.GOALIE)) {
				playa.setxCoord(rand.nextInt(XDIM/9) + XDIM/3);
				playa.setyCoord(rand.nextInt(10) + YDIM/2-10);
			} else if (playa.getPosition().equals(Player.Positron.BACK)) {
				playa.setxCoord(rand.nextInt(XDIM/9) + XDIM/2);
				playa.setyCoord(rand.nextInt(YPENALTYBOX) + YDIM/2 - YPENALTYBOX/2);
			} else if (playa.getPosition().equals(Player.Positron.MID)) {
				if (!midset) {
					playa.setKicking(true);
					if (topCorner) {
						playa.setxCoord(XDIM);
						playa.setyCoord(0);
					} else {
						playa.setxCoord(XDIM);
						playa.setyCoord(YDIM);
					}
					midset = true;
				} else {
				playa.setxCoord(rand.nextInt(XPENALTYBOX) + XDIM-3/2*XPENALTYBOX);
				playa.setyCoord(rand.nextInt(YPENALTYBOX) - YPENALTYBOX/2 + (YDIM - YPENALTYBOX));
				}
			} else if (playa.getPosition().equals(Player.Positron.FORWARD)) {
				playa.setxCoord(rand.nextInt(XDIM/9) + XDIM-XDIM/9);
				playa.setyCoord(rand.nextInt(YPENALTYBOX) + (YDIM-YPENALTYBOX)/2);
			}
		}
		
		if (!youAreKicking) {
			mirror(kicking);
			mirror(defending);
		}
		
	}
	
	public void mirror(ArrayList <Player> playas) {
		for (Player playa : playas)
			playa.setxCoord(XDIM - playa.getxCoord());
	}
	
	
	public void setupFreeKick(boolean youAreKicking, Point ballLocation){

	}

	//////////////////////////////////////////

	public Map getFormationList() {
		return startFormations;
	}
	public ArrayList getHumanTeam(){
		return humanTeam;
	}
	public ArrayList getCompTeam(){
		return compTeam;
	}
	public void setHumanFormation(Formation formation) {
		this.humanFormation = formation;
		humanTeam = (ArrayList<Player>) humanFormation.getXtemplate().clone();
	}
	public void setCompFormation(Formation formation) {
		// since we create a computer 'Formation' it seems logical to set compTeam to the teamXtemplate
		this.compFormation = formation;
		compFormation.resetPlayers();
		mirror(compFormation.getXtemplate());
		compTeam = (ArrayList<Player>) compFormation.getTeamX(); 
	}
	public static int getXdim() {
		return XDIM;
	}
	public static int getYdim() {
		return YDIM;
	}
	public Formation getHumanFormation(){
		return humanFormation;
	}
	public Formation getCompFormation(){
		return compFormation;
	}
	
	public static void main(String[] args) {
		  Field gui = new Field("startFormsConfig.txt");
		  gui.setVisible(true);
		  
	}
	
	///////////////////////////////////////////////////
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}
	@Override
	public void mousePressed(MouseEvent e) {
		boolean validPlayer = false;
		for(Player playa : humanTeam){
			if(playa.containsClick(e.getX(), e.getY(), this)){
				selectedPlayer = playa;
				validPlayer = true;
			}
		}
		if(validPlayer = false){
			JOptionPane.showMessageDialog(null, "That is an invalid choice, try again.", "OOPS!!", 
					JOptionPane.INFORMATION_MESSAGE);
			selectedPlayer = null;
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		try{
			selectedPlayer.calculatDirection(e.getX(), e.getY());
		}
		catch(NullPointerException n){
			JOptionPane.showMessageDialog(null, "That is an invalid choice, try again.", "OOPS!!", 
					JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
