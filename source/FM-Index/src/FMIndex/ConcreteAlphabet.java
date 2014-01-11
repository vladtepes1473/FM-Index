package FMIndex;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ConcreteAlphabet implements Alphabet {

	private Map<Byte,Integer> alphabet;
	private Map<Byte, Integer> characterPositions;
	
	private ConcreteAlphabet(){
		alphabet = new TreeMap<Byte, Integer>();
		characterPositions = new TreeMap<Byte, Integer>();
	}
	
	public ConcreteAlphabet(StringWrapper string){
		alphabet = new TreeMap<Byte, Integer>();
		characterPositions = new TreeMap<Byte, Integer>();
		byte[] array = string.string;
		for(byte c : array){
			if(alphabet.containsKey(c)){
				alphabet.put(c, alphabet.get(c)+1);
			}
			else{
				alphabet.put(c, 1);
			}
		}
		Byte[] charArray = getAllCharacters();
		for(int i = 0; i < charArray.length; i++ ){
			characterPositions.put(charArray[i], i);
		}
	}
	
	public boolean containsCharacter(Byte c){
		return alphabet.containsKey(c);
	}
	
	public Integer getOccurancesForCharacter(Byte c) throws AlphabetException{
		if(!containsCharacter(c))
			throw new AlphabetException("Alphabet does not contain such a character!");
		return alphabet.get(c);
	}
	
	public Byte[] getAllCharacters(){
		return alphabet.keySet().toArray(new Byte[0]);
	}

	@Override
	public int size() {
		return alphabet.keySet().size();
	}

	@Override
	public Alphabet[] splitAlphabet(Byte pivot) {
		ConcreteAlphabet alphabet1 = new ConcreteAlphabet();
		ConcreteAlphabet alphabet2 = new ConcreteAlphabet();
		for(Entry<Byte, Integer> pair : alphabet.entrySet()){
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
	public Integer getCharacterIndex(Byte character) throws AlphabetException {
		if(!characterPositions.containsKey(character)){
			throw new AlphabetException("No such character exists in the alphabet");
		}
		int position = 0;
		position = characterPositions.get(character);
		return position;
	}
}
