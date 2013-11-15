package fieldFiles;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

public class Field {
	private final static int XDIM = 900;
	private final static int YDIM = 675;
	private Formation humanFormation;
	private Formation compFormation;
	private String startFormFile, cornerFile;
	private Map<String,ArrayList<Player>> startFormations;
	private ArrayList<Player> humanTeam;
	private ArrayList<Player> compTeam;
	
	public Field(String startFormFile, String cornerFile) {
		super();
		this.startFormFile = startFormFile;
		this.cornerFile = cornerFile;
		loadConfigFiles(startFormFile, cornerFile);
	}
	public void drag(){
		
	}
	public void loadConfigFiles(String startFormFile, String cornerFile){
		
	}
	
	public void whichPlayer(int x, int y){
		
	}
	public void runSimulation(){
		
	}
	public void switchFormation(String humanFormation, String compFormation){
		
	}
	public void setupCornerKick(boolean youAreKicking, boolean topCorner){
		
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
	}
	public void setCompFormation(Formation formation) {
		this.compFormation = formation;
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
