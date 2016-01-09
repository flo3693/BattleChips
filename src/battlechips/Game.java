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
    
    static Board currentBoard;
    static boolean quit = false;
    
    public Game(){
        currentBoard = new Board();
    }
    
    public static void createNewBoard(){
    	currentBoard = new Board();
    }
    
    public static void readCommand(String command){
        Scanner sc = new Scanner(System.in);
        command.toUpperCase();
        if(command == "Q" || command == "QUIT"){
            System.exit(0);
        }
        else if(command == "R" || command=="RESTART"){
            createNewBoard();
        }
        else {
            Pattern pattern = Pattern.compile("[A-J][10|[1-9]]");
            Matcher matcher = pattern.matcher(command);
            while(true){
                if(command.length()<2 || !matcher.find()){
                    System.out.println("Please enter a valid command in the format LetterDigit :");
                    command = sc.nextLine(); 
                    command.toUpperCase();
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
                    break;
                }
            }
        }
    }
       
}
