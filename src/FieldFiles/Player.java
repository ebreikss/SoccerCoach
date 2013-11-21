package FieldFiles;
import java.awt.*;
import java.util.Random;

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

	//	protected int xCoord;
	//	protected int yCoord;
	//	protected int radius;
	//	protected int velocity;
	//	protected int directionAngle;

	private char team;
	private String name;
	private String positionName;
	private Color color;
	private int distance; // We have xCoord and yCoord for these: x, y;
	private Positron position;
	private boolean computer;
	private boolean isKickingOrDefending = false; // for corner kicks

	public Player(){
		// default just to make testing simple
	}

	public boolean isKickingOrDefending() {
		return isKickingOrDefending;
	}

	public void setKickingOrDefending(boolean isKickingOrDefending) {
		this.isKickingOrDefending = isKickingOrDefending;
	}

	public Player(String position, String name, int xCoord, int yCoord){
		this.position = Positron.valueOf(position);
		this.name = name;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}

	@Override
	public void simulate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	private void randomWalk() {
		Random rand = new Random();
		int randVel = rand.nextInt(Math.abs(velocity)) / 2;
		System.out.println("Randomly Walked");

		// x-direction
		if (rand.nextInt(2) == 0)
			xCoord += randVel;
		else
			xCoord -= randVel;

		// y-direction
		if (rand.nextInt(2) == 0)
			yCoord += randVel;
		else 
			yCoord -= randVel;
	}

	@Override
	public void move() {
		double radianAngle = directionAngle * Math.PI / 180;
		int xVelocity = (int) (Math.cos(radianAngle) * velocity);
		int yVelocity = (int) (Math.sin(radianAngle) * velocity);
		
		if (computer) {

			if (directionAngle < 0) // aka, null 
				randomWalk();
			else { // Move regularly

				Random rand = new Random();

				// X-Direction
				if (xVelocity != 0) {
					int xDist = rand.nextInt(Math.abs(xVelocity));
					if (xVelocity < 0)
						xDist *= -1;
					if (rand.nextInt(10) < 9)  // 90% chance of moving correct direction
						xCoord += xDist;  // move random number of pixels from 0 - xVel
					else // move wrong direction
						xCoord += -Math.floor(xDist/2); // move randomly but not as far
				} else {
					// wander in this direction
					if (rand.nextInt(2) == 0)
						xCoord += 1;
					else
						xCoord += -1;
				}

				// Y-Direction
				if (yVelocity != 0) {
					int yDist = rand.nextInt(Math.abs(yVelocity));
					if (yVelocity < 0)
						yDist *= -1;
					if (rand.nextInt(10) < 9)
						yCoord += -yDist; // Negative cuz y-axis is backwards
					else
						yCoord += Math.floor(yDist/2);
				} else {
					// wander in this direction
					if (rand.nextInt(2) == 0)
						yCoord += 1;
					else
						yCoord += -1;
				}
			}
		} else	{ 		// human player
			xCoord += xVelocity;
			yCoord -= yVelocity;
		}
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
