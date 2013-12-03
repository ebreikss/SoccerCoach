package fieldFiles;
import java.util.*;
import java.awt.*;

public class Formation implements Cloneable {
	public static final int FREE_KICK_RADIUS = 50; // Incorrect

	private ArrayList<Player> teamX; // human
	private ArrayList<Player> teamXtemplate; // human players
	
	private String name;
	//private Field field;

	public Formation(){
		//this.field = field;
		// allocating space
		teamX = new ArrayList<Player>();
		for (int i = 0; i < 11; i++) {
			teamX.add(new Player());
		}
		teamXtemplate = new ArrayList<Player>();
	}
	
	public Formation(String name){
		this.name = name;
		//this.field = field;
		// allocating space
		teamX = new ArrayList<Player>();
		for (int i = 0; i < 11; i++) {
			teamX.add(new Player());
		}
		teamXtemplate = new ArrayList<Player>();
	}

	public void resetPlayers(){
		// will be used initially to set the teams to the templates
		// can be used to reset each team back to their templates
		for (int i = 0; i < 11; i++) {
			teamX.get(i).setxCoord(teamXtemplate.get(i).getxCoord());
			teamX.get(i).setyCoord(teamXtemplate.get(i).getyCoord());
			teamX.get(i).setPosition(teamXtemplate.get(i).getPosition());
		}	
	}

	public void setTeamXtemplate(ArrayList<Player> teamXtemplate) {
		this.teamXtemplate = teamXtemplate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Formation other = (Formation) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (teamX == null) {
			if (other.teamX != null)
				return false;
		} else if (!teamX.equals(other.teamX))
			return false;
		if (teamXtemplate == null) {
			if (other.teamXtemplate != null)
				return false;
		} else if (!teamXtemplate.equals(other.teamXtemplate))
			return false;
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		
		return super.clone();
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
