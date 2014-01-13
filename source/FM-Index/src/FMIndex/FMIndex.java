package FMIndex;

/**
 * Class for building the FM index structure
 *
 */
public class FMIndex {
	
	private Alphabet alphabet;
	private OccTable occTable;
	private PrefixSumTable cTable;
	
	/**
	 * Builds the FM-index on the given StringWrapper
	 * @param string The Stringwrapper on which the index should be built
	 * @throws AlphabetException 
	 */
	public FMIndex(StringWrapper string) throws AlphabetException {
		this.alphabet = new ConcreteAlphabet(string);
		this.cTable = new DictionaryPrefixSumTable(this.alphabet);
		long startTime = System.currentTimeMillis();
		
		StringWrapper sw = new StringWrapper();
		//SaisBWT.PerformBWT(string, sw);
		BWT.performBWT(string, sw);
		
		string = null;
		System.gc();

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Time BWT:" + elapsedTime);
		startTime = System.currentTimeMillis();
		this.occTable = new WaveletTree(sw, alphabet);
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println("Time wavelet:" + elapsedTime);
	}
	
	/**
	 * Performs the count algorithm on the index
	 * @param string The pattern which is to be counted by the index
	 * @return Number of the occurrences of the pattern
	 */
	public int Count(StringWrapper string){
		int position = string.length()-1;
		byte character = string.charAt(position);
		int characterIndex=0;
		if(!alphabet.containsCharacter(character))
			return 0;
		try{
			characterIndex = alphabet.getCharacterIndex(character);}
		catch(AlphabetException e){
			return 0;
		}
		int startPosition = cTable.getCharacterPrefixSum(characterIndex)+1;
		int endPosition = cTable.getCharacterPrefixSum(characterIndex+1);
		for(position = position-1; position>=0; position--){
			character = string.charAt(position);
			if(!alphabet.containsCharacter(character))
				return 0;
			startPosition = cTable.getCharacterPrefixSum(character) + occTable.getCharacterOcurrance(character, startPosition-2)+1;
			endPosition = cTable.getCharacterPrefixSum(character) + occTable.getCharacterOcurrance(character, endPosition-1);
		}
		
		if(endPosition>=startPosition)
			return endPosition-startPosition+1;
		else
			return 0;
		
	}
	
}
