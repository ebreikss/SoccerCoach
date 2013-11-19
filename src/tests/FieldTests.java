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
		tempPlayers = (ArrayList<Player>) ((Formation) soccerField.getFormationList().get("Pyramid")).getTeamX();
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
		tempPlayers = (ArrayList<Player>) ((Formation) soccerField.getFormationList().get("Metodo")).getTeamX();
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
		playa.setComputer(false);
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
	}

	@Test
	public void computerPlayerMoveTest(){
		// given same stuff as above, only with a computer = true
		// test that based on velocity vector, moving towards one direction is favored over another in the end
		// loop the function like 100 times or something and count

		Player playa = new Player();
		playa.setxCoord(50);
		playa.setyCoord(50);
		playa.setDirectionAngle(0);
		playa.setVelocity(1);
		playa.setComputer(true);

		int movedRight = 0;
		int movedLeft = 0;
		int movedUp = 0;
		int movedDown = 0;

		for (int i = 0; i < 100; i++) {
			playa.setxCoord(50);
			playa.setyCoord(50);
			
			playa.move();
			if (playa.getxCoord() > 50)
				movedRight++;
			else
				movedLeft++;
			if (playa.getyCoord() > 50)
				movedDown++;
			else 
				movedUp++;
		}

		assertTrue(movedRight > movedLeft);
		assertTrue(movedDown > 15);
		assertTrue(movedUp > 15);

		playa.setDirectionAngle(90);
		playa.setVelocity(1);

		// Reset the counters
		movedRight = 0;
		movedLeft = 0;
		movedUp = 0;
		movedDown = 0;

		for (int i = 0; i < 100; i++) {
			playa.setxCoord(50);
			playa.setyCoord(50);
			
			playa.move();
			if (playa.getxCoord() > 50)
				movedRight++;
			else
				movedLeft++;
			if (playa.getyCoord() > 50)
				movedDown++;
			else 
				movedUp++;
		}

		assertTrue(movedDown < movedUp);
		assertTrue(movedRight > 15);
		assertTrue(movedLeft > 15);


		playa.setDirectionAngle(45);
		playa.setVelocity(2);
		
		// Reset the counters
		movedRight = 0;
		movedLeft = 0;
		movedUp = 0;
		movedDown = 0;

		for (int i = 0; i < 100; i++) {
			playa.setxCoord(50);
			playa.setyCoord(50);
			
			playa.move();
			if (playa.getxCoord() > 50)
				movedRight++;
			else
				movedLeft++;
			if (playa.getyCoord() > 50)
				movedDown++;
			else 
				movedUp++;
		}

		assertTrue(movedDown < movedUp);
		assertTrue(movedRight > movedLeft);


		playa.setxCoord(50);
		playa.setyCoord(50);
		playa.setDirectionAngle((Integer) null); // maybe a random negaitive would work? or a different function?
		playa.setVelocity(2);

		// Reset the counters
		movedRight = 0;
		movedLeft = 0;
		movedUp = 0;
		movedDown = 0;

		for (int i = 0; i < 100; i++) {
			playa.setxCoord(50);
			playa.setyCoord(50);
			
			playa.move();
			if (playa.getxCoord() > 50)
				movedRight++;
			else
				movedLeft++;
			if (playa.getyCoord() > 50)
				movedDown++;
			else 
				movedUp++;
		}

		assertTrue(movedDown > 15);
		assertTrue(movedUp > 15);
		assertTrue(movedRight > 15);
		assertTrue(movedLeft > 15);
		movedRight = 0;
		movedLeft = 0;
		movedUp = 0;
		movedDown = 0;
		playa.setDirectionAngle(135);

		for (int i = 0; i < 100; i++) {
			playa.setxCoord(50);
			playa.setyCoord(50);
			
			playa.move();
			if (playa.getxCoord() > 50)
				movedRight++;
			else
				movedLeft++;
			if (playa.getyCoord() > 50)
				movedDown++;
			else 
				movedUp++;
		}

		assertTrue(movedUp > movedDown);
		assertTrue(movedRight < movedLeft);


		// try to find both simple and complicated cases (such as a 'null' direction where they just do a random walk)
	}

	// ----------------------------------------------------------------------------------------
	// TEST formations 

	@Test
	public void switchFormationTest(){
		
		Formation humanFormation = (Formation) soccerField.getFormationList().get("Pyramid");
		soccerField.setHumanFormation(humanFormation);
		Formation compFormation = (Formation) soccerField.getFormationList().get("Metodo");
		soccerField.setCompFormation(compFormation);
		soccerField.switchFormation("Metodo", "Pyramid");
		Assert.assertTrue(soccerField.getHumanTeam().contains(soccerField.getFormationList().get("Metodo")));
		Assert.assertTrue(soccerField.getCompTeam().contains(soccerField.getFormationList().get("Pyramid")));
		
		
		// test the function in Field
		// see that it only changes the template teams
		// also test that Team Players get updated

	}

	@Test
	public void cornerKickFormationTest(){
		soccerField.setupCornerKick(true, true);
		ArrayList<Player> tempHplayers = soccerField.getHumanTeam();
		ArrayList<Player> tempCplayers = soccerField.getCompTeam();
		for(int i = 0; i < tempHplayers.size(); i++){
			
			Assert.assertTrue(tempHplayers.get(i).getxCoord() > 780);
			Assert.assertTrue(tempHplayers.get(i).getxCoord() < 900);
			Assert.assertTrue(tempHplayers.get(i).getyCoord() > 188);
			Assert.assertTrue(tempHplayers.get(i).getyCoord() < 488);
			Assert.assertTrue(tempCplayers.get(i).getxCoord() > 780);
			Assert.assertTrue(tempCplayers.get(i).getxCoord() < 900);
			Assert.assertTrue(tempCplayers.get(i).getyCoord() > 188);
			Assert.assertTrue(tempCplayers.get(i).getyCoord() < 488);
			
		}
		
		// test location like in above test

		// find a way to test that we can have mirror abilities, that 
		//     setupCornerKick(true,true) == x,y mirror of setupCornerKick(true, false)
		//     setupCornerKick(false, true) and setupCornerKick(true, true)
	}

	// took out freeKickTest because that will be better to look for in la GUI
	
	// ----------------------------------------------------------------------------------------
	// TEST other

	@Test
	public void resetPlayersTest(){
		// Check that the teams are set to same LOCATION as the current template
		//  We don't want to be overwriting the Players, just changing the coords.
		Formation template = (Formation) soccerField.getFormationList().get("Metodo");
		Formation actual = (Formation) soccerField.getFormationList().get("Metodo");
		ArrayList<Player> tempPlayers = actual.getTeamX();
		for(int i = 0; i < tempPlayers.size(); i++){
			tempPlayers.get(i).setxCoord(30);
			tempPlayers.get(i).setyCoord(25);
		}
		actual.resetPlayers();
		Assert.assertTrue(template.getTeamX().containsAll(actual.getTeamX()));	
	}
	// I cannot think of other things to test.  The setupFreeKickTest() will be the biggie...
}
