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

	public Formation(String name){
		this.name = name;
		// allocating space
		teamX = new ArrayList<Player>();
		teamO = new ArrayList<Player>();
		teamXtemplate = new ArrayList<Player>();
		teamOtemplate = new ArrayList<Player>();
	}

	public void resetPlayers(){
		// will be used initially to set the teams to the templates
		// can be used to reset each team back to their templates
		teamX = (ArrayList<Player>) teamXtemplate.clone();
		teamO = (ArrayList<Player>) teamOtemplate.clone();
	}
	public void mirrorTeamXtemplate(){
		// used to set the computer team to the same formation as human team
		for(int i = 0; i < teamXtemplate.size(); i++){
			teamOtemplate.add(teamXtemplate.get(i));
			teamOtemplate.get(i).setComputer(true);
		}
	}
	public String getName(){
		return name;
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
