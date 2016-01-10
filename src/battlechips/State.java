/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlechips;

/**
 * Represents the game state of a board cell.
 * @author Florian, Nicolas
 */
public enum State {
	WATER,   // No "chip" on this cell.
	HIT,     // A "chip" was hit on the cell.
	UNKNOWN; // The cell was not fired yet.
	
	/**
	 * Gets the string representation of a cell state.
	 * @param cell The state of a cell.
	 * @return the string representing this cell state.
	 */
	public static String toString(State cell) {
		switch (cell) {
		case WATER:   return "o";
		case HIT:     return "x";
		case UNKNOWN: return ".";
		default:      return " ";
		}
	}
}
