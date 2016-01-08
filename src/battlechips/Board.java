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
        //Random r = new Random();
    	System.out.println("I start initship");
        int x = 0; // + (int)(Math.random()*5);
        int y = 0; // + (int)(Math.random()*5);
    	
        chips[0].setCoordonates(x, y);
        int i = 1;
        boolean collisionDetected = false;
        System.out.println("right before the loop");
        while(i<5){
        	x = 0 + (int)(Math.random()*5); 
        	y = 0 + (int)(Math.random()*5);
        	System.out.println(x);
        	System.out.println(y);
            for(int j=0;j<i;j++){
                if(collision(x, y, chips[i], chips[j])){
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
    
    public boolean isFinished(){
        for(int i=0;i<5;i++)
        {
            if(!chips[i].isSunk())
                return false;
        }
        return true;
    }
    
    public void display(){
    	
    	System.out.println(" A   B   C   D   E   F   G   H   I   J");
        System.out.println(" ---------------------------------------");
        for(int i=0;i<10; i++){
            
            //System.out.print("|   |   |   |   |   |   |   |   |   |   |");
            //System.out.print("| ");
            for(int j=0;j<10;j++){
            	System.out.print(" | ");
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
                else{
                	if(y>=c.y && y<=(c.y+c.size)){// we hit a chip
                		sea[x][y]=State.HIT;
                		c.hit[y-c.y] = true;// update the cell of the boat because it was hitten (y-c.y will return the index of the hitten cell)
                	}
                	else{
                    sea[x][y]=State.WATER;
                	}
                }
            }
            else{
                if(y!=c.y){// if they don't have the same y, it means the fire is gone into water because they're not on the same column
                    sea[x][y]=State.WATER;
                }
                else{
	                if(x>=c.x && x<=(c.x+c.size)){// we hit a chip
	                    sea[x][y]=State.HIT;
	                    c.hit[x-c.x] = true;// update the cell of the boat because it was hitten (x-c.x will return the index of the hitten cell)
	                }
	                else{
	                    sea[x][y]=State.WATER;
	                }
                }
            }
        }
        return true;
    }
    
    public boolean collision(int x, int y, Chip chipToPlace, Chip chipAlreadyPlaced){
        if(chipAlreadyPlaced.direction==0){//horizontal chipAlreadyPlaced
            if(chipToPlace.direction==0){//horizontal chipToPlace
                if(x!=chipAlreadyPlaced.x)// if they are not on the same line : no collision
                    return false;
                else{// they are on the same line
                    if(y>=chipAlreadyPlaced.y && y<=(chipAlreadyPlaced.y+chipAlreadyPlaced.size))// y is in the chip : collision
                        return true;
                    else// y not in the ship : no collision
                        return false;
                }
            }
            else{//vertical chipToPlace
                if(chipAlreadyPlaced.x>=x && chipAlreadyPlaced.x<=(x+chipToPlace.size))// x is inside the possible x of the chip already placed
                    if(chipAlreadyPlaced.y>=y && y<=(y+chipAlreadyPlaced.size))// and they have the same y : collision
                        return true;
                    else // and they don't share the same y : no collision
                        return false;
                else // x is not inside the possible x of the chip already placed : no collison
                    return false;
            }
        }
        else{//vertical chipAlreadyPlaced
            if(chipToPlace.direction==0){//horizontal chipToPlace
                if(chipAlreadyPlaced.y>=y && chipAlreadyPlaced.y<=(y+chipToPlace.size))// y is inside the possible y of the chip already placed
                    if(chipAlreadyPlaced.x>=x && x<=(x+chipAlreadyPlaced.size))// and they have the same x : collision
                        return true;
                    else // and they don't share the same x : no collision
                        return false;
                else // y is not inside the possible y of the chip already placed : no collison
                    return false;
            }
            else{//vertical chipToPlace
                if(y!=chipAlreadyPlaced.y)// if they are not on the same column : no collision
                    return false;
                else{// they are on the same line
                    if(x>=chipAlreadyPlaced.x && x<=(chipAlreadyPlaced.x+chipAlreadyPlaced.size))// x is in the chip : collision
                        return true;
                    else// x not in the ship : no collision
                        return false;
                }
            }            
        }
    }
    
}
