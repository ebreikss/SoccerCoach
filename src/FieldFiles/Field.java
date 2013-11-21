package FieldFiles;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Field {
	private final static int XDIM = 900;
	private final static int YDIM = 675;
	private final static int XPENALTYBOX = 120;
	private final static int YPENALTYBOX = 300;
	private final static int XGOALIEBOX = 60;
	private final static int YGOALIEBOX = 135;
	
	private Ball ball;
	
	private Formation humanFormation;
	private Formation compFormation;
	private String startFormFile, cornerFile;
	private Map<String,Formation> startFormations;  // found out formation class will be useful so i made this hold formations
	// instead of a list of players
	private ArrayList<Player> humanTeam;
	private ArrayList<Player> compTeam;

	public Field(String startFormFile, String cornerFile) {
		super();
		startFormations = new HashMap<String, Formation>();
		this.startFormFile = startFormFile;
		this.cornerFile = cornerFile;
		loadConfigFiles(startFormFile, cornerFile);
	}
	public void drag(){

	}
	public void loadConfigFiles(String startFormFile, String cornerFile){
		try{
			FileReader reader = new FileReader(startFormFile);
			Scanner in = new Scanner(reader);
			String name;
			Formation formation;
			String[] line = "nothing".split(",");
			while(in.hasNext()){
				name = in.nextLine();
				formation = new Formation(name);
				line = in.nextLine().split(", ");
				while(!line[0].equals("END")){
					if(!line[0].equals("END")){
						formation.getXtemplate().add(new Player(line[0], line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3])));
					}
					line = in.nextLine().split(", ");
				}
				formation.mirrorTeamXtemplate();
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
		humanTeam = (ArrayList<Player>) startFormations.get(humanFormation).getXtemplate().clone();
		compTeam = (ArrayList<Player>) startFormations.get(compFormation).getXtemplate().clone();
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
		mirror(compFormation.getOtemplate());
		compTeam = (ArrayList<Player>) compFormation.getOtemplate().clone(); 
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
}
