/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlechips;

/**
 * This class represents a "chip" (ship) in the battleship game.
 * A "chip" is composed of a position, a direction, a size and list of state for each
 * cell that makes up the ship.
 * @class
 * @author Florian
 */
public class Chip {
	
	/** The column index of the "chip". */
	int col;
	
	/** The row index of the "chip". */
	int row;
	
	/**
	 * The direction of the "chip".
	 * 0 means horizontal (line) while 1 means vertical (column).
	 */
	int direction;
	
	/** The size of the "chip". */
	int size;
	
	/** The list of cell states for each part of the "chip". */
	boolean[] hit;
	
	/**
	 * Creates a new "chip" with a given size.
	 * @param n The size of the "chip".
	 * @constructor
	 */
	public Chip(int n) {
		direction = ((int)(Math.random() * 101)) % 2;
		size = n;
		hit = new boolean[size + 1];
		// Init cell states : not hit
		for (int i = 0; i < size; i++) {
			hit[i] = false;
		}
	}
	
	/**
	 * Returns whether a "chip" is sunk (all its cells are hit).
	 * @return true if the "chip" is sunk, false otherwise.
	 */
	public boolean isSunk() {
		for (int i = 0; i < this.size; i++) {
			if (!this.hit[i]) return false;
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
