package FMIndex;

/**
 * 
 * Represents a string wrapper so that long
 * long strings can be passed to methods
 *
 */
public class StringWrapper {

	public String string;
	
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
	
	public char charAt(int index){
		return string.charAt(index);
	}
}
