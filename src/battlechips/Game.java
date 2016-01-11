/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlechips;

import java.util.ArrayList;
import java.util.regex.*;

/**
 *
 * @author Florian
 */
public class Game {
	
    public static ArrayList<String> letters = new ArrayList<String>();
    public static boolean quit = false;
    public static Board currentBoard;
    
    public static Board getCurrentBoard() {
        return currentBoard;
    }

    public static void setCurrentBoard(Board currentBoard) {
        Game.currentBoard = currentBoard;
    }

    public static boolean isQuit() {
        return quit;
    }

    public static void setQuit(boolean quit) {
        Game.quit = quit;
    }
    
    public Game(){
        currentBoard = new Board();
    }
    
    public static void createNewBoard(){
    	currentBoard = new Board();
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");
        letters.add("F");
        letters.add("G");
        letters.add("H");
        letters.add("I");
        letters.add("J");
    }
    
	public static boolean readCommand(String command){     
        command = command.toUpperCase();
        if(command.equals("Q") || command.equals("QUIT")){
            System.out.println("END OF THE GAME !");
            System.exit(0);
        }
        else if(command.equals("R") || command.equals("RESTART")){
            System.out.println("NEW GAME ...");
            createNewBoard();
            return true;
        }
        else {
            Pattern pattern = Pattern.compile("^[A-J]([1-9]|10)$");
            Matcher matcher = pattern.matcher(command);   
            int col = letters.indexOf(command.substring(0,1));
            if(!matcher.find() ||
            		!currentBoard.fire(Integer.parseInt(command.substring(1))-1,col)){
               return false;
            }
            else{
                currentBoard.nbFires++;
                return true;
            }
        }
        return true;
    }       
}
