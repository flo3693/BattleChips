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
    int row;
    int col;
    int direction;// 0=> horizontal  1=> vertical
    int size;
    boolean[] hit;
    
    public Chip(int n){
        // génération d'un nombre aléatoire entre 0 et 100 puis modulo 2 pour obtenir 0 ou 1
        this.direction = ((int)( Math.random()*101))%2;
       
        this.size = n;
        
        hit = new boolean[this.size+1];
        
        for(int i=0; i<n; i++){

            this.hit[i]=false;
            
        }
    }
    
    public boolean isSunk(){
        for(int i=0; i<this.size;i++)
        {
            if(this.hit[i]==false)
                return false;
        }            
        return true;
    }
    
    /**
     * Gets the direction of a "chip".
     * @return the direction of a "chip".
     */
    public int getDirection() {
    	return direction;
    }
    
    /**
     * Gets the size of a "chip".
     * @return the size of a "chip".
     */
    public int getSize() {
    	return size;
    }
    
    public void setCoordonates(int row, int col){
        this.row=row;
        this.col=col;
    }
    
}
