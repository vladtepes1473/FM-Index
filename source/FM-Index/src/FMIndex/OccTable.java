package FMIndex;

public interface OccTable {
	/**
	 * Returns character occurrence for given character and position in the string.
	 * @param character - character you want to find occurrence for
	 * @param position - last position to count character occurrence
	 * @return character occurrence
	 */
	int getCharacterOcurrance(byte character, Integer position);
	public Node getRoot();
}
