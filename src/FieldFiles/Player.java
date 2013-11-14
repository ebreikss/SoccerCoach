package fieldFiles;
import java.awt.*;

public class Player extends Ball {
	public enum Positon {
		GOALIE (Color.gray),
		BACK (Color.blue),
		MID (Color.yellow),
		FORWARD (Color.red);
		
		private Color value;
		
		Positon (Color aValue) {
			value = aValue;
		}
		
		public String toString() {
			return value.toString();
		}
	}
	
	private char team;
	private String name;
	private String positionName;
	private Color color;
	private int distance;
	
	private boolean computer;
	
	public void simulate(){
		
	}
	public void draw (Graphics g){
		
	}
	
	///////////////////////////////////////
	
	public char getTeam() {
		return team;
	}
	public void setTeam(char team) {
		this.team = team;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public boolean isComputer() {
		return computer;
	}
	public void setComputer(boolean computer) {
		this.computer = computer;
	}
	
	
}
