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
                if(collision(row, col, chips[i].direction, chips[i].size, chips[j])){
                    collisionDetected = true;
                    break;
                }
            }            
            if(!collisionDetected){
                chips[i].setCoordonates(row, col);
                i++;
            }
        }
    }
    
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
    
    
    public boolean collision(int row, int col, int direction, int size, Chip chipAlreadyPlaced){
        if(chipAlreadyPlaced.direction==0){//horizontal chipAlreadyPlaced
            if(direction==0){//horizontal chipToPlace
                if(row!=chipAlreadyPlaced.row)// if they are not on the same line : no collision
                    return false;
                else{// they are on the same line
                    if(col>=chipAlreadyPlaced.col && col<=(chipAlreadyPlaced.col+chipAlreadyPlaced.size))// y is in the chip : collision
                        return true;
                    else// y not in the ship : no collision
                        return false;
                }
            }
            else{//vertical chipToPlace
                if(chipAlreadyPlaced.row>=row && chipAlreadyPlaced.row<=(row+size))// x is inside the possible x of the chip already placed
                    if(chipAlreadyPlaced.col>=col && col<=(col+chipAlreadyPlaced.size))// and they have the same y : collision
                        return true;
                    else // and they don't share the same y : no collision
                        return false;
                else // x is not inside the possible x of the chip already placed : no collison
                    return false;
            }
        }
        else{//vertical chipAlreadyPlaced
            if(direction==0){//horizontal chipToPlace
                if(chipAlreadyPlaced.col>=col && chipAlreadyPlaced.col<=(col+size))// y is inside the possible y of the chip already placed
                    if(chipAlreadyPlaced.row>=row && row<=(row+chipAlreadyPlaced.size))// and they have the same x : collision
                        return true;
                    else // and they don't share the same x : no collision
                        return false;
                else // y is not inside the possible y of the chip already placed : no collison
                    return false;
            }
            else{//vertical chipToPlace
                if(col!=chipAlreadyPlaced.col)// if they are not on the same column : no collision
                    return false;
                else{// they are on the same line
                    if(row>=chipAlreadyPlaced.row && row<=(chipAlreadyPlaced.row+chipAlreadyPlaced.size))// x is in the chip : collision
                        return true;
                    else// x not in the ship : no collision
                        return false;
                }
            }            
        }
    }
    
}
