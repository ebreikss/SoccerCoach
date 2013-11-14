package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import fieldFiles.Field;
import fieldFiles.Formation;

public class FieldTests {
	
	static Field field;
	static Formation formation;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		field = new Field(startFormFile, cornerFile);
		field.getFormation().loadConfigFiles(startFormFile, cornerFile);
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
		
		// check the template Players. Check some of their locations, colors, positions, etc (not *all* is needed)
		fail("Not yet implemented");
	}
	
	// ----------------------------------------------------------------------------------------
	// TEST runSimulation() 
	
	@Test
	public void humanPlayerMoveTest(){
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
