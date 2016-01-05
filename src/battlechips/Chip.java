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
public class Chip {
    int x;
    int y;
    int direction;
    int size;
    boolean[] hit;
    
    public Chip(int n){
        
    }
    
    public boolean isSunk(){
        for(int i=0; i<this.size;i++)
        {
            if(hit[i]==false)
                return false;
        }            
        return true;
    }   
    
}
