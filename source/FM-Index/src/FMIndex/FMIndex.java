package FMIndex;

public class FMIndex {
	
	private Alphabet alphabet;
	private OccTable occTable;
	private PrefixSumTable cTable;
	
	public FMIndex(StringWrapper string){
		this.alphabet = new ConcreteAlphabet(string);
		this.cTable = new DictionaryPrefixSumTable(this.alphabet);
		StringWrapper bwtString = new StringWrapper();
		BWT.performBTW(string, bwtString);
		this.occTable = new WaveletTree(bwtString, alphabet);
		
	}
	
	public int Count(StringWrapper string){
		int position = string.string.length()-1;
		Character character = string.string.charAt(position);
		int characterIndex = alphabet.getCharacterIndex(character);
		int startPosition = cTable.getCharacterPrefixSum(characterIndex)+1;
		int endPosition = cTable.getCharacterPrefixSum(characterIndex+1);
		for(position = position-1; position>=0; position--){
			character = string.string.charAt(position);
			int bla = cTable.getCharacterPrefixSum(character) ;
			int ll = occTable.getCharacterOcurrance(character, startPosition-1);
			startPosition = cTable.getCharacterPrefixSum(character) + occTable.getCharacterOcurrance(character, startPosition-1);
			endPosition = cTable.getCharacterPrefixSum(character) + occTable.getCharacterOcurrance(character, endPosition);
		}
		
		if(endPosition>=startPosition)
			return endPosition-startPosition+1;
		else
			return 0;
		
	}
	
}
