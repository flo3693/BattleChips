/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlechips;

import java.util.Scanner;
import java.util.regex.*;

/**
 *
 * @author Florian
 */
public class Game {
    
    static Board currentBoard;
    static boolean quit = false;
    
    public static void createNewBoard(){
    	int x,y;
    	System.out.println("welcome");
    	currentBoard = new Board();
    	System.out.println("board initiated");
    	currentBoard.display();
    	while(!currentBoard.isFinished()){
            /*x = 0 + (int)(Math.random()*10); 
            y = 0 + (int)(Math.random()*10);
            System.out.println(x);
            System.out.println(y);
            currentBoard.fire(x, y);
            currentBoard.display();*/
    	}
    	System.out.println("game finished !");
    
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
                if(command.length()>2 || !matcher.find()){
                    System.out.println("Veuillez rentrer une commande valide au format lettre-chiffre :");
                    command = sc.nextLine();                    
                }
                else{
                    currentBoard.fire(Integer.parseInt(command.substring(0,1)),Integer.parseInt(command.substring(1)));
                    break;
                }
            }
        }
    }
    
    
}
