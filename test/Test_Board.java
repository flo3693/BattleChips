

import battlechips.Board;
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
    public void tearUp(){
        
    }

    @Test
    public void test_initChips(){
        for(int i=0; i<4; i++){
            for(int j=i+1;j<5;j++){                
                Assert.assertEquals(false,Game.currentBoard.collision(Game.currentBoard.getChips()[i].getRow(),Game.currentBoard.getChips()[i].getCol(),
                        Game.currentBoard.getChips()[i].getDirection(), Game.currentBoard.getChips()[i].getSize(),
                        Game.currentBoard.getChips()[j]));// vérification qu'aucun bateau n'entre en collision avec un autre
            }
        }
    }

    @Test
    public void test_fire(){
        Game.currentBoard.getChips()[0].setCoordonates(0, 0);
        Game.currentBoard.getChips()[0].setDirection(0);
        Game.currentBoard.getChips()[1].setCoordonates(0, 4);
        Game.currentBoard.getChips()[1].setDirection(0);
        Game.currentBoard.getChips()[2].setCoordonates(2, 0);
        Game.currentBoard.getChips()[2].setDirection(1);
        Game.currentBoard.getChips()[3].setCoordonates(5, 9);
        Game.currentBoard.getChips()[3].setDirection(1);
        Game.currentBoard.getChips()[4].setCoordonates(6, 0);
        Game.currentBoard.getChips()[4].setDirection(0);
        Game.currentBoard.fire(1,1);
        Assert.assertEquals(Game.currentBoard.fire(1,1),false);// tir sur une case déjà tirée
        Assert.assertEquals(Game.currentBoard.fire(6,2),true);// tir sur un bateau horizontal
        Assert.assertEquals(Game.currentBoard.fire(7,9),true);// tir sur un bateau vertical
        Assert.assertEquals(Game.currentBoard.fire(9,0),true);// tir dans l'eau
    }

    @Test
    public void test_collision(){
        Game.currentBoard.getChips()[0].setCoordonates(0, 0);
        Game.currentBoard.getChips()[0].setDirection(0);
        Game.currentBoard.getChips()[1].setCoordonates(0, 4);
        Game.currentBoard.getChips()[1].setDirection(0);
        Game.currentBoard.getChips()[2].setCoordonates(2, 0);
        Game.currentBoard.getChips()[2].setDirection(1);
        Game.currentBoard.getChips()[3].setCoordonates(5, 9);
        Game.currentBoard.getChips()[3].setDirection(1);
        Game.currentBoard.getChips()[4].setCoordonates(6, 0);
        Game.currentBoard.getChips()[4].setDirection(0);
        Assert.assertEquals(true, Game.currentBoard.collision(0, 0, 1, 3, Game.currentBoard.getChips()[0]));// collision
        Assert.assertEquals(false, Game.currentBoard.collision(0, 0, 1, 3, Game.currentBoard.getChips()[3]));// pas de collision
    }
    
    @Test
    public void test_readCommand(){
        
    }

}
