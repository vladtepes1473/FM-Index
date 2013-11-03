package FMIndex;

public interface Alphabet {
	public boolean containsCharacter(Character c);
	public Integer getOccurancesForCharacter(Character c);
	public Character[] getAllCharacters();
	public int size();
	public Alphabet[] splitAlphabet(Character pivot);
	public Integer getCharacterIndex(Character character);
}
