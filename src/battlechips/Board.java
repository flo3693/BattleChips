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
    int nbHits;
    
    public Board(){
        
    }
    
    private void initChip(int size){
        
    }
    
    public boolean isFinished(){
        return false;
    }
    
    public void display(){
        
    }
    
    public State fire(int x, int y){
        return State.UNKNOWN;
    }
    
}
