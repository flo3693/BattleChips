/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlechips;

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
        if(command.length()>2)
            System.out.println("Veuillez rentrer une commande valide");
        else{
            command.toUpperCase();
            if(command == "Q"){
                
            }
           
        }
    }
    
    
}
