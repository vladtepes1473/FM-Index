package FMIndex;


/**
 * Represents the prefix sum data structure
 */
public interface PrefixSumTable {
	/**
	 * Gets the prefix sum of the given character
	 * @param c Character for which the prefix sum will be calculated
	 * @return Prefix sum of the character
	 */
	public Integer getCharacterPrefixSum(byte c);
	
	/**
	 * Gets the prefix sum of the character on the given index
	 * @param characterIndex Index of the character for which the prefix sum will be calculated
	 * @return Prefix sum of the character
	 */
	public Integer getCharacterPrefixSum(int characterIndex);
}
