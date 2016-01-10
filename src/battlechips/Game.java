/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlechips;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

/**
 *
 * @author Florian
 */
public class Game {
    
    public static boolean quit = false;
    public static Board currentBoard;
    
    public static void createNewBoard(){
    	currentBoard = new Board();
    }
    
    @SuppressWarnings("resource")
	public static void readCommand(String command){                    
    	Scanner sc = new Scanner(System.in);
        command = command.toUpperCase();
        boolean commandOK = false;
        while(!commandOK){
            if(command.equals("Q") || command.equals("QUIT")){
                System.out.println("END OF THE GAME !");
                System.exit(0);
            }
            else if(command.equals("R") || command.equals("RESTART")){
                System.out.println("NEW GAME ...");
                createNewBoard();
                break;
            }
            else {
                Pattern pattern = Pattern.compile("^[A-J]([1-9]|10)$");
                Matcher matcher = pattern.matcher(command);            
                if(!matcher.find()){
                    System.out.println("Please enter a valid command in the format LetterDigit :");
                    command = sc.nextLine(); 
                    command = command.toUpperCase();
                }
                else{
                    ArrayList<String> letters = new ArrayList<String>();
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
                    int col = letters.indexOf(command.substring(0,1));
                    currentBoard.fire(Integer.parseInt(command.substring(1))-1,col);
                    commandOK = true;
                }
            }
        }
    }       
}
