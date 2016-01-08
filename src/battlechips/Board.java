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
        
        initChip();
    }
    
    private void initChip(){
        Random r = new Random();
        int x = 1 + r.nextInt(9);
        int y = 1 + r.nextInt(9);
        chips[0].setCoordonates(x, y);
        int i = 1;
        boolean collisionDetected = false;
        while(i<5){
            x = 1 + r.nextInt(9);
            y = 1 + r.nextInt(9);
            for(int j=0;j<i;j++){
                if(collision(x, y, chips[i].getDirection(), chips[i].getSize(), chips[j])){
                    collisionDetected = true;
                    break;
                }
            }            
            if(!collisionDetected){
                chips[i].setCoordonates(x, y);
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
        for(int i=0;i<10; i++){
            System.out.println(" A   B   C   D   E   F   G   H   I   J");
            System.out.println(" ---------------------------------------");
            //System.out.print("|   |   |   |   |   |   |   |   |   |   |");
            System.out.print("| ");
            for(int j=0;j<10;j++){
                if(sea[i][j]==State.HIT)
                    System.out.print("X");
                else if(sea[i][j]==State.UNKNOWN)
                    System.out.print(" ");
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
    public boolean fire(int x, int y){
        if(sea[x][y]!=State.UNKNOWN){
            System.out.println("You already fired this cell. Try another one !");
            return false;
        }
        
        for(Chip c : chips){
            if(c.direction==0){// if the chip is horizontally placed
                if(x!=c.x){// if they don't have the same x, it means the fire is gone into water because they're not on the same line
                    sea[x][y]=State.WATER;
                }
                if(y>=c.y && y<=(c.y+c.size)){// we hit a chip
                    sea[x][y]=State.HIT;
                    c.hit[y-c.y] = true;// update the cell of the boat because it was hitten (y-c.y will return the index of the hitten cell)
                }
                else{
                    sea[x][y]=State.WATER;
                }
            }
            else{
                if(y!=c.y){// if they don't have the same y, it means the fire is gone into water because they're not on the same column
                    sea[x][y]=State.WATER;
                }
                if(x>=c.x && y<=(c.x+c.size)){// we hit a chip
                    sea[x][y]=State.HIT;
                    c.hit[x-c.x] = true;// update the cell of the boat because it was hitten (x-c.x will return the index of the hitten cell)
                }
                else{
                    sea[x][y]=State.WATER;
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
    	if (chip.direction == 0) {
    		if (direction == 0) {
    			return row == chip.row &&
    					col < chip.col + chip.size && col + size > chip.col;
    		} else if (direction == 1) {
    			return row <= chip.row && row + size > chip.row &&
    					col >= chip.col && col < chip.col + chip.size;
    		}
    	} else if (chip.direction == 1) {
    		if (direction == 0) {
    			return col <= chip.col && col + size > chip.col &&
    					row >= chip.row && row < chip.row + chip.size;
    		} else if (direction == 1) {
    			return col == chip.col &&
    					row < chip.row + chip.size && row + size > chip.row;
    		}
    	}
    }
    
}
