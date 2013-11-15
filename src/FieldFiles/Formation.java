package fieldFiles;
import java.util.*;
import java.awt.*;

public class Formation {
	public static final int FREE_KICK_RADIUS = 50; // Incorrect
	
	public enum Side { 
		LEFT ("Left"),
		RIGHT ("Right");
		
		private String value;
		
		Side (String aValue) {
			value = aValue;
		}
		
		public String toString() {
			return value;
		}
	}
	
	
	private ArrayList<Player> teamX; // human
	private ArrayList<Player> teamO; // comp
	
	private ArrayList<Player> teamXtemplate; // human players
	private ArrayList<Player> teamOtemplate; // computer players
	private Map<String,ArrayList<Player>> cornerKickFormations;
	private String name;
	private Side side;
	
	public void resetPlayers(){
		
	}

	public ArrayList<Player> getTeamX(){
		return teamX;
	}
	public ArrayList<Player> getTeamO(){
		return teamO;
	}
	
	public ArrayList<Player> getXtemplate(){
		return teamXtemplate;
	}
	public ArrayList<Player> getOtemplate(){
		return teamOtemplate;
	}


}
