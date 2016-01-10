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
        this.direction = (int) (Math.random() * 2);
       
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
     * Returns the column index of a "chip"
     * @return the column index of a "chip" (0-based)
     */
    public int getCol() {
    	return col;
    }
    
    /**
     * Returns the row index of a "chip"
     * @return the row index of a "chip" (0-based)
     */
    public int getRow() {
    	return row;
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
    
    /**
     * Sets the coordinates of a "chip".
     * @param row The row index of the "chip".
     * @param col The column index of the "chip".
     */
    public void setCoordonates(int row, int col) {
    	// Clamp input indices in [0,9]
    	if (row < 0) row = 0;
    	else if (row > 9) row = 9;
        if (col < 0) col = 0;
        else if (col > 9) col = 9;
        // Set the "chip" coordinates
        this.col = col;
        this.row = row;
    }
    
    /**
     * Sets the direction of the "chip".
     * @param direction The direction of the "chip".
     */
    public void setDirection(int direction) {
    	this.direction = direction <= 0 ? 0 : 1;
    }
    
}
