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

    public Chip[] getChips() {
        return chips;
    }

    public void setChips(Chip[] chips) {
        this.chips = chips;
    }

    public State[][] getSea() {
        return sea;
    }

    public void setSea(State[][] sea) {
        this.sea = sea;
    }

    public int getNbFires() {
        return nbFires;
    }

    public void setNbFires(int nbFires) {
        this.nbFires = nbFires;
    }
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
        int i = 0;
        boolean collisionDetected;
        while(i < 5){
            collisionDetected = false;
            int col = ((chips[i].direction==0)?r.nextInt(9-chips[i].size):r.nextInt(9)); 
            int row = ((chips[i].direction==1)?r.nextInt(9-chips[i].size):r.nextInt(9));
            for(int j=0 ; j<i && !collisionDetected; j++){
                if(collision(col, row, chips[i].getDirection(), chips[i].getSize(), chips[j]))
                    collisionDetected = true;
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
    
    public void display1(){
    	
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
    
    /*private Chip _TEMP_collisionAt(int col, int row) {
    	for (int i = 0, len = chips.length; i < len; i++) {
    		Chip chip = chips[i];
    		if (chips[i].getDirection() == 0 && row == chip.getRow()) {
    			if (col >= chip.getCol() && col < chip.getCol() + chip.getSize()) {
    				return chip;
    			}
    		} else if (chips[i].getDirection() == 1 && col == chips[i].getCol()) {
    			if (row >= chip.getRow() && row < chip.getRow() + chip.getSize()) {
    				return chip;
    			}
    		}
    	}
    	return null;
    }*/
    
    public void display() {
    	/*for (int i = 0; i < 5; i++) {
    		Chip c = chips[i];
    		System.out.println("(" + c.col + "," + c.row + ") " + c.direction + " / " +
    				c.size);
    	}*/
    	String line;
    	System.out.println();
    	System.out.println("  `  A  B  C  D  E  F  G  H  I  J");
    	for (int i = 0; i < 10; i++) {
    		line = (i == 9 ? " " : "  ") + (i + 1) + " ";
    		for (int j = 0; j < 10; j++) {
    			if (sea[i][j] == State.WATER) {
    				line += " o ";
    			} else if (sea[i][j] == State.HIT) {
    				line += " x ";
    			} else {
    				line += " . ";
    			}
    			/*Chip chip = _TEMP_collisionAt(j, i);
    			if (chip != null) {
    				line += chip.direction == 0 ? " - " : " | ";
    			} else {
    				line += "   ";
    			}*/
    		}
    		System.out.println(line);
    	}
    	System.out.println();
    }
    
    /** Fires at the position specified via row and col
     * @param row
     * @param col
     * @return true if the fire hit a cell never hitten before
     */
    public boolean fire(int row, int col){
        if(sea[row][col]!=State.UNKNOWN)
            return false;
        for(Chip c : chips){
            if(c.direction==0){// if the chip is horizontally placed
                if(row==c.row){// if the fire is on the same line as the chip
                    if(col>=c.col && col<(c.col+c.size)){// we hit a chip
                        sea[row][col]=State.HIT;// update the cell of the boat because it was hitten (col-c.col will return the index of the hitten cell)
                        c.hit[(c.col + c.size) - col - 1] = true;
                        System.out.println("HIT !");
                        return true;
                    }
                }
            }
            else{
                if(col==c.col){// if the fire is on the same column as the chip
                    if(row>=c.row && row<(c.row+c.size)){// we hit a chip
                        sea[row][col]=State.HIT;// update the cell of the boat because it was hitten (row-c.row will return the index of the hitten cell)                          
                        c.hit[(c.row + c.size) - row - 1] = true;
                        System.out.println("HIT !");
                        return true;
                    }
                }
            }
        }
        sea[row][col]=State.WATER;
        System.out.println("IN THE WATER !");
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
    	// TODO: use getters!
    	boolean collide = false;
    	if (chip.getDirection() == 0) {
    		if (direction == 0) {
    			collide = row == chip.row &&
    					col < chip.col + chip.getSize() && col + size > chip.col;
    		} else if (direction == 1) {
    			collide = row <= chip.row && row + size > chip.row &&
    					col >= chip.col && col < chip.col + chip.getSize();
    		}
    	} else if (chip.getDirection() == 1) {
    		if (direction == 0) {
    			collide = col <= chip.col && col + size > chip.col &&
    					row >= chip.row && row < chip.row + chip.getSize();
    		} else if (direction == 1) {
    			collide = col == chip.col &&
    					row < chip.row + chip.getSize() && row + size > chip.row;
    		}
    	}
    	return collide;
    }
    
}
