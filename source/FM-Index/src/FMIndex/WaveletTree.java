package FMIndex;

/**
 * Class that represents a wavelet tree.
 */
public class WaveletTree implements OccTable {

	WaveletNode root;
	
	/**
	 * Constructor for WaveletTree.
	 * @param string - input string that you want to build the wavelet tree of
	 * @param alphabet - Alphabet object containing alphabet information of the given input string
	 */
	public WaveletTree(StringWrapper string, Alphabet alphabet){
		root = new WaveletNode(string, alphabet);
	}
	
	/**
	 * @return root
	 */
	public Node getRoot(){
		return root;
	}
	
	@Override
	public int getCharacterOcurrance(byte character, Integer position) {
		return root.getCharacterOcurrance(character, position);
	}	
}
