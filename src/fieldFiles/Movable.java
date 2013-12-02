package fieldFiles;

import java.awt.Graphics;

public abstract class Movable {
	protected int xCoord;
	protected int yCoord;
	protected int radius;
	protected int velocity;
	protected double directionAngle;
	
	public abstract void simulate();
	public abstract void draw(Graphics g);
	
	
	public int getxCoord() {
		return xCoord;
	}
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	public int getyCoord() {
		return yCoord;
	}
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public int getVelocity() {
		return velocity;
	}
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	public double getDirectionAngle() {
		return directionAngle;
	}
	public void setDirectionAngle(double directionAngle) {
		this.directionAngle = directionAngle;
	}
	public abstract void move();
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xCoord;
		result = prime * result + yCoord;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movable other = (Movable) obj;
		if (xCoord != other.xCoord)
			return false;
		if (yCoord != other.yCoord)
			return false;
		return true;
	}
	
	
	
	
}
