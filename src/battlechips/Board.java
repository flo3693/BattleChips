/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlechips;

import java.util.Random;

/**
 *
 * @author Florian
 */
public class Board {
    
    Chip[] chips;
    State[][] sea;
    int nbFires;
    
    public Board(){
        chips = new Chip[5];
        chips[0] = new Chip(2);
        chips[1] = new Chip(3);
        chips[2] = new Chip(3);
        chips[3] = new Chip(4);
        chips[4] = new Chip(5);
        
        sea = new State[10][10];
        for(int i=0;i<10;i++){
            for(int j=0; j<10; j++){
                sea[i][j] = State.UNKNOWN;
            }
        }
        
        nbFires = 0;
        
        initChips();
    }
    
    private void initChips(){
        Random r = new Random();
    	System.out.println("I start initship");
        int row = ((chips[0].direction==0)?1+r.nextInt(9-chips[0].size):1+r.nextInt(9));
        int col = ((chips[0].direction==1)?1+r.nextInt(9-chips[0].size):1+r.nextInt(9));
    	
        chips[0].setCoordonates(row, col);
        int i = 1;
        boolean collisionDetected = false;
        System.out.println("right before the loop");
        while(i<5){
            row = ((chips[i].direction==0)?1+r.nextInt(9-chips[i].size):1+r.nextInt(9)); 
            col = ((chips[i].direction==1)?1+r.nextInt(9-chips[i].size):1+r.nextInt(9));
            for(int j=0;j<i;j++){
                System.out.println("collision in");
                if(collision(row, col, chips[i].getDirection(), chips[i].getSize(), chips[j])){
                    collisionDetected = true;
                    break;
                }
                System.out.println("collision out");
            }            
            if(!collisionDetected){
                chips[i].setCoordonates(row, col);
                i++;
            }
        }
    }
    
    /**
     * Returns whether the game board is finished.
     * @return true if the game board is finished.
     */
    public boolean isFinished(){
        for(int i=0;i<5;i++)
        {
            if(!chips[i].isSunk())
                return false;
        }
        return true;
    }
    
    public void display(){
    	
    	System.out.println("  A   B   C   D   E   F   G   H   I   J");
        System.out.println(" ---------------------------------------");
        for(int i=0;i<10; i++){
            
            //System.out.print("|   |   |   |   |   |   |   |   |   |   |");
            System.out.print("| ");
            for(int j=0;j<10;j++){
            	//System.out.print(" | ");
                if(sea[i][j]==State.HIT)
                    System.out.print("X");
                else if(sea[i][j]==State.UNKNOWN)
                    System.out.print("-");
                else
                    System.out.print("O");
                System.out.print(" | ");
            }
            System.out.println(i+1);
        }
        System.out.println(" ---------------------------------------");        
    }
    
    /**
     * @param x
     * @param y
     * @return
     */
    public boolean fire(int row, int col){
        if(sea[row][col]!=State.UNKNOWN){
            System.out.println("You already fired this cell. Try another one !");
            return false;
        }
        
        for(Chip c : chips){
            if(c.direction==0){// if the chip is horizontally placed
                if(row!=c.row){// if they don't have the same x, it means the fire is gone into water because they're not on the same line
                    sea[row][col]=State.WATER;
                }
                else{
                	if(col>=c.col && col<=(c.col+c.size)){// we hit a chip
                		sea[row][col]=State.HIT;
                		c.hit[col-c.col] = true;// update the cell of the boat because it was hitten (y-c.y will return the index of the hitten cell)
                	}
                	else{
                    sea[row][col]=State.WATER;
                	}
                }
            }
            else{
                if(col!=c.col){// if they don't have the same y, it means the fire is gone into water because they're not on the same column
                    sea[row][col]=State.WATER;
                }
                else{
	                if(row>=c.row && row<=(c.row+c.size)){// we hit a chip
	                    sea[row][col]=State.HIT;
	                    c.hit[row-c.row] = true;// update the cell of the boat because it was hitten (x-c.x will return the index of the hitten cell)
	                }
	                else{
	                    sea[row][col]=State.WATER;
	                }
                }
            }
        }
        return true;
    }
    
    /**
     * Returns whether an object collides a "chip".
     * @param col The column index of the object.
     * @param row The row index of the object.
     * @param direction The direction of the object.
     * @param size The size of the object.
     * @param chip The "chip" to collide.
     * @return true if the object collides the "chip".
     */
    public boolean collision(int col, int row, int direction, int size, Chip chip) {
    	boolean collide = false;
    	if (chip.direction == 0) {
    		if (direction == 0) {
    			collide = row == chip.row &&
    					col < chip.col + chip.size && col + size > chip.col;
    		} else if (direction == 1) {
    			collide = row <= chip.row && row + size > chip.row &&
    					col >= chip.col && col < chip.col + chip.size;
    		}
    	} else if (chip.direction == 1) {
    		if (direction == 0) {
    			collide = col <= chip.col && col + size > chip.col &&
    					row >= chip.row && row < chip.row + chip.size;
    		} else if (direction == 1) {
    			collide = col == chip.col &&
    					row < chip.row + chip.size && row + size > chip.row;
    		}
    	}
    	return collide;
    }
    
}
