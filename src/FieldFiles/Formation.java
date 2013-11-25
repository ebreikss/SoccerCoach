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
	private ArrayList<Player> teamXtemplate; // human players

	
	private String name;
	private Side side;
	private Field field;

	public Formation(String name, Field field){
		this.name = name;
		this.field = field;
		// allocating space
		teamX = new ArrayList<Player>();
		teamXtemplate = new ArrayList<Player>();
	}

	public void resetPlayers(){
		// will be used initially to set the teams to the templates
		// can be used to reset each team back to their templates
		teamX = (ArrayList<Player>) teamXtemplate.clone();

	}

	public String getName(){
		return name;
	}

	public ArrayList<Player> getTeamX(){
		return teamX;
	}

	public ArrayList<Player> getXtemplate(){
		return teamXtemplate;
	}

}
