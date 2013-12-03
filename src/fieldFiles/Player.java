package fieldFiles;
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

	// For reference:
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
	private boolean isKicking = false; // for corner kicks
	private final int PLAYERSIZE = 30;

	public Player(){
		// default just to make testing simple
	}

	public boolean isKicking() {
		return isKicking;
	}

	public void setKicking(boolean isKicking) {
		this.isKicking = isKicking;
	}

	public Player(String position, String name, int xCoord, int yCoord){
		this.position = Positron.valueOf(position);
		this.name = name;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		directionAngle = 0;
	}

	@Override
	public void simulate(int n) {
		// DEPRECIATIED!!
		for (int i = 0; i < n; i++)
			move();
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(position.value);
		if (!computer) {
			g.fillRect((xCoord-PLAYERSIZE/2), (yCoord-PLAYERSIZE/2), PLAYERSIZE, PLAYERSIZE);
			g.setColor(Color.black);
			g.drawRect((xCoord-PLAYERSIZE/2), (yCoord-PLAYERSIZE/2), PLAYERSIZE, PLAYERSIZE);
		}
		else {
			g.fillOval((xCoord-PLAYERSIZE/2), (yCoord-PLAYERSIZE/2), PLAYERSIZE, PLAYERSIZE);
			g.setColor(Color.black);
			g.drawOval((xCoord-PLAYERSIZE/2), (yCoord-PLAYERSIZE/2), PLAYERSIZE, PLAYERSIZE);
		}

	}
	
	// -------------------------------------
	
	// Was too complicated and unneccesary
	
/*	public void drawLine(Graphics g){
		if((xCoord - deltaX) == lineXInit){
			g.setColor(position.value);
			g.drawRect(xCoord, yCoord, PLAYERSIZE, PLAYERSIZE);
			//g.drawLine((int)xCoord, (int)yCoord, (int)deltaX + (int)lineXInit, ((int)lineYInit - (int)deltaY));
		}
		else
			g.drawLine((int)xCoord, (int)yCoord, (int)deltaX + (int)lineXInit, ((int)lineYInit - (int)deltaY));
	}*/
	
	// -------------------------------------
	
	double deltaX = 0;
	double deltaY = 0;
	double lineXInit = 0;
	double lineYInit = 0;
	public void calculatDirection(int x, int y) {
		lineXInit = xCoord;
		lineYInit = yCoord;
		deltaX = x - xCoord;
		deltaY = -(y - yCoord); // cuz coords are flipped in CS...
		
		directionAngle = Math.atan(deltaY/deltaX) * 180 / Math.PI;
		
		if (deltaX <= 0) 
			directionAngle += 180;
		else if (deltaY <= 0)
			directionAngle += 360;
		else { /* it's fine */ }
		
		velocity = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
		// velocity will be set to current / timestep eventually
	}


	private void randomWalk() {
		Random rand = new Random();
		double randVel = rand.nextInt((int) Math.abs(velocity)) / 2;
		//System.out.println("Randomly Walked");

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
	
public boolean containsClick(int mouseX, int mouseY, Field f) {
		
		Rectangle rect = new Rectangle((xCoord-PLAYERSIZE/2), (yCoord-PLAYERSIZE/2), PLAYERSIZE, PLAYERSIZE);
		if (rect.contains(new Point(mouseX, mouseY)))
			return true;
		return false;
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

	public void setPosition(Positron position) {
		this.position = position;
	}	
}
