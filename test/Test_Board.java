

import battlechips.Board;
import battlechips.Chip;
import battlechips.Game;
import battlechips.State;
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
    public void tearUp(){
        
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
        Game.currentBoard.getChips()[1].setCoordinates(0, 4);
        Game.currentBoard.getChips()[1].setDirection(0);
        Game.currentBoard.getChips()[2].setCoordinates(2, 0);
        Game.currentBoard.getChips()[2].setDirection(1);
        Game.currentBoard.getChips()[3].setCoordinates(5, 9);
        Game.currentBoard.getChips()[3].setDirection(1);
        Game.currentBoard.getChips()[4].setCoordinates(6, 0);
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
		Assert.assertEquals(false, Game.currentBoard.collision(7, 2, 0, 2, chip));
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
		Assert.assertEquals(false, Game.currentBoard.collision(4, 3, 1, 4, chip));
		Assert.assertEquals(false, Game.currentBoard.collision(5, 7, 1, 4, chip));
		Assert.assertEquals(false, Game.currentBoard.collision(5, 0, 1, 4, chip));
		Assert.assertEquals(true, Game.currentBoard.collision(5, 5, 1, 4, chip));
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
    
    @Test
    public void test_readCommand(){
        Board ancientBoard = Game.currentBoard;
        boolean different=false, hitten=false, changement=false;
        Game.readCommand("R");// test si restart marche bien
        for(int i=0;i<5;i++){// test si les coordonnées des chips sont différent entre avant et après le restart
            Chip chip = ancientBoard.getChips()[i];
            if(chip.getRow() != Game.currentBoard.getChips()[i].getRow() || 
                    chip.getCol() != Game.currentBoard.getChips()[i].getCol()){// si c'est différent, on passe le booleen à true
                different = true;
                break;
            }
        }
        assertEquals(true, different);
        
        Game.readCommand("A1");// test si le tir sur une case correct marche bien
        if(Game.currentBoard.getSea()[0][0] == State.HIT || Game.currentBoard.getSea()[0][0] == State.WATER)
            hitten = true;
        assertEquals(true, hitten);
        
       /* ancientBoard = Game.currentBoard;
        Game.readCommand("A1G");// test si le tir sur une case incorrect ne marche pas en comparant l'état de la sea
        for(int i=0;i<10;i++){
            for(int j=0; j<10;j++){
                if(Game.currentBoard.getSea()[i][j] != ancientBoard.getSea()[i][j])
                    changement = true;
            }            
        }
        
        assertEquals(false, changement);*/
        
    }

}
