package FMIndex;

/**
 * 
 * Represents a string wrapper so that long
 * long strings can be passed to methods
 *
 */
public class StringWrapper {

	public byte[] string;
	
	
	/**
	 * Returns the string enclosed in the StringWrapper class
	 * @return Length of the string
	 */
	public int length(){
		return string.length;
	}
	

	
	/**
	 * Returns the character at the specified position in the string
	 * @param index Index of the requested character
	 * @return Character at the given position
	 */
	public byte charAt(int index){
		return string[index];
	}
}
