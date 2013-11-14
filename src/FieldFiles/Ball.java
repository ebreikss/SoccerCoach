package fieldFiles;
import java.awt.*;

public class Ball {
	private int xCoord;
	private int yCoord;
	private int radius;
	private int velocity;
	private int directionAngle;
	
	
	public void simulate(){
		
	}
	public void draw(Graphics g){
		
	}
	
	// ----------------------------------------------------------------------------------------
	
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
	public int getDirectionAngle() {
		return directionAngle;
	}
	public void setDirectionAngle(int directionAngle) {
		this.directionAngle = directionAngle;
	}
	

}
