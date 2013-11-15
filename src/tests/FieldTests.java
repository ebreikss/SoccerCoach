package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import fieldFiles.Field;
import fieldFiles.Formation;
import fieldFiles.Player;
import fieldFiles.Player.Positron;

public class FieldTests {
	
	static Field soccerField;
	static Formation formation;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String startFormFile = "startFormsConfig";
		String cornerFile = "cornerFile";
		soccerField = new Field(startFormFile, cornerFile); // constructor also calls loadConfigFiles()
		
		// if you notice more things that need to change, do it. 
		// Constructors and parameters mostly, but don't be afraid to add functions, etc
		// for instance, loadConfigFiles() might need to be moved into Field
		
		// We are testing that the functions DO things
		//    that they set up the formations
		//    that they can change the formations
		//    that they can change the Players' attributes
		//    ...correctly
		
		// Not concerned about drawing or *how* we will ultimately pass these variables in, just that
		//   once we can pass them in, the functions will compute correctly, etc.
	}

	
	@Test
	public void loadConfigFilesTest(){
		// tests both starting formations and corner kick loads right
		ArrayList <Player> tempPlayers;
		
		// ensuring that the right amount of Formations are made
		int expected = 4;
		int actual = soccerField.getFormationList().size();
		Assert.assertEquals(expected, actual);
		expected = 5;
		actual = 0;
		int expecto = 1;
		int actualo = 0;
		tempPlayers = (ArrayList<Player>) soccerField.getFormationList().get("Pyramid");
		for(int i = 0; i < tempPlayers.size(); i++){
			if(tempPlayers.get(i).getPosition() == Positron.FORWARD){
				actual++;
			}
			else if(tempPlayers.get(i).getPosition() == Positron.GOALIE){
				actualo++;
			}
		}
		Assert.assertEquals(expecto, actualo); // makes sure there's only one goalie 
		Assert.assertEquals(expected, actual); // makes sure there are 5 forwards in 'pyramid'
		expected = 5;
		actual = 0;
		expecto = 3;
		actualo = 0;
		tempPlayers = (ArrayList<Player>) soccerField.getFormationList().get("Metodo");
		for(int i = 0; i < tempPlayers.size(); i++){
			if(tempPlayers.get(i).getPosition() == Positron.FORWARD){
				actual++;
			}
			else if(tempPlayers.get(i).getPosition() == Positron.MID){
				actualo++;
			}
		}
		Assert.assertEquals(expecto, actualo); // checks for correct amount of mids in 'metodo'
		Assert.assertEquals(expected, actual); // checks for correct amount of forwards in 'metodo'
		expected = 11;
		actual = tempPlayers.size();
		Assert.assertEquals(expected, actual);
		
		// check the template Players. Check some of their locations, colors, positions, etc (not *all* is needed)
		fail("Not yet implemented");
	}
	
	// ----------------------------------------------------------------------------------------
	// TEST runSimulation() 
	
	@Test
	public void humanPlayerMoveTest(){
		// tests that the players move the correct amount in the correct direction
		Player playa = new Player();
		playa.setxCoord(50);
		playa.setyCoord(50);
		playa.setDirectionAngle(0);
		playa.setVelocity(1);
		playa.move();
		int expected = 51;
		int actual = playa.getxCoord();
		Assert.assertEquals(expected,actual);
		playa.setxCoord(50);
		playa.setyCoord(50);
		playa.setDirectionAngle(90);
		playa.setVelocity(1);
		playa.move();
		expected = 49;
		actual = playa.getyCoord();
		Assert.assertEquals(expected,actual);
		playa.setxCoord(50);
		playa.setyCoord(50);
		playa.setDirectionAngle(45);
		playa.setVelocity(2);
		playa.move();
		expected = 51;
		actual = playa.getxCoord();
		Assert.assertEquals(expected,actual);
		expected = 49;
		actual = playa.getyCoord();
		Assert.assertEquals(expected,actual);
		
		
		
		// given a velocity, location, and direction angle, given a time moves to right location 
		// Test multiple situations and/or multiple players at once, and the BALL too
		fail("Not yet implemented");
	}
	
	@Test
	public void computerPlayerMoveTest(){
		// given same stuff as above, only with a computer = true
		// test that based on velocity vector, moving towards one direction is favored over another in the end
		// loop the function like 100 times or something and count
		
		// try to find both simple and complicated cases (such as a 'null' direction where they just do a random walk)
		fail("Not yet implemented");
	}
	
	// ----------------------------------------------------------------------------------------
	// TEST formations 
	
	@Test
	public void switchFormationTest(){
		// test the function in Field
		// see that it only changes the template teams
		fail("Not yet implemented");
	}
	
	@Test
	public void cornerKickFormationTest(){
		// test location like in above test
		
		// find a way to test that we can have mirror abilities, that 
		//     setupCornerKick(true,true) == x,y mirror of setupCornerKick(true, false)
		//     setupCornerKick(false, true) and setupCornerKick(true, true)
		fail("Not yet implemented");
	}
	
	@Test
	public void setupFreeKickTest(){
		// this needs to have the location of the ball passed in.  That sets the radius.  
		//  We might need to add a few extra features that will let only the kicker be inside that radius
		// what comes to mind for me is testing that a moveToLocation() function properly returns true and false
		//    (I know, that involves extra functions we didn't foresee)
		
		// Test it works for both human and computer player kicker?  Both sides of the field
		
		//  Do we need a config file for this too???  I think we can setup the locations based upon who is kicking and
		//   where the ball is.  Kinda place the forwards as a "wall" and everyone else randomly in and nearby the penalty box
		// Of course, if on the other side of the field, we wouldn't have a wall because who makes a goal from across field???
		
		// Three tests:
		// 		radius checking (both placing and can-move-to)
		//		goal-potential (so, walls!)
		//      no-goal-potential
		
		
		fail("Not yet implemented");
	}
	
	// ----------------------------------------------------------------------------------------
	// TEST other
	
	@Test
	public void resetPlayersTest(){
		// Check that the teams are set to same LOCATION as the current template
		//  We don't want to be overwriting the Players, just changing the coords.
		fail("Not yet implemented");
	}
	
	// I cannot think of other things to test.  The setupFreeKickTest() will be the biggie...
	
	@Test
	public void test(){
		
		fail("Not yet implemented");
	}

}
