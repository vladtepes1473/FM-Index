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
	 */
	public FMIndex(StringWrapper string) {
		this.alphabet = new ConcreteAlphabet(string);
		this.cTable = new DictionaryPrefixSumTable(this.alphabet);
		StringWrapper bwtString = new StringWrapper();
		long startTime = System.currentTimeMillis();
		
		for(byte b : alphabet.getAllCharacters()){
			System.out.println(((char)b)+": "+alphabet.getOccurancesForCharacter(b));
		}
		
		
		System.out.println(string.length());
//		char[] c = string.string.toCharArray();
//		ByteBuffer bb = Charset.forName("UTF-8").encode(CharBuffer.wrap(c));
//		byte[] b = new byte[bb.remaining()];
//		bb.get(b);
		StringWrapper sw = new StringWrapper();
		//byte[] bwt = new byte[string.length()];
		//sw.string = bwt;
		//new sais().bwtransform(b, bwt, new int[b.length], b.length);
		SaisBWT.PerformBWT(string, sw);
		//(new BWT()).performBWT(string, sw);
		
		string = null;
		System.gc();
		bwtString = new StringWrapper();
		bwtString.string = sw.string;
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Time:" + elapsedTime);
		startTime = System.currentTimeMillis();
		this.occTable = new WaveletTree(sw, alphabet);
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println("Time:" + elapsedTime);
		bwtString = null;
		}
	
	/**
	 * Performs the count algorithm on the index
	 * @param string The pattern which is to be counted by the index
	 * @return Number of the occurrences of the pattern
	 */
	public int Count(StringWrapper string){
		int position = string.length()-1;
		byte character = string.charAt(position);
		int characterIndex = alphabet.getCharacterIndex(character);
		int startPosition = cTable.getCharacterPrefixSum(characterIndex)+1;
		int endPosition = cTable.getCharacterPrefixSum(characterIndex+1);
		for(position = position-1; position>=0; position--){
			character = string.charAt(position);
			startPosition = cTable.getCharacterPrefixSum(character) + occTable.getCharacterOcurrance(character, startPosition-2)+1;
			endPosition = cTable.getCharacterPrefixSum(character) + occTable.getCharacterOcurrance(character, endPosition-1);
		}
		
		if(endPosition>=startPosition)
			return endPosition-startPosition+1;
		else
			return 0;
		
	}
	
}
