

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
        
    }

    @Test
    public void testFire(){
        Game.currentBoard.getChips()[0].setCoordonates(0, 0);
        Game.currentBoard.getChips()[0].setDirection(0);
        Game.currentBoard.fire(1,1);
        Assert.assertEquals(Game.currentBoard.fire(1,1),false);// tir sur une case déjà tirée
    }

    @Test
    public void test_collision(){

    }
    
    @Test
    public void test_readCommand(){
        
    }

}
