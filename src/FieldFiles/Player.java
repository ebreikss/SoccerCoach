package fieldFiles;
import java.awt.*;

public class Player extends Movable {
	public enum Positron {
		GOALIE (Color.gray),
		BACK (Color.blue),
		MID (Color.yellow),
		FORWARD (Color.red);
		
		private Color value;
		
		Positron (Color aValue) {
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
	private int distance, x, y;
	private Positron position;
	
	private boolean computer;
	public Player(){
		// default just to make testing simple
	}
	
	public Player(String position, String name, int x, int y){
		this.position = Positron.valueOf(position);
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void simulate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
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
	public Positron getPosition(){
		return position;
	}	
}
