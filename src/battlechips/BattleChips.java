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
		@SuppressWarnings("resource")
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
			while(!Game.readCommand(command))
			{
				System.out.println("Please enter a valid command in the format LetterDigit :");
				command = sc.nextLine(); 
				command = command.toUpperCase();
			}
		}
		Game.currentBoard.display();
		System.out.println("YOU WON !");
		System.out.println("Final score (number of hits) : "+Game.currentBoard.nbFires);
		int precision = (int) ( Math.round(((17.0/Game.currentBoard.nbFires)*100.0)));
		System.out.println("Percentage of precision : "+precision+"%");
	}
}
