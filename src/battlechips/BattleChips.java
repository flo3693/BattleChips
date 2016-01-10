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
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        System.out.println("Welcome in BATTLECHIPS !!!!!!");
        Scanner sc = new Scanner(System.in);
        Game.createNewBoard();
        String command;
        System.out.println("Please enter a command among quit(q), restart(r) and the coordinates of a cell (LetterDigit).");
        for(int i=0;i<Game.currentBoard.chips.length;i++){
            System.out.print(Game.currentBoard.chips[i].getCol()+" ");
            System.out.print(Game.currentBoard.chips[i].getRow()+" ");
            System.out.print(Game.currentBoard.chips[i].getDirection()+" ");
            System.out.println(Game.currentBoard.chips[i].getSize());
        }
        while(!Game.currentBoard.isFinished()){
            Game.currentBoard.display();
            System.out.println("Please enter a command : ");
            command = sc.nextLine();
            Game.readCommand(command);
        }
        Game.currentBoard.display();            
    }
    
}
