
import battlechips.Chip;
import battlechips.Game;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.Test;

public class Test_Board {
    
    public Test_Board() {        
        Game.createNewBoard();
        
    }
    
    @Before
    public void setUp(){       
    }
    
    @After
    public void tearDown(){
        
    }

    @Test
    public void test_initChips(){
        for(int i=0; i<4; i++){
            for(int j=i+1;j<5;j++){                
                Assert.assertEquals(false,Game.currentBoard.collision(Game.currentBoard.getChips()[i].getCol(),
                        Game.currentBoard.getChips()[i].getRow(), Game.currentBoard.getChips()[i].getDirection(), 
                        Game.currentBoard.getChips()[i].getSize(), Game.currentBoard.getChips()[j]));
                        // vérification qu'aucun bateau n'entre pas en collision avec un autre
            }
        }
    }

    @Test
    public void test_fire(){
        Game.currentBoard.getChips()[0].setCoordinates(0, 0);
        Game.currentBoard.getChips()[0].setDirection(0);
        Game.currentBoard.getChips()[1].setCoordinates(4, 0);
        Game.currentBoard.getChips()[1].setDirection(0);
        Game.currentBoard.getChips()[2].setCoordinates(0, 2);
        Game.currentBoard.getChips()[2].setDirection(1);
        Game.currentBoard.getChips()[3].setCoordinates(9, 5);
        Game.currentBoard.getChips()[3].setDirection(1);
        Game.currentBoard.getChips()[4].setCoordinates(0, 6);
        Game.currentBoard.getChips()[4].setDirection(0);
        Game.currentBoard.fire(1,1);
        Assert.assertEquals(Game.currentBoard.fire(1,1),false);// tir sur une case déjà tirée
        Assert.assertEquals(Game.currentBoard.fire(6,2),true);// tir sur un bateau horizontal
        Assert.assertEquals(Game.currentBoard.fire(7,9),true);// tir sur un bateau vertical
        Assert.assertEquals(Game.currentBoard.fire(9,0),true);// tir dans l'eau
    }
    
    @Test
    public void test_fire_functionnal(){
    	assertEquals(true, Game.currentBoard.fire(1, 3));
    	assertEquals(false, Game.currentBoard.fire(1, 3));
    }
    
	@Test
	public void test_collision() {
		Chip chip = new Chip(5);
		chip.setCoordinates(2, 1);
		chip.setDirection(0);
		Assert.assertEquals(false, Game.currentBoard.collision(4, 2, 0, 2, chip));
		Assert.assertEquals(false, Game.currentBoard.collision(7, 1, 0, 2, chip));
		Assert.assertEquals(false, Game.currentBoard.collision(0, 1, 0, 2, chip));
		Assert.assertEquals(true, Game.currentBoard.collision(1, 1, 0, 2, chip));
		chip.setCoordinates(2, 2);
		Assert.assertEquals(false, Game.currentBoard.collision(3, 3, 1, 2, chip));
		Assert.assertEquals(false, Game.currentBoard.collision(3, 0, 1, 2, chip));
		Assert.assertEquals(false, Game.currentBoard.collision(1, 1, 1, 2, chip));
		Assert.assertEquals(false, Game.currentBoard.collision(7, 1, 1, 2, chip));
		Assert.assertEquals(true, Game.currentBoard.collision(4, 1, 1, 2, chip));
		chip = new Chip(3);
		chip.setCoordinates(5, 6);
		chip.setDirection(1);
		Assert.assertEquals(false, Game.currentBoard.collision(6, 7, 0, 4, chip));
		Assert.assertEquals(false, Game.currentBoard.collision(1, 7, 0, 4, chip));
		Assert.assertEquals(false, Game.currentBoard.collision(4, 5, 0, 4, chip));
		Assert.assertEquals(false, Game.currentBoard.collision(4, 9, 0, 4, chip));
		Assert.assertEquals(true, Game.currentBoard.collision(4, 7, 0, 4, chip));
		chip.setCoordinates(5, 4);
		Assert.assertEquals(false, Game.currentBoard.collision(4, 3, 1, 3, chip));
		Assert.assertEquals(false, Game.currentBoard.collision(5, 7, 1, 3, chip));
		Assert.assertEquals(false, Game.currentBoard.collision(5, 1, 1, 3, chip));
		Assert.assertEquals(true, Game.currentBoard.collision(5, 5, 1, 3, chip));
	}
	
    @Test
    public void test_collision_functionnal(){
    	Chip chip = new Chip(4);
    	chip.setCoordinates(1, 1);
    	chip.setDirection(0);
    	assertEquals(true, Game.currentBoard.collision(2, 0, 1, 3, chip));
    	assertEquals(false, Game.currentBoard.collision(2, 0, 0, 3, chip));
    	chip = null;
    }
}
