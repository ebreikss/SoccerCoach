package fieldFiles;

import java.util.ArrayList;
import java.util.Map;

public class Field {
	private final static int XDIM = 900;
	private final static int YDIM = 675;
	private Formation formation;
	private String startFormFile, cornerFile;
	private Map<String,ArrayList<Player>> startFormations;
	
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
	public void switchFormation(String newFormation){
		
	}
	
	//////////////////////////////////////////
	
	public Map getFormationList() {
		return startFormations;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	public static int getXdim() {
		return XDIM;
	}
	public static int getYdim() {
		return YDIM;
	}
}
