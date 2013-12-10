package FMIndex;

public interface BitLookup {
	
	/**
	 * Returns rank of the bit sequence up to the given position
	 * @param position position of the final bit of the bit sequence that is included in rank calculation
	 * @return rank of the bit sequence 
	 */
	int rank(Integer position);
	
}
