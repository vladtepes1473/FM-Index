package FMIndex;

public class WaveletTree implements OccTable {

	WaveletNode root;
	
	public WaveletTree(StringWrapper string){
		root = new WaveletNode(string);
	}
	
	@Override
	public int getCharacterOcurrance(Character character, Integer position) {
		return root.getCharacterOcurrance(character, position);
	}	
}
