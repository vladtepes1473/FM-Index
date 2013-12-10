package FMIndex;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * Class that implements compressed representation for the bit sequence
 * enabling O(1) rank queries
 */
public class RRR implements BitLookup {

	private BitSet bitString = new BitSet();
	private int bitStringLength;
	private int bucketSize;
	private ArrayList<Short> BS = new ArrayList<Short>(); 
	private ArrayList<Integer> SBS = new ArrayList<Integer>(); 
	
	/**
	 * Constructs bit sequence (bitString), buckets and super buckets for given string and pivot character
	 * @param pivot character used as pivot for creating bit sequence from given string
	 * @param string string that needs to be converted into bit sequence
	 */
	public RRR(Character pivot, StringWrapper string){
		
		bitStringLength = string.length(); 
		bucketSize = calculateBucketSize(string.length(), 2);	 
		
		short bsCounter = 0; // number of the set bits (bits with value 1) from the last filled super bucket
		
		/*
		 *  Creation of the bit sequence bitString using given string
		 */
		for (int i=0, n=string.length() ; i<n ; i++) { 
			
			Character c = string.charAt(i); 
			c = Character.toUpperCase(c);
			pivot = Character.toUpperCase(pivot);
			

			short bitValue = 0;
			/*
			 * If the string character is numerically larger or equal to pivot character,
			 * it is represented with 1 (default value of the BitSet bitString is 0)
			 */
		    if (c>=pivot) {
		    	bitString.set(i,true);		
		    	bitValue = 1;
		    } 
		    
		    
		    bsCounter += bitValue;
		    /*
		     * If the bucket is full, save the value of the bsCounter as a bucket value in the list of all buckets BS
		     */
		    if ((i%bucketSize)==bucketSize-1) {
		    	BS.add(bsCounter); 
		    }

		    
		   /*
		    * If the super bucket is full, save super bucket value in the list of all super buckets SBS
		    * After saving super bucket value, set bsCounter to zero because after the super bucket is created,
		    * counting the number of set bits must start from beginning
		    */
		    if ((i%(bucketSize*bucketSize))==(bucketSize*bucketSize)-1) { 
		    	int newSBSValue;
		    	/*
		    	 * If there are already some super buckets in the SBS list, value of the new super bucket is 
		    	 * sum of the value of the last saved super bucket and the number of set bits (counting from
		    	 * the last super bucket).
		    	 */
		    	if (!SBS.isEmpty()) {
		    		newSBSValue = SBS.get(SBS.size()-1) + bsCounter;
		    		}
		    	else {
		    		newSBSValue = bsCounter;
		    	}
		    	SBS.add(newSBSValue); 
		    	bsCounter = 0;
		    }
		}
		
		string = null;
		System.gc();

	}

	
	/**
	 * Calculates bucket size 
	 * @param bitStringLength length of the bit sequence
	 * @param logarithmBase 
	 * @return
	 */
	private int calculateBucketSize(Integer bitStringLength, Integer logarithmBase) {
		
		int size = (int)( Math.log(bitStringLength)/Math.log(logarithmBase) );
		
		/* Due to usage of logarithm, when the bitStringLength is equal 1,
		 * calculated bucket size will be 0, so it is necessary to manually 
		 * change bucket size value to 1 */
		if (size == 0) 
			size = 1;
		
		return size;
	}
	
	
	@Override
	public int rank(Integer position) {
		
		int superBucketRank;
		int bucketRank;
		int bitRank=0;
		
		/*
		 * Get rank value from the super bucket
		 */
		int SBSindex = (position+1)/(bucketSize*bucketSize) - 1 ;
		if (SBSindex == -1) {  // first super bucket is not created yet
			superBucketRank = 0; 
		}
		else {
			superBucketRank = SBS.get(SBSindex);
		}
		
		/*
		 * Get rank value from the bucket
		 */
		int BSindex = (position+1)/bucketSize - 1 ;
		if (BSindex == -1  || (BSindex+1)%bucketSize == 0 ) {    
			// no bucket is filled yet, or the super bucket is just filled 
			bucketRank = 0; 
		}
		else {
			bucketRank = BS.get(BSindex);
		}
		
	
		/*
		 * Get rank value from the part of the sequence that is not covered with bucket or super bucket
		 */
		if ((position+1)%bucketSize == 0){ 
			// the last bit in the bucket which means that one bucket is filled and its value already added to rank
			bitRank = 0;
		}
		else {
			int beginIndex = (BSindex+1)*bucketSize; 
			bitRank = getBitRank(bitString.get(beginIndex, position+1));
		}
			

		int rank = superBucketRank + bucketRank + bitRank;
		return rank;
		
		
	}
	
	
	/**
	 * Returns rank value of the given bit sequence
	 * @param bitSubString bit sequence for which rank is calculated
	 * @return rank value of the given bit sequence
	 */
	private int getBitRank(BitSet bitSubString) {	
		return bitSubString.cardinality();
		
	}


}
