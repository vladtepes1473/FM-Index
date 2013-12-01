package FMIndex;

import java.util.ArrayList;
import java.util.BitSet;


/*
 * @maja,matea
 * kao sto reko, mislim da je bolje da ova struktura
 * sadrzi bitset i da samo ona obavlja pretrazivanje
 * 
 * Ali me sad muci jedna stvar, a to je konstruktor
 * Sto mu predati?
 * Ako mu predamo bitset, on mora ponovo prolazit kroz
 * bit set da izracuna pojavljivanja za pretince i nadpretince
 * 
 * Ako mu predamo String (tj. stringwrapper)i pivota, onda on moze
 * sam izgradivati bitset i odma racunati broj jedinica 
 * (time bi trebali dobiti manju slozenost!!!!), ali mi to s
 * objektno orjentirane strane ne drzi bas vodu (zasto bi ta struktura
 * trebala znati kako mora gradit bitset).
 * 
 *Ja sam vise za ovu drugu opciju, ali eto, zanima me vase misljenje.
 * 
 */
public class RRR implements BitLookup {

	private BitSet bitString = new BitSet();
	private int bitStringLength;
	private int l;
	private ArrayList<Short> BS = new ArrayList<Short>(); // bucket string
	private ArrayList<Integer> SBS = new ArrayList<Integer>(); // super-bucket string
	
	public RRR(Character pivot, StringWrapper string){
		
		bitStringLength = string.length(); 
		l = calculateL(string.length(), 2);	 
		if (l==0) l=1; // xD -> l bude nula kada je duljina niza jedan znak, kasnije problem s dijeljenjem
		short bsCounter = 0;
		// creating bitString using given string
		for (int i=0, n=string.length() ; i<n ; i++) { 
			
			Character c = string.charAt(i); 
			c = Character.toUpperCase(c);
			pivot = Character.toUpperCase(pivot);
			

			
			int bitValue = 0;
		    if (c>=pivot) {
		    	bitString.set(i,true);		
		    	bitValue = 1;
		    } 
		    
		    
		    bsCounter += bitValue;
		    if ((i%l)==l-1) { // time for new bucket
		    	BS.add(bsCounter); // save value for old bucket
		    }

		    
		    if ((i%(l*l))==(l*l)-1) { // time for new super-bucket
		    	int newSBSValue;
		    	if (!SBS.isEmpty()) {
		    		newSBSValue = SBS.get(SBS.size()-1) + bsCounter;
		    		}
		    	else {
		    		newSBSValue = bsCounter;
		    	}
		    	SBS.add(newSBSValue); // save value for old super-bucket
		    	bsCounter = 0;
		    }
		}
		
		string = null;
		System.gc();


			
		//System.out.println(bitString.toString());
		//System.out.println(BS); 
		//System.out.println(SBS);
		//System.out.println("RANK: "+this.rank(15));

	}

	
	private int calculateL(Integer bitStringLength, Integer logarithmBase) {
		return (int)( Math.log(bitStringLength)/Math.log(logarithmBase) );
	}
	
	
	@Override
	public int rank(Integer position) {
		/*if(position == 0)
			return 1;
		else
			return 2;*/
		
		int superBucketRank;
		int bucketRank;
		int bitRank=0;
		
		int SBSindex = (position+1)/(l*l) - 1 ;
		if (SBSindex == -1) {  // nema prvog super-bloka
			superBucketRank = 0; 
		}
		else {
			superBucketRank = SBS.get(SBSindex);
		}
		
		
		int BSindex = (position+1)/l - 1 ;
		if (BSindex == -1  || (BSindex+1)%l == 0 ) {    // nema još nitijednog punog bloka ili se napunio cijeli super-bucket
			bucketRank = 0; 
		}
		else {
			bucketRank = BS.get(BSindex);
		}
		
	
		
		if ((position+1)%l == 0){ // zadnji bit u bloku, tj napunio se bucket
			bitRank = 0;
		}
		else {
			int beginIndex = (BSindex+1)*l; 
			bitRank = getBitRank(bitString.get(beginIndex, position+1));
			//bitRank = bitString.get(beginIndex, position+1).cardinality();

		}
			

		//System.out.println("superBucketRank: "+superBucketRank);
		//System.out.println("bucketRank: "+bucketRank);
		//System.out.println("bitRank: "+bitRank);
		int rank = superBucketRank + bucketRank + bitRank;
		return rank;
		
		
	}
	

	private int getBitRank(BitSet bitSubString) {	
		return bitSubString.cardinality();
		
	}


}
