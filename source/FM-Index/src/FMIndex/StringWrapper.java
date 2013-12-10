package FMIndex;

/**
 * 
 * Represents a string wrapper so that long
 * long strings can be passed to methods
 *
 */
public class StringWrapper {

	public String string;
	
	
	/**
	 * Returns the string enclosed in the StringWrapper class
	 * @return Length of the string
	 */
	public int length(){
		return string.length();
	}
	
	/**Splits the string wrapper into two parts: { string[ 0, position - 1 ], string[ position, end ]}
	 * @param position
	 * @return Splitted string wrapper
	 */
	public StringWrapper[] split(int position){
		StringWrapper first = new StringWrapper();
		first.string = this.string.substring(0, position);
		StringWrapper second = new StringWrapper();
		second.string = string.substring(position, string.length());
		return new StringWrapper[]{first, second};
	}
	
	/**
	 * Returns the character at the specified position in the string
	 * @param index Index of the requested character
	 * @return Character at the given position
	 */
	public char charAt(int index){
		return string.charAt(index);
	}
}
