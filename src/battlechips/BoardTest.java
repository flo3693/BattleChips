package battlechips;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	protected Board board;
    
    @Before
    public void setUp(){
        board = new Board();
    }
    
    @After
    public void tearUp(){
        board = null;
    }

    @Test
    public void test_initChips(){

    }

    @Test
    public void test_fire() throws Exception{
    	assertEquals(true, board.fire(1, 3));
    	assertEquals(false, board.fire(1, 3));
    }

    @Test
    public void test_collision(){
    	Chip chip = new Chip(4);
    	chip.setCoordinates(1, 1);
    	chip.setDirection(0);
    	assertEquals(true, board.collision(2, 0, 1, 3, chip));
    	assertEquals(false, board.collision(2, 0, 0, 3, chip));
    	chip = null;
    }
}
