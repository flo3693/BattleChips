package battlechips;

/**
 * Represents the direction of a "chip".
 * @author Nicolas
 */
public enum Direction {
	HORIZONTAL, // LINE
	VERTICAL;   // COLUMN
	
	/**
	 * Returns the direction from an integer.
	 * @param direction The direction of a "chip".
	 * @return the direction associated with this integer.
	 */
	public static Direction fromInt(int direction) {
		if (direction <= 0) return HORIZONTAL;
		else return VERTICAL;
	}
}
