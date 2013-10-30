package FMIndex;


/*
 * @maja: mislio sam si da prebacimo bitset u onu
 * strukturu RRR koja zapravo radi pretrazivanje nad tim
 * sta ti mislis o tome?
 * Po meni je to ipak bolje, jer je onda jedna klasa
 * zaduzena za pretrazivanje, a cvor samo pozove odgovarajucu
 * metodu
 */
public class WaveletNode {

	private Node left;
	private Node right;
	private Character pivot;
	
	//ovo bi trebalo mozda bolje nazvat :/
	private BitLookup lookup;
	
	public WaveletNode(StringWrapper string){
		
		
	}
	
}
