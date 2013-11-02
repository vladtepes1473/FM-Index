package FMIndex;


public class WaveletNode {

	private WaveletNode left = null;
	private WaveletNode right = null;
	private Character pivot;
	private BitLookup bitContent;
	private Alphabet alphabet;
	//TODO alphabet
	public WaveletNode(StringWrapper string, Alphabet alphabet){
		Character[] allCharacters = alphabet.getAllCharacters();
		
		int alphabetSize = alphabet.size();
		if(alphabetSize <= 2){
			if(alphabetSize == 2)
				pivot = allCharacters[1];
			else
				pivot = allCharacters[0];
			bitContent = new RRR(pivot, string);
			return;	
		}
		int half;
		if(alphabetSize % 2 == 0)
			half = alphabet.size() / 2 - 1;
		else
			half = alphabetSize / 2;
		
		int totalOccurencesSoFar = 0;
		
		int pivotIndex;
		for(pivotIndex = 0; pivotIndex < half; pivotIndex++){
			totalOccurencesSoFar += alphabet.getOccurancesForCharacter(allCharacters[pivotIndex]);
		}

		if( (string.length() - totalOccurencesSoFar) > 
			(string.length() + alphabet.getOccurancesForCharacter(allCharacters[pivotIndex + 1]) - totalOccurencesSoFar) ){
				pivotIndex ++;
		}
		
		this.pivot = allCharacters[pivotIndex];
		bitContent = new RRR(pivot, string);
		
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
		

		left = new WaveletNode(zeros, splittedAlphabets[0]);
		right = new WaveletNode(ones, splittedAlphabets[1]);
	}
	
	public int getCharacterOcurrance(Character character, Integer position){
		int rank = bitContent.rank(position);
		if(character < pivot){
			if(left != null)
				return left.getCharacterOcurrance(character,  position - rank-1);
			else
				return position - rank;
		}
		else{
			if(right != null)
				return right.getCharacterOcurrance(character, rank-1);
			else
				return rank;
		}
			
	}
}
