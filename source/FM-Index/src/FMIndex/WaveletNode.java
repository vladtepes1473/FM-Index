package FMIndex;


/**
 * Class that represents a single wavelet tree node.
 */
public class WaveletNode {

	private WaveletNode left = null;
	private WaveletNode right = null;
	private Character pivot;
	private BitLookup bitContent;
	
	/**
	 * Constructor for WaveletNode. Recursively builds the wavelet tree node and its subtrees.
	 * @param string - a string that that you want to build the wavelet tree node of
	 * @param alphabet - Alphabet object containing alphabet information of the given string
	 */
	public WaveletNode(StringWrapper string, Alphabet alphabet){
		Character[] allCharacters = alphabet.getAllCharacters();
		
		int alphabetSize = alphabet.size();
		/*
		 * If the alphabet's size is equal to 2, the pivot is the second character.
		 * Else, if alphabet's size is equal to 1, the pivot is that only character.
		 * When you do this, it means you have built a leaf of the wavelet tree and you can return.
		 */
		if(alphabetSize <= 2){
			if(alphabetSize == 2)
				pivot = allCharacters[1];
			else
				pivot = allCharacters[0];
			bitContent = new RRR(pivot, string);
			return;	
		}
		
		/*
		 * Find the middle of the string and put it in variable half.
		 */
		int half;
		if(alphabetSize % 2 == 0)
			half = alphabet.size() / 2 - 1;
		else
			half = alphabetSize / 2;
		
		/*
		 * Finding the pivot.
		 * Variable pivotIndex is an index of potential pivot in the alphabet's character array (which is
		 * a sorted array of all the characters that input string contains).
		 * The for loop iterates through the alpahabet's character array, adding to variable totalOccurencesSoFar
		 * each of the potential pivot's occurrence. The loop breaks when the totalOccurencesSoFar reaches the 
		 * value of half of the input string length; therefore finding the optimal pivot considering probability
		 * of it's occurrence in the string.
		 */
		int totalOccurencesSoFar = 0;
		int pivotIndex;
		for(pivotIndex = 0; totalOccurencesSoFar < half; pivotIndex++){
			totalOccurencesSoFar += alphabet.getOccurancesForCharacter(allCharacters[pivotIndex]);
		}
		/*
		 * The final check sees if found pivot is better pivot than the character that comes after it in the alphabet's
		 * character array (since the for loop stops when totalOccurencesSoFar < half).
		 */
		if( (string.length() - totalOccurencesSoFar) > 
			(string.length() + alphabet.getOccurancesForCharacter(allCharacters[pivotIndex + 1]) - totalOccurencesSoFar) &&
			pivotIndex < alphabet.size()-1){
				pivotIndex ++;
		}
		/*
		 * Building the RRR structure.
		 */
		this.pivot = allCharacters[pivotIndex];
		bitContent = new RRR(pivot, string);
		
		/*
		 * Building the contents of the child nodes.
		 */
		StringWrapper zeros = new StringWrapper();
		StringWrapper ones = new StringWrapper();
		StringBuilder buildZeros = new StringBuilder();
		StringBuilder buildOnes = new StringBuilder();
		
		for(int i = 0; i < string.length(); i++){
			if(string.charAt(i) < allCharacters[pivotIndex])
				buildZeros.append(string.charAt(i));
			else
				buildOnes.append(string.charAt(i));
		}
		
		zeros.string = buildZeros.toString();
		ones.string = buildOnes.toString();
		
		Alphabet[] splittedAlphabets = alphabet.splitAlphabet(pivot);

		/*
		 * Creating child nodes
		 */
		left = new WaveletNode(zeros, splittedAlphabets[0]);
		right = new WaveletNode(ones, splittedAlphabets[1]);
	}
	
	/**
	 * Gets character occurrence to given position.
	 * @param character
	 * @param position
	 * @return occurrence of given character 
	 */
	public int getCharacterOcurrance(Character character, Integer position){
		int rank = bitContent.rank(position);
		if(character < pivot){
			if(left != null)
				return left.getCharacterOcurrance(character,  position + 1 - rank - 1);
			else
				return position + 1 - rank;
		}
		else{
			if(right != null)
				return right.getCharacterOcurrance(character, rank - 1);
			else
				return rank;
		}
			
	}
}
