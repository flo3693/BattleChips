

import battlechips.Board;
import battlechips.Chip;
import battlechips.Game;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.Test;

public class Test_Board {
    Game game = new Game();
    
    public Test_Board() {
    }
    
    @Before
    public void setUp(){
        
    }
    
    @After
    public void tearUp(){
        
    }

    @Test
    private void test_initChips(){
        
    }

    @Test
    private void test_fire(){
        game.getCurrentBoard().fire(1,1);
        Assert.assertFalse(game.getCurrentBoard().fire(1,1));
    }

    @Test
    private void test_collision(){

    }
    
    @Test
    private void test_readCommand(){
        
    }

}
