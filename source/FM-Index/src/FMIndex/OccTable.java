package FMIndex;

public interface OccTable {
	/**
	 * Returns character occurence for given character and position in the string.
	 * @param character - character you want to find occurence for
	 * @param position - last position to count character occurence
	 * @return character occurence
	 */
	int getCharacterOcurrance(Character character, Integer position);
}
