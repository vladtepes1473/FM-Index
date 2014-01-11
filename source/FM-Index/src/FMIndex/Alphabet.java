package FMIndex;

/**
 * 
 * Alphabet interface for handling character occurrences
 *
 */
public interface Alphabet {
	
	/**
	 * Checks if the alphabet contains the character c
	 * @param c Character for which the occurrence in the alphabet will be checked
	 * @return Returns true if the alphabet contains the given character
	 */
	public boolean containsCharacter(Byte c);
	
	/**
	 * Gets the number of occurrences of the given character
	 * @param c Character for which the number of occurrences will be returned
	 * @return Number of occurrences for the given character
	 * @throws AlphabetException 
	 */
	public Integer getOccurancesForCharacter(Byte c) throws AlphabetException;
	
	/**
	 * Gets all the characters in the alphabet
	 * @return Array containing all the characters in the alphabet 
	 */
	public Byte[] getAllCharacters();
	
	/**
	 * Gets the size of the alphabet
	 * @return Size of the alphabet
	 */
	public int size();
	
	/**
	 * Splits the alphabet into two distinct alphabets by the pivot element
	 * @param pivot Element by which the alphabet will be split
	 * @return Array of the two splitted alphabets
	 */
	public Alphabet[] splitAlphabet(Byte pivot);
	
	/**
	 * Gets the index of the character in the alphabet
	 * @param character Character for which the index should be returned
	 * @return The index of the character in the alphabet (starting with 0)
	 * @throws AlphabetException 
	 */
	public Integer getCharacterIndex(Byte character) throws AlphabetException;
}
