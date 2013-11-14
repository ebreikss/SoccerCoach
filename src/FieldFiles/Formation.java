package fieldFiles;
import java.util.*;
import java.awt.*;

public class Formation {
	public static final int FREE_KICK_RADIUS = 50; // Incorrect
	private Map<String,ArrayList<Player>> startFormations;
	
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
	
	private ArrayList<Player> teamX;
	private ArrayList<Player> teamO;
	
	private ArrayList<Player> teamXtemplate;
	private ArrayList<Player> teamOtemplate;
	private Map<String,ArrayList<Player>> cornerKickFormations;
	
	
	public void resetPlayers(){
		
	}
	public void setupCornerKick(boolean youAreKicking, boolean topCorner){
		
	}
	public void setupFreeKick(boolean youAreKicking, Point ballLocation){
		
	}
	public void loadConfigFiles(String startFormFile, String cornerFile){
		
	}
	

}
