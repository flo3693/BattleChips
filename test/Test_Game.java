import battlechips.Board;
import battlechips.Chip;
import battlechips.Game;
import battlechips.State;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test_Game {

	@Before
	public void setUp() throws Exception {
            Game.createNewBoard();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_readCommand() {
            assertEquals(true, Game.readCommand("R")); // on test si readCommand renvoie bien true            
            Board ancientBoard = Game.currentBoard;
            boolean different=false;
            Game.readCommand("R");// reset du Board
            for(int i=0;i<5;i++){// test si les coordonnées des chips sont différentes entre avant et après le restart
                Chip chip = ancientBoard.getChips()[i];
                if(chip.getRow() != Game.currentBoard.getChips()[i].getRow() || 
                        chip.getCol() != Game.currentBoard.getChips()[i].getCol()){// si c'est différent, on passe le booleen à true
                    different = true;
                    break;
                }
            }
            assertEquals(true, different); // on test si readCommand a bien reset le Board
               
            boolean hitten = false;
            assertEquals(true, Game.readCommand("A1"));// test si le tir sur une case correct renvoie true
            if(Game.currentBoard.getSea()[0][0] == State.HIT || Game.currentBoard.getSea()[0][0] == State.WATER)
                hitten = true;
            assertEquals(true, hitten);// test si la case s'est bien mis à jour
            
            assertEquals(false, Game.readCommand("A1"));// test si le tir sur une case déjà choisie renvoie false
            
            assertEquals(false, Game.readCommand("A11"));// test si le tir sur une case incorrect renvoie false
            
            assertEquals(false, Game.readCommand("bhfsg"));// test si une mauvaise commande renvoie false
            assertEquals(true, Game.readCommand("C7"));
    		assertEquals(false, Game.readCommand("K5"));
    		assertEquals(false, Game.readCommand("B12"));
    		assertEquals(false, Game.readCommand("Y76"));
	}

}
