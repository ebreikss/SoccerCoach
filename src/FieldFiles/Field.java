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
				System.out.println(name);
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
		// one mid will be randomly placed out of the box to defend
		// one mid will be randomly selected to be kicking
		// two defenders on defending team will be placed in smaller goal area
		if (topCorner) {
			for (Player playa : humanTeam) {
				if (playa.getPositionName().equalsIgnoreCase("goalie")) {
					playa.setxCoord(rand.nextInt(100) + 250);
					playa.setyCoord(rand.nextInt(10) + 332);
				} else if (playa.getPositionName().equalsIgnoreCase("back")) {
					playa.setxCoord(rand.nextInt(100) + 450);
					playa.setyCoord(rand.nextInt(100) + 287);
				} else if (playa.getPositionName().equalsIgnoreCase("mid")) {
					playa.setxCoord(rand.nextInt(130) + 700);
					playa.setyCoord(rand.nextInt(300) + 187);
				} else if (playa.getPositionName().equalsIgnoreCase("forward")) {
					playa.setxCoord(rand.nextInt(130) + 800);
					playa.setyCoord(rand.nextInt(300) + 187);
				}
			}
		}
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
		compTeam = (ArrayList<Player>) compFormation.getXtemplate().clone(); 
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
