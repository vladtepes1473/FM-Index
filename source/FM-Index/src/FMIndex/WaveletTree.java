package FMIndex;

public class WaveletTree implements OccTable {

	WaveletNode root;
	
	public WaveletTree(StringWrapper string, Alphabet alphabet){
		root = new WaveletNode(string, alphabet);
	}
	
	@Override
	public int getCharacterOcurrance(Character character, Integer position) {
		return root.getCharacterOcurrance(character, position);
	}	
}
