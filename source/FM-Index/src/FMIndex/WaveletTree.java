package FMIndex;

public class WaveletTree implements OccTable {

	WaveletNode root;
	
	public WaveletTree(StringWrapper string){
		root = new WaveletNode(string);
	}
	
	
	@Override
	public int getCharacterOcurrance(Character character, Integer position) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}