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
    }
    
    private boolean initChip(int size){
        return false;
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
    
}
