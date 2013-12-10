package FMIndex;

import java.util.Map;
import java.util.TreeMap;

/**
 * Represents the prefix sum table
 *
 */
public class DictionaryPrefixSumTable implements PrefixSumTable {

	private Map<Character, Integer> table;
	private Integer[] occurance;
	
	/**
	 * Builds the prefix sum table on the given alphabet
	 * @param alphabet Alpgabet on which the prefix sum table will be built
	 */
	public DictionaryPrefixSumTable(Alphabet alphabet){
		table = new TreeMap<Character, Integer>();
		Integer sum = 0;
		Integer position = 0;
		Character[] array = alphabet.getAllCharacters();
		occurance = new Integer[array.length+1];
		for(Character c : array){
			table.put(c,sum);
			occurance[position] = sum;
			sum += alphabet.getOccurancesForCharacter(c);
			position++;
		}
		occurance[position] = sum;
	}
	
	public Integer getCharacterPrefixSum(Character c){
		return table.get(c);
	}

	@Override
	public Integer getCharacterPrefixSum(int characterIndex) {
		return occurance[characterIndex];
	}
	
}
