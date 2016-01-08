/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlechips;

import java.util.Scanner;

/**
 *
 * @author Florian
 */
public class BattleChips {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome in BATTLECHIPS !!!!!!");
        Game currentGame = new Game();
        Scanner sc = new Scanner(System.in);
        String command;
        System.out.println("Please entre a command between quit(q), restart(r) and the coordinates of a cell (letterdigit).");
        while(!currentGame.currentBoard.isFinished()){
            currentGame.currentBoard.display();
            System.out.println("Please enter a command : ");
            command = sc.nextLine();
            currentGame.readCommand(command);
        }
            
    }
    
}
