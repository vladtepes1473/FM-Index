package FMIndex;

import java.util.Map;
import java.util.TreeMap;

public class ConcreteAlphabet implements Alphabet {

	private Map<Character,Integer> alphabet;
	
	public ConcreteAlphabet(StringWrapper string){
		alphabet = new TreeMap<Character, Integer>();
		char array[] = string.string.toCharArray();
		for(Character c : array){
			if(alphabet.containsKey(c)){
				alphabet.put(c, alphabet.get(c)+1);
			}
			else{
				alphabet.put(c, 1);
			}
		}
	}
	
	public boolean containsCharacter(Character c){
		return alphabet.containsKey(c);
	}
	
	public Integer getOccurancesForCharacter(Character c){
		return alphabet.get(c);
	}
	
	public Character[] getAllCharacters(){
		return alphabet.keySet().toArray(new Character[0]);
	}
	
	
}
