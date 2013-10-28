package FMIndex;

import java.util.Map;
import java.util.TreeMap;

public class DictionaryPrefixSumTable implements PrefixSumTable {

	private Map<Character, Integer> table;
	
	public DictionaryPrefixSumTable(Alphabet alphabet){
		table = new TreeMap<Character, Integer>();
		Integer sum = 0;
		for(Character c : alphabet.getAllCharacters()){
			table.put(c,sum);
			sum += alphabet.getOccurancesForCharacter(c);
		}
	}
	
	public Integer getCharacterPrefixSum(Character c){
		return table.get(c);
	}
	
}
