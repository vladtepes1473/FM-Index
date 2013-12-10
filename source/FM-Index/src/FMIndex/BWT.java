package FMIndex;

/**
 * Class for performing the BWT transformation
 */
public class BWT {
	
	/*
	 * Static fields used by the algorithm
	 */
	private static int[] pointers;
	private static String inputString;
	private static int partIndex;
	private static int swapIndex;
	private static int swapLength;
	private static char partitionCharacter;
	private static char currentCharacter;
	private static StringBuilder outputStringBuilder;
	
	/**
	 * Performs the BWT on the given string
	 * @param inputString the string wrapper on which the BWT should be performed
	 * @param outputString the string wrapper where the transformed string will be saved
	 */
	public static void performBWT(StringWrapper inputString, StringWrapper outputString){
		BWT.inputString = inputString.string;
		inputString.string = null;
		System.gc();
		int stringLength = BWT.inputString.length();
		
		// We initialize the array of integer pointers on the string 
		pointers = new int[stringLength];
		for(int i = 0; i < stringLength; i++){
			pointers[i] = i;
		}
		
		//Perform the multikey quicksort
		sort(0, stringLength, 0);
		
		
		outputStringBuilder = new StringBuilder();
		for(int i = 0; i < stringLength; i++){
			outputStringBuilder.append(BWT.inputString.charAt((pointers[i]+stringLength-1)%stringLength));
		}
		outputString.string = outputStringBuilder.toString();
		inputString= null;
		pointers = null;
		outputStringBuilder= null;
		System.gc();
	}
	
	
	/**
	 * Sorts the BWT strings using the Mulitkey Quicksort
	 * @param pstart Start index of the array which will be sorted
	 * @param pLength Length of the array which will be sorted
	 * @param compIndex Index of the string by which we are currently sorting all the BWT strings
	 */
	private static void sort(int pstart, int pLength, int compIndex){
		int a;
		int b;
		int c;
		int d;

			
		if(pLength<=1)
			return;
		
		partIndex = pstart + (int)(Math.random()*(double)pLength)%pLength;
		swapElements(pstart, partIndex);
		
		partitionCharacter = inputString.charAt((pointers[pstart]+compIndex)%inputString.length());
		
		a=pstart + 1;
		b = a;
		c = pstart + pLength - 1;
		d = c;
		
		while(b<=c){
			do{
				currentCharacter = inputString.charAt((pointers[b]+compIndex)%inputString.length());
				if(b<=c && currentCharacter <= partitionCharacter){
					if(currentCharacter == partitionCharacter){
						swapElements(a, b);
						a++;
					}
					b++;
				}
			}while(b<=c&&currentCharacter <= partitionCharacter);
			
			do{
				currentCharacter = inputString.charAt((pointers[c]+compIndex)%inputString.length());
				if(b<=c&& currentCharacter >= partitionCharacter){
					if(currentCharacter == partitionCharacter){
						swapElements(c,d);
						d--;
					}
					c--;
				}
			}while(b<=c&&currentCharacter>=partitionCharacter);
			
			if(b<=c){
				swapElements(b, c);
				b++;
				c--;
			}
			
		}
		
		swapLength = a-pstart;
		swapLength = (swapLength <= (b-a)?swapLength:b-a);
		for(swapIndex = 0; swapIndex< swapLength; swapIndex++){
			swapElements(pstart+swapIndex, b - swapLength+swapIndex);
		}
		
		swapLength = pLength - (d+1-pstart);
		swapLength = (swapLength<=(d-c)? swapLength:d-c);
		for(swapIndex = 0; swapIndex < swapLength; swapIndex++){
			swapElements(b+swapIndex, pstart+pLength-swapLength+swapIndex);
		}
		
		sort(pstart, b-a, compIndex);
		
		if(compIndex<(inputString.length()-1)){
			sort(pstart+b-a,(a-pstart)+(pLength-(d+1-pstart)), compIndex+1);
		}
		
		sort(pstart+pLength-(d-c), d-c, compIndex);	
	}
	
	/**
	 * Swaps two elements in the pointer array
	 * @param firstIndex Index of the first pointer
	 * @param secondIndex Index of the second pointer
	 */
	private static void swapElements(int firstIndex, int secondIndex){
		int temp = pointers[firstIndex];
		pointers[firstIndex] = pointers[secondIndex];
		pointers[secondIndex] = temp;
	}
}
