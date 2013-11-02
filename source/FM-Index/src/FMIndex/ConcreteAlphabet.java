package FMIndex;

import java.awt.RenderingHints.Key;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

public class ConcreteAlphabet implements Alphabet {

	private Map<Character,Integer> alphabet;
	private Map<Character, Integer> characterPositions;
	
	private ConcreteAlphabet(){
		alphabet = new TreeMap<Character, Integer>();
		characterPositions = new TreeMap<Character, Integer>();
	}
	
	public ConcreteAlphabet(StringWrapper string){
		alphabet = new TreeMap<Character, Integer>();
		characterPositions = new TreeMap<Character, Integer>();
		char[] array = string.string.toCharArray();
		for(Character c : array){
			if(alphabet.containsKey(c)){
				alphabet.put(c, alphabet.get(c)+1);
			}
			else{
				alphabet.put(c, 1);
			}
		}
		Character[] charArray = getAllCharacters();
		for(int i = 0; i < charArray.length; i++ ){
			characterPositions.put(charArray[i], i);
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

	@Override
	public int size() {
		return alphabet.keySet().size();
	}

	@Override
	public Alphabet[] splitAlphabet(Character pivot) {
		ConcreteAlphabet alphabet1 = new ConcreteAlphabet();
		ConcreteAlphabet alphabet2 = new ConcreteAlphabet();
		for(Entry<Character, Integer> pair : alphabet.entrySet()){
			if(pivot>pair.getKey()){
				alphabet1.alphabet.put(pair.getKey(), pair.getValue());
			}
			else{
				alphabet2.alphabet.put(pair.getKey(), pair.getValue());
			}
		}
		
		Alphabet alphabets[] = new Alphabet[2];
		
		alphabets[0] = alphabet1;
		alphabets[1] = alphabet2;
		return alphabets;
	}

	@Override
	public Integer getCharacterIndex(Character character) {
		int position = 0;
		position = characterPositions.get(character);
		return position;
	}
}
