/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlechips;

/**
 * Represents the game state of a board cell.
 * @author Florian
 */
public enum State {
    WATER, // No "chip" on this cell.
    HIT, // A "chip" was hit on the cell.
    UNKNOWN; // 
}
